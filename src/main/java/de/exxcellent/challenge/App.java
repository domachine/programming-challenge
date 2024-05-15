package de.exxcellent.challenge;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 * The entry class for your solution. This class is only aimed as starting point
 * and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {
    /**
     * This is the main entry method of your program.
     * 
     * @param args The CLI arguments passed
     * @throws Exception
     */
    public static void main(String... args) throws Exception {
        if (args.length != 2) {
            throw new Exception("invalid number of arguments");
        }
        if (args[0].equals("--weather")) {
            List<Day> weatherData = readWeatherData(args[1]);
            Day dayWithSmallestTempSpread = getDayWithSmallestTempSpread(weatherData);
            System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread.getNumber());
        } else if (args[0].equals("--football")) {
            List<FootballResult> footballResults = readFootballData(args[1]);
            FootballResult footballResultWithSmallestGoalSpread = getFootballResultWithSmallestGoalDifference(
                    footballResults);
            System.out.printf("Team with smallest goal spread       : %s%n",
                    footballResultWithSmallestGoalSpread.getTeam());
        } else {
            throw new Exception("unknown processing mode\n\nusage: --weather file.csv | --football football.csv");
        }
    }

    /**
     * This method contains the algorithm to find the day with the smallest
     * temperature spread in a list of days.
     * 
     * @param days
     * @return
     */
    static Day getDayWithSmallestTempSpread(List<Day> days) {
        Day dayWithSmallestTempSpread = null;
        int min = Integer.MAX_VALUE;
        for (Day day : days) {
            if (day.getTemperatureSpread() < min) {
                min = day.getTemperatureSpread();
                dayWithSmallestTempSpread = day;
            }
        }
        return dayWithSmallestTempSpread;
    }

    /**
     * This method is responsible for reading the input csv an turning it into a
     * list of days. It is probably better placed in a separate "Driver" class. But
     * for the moment I leave it here for simplicity reasons.
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    static List<Day> readWeatherData(String fileName) throws IOException {
        InputStream is = App.class.getResourceAsStream(fileName);
        ArrayList<Day> days = new ArrayList<>();

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(WeatherDataHeader.class)
                .setSkipHeaderRecord(true)
                .build();

        try (Reader reader = new InputStreamReader(is)) {
            Iterable<CSVRecord> records = csvFormat.parse(reader);
            for (CSVRecord record : records) {
                String day = record.get(WeatherDataHeader.name);
                String mxt = record.get(WeatherDataHeader.mxt);
                String mnt = record.get(WeatherDataHeader.mnt);
                days.add(new Day(Integer.parseInt(day), Integer.parseInt(mxt), Integer.parseInt(mnt)));
            }
        }

        return days;
    }

    public static List<FootballResult> readFootballData(String fileName) throws IOException {
        InputStream is = App.class.getResourceAsStream(fileName);
        ArrayList<FootballResult> results = new ArrayList<>();

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(FootballDataHeader.class)
                .setSkipHeaderRecord(true)
                .build();

        try (Reader reader = new InputStreamReader(is)) {
            Iterable<CSVRecord> records = csvFormat.parse(reader);
            for (CSVRecord record : records) {
                results.add(new FootballResult(record.get(FootballDataHeader.team),
                        Integer.parseInt(record.get(FootballDataHeader.goals)),
                        Integer.parseInt(record.get(FootballDataHeader.goalsAllowed))));
            }
        }

        return results;
    }

    public static FootballResult getFootballResultWithSmallestGoalDifference(List<FootballResult> footballData) {
        FootballResult resultWithSmallestDistance = null;
        int min = Integer.MAX_VALUE;
        for (FootballResult result : footballData) {
            if (result.getDistance() < min) {
                min = result.getDistance();
                resultWithSmallestDistance = result;
            }
        }
        return resultWithSmallestDistance;
    }
}

enum WeatherDataHeader {
    name, mxt, mnt
}

enum FootballDataHeader {
    team, games, wins, losses, draws, goals, goalsAllowed, points
}

class Day {
    private int number;
    private int maximum;
    private int minimum;

    Day(int number, int maximum, int minimum) {
        this.number = number;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    int getTemperatureSpread() {
        return maximum - minimum;
    }

    int getNumber() {
        return number;
    }
}

class FootballResult {
    private String team;
    private int goals;
    private int goalsAllowed;

    FootballResult(String team, int goals, int goalsAllowed) {
        this.team = team;
        this.goals = goals;
        this.goalsAllowed = goalsAllowed;
    }

    String getTeam() {
        return team;
    }

    int getDistance() {
        return Math.abs(goals - goalsAllowed);
    }
}
