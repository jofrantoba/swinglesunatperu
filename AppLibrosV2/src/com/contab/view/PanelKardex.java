package com.contab.view;

import com.contab.bean.BeanAlmacen;
import com.contab.bean.BeanEmpresa;
import com.contab.bean.BeanImpresion;
import com.contab.bean.BeanMes;
import com.contab.combobox.ComboModelAlmacen;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.combobox.ComboModelImpresion;
import com.contab.combobox.ComboModelMes;
import com.contab.logic.LogicAlmacen;
import com.contab.logic.LogicEmpresa;
import com.contab.logic.LogicKardex;
import com.contab.util.JComboBoxAutocomplete;
import com.contab.util.JFileChooserModel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import org.openide.util.Exceptions;

public class PanelKardex extends JPanel {

    private JLabel lblEmpresa = new JLabel("Empresa");
    private ComboModelAlmacen modelAlmacen = new ComboModelAlmacen();
    private ComboModelEmpresas modelcbempresa = new ComboModelEmpresas();
    private ComboModelMes modelcbMes = new ComboModelMes();
    private ComboModelImpresion modelcbImpresion = new ComboModelImpresion();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private JComboBoxAutocomplete cbuMes = new JComboBoxAutocomplete();
    private JComboBoxAutocomplete cbuImpresion = new JComboBoxAutocomplete();
    private JLabel lblAlmacen = new JLabel("Almacen");
    private ComboModelAlmacen modelcbAlmacen = new ComboModelAlmacen();
    private JComboBoxAutocomplete cbAlmacen = new JComboBoxAutocomplete();
    //   private ComboBoxModel Cbomes  = new ComboBoxModel();
    private JLabel Mes = new JLabel("Mes");
    private JLabel lbPeriodo = new JLabel("periodo");
    private JLabel lbImpresion = new JLabel("Tipo de Impresión");
    private JTextField txtAno = new JTextField();
    private FrmPanel panelKardex = new FrmPanel(ViewLogin.picture.getIconCuenta14(), "KARDEX");
    private JPanel panelMolde = new JPanel();
    private JFileChooserModel fileChooser = new JFileChooserModel();
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    FileNameExtensionFilter filterDOC = new FileNameExtensionFilter("docx", "docx");
    FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("txt", "txt");

    public PanelKardex() throws SQLException {
        initComponets();
    }

