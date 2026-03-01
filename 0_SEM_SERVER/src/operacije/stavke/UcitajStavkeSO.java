/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.stavke;

import domen.StavkaRacuna;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class UcitajStavkeSO extends ApstraktnaGenerickaOperacija{
    List<StavkaRacuna> stavke;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
       String uslov = " JOIN drustvena_igra ON stavka_racuna.idDrustvenaIgra=drustvena_igra.idDrustvenaIgra"
                + " WHERE stavka_racuna.idRacun=" + (long) objekat;
       stavke = broker.getAll(new StavkaRacuna(), uslov);
    }

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }
    
}
