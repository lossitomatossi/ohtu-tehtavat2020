
package laskin.komento;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.Sovelluslogiikka;

public class Summa extends Komento{

    public Summa(TextField tulostekentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tulostekentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        this.arvo = 0;
        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        this.sovellus.plus(arvo);
        this.tulostekentta.setText(Integer.toString(this.sovellus.tulos()));
        this.syotekentta.setText("");
        this.nollaa.disableProperty().set(false);
        this.undo.disableProperty().set(false);
    }

    @Override
    public void peru() {
        this.sovellus.miinus(arvo);
        this.tulostekentta.setText(Integer.toString(this.sovellus.tulos()));
        this.syotekentta.setText("");
        this.nollaa.disableProperty().set(false);
        this.undo.disableProperty().set(true);
    }
    
}
