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
