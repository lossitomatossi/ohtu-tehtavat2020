package ohtu;

public class TennisGame {

    private int score1 = 0;
    private int score2 = 0;

    public TennisGame(String player1Name, String player2Name) {
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName)) {
            score1 += 1;
        } else {
            score2 += 1;
        }
    }

    public String getScore() {
        String score = "";
        if (score1 == score2) {
            score = statusWhenEven(score1);
        } else if (score1 >= 4 || score2 >= 4) {
            score = statusAfter4Points(score1, score2);
        } else {
            score = statusWhenUneven(score1, score2);
        }
        return score;
    }

    private String pointsToWords(int points) {
        switch (points) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
        return null;
    }

    private String statusAfter4Points(int pointsP1, int pointsP2) {
        int differenceInPoints = score1 - score2;
        if (differenceInPoints == 1) {
            return "Advantage player1";
        } else if (differenceInPoints == -1) {
            return "Advantage player2";
        } else if (differenceInPoints >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    private String statusWhenEven(int pointsP1) {
        if (pointsP1 < 4) {
            return pointsToWords(pointsP1) + "-All";
        } else {
            return "Deuce";
        }
    }
    
    private String statusWhenUneven(int pointsP1, int pointsP2) {
        return pointsToWords(pointsP1) + "-" + pointsToWords(pointsP2);
    }
}
