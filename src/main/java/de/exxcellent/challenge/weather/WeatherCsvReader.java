package de.exxcellent.challenge.weather;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class WeatherCsvReader implements WeatherReader {
    private InputStream inputStream;

    public WeatherCsvReader(InputStream is) {
        this.inputStream = is;
    }

    /**
     * This method is responsible for reading the input csv an turning it into a
     * list of days.
     *
     * @param is
     * @return
     * @throws IOException
     */
    @Override
    public List<Day> readWeatherData() throws IOException {
        ArrayList<Day> days = new ArrayList<>();

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(WeatherDataHeader.class)
                .setSkipHeaderRecord(true)
                .build();

        try (Reader reader = new InputStreamReader(inputStream)) {
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
