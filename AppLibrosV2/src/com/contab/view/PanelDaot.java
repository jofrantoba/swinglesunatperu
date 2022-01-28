/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

import com.contab.bean.BeanEmpresa;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.datasource.DataSourceDaot;
import com.contab.datasource.DataSourceRptSunat;
import com.contab.logic.LogicDaot;
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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;

/**
 *
 * @author Administrador
 */
public class PanelDaot {
    private JLabel lblEmpresa = new JLabel("Empresa");
    private ComboModelEmpresas modelcbempresa = new ComboModelEmpresas();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private JTextField txtanno;       
    private JLabel lblanno = new JLabel("Periodo"); 
    private JLabel lblcxc=new JLabel("CXC");
    private JLabel lblcxp=new JLabel("CXP");
    private JRadioButton cxc=new JRadioButton();
    private JRadioButton cxp=new JRadioButton();
    private JLabel lbldetalle=new JLabel("Detalle");
    private JLabel lblconsolidado=new JLabel("Consolidado");
    private JRadioButton detalle=new JRadioButton();
    private JRadioButton consolidado=new JRadioButton();
    private ButtonGroup  group=new ButtonGroup();
    private ButtonGroup  groupdetalle=new ButtonGroup();
    private FrmPanel panel = new FrmPanel(ViewLogin.picture.getIconCuenta14(), "DAOT");
    private JFileChooserModel fileChooser = new JFileChooserModel();
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");    
    private JPanel panelMolde=new JPanel();
    private JLabel lbluit=new JLabel("UIT");
    private JTextField txtuit=new JTextField();
    public PanelDaot() throws SQLException {
        initComponets();
    }

