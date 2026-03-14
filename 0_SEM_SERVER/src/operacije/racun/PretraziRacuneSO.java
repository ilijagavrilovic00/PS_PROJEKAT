/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.racun;

import domen.ApstraktniDomenskiObjekat;
import domen.Racun;
import domen.StavkaRacuna;
import java.util.ArrayList;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author ilija
 */
public class PretraziRacuneSO extends ApstraktnaGenerickaOperacija {

    private List<Racun> racuni;
    private Racun kriterijum;
    
    @Override
    protected void preduslovi(ApstraktniDomenskiObjekat objekat) throws Exception {
    if (objekat != null && !(objekat instanceof Racun)) {
            throw new Exception("Sistem ne moze da pretrazi racune: neispravan kriterijum.");
        }

        kriterijum = (Racun) objekat;
        if (kriterijum == null) {
            return;
        }

        if (kriterijum.getZaposleni() != null && kriterijum.getZaposleni().getIdZaposleni() <= 0) {
            throw new Exception("Sistem ne moze da pretrazi racune: neispravan zaposleni u kriterijumu.");
        }

        if (kriterijum.getKlijent() != null && kriterijum.getKlijent().getIdKlijent() <= 0) {
            throw new Exception("Sistem ne moze da pretrazi racune: neispravan klijent u kriterijumu.");
        }

        if (kriterijum.getStavke() != null && !kriterijum.getStavke().isEmpty()) {
            StavkaRacuna prvaStavka = kriterijum.getStavke().get(0);
            if (prvaStavka != null && prvaStavka.getDrustvenaIgra() != null
                    && prvaStavka.getDrustvenaIgra().getIdDrustvenaIgra() <= 0) {
                throw new Exception("Sistem ne moze da pretrazi racune: neispravna drustvena igra u kriterijumu.");
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(ApstraktniDomenskiObjekat objekat, String kljuc) throws Exception {
        StringBuilder uslov = new StringBuilder();
        uslov.append(" JOIN zaposleni ON racun.idZaposleni = zaposleni.idZaposleni ");
        uslov.append(" JOIN klijent ON racun.idKlijent = klijent.idKlijent ");

        
            boolean whereDodat = false;

            if (kriterijum != null && kriterijum.getZaposleni() != null) {
                uslov.append(whereDodat ? " AND " : " WHERE ");
                uslov.append("racun.idZaposleni=").append(kriterijum.getZaposleni().getIdZaposleni());
                whereDodat = true;
            }
            if (kriterijum != null && kriterijum.getKlijent() != null) {
                uslov.append(whereDodat ? " AND " : " WHERE ");
                uslov.append("racun.idKlijent=").append(kriterijum.getKlijent().getIdKlijent());
                whereDodat = true;
            }
            if (kriterijum != null && kriterijum.getStavke() != null && !kriterijum.getStavke().isEmpty()
                    && kriterijum.getStavke().get(0) != null && kriterijum.getStavke().get(0).getDrustvenaIgra() != null) {
                uslov.append(whereDodat ? " AND " : " WHERE ");
                uslov.append("EXISTS (SELECT 1 FROM stavka_racuna sr WHERE sr.idRacun=racun.idRacun ");
                uslov.append("AND sr.idDrustvenaIgra=")
                        .append(kriterijum.getStavke().get(0).getDrustvenaIgra().getIdDrustvenaIgra())
                        .append(")");
            }
        racuni = broker.getAll(new Racun(), uslov.toString());
    
        if (racuni == null) {
            racuni = new ArrayList<>();
            return;
        }

        for (Racun racun : racuni) {
            racun.setStavke(ucitajStavkeRacuna(racun.getIdRacun()));
        }
    }

    private List<StavkaRacuna> ucitajStavkeRacuna(long idRacuna) throws Exception {
        String uslovStavke = " JOIN drustvena_igra ON stavka_racuna.idDrustvenaIgra=drustvena_igra.idDrustvenaIgra"
                + " WHERE stavka_racuna.idRacun=" + idRacuna;

        List<StavkaRacuna> stavke = broker.getAll(new StavkaRacuna(), uslovStavke);
        return stavke != null ? stavke : new ArrayList<>();
    }
    
    public List<Racun> getRacuni() {
        return racuni;
    }
}