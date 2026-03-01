/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.DrustvenaIgra;
import domen.Racun;
import domen.Klijent;
import domen.Mesto;
import domen.StavkaRacuna;
import domen.Zaposleni;
import java.util.List;
import operacije.igre.UcitajIgreSO;
import operacije.racun.ObrisiRacunSO;
import operacije.racun.UcitajRacuneSO;
import operacije.klijenti.AzurirajKlijentaSO;
import operacije.klijenti.KreirajKlijentaSO;
import operacije.klijenti.ObrisiKlijentaSO;
import operacije.klijenti.UcitajKlijenteSO;
import operacije.login.LoginOperacija;
import operacije.mesta.UcitajMestaSO;
import operacije.racun.AzurirajRacunSO;
import operacije.racun.DodajRacunSO;
import operacije.stavke.AzurirajStavkuSO;
import operacije.stavke.ObrisiStavkuSO;
import operacije.stavke.UcitajStavkeSO;
import operacije.zaposleni.UcitajZaposleneSO;

/**
 *
 * @author ilija
 */
public class Controller {
    private static Controller instance;
    
    private Controller(){
    }
    public static Controller getInstance(){
        if(instance==null){
            instance=new Controller();
        }
        return instance;
    }

    public Zaposleni login(Zaposleni z) throws Exception {
        LoginOperacija login = new LoginOperacija();
        login.izvrsi(z, null);
        return login.getZaposleni();
    }

    public List<Klijent> ucitajKlijente() throws Exception{
        UcitajKlijenteSO ucitaj = new UcitajKlijenteSO();
        ucitaj.izvrsi(new Klijent(), "");
        return ucitaj.getKlijenti();
    }

    public void obrisiKlijenta(Klijent k) throws Exception {
        ObrisiKlijentaSO operacija = new ObrisiKlijentaSO();
        operacija.izvrsi(k, null);
    }

    public void dodajKlijenta(Klijent k) throws Exception {
        KreirajKlijentaSO operacija = new KreirajKlijentaSO();
        operacija.izvrsi(k, null);
    }
    
     public List<Mesto> ucitajMesta() throws Exception{
        UcitajMestaSO ucitaj = new UcitajMestaSO();
        ucitaj.izvrsi(new Mesto(), "");
        return ucitaj.getMesta();
    }

    public void azurirajKlijenta(Klijent k)throws Exception {
        AzurirajKlijentaSO azuriraj = new AzurirajKlijentaSO();
        azuriraj.izvrsi(k, null);
    }

    public List<Racun> ucitajRacune() throws Exception {
        UcitajRacuneSO ucitaj = new UcitajRacuneSO();
        ucitaj.izvrsi(null, null);
        return ucitaj.getRacuni();
    }

    public List<StavkaRacuna> ucitajStavke(long id) throws Exception {
        UcitajStavkeSO operacija = new UcitajStavkeSO();
        operacija.izvrsi(id,null);
        return operacija.getStavke();
    }

    public void obrisiRacun(Racun i) throws Exception {
        ObrisiRacunSO operacija = new ObrisiRacunSO();
        operacija.izvrsi(i, null);
    }

    public List<Zaposleni> ucitajZaposlene() throws Exception {
        UcitajZaposleneSO operacija = new UcitajZaposleneSO();
        operacija.izvrsi(new Zaposleni(), null);
        return operacija.getZaposleni();
    }

    public List<DrustvenaIgra> ucitajIgre() throws Exception {
        UcitajIgreSO operacija = new UcitajIgreSO();
        operacija.izvrsi(new DrustvenaIgra(), null);
        return operacija.getIgre();
    }

    public void dodajRacun(Racun r2) throws Exception {
        DodajRacunSO operacija = new DodajRacunSO();
        operacija.izvrsi(r2, null);
    }

    public void obrisiStavkuRacuna(StavkaRacuna sr) throws Exception {
        ObrisiStavkuSO operacija = new ObrisiStavkuSO();
        operacija.izvrsi(sr, null);
    }

    public void azurirajStavku(StavkaRacuna sr2) throws Exception{
        AzurirajStavkuSO operacija = new AzurirajStavkuSO();
        operacija.izvrsi(sr2, null);
    }

    public void azurirajRacun(Racun r3) throws Exception {
      AzurirajRacunSO operacija = new AzurirajRacunSO();
      operacija.izvrsi(r3, null);
    }
}
