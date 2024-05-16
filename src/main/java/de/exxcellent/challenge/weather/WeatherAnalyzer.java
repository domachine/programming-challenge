package de.exxcellent.challenge.weather;

import java.io.IOException;
import java.util.Optional;

enum WeatherDataHeader {
    name, mxt, mnt
}

public class WeatherAnalyzer {
    private WeatherReader reader;

    public WeatherAnalyzer(WeatherReader reader) {
        this.reader = reader;
    }

    /**
     * This method contains the algorithm to find the day with the smallest
     * temperature spread in a list of days.
     * 
     * @return
     * @throws IOException
     */
    public Optional<Day> getDayWithSmallestTempSpread() throws IOException {
        Day result = reader.readWeatherData().stream().reduce(
                null,
                (minDay, day) -> minDay == null || day.getTemperatureSpread() < minDay.getTemperatureSpread()
                        ? day
                        : minDay);

        return Optional.ofNullable(result);
    }
}
