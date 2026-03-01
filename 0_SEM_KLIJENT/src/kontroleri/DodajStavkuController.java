 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import communication.Komunikacija;
import domen.DrustvenaIgra;
import domen.Klijent;
import domen.Mesto;
import domen.Racun;
import domen.StavkaRacuna;
import forme.DodajKlijentaForma;
import forme.DodajStavkuForma;
import forme.FormaMod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import koordinator.Koordinator;

/**
 *
 * @author ilija
 */
public class DodajStavkuController {
    private final DodajStavkuForma dsf;

    public DodajStavkuController(DodajStavkuForma dsf) {
        this.dsf = dsf;
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod mod){
        pripremiFormu(mod);
        dsf.setVisible(true);
    }

    private void addActionListener() {
         
            dsf.azuriranjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    azuriraj(e);
                } catch (Exception ex) {
                    Logger.getLogger(DodajKlijentaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            private void azuriraj(ActionEvent e) {
               int id = Integer.parseInt(dsf.getTxtRacunId().getText());
               int rb = Integer.parseInt(dsf.getTxtRB().getText());
               int kolicina = Integer.parseInt(dsf.getTxtKolicina().getText());
               double cena = Double.parseDouble(dsf.getTxtCena().getText());
               DrustvenaIgra d = (DrustvenaIgra) dsf.getCmbDrustveneIgre().getSelectedItem();
               
               Racun r = new Racun();
               r.setIdRacun(id);
               StavkaRacuna sr = new StavkaRacuna();
               sr.setRacun(r);
               sr.setRb(rb);
               sr.setCena(cena);
               sr.setKolicina(kolicina);
               sr.setDrustvenaIgra(d);
               try{
                   Komunikacija.getInstance().azurirajStavku(sr); 
                   JOptionPane.showMessageDialog(dsf, "USPEH", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                   dsf.dispose();
               }catch(Exception ex){
                      JOptionPane.showMessageDialog(dsf, "GRESKA", "GRESKA", JOptionPane.ERROR_MESSAGE);
               }
            }
             });
    
    
    }


    private void pripremiFormu(FormaMod mod) {
        List<DrustvenaIgra> sveDrustveneIgre = Komunikacija.getInstance().ucitajDrustveneIgre();
        dsf.getCmbDrustveneIgre().removeAllItems();
        for(DrustvenaIgra i: sveDrustveneIgre){
            dsf.getCmbDrustveneIgre().addItem(i);
        }
        
        switch(mod){
            case DODAJ:
                dsf.getTxtRacunId().setEnabled(false);
                dsf.getTxtRB().setEnabled(false);
                dsf.getBtnIzmeni().setVisible(false);
                dsf.getBtnKreiraj().setVisible(true);
                dsf.getBtnKreiraj().setEnabled(true);
                break;
            case IZMENI:
                dsf.getBtnIzmeni().setVisible(true);
                dsf.getBtnKreiraj().setVisible(false);
                dsf.getBtnIzmeni().setEnabled(true);
                
                StavkaRacuna sr = (StavkaRacuna) Koordinator.getInstance().vratiParam("stavka_za_izmenu");
                dsf.getTxtRacunId().setText(sr.getRacun().getIdRacun()+"");
                dsf.getTxtRB().setText(sr.getRb()+"");        
                dsf.getTxtKolicina().setText(sr.getKolicina()+"");
                dsf.getCmbDrustveneIgre().setSelectedItem(sr.getDrustvenaIgra());
                
                double cena = sr.getKolicina()*sr.getDrustvenaIgra().getCena();
                dsf.getTxtCena().setText(cena+"");
                break;
            default:
                throw new AssertionError();
        }
    }
}
 