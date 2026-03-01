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
import javax.swing.JComboBox;
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
        popuniZaposlene();
        popuniKlijente();
        popuniDrustveneIgre();
        addActionListener();
        addMouseListener();
    }
    
    
     public void otvoriFormu() {
        pripremiFormu();
        pi.setVisible(true);
    }

    public void pripremiFormu() {
        List<Racun> racuni = new ArrayList<>();
        try {
            racuni = Komunikacija.getInstance().ucitajRacune();
             if(racuni.isEmpty()){
                JOptionPane.showMessageDialog(pi, "Sistem ne moze da ucita racune.", "GRESKA", JOptionPane.ERROR_MESSAGE);
             }else{
                JOptionPane.showMessageDialog(pi, "Sistem je ucitao racune.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
             }
        } catch (RuntimeException ex) {
           JOptionPane.showMessageDialog(pi, "Sistem ne moze da ucita racune.", "Greska", JOptionPane.ERROR_MESSAGE);
        }
        ModelTabeleRacuna mti= new ModelTabeleRacuna(racuni);
        pi.getTblRacuni().setModel(mti);
        
        List<StavkaRacuna> stavke = new ArrayList<>();
        ModelTabeleStavkeRacuna mtsi = new ModelTabeleStavkeRacuna(stavke);
        pi.getTblStavke().setModel(mtsi);
   
    }

    private void addActionListener() {
        pi.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pi.getTblRacuni().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pi, "Sistem ne moze da obrise racun.", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleRacuna mti = (ModelTabeleRacuna) pi.getTblRacuni().getModel();
                    Racun r = mti.getLista().get(red);
                    List<StavkaRacuna> stavke = Komunikacija.getInstance().ucitajStavke(r.getIdRacun());
                    r.setStavke(stavke);
                    try{
                        Komunikacija.getInstance().obrisiRacun(r);
                        JOptionPane.showMessageDialog(pi, "Sistem je obrisao racun", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(pi, "Sistem ne moze da obrise racun", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        pi.obrisiStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pi.getTblStavke().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pi, "Sistem ne moze da obrise stavku.", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleStavkeRacuna mtsr = (ModelTabeleStavkeRacuna) pi.getTblStavke().getModel();
                    StavkaRacuna s = mtsr.getLista().get(red);
                   
                    try{
                        Komunikacija.getInstance().obrisiStavku(s);
                        JOptionPane.showMessageDialog(pi, "Sistem je obrisao stavku racuna", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        mtsr.obrisiStavku(s);
                        // pripremiFormu();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(pi, "Sistem ne moze da obrise stavku racuna", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        pi.izmeniStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pi.getTblStavke().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pi, "Sistem ne moze da izmeni stavku.", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
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
                if(red==-1){
                    JOptionPane.showMessageDialog(pi, "Sistem ne moze da nadje racun.", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleRacuna mti = (ModelTabeleRacuna) pi.getTblRacuni().getModel();
                    Racun r = mti.getLista().get(red);
                    List<StavkaRacuna> stavke = Komunikacija.getInstance().ucitajStavke(r.getIdRacun());
                    r.setStavke(stavke);
                    JOptionPane.showMessageDialog(pi, "Sistem je nasao racun.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().dodajParam("razun_za_izmenu",r);
                    Koordinator.getInstance().otvoriGlavnuFormu(FormaMod.IZMENI);
                }
            }
        });
         pi.getBtnPretrazi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Racun kriterijum = kreirajKriterijumPretrage();
                List<Racun> racuni;
                try {
                    racuni = Komunikacija.getInstance().pretraziRacune(kriterijum);
                    if(racuni.isEmpty()){
                        JOptionPane.showMessageDialog(pi, "Sistem ne moze da nadje racune po zadatim kriterijumima.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(pi, "Sistem je nasao racune po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(pi, "Sistem ne moze da nadje racune po zadatim kriterijumima.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ModelTabeleRacuna mti= new ModelTabeleRacuna(racuni);
                pi.getTblRacuni().setModel(mti);
                pi.getTblStavke().setModel(new ModelTabeleStavkeRacuna(new ArrayList<>()));
            }

            private Racun kreirajKriterijumPretrage() {
                Racun kriterijum = new Racun();

                Zaposleni zaposleni = (Zaposleni) pi.getCmbZaposleni().getSelectedItem();
                Klijent klijent = (Klijent) pi.getCmbKlijent().getSelectedItem();
                DrustvenaIgra igra = (DrustvenaIgra) pi.getCmbDrustvenaIgra().getSelectedItem();

                 if (zaposleni != null && zaposleni.getIdZaposleni() > 0) {
                    kriterijum.setZaposleni(zaposleni);
                }

                if (klijent != null && klijent.getIdKlijent() > 0) {
                    kriterijum.setKlijent(klijent);
                }

                if (igra != null && igra.getIdDrustvenaIgra() > 0) {
                    StavkaRacuna stavka = new StavkaRacuna();
                    stavka.setDrustvenaIgra(igra);
                    List<StavkaRacuna> stavke = new ArrayList<>();
                    stavke.add(stavka);
                    kriterijum.setStavke(stavke);
                }

                return kriterijum;
            }
        });
    }

    private void addMouseListener() {
        pi.getTblRacuni().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = pi.getTblRacuni().getSelectedRow();
                if(red!=-1){
                    ModelTabeleRacuna mtr = (ModelTabeleRacuna) pi.getTblRacuni().getModel();
                    Racun r = mtr.getLista().get(red);
                    List<StavkaRacuna> stavke = Komunikacija.getInstance().ucitajStavke(r.getIdRacun());
                    //novo
                    r.setStavke(stavke);
                    mtr.fireTableRowsUpdated(red, red);
                    //
                    ModelTabeleStavkeRacuna mtsi = new ModelTabeleStavkeRacuna(stavke);
                    pi.getTblStavke().setModel(mtsi);
                }
            }
        });
    }

     private void popuniZaposlene() {
        JComboBox<Zaposleni> cmb = pi.getCmbZaposleni();
        cmb.removeAllItems();
        cmb.addItem(null);
        for (Zaposleni z : Komunikacija.getInstance().ucitajZaposlene()) {
            cmb.addItem(z);
        }
    }

    private void popuniKlijente() {
        JComboBox<Klijent> cmb = pi.getCmbKlijent();
        cmb.removeAllItems();
        cmb.addItem(null);
        for (Klijent k : Komunikacija.getInstance().ucitajKlijente()) {
            cmb.addItem(k);
        }
    }

    private void popuniDrustveneIgre() {
        JComboBox<DrustvenaIgra> cmb = pi.getCmbDrustvenaIgra();
        cmb.removeAllItems();
        cmb.addItem(null);
        for (DrustvenaIgra di : Komunikacija.getInstance().ucitajDrustveneIgre()) {
            cmb.addItem(di);
        }
    }
}
