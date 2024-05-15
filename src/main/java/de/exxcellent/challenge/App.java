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
     * @throws IOException
     */
    public static void main(String... args) throws IOException {
        List<Day> weatherData = readWeatherData("weather.csv");
        Day dayWithSmallestTempSpread = getDayWithSmallestTempSpread(weatherData);

        System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread.getNumber());
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
                days.add(new Day(day, Integer.parseInt(mxt), Integer.parseInt(mnt)));
            }
        }

        return days;
    }
}

enum WeatherDataHeader {
    name, mxt, mnt
}

class Day {
    private String number;
    private int mxt;
    private int mnt;

    Day(String number, int mxt, int mnt) {
        this.number = number;
        this.mxt = mxt;
        this.mnt = mnt;
    }

    int getTemperatureSpread() {
        return mxt - mnt;
    }

    String getNumber() {
        return number;
    }
}
