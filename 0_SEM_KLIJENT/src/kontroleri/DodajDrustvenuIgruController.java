/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import communication.Komunikacija;
import domen.DrustvenaIgra;
import forme.DodajDrustvenuIgruForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author ilija
 */
public class DodajDrustvenuIgruController {

    private final DodajDrustvenuIgruForma dodajDrustvenuIgruForma;
    
    public DodajDrustvenuIgruController(DodajDrustvenuIgruForma dodajDrustvenuIgruForma) {
        this.dodajDrustvenuIgruForma = dodajDrustvenuIgruForma;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        dodajDrustvenuIgruForma.setVisible(true);
    }
    private void addActionListener() {
        dodajDrustvenuIgruForma.dodavanjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj();
            }

            private void dodaj() {
                String naziv = dodajDrustvenuIgruForma.getTxtNaziv().getText().trim();
                String cenaUnos = dodajDrustvenuIgruForma.getTxtCena().getText().trim();
                String opis = dodajDrustvenuIgruForma.getTxtAreaOpis().getText().trim();

                if (naziv.isEmpty() || cenaUnos.isEmpty() || opis.isEmpty()) {
                    JOptionPane.showMessageDialog(dodajDrustvenuIgruForma, "Sva polja su obavezna.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double cena;
                try {
                    cena = Double.parseDouble(cenaUnos);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dodajDrustvenuIgruForma, "Cena mora biti broj.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                DrustvenaIgra igra = new DrustvenaIgra(-1, naziv, cena, opis);

                try {
                    Komunikacija.getInstance().dodajDrustvenuIgru(igra);
                    JOptionPane.showMessageDialog(dodajDrustvenuIgruForma, "Sistem je zapamtio drustvenu igru.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    dodajDrustvenuIgruForma.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dodajDrustvenuIgruForma, "Sistem ne moze da zapamti drustvenu igru.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void pripremiFormu() {
        dodajDrustvenuIgruForma.getTxtId().setEnabled(false);
        dodajDrustvenuIgruForma.getTxtId().setText("automatski");
    }


    
}
