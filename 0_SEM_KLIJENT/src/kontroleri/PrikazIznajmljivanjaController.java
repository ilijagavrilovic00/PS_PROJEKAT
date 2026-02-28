 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import communication.Komunikacija;
import domen.Iznajmljivanje;
import domen.Klijent;
import domen.StavkaIznajmljivanja;
import forme.PrikazIznajmljivanjaForma;
import forme.modeli.ModelTabeleIznajmljivanja;
import forme.modeli.ModelTabeleKlijenti;
import forme.modeli.ModelTabeleStavkeIznajmljivanja;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ilija
 */
public class PrikazIznajmljivanjaController {
    private final PrikazIznajmljivanjaForma pi;

    public PrikazIznajmljivanjaController(PrikazIznajmljivanjaForma pi) {
        this.pi = pi;
        addActionListener();
        addMouseListener();
    }
    
    
     public void otvoriFormu() {
        pripremiFormu();
        pi.setVisible(true);
    }

    public void pripremiFormu() {
        List<Iznajmljivanje> iznajmljivanja = Komunikacija.getInstance().ucitajIznajmljivanja();
        ModelTabeleIznajmljivanja mti= new ModelTabeleIznajmljivanja(iznajmljivanja);
        pi.getTblIznajmljivanja().setModel(mti);
        
        List<StavkaIznajmljivanja> stavke = new ArrayList<>();
        ModelTabeleStavkeIznajmljivanja mtsi = new ModelTabeleStavkeIznajmljivanja(stavke);
        pi.getTblStavke().setModel(mtsi);
    }

    private void addActionListener() {
        //obirisiAddActionListener
        pi.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pi.getTblIznajmljivanja().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pi, "Sistem ne moze da obrise iznajmljivanje.", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                    ModelTabeleIznajmljivanja mti = (ModelTabeleIznajmljivanja) pi.getTblIznajmljivanja().getModel();
                    Iznajmljivanje i = mti.getLista().get(red);
                    List<StavkaIznajmljivanja> stavke = Komunikacija.getInstance().ucitajStavke(i.getIdIznajmljivanje());
                    i.setStavke(stavke);
                    try{
                        Komunikacija.getInstance().obrisiIznajmljivanje(i);
                        JOptionPane.showMessageDialog(pi, "Sistem je obrisao iznajmljivanje", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(pi, "Sistem ne moze da obrise iznajmljivanje", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void addMouseListener() {
        pi.getTblIznajmljivanja().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = pi.getTblIznajmljivanja().getSelectedRow();
                if(red!=-1){
                    ModelTabeleIznajmljivanja mti = (ModelTabeleIznajmljivanja) pi.getTblIznajmljivanja().getModel();
                    Iznajmljivanje i = mti.getLista().get(red);
                    List<StavkaIznajmljivanja> stavke = Komunikacija.getInstance().ucitajStavke(i.getIdIznajmljivanje());
                    //novo
                    i.setStavke(stavke);
                    mti.fireTableRowsUpdated(red, red);
                    //
                    ModelTabeleStavkeIznajmljivanja mtsi = new ModelTabeleStavkeIznajmljivanja(stavke);
                    pi.getTblStavke().setModel(mtsi);
                }
            }
        });
    }
}
