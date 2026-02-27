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
public class Smena implements ApstraktniDomenskiObjekat{
    private long idSmena;
    private String tipSmene;

    public Smena() {
    }

    public Smena(long idSmena, String tipSmene) {
        this.idSmena = idSmena;
        this.tipSmene = tipSmene;
    }

    public long getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(long idSmena) {
        this.idSmena = idSmena;
    }

    public String getTipSmene() {
        return tipSmene;
    }

    public void setTipSmene(String tipSmene) {
        this.tipSmene = tipSmene;
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
        final Smena other = (Smena) obj;
        return Objects.equals(this.tipSmene, other.tipSmene);
    }

    @Override
    public String toString() {
        return "Smena{" + "tipSmene=" + tipSmene + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "smena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Smena s = new Smena(); 
            s.setIdSmena(rs.getLong("smena.idSmena"));   
            s.setTipSmene(rs.getString("smena.tipSmene")); 

            lista.add(s);
        }

        return lista;
    }


    @Override
    public String vratiKoloneZaUbacivanje() {
        return "tipSmene";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+tipSmene+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "smena.idSmena="+idSmena;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "tipSmene='"+tipSmene+"'";
    }

    @Override
    public String vratiJoinUslov() {
        return "";
    }
    
    
}
