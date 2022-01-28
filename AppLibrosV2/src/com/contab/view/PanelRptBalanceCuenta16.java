/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

import com.contab.bean.BeanEmpresa;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.datasource.DataSourceBICuenta16;
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
public class PanelRptBalanceCuenta16 {

    private JLabel lblEmpresa = new JLabel("Empresa");
    private ComboModelEmpresas modelcbempresa = new ComboModelEmpresas();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private JTextField txtanno;
    private JLabel lblanno = new JLabel("AÑO");
    private FrmPanel panelCuenta14 = new FrmPanel(ViewLogin.picture.getIconCuenta14(), "CUENTAS POR COBRAR DIVERSAS");
    private JFileChooserModel fileChooser = new JFileChooserModel();
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    FileNameExtensionFilter filterDOC = new FileNameExtensionFilter("docx", "docx");
    private JPanel panelMolde = new JPanel();

    public PanelRptBalanceCuenta16() throws SQLException {
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
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lblanno, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(txtanno, gbc);
        txtanno.setColumns(5);
        panelCuenta14.getPanelFormulario().add(panelMolde);
        panelCuenta14.getNuevo().addActionListener(actionListener);
        panelCuenta14.getActualizar().addActionListener(actionListener);
        panelCuenta14.getEliminar().addActionListener(actionListener);
    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == panelCuenta14.getEliminar()) {
                WorkerLIBCuenta14PDF pdf = new WorkerLIBCuenta14PDF();
                pdf.execute();
            } else if (e.getSource() == panelCuenta14.getActualizar()) {
                WorkerLIBCuenta14Excel xls = new WorkerLIBCuenta14Excel();
                xls.execute();
            } else if (e.getSource() == panelCuenta14.getNuevo()) {
                WorkerLIBCuenta14DOC xls = new WorkerLIBCuenta14DOC();
                xls.execute();
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
        JOptionPane.showMessageDialog(null, "REPORTE LIBRO CUENTA 16 ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
    }

    public ArrayList generarLibroIBCuenta16() throws SQLException {
        try {
            ArrayList array = new ArrayList();
            BeanEmpresa emp = ((ComboModelEmpresas) cbEmpresa.getModel()).getElement(cbEmpresa.getSelectedIndex());
            String periodo = txtanno.getText();
            DataSourceBICuenta16 ds = new DataSourceBICuenta16();
            LogicBalanceInventarios.BICuenta16(periodo, emp.getCodigo(), ds);
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

    private void exportarLibroBICuenta14APDF() throws SQLException {
        File archivo = seleccionarArchivo(filterPDF, "LibroInvBalancesCuenta16", "pdf");
        if (archivo != null) {
            ArrayList array = new ArrayList();
            array = generarLibroIBCuenta16();
            if (array != null) {
                DataSourceBICuenta16 ds = (DataSourceBICuenta16) array.get(0);
                String periodo = array.get(1).toString();
                BeanEmpresa emp = (BeanEmpresa) array.get(2);
                Map parameters = new HashMap();
                parameters.put("P_ANNO", periodo);
                parameters.put("P_RUC", emp.getRuc());
                parameters.put("P_RAZON", emp.getDescripcion());
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutaJasper = "/com/contab/report/rptibcuenta16.jasper";
                generarReporteDS(archivo, "pdf", parameters, ds, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");
            }
        }

    }

    private void exportarLibroBICuenta14AEXCEL() throws SQLException {
        File archivo = seleccionarArchivo(filterXLS, "LibroInvBalancesCuenta16", "xlsx");
        if (archivo != null) {
            ArrayList array = new ArrayList();
            array = generarLibroIBCuenta16();
            if (array != null) {
                DataSourceBICuenta16 ds = (DataSourceBICuenta16) array.get(0);
                String periodo = array.get(1).toString();
                BeanEmpresa emp = (BeanEmpresa) array.get(2);
                Map parameters = new HashMap();
                parameters.put("P_ANNO", periodo);
                parameters.put("P_RUC", emp.getRuc());
                parameters.put("P_RAZON", emp.getDescripcion());
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutaJasper = "/com/contab/report/rptibcuenta16.jasper";
                generarReporteDS(archivo, "xlsx", parameters, ds, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");
            }
        }

    }

    private void exportarLibroBICuenta14ADOC() throws SQLException {
        File archivo = seleccionarArchivo(filterDOC, "LibroInvBalancesCuenta16", "docx");
        if (archivo != null) {
            ArrayList array = new ArrayList();
            array = generarLibroIBCuenta16();
            if (array != null) {
                DataSourceBICuenta16 ds = (DataSourceBICuenta16) array.get(0);
                String periodo = array.get(1).toString();
                BeanEmpresa emp = (BeanEmpresa) array.get(2);
                Map parameters = new HashMap();
                parameters.put("P_ANNO", periodo);
                parameters.put("P_RUC", emp.getRuc());
                parameters.put("P_RAZON", emp.getDescripcion());
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutaJasper = "/com/contab/report/rptibcuenta16.jasper";
                generarReporteDS(archivo, "docx", parameters, ds, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");
            }
        }

    }

    class WorkerLIBCuenta14Excel extends SwingWorker<Boolean, Integer> {

        public WorkerLIBCuenta14Excel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroBICuenta14AEXCEL();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerLIBCuenta14PDF extends SwingWorker<Boolean, Integer> {

        public WorkerLIBCuenta14PDF() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroBICuenta14APDF();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerLIBCuenta14DOC extends SwingWorker<Boolean, Integer> {

        public WorkerLIBCuenta14DOC() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroBICuenta14ADOC();
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
