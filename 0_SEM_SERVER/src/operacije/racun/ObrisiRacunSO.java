/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.racun;

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
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Racun)){
            throw new Exception("Sistem ne moze da obrise iznajmljivanje");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Racun r = (Racun) objekat;
        
        List<StavkaRacuna> stavke = r.getStavke();
        for(StavkaRacuna s: stavke){
            s.setRacun(r);
            broker.delete(s);
        }
        
        broker.delete(r);
        
    }
    
}
