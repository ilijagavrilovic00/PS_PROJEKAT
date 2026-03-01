/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import domen.Zaposleni;
import forme.DodajKlijentaForma;
import forme.DodajStavkuForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.LoginForma;
import forme.PrikazRacunaForma;
import forme.PrikazKlijenataForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.DodajKlijentaController;
import kontroleri.DodajStavkuController;
import kontroleri.GlavnaFormaController;
import kontroleri.LoginController;
import kontroleri.PrikazRacunaController;
import kontroleri.PrikazKlijenataController;

/**
 *
 * @author ilija
 */
public class Koordinator {
    private static Koordinator instance;
    private Zaposleni ulogovani;
    private LoginController loginController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazKlijenataController prikazKlijenataController;
    private DodajKlijentaController dodajKlijentaController;
    private Map<String, Object> parametri;
    private PrikazRacunaController prikazIznajmljivanjaController;
    private DodajStavkuController dodajStavkuController;
    
    private Koordinator(){
        parametri = new HashMap<>();
    }

    public static Koordinator getInstance() {
        if(instance==null){
            instance = new Koordinator();
        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForma());
        loginController.otvoriFormu();
    }
    
     public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }
    
    public void otvoriPrikazKlijenata() {
        prikazKlijenataController = new PrikazKlijenataController(new PrikazKlijenataForma());    
        prikazKlijenataController.otvoriFormu();
    }
    public void otvoriDodajKlijenta(){
        dodajKlijentaController = new DodajKlijentaController(new DodajKlijentaForma());
        dodajKlijentaController.otvoriFormu(FormaMod.IZMENI);
    }

    
    public Zaposleni getUlogovani() {
        return ulogovani;
    }
    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }

    public void dodajParam(String s, Object o){
        parametri.put(s,o);
    }
    
    public Object vratiParam(String s){
        return parametri.get(s);
    }

    public void otvoriIzmeniKlijenta() {
        dodajKlijentaController = new DodajKlijentaController(new DodajKlijentaForma());
        dodajKlijentaController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormu() {
        prikazKlijenataController.osveziFormu();
    }

    public void otvoriPrikazIznajmljivanja() {
        prikazIznajmljivanjaController= new PrikazRacunaController(new PrikazRacunaForma());
        prikazIznajmljivanjaController.otvoriFormu();
    }

    public void otvoriIzmeniStavku() {
        dodajStavkuController = new DodajStavkuController(new DodajStavkuForma());
        dodajStavkuController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriGlavnuFormu(FormaMod formaMod) {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu(formaMod);
    
    }
}
