 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import communication.Komunikacija;
import domen.Racun;
import domen.StavkaRacuna;
import forme.PrikazRacunaForma;
import forme.modeli.ModelTabeleRacuna;
import forme.modeli.ModelTabeleStavkeRacuna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
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
        List<Racun> iznajmljivanja = Komunikacija.getInstance().ucitajRacune();
        ModelTabeleRacuna mti= new ModelTabeleRacuna(iznajmljivanja);
        pi.getTblRacuni().setModel(mti);
        
        List<StavkaRacuna> stavke = new ArrayList<>();
        ModelTabeleStavkeRacuna mtsi = new ModelTabeleStavkeRacuna(stavke);
        pi.getTblStavke().setModel(mtsi);
    }

    private void addActionListener() {
        //obirisiAddActionListener
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
                int red = pi.getTblRacuni().getSelectedRow();
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
                int red = pi.getTblRacuni().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(pi, "Sistem ne moze da izmeni stavku.", "Greska", JOptionPane.ERROR_MESSAGE);
                }else{
                   ModelTabeleStavkeRacuna mtsr = (ModelTabeleStavkeRacuna) pi.getTblStavke().getModel();
                   StavkaRacuna sr = mtsr.getLista().get(red);
                   Koordinator.getInstance().otvoriIzmeniStavku();
                   Koordinator.getInstance().dodajParam("stavka_za_izmenu", sr);
                }
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
}
