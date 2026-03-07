/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.racun;

import domen.Racun;
import domen.StavkaRacuna;
import java.util.ArrayList;
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
