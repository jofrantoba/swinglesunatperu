package com.contab.view;

import com.contab.bean.BeanEmpresa;
import com.contab.combobox.ComboModelEmpresas;


import com.contab.logic.LogicEmpresa;
import com.contab.logic.LogicMercaderia;
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

public class PanelMercaderiaCuenta21 extends JPanel {

    private JLabel lblEmpresa = new JLabel("Empresa");
    private ComboModelEmpresas modelcbempresa = new ComboModelEmpresas();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private JLabel lbPeriodo = new JLabel("periodo");
    private JTextField txtAno = new JTextField();
    private FrmPanel panelMercaderiaCuenta21 = new FrmPanel(ViewLogin.picture.getIconCuenta14(), " MERCADERÍAS Y LA CUENTA 20");
    private JPanel panelMolde = new JPanel();
    private JFileChooserModel fileChooser = new JFileChooserModel();
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
   FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("docx", "docx");

    public PanelMercaderiaCuenta21() throws SQLException {
        initComponets();
    }

    private void initComponets() throws SQLException {
       panelMercaderiaCuenta21.getNuevo().setIcon(ViewLogin.picture.getWord());
        panelMercaderiaCuenta21.getNuevo().setToolTipText("Exportar a Word");
        panelMercaderiaCuenta21.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panelMercaderiaCuenta21.getActualizar().setToolTipText("Exportar a Excel");
        panelMercaderiaCuenta21.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panelMercaderiaCuenta21.getEliminar().setToolTipText("Expotar a pdf");
        panelMercaderiaCuenta21.getPanelData().setVisible(false);
        panelMercaderiaCuenta21.getPanelFormulario().setLayout(new BorderLayout());
        modelcbempresa.setData(LogicEmpresa.listaDeEmpresa());
        cbEmpresa.setModel(modelcbempresa);
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

        panelMercaderiaCuenta21.add(panelMolde);
         panelMercaderiaCuenta21.getNuevo().addActionListener(actionListener);
        panelMercaderiaCuenta21.getActualizar().addActionListener(actionListener);
        panelMercaderiaCuenta21.getEliminar().addActionListener(actionListener);
    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == panelMercaderiaCuenta21.getActualizar()) {
                WorkerBalanceComprobacionExcel xls = new WorkerBalanceComprobacionExcel();
                xls.execute();
            } else if (e.getSource() == panelMercaderiaCuenta21.getEliminar()) {
                WorkerBalanceComprobacionPDF pdf = new WorkerBalanceComprobacionPDF();
                pdf.execute();
            }
            else if (e.getSource() == panelMercaderiaCuenta21.getNuevo()) {
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

    private void exportarMercaderiaCuenta21AEXCEL() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterXLS, "MercaderiaCuenta21", "xlsx");
            String tipo = "Ejercicio";
            String metodo = "Metodo";

            LogicMercaderia.listaMercaderia(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, tipo, metodo, archivo, "xlsx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarMercaderiaCuenta21APDF() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterPDF, "MercaderiaCuenta21", "pdf");
            String tipo = "Ejercicio";
            String metodo = "Metodo";
            LogicMercaderia.listaMercaderia(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, tipo, metodo, archivo, "pdf");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }
       private void exportarMercaderiaCuenta21ADOC() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterTXT, "MercaderiaCuenta21", "docx");
            String tipo = "Ejercicio";
            String metodo = "Metodo";
            LogicMercaderia.listaMercaderia(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, tipo, metodo, archivo, "docx");

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
                exportarMercaderiaCuenta21AEXCEL();
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
                exportarMercaderiaCuenta21APDF();
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
                exportarMercaderiaCuenta21ADOC();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    public FrmPanel getMercaderiaCuenta21() {
        return panelMercaderiaCuenta21;
    }
}
