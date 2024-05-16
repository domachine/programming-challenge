package de.exxcellent.challenge.football;

import java.io.IOException;
import java.util.Optional;

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
    public Optional<FootballResult> getFootballResultWithSmallestGoalDifference() throws IOException {
        FootballResult nullableResult = reader.readFootballData().stream().reduce(
                null,
                (minResult, result) -> minResult == null || result.getDistance() < minResult.getDistance()
                        ? result
                        : minResult);

        return Optional.ofNullable(nullableResult);
    }
}
