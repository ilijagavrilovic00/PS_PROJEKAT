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
public class AzurirajRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Racun)){
            throw new Exception("Sistem ne moze da doda racun: neispravan unos");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Racun r = (Racun)objekat;
        
        broker.edit(r);
        String uslov = " JOIN drustvena_igra ON stavka_racuna.idDrustvenaIgra=drustvena_igra.idDrustvenaIgra"
                + " WHERE stavka_racuna.idRacun=" + (long) r.getIdRacun();
        List<StavkaRacuna> stareStavke = broker.getAll(new StavkaRacuna(), uslov);
        
        for(StavkaRacuna s: stareStavke){
            broker.delete(s);
        }
        
        List<StavkaRacuna> noveStavke = r.getStavke();
        if(noveStavke==null){
            return;
        }
        for(StavkaRacuna sr: noveStavke){
            sr.setRacun(r);
            broker.add(sr);
        }
    }
    
}
