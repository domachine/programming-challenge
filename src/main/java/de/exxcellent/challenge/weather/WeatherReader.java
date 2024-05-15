package de.exxcellent.challenge.weather;

import java.io.IOException;
import java.util.List;

public interface WeatherReader {
    /**
     * This method is responsible for reading some input source and turning it into
     * a list of days.
     *
     * @param is
     * @return
     * @throws IOException
     */
    List<Day> readWeatherData() throws IOException;
}