/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

import com.contab.bean.BeanCodigoAuxiliar;
import com.contab.bean.BeanCuenta50;
import com.contab.bean.BeanEmpleado;
import com.contab.bean.BeanEmpresa;
import com.contab.combobox.ComboModelCodAux;
import com.contab.combobox.ComboModelEmpleado;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.logic.LogicCuenta50;
import com.contab.logic.LogicEmpleado;
import com.contab.logic.LogicEmpresa;
import com.contab.tablemodel.ModelCuenta50;
import com.contab.tablemodel.ModelTablaEmpleado;
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
public class ViewCuenta50 {

    private FrmPanel panelCuenta50 = new FrmPanel(ViewLogin.picture.getIconCuenta14(), "ACCIONISTAS");
    private JLabel lblEmpresa = new JLabel("EMPRESA");
    private JTextField txtEmpresa = new JTextField();
    private JLabel lblAnno = new JLabel("AÑO");
    private JTextField txtAnno = new JTextField();
    private JLabel lblValorMoninal = new JLabel("VALOR NOMINAL POR ACCIÓN O PARTICIPACIÓN SOCIAL");
    private JTextField txtValorMoninal = new JTextField();
    private JLabel lblNumeroAccionesS = new JLabel("NÚMERO DE ACCIONES O PARTICIPACIONES SOCIALES SUSCRITAS");
    private JTextField txtNumeroAccionesS = new JTextField();
    private JLabel lblNumeroAccionesP = new JLabel("NÚMERO DE ACCIONES O PARTICIPACIONES SOCIALES PAGADAS  ");
    private JTextField txtNumeroAccionesP = new JTextField();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private ComboModelEmpresas mdcbempresas = new ComboModelEmpresas();
    private ComboModelEmpleado mdemp = new ComboModelEmpleado();
    private JButton btnAdd = new JButton(ViewLogin.picture.getIconAdd());
    private JButton btnCommit = new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback = new JButton(ViewLogin.picture.getIconRollBack());
    private JLabel lblDescri = new JLabel("Descripcion  ");
    private JLabel lblDoc = new JLabel("Documento  ");
    private JTextField txtDescri = new JTextField();
    private JTextField txtDoc = new JTextField();
    private JFileChooserModel fileChooser = new JFileChooserModel();
    private ModelCuenta50 mdtabemp = new ModelCuenta50(panelCuenta50.getTablaDatos());
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    FileNameExtensionFilter filterDOC = new FileNameExtensionFilter("docx", "docx");
    FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("txt", "txt");

    public ViewCuenta50() throws SQLException {
        initComponents();
    }

