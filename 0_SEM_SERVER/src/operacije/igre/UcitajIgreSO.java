/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.igre;

import domen.ApstraktniDomenskiObjekat;
import domen.DrustvenaIgra;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class UcitajIgreSO extends ApstraktnaGenerickaOperacija{
    List<DrustvenaIgra> igre;
    @Override
    protected void preduslovi(ApstraktniDomenskiObjekat objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat objekat, String kljuc) throws Exception {
        igre = broker.getAll(objekat, kljuc);
    }

    public List<DrustvenaIgra> getIgre() {
        return igre;
    }
}
