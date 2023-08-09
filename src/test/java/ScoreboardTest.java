package test.java;

import main.java.Match;
import main.java.Scoreboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static main.java.Constant.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreboardTest {

    private Scoreboard scoreboard;

    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    private void initializeToStartMatches() {
        scoreboard.startMatch(MEXICO, CANADA);
        scoreboard.startMatch(SPAIN, BRAZIL);
    }

    private void initializeToStartAllMatches() {
        scoreboard.startMatch(MEXICO, CANADA);
        scoreboard.startMatch(SPAIN, BRAZIL);
        scoreboard.startMatch(GERMANY, FRANCE);
        scoreboard.startMatch(URUGUAY, ITALY);
        scoreboard.startMatch(ARGENTINA, AUSTRALIA);
    }

    private void updateScoresForAllMatches() {
        scoreboard.updateScore(MEXICO, CANADA, 0, 5);
        scoreboard.updateScore(SPAIN, BRAZIL, 10, 2);
        scoreboard.updateScore(GERMANY, FRANCE, 2, 2);
        scoreboard.updateScore(URUGUAY, ITALY, 6, 6);
        scoreboard.updateScore(ARGENTINA, AUSTRALIA, 3, 1);
    }

    @Test
    void test_startNewMatch_FirstMatch() {
        scoreboard.startMatch(MEXICO, CANADA);
        assertEquals(1, scoreboard.getAllLiveMatches());
    }

    @Test
    void test_startNewMatch_AlongExisting() {
        initializeToStartMatches();
        assertEquals(2, scoreboard.getAllLiveMatches());

        scoreboard.startMatch(GERMANY, FRANCE);
        assertEquals(3, scoreboard.getAllLiveMatches());
    }

    @Test
    public void test_StartMatch_AndThen_UpdateScore() {
        initializeToStartMatches();

        scoreboard.updateScore(SPAIN, BRAZIL, 10, 2);
        Match match = scoreboard.getMatch(SPAIN, BRAZIL);
        assertEquals(12, match.getTotalScore());
    }

    @Test
    public void test_StartNotMatch_AndThen_UpdateScore() {
        initializeToStartMatches();

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> scoreboard.updateScore(URUGUAY, ITALY, 6, 6));
        assertEquals("Cannot update the score as Match is not in progress", exception.getMessage());
    }

    @Test
    public void test_finishInProgressMatch() {
        initializeToStartMatches();
        assertEquals(2, scoreboard.getMatches().size());

        scoreboard.finishMatch(MEXICO, CANADA);
        scoreboard.finishMatch(SPAIN, BRAZIL);
        assertEquals(0, scoreboard.getMatches().size());
    }

    @Test
    public void test_MatchInProgressOrderByRecentStarted() {
        initializeToStartAllMatches();
        updateScoresForAllMatches();

        List<Match> matchesInProgressOrderedByTotalScore = scoreboard.getMatchesInProgressOrderedByTotalScore();

        assertEquals(5, matchesInProgressOrderedByTotalScore.size());
        assertEquals(URUGUAY, matchesInProgressOrderedByTotalScore.get(0).getHomeTeam());
        assertEquals(ITALY, matchesInProgressOrderedByTotalScore.get(0).getAwayTeam());

        assertEquals(SPAIN, matchesInProgressOrderedByTotalScore.get(1).getHomeTeam());
        assertEquals(BRAZIL, matchesInProgressOrderedByTotalScore.get(1).getAwayTeam());

        assertEquals(MEXICO, matchesInProgressOrderedByTotalScore.get(2).getHomeTeam());
        assertEquals(CANADA, matchesInProgressOrderedByTotalScore.get(2).getAwayTeam());

        assertEquals(ARGENTINA, matchesInProgressOrderedByTotalScore.get(3).getHomeTeam());
        assertEquals(AUSTRALIA, matchesInProgressOrderedByTotalScore.get(3).getAwayTeam());

        assertEquals(GERMANY, matchesInProgressOrderedByTotalScore.get(4).getHomeTeam());
        assertEquals(FRANCE, matchesInProgressOrderedByTotalScore.get(4).getAwayTeam());

    }
}