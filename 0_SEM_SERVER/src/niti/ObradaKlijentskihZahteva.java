/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.Iznajmljivanje;
import domen.Klijent;
import domen.Mesto;
import domen.StavkaIznajmljivanja;
import domen.Zaposleni;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;

/**
 *
 * @author ilija
 */
public class ObradaKlijentskihZahteva extends Thread{
    Socket socket;
    Primalac primalac;
    Posiljalac posiljalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        posiljalac = new Posiljalac(socket);
        primalac = new Primalac(socket);
    }
    
    @Override
    public void run() {
       
        while(!kraj){
           try {
           Zahtev zahtev =(Zahtev) primalac.primi();
           Odgovor odgovor = new Odgovor();
           
           if(zahtev==null){
               System.out.println("Klijent je zatvorio vezu!");
               kraj = true;
               continue;
           }
           
            switch (zahtev.getOperacija()) {
               case LOGIN:
                    Zaposleni z = (Zaposleni) zahtev.getParametar();
                    z = Controller.getInstance().login(z);
                    odgovor.setOdgovor(z);
                    break;
                case UCITAJ_KLIJENTE:
                    List<Klijent> klijenti = Controller.getInstance().ucitajKlijente();
                    odgovor.setOdgovor(klijenti);
                    break;
                case OBRISI_KLIJENTA:
                    try{
                    Klijent k = (Klijent) zahtev.getParametar();
                    Controller.getInstance().obrisiKlijenta(k);
                    odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    break; 
                case DODAJ_KLIJENTA:
                    Klijent k = (Klijent) zahtev.getParametar();
                    Controller.getInstance().dodajKlijenta(k);
                    odgovor.setOdgovor(null);
                    break;
                case UCITAJ_MESTA:
                    List<Mesto> mesta = Controller.getInstance().ucitajMesta();
                    odgovor.setOdgovor(mesta);
                    break;
                case AZURIRAJ_KLIJENTA:
                    Klijent k2 = (Klijent) zahtev.getParametar();
                    Controller.getInstance().azurirajKlijenta(k2);
                    odgovor.setOdgovor(null);
                    break;
                case UCITAJ_IZNAJMLJIVANJA:
                    List<Iznajmljivanje> i = Controller.getInstance().ucitajIznajmljivanja();
                    odgovor.setOdgovor(null);
                    break;
                case UCITAJ_STAVKE:
                    List<StavkaIznajmljivanja> stavke = Controller.getInstance().ucitajStavke((long)zahtev.getParametar());
                    odgovor.setOdgovor(null);
                    break;
                case OBRISI_IZNAJMLJIVANJE:
                    try{
                    Iznajmljivanje i1 = (Iznajmljivanje) zahtev.getParametar();
                    Controller.getInstance().obrisiIznajmljivanje(i1);
                    odgovor.setOdgovor(null);
                    }catch(Exception e){
                        odgovor.setOdgovor(e);
                    }
                    break;
                default:
                    System.out.println("GRESKA, OPERACIJA NE POSTOJI!");
            }
            posiljalac.posalji(odgovor);
            }catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                kraj = true;
            }
        }
        
       prekini(); 
    }
    
    public void prekini(){
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
    
    
}
