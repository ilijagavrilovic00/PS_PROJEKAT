/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.login;

import domen.Zaposleni;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class LoginOperacija extends ApstraktnaGenerickaOperacija {
    Zaposleni zaposleni = null;

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

  
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Zaposleni)){
            throw new Exception("Sistem nije mogao da pronadje zaposlenog");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<Zaposleni> sviZaposleni = broker.getAll((Zaposleni) objekat, null);
        
        for(Zaposleni z: sviZaposleni){
            if(z.equals((Zaposleni)objekat)){
                zaposleni = z;
                return;
            }
        }
    }
    
}
