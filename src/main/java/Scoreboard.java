package main.java;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    List<Match> matches = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam) {
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
    }

    public int getAllLiveMatches() {
        return matches.size();
    }

    public void updateScore(String homeTeam,
                            String awayTeam,
                            int homeTeamScore,
                            int awayTeamScore) {
        Match match = getMatch(homeTeam, awayTeam);
        match.updateScore(homeTeamScore, awayTeamScore);
    }

    public Match getMatch(String homeTeam, String awayTeam) {
        return matches.stream()
                .filter(match -> match.getHomeTeam().equalsIgnoreCase(homeTeam))
                .filter(match -> match.getAwayTeam().equalsIgnoreCase(awayTeam))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cannot update the score as Match is not in progress"));
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        matches.removeIf(match ->
                match.getHomeTeam().equalsIgnoreCase(homeTeam) && match.getAwayTeam().equalsIgnoreCase(awayTeam));
    }

    public List<Match> getMatchesInProgressOrderedByTotalScore() {
        List<Match> matchesInProgress = getMatches();
        matchesInProgress.sort((match1, match2) -> {
            int totalScoreComparison = Integer.compare(match2.getTotalScore(), match1.getTotalScore());
            if (totalScoreComparison != 0) {
                return totalScoreComparison;
            } else {
                return Integer.compare(matches.indexOf(match2), matches.indexOf(match1));
            }
        });
        return matchesInProgress;
    }
}
