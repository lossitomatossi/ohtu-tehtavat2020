package statistics.matcher;

import statistics.Player;

public class Or implements Matcher{
    private Matcher[] matches;

    public Or(Matcher... matches) {
        this.matches = matches;
    }

    @Override
    public boolean matches(Player p) {
        for (Matcher m : matches) {
            if (m.matches(p)) {
                return true;
            }
        }
        return false;
    }
    
    
}
