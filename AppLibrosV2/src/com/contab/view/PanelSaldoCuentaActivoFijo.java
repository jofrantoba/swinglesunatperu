package com.contab.view;

import com.contab.bean.BeanEmpresa;
import com.contab.bean.BeanImpresion;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.combobox.ComboModelImpresion;
import com.contab.combobox.ComboModelTipo;


import com.contab.logic.LogicActivoFijo;
import com.contab.logic.LogicEmpresa;
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
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelSaldoCuentaActivoFijo extends JPanel {

    private JLabel lblEmpresa = new JLabel("Empresa");
    private ComboModelEmpresas modelcbempresa = new ComboModelEmpresas();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private JLabel lbPeriodo = new JLabel("Periodo");
    private JLabel lbImpresion = new JLabel("Tipo de Impresión");
    private JComboBoxAutocomplete cbuImpresion = new JComboBoxAutocomplete();
    private ComboModelTipo modelcbImpresion = new ComboModelTipo();
    private JTextField txtAno = new JTextField();
    private FrmPanel panelSaldoCuenta42 = new FrmPanel(ViewLogin.picture.getIconCuenta14(), "ACTIVO FIJO ");
    private JPanel panelMolde = new JPanel();
    private JLabel lblempleado = new JLabel("Reporte 1");
    private JLabel lblAccionista = new JLabel("Reporte 2");
    private JCheckBox chkemp = new JCheckBox();
    private JCheckBox chkacc = new JCheckBox();
    private JFileChooserModel fileChooser = new JFileChooserModel();
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("docx", "docx");

    public PanelSaldoCuentaActivoFijo() throws SQLException {
        initComponets();
    }

    private void initComponets() throws SQLException {
        panelSaldoCuenta42.getWord().setIcon(ViewLogin.picture.getWord());
        panelSaldoCuenta42.getWord().setToolTipText("Exportar a Word");
        panelSaldoCuenta42.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panelSaldoCuenta42.getActualizar().setToolTipText("Exportar a Excel");
        panelSaldoCuenta42.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panelSaldoCuenta42.getEliminar().setToolTipText("Expotar a pdf");
        panelSaldoCuenta42.getPanelData().setVisible(false);
        panelSaldoCuenta42.getPanelFormulario().setLayout(new BorderLayout());
        
        
        modelcbempresa.setData(LogicEmpresa.listaDeEmpresa());
        chkacc.getFocusTraversalKeysEnabled();
        txtAno.setText("2011");
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


        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lblempleado, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(chkemp, gbc);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lblAccionista, gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(chkacc, gbc);


        panelSaldoCuenta42.add(panelMolde);
        panelSaldoCuenta42.getWord().addActionListener(actionListener);
        panelSaldoCuenta42.getActualizar().addActionListener(actionListener);
        panelSaldoCuenta42.getEliminar().addActionListener(actionListener);
    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == panelSaldoCuenta42.getActualizar()) {
                WorkerBalanceComprobacionExcel xls = new WorkerBalanceComprobacionExcel();
                xls.execute();
            } else if (e.getSource() == panelSaldoCuenta42.getEliminar()) {
                WorkerBalanceComprobacionPDF pdf = new WorkerBalanceComprobacionPDF();
                pdf.execute();
            } else if (e.getSource() == panelSaldoCuenta42.getWord()) {
                WorkerBalanceComprobacionDOC pdf = new WorkerBalanceComprobacionDOC();
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

    private void exportarSaldoCuenta42AEXCEL() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        BeanImpresion objImpresion = ((ComboModelTipo) (cbuImpresion.getModel())).getElement(cbuImpresion.getSelectedIndex());
        String tipo = objImpresion.getNumero();
        System.out.println("tipo" + tipo);
        try {
            File archivo = seleccionarArchivo(filterXLS, "ACTIVOFIJO", "xlsx");

            //  if (chkemp.isSelected() && chkacc.isSelected()) {
            //return "AMBOS";
            //  } else

            if (chkemp.isSelected()) {

                LogicActivoFijo.listaSaldoACTIVOFIJO(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "xlsx");
                //return "EMP";
            } else if (chkacc.isSelected()) {
                // return "ACC";
                LogicActivoFijo.listaSaldoACTIVOFIJO(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "xlsx");
            }



        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarSaldoCuenta42APDF() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        BeanImpresion objImpresion = ((ComboModelTipo) (cbuImpresion.getModel())).getElement(cbuImpresion.getSelectedIndex());
        // String tipo=objImpresion.getNumero();
        String tipo = objImpresion.getNumero();
        try {
            File archivo = seleccionarArchivo(filterPDF, "ACTIVOFIJO", "pdf");


            if (chkemp.isSelected()) {

                LogicActivoFijo.listaSaldoACTIVOFIJO(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "pdf");
                //return "EMP";
            } else if (chkacc.isSelected()) {
                // return "ACC";
                LogicActivoFijo.listaSaldoACTIVOFIJO(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "pdf");
            }


        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarSaldoCuenta42ADOC() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        BeanImpresion objImpresion = ((ComboModelTipo) (cbuImpresion.getModel())).getElement(cbuImpresion.getSelectedIndex());
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterTXT, "ACTIVOFIJO", "docx");




            if (chkemp.isSelected()) {

                LogicActivoFijo.listaSaldoACTIVOFIJO(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "docx");
                //return "EMP";
            } else if (chkacc.isSelected()) {
                // return "ACC";
                LogicActivoFijo.listaSaldoACTIVOFIJO(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "docx");
            }

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
                exportarSaldoCuenta42AEXCEL();
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
                exportarSaldoCuenta42APDF();
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
                exportarSaldoCuenta42ADOC();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    public FrmPanel getSaldoCuenta42() {
        return panelSaldoCuenta42;
    }
}
