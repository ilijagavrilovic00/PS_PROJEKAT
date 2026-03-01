/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.stavke;

import domen.StavkaRacuna;
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
        
    }
    
}
