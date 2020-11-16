package ohtu;

import com.google.gson.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

        String bodyText = Request.Get(url).execute().returnContent().asString();

//        System.out.println("json-muotoinen data:");
//        System.out.println( bodyText );
        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);

        System.out.println("Players from FIN " + java.time.LocalDateTime.now());
        ArrayList<Player> lista = new ArrayList();

        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                lista.add(player);
                //System.out.println(player);
            }
        }

        Collections.sort(lista);
        for (Player p : lista) {
            System.out.println(p);
        }
    }

}
