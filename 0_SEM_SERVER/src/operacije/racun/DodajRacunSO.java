/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.racun;

import domen.Racun;
import domen.ApstraktniDomenskiObjekat;
import domen.StavkaRacuna;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class DodajRacunSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(ApstraktniDomenskiObjekat objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Racun)){
            throw new Exception("Sistem ne moze da doda racun: neispravan unos");
        }
        Racun r = (Racun) objekat;
        validirajRacun(r);
    }

    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat objekat, String kljuc) throws Exception {
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

    private void validirajRacun(Racun r) throws Exception {
        if (r.getDatum() == null) {
            throw new Exception("Sistem ne moze da doda racun: datum je obavezan.");
        }
        if (r.getZaposleni() == null || r.getZaposleni().getIdZaposleni() <= 0) {
            throw new Exception("Sistem ne moze da doda racun: zaposleni je obavezan.");
        }
        if (r.getKlijent() == null || r.getKlijent().getIdKlijent() <= 0) {
            throw new Exception("Sistem ne moze da doda racun: klijent je obavezan.");
        }
        if (r.getStavke() == null || r.getStavke().isEmpty()) {
            throw new Exception("Sistem ne moze da doda racun: racun mora imati bar jednu stavku.");
        }

        for (StavkaRacuna stavka : r.getStavke()) {
            if (stavka == null) {
                throw new Exception("Sistem ne moze da doda racun: neispravna stavka racuna.");
            }
            if (stavka.getRb() <= 0) {
                throw new Exception("Sistem ne moze da doda racun: redni broj stavke mora biti veci od nule.");
            }
            if (stavka.getDrustvenaIgra() == null || stavka.getDrustvenaIgra().getIdDrustvenaIgra() <= 0) {
                throw new Exception("Sistem ne moze da doda racun: drustvena igra na stavci je obavezna.");
            }
            if (stavka.getKolicina() <= 0) {
                throw new Exception("Sistem ne moze da doda racun: kolicina mora biti veca od nule.");
            }
            if (stavka.getCena() <= 0) {
                throw new Exception("Sistem ne moze da doda racun: cena stavke mora biti veca od nule.");
            }
        }
    }
    
}
