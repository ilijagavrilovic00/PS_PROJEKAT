/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ilija
 */
public class DrustvenaIgra implements ApstraktniDomenskiObjekat {
    private long idDrustvenaIgra;
    private String naziv;
    private double cena;
    private String opis;

    public DrustvenaIgra() {
    }

    public DrustvenaIgra(long idDrustvenaIgra, String naziv, double cena, String opis) {
        this.idDrustvenaIgra = idDrustvenaIgra;
        this.naziv = naziv;
        this.cena = cena;
        this.opis = opis;
    }

    public long getIdDrustvenaIgra() {
        return idDrustvenaIgra;
    }

    public void setIdDrustvenaIgra(long idDrustvenaIgra) {
        this.idDrustvenaIgra = idDrustvenaIgra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "DrustvenaIgra{" + "naziv=" + naziv + ", cena=" + cena + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final DrustvenaIgra other = (DrustvenaIgra) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String vratiNazivTabele() {
        return "drustvena_igra";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            DrustvenaIgra igra = new DrustvenaIgra();
            igra.setIdDrustvenaIgra(rs.getLong("idDrustvenaIgra")); 
            igra.setNaziv(rs.getString("naziv"));                   
            igra.setCena(rs.getDouble("cena"));                    
            igra.setOpis(rs.getString("opis"));                     
            lista.add(igra);
        }

        return lista;
    }


    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,cena,opis";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "'," + cena + ",'" + opis + "'";
    }


    @Override
    public String vratiPrimarniKljuc() {
        return "drustvena_igra.idDrustvenaIgra="+idDrustvenaIgra;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', " +
           "cena=" + cena + ", " +
           "opis='" + opis + "'";
    }

    
    
}
