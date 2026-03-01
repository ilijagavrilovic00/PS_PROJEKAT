/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.stavke;

import domen.Racun;
import domen.StavkaRacuna;
import java.util.ArrayList;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class ObrisiStavkuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof StavkaRacuna)){
            throw new Exception("Sistem ne moze da obrise stavku racuna.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        StavkaRacuna s = (StavkaRacuna) objekat;
        broker.delete(s);
       azurirajUkupanIznosRacuna(s.getRacun().getIdRacun());
    }

    private void azurirajUkupanIznosRacuna(long idRacuna) throws Exception {
        String uslovZaRacun = " JOIN zaposleni ON racun.idZaposleni = zaposleni.idZaposleni "
                + " JOIN klijent ON racun.idKlijent = klijent.idKlijent "
                + " WHERE racun.idRacun=" + idRacuna;
        List<Racun> racuni = broker.getAll(new Racun(), uslovZaRacun);
        if (racuni.isEmpty()) {
            return;
        }

        Racun racun = racuni.get(0);

        String uslovZaStavke = " JOIN drustvena_igra ON stavka_racuna.idDrustvenaIgra=drustvena_igra.idDrustvenaIgra"
                + " WHERE stavka_racuna.idRacun=" + idRacuna;
        List<StavkaRacuna> stavke = broker.getAll(new StavkaRacuna(), uslovZaStavke);
        racun.setStavke(stavke != null ? stavke : new ArrayList<>());
        broker.edit(racun);
    }
    
    
}
