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
public class Zaposleni implements ApstraktniDomenskiObjekat{
    private long idZaposleni;
    private String ime;
    private String prezime;
    private String brojTelefona;
    private String korisnickoIme;
    private String sifra;

    public Zaposleni() {
    }

    public Zaposleni(long idZaposleni, String ime, String prezime, String brojTelefona, String korisnickoIme, String sifra) {
        this.idZaposleni = idZaposleni;
        this.ime = ime;
        this.prezime = prezime;
        this.brojTelefona = brojTelefona;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public long getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(long idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
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
        final Zaposleni other = (Zaposleni) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    

   
    @Override
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Zaposleni z = new Zaposleni();
            z.setIdZaposleni(rs.getLong("zaposleni.idZaposleni"));
            z.setIme(rs.getString("zaposleni.ime"));
            z.setPrezime(rs.getString("zaposleni.prezime"));
            z.setBrojTelefona(rs.getString("zaposleni.brojTelefona"));
            z.setKorisnickoIme(rs.getString("zaposleni.korisnickoIme"));
            z.setSifra(rs.getString("zaposleni.sifra"));

            lista.add(z);
        }

        return lista;
    }


    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, brojTelefona, korisnickoIme, sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"','"+prezime+"','"+brojTelefona+"','"+korisnickoIme+"','"+sifra+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "zaposleni.idZaposleni="+idZaposleni;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
       return "ime='"+ime+"', prezime='"+prezime+
       "', brojTelefona='"+brojTelefona+
       "', korisnickoIme='"+korisnickoIme+
       "', sifra='"+sifra+"'";
    }

    @Override
    public String vratiJoinUslov() {
        return "";
    }
    
    
}
