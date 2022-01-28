package com.contab.view;

import com.contab.bean.BeanEmpresa;
import com.contab.bean.BeanMes;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.combobox.ComboModelMes;


import com.contab.logic.LogicEmpresa;
import com.contab.logic.LogicVenta;
import com.contab.util.JComboBoxAutocomplete;

import com.contab.util.JFileChooserModel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelVenta extends JPanel {

    private JLabel lblEmpresa = new JLabel("Empresa");
    private ComboModelEmpresas modelcbempresa = new ComboModelEmpresas();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private JLabel lbPeriodo = new JLabel("periodo");
    private JTextField txtAno = new JTextField();
    private FrmPanel panelRegistroVenta = new FrmPanel(ViewLogin.picture.getVenta(), " REGISTRO DE VENTA");
    private JPanel panelMolde = new JPanel();
    private JFileChooserModel fileChooser = new JFileChooserModel();
    private ComboModelMes modelcbMes = new ComboModelMes();
    private JComboBoxAutocomplete cbuMes = new JComboBoxAutocomplete();
    private JLabel Mes = new JLabel("Mes");
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("docx", "docx");

    public PanelVenta() throws SQLException {
        initComponets();
    }

    private void initComponets() throws SQLException {
        panelRegistroVenta.getNuevo().setIcon(ViewLogin.picture.getWord());
        panelRegistroVenta.getNuevo().setToolTipText("Exportar a Word");
        panelRegistroVenta.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panelRegistroVenta.getActualizar().setToolTipText("Exportar a Excel");
        panelRegistroVenta.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panelRegistroVenta.getEliminar().setToolTipText("Expotar a pdf");
        panelRegistroVenta.getPanelData().setVisible(false);
        panelRegistroVenta.getPanelFormulario().setLayout(new BorderLayout());
        modelcbempresa.setData(LogicEmpresa.listaDeEmpresa());
        cbEmpresa.setModel(modelcbempresa);
        cbuMes.setModel(modelcbMes);
        panelMolde.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lblEmpresa, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(cbEmpresa, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lbPeriodo, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(txtAno, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(Mes, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(cbuMes, gbc);

        panelRegistroVenta.add(panelMolde);
        panelRegistroVenta.getNuevo().addActionListener(actionListener);
        panelRegistroVenta.getActualizar().addActionListener(actionListener);
        panelRegistroVenta.getEliminar().addActionListener(actionListener);
    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == panelRegistroVenta.getActualizar()) {
                WorkerBalanceComprobacionExcel xls = new WorkerBalanceComprobacionExcel();
                xls.execute();
            } else if (e.getSource() == panelRegistroVenta.getEliminar()) {
                WorkerBalanceComprobacionPDF pdf = new WorkerBalanceComprobacionPDF();
                pdf.execute();
            } else if (e.getSource() == panelRegistroVenta.getNuevo()) {
                WorkerBalanceComprobacionDOC doc = new WorkerBalanceComprobacionDOC();
                doc.execute();
            }
        }
    };

    private File seleccionarArchivo(FileNameExtensionFilter tipoFiltro, String nombreLibro, String tipoExportacion) {
        fileChooser.setFileFilter(tipoFiltro);
        Date hoy = new Date();
        File archivo = new File(nombreLibro + hoy.getTime() + "." + tipoExportacion);
        fileChooser.setSelectedFile(archivo);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            JOptionPane.showMessageDialog(null, "No seleccionó ningún fichero", "Diálogo cerrado o cancelado", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }

    private void exportarVenta1AEXCEL() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterXLS, "Venta", "xlsx");
             BeanMes objMes = ((ComboModelMes) (cbuMes.getModel())).getElement(cbuMes.getSelectedIndex());

            LogicVenta.listaSaldoVenta(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, objMes.getNumero(), archivo, "xlsx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarVenta1APDF() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterPDF, "Venta", "pdf");
             BeanMes objMes = ((ComboModelMes) (cbuMes.getModel())).getElement(cbuMes.getSelectedIndex());

            LogicVenta.listaSaldoVenta(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, objMes.getNumero(), archivo, "pdf");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarVenta1ADOC() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterTXT, "Venta", "docx");
            BeanMes objMes = ((ComboModelMes) (cbuMes.getModel())).getElement(cbuMes.getSelectedIndex());

            LogicVenta.listaSaldoVenta(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, objMes.getNumero(), archivo, "docx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    class WorkerBalanceComprobacionExcel extends SwingWorker<Boolean, Integer> {

        public WorkerBalanceComprobacionExcel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarVenta1AEXCEL();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerBalanceComprobacionPDF extends SwingWorker<Boolean, Integer> {

        public WorkerBalanceComprobacionPDF() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarVenta1APDF();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerBalanceComprobacionDOC extends SwingWorker<Boolean, Integer> {

        public WorkerBalanceComprobacionDOC() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarVenta1ADOC();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    public FrmPanel getRegistroVenta() {
        return panelRegistroVenta;
    }
}