    private void initComponets() throws SQLException {
        panel.getNuevo().setIcon(ViewLogin.picture.getIconTXT());
        panel.getNuevo().setToolTipText("Reporte sunat");
        panel.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panel.getActualizar().setToolTipText("Exportar a Excel");
        panel.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panel.getEliminar().setToolTipText("Expotar a pdf");
        panel.getPanelData().setVisible(false);
        panel.getPanelFormulario().setLayout(new BorderLayout());        
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
        panelMolde.add(lblcxc, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(cxc, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lblcxp, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(cxp, gbc);
        group.add(cxc);
        group.add(cxp);
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
        
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lbluit, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(txtuit, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lbldetalle, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(detalle, gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lblconsolidado, gbc);
        gbc.gridx = 3;
        gbc.gridy = 4;        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(consolidado, gbc);
        groupdetalle.add(detalle);
        groupdetalle.add(consolidado);           
        txtuit.setColumns(5);
        panel.getPanelFormulario().add(panelMolde);
        panel.getNuevo().addActionListener(actionListener);
        panel.getActualizar().addActionListener(actionListener);
        panel.getEliminar().addActionListener(actionListener);        
    }
    
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
    }
    
    public ArrayList generarLibroDaot() throws SQLException {        
        try {
            ArrayList array=new ArrayList();
            BeanEmpresa emp = ((ComboModelEmpresas) cbEmpresa.getModel()).getElement(cbEmpresa.getSelectedIndex());                                                
            String periodo=txtanno.getText();
            DataSourceDaot ds = new DataSourceDaot();
            String fechaini=periodo+"-01-01";
            String fechafin=periodo+"-12-31";
            java.sql.Date ini=java.sql.Date.valueOf(fechaini);
            java.sql.Date fin=java.sql.Date.valueOf(fechafin);
            if(cxc.isSelected()){
                BigDecimal uit=BigDecimal.valueOf(7200);
             try{
                 uit=BigDecimal.valueOf(Double.valueOf(txtuit.getText()));
             }catch(Exception ex){}
             if(detalle.isSelected()){
                 LogicDaot.generandoDaot(emp.getCodigo(),emp.getRuc(), periodo, ini, fin,uit,ds,"detalle");
             }else if(consolidado.isSelected()){
                 LogicDaot.generandoDaot(emp.getCodigo(),emp.getRuc(), periodo, ini, fin,uit,ds,"resumen");
             }             
            }      
            
            if(cxp.isSelected()){
                BigDecimal uit=BigDecimal.valueOf(7200);
             try{
                 uit=BigDecimal.valueOf(Double.valueOf(txtuit.getText()));
             }catch(Exception ex){}
             if(detalle.isSelected()){
                 LogicDaot.generandoCxpDaot(emp.getCodigo(),emp.getRuc(), periodo, ini, fin,uit,ds,"detalle");
             }else if(consolidado.isSelected()){
                 LogicDaot.generandoCxpDaot(emp.getCodigo(),emp.getRuc(), periodo, ini, fin,uit,ds,"resumen");
             }             
            }      
            
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
    
    public ArrayList generarLibroDaotSunat() throws SQLException {        
        try {
            ArrayList array=new ArrayList();
            BeanEmpresa emp = ((ComboModelEmpresas) cbEmpresa.getModel()).getElement(cbEmpresa.getSelectedIndex());                                                
            String periodo=txtanno.getText();
            DataSourceRptSunat ds = new DataSourceRptSunat();
            String fechaini=periodo+"-01-01";
            String fechafin=periodo+"-12-31";
            java.sql.Date ini=java.sql.Date.valueOf(fechaini);
            java.sql.Date fin=java.sql.Date.valueOf(fechafin);
            if(cxc.isSelected()){
                BigDecimal uit=BigDecimal.valueOf(7200);
             try{
                 uit=BigDecimal.valueOf(Double.valueOf(txtuit.getText()));
             }catch(Exception ex){}            
                 LogicDaot.generandoDaotSunat(emp.getCodigo(),emp.getRuc(), periodo, ini, fin,uit,ds);             
            }            
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
    
    private void exportarDaotPDF() throws SQLException {
        File archivo = seleccionarArchivo(filterPDF, "LibroDaot", "pdf");
        if (archivo != null) {
            ArrayList array=new ArrayList();
            array = generarLibroDaot();            
            if (array != null) {
                DataSourceDaot ds = (DataSourceDaot) array.get(0);
                String periodo = array.get(1).toString();
                BeanEmpresa emp = (BeanEmpresa) array.get(2);
                Map parameters = new HashMap();
                parameters.put("P_ANNO", periodo);
                parameters.put("P_RUC", emp.getRuc());
                parameters.put("P_RAZON", emp.getDescripcion());
                 parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutaJasper = "/com/contab/report/rptDaot.jasper";
                generarReporteDS(archivo, "pdf", parameters, ds, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");
                JOptionPane.showMessageDialog(null, "REPORTE GENERADO CORRECTAMENTE  ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    private void exportarDaotEXCEL() throws SQLException {        
        File archivo = seleccionarArchivo(filterXLS, "LibroDaot", "xlsx");
        if (archivo != null) {
            ArrayList array=new ArrayList();
            array = generarLibroDaot();            
            if (array != null) {
                DataSourceDaot ds = (DataSourceDaot) array.get(0);
                String periodo = array.get(1).toString();
                BeanEmpresa emp = (BeanEmpresa) array.get(2);
                Map parameters = new HashMap();
                parameters.put("P_ANNO", periodo);
                parameters.put("P_RUC", emp.getRuc());
                parameters.put("P_RAZON", emp.getDescripcion());
                    parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutaJasper = "/com/contab/report/rptDaot.jasper";
                generarReporteDS(archivo, "xlsx", parameters, ds, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");
                JOptionPane.showMessageDialog(null, "REPORTE GENERADO CORRECTAMENTE  ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
    
    private void exportarDaotSunatEXCEL() throws SQLException {        
        File archivo = seleccionarArchivo(filterXLS, "LibroDaotSunat", "xlsx");
        if (archivo != null) {
            ArrayList array=new ArrayList();
            array = generarLibroDaotSunat();            
            if (array != null) {
                DataSourceRptSunat ds = (DataSourceRptSunat) array.get(0);
                String periodo = array.get(1).toString();
                BeanEmpresa emp = (BeanEmpresa) array.get(2);
                Map parameters = new HashMap();
                parameters.put("P_ANNO", periodo);
                parameters.put("P_RUC", emp.getRuc());
                parameters.put("P_RAZON", emp.getDescripcion());
                    parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutaJasper = "/com/contab/report/rptDaotSunat.jasper";
                generarReporteDS(archivo, "xlsx", parameters, ds, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");
                JOptionPane.showMessageDialog(null, "REPORTE GENERADO CORRECTAMENTE  ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
    
    class WorkerDaotExcel extends SwingWorker<Boolean, Integer> {        
        public WorkerDaotExcel() {        
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarDaotEXCEL();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }
    
    class WorkerDaotSunatExcel extends SwingWorker<Boolean, Integer> {        
        public WorkerDaotSunatExcel() {        
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarDaotSunatEXCEL();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerDaotPDF extends SwingWorker<Boolean, Integer> {        

        public WorkerDaotPDF() {            
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarDaotPDF();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }
    
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == panel.getEliminar()) {                
                    WorkerDaotPDF pdf = new WorkerDaotPDF();   
                    pdf.execute();                
            } else if (e.getSource() == panel.getActualizar()) {                
                WorkerDaotExcel xls = new WorkerDaotExcel();
                xls.execute();                
            } else if (e.getSource() == panel.getNuevo()) {                
                WorkerDaotSunatExcel xls = new WorkerDaotSunatExcel();
                xls.execute();                
            } 
        }
    };

    public FrmPanel getPanel() {
        return panel;
    }
        
}
