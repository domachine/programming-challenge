package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

/**
 * Example JUnit 5 test case.
 * 
 * @author Benjamin Schmid <benjamin.schmid@exxcellent.de>
 */
class AppTest {
    @TestFactory
    Stream<DynamicTest> weatherCsvTests2() {
        List<WeatherTestCase> testCases = Arrays.asList(
                new WeatherTestCase("weather.csv", "14"),
                new WeatherTestCase("weather_sample_2.csv", "2"));

        return testCases.stream().map(testCase -> DynamicTest.dynamicTest(testCase.getFile(), () -> {
            Day day = App.getDayWithSmallestTempSpread(App.readWeatherData(testCase.getFile()));
            assertEquals(testCase.getExpected(), day.getNumber());
        }));
    }
}

/**
 * This is a tiny class to hold information about a test case. I like it to have
 * multiple chunks of sample data in a format which is consumed by the
 * application (in this case csv). So it's probably more an integration test
 * than a unit test. But
 * I think this makes the test more robust against further refactorings of the
 * code.
 */
class WeatherTestCase {
    private String file;
    private String expected;

    WeatherTestCase(String file, String expected) {
        this.file = file;
        this.expected = expected;
    }

    String getFile() {
        return this.file;
    }

    String getExpected() {
        return this.expected;
    }
}
