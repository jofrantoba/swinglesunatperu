/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

import com.contab.bean.BeanCuenta31;
import com.contab.bean.BeanEmpresa;
import com.contab.combobox.ComboModelEmpleado;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.logic.LogicCuenta31;
import com.contab.logic.LogicEmpresa;
import com.contab.tablemodel.ModelCuenta31;
import com.contab.util.JComboBoxAutocomplete;
import com.contab.util.JFileChooserModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.math.BigDecimal;
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
public class ViewCuenta31 {

    private FrmPanel panelCuenta31 = new FrmPanel(ViewLogin.picture.getIconCuenta14(), "ACCIONISTAS");
    private JLabel lblEmpresa = new JLabel("EMPRESA");
    private JTextField txtEmpresa = new JTextField();
    private JLabel lblAnno = new JLabel("AÑO");
    private JTextField txtAnno = new JTextField();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private ComboModelEmpresas mdcbempresas = new ComboModelEmpresas();
    private ComboModelEmpleado mdemp = new ComboModelEmpleado();
    private JButton btnAdd = new JButton(ViewLogin.picture.getIconAdd());
    private JButton btnCommit = new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback = new JButton(ViewLogin.picture.getIconRollBack());
    private JLabel lblDescri = new JLabel("Denominación   ");
    private JLabel lblDoc = new JLabel("Documento  ");
    private JTextField txtDescri = new JTextField();
    private JTextField txtDoc = new JTextField();
    private JFileChooserModel fileChooser = new JFileChooserModel();
    private ModelCuenta31 mdtabemp = new ModelCuenta31(panelCuenta31.getTablaDatos());
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    FileNameExtensionFilter filterDOC = new FileNameExtensionFilter("docx", "docx");
    FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("txt", "txt");

    public ViewCuenta31() throws SQLException {
        initComponents();
    }

