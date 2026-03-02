/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijenti;

import domen.Klijent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import operacije.ApstraktnaGenerickaOperacija;
import repository.db.DBConnectionFactory;

/**
 *
 * @author ilija
 */
public class ObrisiKlijentaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Klijent)){
            throw new Exception("Sistem nije mogao da obrise klijenta");
        }

        Klijent klijent = (Klijent) objekat;
        String upit = "SELECT COUNT(*) FROM racun WHERE idKlijent = ?";

        try (PreparedStatement ps = DBConnectionFactory.getInstance().getConnection().prepareStatement(upit)) {
            ps.setLong(1, klijent.getIdKlijent());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new Exception("Sistem ne moze da obrise klijenta koji ima racune.");
                }
            }
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((Klijent)objekat);
    }
    
}
