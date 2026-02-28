/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.StavkaIznajmljivanja;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ilija
 */
public class ModelTabeleStavkeIznajmljivanja extends AbstractTableModel {
    List<StavkaIznajmljivanja> lista;
    List<StavkaIznajmljivanja> originalnaLista;
    String[] kolone ={"rb", "kolicina", "cena", "drustvena igra"};

    public ModelTabeleStavkeIznajmljivanja(List<StavkaIznajmljivanja> lista) {
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
        StavkaIznajmljivanja si = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return si.getRb();
            case 1: return si.getKolicina();
            case 2: return si.getCena();
            case 4: return si.getDrustvenaIgra().getNaziv();
            default: return "NA";
        }
    }

    public List<StavkaIznajmljivanja> getLista() {
        return lista;
    }

    public void setLista(List<StavkaIznajmljivanja> lista) {
        this.lista = lista;
    }
}
