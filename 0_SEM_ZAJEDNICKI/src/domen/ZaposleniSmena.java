/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ilija
 */
public class ZaposleniSmena implements ApstraktniDomenskiObjekat{
    private Zaposleni zaposleni;
    private Smena smena;
    private Date datum;
    private String beleskeOSmeni;

    public ZaposleniSmena() {
    }

    public ZaposleniSmena(Zaposleni zaposleni, Smena smena, Date datum, String beleskeOSmeni) {
        this.zaposleni = zaposleni;
        this.smena = smena;
        this.datum = datum;
        this.beleskeOSmeni = beleskeOSmeni;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Smena getSmena() {
        return smena;
    }

    public void setSmena(Smena smena) {
        this.smena = smena;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getBeleskeOSmeni() {
        return beleskeOSmeni;
    }

    public void setBeleskeOSmeni(String beleskeOSmeni) {
        this.beleskeOSmeni = beleskeOSmeni;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final ZaposleniSmena other = (ZaposleniSmena) obj;
        if (!Objects.equals(this.zaposleni, other.zaposleni)) {
            return false;
        }
        if (!Objects.equals(this.smena, other.smena)) {
            return false;
        }
        return Objects.equals(this.datum, other.datum);
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposleni_smena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   @Override
    public String vratiKoloneZaUbacivanje() {
        return "idZaposleni, idSmena, datum, beleskeOSmeni";
    }


    @Override
    public String vratiVrednostiZaUbacivanje() {
        return zaposleni.getIdZaposleni()+","+
           smena.getIdSmena()+","+
           "'"+new java.sql.Date(datum.getTime())+"',"+
           "'"+beleskeOSmeni+"'";
    }
    @Override
    public String vratiPrimarniKljuc() {
        return "zaposleni_smena.idZaposleni=" + zaposleni.getIdZaposleni() +
           " AND zaposleni_smena.idSmena=" + smena.getIdSmena() +
           " AND zaposleni_smena.datum='" + new java.sql.Date(datum.getTime()) + "'";
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiJoinUslov() {
        return "";
    }
    
}
