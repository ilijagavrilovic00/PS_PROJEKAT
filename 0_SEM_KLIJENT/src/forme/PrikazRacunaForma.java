package forme;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class PrikazRacunaForma extends JFrame {

    private JButton btnAzuriraj;
    private JButton btnAzurirajStavku;
    private JButton btnObrisi;
    private JButton btnObrisiStavku;
    private JButton btnPretrazi;
    private JTable tblRacuni;
    private JTable tblStavke;
    private JTextField txtIdRacuna;
    private JComboBox<Object> cmbZaposleni;
    private JComboBox<Object> cmbKlijenti;
    private JComboBox<Object> cmbIgre;

    public PrikazRacunaForma() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pregled racuna");

        btnObrisi = new JButton("Obrisi");
        btnAzuriraj = new JButton("Azuriraj");
        btnObrisiStavku = new JButton("Obrisi stavku");
        btnAzurirajStavku = new JButton("Azuriraj");
        btnPretrazi = new JButton("Pretrazi");

        txtIdRacuna = new JTextField(7);
        cmbZaposleni = new JComboBox<>();
        cmbKlijenti = new JComboBox<>();
        cmbIgre = new JComboBox<>();

        tblRacuni = new JTable();
        tblStavke = new JTable();

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filterPanel.add(new JLabel("ID racuna:"));
        filterPanel.add(txtIdRacuna);
        filterPanel.add(new JLabel("Zaposleni:"));
        filterPanel.add(cmbZaposleni);
        filterPanel.add(new JLabel("Klijent:"));
        filterPanel.add(cmbKlijenti);
        filterPanel.add(new JLabel("Drustvena igra:"));
        filterPanel.add(cmbIgre);
        filterPanel.add(btnPretrazi);

        JPanel racuniPanel = new JPanel(new BorderLayout());
        JPanel racuniButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        racuniButtonsPanel.add(btnAzuriraj);
        racuniButtonsPanel.add(btnObrisi);
        racuniPanel.add(racuniButtonsPanel, BorderLayout.NORTH);
        racuniPanel.add(new JScrollPane(tblRacuni), BorderLayout.CENTER);

        JPanel stavkePanel = new JPanel(new BorderLayout());
        JPanel stavkeButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        stavkeButtonsPanel.add(btnObrisiStavku);
        stavkeButtonsPanel.add(btnAzurirajStavku);
        stavkePanel.add(stavkeButtonsPanel, BorderLayout.NORTH);
        stavkePanel.add(new JScrollPane(tblStavke), BorderLayout.CENTER);

        JPanel centar = new JPanel(new java.awt.GridLayout(1, 2, 10, 0));
        centar.add(racuniPanel);
        centar.add(stavkePanel);

        setLayout(new BorderLayout(8, 8));
        add(filterPanel, BorderLayout.NORTH);
        add(centar, BorderLayout.CENTER);

        setSize(1050, 450);
        setLocationRelativeTo(null);
    }

    public JTable getTblRacuni() {
        return tblRacuni;
    }

    public void setTblRacuni(JTable tblRacuni) {
        this.tblRacuni = tblRacuni;
    }

    public JTable getTblStavke() {
        return tblStavke;
    }

    public void setTblStavke(JTable tblStavke) {
        this.tblStavke = tblStavke;
    }

    public JButton getBtnObrisi() {
        return btnObrisi;
    }

    public void setBtnObrisi(JButton btnObrisi) {
        this.btnObrisi = btnObrisi;
    }

    public JButton getBtnAzuriraj() {
        return btnAzuriraj;
    }

    public void setBtnAzuriraj(JButton btnAzuriraj) {
        this.btnAzuriraj = btnAzuriraj;
    }

    public JButton getBtnAzurirajStavku() {
        return btnAzurirajStavku;
    }

    public void setBtnAzurirajStavku(JButton btnAzurirajStavku) {
        this.btnAzurirajStavku = btnAzurirajStavku;
    }

    public JButton getBtnObrisiStavku() {
        return btnObrisiStavku;
    }

    public void setBtnObrisiStavku(JButton btnObrisiStavku) {
        this.btnObrisiStavku = btnObrisiStavku;
    }

    public JButton getBtnPretrazi() {
        return btnPretrazi;
    }

    public JTextField getTxtIdRacuna() {
        return txtIdRacuna;
    }

    public JComboBox<Object> getCmbZaposleni() {
        return cmbZaposleni;
    }

    public JComboBox<Object> getCmbKlijenti() {
        return cmbKlijenti;
    }

    public JComboBox<Object> getCmbIgre() {
        return cmbIgre;
    }

    public void obrisiAddActionListener(ActionListener actionListener) {
        btnObrisi.addActionListener(actionListener);
    }

    public void azuriranjeAddActionListener(ActionListener actionListener) {
        btnAzuriraj.addActionListener(actionListener);
    }

    public void obrisiStavkuAddActionListener(ActionListener actionListener) {
        btnObrisiStavku.addActionListener(actionListener);
    }

    public void izmeniStavkuAddActionListener(ActionListener actionListener) {
        btnAzurirajStavku.addActionListener(actionListener);
    }

    public void pretraziAddActionListener(ActionListener actionListener) {
        btnPretrazi.addActionListener(actionListener);
    }
}
