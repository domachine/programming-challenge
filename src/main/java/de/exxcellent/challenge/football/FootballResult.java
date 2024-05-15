package de.exxcellent.challenge.football;

public class FootballResult {
    private String team;
    private int goals;
    private int goalsAllowed;

    FootballResult(String team, int goals, int goalsAllowed) {
        this.team = team;
        this.goals = goals;
        this.goalsAllowed = goalsAllowed;
    }

    public String getTeam() {
        return team;
    }

    public int getDistance() {
        return Math.abs(goals - goalsAllowed);
    }
}
