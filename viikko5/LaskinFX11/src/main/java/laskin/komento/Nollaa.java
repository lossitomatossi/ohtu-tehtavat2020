package laskin.komento;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

public class Nollaa extends Komento {

    public Nollaa(TextField tulostekentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tulostekentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        this.arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        this.sovellus.nollaa();
        this.syotekentta.setText("");
        this.tulostekentta.setText("0");
        this.nollaa.disableProperty().set(true);
        this.undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
