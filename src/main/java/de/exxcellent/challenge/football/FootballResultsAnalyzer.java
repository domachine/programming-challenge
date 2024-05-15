package de.exxcellent.challenge.football;

import java.io.IOException;
import java.util.List;

public class FootballResultsAnalyzer {
    private FootballReader reader;

    public FootballResultsAnalyzer(FootballReader reader) {
        this.reader = reader;
    }

    /**
     * This is the algorithm which analyzes the data to find the team with smallest
     * distance.
     *
     * @return
     * @throws IOException
     */
    public FootballResult getFootballResultWithSmallestGoalDifference() throws IOException {
        List<FootballResult> footballData = reader.readFootballData();

        return footballData.stream().reduce(
                null,
                (minResult, result) -> minResult == null || result.getDistance() < minResult.getDistance()
                        ? result
                        : minResult);
    }
}
