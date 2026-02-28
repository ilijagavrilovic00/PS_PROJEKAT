/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Mesto;
import domen.StavkaIznajmljivanja;
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
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
        }else{
            System.out.println("GRESKA");
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
        if(odg.getOdgovor()==null){
            System.out.println("USPEH");
            Koordinator.getInstance().osveziFormu();
        }else{
            System.out.println("GRESKA");
        }
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_IZNAJMLJIVANJA, null);
        List<Iznajmljivanje> iznajmljivanja = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        
        iznajmljivanja= (List<Iznajmljivanje>) odg.getOdgovor();
        return iznajmljivanja;
    }

    public List<StavkaIznajmljivanja> ucitajStavke(long idIznajmljivanje) {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE, idIznajmljivanje);
        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        posiljalac.posalji(zahtev);
        Odgovor odg = (Odgovor) primalac.primi();
        stavke = (List<StavkaIznajmljivanja>) odg.getOdgovor();
        return stavke;
        
    }

    public void obrisiIznajmljivanje(Iznajmljivanje i) throws Exception {
       Zahtev zahtev = new Zahtev(Operacija.OBRISI_IZNAJMLJIVANJE, i);
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
   
}
