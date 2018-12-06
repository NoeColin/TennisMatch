public class TennisMatch {
    Player player1;
    Player player2;
    MatchType matchType;

    public MatchType getMatchType() {
        return matchType;
    }

    public boolean isTieBreakInLastSet() {
        return tieBreakInLastSet;
    }

    public String getPointPlayer1() {
        return pointPlayer1;
    }

    public String getPointPlayer2() {
        return pointPlayer2;
    }

    public int getGamePlayer1() {
        return gamePlayer1;
    }

    public int getGamePlayer2() {
        return gamePlayer2;
    }

    boolean tieBreakInLastSet;
    String pointPlayer1 = "0";
    String pointPlayer2 = "0";
    int gamePlayer1 = 0;
    int gamePlayer2 = 0;
    int setTennisPlayer1;


    public TennisMatch(Player player1, Player player2, MatchType matchType, boolean tieBreakInLastSet) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchType = matchType;
        this.tieBreakInLastSet = tieBreakInLastSet;
    }

    public void updateWithPointWonBy(Player player) {
        if (player == player1) {
            pointPlayer1 = "";
        } else if (player == player2) {

        }
    }

    public String pointsForPlayer(Player player) {
        if (player == player1) {
            return pointPlayer1;
        } else if (player == player2) {
            return pointPlayer2;
        } else {
            return "Error: wrong player";
        }
    }

    public int currentSetNumber() {
        return 1;
    }

    public int gamesInCurrentSetForPlayer(Player player) {
        return 1;
    }

    public int gamesInSetForPlayer(int setNumber, Player player) {
        return 1;
    }

    public boolean isFinished() {
        return true;
    }

    private void addPoint(Player player, String pointPlayer){
        if(player.equals(player1)){
            switch (pointPlayer) {
                case "0":
                    pointPlayer1 = "15";
                    break;
                case "15":
                    pointPlayer1 = "30";
                    break;
                case "30":
                    pointPlayer1 = "40";
                    break;
                case "40":
                    if(pointPlayer2 == "40"){
                        pointPlayer1 = "A";
                    }
                    else if(pointPlayer2 == "A") {
                        pointPlayer2 = "40";
                    }
                    else {
                        pointPlayer1 = "0";
                    }
                    break;
                case "A":
                    pointPlayer1 = "0";
            }

        }
        else if(player.equals(player2)){
            switch (pointPlayer) {
                case "0":
                    pointPlayer2 = "15";
                    break;
                case "15":
                    pointPlayer2 = "30";
                    break;
                case "30":
                    pointPlayer2 = "40";
                    break;
                case "40":
                    if(pointPlayer1 == "40"){
                        pointPlayer2 = "A";
                    }
                    else if(pointPlayer1 == "A") {
                        pointPlayer1 = "40";
                    }
                    else {
                        pointPlayer2 = "0";
                    }
                    break;
                case "A":
                    pointPlayer2 = "0";
            }
        }


    }

}
