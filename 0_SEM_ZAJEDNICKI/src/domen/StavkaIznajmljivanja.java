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
public class StavkaIznajmljivanja  implements ApstraktniDomenskiObjekat{
    private static final int PODRAZUMEVANA_KOLICINA = 1;
    private Iznajmljivanje iznajmljivanje;
    private int rb;
    private double cena;
    private int kolicina = PODRAZUMEVANA_KOLICINA;
    private DrustvenaIgra drustvenaIgra;

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
        
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        if(kolicina<=0) this.kolicina = PODRAZUMEVANA_KOLICINA;
        else this.kolicina = kolicina;
    }
    
    public DrustvenaIgra getDrustvenaIgra() {
        return drustvenaIgra;
    }

    public void setDrustvenaIgra(DrustvenaIgra drustvenaIgra) {
        this.drustvenaIgra = drustvenaIgra;
    }
    
    public double izracunajIznos() {
        return kolicina * cena;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89*hash+this.rb;
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
        if (this.kolicina != other.kolicina) {
            return false;
        }
        
        if (!Objects.equals(this.iznajmljivanje, other.iznajmljivanje)) {
            return false;
        }
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
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
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            StavkaIznajmljivanja stavka = new StavkaIznajmljivanja();
            stavka.setRb(rs.getInt("rb"));
            stavka.setKolicina(rs.getInt("kolicina"));
            stavka.setCena(rs.getDouble("cena"));
            
            DrustvenaIgra di = new DrustvenaIgra();
            di.setCena(rs.getDouble("drustvena_igra.cena"));
            di.setNaziv(rs.getString("naziv"));
            di.setOpis(rs.getString("opis"));
            di.setIdDrustvenaIgra(rs.getLong("idDrustvenaIgra"));
            
            stavka.setDrustvenaIgra(di);
            lista.add(stavka);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb, kolicina, cena, idDrustvenaIgra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return rb + "," +
            kolicina + "," +
            cena + "," +
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

    @Override
    public String vratiJoinUslov() {
        return "";
    }
}
