package de.exxcellent.challenge.weather;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

enum WeatherDataHeader {
    name, mxt, mnt
}

public class WeatherAnalyzer {
    /**
     * This method contains the algorithm to find the day with the smallest
     * temperature spread in a list of days.
     * 
     * @param days
     * @return
     */
    public Day getDayWithSmallestTempSpread(List<Day> days) {
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
     * @param is
     * @return
     * @throws IOException
     */
    public List<Day> readWeatherData(InputStream is) throws IOException {
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
                days.add(new Day(Integer.parseInt(day), Integer.parseInt(mxt), Integer.parseInt(mnt)));
            }
        }

        return days;
    }
}
