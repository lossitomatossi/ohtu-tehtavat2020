package statistics;

import statistics.matcher.*;

public class Main {

    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));

        Matcher m = new And(new HasAtLeast(5, "goals"),
                new HasAtLeast(5, "assists"),
                new PlaysIn("PHI")
        );

        Matcher n = new And(
                new HasAtLeast(50, "points"),
                new Or(
                        new PlaysIn("NYR"),
                        new PlaysIn("NYI"),
                        new PlaysIn("BOS")
                )
        );

        for (Player player : stats.matches(n)) {
            System.out.println(player);
        }
    }
}
