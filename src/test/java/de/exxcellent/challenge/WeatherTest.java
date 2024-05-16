package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.weather.Day;
import de.exxcellent.challenge.weather.WeatherAnalyzer;
import de.exxcellent.challenge.weather.WeatherReader;

public class WeatherTest {
    @Test
    void nullCase() throws IOException {
        WeatherAnalyzer analyzer = new WeatherAnalyzer(new WeatherReader() {
            @Override
            public List<Day> readWeatherData() throws IOException {
                return List.of();
            }
        });

        assertEquals(Optional.empty(), analyzer.getDayWithSmallestTempSpread());
    }
}