    private void initComponents() throws SQLException {
        btnAdd.setToolTipText("Agregar nuevo registro");
        btnCommit.setToolTipText("Confirmar operaciones");
        btnRollback.setToolTipText("Actualizar");
        panelCuenta31.getWord().setIcon(ViewLogin.picture.getWord());
        panelCuenta31.getWord().setToolTipText("Exportar a Word");
        panelCuenta31.getNuevo().setIcon(ViewLogin.picture.getIconExcel());
        panelCuenta31.getNuevo().setToolTipText("Exportar a Excel");
        panelCuenta31.getActualizar().setIcon(ViewLogin.picture.getIconPDF32());
        panelCuenta31.getActualizar().setToolTipText("Exportar a pdf");
        panelCuenta31.getBarraBusqueda().add(btnAdd);
        panelCuenta31.getBarraBusqueda().add(btnCommit);
        panelCuenta31.getBarraBusqueda().add(btnRollback);
        panelCuenta31.getBarraBusqueda().addSeparator();
        panelCuenta31.getBarraBusqueda().add(lblDescri);
        panelCuenta31.getBarraBusqueda().add(txtDescri);
        panelCuenta31.getBarraBusqueda().addSeparator();
        panelCuenta31.getBarraBusqueda().add(lblDoc);
        panelCuenta31.getBarraBusqueda().add(txtDoc);
        Dimension dim = new Dimension(200, 20);
        txtDoc.setMaximumSize(dim);
        txtDescri.setMaximumSize(dim);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta31.getPanelFormulario().add(lblEmpresa, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta31.getPanelFormulario().add(txtEmpresa, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta31.getPanelFormulario().add(cbEmpresa, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta31.getPanelFormulario().add(lblAnno, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta31.getPanelFormulario().add(txtAnno, gbc);



        txtEmpresa.setColumns(10);
        txtAnno.setColumns(10);



        Dimension d = new Dimension(300, 20);
        cbEmpresa.setPreferredSize(d);

        mdcbempresas.setData(LogicEmpresa.listaDeEmpresa());
        cbEmpresa.setModel(mdcbempresas);
        cbEmpresa.addActionListener(actionListener);
        txtEmpresa.addKeyListener(keyListener);
        //llenar la tabla
        mdtabemp.setData(LogicCuenta31.listaCuenta31());
        panelCuenta31.getTablaDatos().setModel(mdtabemp);
        panelCuenta31.getTablaDatos().setSelectionBackground(Color.PINK);
        panelCuenta31.getTablaDatos().setCellSelectionEnabled(true);
        panelCuenta31.getTablaDatos().setFocusable(true);


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
        panelCuenta31.getWord().addActionListener(actionListener);
        panelCuenta31.getNuevo().addActionListener(actionListener);
        panelCuenta31.getActualizar().addActionListener(actionListener);
        panelCuenta31.getEliminar().addActionListener(actionListener);

    }
    CaretListener caretListener = new CaretListener() {

        @Override
        public void caretUpdate(CaretEvent e) {
            if (txtEmpresa == e.getSource()) {
                filtrarTabla(panelCuenta31.getTablaDatos(), mdtabemp);
            } else if (txtAnno == e.getSource()) {
                filtrarTabla(panelCuenta31.getTablaDatos(), mdtabemp);
            } else if (txtDescri == e.getSource()) {
                filtrarTabla(panelCuenta31.getTablaDatos(), mdtabemp);
            } else if (txtDoc == e.getSource()) {
                filtrarTabla(panelCuenta31.getTablaDatos(), mdtabemp);
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
            } else if (e.getSource() == btnAdd) {

                try {

                    txtEmpresa.setText("");
                    txtDescri.setText("");
                    txtDoc.setText("");
                    txtAnno.setText("");
                    filtrarTabla(panelCuenta31.getTablaDatos(), mdtabemp);
                    BeanCuenta31 cuenta31 = new BeanCuenta31();
                    cuenta31.setIdCuenta31(1);
                    cuenta31.setOperacion("i");
                    cuenta31.setDenominacion("");
                    cuenta31.setIdCompania("");
                    cuenta31.setTabla2("");
                    cuenta31.setDenominacion("");
                    cuenta31.setDenominacionrazon("");
                    cuenta31.setAnno("");
                    cuenta31.setCantidad(0);
                    cuenta31.setValorNominal(BigDecimal.ZERO);
                    cuenta31.setProvisionTotal(BigDecimal.ZERO);
                    cuenta31.setTotalNeto(BigDecimal.ZERO);
                    mdtabemp.getData().add(cuenta31);
                    mdtabemp.fireTableDataChanged();
                    panelCuenta31.getTablaDatos().changeSelection(mdtabemp.getRowCount() - 1, 1, true, true);
                    panelCuenta31.getTablaDatos().editCellAt(mdtabemp.getRowCount() - 1, 1);
                } catch (Exception ex) {
                    System.out.println(ex.getLocalizedMessage() + "as" + ex.getMessage());
                }
            } else if (e.getSource() == btnCommit) {
                try {
                    LogicCuenta31.mantenimientoCuenta31(mdtabemp.getListaCommit());
                    mdtabemp.getListaCommit().clear();
                    actualizarTablaCuenta31();
                } catch (SQLException ex) {
                    Exceptions.printStackTrace(ex);
                }
            } else if (e.getSource() == btnRollback) {
                actualizarTablaCuenta31();
            }
            if (e.getSource() == panelCuenta31.getActualizar()) {
                WorkerCuenta31PDF pdf = new WorkerCuenta31PDF();
                pdf.execute();
            } else if (e.getSource() == panelCuenta31.getEliminar()) {
                WorkerCuenta31PDF pdf = new WorkerCuenta31PDF();
                pdf.execute();
            } else if (e.getSource() == panelCuenta31.getNuevo()) {
                WorkerCuenta31Excel word = new WorkerCuenta31Excel();
                word.execute();
            } else if (e.getSource() == panelCuenta31.getWord()) {
                WorkerCuenta31DOC word = new WorkerCuenta31DOC();
                word.execute();
            }

        }
    };

    private void actualizarTablaCuenta31() {
        mdtabemp.setData(LogicCuenta31.listaCuenta31());
        mdtabemp.fireTableDataChanged();
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

    private void exportarCuenta31AEXCEL() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());

        String periodo = txtAnno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString(); txtValorMoninal  txtNumeroAccionesS txtNumeroAccionesP
        try {
            File archivo = seleccionarArchivo(filterXLS, "Cuenta31", "xlsx");
            LogicCuenta31.listaSaldoCuenta31(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "xlsx");

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

    private void filtrarTabla(JTable tabla, ModelCuenta31 model) {
        TableRowSorter<ModelCuenta31> sorter = new TableRowSorter<ModelCuenta31>(model);
        RowFilter<ModelCuenta31, Object> rf1 = null;
        RowFilter<ModelCuenta31, Object> rf2 = null;
        RowFilter<ModelCuenta31, Object> rf3 = null;
        RowFilter<ModelCuenta31, Object> rf4 = null;

        List<RowFilter<ModelCuenta31, Object>> rfs = new ArrayList<RowFilter<ModelCuenta31, Object>>(4);
        try {
            rf1 = RowFilter.regexFilter(txtEmpresa.getText(), 1);
            rf2 = RowFilter.regexFilter(txtDescri.getText(), 2);
            rf3 = RowFilter.regexFilter(txtDoc.getText(), 3);
            rf4 = RowFilter.regexFilter(txtAnno.getText(), 10);
            rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf3);
            rfs.add(rf4);

            RowFilter<ModelCuenta31, Object> af = RowFilter.andFilter(rfs);
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
            if (txtEmpresa == e.getSource()) {
                filtrarTabla(panelCuenta31.getTablaDatos(), mdtabemp);
            } else if (txtAnno == e.getSource()) {
                filtrarTabla(panelCuenta31.getTablaDatos(), mdtabemp);
            } else if (txtDescri == e.getSource()) {
                filtrarTabla(panelCuenta31.getTablaDatos(), mdtabemp);
            } else if (txtDoc == e.getSource()) {
                filtrarTabla(panelCuenta31.getTablaDatos(), mdtabemp);
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

    public FrmPanel getpanelCuenta31() {
        return panelCuenta31;
    }
}
