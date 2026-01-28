/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ilija
 */
public class Konfiguracija {
    private static Konfiguracija instance;
    private Properties properties;
    
    private Konfiguracija(){
        try {
            properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\ilija\\Documents\\NetBeansProjects\\0_SEM_SERVER\\config\\dbconfig.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Konfiguracija getInstance() {
        if(instance==null){
            instance = new Konfiguracija();
        }
        return instance;
    }

    public String getProperty(String key){
        return properties.getProperty(key, "n/a");
    }
    
    public void setProperty(String key, String value){
        properties.setProperty(key, value);
    }
    
    public void sacuvajIzmene(){
        try {
            properties.store(new FileOutputStream("C:\\Users\\ilija\\Documents\\NetBeansProjects\\0_SEM_SERVER\\config\\dbconfig.properties"), null);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
