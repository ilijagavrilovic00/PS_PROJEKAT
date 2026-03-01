/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.racun;

import domen.Racun;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class UcitajRacuneSO extends ApstraktnaGenerickaOperacija{

    List<Racun> racuni;
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
             StringBuilder uslov = new StringBuilder();
        uslov.append(" JOIN zaposleni ON racun.idZaposleni = zaposleni.idZaposleni ");
        uslov.append(" JOIN klijent ON racun.idKlijent = klijent.idKlijent ");

        if (objekat instanceof Racun) {
            Racun kriterijum = (Racun) objekat;
            boolean whereDodat = false;

            if (kriterijum.getZaposleni() != null) {
                uslov.append(whereDodat ? " AND " : " WHERE ");
                uslov.append("racun.idZaposleni=").append(kriterijum.getZaposleni().getIdZaposleni());
                whereDodat = true;
            }

            if (kriterijum.getKlijent() != null) {
                uslov.append(whereDodat ? " AND " : " WHERE ");
                uslov.append("racun.idKlijent=").append(kriterijum.getKlijent().getIdKlijent());
                whereDodat = true;
            }

            if (kriterijum.getStavke() != null && !kriterijum.getStavke().isEmpty()
                    && kriterijum.getStavke().get(0).getDrustvenaIgra() != null) {
                uslov.append(whereDodat ? " AND " : " WHERE ");
                uslov.append("EXISTS (SELECT 1 FROM stavka_racuna sr WHERE sr.idRacun=racun.idRacun ");
                uslov.append("AND sr.idDrustvenaIgra=")
                        .append(kriterijum.getStavke().get(0).getDrustvenaIgra().getIdDrustvenaIgra())
                        .append(")");
            }
        }

        racuni = broker.getAll(new Racun(), uslov.toString());
    }

    public List<Racun> getRacuni() {
        return racuni;
    }
}
