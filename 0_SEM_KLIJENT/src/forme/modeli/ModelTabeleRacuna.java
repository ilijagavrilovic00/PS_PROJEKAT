/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Racun;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ilija
 */
public class ModelTabeleRacuna extends AbstractTableModel {
    List<Racun> lista;
    List<Racun> originalnaLista;
    String[] kolone ={"id", "datum", "klijent", "ukupan iznos"};

    public ModelTabeleRacuna(List<Racun> lista) {
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
        Racun i = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return i.getIdRacun();
            case 1: return i.getDatum();
            case 2: return i.getKlijent();
            case 3: return i.getUkupanIznos();
            default: return "NA";
        }
    }

    public List<Racun> getLista() {
        return lista;
    }

    public void setLista(List<Racun> lista) {
        this.lista = lista;
    }

    
}
