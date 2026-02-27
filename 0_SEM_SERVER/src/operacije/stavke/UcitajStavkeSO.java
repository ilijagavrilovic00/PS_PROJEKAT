/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.stavke;

import domen.StavkaIznajmljivanja;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class UcitajStavkeSO extends ApstraktnaGenerickaOperacija{
    List<StavkaIznajmljivanja> stavke;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " JOIN stavka_iznajmljivanja.idDrustvenaIgra=drustvena_igra.idDrustvenaIgra WHERE idIznajmljivanja="+(long)objekat+"";
        stavke = broker.getAll(new StavkaIznajmljivanja(), uslov);
    }

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }
    
}