    private void initComponents() throws SQLException {
        btnAdd.setToolTipText("Agregar nuevo registro");
        btnCommit.setToolTipText("Confirmar operaciones");
        btnRollback.setToolTipText("Actualizar");
        panelCuenta50.getWord().setIcon(ViewLogin.picture.getWord());
        panelCuenta50.getWord().setToolTipText("Exportar a Word");
        panelCuenta50.getNuevo().setIcon(ViewLogin.picture.getIconExcel());
        panelCuenta50.getNuevo().setToolTipText("Exportar a Excel");
        panelCuenta50.getActualizar().setIcon(ViewLogin.picture.getIconPDF32());
        panelCuenta50.getActualizar().setToolTipText("Exportar a pdf");
        panelCuenta50.getBarraBusqueda().add(btnAdd);
        panelCuenta50.getBarraBusqueda().add(btnCommit);
        panelCuenta50.getBarraBusqueda().add(btnRollback);
        panelCuenta50.getBarraBusqueda().addSeparator();
        panelCuenta50.getBarraBusqueda().add(lblDescri);
        panelCuenta50.getBarraBusqueda().add(txtDescri);
        panelCuenta50.getBarraBusqueda().addSeparator();
        panelCuenta50.getBarraBusqueda().add(lblDoc);
        panelCuenta50.getBarraBusqueda().add(txtDoc);
        Dimension dim = new Dimension(200, 20);
        txtDoc.setMaximumSize(dim);
        txtDescri.setMaximumSize(dim);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta50.getPanelFormulario().add(lblEmpresa, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta50.getPanelFormulario().add(txtEmpresa, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta50.getPanelFormulario().add(cbEmpresa, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta50.getPanelFormulario().add(lblAnno, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta50.getPanelFormulario().add(txtAnno, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta50.getPanelFormulario().add(lblValorMoninal, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta50.getPanelFormulario().add(txtValorMoninal, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta50.getPanelFormulario().add(lblNumeroAccionesS, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta50.getPanelFormulario().add(txtNumeroAccionesS, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta50.getPanelFormulario().add(lblNumeroAccionesP, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelCuenta50.getPanelFormulario().add(txtNumeroAccionesP, gbc);


        txtEmpresa.setColumns(10);
        txtAnno.setColumns(10);
        txtValorMoninal.setColumns(10);
        txtNumeroAccionesS.setColumns(10);
        txtNumeroAccionesP.setColumns(10);


        Dimension d = new Dimension(300, 20);
        cbEmpresa.setPreferredSize(d);

        mdcbempresas.setData(LogicEmpresa.listaDeEmpresa());
        cbEmpresa.setModel(mdcbempresas);
        cbEmpresa.addActionListener(actionListener);
        txtEmpresa.addKeyListener(keyListener);
        //llenar la tabla
        mdtabemp.setData(LogicCuenta50.listaCuenta50());
        panelCuenta50.getTablaDatos().setModel(mdtabemp);
        panelCuenta50.getTablaDatos().setSelectionBackground(Color.PINK);
        panelCuenta50.getTablaDatos().setCellSelectionEnabled(true);
        panelCuenta50.getTablaDatos().setFocusable(true);


        txtEmpresa.addKeyListener(keyfiltrar);
        txtDescri.addKeyListener(keyfiltrar);
        txtDoc.addKeyListener(keyfiltrar);
        txtAnno.addKeyListener(keyfiltrar);
        txtAnno.addCaretListener(caretListener);


        btnAdd.addActionListener(actionListener);
        btnCommit.addActionListener(actionListener);
        btnRollback.addActionListener(actionListener);
        panelCuenta50.getWord().addActionListener(actionListener);
        panelCuenta50.getNuevo().addActionListener(actionListener);
        panelCuenta50.getActualizar().addActionListener(actionListener);
        panelCuenta50.getEliminar().addActionListener(actionListener);
    }
    CaretListener caretListener = new CaretListener() {

        @Override
        public void caretUpdate(CaretEvent e) {
           if (txtEmpresa == e.getSource()) {
                filtrarTabla(panelCuenta50.getTablaDatos(), mdtabemp);
            } else if (txtAnno == e.getSource()) {
                filtrarTabla(panelCuenta50.getTablaDatos(), mdtabemp);
            } else if (txtDescri == e.getSource()) {
                filtrarTabla(panelCuenta50.getTablaDatos(), mdtabemp);
            } else if (txtDoc == e.getSource()) {
                filtrarTabla(panelCuenta50.getTablaDatos(), mdtabemp);
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
                    filtrarTabla(panelCuenta50.getTablaDatos(), mdtabemp);
                    BeanCuenta50 emp = new BeanCuenta50();
                    emp.setOperacion("i");
                    emp.setDenominacion("");
                    emp.setTipoAccion("");

                    mdtabemp.getData().add(emp);
                    mdtabemp.fireTableDataChanged();
                    panelCuenta50.getTablaDatos().changeSelection(mdtabemp.getRowCount() - 1, 1, true, true);
                    panelCuenta50.getTablaDatos().editCellAt(mdtabemp.getRowCount() - 1, 1);
                } catch (Exception ex) {
                }
            } else if (e.getSource() == btnCommit) {
                try {
                    LogicCuenta50.mantenimientoCuenta50(mdtabemp.getListaCommit());
                    mdtabemp.getListaCommit().clear();
                    actualizarTablaCuenta50();
                } catch (SQLException ex) {
                    Exceptions.printStackTrace(ex);
                }
            } else if (e.getSource() == btnRollback) {
                actualizarTablaCuenta50();
            }
            if (e.getSource() == panelCuenta50.getActualizar()) {
                WorkerCuenta50PDF pdf = new WorkerCuenta50PDF();
                pdf.execute();
            } else if (e.getSource() == panelCuenta50.getEliminar()) {
                WorkerCuenta50PDF pdf = new WorkerCuenta50PDF();
                pdf.execute();
            } else if (e.getSource() == panelCuenta50.getNuevo()) {
                WorkerCuenta50Excel word = new WorkerCuenta50Excel();
                word.execute();
            } else if (e.getSource() == panelCuenta50.getWord()) {
                WorkerCuenta50DOC word = new WorkerCuenta50DOC();
                word.execute();
            }

        }
    };

    private void actualizarTablaCuenta50() {
        mdtabemp.setData(LogicCuenta50.listaCuenta50());
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

    private void exportarCuenta50AEXCEL() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());

        String periodo = txtAnno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString(); txtValorMoninal  txtNumeroAccionesS txtNumeroAccionesP
        try {
            File archivo = seleccionarArchivo(filterXLS, "cuenta50", "xlsx");
            LogicCuenta50.listaSaldoCuenta50(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, txtValorMoninal.getText(), txtNumeroAccionesS.getText(), txtNumeroAccionesP.getText(), archivo, "xlsx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarCuenta50APDF() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAnno.getText();

        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterPDF, "cuenta50", "pdf");
            LogicCuenta50.listaSaldoCuenta50(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, txtValorMoninal.getText(), txtNumeroAccionesS.getText(), txtNumeroAccionesP.getText(), archivo, "pdf");
        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarCuenta50ADOC() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAnno.getText();

        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterDOC, "cuenta50", "docx");
            LogicCuenta50.listaSaldoCuenta50(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, txtValorMoninal.getText(), txtNumeroAccionesS.getText(), txtNumeroAccionesP.getText(), archivo, "docx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void filtrarTabla(JTable tabla, ModelCuenta50 model) {
        TableRowSorter<ModelCuenta50> sorter = new TableRowSorter<ModelCuenta50>(model);
        RowFilter<ModelCuenta50, Object> rf1 = null;
        RowFilter<ModelCuenta50, Object> rf2 = null;
        RowFilter<ModelCuenta50, Object> rf3 = null;
        RowFilter<ModelCuenta50, Object> rf4 = null;

        List<RowFilter<ModelCuenta50, Object>> rfs = new ArrayList<RowFilter<ModelCuenta50, Object>>(4);
        try {
            rf1 = RowFilter.regexFilter(txtEmpresa.getText(), 1);
            rf2 = RowFilter.regexFilter(txtDescri.getText(), 2);
            rf3 = RowFilter.regexFilter(txtDoc.getText(), 3);
            rf4 = RowFilter.regexFilter(txtAnno.getText(), 7);
            rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf3);
            rfs.add(rf4);

            RowFilter<ModelCuenta50, Object> af = RowFilter.andFilter(rfs);
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
                filtrarTabla(panelCuenta50.getTablaDatos(), mdtabemp);
            } else if (txtAnno == e.getSource()) {
                filtrarTabla(panelCuenta50.getTablaDatos(), mdtabemp);
            } else if (txtDescri == e.getSource()) {
                filtrarTabla(panelCuenta50.getTablaDatos(), mdtabemp);
            } else if (txtDoc == e.getSource()) {
                filtrarTabla(panelCuenta50.getTablaDatos(), mdtabemp);
            }
        }
    };

    /**
     * @return the txtValorMoninal
     */
    class WorkerCuenta50Excel extends SwingWorker<Boolean, Integer> {

        public WorkerCuenta50Excel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarCuenta50AEXCEL();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCuenta50PDF extends SwingWorker<Boolean, Integer> {

        public WorkerCuenta50PDF() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarCuenta50APDF();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCuenta50DOC extends SwingWorker<Boolean, Integer> {

        public WorkerCuenta50DOC() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarCuenta50ADOC();
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

    public FrmPanel getpanelCuenta50() {
        return panelCuenta50;
    }
}
