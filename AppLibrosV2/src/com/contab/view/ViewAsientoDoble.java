/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

import com.contab.bean.BeanEmpresa;
import com.contab.combobox.ComboModelEmpleado;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.logic.LogicAsientoDoble;
import com.contab.logic.LogicCuenta31;
import com.contab.logic.LogicEmpresa;
import com.contab.tablemodel.ModelAsientoDoble;
import com.contab.util.JComboBoxAutocomplete;
import com.contab.util.JFileChooserModel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class ViewAsientoDoble {

    private FrmPanel panelAsientoDoble = new FrmPanel(ViewLogin.picture.getIconCuenta14(), "Actualizar Cuentas ");
    private JLabel lblEmpresa = new JLabel("EMPRESA");
    private JLabel lblMes = new JLabel("MES");
    private JLabel lblCuenta = new JLabel("CUENTA");
    private JTextField txtEmpresa = new JTextField();
    private JLabel lblAnno = new JLabel("AÑO");
    private JTextField txtAnno = new JTextField();
    private JTextField txtMes = new JTextField();
    private JTextField txtCuenta = new JTextField();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private ComboModelEmpresas mdcbempresas = new ComboModelEmpresas();
    private ComboModelEmpleado mdemp = new ComboModelEmpleado();
    private JButton btnAdd = new JButton(ViewLogin.picture.getIconAdd());
    private JButton btnCommit = new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback = new JButton(ViewLogin.picture.getIconRollBack());
    private JLabel lblDescri = new JLabel("CUENTA   ");
    private JLabel lblDoc = new JLabel("CENTRO  ");
    private JTextField txtDescri = new JTextField();
    private JTextField txtDoc = new JTextField();
    private JFileChooserModel fileChooser = new JFileChooserModel();
    private ModelAsientoDoble mdtabemp = new ModelAsientoDoble();  //ModelAsientoDoble panelAsientoDoble.getTablaDatos()
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    FileNameExtensionFilter filterDOC = new FileNameExtensionFilter("docx", "docx");
    FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("txt", "txt");

    public ViewAsientoDoble() throws SQLException {
        initComponents();
    }

    private void initComponents() throws SQLException {
        //panelAsientoDoble.getTablaDatos().setModel(mdtabemp);
        mdtabemp.setTablaDatos(panelAsientoDoble.getTablaDatos());
        mdtabemp.getTablaDatos().setModel(mdtabemp);
        btnAdd.setToolTipText("Consultar Cuentas");
        btnCommit.setToolTipText("Confirmar operaciones");
       // btnRollback.setToolTipText("Actualizar");
        panelAsientoDoble.getWord().setIcon(ViewLogin.picture.getWord());
        panelAsientoDoble.getWord().setToolTipText("Exportar a Word");
        panelAsientoDoble.getNuevo().setIcon(ViewLogin.picture.getIconExcel());
        panelAsientoDoble.getNuevo().setToolTipText("Exportar a Excel");
        panelAsientoDoble.getActualizar().setIcon(ViewLogin.picture.getIconPDF32());
        panelAsientoDoble.getActualizar().setToolTipText("Exportar a pdf");
        panelAsientoDoble.getBarraBusqueda().add(btnAdd);
        panelAsientoDoble.getBarraBusqueda().add(btnCommit);
    //    panelAsientoDoble.getBarraBusqueda().add(btnRollback);
        panelAsientoDoble.getBarraBusqueda().addSeparator();
        panelAsientoDoble.getBarraBusqueda().add(lblDescri);
        panelAsientoDoble.getBarraBusqueda().add(txtDescri);
        panelAsientoDoble.getBarraBusqueda().addSeparator();
        panelAsientoDoble.getBarraBusqueda().add(lblDoc);
        panelAsientoDoble.getBarraBusqueda().add(txtDoc);
        Dimension dim = new Dimension(200, 20);
        txtDoc.setMaximumSize(dim);
        txtDescri.setMaximumSize(dim);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelAsientoDoble.getPanelFormulario().add(lblEmpresa, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelAsientoDoble.getPanelFormulario().add(txtEmpresa, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelAsientoDoble.getPanelFormulario().add(cbEmpresa, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelAsientoDoble.getPanelFormulario().add(lblAnno, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelAsientoDoble.getPanelFormulario().add(txtAnno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelAsientoDoble.getPanelFormulario().add(lblMes, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelAsientoDoble.getPanelFormulario().add(txtMes, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelAsientoDoble.getPanelFormulario().add(lblCuenta, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelAsientoDoble.getPanelFormulario().add(txtCuenta, gbc);


        txtEmpresa.setColumns(10);
        txtAnno.setColumns(10);
        txtMes.setColumns(10);

        Dimension d = new Dimension(300, 20);
        cbEmpresa.setPreferredSize(d);

        mdcbempresas.setData(LogicEmpresa.listaDeEmpresa());
        cbEmpresa.setModel(mdcbempresas);
        cbEmpresa.addActionListener(actionListener);
        txtEmpresa.addKeyListener(keyListener);
        //llenar la tabla
        // mdtabemp.setData(LogicAsientoDoble.listaCuentaDobleASiento("02", "2011", "12"));
        //panelAsientoDoble.getTablaDatos().setModel(mdtabemp);
        //panelAsientoDoble.getTablaDatos().setSelectionBackground(Color.PINK);
        //panelAsientoDoble.getTablaDatos().setCellSelectionEnabled(true);
        //panelAsientoDoble.getTablaDatos().setFocusable(true);

        txtEmpresa.addKeyListener(keyfiltrar);
        txtAnno.addKeyListener(keyfiltrar);
        txtDescri.addKeyListener(keyfiltrar);
        txtDoc.addKeyListener(keyfiltrar);
        txtEmpresa.addCaretListener(caretListener);
        txtAnno.addCaretListener(caretListener);
        txtDescri.addCaretListener(caretListener);
        txtDoc.addCaretListener(caretListener);


        btnAdd.addActionListener(actionListener);
        btnCommit.addActionListener(actionListener);
        btnRollback.addActionListener(actionListener);
        panelAsientoDoble.getWord().addActionListener(actionListener);
        panelAsientoDoble.getNuevo().addActionListener(actionListener);
        panelAsientoDoble.getActualizar().addActionListener(actionListener);
        panelAsientoDoble.getEliminar().addActionListener(actionListener);

    }
    CaretListener caretListener = new CaretListener() {

        @Override
        public void caretUpdate(CaretEvent e) {
            /*  if (txtEmpresa == e.getSource()) {
            filtrarTabla(panelAsientoDoble.getTablaDatos(), mdtabemp);
            } else if (txtAnno == e.getSource()) {
            filtrarTabla(panelAsientoDoble.getTablaDatos(), mdtabemp);
            } else */

            if (txtDescri == e.getSource()) {
                filtrarTabla(panelAsientoDoble.getTablaDatos(), mdtabemp);
            } else if (txtDoc == e.getSource()) {
                filtrarTabla(panelAsientoDoble.getTablaDatos(), mdtabemp);
            }
        }
    };
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cbEmpresa) {
                cbEmpresa.getEditor().setItem(cbEmpresa.getSelectedItem());
                txtEmpresa.setText(mdcbempresas.getElement(cbEmpresa.getSelectedIndex()).getCodigo());
                try {
                    String codemp = mdcbempresas.getElement(cbEmpresa.getSelectedIndex()).getCodigo();

                    //  mdemp.setData(LogicEmpleado.listaDeEmpleado(codemp,codaux));

                } catch (Exception ex) {
                }
            } else if (e.getSource().equals(btnAdd)) {
                BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
                String periodo = txtAnno.getText();
                String mes = txtMes.getText();
                String cuenta = txtCuenta.getText();
                try {
                    //  panelAsientoDoble.getTablaDatos().r
                    txtDescri.setText("");
                    txtDoc.setText("");
                    mdtabemp.setData(LogicAsientoDoble.listaCuentaDobleASiento(objEmpresa.getCodigo(), periodo, mes, cuenta));
                    //panelAsientoDoble.getTablaDatos().setModel(mdtabemp);
                    //panelAsientoDoble.getTablaDatos().setSelectionBackground(Color.PINK);
                    // panelAsientoDoble.getTablaDatos().setCellSelectionEnabled(true);
                    //   panelAsientoDoble.getTablaDatos().setFocusable(true);
                    //mdtabemp.fireTableDataChanged();
                    //panelAsientoDoble.getTablaDatos().updateUI();

                } catch (Exception ex) {
                    System.out.println(ex.getLocalizedMessage() + "as" + ex.getMessage());
                }
            } else if (e.getSource() == btnCommit) {
                BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
                String periodo = txtAnno.getText();
                String mes = txtMes.getText();
                String cuenta = txtCuenta.getText();
                try {
                    LogicAsientoDoble.listaSaldoActualizarBalance(objEmpresa.getCodigo(), periodo, mes, cuenta);
                } catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                }


            }

            if (e.getSource() == panelAsientoDoble.getActualizar()) {
                WorkerCuenta31PDF pdf = new WorkerCuenta31PDF();
                pdf.execute();
            } else if (e.getSource() == panelAsientoDoble.getEliminar()) {
                WorkerCuenta31PDF pdf = new WorkerCuenta31PDF();
                pdf.execute();
            } else if (e.getSource() == panelAsientoDoble.getNuevo()) {
                WorkerCuenta31Excel word = new WorkerCuenta31Excel();
                word.execute();
            } else if (e.getSource() == panelAsientoDoble.getWord()) {
                WorkerCuenta31DOC word = new WorkerCuenta31DOC();
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

    private void exportarCuenta31AEXCEL() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAnno.getText();
        String mes = txtMes.getText();
String cuenta = txtCuenta.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterPDF, "balance", "xlsx");
            LogicAsientoDoble.listaSaldoCuenta50(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, mes,cuenta, archivo, "xlsx");
        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }


    }

    private void exportarCuenta31APDF() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAnno.getText();

        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterPDF, "Cuenta31", "pdf");
            LogicCuenta31.listaSaldoCuenta31(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "pdf");
        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarCuenta31ADOC() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAnno.getText();

        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterDOC, "Cuenta31", "docx");
            LogicCuenta31.listaSaldoCuenta31(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "docx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void filtrarTabla(JTable tabla, ModelAsientoDoble model) {
        TableRowSorter<ModelAsientoDoble> sorter = new TableRowSorter<ModelAsientoDoble>(model);
        RowFilter<ModelAsientoDoble, Object> rf1 = null;
        RowFilter<ModelAsientoDoble, Object> rf2 = null;
        RowFilter<ModelAsientoDoble, Object> rf3 = null;
        RowFilter<ModelAsientoDoble, Object> rf4 = null;

        List<RowFilter<ModelAsientoDoble, Object>> rfs = new ArrayList<RowFilter<ModelAsientoDoble, Object>>(2);
        try {
            //rf1 = RowFilter.regexFilter(txtEmpresa.getText(), 1);
            rf2 = RowFilter.regexFilter(txtDescri.getText(), 1);
            rf3 = RowFilter.regexFilter(txtDoc.getText(), 2);
            // rf4 = RowFilter.regexFilter(txtAnno.getText(), 10);
            //rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf3);
            // rfs.add(rf4);

            RowFilter<ModelAsientoDoble, Object> af = RowFilter.andFilter(rfs);
            sorter.setRowFilter(af);
            tabla.setRowSorter(sorter);
        } catch (Exception ex) {
            return;
        }
    }
    KeyListener keyfiltrar = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (txtDescri == e.getSource()) {
                filtrarTabla(panelAsientoDoble.getTablaDatos(), mdtabemp);
            } else if (txtDoc == e.getSource()) {
                filtrarTabla(panelAsientoDoble.getTablaDatos(), mdtabemp);
            }
        }
    };

    /**
     * @return the txtValorMoninal
     */
    class WorkerCuenta31Excel extends SwingWorker<Boolean, Integer> {

        public WorkerCuenta31Excel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarCuenta31AEXCEL();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCuenta31PDF extends SwingWorker<Boolean, Integer> {

        public WorkerCuenta31PDF() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarCuenta31APDF();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCuenta31DOC extends SwingWorker<Boolean, Integer> {

        public WorkerCuenta31DOC() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarCuenta31ADOC();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }
    KeyListener keyListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getSource() == txtEmpresa) {
                String item = null;
                Iterator i = mdcbempresas.getData().iterator();
                String codigo = txtEmpresa.getText();
                while (i.hasNext()) {
                    BeanEmpresa b = (BeanEmpresa) i.next();
                    if (b.getCodigo().equals(codigo)) {
                        item = mdcbempresas.getElement(codigo).getDescripcion();
                        break;
                    }
                }
                if (item != null) {
                    cbEmpresa.setSelectedItem(item);
                } else {
                    cbEmpresa.getEditor().setItem(null);
                }
            }
        }
    };

    public FrmPanel getpanelAsientoDoble() {
        return panelAsientoDoble;
    }
}
