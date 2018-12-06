import static org.junit.Assert.*;

public class TennisMatchTest {

    @org.junit.Test
    public void updateWithPointWonBy() {
        Player p1 = new Player("John");
        Player p2 = new Player("Jeanne");
        TennisMatch t = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, false);
        assertEquals(0, );
    }

    @org.junit.Test
    public void pointsForPlayer() {
    }

    @org.junit.Test
    public void currentSetNumber() {
    }

    @org.junit.Test
    public void gamesInCurrentSetForPlayer() {
    }

    @org.junit.Test
    public void gamesInSetForPlayer() {
    }

    @org.junit.Test
    public void isFinished() {
    }
}