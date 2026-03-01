/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.igre;

import domen.DrustvenaIgra;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class DodajDrustvenuIgruSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof DrustvenaIgra)) {
            throw new Exception("Sistem ne moze da doda drustvenu igru: neispravan unos");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((DrustvenaIgra) objekat);
    }
    
}
