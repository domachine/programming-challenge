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

public class FootballResultsAnalyzer {
    /**
     * This method reads the csv file and turns it into entity objects.
     *
     * @param is
     * @return
     * @throws IOException
     */
    public List<FootballResult> readFootballData(InputStream is) throws IOException {
        ArrayList<FootballResult> results = new ArrayList<>();

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(FootballDataHeader.class)
                .setSkipHeaderRecord(true)
                .build();

        try (Reader reader = new InputStreamReader(is)) {
            Iterable<CSVRecord> records = csvFormat.parse(reader);
            for (CSVRecord record : records) {
                results.add(new FootballResult(record.get(FootballDataHeader.team),
                        Integer.parseInt(record.get(FootballDataHeader.goals)),
                        Integer.parseInt(record.get(FootballDataHeader.goalsAllowed))));
            }
        }

        return results;
    }

    /**
     * This is the algorithm which analyzes the data to find the team with smallest
     * distance.
     *
     * @param footballData
     * @return
     */
    public FootballResult getFootballResultWithSmallestGoalDifference(List<FootballResult> footballData) {
        FootballResult resultWithSmallestDistance = null;
        int min = Integer.MAX_VALUE;
        for (FootballResult result : footballData) {
            if (result.getDistance() < min) {
                min = result.getDistance();
                resultWithSmallestDistance = result;
            }
        }
        return resultWithSmallestDistance;
    }

}
