import static org.junit.Assert.*;

public class TennisMatchTest {

    Player p1 = new Player("John");
    Player p2 = new Player("Jeanne");

    @org.junit.Test
    public void updateWithPointWonBy() {
        Player p1 = new Player("John");
        Player p2 = new Player("Jeanne");
        TennisMatch match = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, false);
        match.updateWithPointWonBy(p1);
        assertEquals(match.pointPlayer1, "15");
        match.updateWithPointWonBy(p1);
        assertEquals(match.pointPlayer1, "30");
        match.updateWithPointWonBy(p1);
        assertEquals(match.pointPlayer1, "40");

        match.updateWithPointWonBy(p2);
        assertEquals(match.pointPlayer2, "15");
        match.updateWithPointWonBy(p2);
        assertEquals(match.pointPlayer2, "30");
        match.updateWithPointWonBy(p2);
        assertEquals(match.pointPlayer2, "40");
    }

    @org.junit.Test
    public void updateWithPointWonByAdventage() {
        TennisMatch match = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, false);
        for(int i=0; i<4; i++){
            match.updateWithPointWonBy(p1);
            match.updateWithPointWonBy(p2);
        }
        match.updateWithPointWonBy(p2);
        assertEquals(match.pointPlayer2, "A");
        match.updateWithPointWonBy(p1);
        assertEquals(match.pointPlayer2, "40");
        assertEquals(match.pointPlayer1, "40");


        match.updateWithPointWonBy(p1);
        assertEquals(match.pointPlayer1, "A");
        match.updateWithPointWonBy(p2);
        assertEquals(match.pointPlayer2, "40");
        assertEquals(match.pointPlayer1, "40");
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