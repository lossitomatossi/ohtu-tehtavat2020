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
        arvo = this.sovellus.tulos();
        System.out.println("arvo oli " + arvo);
        this.sovellus.nollaa();
        this.syotekentta.setText("");
        this.tulostekentta.setText("0");
        this.nollaa.disableProperty().set(true);
        this.undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        this.sovellus.plus(arvo);
        this.tulostekentta.setText(Integer.toString(this.sovellus.tulos()));
        this.syotekentta.setText("");
        this.nollaa.disableProperty().set(false);
        this.undo.disableProperty().set(true);
    }

}
