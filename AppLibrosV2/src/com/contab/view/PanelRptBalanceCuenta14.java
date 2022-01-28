/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

import com.contab.bean.BeanEmpresa;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.datasource.DataSourceBICuenta14;
import com.contab.logic.LogicBalanceInventarios;
import com.contab.logic.LogicEmpresa;
import com.contab.util.Exportar;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;

/**
 *
 * @author Administrador
 */
public class PanelRptBalanceCuenta14 {

    private JLabel lblEmpresa = new JLabel("Empresa");
    private ComboModelEmpresas modelcbempresa = new ComboModelEmpresas();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private JTextField txtanno;
    private JLabel lblanno = new JLabel("AÑO");
    private JLabel lblempleado = new JLabel("Empleados");
    private JLabel lblAccionista = new JLabel("Accionistas");
    private JCheckBox chkemp = new JCheckBox();
    private JCheckBox chkacc = new JCheckBox();
    private FrmPanel panelCuenta14 = new FrmPanel(ViewLogin.picture.getIconCuenta14(), "CUENTAS POR COBRAR A EMPLEADOS Y ACCIONISTAS");
    private JFileChooserModel fileChooser = new JFileChooserModel();
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    FileNameExtensionFilter filterDOC = new FileNameExtensionFilter("docx", "docx");
    private JPanel panelMolde = new JPanel();

    public PanelRptBalanceCuenta14() throws SQLException {
        initComponets();
    }

