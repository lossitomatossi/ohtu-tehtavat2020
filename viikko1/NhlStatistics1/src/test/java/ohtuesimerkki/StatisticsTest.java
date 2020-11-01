/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lossitomatossi
 */
public class StatisticsTest {

    public StatisticsTest() {
    }

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);

    }

    @Test
    public void etsiminenToimiiYhelle() {
        Player kurri = stats.search("Kurri");
        assertEquals("Kurri", kurri.getName());
    }
    
    @Test
    public void puuttuvanEtsiminenNull() {
        Player player = stats.search("ei ole olemassa");
        assertEquals(null, player);
    }
    
    @Test
    public void teamTest() {
        List<Player> lista = stats.team("EDM");
        for (Player p : lista) {
            assertEquals("EDM", p.getTeam());
        }
    }
    
    @Test
    public void topScorerTest() {
        List<Player> lista = stats.topScorers(2);
        assertEquals(124, lista.get(0).getPoints());
        assertEquals(99, lista.get(1).getPoints());
    }

}
