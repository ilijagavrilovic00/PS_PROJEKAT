/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ilija
 */
public class Iznajmljivanje implements ApstraktniDomenskiObjekat{
    private static final int GRANICA_ZA_POPUST = 5;
    private static final double PROCENAT_POPUSTA = 0.10;
    
    private long idIznajmljivanje;
    private double ukupanIznos;
    private Zaposleni zaposleni;
    private Date datum;
    private Klijent klijent;
    private List<StavkaIznajmljivanja> stavke;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(long idIznajmljivanje, double ukupanIznos, Date datum, Zaposleni zaposleni, Klijent klijent, List<StavkaIznajmljivanja> stavke) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.ukupanIznos = ukupanIznos;
        this.zaposleni = zaposleni;
        this.klijent = klijent;
        this.stavke = stavke;
        this.datum = datum;
    }
    
    public Iznajmljivanje(long idIznajmljivanje, double ukupanIznos, Zaposleni zaposleni, Klijent klijent) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.ukupanIznos = ukupanIznos;
        this.zaposleni = zaposleni;
        this.klijent = klijent;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    
    public long getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(long idIznajmljivanje) {
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
        this.ukupanIznos = izracunajUkupanIznosSaPopustom();
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
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            Iznajmljivanje iznajmljivanje = new Iznajmljivanje();
            iznajmljivanje.setIdIznajmljivanje(rs.getLong("idIznajmljivanje"));
            iznajmljivanje.setUkupanIznos(rs.getDouble("ukupanIznos"));
            iznajmljivanje.setDatum(rs.getDate("datum"));
            // ovde potencijalno greska zbog prebacivanja datuma iz sql tip u java tip
            Zaposleni zaposleni = new Zaposleni();
            zaposleni.setIdZaposleni(rs.getLong("idZaposleni"));
            zaposleni.setIme(rs.getString("zaposleni.ime"));
            zaposleni.setPrezime(rs.getString("zaposleni.prezime"));
            iznajmljivanje.setZaposleni(zaposleni);
            
            Klijent klijent = new Klijent();
            klijent.setIdKlijent(rs.getLong("klijent.idKlijent"));
            klijent.setIme(rs.getString("klijent.ime"));
            klijent.setPrezime(rs.getString("klijent.prezime"));
            iznajmljivanje.setKlijent(klijent);
           
            iznajmljivanje.setStavke(new ArrayList<>());
            lista.add(iznajmljivanje);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datum, ukupanIznos, idZaposleni, idKlijent";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + new java.sql.Date(datum.getTime()) + "'," + ukupanIznos + "," +
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
        return "datum='" + new java.sql.Date(datum.getTime())+ "'," +
           "ukupanIznos=" + ukupanIznos + ", " +
           "idZaposleni=" + zaposleni.getIdZaposleni() + ", " +
           "idKlijent=" + klijent.getIdKlijent();
    }

    @Override
    public String vratiJoinUslov() {
        return "";
    }

    public int izracunajUkupnuKolicinu(){
        if(stavke==null){
            return 0;
        }
        return stavke.stream().mapToInt(StavkaIznajmljivanja::getKolicina).sum();
    }
    
    private double izracunajUkupanIznosSaPopustom() {
        if(stavke==null || stavke.isEmpty()){
            return ukupanIznos;
        }
        
        double osnovniIznos = stavke.stream()
                .mapToDouble(StavkaIznajmljivanja::izracunajIznos)
                .sum();
        
        if(izracunajUkupnuKolicinu() >= GRANICA_ZA_POPUST){
            return osnovniIznos * (1-PROCENAT_POPUSTA);
        }
        
        return osnovniIznos;
    }

    

    
    
}
