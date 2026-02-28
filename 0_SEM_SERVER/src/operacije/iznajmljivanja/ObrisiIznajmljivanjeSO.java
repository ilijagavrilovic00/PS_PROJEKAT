/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.iznajmljivanja;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class ObrisiIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Iznajmljivanje)){
            throw new Exception("Sistem ne moze da obrise iznajmljivanje");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Iznajmljivanje i = (Iznajmljivanje) objekat;
        
        List<StavkaIznajmljivanja> stavke = i.getStavke();
        for(StavkaIznajmljivanja s: stavke){
            s.setIznajmljivanje(i);
            broker.delete(s);
        }
        
        broker.delete(i);
        
    }
    
}