    private void initComponets() throws SQLException {
        panelCuenta14.getNuevo().setIcon(ViewLogin.picture.getWord());
        panelCuenta14.getNuevo().setToolTipText("Exportar a Word");
        panelCuenta14.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panelCuenta14.getActualizar().setToolTipText("Exportar a Excel");
        panelCuenta14.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panelCuenta14.getEliminar().setToolTipText("Expotar a pdf");
        panelCuenta14.getPanelData().setVisible(false);
        panelCuenta14.getPanelFormulario().setLayout(new BorderLayout());
        txtanno = new JTextField();
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
        panelMolde.add(lblempleado, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(chkemp, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lblAccionista, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(chkacc, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lblanno, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(txtanno, gbc);
        txtanno.setColumns(5);
        panelCuenta14.getPanelFormulario().add(panelMolde);
        panelCuenta14.getActualizar().addActionListener(actionListener);
        panelCuenta14.getEliminar().addActionListener(actionListener);
        panelCuenta14.getNuevo().addActionListener(actionListener);
    }

    private boolean validarCamposForm() {
        boolean estado = false;
        if (cbEmpresa.getSelectedItem() != null && !txtanno.getText().equals("") && !tipoDePersona().equals("NADA")) {
            estado = true;
        }
        return estado;
    }

    private String tipoDePersona() {
        if (chkemp.isSelected() && chkacc.isSelected()) {
            return "AMBOS";
        } else if (chkemp.isSelected()) {
            return "EMP";
        } else if (chkacc.isSelected()) {
            return "ACC";
        }
        return "NADA";
    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == panelCuenta14.getEliminar()) {
                if (validarCamposForm()) {
                    WorkerLIBCuenta14PDF pdf = new WorkerLIBCuenta14PDF(tipoDePersona());
                    pdf.execute();
                } else {
                    JOptionPane.showMessageDialog(null, "Llene los datos correctamente", "", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (e.getSource() == panelCuenta14.getActualizar()) {
                if (validarCamposForm()) {
                    WorkerLIBCuenta14Excel xls = new WorkerLIBCuenta14Excel(tipoDePersona());
                    xls.execute();
                } else {
                    JOptionPane.showMessageDialog(null, "Llene los datos correctamente", "", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (e.getSource() == panelCuenta14.getNuevo()) {
                if (validarCamposForm()) {
                    WorkerLIBCuenta14DOC xls = new WorkerLIBCuenta14DOC(tipoDePersona());
                    xls.execute();
                } else {
                    JOptionPane.showMessageDialog(null, "Llene los datos correctamente", "", JOptionPane.INFORMATION_MESSAGE);
                }
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

    private void generarReporteDS(File archivo, String tipoExportacion, Map parameters, JRDataSource cnx, String rutaJasper) {
        Exportar ex = new Exportar(archivo, parameters, tipoExportacion, cnx, rutaJasper);
        ex.show();
        JOptionPane.showMessageDialog(null, "REPORTE LIBRO CUENTA 14 ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
    }

    public ArrayList generarLibroIBCuenta14(String tipo) throws SQLException {
        try {
            ArrayList array = new ArrayList();
            BeanEmpresa emp = ((ComboModelEmpresas) cbEmpresa.getModel()).getElement(cbEmpresa.getSelectedIndex());
            String periodo = txtanno.getText();
            DataSourceBICuenta14 ds = new DataSourceBICuenta14();
            LogicBalanceInventarios.BICuenta14(periodo, emp.getCodigo(), tipo, ds);
            ViewApplication.mensajeEstado.setText(null);
            ViewApplication.mensajeEstado.setText("PREPARANDOSE PARA GENERAR REPORTE");
            array.add(ds);
            array.add(periodo);
            array.add(emp);
            return array;
        } catch (ArrayIndexOutOfBoundsException arr) {
            System.err.println(arr.getCause());
            return null;
        }
    }

    private void exportarLibroBICuenta14APDF(String tipo) throws SQLException {
        File archivo = seleccionarArchivo(filterPDF, "LibroInvBalancesCuenta14", "pdf");
        if (archivo != null) {
            ArrayList array = new ArrayList();
            array = generarLibroIBCuenta14(tipo);
            if (array != null) {
                DataSourceBICuenta14 ds = (DataSourceBICuenta14) array.get(0);
                String periodo = array.get(1).toString();
                BeanEmpresa emp = (BeanEmpresa) array.get(2);
                Map parameters = new HashMap();
                parameters.put("P_ANNO", periodo);
                parameters.put("P_RUC", emp.getRuc());
                parameters.put("P_RAZON", emp.getDescripcion());
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutaJasper = "/com/contab/report/rptibcuenta14.jasper";
                generarReporteDS(archivo, "pdf", parameters, ds, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");
            }
        }

    }

    private void exportarLibroBICuenta14AEXCEL(String tipo) throws SQLException {
        File archivo = seleccionarArchivo(filterXLS, "LibroInvBalancesCuenta14", "xlsx");
        if (archivo != null) {
            ArrayList array = new ArrayList();
            array = generarLibroIBCuenta14(tipo);
            if (array != null) {
                DataSourceBICuenta14 ds = (DataSourceBICuenta14) array.get(0);
                String periodo = array.get(1).toString();
                BeanEmpresa emp = (BeanEmpresa) array.get(2);
                Map parameters = new HashMap();
                parameters.put("P_ANNO", periodo);
                parameters.put("P_RUC", emp.getRuc());
                parameters.put("P_RAZON", emp.getDescripcion());
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutaJasper = "/com/contab/report/rptibcuenta14.jasper";
                generarReporteDS(archivo, "xlsx", parameters, ds, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");
            }
        }

    }

    private void exportarLibroBICuenta14ADOC(String tipo) throws SQLException {
        File archivo = seleccionarArchivo(filterDOC, "LibroInvBalancesCuenta14", "doc");
        if (archivo != null) {
            ArrayList array = new ArrayList();
            array = generarLibroIBCuenta14(tipo);
            if (array != null) {
                DataSourceBICuenta14 ds = (DataSourceBICuenta14) array.get(0);
                String periodo = array.get(1).toString();
                BeanEmpresa emp = (BeanEmpresa) array.get(2);
                Map parameters = new HashMap();
                parameters.put("P_ANNO", periodo);
                parameters.put("P_RUC", emp.getRuc());
                parameters.put("P_RAZON", emp.getDescripcion());
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutaJasper = "/com/contab/report/rptibcuenta14.jasper";
                generarReporteDS(archivo, "docx", parameters, ds, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");
            }
        }

    }

    class WorkerLIBCuenta14Excel extends SwingWorker<Boolean, Integer> {

        private String tipoper;

        public WorkerLIBCuenta14Excel(String tipo) {
            tipoper = tipo;
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroBICuenta14AEXCEL(tipoper);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerLIBCuenta14PDF extends SwingWorker<Boolean, Integer> {

        private String tipoper;

        public WorkerLIBCuenta14PDF(String tipo) {
            tipoper = tipo;
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroBICuenta14APDF(tipoper);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerLIBCuenta14DOC extends SwingWorker<Boolean, Integer> {

        private String tipoper;

        public WorkerLIBCuenta14DOC(String tipo) {
            tipoper = tipo;
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroBICuenta14ADOC(tipoper);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    public FrmPanel getPanelCuenta14() {
        return panelCuenta14;
    }
}
