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
public class Mesto implements ApstraktniDomenskiObjekat {
    private long idMesto;
    private String naziv;

    public Mesto() {
    }

    public Mesto(long idMesto, String naziv) {
        this.idMesto = idMesto;
        this.naziv = naziv;
    }
    
    public long getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(long idMesto) {
        this.idMesto = idMesto;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Mesto)) return false;
        Mesto m = (Mesto) obj;
        return this.idMesto == m.idMesto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMesto);
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Mesto m = new Mesto();
            m.setIdMesto(rs.getLong("idMesto"));  // long tip
            m.setNaziv(rs.getString("naziv"));   // String tip

            lista.add(m);
        }

        return lista;
    }


    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "mesto.idMesto="+idMesto;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "'";
    }

    @Override
    public String vratiJoinUslov() {
        return "";
    }

    
    
}
