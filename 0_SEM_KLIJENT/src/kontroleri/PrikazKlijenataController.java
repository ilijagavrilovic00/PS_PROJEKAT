/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import communication.Komunikacija;
import domen.Klijent;
import domen.Mesto;
import forme.PrikazKlijenataForma;
import forme.modeli.ModelTabeleKlijenti;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import koordinator.Koordinator;

/**
 *
 * @author ilija
 */
public class PrikazKlijenataController {
    private final PrikazKlijenataForma pk;

    public PrikazKlijenataController(PrikazKlijenataForma pk) {
        this.pk = pk;
        popuniMesta();
        addActionListener();
    }
    
    
     public void otvoriFormu() {
        pripremiFormu();
        pk.setVisible(true);
    }

    public void pripremiFormu() {
        List<Klijent> klijenti = Komunikacija.getInstance().ucitajKlijente();
        ModelTabeleKlijenti mtk = new ModelTabeleKlijenti(klijenti);
        pk.getTblKlijenti().setModel(mtk);
    }

    private void addActionListener() {
        pk.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pk.getTblKlijenti().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pk, "Sistem ne moze da obrise klijenta.", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKlijenti mtk = (ModelTabeleKlijenti) pk.getTblKlijenti().getModel();
                    Klijent k = mtk.getLista().get(red);
                    try{
                        Komunikacija.getInstance().obrisiKlijenta(k);
                        JOptionPane.showMessageDialog(pk, "Sistem je obrisao klijenta", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(pk, "Sistem ne moze da obrise klijenta", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        pk.addBtnAzurirajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pk.getTblKlijenti().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pk, "Sistem ne moze da ndaje klijenta.", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleKlijenti mtk = (ModelTabeleKlijenti) pk.getTblKlijenti().getModel();
                    Klijent k = mtk.getLista().get(red);
                    JOptionPane.showMessageDialog(pk, "Sistem je nasao klijenta.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().dodajParam("klijent", k);
                    Koordinator.getInstance().otvoriIzmeniKlijenta();
                }
            }
        });
        pk.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String ime = pk.getTxtIme().getText().trim();
               String prezime = pk.getTxtPrezime().getText().trim();
               Mesto mesto = (Mesto)pk.getCmbMesto().getSelectedItem();
               
               ModelTabeleKlijenti mtk = (ModelTabeleKlijenti) pk.getTblKlijenti().getModel();
               mtk.pretrazi(ime, prezime, mesto);
               if(mtk.getLista().isEmpty()){
                   JOptionPane.showMessageDialog(pk, "Sistem ne moze da nadje klijente po zadatim kriterijumima.", "GRESKA", JOptionPane.ERROR_MESSAGE);
               }else{
                   JOptionPane.showMessageDialog(pk, "Sistem je nasao klijente po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
               }
            }
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }

    private void popuniMesta() {
        JComboBox<Mesto> cmb = pk.getCmbMesto();
        List<Mesto >listaMesta = Komunikacija.getInstance().ucitajMesta();
        cmb.addItem(null);
        for(Mesto m: listaMesta){
            cmb.addItem(m);
        }
    }
}
