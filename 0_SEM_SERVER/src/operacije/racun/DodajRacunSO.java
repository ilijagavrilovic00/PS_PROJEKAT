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
public class DodajRacunSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Racun)){
            throw new Exception("Sistem ne moze da doda racun: neispravan unos");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Racun r = (Racun) objekat;
        int idRacun = broker.addReturnKey(r);
        
        List<StavkaRacuna> stavke = r.getStavke();
        for(StavkaRacuna s: stavke){
            r.setIdRacun(idRacun);
            s.setRacun(r);
            broker.add(s);
        }
    }
    
}