    private void initComponets() throws SQLException {


        panelKardex.getTxt().setIcon(ViewLogin.picture.getIconTXT());
        panelKardex.getTxt().setToolTipText("Exportar a TXT");
        panelKardex.getNuevo().setIcon(ViewLogin.picture.getWord());
        panelKardex.getNuevo().setToolTipText("Exportar a Word");
        panelKardex.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panelKardex.getActualizar().setToolTipText("Exportar a Excel");
        panelKardex.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panelKardex.getEliminar().setToolTipText("Expotar a pdf");
        panelKardex.getPanelData().setVisible(false);
        panelKardex.getPanelFormulario().setLayout(new BorderLayout());
        modelcbempresa.setData(LogicEmpresa.listaDeEmpresa());
        cbEmpresa.setModel(modelcbempresa);
        cbuMes.setModel(modelcbMes);
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
        panelMolde.add(lblAlmacen, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(cbAlmacen, gbc);
        /// años
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lbPeriodo, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(txtAno, gbc);

        ///combo de mes 
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

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lbImpresion, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(cbuImpresion, gbc);



        panelKardex.add(panelMolde);
        panelKardex.getTxt().addActionListener(actionListener);
        panelKardex.getNuevo().addActionListener(actionListener);
        panelKardex.getActualizar().addActionListener(actionListener);
        panelKardex.getEliminar().addActionListener(actionListener);
        cbEmpresa.addItemListener(itemListener);

    }
    ItemListener itemListener = new ItemListener() {

        @Override
        public void itemStateChanged(ItemEvent e) {
            try {
                cbAlmacen.setSelectedItem(null);
                modelAlmacen.setData(LogicAlmacen.listaDeAlmacen(modelcbempresa.getElement(cbEmpresa.getSelectedIndex()).getCodigo()));
                cbAlmacen.setModel(modelAlmacen);
                cbAlmacen.updateUI();
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    };
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == panelKardex.getActualizar()) {
                WorkerKardexExcel xls = new WorkerKardexExcel();
                xls.execute();
            } else if (e.getSource() == panelKardex.getEliminar()) {
                WorkerKardexPDF pdf = new WorkerKardexPDF();
                pdf.execute();
            } else if (e.getSource() == panelKardex.getNuevo()) {
                WorkerKardexDOC word = new WorkerKardexDOC();
                word.execute();
            } else if (e.getSource() == panelKardex.getTxt()) {
                WorkerKardexTXT word = new WorkerKardexTXT();
                word.execute();
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

    private void exportarKardexAEXCEL() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        BeanAlmacen objAlmacen = ((ComboModelAlmacen) (cbAlmacen.getModel())).getElement(cbAlmacen.getSelectedIndex());
        BeanMes objMes = ((ComboModelMes) (cbuMes.getModel())).getElement(cbuMes.getSelectedIndex());
        BeanImpresion objImpresion = ((ComboModelImpresion) (cbuImpresion.getModel())).getElement(cbuImpresion.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterXLS, "Kardex", "xlsx");
            LogicKardex.listaKardex(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), objAlmacen.getCodigo(), objAlmacen.getDireccion(), periodo, objMes.getNombre(), objMes.getNumero(), objImpresion.getNumero(), archivo, "xlsx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarKardexAPDF() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());

        BeanAlmacen objAlmacen = ((ComboModelAlmacen) (cbAlmacen.getModel())).getElement(cbAlmacen.getSelectedIndex());
        BeanMes objMes = ((ComboModelMes) (cbuMes.getModel())).getElement(cbuMes.getSelectedIndex());
        String periodo = txtAno.getText();
        BeanImpresion objImpresion = ((ComboModelImpresion) (cbuImpresion.getModel())).getElement(cbuImpresion.getSelectedIndex());
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterPDF, "Kardex", "pdf");
            LogicKardex.listaKardex(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), objAlmacen.getCodigo(), objAlmacen.getDireccion(), periodo, objMes.getNombre(), objMes.getNumero(), objImpresion.getNumero(), archivo, "pdf");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarKardexADOC() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());

        BeanAlmacen objAlmacen = ((ComboModelAlmacen) (cbAlmacen.getModel())).getElement(cbAlmacen.getSelectedIndex());
        BeanMes objMes = ((ComboModelMes) (cbuMes.getModel())).getElement(cbuMes.getSelectedIndex());
        String periodo = txtAno.getText();
        BeanImpresion objImpresion = ((ComboModelImpresion) (cbuImpresion.getModel())).getElement(cbuImpresion.getSelectedIndex());
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterDOC, "Kardex", "docx");
            LogicKardex.listaKardex(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), objAlmacen.getCodigo(), objAlmacen.getDireccion(), periodo, objMes.getNombre(), objMes.getNumero(), objImpresion.getNumero(), archivo, "docx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarKardexATXT() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());

        BeanAlmacen objAlmacen = ((ComboModelAlmacen) (cbAlmacen.getModel())).getElement(cbAlmacen.getSelectedIndex());
        BeanMes objMes = ((ComboModelMes) (cbuMes.getModel())).getElement(cbuMes.getSelectedIndex());
        String periodo = txtAno.getText();
        BeanImpresion objImpresion = ((ComboModelImpresion) (cbuImpresion.getModel())).getElement(cbuImpresion.getSelectedIndex());
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterTXT, "Kardex", "txt");
            LogicKardex.listaKardex(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), objAlmacen.getCodigo(), objAlmacen.getDireccion(), periodo, objMes.getNombre(), objMes.getNumero(), objImpresion.getNumero(), archivo, "txt");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    class WorkerKardexExcel extends SwingWorker<Boolean, Integer> {

        public WorkerKardexExcel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarKardexAEXCEL();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerKardexPDF extends SwingWorker<Boolean, Integer> {

        public WorkerKardexPDF() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarKardexAPDF();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerKardexDOC extends SwingWorker<Boolean, Integer> {

        public WorkerKardexDOC() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarKardexADOC();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerKardexTXT extends SwingWorker<Boolean, Integer> {

        public WorkerKardexTXT() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarKardexATXT();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    public FrmPanel getPanelKardex() {
        return panelKardex;
    }
}
