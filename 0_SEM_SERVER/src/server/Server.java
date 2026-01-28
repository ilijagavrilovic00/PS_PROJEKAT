/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.util.List;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author ilija
 */
public class Server extends Thread{
    
    boolean kraj = false;
    ServerSocket serverSoket;
    List<ObradaKlijentskihZahteva> klijenti;
    

    public Server() {
        klijenti = new ArrayList<>();
    }

    @Override
    public void run() { //pokreniServer
        try {
            serverSoket = new ServerSocket(9000);
            while(!kraj){
                Socket s = serverSoket.accept();
                System.out.println("Klijent je povezan!");
                
                //Obrada klijentskih zahteva//
                
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                klijenti.add(okz);
                okz.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    
    //za vise klijenata cuvati listu niti 
    //za svakog klijenta zaseban poziv metode prekini
    
    public void zaustaviServer() throws IOException{
        kraj = true;
        for(ObradaKlijentskihZahteva k: klijenti){
            k.prekini();
        }
        serverSoket.close();
    }
}
