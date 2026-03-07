/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.racun;

import domen.Racun;
import domen.StavkaRacuna;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class AzurirajRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Racun)){
            throw new Exception("Sistem ne moze da doda racun: neispravan unos");
        }
    
        Racun r = (Racun) objekat;
        if (r.getIdRacun() <= 0) {
            throw new Exception("Sistem ne moze da zapamti racun: neispravan ID racuna.");
        }
        if (r.getStavke() == null || r.getStavke().isEmpty()) {
            throw new Exception("Sistem ne moze da zapamti racun: racun mora imati bar jednu stavku.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Racun r = (Racun) objekat;
        List<StavkaRacuna> noveStavke = r.getStavke();

        r.setStavke(noveStavke);
        
        broker.edit(r);
        List<StavkaRacuna> stareStavke = ucitajStavkeRacuna(r.getIdRacun());

        Set<Integer> stariRbBrojevi = new HashSet<>();
        for (StavkaRacuna staraStavka : stareStavke) {
            stariRbBrojevi.add(staraStavka.getRb());
        }
        
        for (StavkaRacuna novaStavka : noveStavke) {
            novaStavka.setRacun(r);
            if (stariRbBrojevi.contains(novaStavka.getRb())) {
                broker.edit(novaStavka);
            } else {
                broker.add(novaStavka);
            }
        }
         Set<Integer> noviRbBrojevi = new HashSet<>();
        for (StavkaRacuna novaStavka : noveStavke) {
            noviRbBrojevi.add(novaStavka.getRb());
        }

        for (StavkaRacuna staraStavka : stareStavke) {
            if (!noviRbBrojevi.contains(staraStavka.getRb())) {
                broker.delete(staraStavka);
            }
        }
       
}
    
     private List<StavkaRacuna> ucitajStavkeRacuna(long idRacuna) throws Exception {
        String uslov = " JOIN drustvena_igra ON stavka_racuna.idDrustvenaIgra=drustvena_igra.idDrustvenaIgra"
                + " WHERE stavka_racuna.idRacun=" + idRacuna;

        List<StavkaRacuna> stavke = broker.getAll(new StavkaRacuna(), uslov);
        return stavke != null ? stavke : new ArrayList<>();
    
    }
}
