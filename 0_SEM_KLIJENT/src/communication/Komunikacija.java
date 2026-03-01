/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domen.DrustvenaIgra;
import domen.Racun;
import domen.Klijent;
import domen.Mesto;
import domen.StavkaRacuna;
import domen.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import komunikacija.Odgovor;
import komunikacija.Operacija;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import koordinator.Koordinator;

/**
 *
 * @author ilija
 */
public class Komunikacija {
   private Socket soket;
   private Posiljalac posiljalac;
   private Primalac primalac;
   
   private static Komunikacija instance;
   
   private Komunikacija(){
   }

    public static Komunikacija getInstance() {
        if(instance==null){
            instance = new Komunikacija();
        }
        return instance;
    }
   public void konekcija() throws Exception{
      try{
       soket = new Socket("localhost", 9000);
       posiljalac = new Posiljalac(soket);
       primalac = new Primalac(soket);
      }catch(IOException ex){
        System.out.println("SERVER NIJE POVEZAN.");
      }
       
   }

    public Zaposleni login(String username, String password) {
        Zaposleni z = new Zaposleni();
        z.setKorisnickoIme(username);
        z.setSifra(password);
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, z);
        
        posiljalac.posalji(zahtev);
        
        Odgovor odg = (Odgovor) primalac.primi();
        
        z = (Zaposleni) odg.getOdgovor();
        return z;
    }

    public List<Klijent> ucitajKlijente() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KLIJENTE, null);
        List<Klijent> klijenti = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        klijenti = (List<Klijent>) odg.getOdgovor();
        return klijenti;
    }

    public void obrisiKlijenta(Klijent k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }else{
            System.out.println("GRESKA NISI");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }

    }

    public void dodajKlijenta(Klijent k) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg == null) {
            throw new RuntimeException("Nije stigao odgovor servera za dodavanje klijenta.");
        }
        if (odg.getOdgovor() instanceof Exception) {
            throw new RuntimeException((Exception) odg.getOdgovor());
        }
        if (odg.getOdgovor() != null) {
            throw new RuntimeException("Sistem ne moze da zapamti klijenta.");
        }
    }

    public List<Mesto> ucitajMesta() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA, null);
        List<Mesto> mesta = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        mesta = (List<Mesto>) odg.getOdgovor();
        return mesta;
    }

    public void azurirajKlijenta(Klijent k) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_KLIJENTA, k);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        if (odg == null) {
            throw new RuntimeException("Nije stigao odgovor servera za azuriranje klijenta.");
        }
        if (odg.getOdgovor() instanceof Exception) {
            throw new RuntimeException((Exception) odg.getOdgovor());
        }
        if (odg.getOdgovor() != null) {
            throw new RuntimeException("Sistem ne moze da zapamti klijenta.");
        }
        Koordinator.getInstance().osveziFormu();
    
    }

    

    

    public void obrisiRacun(Racun r) throws Exception {
       Zahtev zahtev = new Zahtev(Operacija.OBRISI_RACUN, r);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }else{
            System.out.println("GRESKA NISI");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public List<Zaposleni> ucitajZaposlene() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_ZAPOSLENE, null);
        List<Zaposleni> zaposleni = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        zaposleni = (List<Zaposleni>) odg.getOdgovor();
        return zaposleni;
    }

    public List<DrustvenaIgra> ucitajDrustveneIgre() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_IGRE, null);
        List<DrustvenaIgra> igre = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        igre = (List<DrustvenaIgra>) odg.getOdgovor();
        return igre;
    }

    public void dodajRacun(Racun r) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_RACUN, r);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        if (odg == null) {
            throw new RuntimeException("Nije stigao odgovor servera za dodavanje racuna.");
        }
        if (odg.getOdgovor() instanceof Exception) {
            throw new RuntimeException((Exception) odg.getOdgovor());
        }
        if (odg.getOdgovor() != null) {
            throw new RuntimeException("Sistem ne moze da zapamti racun.");
        }
    }

    public void obrisiStavku(StavkaRacuna s) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_STAVKU, s);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }else{
            System.out.println("GRESKA NISI");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
        }
    }

    public void azurirajStavku(StavkaRacuna sr) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_STAVKU, sr);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        if (odg == null) {
            throw new RuntimeException("Nije stigao odgovor servera za azuriranje stavke.");
        }
        if (odg.getOdgovor() instanceof Exception) {
            throw new RuntimeException((Exception) odg.getOdgovor());
        }
        if (odg.getOdgovor() != null) {
            throw new RuntimeException("Sistem ne moze da azurira stavku racuna.");
        }
        Koordinator.getInstance().osveziFormu();
    }

    public void izmeniRacun(Racun r) {
        Zahtev zahtev = new Zahtev(Operacija.AZURIRAJ_RACUN, r);
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        if (odg == null) {
            throw new RuntimeException("Nije stigao odgovor servera za azuriranje racuna.");
        }
        if (odg.getOdgovor() instanceof Exception) {
            throw new RuntimeException((Exception) odg.getOdgovor());
        }
        if (odg.getOdgovor() != null) {
            throw new RuntimeException("Sistem ne moze da zapamti racun.");
        }
    }

    public List<Racun> ucitajRacune() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RACUNE, null);
        List<Racun> racuni = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if(odg==null){
            throw new RuntimeException("Nije stigao odgovor servera za ucitavanje racuna.");       
        }
        if(odg.getOdgovor() instanceof Exception){
            throw new RuntimeException((Exception) odg.getOdgovor());
        }
         if(odg.getOdgovor()==null){
            return racuni;
        }
        racuni= (List<Racun>) odg.getOdgovor();
        return racuni;
    }

    public List<StavkaRacuna> ucitajStavke(long idRacun) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE, idRacun);
        List<StavkaRacuna> stavke = new ArrayList<>();

        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        if (odg == null) {
            throw new RuntimeException("Nije stigao odgovor servera za ucitavanje stavki.");
        }
        if (odg.getOdgovor() instanceof Exception) {
            throw new RuntimeException((Exception) odg.getOdgovor());
        }
        if (odg.getOdgovor() == null) {
            return stavke;
        }

        stavke = (List<StavkaRacuna>) odg.getOdgovor();
        return stavke;
    }
   
}