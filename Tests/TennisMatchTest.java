import static org.junit.Assert.*;

public class TennisMatchTest {

    Player p1 = new Player("John");
    Player p2 = new Player("Jeanne");

    @org.junit.Test
    public void updateWithPointWonBy() {

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
    public void addGame() {
        TennisMatch match = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, false);
        match.setPointsPlayers("40", "30");
        match.updateWithPointWonBy(p1);
        assertEquals(match.gamePlayer1,1);
        assertEquals(match.gamePlayer2,0);

        match.setPointsPlayers("30", "40");
        match.updateWithPointWonBy(p2);
        assertEquals(match.gamePlayer1,1);
        assertEquals(match.gamePlayer2,1);
    }

    @org.junit.Test
    public void addSet() {
        TennisMatch match = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, false);
        match.setPointsPlayers("40", "30");
        match.setGamesPlayers(5, 2);
        match.updateWithPointWonBy(p1);
        assertEquals(match.gamePlayer1,0);
        assertEquals(match.gamePlayer2,0);
        assertEquals(match.setPlayer1,1);
        assertEquals(match.setPlayer2,0);

        match.setPointsPlayers("30", "40");
        match.setGamesPlayers(2, 5);
        match.updateWithPointWonBy(p2);
        assertEquals(match.gamePlayer1,0);
        assertEquals(match.gamePlayer2,0);
        assertEquals(match.setPlayer1,1);
        assertEquals(match.setPlayer2,1);
    }

    @org.junit.Test
    public void showFinalScore() {
        TennisMatch match = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, false);
        for(int i=0; i<42; i++){
            match.updateWithPointWonBy(p1);
        }
        System.out.println(match.getFinalScore());
    }

    @org.junit.Test
    public void currentSetNumber() {
        TennisMatch match = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, false);
        match.setSetsPlayers(1,2);
        assertEquals(3,match.currentSetNumber());
    }

    @org.junit.Test
    public void gamesInCurrentSetForPlayer() {
        TennisMatch match = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, false);
        match.setGamesPlayers(2,3);
        assertEquals(2,match.gamesInCurrentSetForPlayer(p1));
        assertEquals(3,match.gamesInCurrentSetForPlayer(p2));
    }

    @org.junit.Test
    public void gamesInSetForPlayer() {
        TennisMatch match = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, false);
        for(int i=0; i<42; i++){
            match.updateWithPointWonBy(p1);
        }
        assertEquals(6,match.gamesInSetForPlayer(1,p1));
    }

    @org.junit.Test
    public void isFinished() {
        TennisMatch match = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, false);
        for(int i=0; i<71; i++){
            match.updateWithPointWonBy(p1);
        }
        assertEquals(false, match.isFinished());
        match.updateWithPointWonBy(p1);
        assertEquals(true, match.isFinished());
    }

    @org.junit.Test
    public void tieBreak(){
        TennisMatch match = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, false);
        match.setGamesPlayers(5,5);
        for(int i=0; i<4; i++){
            match.updateWithPointWonBy(p1);
        }
        for(int i=0; i<4; i++){
            match.updateWithPointWonBy(p2);
        }
        assertEquals(true, match.isATieBreak);


        match.updateWithPointWonBy(p1);
        match.updateWithPointWonBy(p1);
        assertEquals(2, match.pointTieBreakP1);

        for(int i=0; i<6; i++){
            match.updateWithPointWonBy(p2);
        }
        assertEquals(6, match.pointTieBreakP2);

        for(int i=0; i<4; i++){
            match.updateWithPointWonBy(p1);
        }
        assertEquals(6, match.pointTieBreakP1);

        match.updateWithPointWonBy(p1);
        assertEquals(7, match.pointTieBreakP1);
        match.updateWithPointWonBy(p2);
        assertEquals(7, match.pointTieBreakP2);
        match.updateWithPointWonBy(p1);
        assertEquals(8, match.pointTieBreakP1);

        assertEquals(0, match.setPlayer1);
        match.updateWithPointWonBy(p1);
        assertEquals(1, match.setPlayer1);
    }

    @org.junit.Test
    public void noTieBreakInLastSet() {
        TennisMatch match = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, false);
        match.setSetsPlayers(1,2);
        match.setGamesPlayers(5,0);
        for(int i=0; i<4; i++){
            match.updateWithPointWonBy(p1);
        }
        assertEquals(true, match.isLastSet);

        match.setGamesPlayers(5,5);
        for(int i=0; i<4; i++){
            match.updateWithPointWonBy(p1);
        }
        for(int i=0; i<4; i++){
            match.updateWithPointWonBy(p2);
        }
        assertEquals(false, match.isATieBreak);

        for(int i=0; i<4; i++){
            match.updateWithPointWonBy(p2);
        }
        assertEquals(7, match.gamePlayer2);
    }

    @org.junit.Test
    public void TieBreakInLastSet() {
        TennisMatch match = new TennisMatch(p1, p2, MatchType.BEST_OF_FIVE, true);
        match.setSetsPlayers(1, 2);
        match.setGamesPlayers(5, 0);
        for (int i = 0; i < 4; i++) {
            match.updateWithPointWonBy(p1);
        }
        assertEquals(true, match.isLastSet);

        match.setGamesPlayers(5, 5);
        for (int i = 0; i < 4; i++) {
            match.updateWithPointWonBy(p1);
        }
        for (int i = 0; i < 4; i++) {
            match.updateWithPointWonBy(p2);
        }
        assertEquals(true, match.isATieBreak);
    }



}