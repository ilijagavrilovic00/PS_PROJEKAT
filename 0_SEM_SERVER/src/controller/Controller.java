/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Mesto;
import domen.StavkaIznajmljivanja;
import domen.Zaposleni;
import java.util.List;
import operacije.iznajmljivanja.UcitajIznajmljivanjaSO;
import operacije.klijenti.AzurirajKlijentaSO;
import operacije.klijenti.KreirajKlijentaSO;
import operacije.klijenti.ObrisiKlijentaSO;
import operacije.klijenti.UcitajKlijenteSO;
import operacije.login.LoginOperacija;
import operacije.mesta.UcitajMestaSO;
import operacije.stavke.UcitajStavkeSO;

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

    public List<Iznajmljivanje> ucitajIznajmljivanja() throws Exception {
        UcitajIznajmljivanjaSO ucitaj = new UcitajIznajmljivanjaSO();
        ucitaj.izvrsi(null, null);
        return ucitaj.getIznajmljivanja();
    }

    public List<StavkaIznajmljivanja> ucitajStavke(long id) throws Exception {
        UcitajStavkeSO operacija = new UcitajStavkeSO();
        operacija.izvrsi(id,null);
        return operacija.getStavke();
    }
}
