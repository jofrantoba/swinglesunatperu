package com.contab.view;


import com.contab.bean.BeanEmpresa;
import com.contab.combobox.ComboModelEmpresas;


import com.contab.logic.LogicBalanceComprobacion;
import com.contab.logic.LogicCaja;
import com.contab.logic.LogicEmpresa;
import com.contab.logic.LogicSaldoDeCuenta;
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
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRDataSource;

public class PanelDetalleCuenta10 extends JPanel {

    private JLabel lblEmpresa = new JLabel("Empresa");
    private ComboModelEmpresas modelcbempresa = new ComboModelEmpresas();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private JLabel lbPeriodo = new JLabel("periodo");        
    private JTextField txtAno=new JTextField();
    private FrmPanel panelDetalleCuenta10= new FrmPanel(ViewLogin.picture.getIconCuenta14(), "DETALLE DE CUENTA 10");
    private JPanel panelMolde=new JPanel();
    
    private JFileChooserModel fileChooser = new JFileChooserModel();
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("docx", "docx");
    public PanelDetalleCuenta10() throws SQLException {
        initComponets();
    }

    private void initComponets() throws SQLException {
       panelDetalleCuenta10.getNuevo().setIcon(ViewLogin.picture.getWord());
        panelDetalleCuenta10.getNuevo().setToolTipText("Exportar a Word");
        panelDetalleCuenta10.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panelDetalleCuenta10.getActualizar().setToolTipText("Exportar a Excel");
        panelDetalleCuenta10.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panelDetalleCuenta10.getEliminar().setToolTipText("Expotar a pdf");
        panelDetalleCuenta10.getPanelData().setVisible(false);
        panelDetalleCuenta10.getPanelFormulario().setLayout(new BorderLayout());        
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
      
        panelDetalleCuenta10.add(panelMolde);
          panelDetalleCuenta10.getNuevo().addActionListener(actionListener);
        panelDetalleCuenta10.getActualizar().addActionListener(actionListener);
        panelDetalleCuenta10.getEliminar().addActionListener(actionListener);        
    }
    
     ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == panelDetalleCuenta10.getActualizar()) {
                WorkerBalanceComprobacionExcel xls = new WorkerBalanceComprobacionExcel();
                xls.execute();
            } else if(e.getSource() == panelDetalleCuenta10.getEliminar()){
                WorkerBalanceComprobacionPDF pdf=new WorkerBalanceComprobacionPDF();
                pdf.execute();
            }
            else if (e.getSource() == panelDetalleCuenta10.getNuevo()) {
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
    
    private void exportarDetalleCuenta10AEXCEL() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());        
        String periodo=txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterXLS, "DetalleCuenta10", "xlsx");
            String tipo="S/.";
            LogicCaja.listaDatelleCuenta10(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo,tipo, archivo, "xlsx");
            
        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }
     private void exportarDetalleCuenta10ADOC() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterPDF, "DetalleCuenta10", "docx");
            String tipo = "S/.";
            LogicCaja.listaDatelleCuenta10(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, tipo, archivo, "docx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }
    
    private void exportarDetalleCuenta10APDF() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());        
        String periodo=txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterPDF, "DetalleCuenta10", "pdf");
              String tipo="S/.";
            LogicCaja.listaDatelleCuenta10(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo,tipo, archivo, "pdf");
            
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
                exportarDetalleCuenta10AEXCEL();
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
                exportarDetalleCuenta10APDF();
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
                exportarDetalleCuenta10ADOC();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    public FrmPanel getDetalleCuenta10() {
        return panelDetalleCuenta10;
    }
        
}
