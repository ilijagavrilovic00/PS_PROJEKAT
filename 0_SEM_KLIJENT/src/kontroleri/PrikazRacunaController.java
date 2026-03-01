 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import communication.Komunikacija;
import domen.DrustvenaIgra;
import domen.Klijent;
import domen.Racun;
import domen.StavkaRacuna;
import domen.Zaposleni;
import forme.FormaMod;
import forme.PrikazRacunaForma;
import forme.modeli.ModelTabeleRacuna;
import forme.modeli.ModelTabeleStavkeRacuna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import koordinator.Koordinator;

/**
 *
 * @author ilija
 */
public class PrikazRacunaController {

    private final PrikazRacunaForma pi;

    public PrikazRacunaController(PrikazRacunaForma pi) {
        this.pi = pi;
        addActionListener();
        addMouseListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pi.setVisible(true);
    }

    public void pripremiFormu() {
        popuniFiltere();
        osveziTabeluRacuna(Komunikacija.getInstance().ucitajRacune());

        List<StavkaRacuna> stavke = new ArrayList<>();
        ModelTabeleStavkeRacuna mtsi = new ModelTabeleStavkeRacuna(stavke);
        pi.getTblStavke().setModel(mtsi);
    }

    private void popuniFiltere() {
        DefaultComboBoxModel<Object> modelZaposleni = new DefaultComboBoxModel<>();
        modelZaposleni.addElement("Svi");
        for (Zaposleni z : Komunikacija.getInstance().ucitajZaposlene()) {
            modelZaposleni.addElement(z);
        }
        pi.getCmbZaposleni().setModel(modelZaposleni);

        DefaultComboBoxModel<Object> modelKlijenti = new DefaultComboBoxModel<>();
        modelKlijenti.addElement("Svi");
        for (Klijent k : Komunikacija.getInstance().ucitajKlijente()) {
            modelKlijenti.addElement(k);
        }
        pi.getCmbKlijenti().setModel(modelKlijenti);

        DefaultComboBoxModel<Object> modelIgre = new DefaultComboBoxModel<>();
        modelIgre.addElement("Sve");
        for (DrustvenaIgra i : Komunikacija.getInstance().ucitajDrustveneIgre()) {
            modelIgre.addElement(i);
        }
        pi.getCmbIgre().setModel(modelIgre);
    }

    private void osveziTabeluRacuna(List<Racun> racuni) {
        if (racuni == null) {
            racuni = new ArrayList<>();
        }
        ModelTabeleRacuna mti = new ModelTabeleRacuna(racuni);
        pi.getTblRacuni().setModel(mti);
    }

