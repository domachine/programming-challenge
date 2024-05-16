package de.exxcellent.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import de.exxcellent.challenge.football.FootballReader;
import de.exxcellent.challenge.football.FootballResult;
import de.exxcellent.challenge.football.FootballResultsAnalyzer;

public class FootballTest {
    @Test
    void nullCase() throws IOException {
        FootballResultsAnalyzer analyzer = new FootballResultsAnalyzer(new FootballReader() {
            @Override
            public List<FootballResult> readFootballData() throws IOException {
                return List.of();
            }
        });

        assertEquals(Optional.empty(), analyzer.getFootballResultWithSmallestGoalDifference());
    }
}
