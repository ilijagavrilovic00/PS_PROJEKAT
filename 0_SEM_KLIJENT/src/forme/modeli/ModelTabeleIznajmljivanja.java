/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.Iznajmljivanje;
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
public class ModelTabeleIznajmljivanja extends AbstractTableModel {
    List<Iznajmljivanje> lista;
    List<Iznajmljivanje> originalnaLista;
    String[] kolone ={"id", "zaposleni", "klijent"};

    public ModelTabeleIznajmljivanja(List<Iznajmljivanje> lista) {
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
        Iznajmljivanje i = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return i.getIdIznajmljivanje();
            case 1: return i.getZaposleni();
            case 2: return i.getKlijent();
            default: return "NA";
        }
    }

    public List<Iznajmljivanje> getLista() {
        return lista;
    }

    public void setLista(List<Iznajmljivanje> lista) {
        this.lista = lista;
    }

    
}
