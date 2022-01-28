package com.contab.view;

import com.contab.bean.BeanEmpresa;
import com.contab.bean.BeanImpresion;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.combobox.ComboModelTipo;



import com.contab.logic.LogicEmpresa;
import com.contab.logic.LogicSaldoDeCuenta;

import com.contab.util.JComboBoxAutocomplete;

import com.contab.util.JFileChooserModel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;
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

public class PanelSaldoDeCuenta extends JPanel {

    private JLabel lblEmpresa = new JLabel("Empresa");
    private ComboModelEmpresas modelcbempresa = new ComboModelEmpresas();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private FrmPanel panelSaldoDeCuenta = new FrmPanel(ViewLogin.picture.getIconCuenta14(), "SALDO DE CUENTA 12");
    private JPanel panelMolde = new JPanel();
    private JLabel lblAnno = new JLabel("AÑO");
    private JTextField txtAnno = new JTextField();
    private JDateChooser fechainicio;
    private JDateChooser fechafin;
    private JSpinnerDateEditor txtfechainicio = new JSpinnerDateEditor();
    private JSpinnerDateEditor txtfechafin = new JSpinnerDateEditor();
    private JFileChooserModel fileChooser = new JFileChooserModel();
       private JLabel lbImpresion = new JLabel("Tipo de Impresión");
       private JComboBoxAutocomplete cbuImpresion = new JComboBoxAutocomplete();
         private ComboModelTipo modelcbImpresion = new ComboModelTipo();
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("docx", "docx");

    public PanelSaldoDeCuenta() throws SQLException {
        initComponets();
    }

    private void initComponets() throws SQLException {
        fechainicio = new JDateChooser(txtfechainicio);
        fechafin = new JDateChooser(txtfechafin);
        fechainicio.setDate(new Date());
        fechafin.setDate(new Date());
        panelSaldoDeCuenta.getWord().setIcon(ViewLogin.picture.getWord());
        panelSaldoDeCuenta.getWord().setToolTipText("Exportar a Word");
        panelSaldoDeCuenta.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panelSaldoDeCuenta.getActualizar().setToolTipText("Exportar a Excel");
        panelSaldoDeCuenta.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panelSaldoDeCuenta.getEliminar().setToolTipText("Expotar a pdf");
        panelSaldoDeCuenta.getPanelData().setVisible(false);
        panelSaldoDeCuenta.getPanelFormulario().setLayout(new BorderLayout());
        modelcbempresa.setData(LogicEmpresa.listaDeEmpresa());
        cbEmpresa.setModel(modelcbempresa);
           cbuImpresion.setModel(modelcbImpresion);
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
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lblAnno, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
          panelMolde.add(txtAnno, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lbImpresion, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(cbuImpresion, gbc);
      

        panelSaldoDeCuenta.add(panelMolde);
        panelSaldoDeCuenta.getWord().addActionListener(actionListener);
        panelSaldoDeCuenta.getActualizar().addActionListener(actionListener);
        panelSaldoDeCuenta.getEliminar().addActionListener(actionListener);
    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == panelSaldoDeCuenta.getActualizar()) {
                WorkerCuenta12Excel xls = new WorkerCuenta12Excel();
                xls.execute();
            } else if (e.getSource() == panelSaldoDeCuenta.getEliminar()) {
                WorkerCuenta12PDF pdf = new WorkerCuenta12PDF();
                pdf.execute();
            } else if (e.getSource() == panelSaldoDeCuenta.getWord()) {
                WorkerCuenta12DOC pdf = new WorkerCuenta12DOC();
                pdf.execute();
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

    private void exportarCuenta12AEXCEL() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
      BeanImpresion objImpresion = ((ComboModelTipo) (cbuImpresion.getModel())).getElement(cbuImpresion.getSelectedIndex());
        String periodo = txtAnno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterXLS, "SaldoDeCuenta", "xlsx");
            java.sql.Date ini = new java.sql.Date(fechainicio.getDate().getTime());
            java.sql.Date fin = new java.sql.Date(fechafin.getDate().getTime());
            LogicSaldoDeCuenta.listaSaldoDeCuenta(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo,objImpresion.getNumero(), archivo, "xlsx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarCuenta12APDF() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
          BeanImpresion objImpresion = ((ComboModelTipo) (cbuImpresion.getModel())).getElement(cbuImpresion.getSelectedIndex());
        String periodo = txtAnno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            java.sql.Date ini = new java.sql.Date(fechainicio.getDate().getTime());
            java.sql.Date fin = new java.sql.Date(fechafin.getDate().getTime());
            File archivo = seleccionarArchivo(filterPDF, "SaldoDeCuenta", "pdf");
            LogicSaldoDeCuenta.listaSaldoDeCuenta(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo,objImpresion.getNumero(), archivo, "pdf");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarCuenta12ADOC() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
           BeanImpresion objImpresion = ((ComboModelTipo) (cbuImpresion.getModel())).getElement(cbuImpresion.getSelectedIndex());
        String periodo = txtAnno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            java.sql.Date ini = new java.sql.Date(fechainicio.getDate().getTime());
            java.sql.Date fin = new java.sql.Date(fechafin.getDate().getTime());
            File archivo = seleccionarArchivo(filterTXT, "SaldoDeCuenta", "docx");
            LogicSaldoDeCuenta.listaSaldoDeCuenta(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo,objImpresion.getNumero(), archivo, "docx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    class WorkerCuenta12Excel extends SwingWorker<Boolean, Integer> {

        public WorkerCuenta12Excel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarCuenta12AEXCEL();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCuenta12PDF extends SwingWorker<Boolean, Integer> {

        public WorkerCuenta12PDF() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarCuenta12APDF();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCuenta12DOC extends SwingWorker<Boolean, Integer> {

        public WorkerCuenta12DOC() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarCuenta12ADOC();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    public FrmPanel getSaldoDeCuenta() {
        return panelSaldoDeCuenta;
    }
}
