package de.exxcellent.challenge.football;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

enum FootballDataHeader {
    team, games, wins, losses, draws, goals, goalsAllowed, points
}

public class FootballCsvReader implements FootballReader {
    private InputStream inputStream;

    public FootballCsvReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * This method reads the csv file and turns it into entity objects.
     *
     * @return
     * @throws IOException
     */
    @Override
    public List<FootballResult> readFootballData() throws IOException {
        ArrayList<FootballResult> results = new ArrayList<>();

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(FootballDataHeader.class)
                .setSkipHeaderRecord(true)
                .build();

        try (Reader reader = new InputStreamReader(inputStream)) {
            Iterable<CSVRecord> records = csvFormat.parse(reader);
            for (CSVRecord record : records) {
                results.add(new FootballResult(record.get(FootballDataHeader.team),
                        Integer.parseInt(record.get(FootballDataHeader.goals)),
                        Integer.parseInt(record.get(FootballDataHeader.goalsAllowed))));
            }
        }

        return results;
    }
}
