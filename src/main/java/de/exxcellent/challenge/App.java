package de.exxcellent.challenge;

import java.util.Optional;

import de.exxcellent.challenge.football.FootballCsvReader;
import de.exxcellent.challenge.football.FootballResult;
import de.exxcellent.challenge.football.FootballResultsAnalyzer;
import de.exxcellent.challenge.weather.Day;
import de.exxcellent.challenge.weather.WeatherAnalyzer;
import de.exxcellent.challenge.weather.WeatherCsvReader;

/**
 * This is the main entry class of the program. Since there are no requirements
 * specified how the output should look like I keep this rather simple and
 * extensible. Means throwing exceptions instead of outputting nicely formatted
 * error messages.
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
            WeatherAnalyzer analyzer = new WeatherAnalyzer(
                    new WeatherCsvReader(App.class.getResourceAsStream(args[1])));
            Optional<Day> dayWithSmallestTempSpread = analyzer.getDayWithSmallestTempSpread();
            System.out.printf("Day with smallest temperature spread : %s%n",
                    dayWithSmallestTempSpread.orElseThrow(() -> new Exception("no lines from input received"))
                            .getNumber());
        } else if (args[0].equals("--football")) {
            FootballResultsAnalyzer analyzer = new FootballResultsAnalyzer(
                    new FootballCsvReader(App.class.getResourceAsStream(args[1])));
            Optional<FootballResult> footballResultWithSmallestGoalSpread = analyzer
                    .getFootballResultWithSmallestGoalDifference();
            System.out.printf("Team with smallest goal spread       : %s%n",
                    footballResultWithSmallestGoalSpread
                            .orElseThrow(() -> new Exception("no lines from input received")).getTeam());
        } else {
            throw new Exception("unknown processing mode\n\nusage: --weather file.csv | --football football.csv");
        }
    }
}
