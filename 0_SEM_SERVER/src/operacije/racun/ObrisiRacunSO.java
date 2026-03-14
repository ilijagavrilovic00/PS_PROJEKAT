/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.racun;

import domen.ApstraktniDomenskiObjekat;
import domen.Racun;
import domen.StavkaRacuna;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class ObrisiRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(ApstraktniDomenskiObjekat objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Racun)){
            throw new Exception("Sistem ne moze da obrise racun");
        }
        Racun r = (Racun) objekat;
        if (r.getIdRacun() <= 0) {
            throw new Exception("Sistem ne moze da obrise racun: neispravan ID racuna.");
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat objekat, String kljuc) throws Exception {
        Racun r = (Racun) objekat;
        
        List<StavkaRacuna> stavke = r.getStavke();
        if (stavke != null) {
            for(StavkaRacuna s: stavke){
                s.setRacun(r);
                broker.delete(s);
            }
        }
        broker.delete(r);
        
    }
    
}
