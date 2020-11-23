package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] numeroJono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alustaJono(KAPASITEETTI);
    }

    private void alustaJono(int koko) {
        numeroJono = new int[koko];
        for (int i = 0; i < numeroJono.length; i++) {
            numeroJono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        alustaJono(kapasiteetti);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei saa olla negatiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko ei saa olla negatiivinen ");
        }
        alustaJono(kapasiteetti);
        this.kasvatuskoko = kasvatuskoko;

    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            numeroJono[0] = luku;
            alkioidenLkm++;
            return true;
        }
        if (!kuuluu(luku)) {
            numeroJono[alkioidenLkm++] = luku;
            if (alkioidenLkm % numeroJono.length == 0) {
                int[] taulukkoOld = numeroJono;
                kopioiTaulukko(numeroJono, taulukkoOld);
                numeroJono = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, numeroJono);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == numeroJono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = -1;
        int apu;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == numeroJono[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
                numeroJono[kohta] = 0;
                break;
            }   
        }
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                apu = numeroJono[j];
                numeroJono[j] = numeroJono[j + 1];
                numeroJono[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);

    }

    public int getAlkioidenLkm() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        switch (alkioidenLkm) {
            case 0:
                return "{}";
            case 1:
                return "{" + numeroJono[0] + "}";
            default:
                String jononPrint = "{";
                for (int i = 0; i < alkioidenLkm - 1; i++) {
                    jononPrint += numeroJono[i];
                    jononPrint += ", ";
                }
                jononPrint += numeroJono[alkioidenLkm - 1];
                jononPrint += "}";
                return jononPrint;

        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(numeroJono, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }

        return z;
    }

}
