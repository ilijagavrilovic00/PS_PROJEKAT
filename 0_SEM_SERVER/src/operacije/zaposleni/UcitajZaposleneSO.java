/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zaposleni;

import domen.ApstraktniDomenskiObjekat;
import domen.Zaposleni;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class UcitajZaposleneSO extends ApstraktnaGenerickaOperacija {

    List<Zaposleni> zaposleni;
    @Override
    protected void preduslovi(ApstraktniDomenskiObjekat objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat objekat, String kljuc) throws Exception {
        zaposleni = broker.getAll(objekat, kljuc);
    }

    public List<Zaposleni> getZaposleni() {
        return zaposleni;
    }
    
}
