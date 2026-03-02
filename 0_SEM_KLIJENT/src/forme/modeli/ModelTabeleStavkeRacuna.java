/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.modeli;

import domen.StavkaRacuna;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ilija
 */
public class ModelTabeleStavkeRacuna extends AbstractTableModel {
    List<StavkaRacuna> lista;
    List<StavkaRacuna> originalnaLista;
    String[] kolone ={"Rb", "Naziv", "Cena", "Kolicina", "Iznos"};
    private boolean editable;
    public ModelTabeleStavkeRacuna(List<StavkaRacuna> lista) {
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return editable && columnIndex == 3;
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (!isCellEditable(rowIndex, columnIndex)) {
            return;
        }

        StavkaRacuna sr = lista.get(rowIndex);
        if (columnIndex == 3) {
            try {
                int novaKolicina = Integer.parseInt(String.valueOf(aValue));
                sr.setKolicina(novaKolicina);
            } catch (NumberFormatException ex) {
                return;
            }
        }

        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        fireTableDataChanged();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRacuna sr = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return sr.getRb();
            case 1: return sr.getDrustvenaIgra().getNaziv();
            case 2: return sr.getCena();   
            case 3: return sr.getKolicina();
            case 4: return sr.izracunajIznos();
            default: return "NA";
        }
    }

    public List<StavkaRacuna> getLista() {
        return lista;
    }

    public void setLista(List<StavkaRacuna> lista) {
        this.lista = lista;
    }

    public void dodajStavku(StavkaRacuna s) {
        int trenutniRB = lista.size();
        s.setRb(trenutniRB+1);
        lista.add(s);
        fireTableDataChanged();
    }

    public void obrisiStavku(StavkaRacuna s) {
        lista.remove(s);
        fireTableDataChanged();
    }
}
