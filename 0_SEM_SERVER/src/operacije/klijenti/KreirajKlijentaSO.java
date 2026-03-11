/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijenti;

import domen.Klijent;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class KreirajKlijentaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Klijent)){
            throw new Exception("Sistem nije mogao da zapamti klijenta");
        }
        Klijent k = (Klijent) objekat;
        if(k.getIme()==null || k.getIme().trim().isEmpty() || k.getIme().trim().length()<3){
            throw new Exception("GRESKA IME");
        }
        if(k.getPrezime()==null || k.getPrezime().trim().isEmpty() || k.getPrezime().trim().length()<3){
            throw new Exception("GRESKA PREZIME");
        }
        if(k.getBrojTelefona()==null || !k.getBrojTelefona().matches("06\\d{8}")){
            throw new Exception("GRESKA BROJ TELEFONA");
        }
        if (k.getMesto() == null || k.getMesto().getIdMesto() <= 0) {
            throw new Exception("GRESKA MESTO");
        }
    
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Klijent) objekat);
    }
    
}
