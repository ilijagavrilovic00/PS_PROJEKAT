/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.LoginForma;
import communication.Komunikacija;
import domen.Zaposleni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import koordinator.Koordinator;

/**
 *
 * @author ilija
 */
public class LoginController {
    private final LoginForma lf;
    
    public LoginController(LoginForma lf){
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    prijava(e);
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            private void prijava(ActionEvent e) throws Exception{
                String username = lf.getTxtUsername().getText().trim();
                String password = String.valueOf(lf.getTxtPass().getPassword());
                
                Komunikacija.getInstance().konekcija();
                Zaposleni ulogovan = Komunikacija.getInstance().login(username, password);
                
                if(ulogovan==null){
                    JOptionPane.showMessageDialog(lf, "Prijava na sistem neuspesna", "GRESKA", JOptionPane.ERROR_MESSAGE);  
                }else{
                    Koordinator.getInstance().setUlogovani(ulogovan);
                    JOptionPane.showMessageDialog(lf, "Prijava na sistem uspesna", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().otvoriGlavnuFormu();
                    lf.dispose();
                }
            }
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
}
