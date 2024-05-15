package de.exxcellent.challenge;

import java.util.List;

import de.exxcellent.challenge.football.FootballResult;
import de.exxcellent.challenge.football.FootballResultsAnalyzer;
import de.exxcellent.challenge.weather.Day;
import de.exxcellent.challenge.weather.WeatherAnalyzer;

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
            WeatherAnalyzer analyzer = new WeatherAnalyzer();
            List<Day> weatherData = analyzer.readWeatherData(App.class.getResourceAsStream(args[1]));
            Day dayWithSmallestTempSpread = analyzer.getDayWithSmallestTempSpread(weatherData);
            System.out.printf("Day with smallest temperature spread : %s%n", dayWithSmallestTempSpread.getNumber());
        } else if (args[0].equals("--football")) {
            FootballResultsAnalyzer analyzer = new FootballResultsAnalyzer();
            List<FootballResult> footballResults = analyzer.readFootballData(App.class.getResourceAsStream(args[1]));
            FootballResult footballResultWithSmallestGoalSpread = analyzer.getFootballResultWithSmallestGoalDifference(
                    footballResults);
            System.out.printf("Team with smallest goal spread       : %s%n",
                    footballResultWithSmallestGoalSpread.getTeam());
        } else {
            throw new Exception("unknown processing mode\n\nusage: --weather file.csv | --football football.csv");
        }
    }
}
