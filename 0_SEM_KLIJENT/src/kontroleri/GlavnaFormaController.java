/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import communication.Komunikacija;
import domen.DrustvenaIgra;
import domen.Klijent;
import domen.StavkaRacuna;
import domen.Zaposleni;
import forme.GlavnaForma;
import forme.modeli.ModelTabeleStavkeRacuna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
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
    }

    public void otvoriFormu() {
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
    
    
}
