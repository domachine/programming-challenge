package de.exxcellent.challenge.weather;

import java.io.IOException;
import java.util.List;

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
    public Day getDayWithSmallestTempSpread() throws IOException {
        List<Day> days = reader.readWeatherData();

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
}
