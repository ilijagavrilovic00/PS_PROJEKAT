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
public class StavkaIznajmljivanja  implements ApstraktniDomenskiObjekat{
    private Iznajmljivanje iznajmljivanje;
    private int rb;
    private Date datumIznajmljivanja;
    private Date datumVracanja;
    private int brojDana;
    private double cenaPoDanu;
    private double iznos;
    private DrustvenaIgra drustvenaIgra;

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
        
    }

    public void setIdIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Date getDatumIznajmljivanja() {
        return datumIznajmljivanja;
    }

    public void setDatumIznajmljivanja(Date datumIznajmljivanja) {
        this.datumIznajmljivanja = datumIznajmljivanja;
    }

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public DrustvenaIgra getDrustvenaIgra() {
        return drustvenaIgra;
    }

    public void setDrustvenaIgra(DrustvenaIgra drustvenaIgra) {
        this.drustvenaIgra = drustvenaIgra;
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
        final StavkaIznajmljivanja other = (StavkaIznajmljivanja) obj;
        if (this.rb != other.rb) {
            return false;
        }
        if (this.brojDana != other.brojDana) {
            return false;
        }
        if (Double.doubleToLongBits(this.cenaPoDanu) != Double.doubleToLongBits(other.cenaPoDanu)) {
            return false;
        }
        if (Double.doubleToLongBits(this.iznos) != Double.doubleToLongBits(other.iznos)) {
            return false;
        }
        if (!Objects.equals(this.iznajmljivanje, other.iznajmljivanje)) {
            return false;
        }
        if (!Objects.equals(this.datumIznajmljivanja, other.datumIznajmljivanja)) {
            return false;
        }
        if (!Objects.equals(this.datumVracanja, other.datumVracanja)) {
            return false;
        }
        return Objects.equals(this.drustvenaIgra, other.drustvenaIgra);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavka_iznajmljivanja";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb, datumIznajmljivanja, datumVracanja, brojDana, cenaPoDanu, iznos, idDrustvenaIgra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return rb + "," +
            "'" + new java.sql.Date(datumIznajmljivanja.getTime()) + "'," +
            "'" + new java.sql.Date(datumVracanja.getTime()) + "'," +
            brojDana + "," +
            cenaPoDanu + "," +
            iznos + "," +
            drustvenaIgra.getIdDrustvenaIgra();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavka_iznajmljivanja.idIznajmljivanje="+iznajmljivanje.getIdIznajmljivanje()+" and stavka_iznajmljivanja.rb="+rb;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
