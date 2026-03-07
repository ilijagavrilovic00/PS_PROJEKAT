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
        Racun r = (Racun) objekat;
        if (r.getStavke() == null || r.getStavke().isEmpty()) {
            throw new Exception("Sistem ne moze da doda racun: racun mora imati bar jednu stavku.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Racun r = (Racun) objekat;
       
        List<StavkaRacuna> stavke = r.getStavke();
        if (stavke == null || stavke.isEmpty()) {
            throw new Exception("Sistem ne moze da doda racun: racun mora imati bar jednu stavku.");
        }

        r.setStavke(stavke);
        int idRacun = broker.addReturnKey(r);
        r.setIdRacun(idRacun);

        for (StavkaRacuna s : stavke) {
            s.setRacun(r);
            broker.add(s);
        }
       
    }
    
}
