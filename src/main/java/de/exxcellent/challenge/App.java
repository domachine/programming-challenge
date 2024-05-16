package de.exxcellent.challenge;

import java.io.IOException;
import java.io.InputStream;

import de.exxcellent.challenge.football.FootballCsvReader;
import de.exxcellent.challenge.football.FootballResultsAnalyzer;
import de.exxcellent.challenge.weather.WeatherAnalyzer;
import de.exxcellent.challenge.weather.WeatherCsvReader;

/**
 * This is the main entry class of the program.
 *
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
public final class App {
    /**
     * This is the main entry method of the program.
     * 
     * @param args The CLI arguments passed
     * @throws Exception
     */
    public static void main(String... args) throws Exception {
        if (args.length != 2) {
            System.err.printf("invalid number of arguments%n%nusage: --weather file.csv | --football file.csv%n");
            System.exit(1);
        }
        try {
            if (args[0].equals("--weather")) {
                new WeatherAnalyzer(
                        new WeatherCsvReader(readResourceFile(args[1])))
                        .getDayWithSmallestTempSpread()
                        .ifPresentOrElse((day) -> {
                            System.out.printf("day with smallest temperature spread : %s%n",
                                    day.getNumber());
                        }, () -> {
                            System.out.println("no input lines");
                        });
            } else if (args[0].equals("--football")) {
                new FootballResultsAnalyzer(
                        new FootballCsvReader(readResourceFile(args[1])))
                        .getFootballResultWithSmallestGoalDifference()
                        .ifPresentOrElse((result) -> {
                            System.out.printf("team with smallest goal spread       : %s%n",
                                    result.getTeam());
                        }, () -> {
                            System.out.println("no input lines");
                        });
            } else {
                System.err.printf("unknown processing mode%n%nusage: --weather file.csv | --football file.csv%n");
                System.exit(1);
            }
        } catch (IOException e) {
            System.err.printf("failed to read input source: %s%n", e.getMessage());
            System.exit(1);
        }
    }

    private static InputStream readResourceFile(String fileName) throws IOException {
        InputStream stream = App.class.getResourceAsStream(fileName);
        if (stream == null) {
            throw new IOException("resource file not found");
        }
        return stream;
    }
}
