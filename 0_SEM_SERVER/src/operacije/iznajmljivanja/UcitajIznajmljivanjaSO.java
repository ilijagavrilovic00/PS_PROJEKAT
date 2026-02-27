/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.iznajmljivanja;

import domen.Iznajmljivanje;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class UcitajIznajmljivanjaSO extends ApstraktnaGenerickaOperacija{

    List<Iznajmljivanje> iznajmljivanja;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        iznajmljivanja= broker.getAll(new Iznajmljivanje(), " JOIN"
                + " zaposleni ON iznajmljivanje.idZaposleni = zaposleni.idZaposleni "
                + " JOIN "
                + "klijent ON iznajmljivanje.idKlijent = klijent.idKlijent");
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }
}