    private void pretraziRacune() {
        try {
            String idRacunText = pi.getTxtIdRacuna().getText().trim();
            Long trazeniIdRacuna = null;
            if (!idRacunText.isEmpty()) {
                trazeniIdRacuna = Long.parseLong(idRacunText);
            }

            Object izabraniZaposleni = pi.getCmbZaposleni().getSelectedItem();
            Long trazeniZaposleniId = (izabraniZaposleni instanceof Zaposleni)
                    ? ((Zaposleni) izabraniZaposleni).getIdZaposleni() : null;

            Object izabraniKlijent = pi.getCmbKlijenti().getSelectedItem();
            Long trazeniKlijentId = (izabraniKlijent instanceof Klijent)
                    ? ((Klijent) izabraniKlijent).getIdKlijent() : null;

            Object izabranaIgra = pi.getCmbIgre().getSelectedItem();
            Long trazenaIgraId = (izabranaIgra instanceof DrustvenaIgra)
                    ? ((DrustvenaIgra) izabranaIgra).getIdDrustvenaIgra() : null;

            List<Racun> sviRacuni = Komunikacija.getInstance().ucitajRacune();
            List<Racun> filtrirani = new ArrayList<>();

            for (Racun racun : sviRacuni) {
                if (trazeniIdRacuna != null && racun.getIdRacun() != trazeniIdRacuna) {
                    continue;
                }
                if (trazeniZaposleniId != null && (racun.getZaposleni() == null
                        || racun.getZaposleni().getIdZaposleni() != trazeniZaposleniId)) {
                    continue;
                }
                if (trazeniKlijentId != null && (racun.getKlijent() == null
                        || racun.getKlijent().getIdKlijent() != trazeniKlijentId)) {
                    continue;
                }

                if (trazenaIgraId != null) {
                    List<StavkaRacuna> stavke = Komunikacija.getInstance().ucitajStavke(racun.getIdRacun());
                    boolean sadrziIgru = false;
                    for (StavkaRacuna stavka : stavke) {
                        if (stavka.getDrustvenaIgra() != null
                                && stavka.getDrustvenaIgra().getIdDrustvenaIgra() == trazenaIgraId) {
                            sadrziIgru = true;
                            break;
                        }
                    }
                    if (!sadrziIgru) {
                        continue;
                    }
                }

                filtrirani.add(racun);
            }

            osveziTabeluRacuna(filtrirani);

            if (filtrirani.isEmpty()) {
                JOptionPane.showMessageDialog(pi, "Sistem ne moze da nadje racune po zadatim kriterijumima.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(pi, "Sistem je nasao racune po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(pi, "ID racuna mora biti broj.", "Greska", JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(pi, "Sistem ne moze da nadje racune po zadatim kriterijumima.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addActionListener() {
        pi.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretraziRacune();
            }
        });

        pi.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pi.getTblRacuni().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pi, "Sistem ne moze da obrise racun.", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleRacuna mti = (ModelTabeleRacuna) pi.getTblRacuni().getModel();
                    Racun r = mti.getLista().get(red);
                    List<StavkaRacuna> stavke = Komunikacija.getInstance().ucitajStavke(r.getIdRacun());
                    r.setStavke(stavke);
                    try {
                        Komunikacija.getInstance().obrisiRacun(r);
                        JOptionPane.showMessageDialog(pi, "Sistem je obrisao racun", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        pretraziRacune();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pi, "Sistem ne moze da obrise racun", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        pi.obrisiStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pi.getTblStavke().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pi, "Sistem ne moze da obrise stavku.", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleStavkeRacuna mtsr = (ModelTabeleStavkeRacuna) pi.getTblStavke().getModel();
                    StavkaRacuna s = mtsr.getLista().get(red);

                    try {
                        Komunikacija.getInstance().obrisiStavku(s);
                        JOptionPane.showMessageDialog(pi, "Sistem je obrisao stavku racuna", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        mtsr.obrisiStavku(s);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(pi, "Sistem ne moze da obrise stavku racuna", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        pi.izmeniStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pi.getTblStavke().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pi, "Sistem ne moze da izmeni stavku.", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleStavkeRacuna mtsr = (ModelTabeleStavkeRacuna) pi.getTblStavke().getModel();
                    StavkaRacuna sr = mtsr.getLista().get(red);
                    Koordinator.getInstance().dodajParam("stavka_za_izmenu", sr);
                    Koordinator.getInstance().otvoriIzmeniStavku();

                }
            }
        });
        pi.azuriranjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pi.getTblRacuni().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(pi, "Sistem ne moze da nadje racun.", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ModelTabeleRacuna mti = (ModelTabeleRacuna) pi.getTblRacuni().getModel();
                    Racun r = mti.getLista().get(red);
                    List<StavkaRacuna> stavke = Komunikacija.getInstance().ucitajStavke(r.getIdRacun());
                    r.setStavke(stavke);
                    JOptionPane.showMessageDialog(pi, "Sistem je nasao racun.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().dodajParam("razun_za_izmenu", r);
                    Koordinator.getInstance().otvoriGlavnuFormu(FormaMod.IZMENI);
                }
            }
        });
    }

    private void addMouseListener() {
        pi.getTblRacuni().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = pi.getTblRacuni().getSelectedRow();
                if (red != -1) {
                    ModelTabeleRacuna mtr = (ModelTabeleRacuna) pi.getTblRacuni().getModel();
                    Racun r = mtr.getLista().get(red);
                    List<StavkaRacuna> stavke = Komunikacija.getInstance().ucitajStavke(r.getIdRacun());
                    r.setStavke(stavke);
                    mtr.fireTableRowsUpdated(red, red);
                    ModelTabeleStavkeRacuna mtsi = new ModelTabeleStavkeRacuna(stavke);
                    pi.getTblStavke().setModel(mtsi);
                }
            }
        });
    }
}
