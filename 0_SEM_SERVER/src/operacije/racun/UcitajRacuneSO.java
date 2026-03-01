/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.racun;

import domen.Racun;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class UcitajRacuneSO extends ApstraktnaGenerickaOperacija{

    List<Racun> racuni;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        racuni= broker.getAll(new Racun(), " JOIN"
                + " zaposleni ON racun.idZaposleni = zaposleni.idZaposleni "
                + " JOIN "
                + "klijent ON racun.idKlijent = klijent.idKlijent");
    }

    public List<Racun> getRacuni() {
        return racuni;
    }
}
