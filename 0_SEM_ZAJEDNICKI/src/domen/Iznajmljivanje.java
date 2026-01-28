/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ilija
 */
public class Iznajmljivanje implements ApstraktniDomenskiObjekat{
    private int idIznajmljivanje;
    private double ukupanIznos;
    private Zaposleni zaposleni;
    private Klijent klijent;
    private List<StavkaIznajmljivanja> stavke;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanje, double ukupanIznos, Zaposleni zaposleni, Klijent klijent, List<StavkaIznajmljivanja> stavke) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.ukupanIznos = ukupanIznos;
        this.zaposleni = zaposleni;
        this.klijent = klijent;
        this.stavke = stavke;
    }
    
    public Iznajmljivanje(int idIznajmljivanje, double ukupanIznos, Zaposleni zaposleni, Klijent klijent) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.ukupanIznos = ukupanIznos;
        this.zaposleni = zaposleni;
        this.klijent = klijent;
    }

    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIznajmljivanja> stavke) {
        this.stavke = stavke;
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        if (this.idIznajmljivanje != other.idIznajmljivanje) {
            return false;
        }
        if (Double.doubleToLongBits(this.ukupanIznos) != Double.doubleToLongBits(other.ukupanIznos)) {
            return false;
        }
        if (!Objects.equals(this.zaposleni, other.zaposleni)) {
            return false;
        }
        return Objects.equals(this.klijent, other.klijent);
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ukupanIznos, idZaposleni, idKlijent";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return ukupanIznos + "," +
           zaposleni.getIdZaposleni() + "," +
           klijent.getIdKlijent();
    }


    @Override
    public String vratiPrimarniKljuc() {
        return "iznajmljivanje.idIznajmljivanje="+idIznajmljivanje;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {    
        return "ukupanIznos=" + ukupanIznos + ", " +
           "idZaposleni=" + zaposleni.getIdZaposleni() + ", " +
           "idKlijent=" + klijent.getIdKlijent();
    }

    
    
}
