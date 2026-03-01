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
import javax.swing.JOptionPane;
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
               int kolicina = Integer.parseInt(gf.getTxtKolicina().getText());
               double cena = kolicina * (i.getCena());
                StavkaRacuna s = new StavkaRacuna();
                s.setDrustvenaIgra(i);
                s.setCena(cena);
                s.setKolicina(kolicina);
                ModelTabeleStavkeRacuna mts = (ModelTabeleStavkeRacuna) gf.getTblRacun().getModel();
                mts.dodajStavku(s); 
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
            
                }
                
           } 
        });
        gf.dodajRacunActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                try {
                    dodaj(e);
                } catch (Exception ex) {
                    Logger.getLogger(GlavnaFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            private void dodaj(ActionEvent e) throws Exception {
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
               JOptionPane.showMessageDialog(null, "Sistem je kreirao racun", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Sistem ne moze da kreira racun", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
                
            } 
        });
        gf.azuriranjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                try {
                   azuriraj(e);
                } catch (Exception ex) {
                    Logger.getLogger(GlavnaFormaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            private void azuriraj(ActionEvent e) throws Exception {
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
               JOptionPane.showMessageDialog(null, "Sistem je kreirao racun", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Sistem ne moze da kreira racun", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
                
            } 
        });
    }

    public void otvoriFormu() {
        gf.getBtnIzmeniRacun().setVisible(false);
        Zaposleni ulogovani = Koordinator.getInstance().getUlogovani();
        String imePrezime = ulogovani.getIme()+" "+ulogovani.getPrezime();
        gf.setVisible(true);
        gf.getLblUlogovani().setText(imePrezime);
        
        List<StavkaRacuna> praznaLista = new ArrayList<>();
        ModelTabeleStavkeRacuna mts = new ModelTabeleStavkeRacuna(praznaLista);
        gf.getTblRacun().setModel(mts);
        
        
        popuniComboBoxeve();
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
       Zaposleni ulogovani = Koordinator.getInstance().getUlogovani();
        String imePrezime = ulogovani.getIme()+" "+ulogovani.getPrezime();
        gf.setVisible(true);
        gf.getLblUlogovani().setText(imePrezime);
        
        List<StavkaRacuna> praznaLista = new ArrayList<>();
        ModelTabeleStavkeRacuna mts = new ModelTabeleStavkeRacuna(praznaLista);
        gf.getTblRacun().setModel(mts);
        
        if(formaMod==FormaMod.IZMENI){
            gf.getBtnKreirajRacun().setVisible(false);
            Racun r = (Racun) Koordinator.getInstance().vratiParam("razun_za_izmenu");
            mts.setLista(r.getStavke());
            gf.getTxtID().setText(r.getIdRacun()+"");
            gf.getTxtID().setEnabled(false);
            gf.getCmbZaposleni().setSelectedItem(r.getZaposleni());
            gf.getCmbKlijent().setSelectedItem(r.getKlijent());
            
            SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
            String datumString = formater.format(r.getDatum());
            gf.getTxtDatum().setText(datumString);
            
        }
    }
    
    
}
