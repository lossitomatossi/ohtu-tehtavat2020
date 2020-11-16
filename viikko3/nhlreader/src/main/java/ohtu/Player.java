package ohtu;

public class Player implements Comparable<Player> {

    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties;
    private String team;
    private int games;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAssists() {
        return assists;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getGames() {
        return games;
    }

    @Override
    public int compareTo(Player p) {
        int points1 = this.assists + this.goals;
        int points2 = p.getAssists() + p.getGoals();
        if (points1 < points2) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        int points = assists + goals;
        return name + "\t" + team + "\t " + goals + " + " + assists + " = "
                + points;
    }

}
