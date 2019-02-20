import java.util.ArrayList;
import java.util.HashMap;

public class TennisMatch {

    Player player1;
    Player player2;
    MatchType matchType;
    String pointPlayer1;
    String pointPlayer2;
    int gamePlayer1 ;
    int gamePlayer2;
    int setPlayer1;
    int setPlayer2;
    int pointTieBreakP1;
    int pointTieBreakP2;
    boolean isATieBreak;
    boolean isLastSet;
    boolean finish;
    boolean tieBreakInLastSet;
    //int score[][];
    HashMap<Player, Integer[]> score;



    public void setPointsPlayers(String pointsP1, String pointsP2){
        pointPlayer1 = pointsP1;
        pointPlayer2 = pointsP2;
    }
    public void setSetsPlayers(int setP1, int setP2){
        setPlayer1 = setP1;
        setPlayer2 = setP2;
    }
    public void setGamesPlayers(int gamesP1, int gamesP2){
        gamePlayer1 = gamesP1;
        gamePlayer2 = gamesP2;
    }

    public String getFinalScore(){
            String scoreSTR = "Le score est de ";
            for (int i = 0; i <= currentSetNumber(); i++) {
                scoreSTR = scoreSTR.concat(String.valueOf(score.get(player1)[i])).concat("-").concat(String.valueOf(score.get(player2)[i]).concat(","));
            }
        return scoreSTR;
    }
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


    public TennisMatch(Player player1, Player player2, MatchType matchType, boolean tieBreakInLastSet) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchType = matchType;
        this.tieBreakInLastSet = tieBreakInLastSet;
        pointPlayer1 = "0";
        pointPlayer2 = "0";
        gamePlayer1 = 0;
        gamePlayer2 = 0;
        setPlayer1 = 0;
        setPlayer2 = 0;
        isATieBreak = false;
        isLastSet = false;
        finish = false;
        pointTieBreakP1 = 0;
        pointTieBreakP2 = 0;
        score = new HashMap<Player, Integer[]>();
        score.put(player1, new Integer[(matchType.numberOfSetsToWin()*2)-1]);
        score.put(player2, new Integer[(matchType.numberOfSetsToWin()*2)-1]);
        for(int i=0; i<matchType.numberOfSetsToWin()*2-1; i++){
            score.get(player1)[i] = 0;
            score.get(player2)[i] = 0;
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
        return (setPlayer1+setPlayer2);
    }

    public int gamesInCurrentSetForPlayer(Player player) {
        if(player == this.player1){
            return gamePlayer1;
        }
        else {
            return gamePlayer2;
        }
    }

    public int gamesInSetForPlayer(int setNumber, Player player) {
        return score.get(player)[setNumber-1];
    }

    public boolean isFinished() {
        if(setPlayer1 == matchType.numberOfSetsToWin()||setPlayer2 == matchType.numberOfSetsToWin())
            finish =true;
        return finish;
    }

    public void updateWithPointWonBy(Player player){
        if(player.equals(player1)){
            if(isATieBreak){
                pointTieBreakP1++;
                if(pointTieBreakP1 >= 7 && pointTieBreakP1 >= pointTieBreakP2 +2){
                    finish = true;
                    addGame(player1);
                    pointPlayer2 = "0";
                    pointPlayer1 = "0";
                }
            }
            else {
                switch (pointPlayer1) {
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
                        if (pointPlayer2 == "40") {
                            pointPlayer1 = "A";
                        } else if (pointPlayer2 == "A") {
                            pointPlayer2 = "40";
                        } else {
                            addGame(player);
                        }
                        break;
                    case "A":
                        addGame(player);
                }
            }
        }
        else if(player.equals(player2)) {
            if (isATieBreak) {
                pointTieBreakP2++;
                if (pointTieBreakP2 >= 7 && pointTieBreakP2 >= pointTieBreakP1 + 2) {
                    finish = true;
                    addGame(player2);
                    pointPlayer2 = "0";
                    pointPlayer1 = "0";
                }
            } else {
                switch (pointPlayer2) {
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
                        if (pointPlayer1 == "40") {
                            pointPlayer2 = "A";
                        } else if (pointPlayer1 == "A") {
                            pointPlayer1 = "40";
                        } else {
                            addGame(player);
                        }
                        break;
                    case "A":
                        addGame(player);
                }
            }
        }
    }

    private void addGame(Player player){
        if(isATieBreak){
            addSet(player);
            isATieBreak = false;
            return;
        }

        if(player == player1){
            score.get(player1)[currentSetNumber()]++;
            if(gamePlayer1 >= 5 && gamePlayer1 >= gamePlayer2+2){
                addSet(player);
                pointPlayer1 = "0";
                pointPlayer2 = "0";
            }
            else {
                pointPlayer1 = "0";
                pointPlayer2 = "0";
                gamePlayer1++;
                if(gamePlayer1 == gamePlayer2 && gamePlayer1 == 6 && ((isTieBreakInLastSet() && isLastSet) || (!isLastSet))){
                    isATieBreak = true;
                }
            }
        }
        else{
            score.get(player2)[currentSetNumber()]++;
            if(gamePlayer2 >= 5 && gamePlayer2 >= gamePlayer1 +2){
                addSet(player);
            }
            else {
                pointPlayer1 = "0";
                pointPlayer2 = "0";
                gamePlayer2++;
                if(gamePlayer1 == gamePlayer2 && gamePlayer1 == 6 && ((isTieBreakInLastSet() && isLastSet) || (!isLastSet))){
                    isATieBreak = true;
                }
            }
        }
    }

    private void addSet(Player player) {

        if(player == player1){
            setPlayer1++;
            if(setPlayer1 == matchType.numberOfSetsToWin() -1 && setPlayer1 == setPlayer2){
                isLastSet = true;
            }
            if(setPlayer1 == matchType.numberOfSetsToWin()){
                finish = true;
            }
            else {

                gamePlayer2 = 0;
                gamePlayer1 = 0;
            }
        }
        else {
            setPlayer2++;
            if(setPlayer1 == matchType.numberOfSetsToWin() -1 && setPlayer1 == setPlayer2){
                isLastSet = true;
            }
            if(setPlayer2 +1 == matchType.numberOfSetsToWin()){
                finish = true;
            }
            else {
                gamePlayer2 = 0;
                gamePlayer1 = 0;
            }
        }
    }
}
