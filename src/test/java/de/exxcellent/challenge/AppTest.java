package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import de.exxcellent.challenge.football.FootballCsvReader;
import de.exxcellent.challenge.football.FootballResult;
import de.exxcellent.challenge.football.FootballResultsAnalyzer;
import de.exxcellent.challenge.weather.WeatherCsvReader;
import de.exxcellent.challenge.weather.Day;
import de.exxcellent.challenge.weather.WeatherAnalyzer;

/**
 * Example JUnit 5 test case.
 * 
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {
    @TestFactory
    Stream<DynamicTest> weatherCsvTests() {
        List<WeatherTestCase> testCases = Arrays.asList(
                new WeatherTestCase("weather.csv", 14),
                new WeatherTestCase("weather_sample_2.csv", 2));

        return testCases.stream().map(testCase -> DynamicTest.dynamicTest(testCase.getFile(), () -> {
            WeatherAnalyzer analyzer = new WeatherAnalyzer(
                    new WeatherCsvReader(App.class.getResourceAsStream(testCase.getFile())));
            Day day = analyzer.getDayWithSmallestTempSpread();
            assertEquals(testCase.getExpected(), day.getNumber());
        }));
    }

    @TestFactory
    Stream<DynamicTest> footballCsvTests() {
        List<FootballTestCase> testCases = Arrays.asList(
                new FootballTestCase("football.csv", "Aston_Villa"),
                new FootballTestCase("football_sample_2.csv", "Liverpool"));

        return testCases.stream().map(testCase -> DynamicTest.dynamicTest(testCase.getFile(), () -> {
            FootballResultsAnalyzer analyzer = new FootballResultsAnalyzer(
                    new FootballCsvReader(App.class.getResourceAsStream(testCase.getFile())));
            FootballResult result = analyzer.getFootballResultWithSmallestGoalDifference();
            assertEquals(testCase.getExpected(), result.getTeam());
        }));
    }
}

/**
 * This is a tiny class to hold information about a weather test case. I like it
 * to have
 * multiple chunks of sample data in a format which is consumed by the
 * application (in this case csv). So it's probably more an integration test
 * than a unit test. But
 * I think this makes the test more robust against further refactorings of the
 * code.
 */
class WeatherTestCase {
    private String file;
    private int expected;

    WeatherTestCase(String file, int expected) {
        this.file = file;
        this.expected = expected;
    }

    String getFile() {
        return this.file;
    }

    int getExpected() {
        return this.expected;
    }
}

/**
 * Just like the {@link WeatherTestCase} this class encapsulates a test case for
 * the football use case.
 */
class FootballTestCase {
    private String file;
    private String expected;

    FootballTestCase(String file, String expectedTeam) {
        this.file = file;
        this.expected = expectedTeam;
    }

    String getFile() {
        return this.file;
    }

    String getExpected() {
        return this.expected;
    }
}
