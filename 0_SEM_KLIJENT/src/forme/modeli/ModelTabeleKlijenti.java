/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Klijent;
import domen.Mesto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ilija
 */
public class ModelTabeleKlijenti extends AbstractTableModel {

    List<Klijent> lista;
    List<Klijent> originalnaLista;
    String[] kolone ={"id", "ime", "prezime","telefon", "mesto"};

    public ModelTabeleKlijenti(List<Klijent> lista) {
        this.lista = lista;
        this.originalnaLista = new ArrayList<>(lista);
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
       return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return k.getIdKlijent();
            case 1: return k.getIme();
            case 2: return k.getPrezime();
            case 3: return k.getBrojTelefona();
            case 4: return k.getMesto();
            default: return "NA";
        }
    }

    public List<Klijent> getLista() {
        return lista;
    }

    public void pretrazi(String ime, String prezime, Mesto mesto) {
        List<Klijent> filtriranaLista = originalnaLista.stream()
                .filter(k -> (ime==null || ime.isEmpty() || k.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(k -> (prezime==null || prezime.isEmpty() || k.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(k -> (mesto==null || k.getMesto().equals(mesto)))
                .collect(Collectors.toList());
        this.lista = filtriranaLista;
        fireTableDataChanged();         
    }
    
    
    
}
