/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import communication.Komunikacija;
import domen.Klijent;
import domen.Mesto;
import forme.DodajKlijentaForma;
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
public class DodajKlijentaController {
    private final DodajKlijentaForma dkf;
    private List<Mesto> listaMesta = new ArrayList<>();

    public DodajKlijentaController(DodajKlijentaForma dkf) {
        this.dkf = dkf;
        popuniMesta();
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod mod){
        pripremiFormu(mod);
        dkf.setVisible(true);
    }

    private void addActionListener() {
            dkf.dodavanjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    kreirajKlijenta(e);
                } catch (Exception ex) {
                    Logger.getLogger(DodajKlijentaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            private void kreirajKlijenta(ActionEvent e) {
               String ime = dkf.getTxtIme().getText().trim();
               String prezime = dkf.getTxtPrezime().getText().trim();
               String brojTelefona = dkf.getTxtBrojTelefona().getText().trim();
               Mesto mesto = (Mesto)dkf.getCmbMesto().getSelectedItem();
               Klijent k = new Klijent(-1,ime,prezime,brojTelefona, mesto);
                
               if (!validanBrojTelefonaZaCuvanje(brojTelefona)) {
                   JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti klijenta.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               try{
                   Komunikacija.getInstance().dodajKlijenta(k); 
                   JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio klijenta.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                   ocistiPoljaNakonDodavanja();
               }catch(Exception ex){
                   JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti klijenta.", "GRESKA", JOptionPane.ERROR_MESSAGE);
               }
            }
              
        });
             dkf.azuriranjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    azurirajKlijenta(e);
                } catch (Exception ex) {
                    Logger.getLogger(DodajKlijentaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            private void azurirajKlijenta(ActionEvent e) {
               int id = Integer.parseInt(dkf.getTxtID().getText());
               String ime = dkf.getTxtIme().getText().trim();
               String prezime = dkf.getTxtPrezime().getText().trim();
               String brojTelefona = dkf.getTxtBrojTelefona().getText().trim();
               Mesto mesto = (Mesto)dkf.getCmbMesto().getSelectedItem();
               Klijent k = new Klijent(id,ime,prezime,brojTelefona, mesto);
                
               if (!validanBrojTelefonaZaCuvanje(brojTelefona)) {
                   JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti klijenta.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                   return;
               }
               try{
                   Komunikacija.getInstance().azurirajKlijenta(k); 
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio klijenta.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().osveziFormu();
                    dkf.dispose();
               }catch(Exception ex){
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti klijenta.", "GRESKA", JOptionPane.ERROR_MESSAGE);
               }
            }
             });
    
    
    }

    private void popuniMesta() {
        JComboBox<Mesto> cmb = dkf.getCmbMesto();
        listaMesta = Komunikacija.getInstance().ucitajMesta();
        cmb.addItem(null);
        for(Mesto m: listaMesta){
            cmb.addItem(m);
        }
    }

    private void pripremiFormu(FormaMod mod) {
        switch(mod){
            case DODAJ:
                dkf.getLblOperacija().setText("Dodaj klijenta");
                dkf.getTxtID().setEnabled(false);
                dkf.getTxtID().setText("A.I.");
                dkf.getBtnAzuriraj().setVisible(false);
                dkf.getBtnDodaj().setVisible(true);
                dkf.getBtnDodaj().setEnabled(true);
                break;
            case IZMENI:
                dkf.getTxtID().setEnabled(false);
                dkf.getLblOperacija().setText("Izmeni klijenta");
                dkf.getBtnAzuriraj().setVisible(true);
                dkf.getBtnDodaj().setVisible(false);
                dkf.getBtnAzuriraj().setEnabled(true);
                
                Klijent k = (Klijent) Koordinator.getInstance().vratiParam("klijent");
                if (k == null) {
                    throw new IllegalStateException("Nije prosledjen klijent za izmenu.");
                }
                dkf.getTxtIme().setText(k.getIme());
                dkf.getTxtPrezime().setText(k.getPrezime());        
                dkf.getTxtBrojTelefona().setText(k.getBrojTelefona());
                dkf.getCmbMesto().setSelectedItem(k.getMesto());
                dkf.getTxtID().setText(k.getIdKlijent()+"");
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private boolean validanBrojTelefonaZaCuvanje(String brojTelefona) {
        return brojTelefona != null && brojTelefona.matches("06\\d{8}");
    }

    private void ocistiPoljaNakonDodavanja() {
        dkf.getTxtIme().setText("");
        dkf.getTxtPrezime().setText("");
        dkf.getTxtBrojTelefona().setText("");

        if (dkf.getCmbMesto().getItemCount() > 0) {
            dkf.getCmbMesto().setSelectedIndex(0);
        }
    }
}
