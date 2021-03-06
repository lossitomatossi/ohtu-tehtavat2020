package ohtu.verkkokauppa;

import ohtu.verkkokauppa.Viitegeneraattori;
import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Tuote;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Kauppa k;
    Varasto varasto;

    @Before
    public void setUp() {
        // luodaan ensin mock-oliot
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        varasto = mock(Varasto.class);
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "ketsuppi", 2));
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "sinappi", 3));
        // sitten testattava kauppa
        k = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaattyminenOikeillaTiedoilla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }

    @Test
    public void pankinMetodiTilisiirtoOikeinUseallaTuotteella() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 7);
    }

    @Test
    public void pankinMetodiTilisiirtoOikeinKunMontaSamaaTuotetta() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 4);
    }

    @Test
    public void pankinMetodiTilisiirtoOikeinKunSaldoEiRiita() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 2);
    }

    @Test
    public void kaupanMetodiAloitaAsiointiNollaaOstokset() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 2);
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("aatu", "54321");

        verify(pankki).tilisiirto("aatu", 42, "54321", "33333-44455", 7);
    }

    @Test
    public void kauppaPyytaaUudenViitenumeronMaksutapahtumille() {
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        // määritellään että metodi palauttaa ensimmäisellä kutsukerralla 1, toisella 2 
        // ja kolmannella 3
        when(mockViite.uusi())
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3);

        Kauppa kauppa = new Kauppa(varasto, pankki, mockViite);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto("pekka", 1, "12345", "33333-44455", 2);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("aatu", "54321");
        verify(pankki).tilisiirto("aatu", 2, "54321", "33333-44455", 7);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(2);
        kauppa.lisaaKoriin(3);
        kauppa.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto("pekka", 3, "12345", "33333-44455", 2);
    }
    
    @Test
    public void poistaKoristaPalauttaaVarastoon() {
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        
        verify(varasto, times(1)).haeTuote(2);
        Tuote t = varasto.haeTuote(2);
        k.poistaKorista(2);
        verify(varasto, times(1)).palautaVarastoon(t);
        
        
    }
}
