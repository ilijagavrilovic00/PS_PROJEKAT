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
import forme.GlavnaForma;
import forme.modeli.ModelTabeleRacuna;
import forme.modeli.ModelTabeleStavkeRacuna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import koordinator.Koordinator;

/**
 *
 * @author ilija
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
    }

    private void addActionListeners() {
           gf.dodajStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                    dodaj(e);
            }
            private void dodaj(ActionEvent e) {
               DrustvenaIgra i = (DrustvenaIgra) gf.getCmbDrustveneIgre().getSelectedItem();
               String kolicinaTekst = gf.getTxtKolicina().getText().trim();
               int kolicina = 1;

               if (!kolicinaTekst.isEmpty()) {
                   try {
                       kolicina = Integer.parseInt(kolicinaTekst);
                   } catch (NumberFormatException ex) {
                       JOptionPane.showMessageDialog(gf, "Kolicina mora biti broj.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                       return;
                   }
               } else {
                   gf.getTxtKolicina().setText("1");
               }
               double cena = i.getCena();
                StavkaRacuna s = new StavkaRacuna();
                s.setDrustvenaIgra(i);
                s.setCena(cena);
                s.setKolicina(kolicina);
                ModelTabeleStavkeRacuna mts = (ModelTabeleStavkeRacuna) gf.getTblRacun().getModel();
                mts.dodajStavku(s); 
                osveziUkupanIznos();
            } 

               
        });
           gf.obrisiStavkuActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                    obrisi(e);
            }
            private void obrisi(ActionEvent e) {
                int red = gf.getTblRacun().getSelectedRow();
                if(red==-1){
                
                }else{
                ModelTabeleStavkeRacuna mts = (ModelTabeleStavkeRacuna) gf.getTblRacun().getModel();
                StavkaRacuna s = mts.getLista().get(red);
                mts.obrisiStavku(s); 
                osveziUkupanIznos();
                }
                
           } 
        });
        gf.dodajRacunActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                try {
                    dodajRacun(e);
                } catch (Exception ex) {
                    Logger.getLogger(GlavnaFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            private void dodajRacun(ActionEvent e) throws Exception {
                try{
               Racun r = new Racun();
             
               String datumString = gf.getTxtDatum().getText();
               SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
               Date datum = sdf.parse(datumString);
               
               r.setDatum(datum);
               r.setZaposleni(Koordinator.getInstance().getUlogovani());
               r.setKlijent((Klijent) gf.getCmbKlijent().getSelectedItem());
               
               ModelTabeleStavkeRacuna mts = (ModelTabeleStavkeRacuna) gf.getTblRacun().getModel();
               List<StavkaRacuna> stavke = mts.getLista();
               r.setStavke(stavke);
               
               Komunikacija.getInstance().dodajRacun(r);
               JOptionPane.showMessageDialog(null, "Sistem je zapamtio racun.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                ocistiPoljaNakonDodavanja();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti racun.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
                
            } 

        });
        gf.azuriranjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                try {
                   azurirajRacun(e);
                } catch (Exception ex) {
                    Logger.getLogger(GlavnaFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            private void azurirajRacun(ActionEvent e) throws Exception {
                try{
               Racun r = new Racun();
               int id = Integer.parseInt(gf.getTxtID().getText());
               r.setIdRacun(id);
               String datumString = gf.getTxtDatum().getText();
               SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
               Date datum = sdf.parse(datumString);
               
               r.setDatum(datum);
               r.setZaposleni(Koordinator.getInstance().getUlogovani());
               r.setKlijent((Klijent) gf.getCmbKlijent().getSelectedItem());
               
               ModelTabeleStavkeRacuna mts = (ModelTabeleStavkeRacuna) gf.getTblRacun().getModel();
               List<StavkaRacuna> stavke = mts.getLista();
               r.setStavke(stavke);
               
               Komunikacija.getInstance().izmeniRacun(r);
               JOptionPane.showMessageDialog(null, "Sistem je zapamtio racun", "USPEH", JOptionPane.INFORMATION_MESSAGE);
               Koordinator.getInstance().osveziPrikazRacuna();
               gf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Sistem ne moze da zapamti racun", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
                
            } 
        });
        
        poveziDugmeIzmeniStavkuAkoPostoji();
    }

    public void otvoriFormu() {
        gf.getBtnKreirajRacun().setVisible(true);
        gf.getBtnIzmeniRacun().setVisible(false);
        gf.prikaziMenuBar();
        gf.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        Zaposleni ulogovani = Koordinator.getInstance().getUlogovani();
        String imePrezime = ulogovani.getIme()+" "+ulogovani.getPrezime();
        gf.setVisible(true);
        gf.getLblUlogovani().setText(imePrezime);
        
        List<StavkaRacuna> praznaLista = new ArrayList<>();
        postaviModelStavki(praznaLista, false);
        
        postaviPocetniUkupanIznos();
        postaviAutomatskiId();
        popuniComboBoxeve();
        postaviUlogovanogZaposlenog();
    }

    private void popuniComboBoxeve() {
        List<Zaposleni> sviZaposleni = Komunikacija.getInstance().ucitajZaposlene();
        List<Klijent> sviKlijenti = Komunikacija.getInstance().ucitajKlijente();
        List<DrustvenaIgra> sveDrustveneIgre = Komunikacija.getInstance().ucitajDrustveneIgre();
        
        gf.getCmbZaposleni().removeAllItems();
        for(Zaposleni z: sviZaposleni){
            gf.getCmbZaposleni().addItem(z);
        }
        
         gf.getCmbKlijent().removeAllItems();
        for(Klijent k: sviKlijenti){
            gf.getCmbKlijent().addItem(k);
        }
        
         gf.getCmbDrustveneIgre().removeAllItems();
        for(DrustvenaIgra i: sveDrustveneIgre){
            gf.getCmbDrustveneIgre().addItem(i);
        }
    }

    public void otvoriFormu(FormaMod formaMod) {
       popuniComboBoxeve();
       postaviUlogovanogZaposlenog();
       Zaposleni ulogovani = Koordinator.getInstance().getUlogovani();
        String imePrezime = ulogovani.getIme()+" "+ulogovani.getPrezime();
        gf.setVisible(true);
        gf.getLblUlogovani().setText(imePrezime);
        
        List<StavkaRacuna> praznaLista = new ArrayList<>();
        postaviModelStavki(praznaLista, false);
        
        if(formaMod==FormaMod.IZMENI){
            gf.getBtnKreirajRacun().setVisible(false);
            gf.getBtnIzmeniRacun().setVisible(true);
            gf.sakrijMenuBar();
            gf.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            Racun r = (Racun) Koordinator.getInstance().vratiParam("razun_za_izmenu");
            postaviModelStavki(r.getStavke(), true);
            gf.getTxtID().setText(r.getIdRacun()+"");
            gf.getTxtID().setEnabled(false);
            gf.getCmbKlijent().setSelectedItem(r.getKlijent());
            
            SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
            String datumString = formater.format(r.getDatum());
            gf.getTxtDatum().setText(datumString);
            
            osveziUkupanIznos();
        }
    }
     private void ocistiPoljaNakonDodavanja() {
        gf.getTxtID().setText("");
        gf.getTxtDatum().setText("");
        gf.getTxtKolicina().setText("");

        if (gf.getCmbKlijent().getItemCount() > 0) {
            gf.getCmbKlijent().setSelectedIndex(0);
        }
        if (gf.getCmbDrustveneIgre().getItemCount() > 0) {
            gf.getCmbDrustveneIgre().setSelectedIndex(0);
        }

        postaviModelStavki(new ArrayList<>(), false);
        postaviPocetniUkupanIznos();
        postaviAutomatskiId();
    }

    private void postaviAutomatskiId() {
        gf.getTxtID().setEnabled(false);
        gf.getTxtID().setText("A.I.");
    }

    private void postaviUlogovanogZaposlenog() {
    Zaposleni ulogovani = Koordinator.getInstance().getUlogovani();
    gf.getCmbZaposleni().setSelectedItem(ulogovani);
    gf.getCmbZaposleni().setEnabled(false);
}
     private void osveziUkupanIznos() {
        ModelTabeleStavkeRacuna mts = (ModelTabeleStavkeRacuna) gf.getTblRacun().getModel();
        double ukupanIznos = 0;
        for (StavkaRacuna stavka : mts.getLista()) {
            ukupanIznos += stavka.izracunajIznos();
        }
        postaviUkupanIznos(ukupanIznos);
    }

    private void postaviPocetniUkupanIznos() {
        postaviUkupanIznos(0);
        JTextField txtUkupno = gf.getTxtUkupno();
        if (txtUkupno != null) {
            txtUkupno.setEnabled(false);
        }
    }

    private void postaviUkupanIznos(double iznos) {
        JTextField txtUkupno = gf.getTxtUkupno();
        if (txtUkupno != null) {
            txtUkupno.setText(String.format("%.2f", iznos));
        }
    }

    private void poveziDugmeIzmeniStavkuAkoPostoji() {
        JButton btnIzmeniStavku = gf.getBtnIzmeniStavku();
        if (btnIzmeniStavku == null) {
            return;
        }

        for (ActionListener actionListener : btnIzmeniStavku.getActionListeners()) {
            btnIzmeniStavku.removeActionListener(actionListener);
        }

        btnIzmeniStavku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajIzmenuStavke();
            }

            private void sacuvajIzmenuStavke() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }

    private void postaviModelStavki(List<StavkaRacuna> stavke, boolean editable) {
        ModelTabeleStavkeRacuna mts = new ModelTabeleStavkeRacuna(stavke);
        mts.setEditable(editable);
        mts.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                osveziUkupanIznos();
            }
        });
        gf.getTblRacun().setModel(mts);
        osveziUkupanIznos();
    }
    private void sacuvajIzmenuStavke() {
        int red = gf.getTblRacun().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(gf, "Izaberite stavku koju zelite da izmenite.", "GRESKA", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ModelTabeleStavkeRacuna mts = (ModelTabeleStavkeRacuna) gf.getTblRacun().getModel();
        StavkaRacuna stavka = mts.getLista().get(red);

        try {
            Komunikacija.getInstance().azurirajStavku(stavka);
            JOptionPane.showMessageDialog(gf, "Sistem je azurirao stavku racuna.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
            osveziUkupanIznos();
            Koordinator.getInstance().osveziPrikazRacuna();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(gf, "Sistem ne moze da azurira stavku racuna.", "GRESKA", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
