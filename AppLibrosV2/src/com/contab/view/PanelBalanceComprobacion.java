package com.contab.view;

import com.contab.bean.BeanCuenta;
import com.contab.bean.BeanEmpresa;
import com.contab.combobox.ComboModelEmpresas;


import com.contab.logic.LogicBalanceComprobacion;
import com.contab.logic.LogicCuenta;
import com.contab.logic.LogicEmpresa;
import com.contab.tablemodel.ModelTablaCuenta;
import com.contab.util.JComboBoxAutocomplete;

import com.contab.util.JFileChooserModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.openide.util.Exceptions;

public class PanelBalanceComprobacion extends JPanel {

    private FrmPanel panelBalanceComprobacion = new FrmPanel(ViewLogin.picture.getIconCuenta14(), "BALANCE DE COMPRBACION");
    private JLabel lblEmpresa = new JLabel("Empresa");
    private ComboModelEmpresas modelcbempresa = new ComboModelEmpresas();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private JLabel lbPeriodo = new JLabel("periodo");
    private JTextField txtAno = new JTextField();
    // private JPanel panelMolde = new JPanel();
    private JButton btnAdd = new JButton(ViewLogin.picture.getIconAdd());
    private JButton btnCommit = new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback = new JButton(ViewLogin.picture.getIconRollBack());
    private JButton btnImpotar = new JButton(ViewLogin.picture.getImpotar());
    private JFileChooserModel fileChooser = new JFileChooserModel();
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    FileNameExtensionFilter filterXLSI = new FileNameExtensionFilter("xls", "xls");
    FileNameExtensionFilter filterXLSG = new FileNameExtensionFilter("xlsx", "xls");
    FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("docx", "docx");
    private JTextField txtDescri = new JTextField();
    private JTextField txtDoc = new JTextField();
    private JLabel lblDescri = new JLabel("CUENTA");
    private JLabel lblDoc = new JLabel("CUENTA NUEVA");
    private ModelTablaCuenta mdtabemp = new ModelTablaCuenta(panelBalanceComprobacion.getTablaDatos());

    public PanelBalanceComprobacion() throws SQLException {

        initComponets();
        String item = null;
        item = modelcbempresa.getElement("01").getDescripcion();
        cbEmpresa.setSelectedItem(item);
    }

    private void initComponets() throws SQLException {
        txtAno.setText("2011");
        btnAdd.setToolTipText("Agregar nuevo registro");
        btnCommit.setToolTipText("Confirmar operaciones");
        btnRollback.setToolTipText("Actualizar");
        btnImpotar.setToolTipText("Importar");
        panelBalanceComprobacion.getNuevo().setIcon(ViewLogin.picture.getWord());
        panelBalanceComprobacion.getNuevo().setToolTipText("Expotar a Word");
        panelBalanceComprobacion.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panelBalanceComprobacion.getActualizar().setToolTipText("Exportar a Excel");
        panelBalanceComprobacion.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panelBalanceComprobacion.getEliminar().setToolTipText("Expotar a pdf");
        panelBalanceComprobacion.getGenerar().setIcon(ViewLogin.picture.getCompra());
        panelBalanceComprobacion.getGenerar().setToolTipText("BC_PCGEmpresarial");
        panelBalanceComprobacion.getBarraBusqueda().add(btnAdd);
        panelBalanceComprobacion.getBarraBusqueda().add(btnCommit);
        panelBalanceComprobacion.getBarraBusqueda().add(btnRollback);
        panelBalanceComprobacion.getBarraBusqueda().add(btnImpotar);
        panelBalanceComprobacion.getBarraBusqueda().addSeparator();
        panelBalanceComprobacion.getBarraBusqueda().add(lblDescri);
        panelBalanceComprobacion.getBarraBusqueda().add(txtDescri);
        panelBalanceComprobacion.getBarraBusqueda().addSeparator();
        panelBalanceComprobacion.getBarraBusqueda().add(lblDoc);
        panelBalanceComprobacion.getBarraBusqueda().add(txtDoc);
        Dimension dim = new Dimension(200, 20);
        txtDoc.setMaximumSize(dim);
        txtDescri.setMaximumSize(dim);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelBalanceComprobacion.getPanelFormulario().add(lblEmpresa, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelBalanceComprobacion.getPanelFormulario().add(cbEmpresa, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        panelBalanceComprobacion.getPanelFormulario().add(lbPeriodo, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        panelBalanceComprobacion.getPanelFormulario().add(txtAno, gbc);

        modelcbempresa.setData(LogicEmpresa.listaDeEmpresa());

        cbEmpresa.setModel(modelcbempresa);
        // cbEmpresa.setSelectedIndex(3);  //setSelectedIndex



        cbEmpresa.setEditable(false);
        cbEmpresa.setFocusable(true);
// setSelectedItem((index == -1) ? null : dataModel.getElementAt(index));

        mdtabemp.setData(LogicCuenta.listaCuentaActuales());
        panelBalanceComprobacion.getTablaDatos().setModel(mdtabemp);
        panelBalanceComprobacion.getTablaDatos().setSelectionBackground(Color.PINK);
        panelBalanceComprobacion.getTablaDatos().setCellSelectionEnabled(true);
        panelBalanceComprobacion.getTablaDatos().setFocusable(true);


        txtDescri.addKeyListener(keyfiltrar);
        txtDoc.addKeyListener(keyfiltrar);
        txtAno.addKeyListener(keyfiltrar);
        cbEmpresa.addKeyListener(keyfiltrar);


        btnAdd.addActionListener(actionListener);
        btnImpotar.addActionListener(actionListener);
        btnCommit.addActionListener(actionListener);
        btnRollback.addActionListener(actionListener);

        panelBalanceComprobacion.getNuevo().addActionListener(actionListener);
        panelBalanceComprobacion.getActualizar().addActionListener(actionListener);
        panelBalanceComprobacion.getEliminar().addActionListener(actionListener);
        panelBalanceComprobacion.getGenerar().addActionListener(actionListener);

    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAdd) {

                try {
                    txtDescri.setText("");
                    txtDoc.setText("");

                    filtrarTabla(panelBalanceComprobacion.getTablaDatos(), mdtabemp);
                    BeanCuenta cuenta31 = new BeanCuenta();
                    cuenta31.setOperacion("i");
                    cuenta31.setDenominacion("");
                    cuenta31.setDenominacion("");

                    mdtabemp.getData().add(cuenta31);
                    mdtabemp.fireTableDataChanged();
                    panelBalanceComprobacion.getTablaDatos().changeSelection(mdtabemp.getRowCount() - 1, 1, true, true);
                    panelBalanceComprobacion.getTablaDatos().editCellAt(mdtabemp.getRowCount() - 1, 1);
                } catch (Exception ex) {
                    System.out.println(ex.getLocalizedMessage() + "as" + ex.getMessage());
                }
            } else if (e.getSource() == btnCommit) {
                try {
                    LogicCuenta.mantenimientoCuenta(mdtabemp.getListaCommit());

                    // LogicCuenta.insertAllArray(mdtabemp.getListaCommit());
                    mdtabemp.getListaCommit().clear();
                    actualizarTablaCuenta();
                } catch (Exception ex) {
                    System.out.println(ex.getLocalizedMessage() + "as" + ex.getMessage());
                }


            } else if (e.getSource() == btnRollback) {
                actualizarTablaCuenta();
            } else if (e.getSource() == btnImpotar) {
                WorkerImpotarCuentaExcel xls = new WorkerImpotarCuentaExcel();
                xls.execute();
            } else if (e.getSource() == panelBalanceComprobacion.getActualizar()) {
                WorkerBalanceComprobacionExcel xls = new WorkerBalanceComprobacionExcel();
                xls.execute();
            } else if (e.getSource() == panelBalanceComprobacion.getEliminar()) {
                WorkerBalanceComprobacionPDF pdf = new WorkerBalanceComprobacionPDF();
                pdf.execute();
            } else if (e.getSource() == panelBalanceComprobacion.getNuevo()) {
                WorkerBalanceComprobacionDOC pdf = new WorkerBalanceComprobacionDOC();
                pdf.execute();
            } else if (e.getSource() == panelBalanceComprobacion.getGenerar()) {
                WorkerBalanceGenerar pdf = new WorkerBalanceGenerar();
                pdf.execute();
            }
        }
    };

    private void actualizarTablaCuenta() {
        mdtabemp.fireTableDataChanged();
        //  mdtabemp.fireTableStructureChanged();
        mdtabemp.setData(LogicCuenta.listaCuentaActuales());
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

    private void exportarBalanceComprobacionAEXCEL() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterXLS, "BalanceDeComprobacion", "xlsx");
            LogicBalanceComprobacion.listaBalanceComprobacion(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "xlsx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void impotarCuentaExcel() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterXLSI, "ImpotarCuenta", "xls");


            txtDescri.setText("");
            txtDoc.setText("");
            txtAno.setText("");
            filtrarTabla(panelBalanceComprobacion.getTablaDatos(), mdtabemp);

            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(archivo));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheet("Hoja1");
            Iterator rowIterator = sheet.rowIterator();

            int contar = 0;
            int contarC = 0;
            //    List cellDataList = new ArrayList();

            int filas = mdtabemp.getRowCount() - 2;

            while (rowIterator.hasNext()) {
                // fila
                // filtrarTabla(panelBalanceComprobacion.getTablaDatos(), mdtabemp);
                filas = filas + 1;
                BeanCuenta cuentaImportar = new BeanCuenta();
                HSSFRow hssfRow = (HSSFRow) rowIterator.next();
                //  if( hssfRow.getZeroHeight()){
                String numero = hssfRow.getCell(0).toString();

                String cuenta = hssfRow.getCell(1).toString();
                String nuevoNumero = hssfRow.getCell(2).toString();
                String nuevaCuenta = hssfRow.getCell(3).toString();
                String annoN = hssfRow.getCell(4).toString();
                int anno = 0;
                int type = hssfRow.getCell(4).getCellType();
                int typeCuenta = hssfRow.getCell(0).getCellType();
                int typeCuentaN = hssfRow.getCell(2).getCellType();



                String valorAnno = "";

                if (type == 1) {
                    valorAnno = annoN.toString();

                } else if (type == 3) {
                    //   System.out.println(hssfRow.getCell(0)+" holas");
                    double valorPx = hssfRow.getCell(4).getNumericCellValue();
                    valorAnno = Double.toString(valorPx);
                } else if (type == 0) {
                    //  System.out.println(hssfRow.getCell(0) + " holas");
                    double valorPx = hssfRow.getCell(4).getNumericCellValue();

                    BigDecimal big = new BigDecimal(valorPx);
                    big = big.setScale(0, RoundingMode.UP);

                    valorAnno = big.toString();
                }
                anno = Integer.parseInt(valorAnno);


                String valorCuenta = "";

                if (typeCuenta == 1) {
                    valorCuenta = numero.toString();
                } else if (typeCuenta == 3) {
                    double valorPx = hssfRow.getCell(0).getNumericCellValue();
                    valorCuenta = Double.toString(valorPx);
                } else if (typeCuenta == 0) {
                    double valorPx = hssfRow.getCell(0).getNumericCellValue();
                    BigDecimal big = new BigDecimal(valorPx);
                    big = big.setScale(0, RoundingMode.UP);
                    valorCuenta = big.toString();
                }

                String valorCuentaN = "";

                if (typeCuentaN == 1) {
                    valorCuentaN = nuevoNumero.toString();
                } else if (typeCuentaN == 3) {
                    double valorPx = hssfRow.getCell(2).getNumericCellValue();
                    valorCuentaN = Double.toString(valorPx);
                } else if (typeCuentaN == 0) {
                    double valorPx = hssfRow.getCell(2).getNumericCellValue();
                    BigDecimal big = new BigDecimal(valorPx);
                    big = big.setScale(0, RoundingMode.UP);
                    valorCuentaN = big.toString();
                }


                //  }
                //String valorP = "";
                cuentaImportar.setOperacion("i");
                cuentaImportar.setNumero(valorCuenta.trim());
                cuentaImportar.setCuenta(cuenta);
                cuentaImportar.setNuevoCuenta(nuevaCuenta.trim());
                cuentaImportar.setNuevonumero(valorCuentaN.trim());
                cuentaImportar.setAnno(anno);
                //  cuentaImportar.setCompania("03");

                mdtabemp.getData().add(cuentaImportar);
                mdtabemp.getListaCommit().add(cuentaImportar);
                //  mdtabemp.fireTableDataChanged();

                //  panelBalanceComprobacion.getTablaDatos().changeSelection(filas, 1, true, true);
            }
            //  filtrarTabla(panelBalanceComprobacion.getTablaDatos(), mdtabemp);
            mdtabemp.fireTableDataChanged();
            //mdtabemp.fireTableStructureChanged();
            panelBalanceComprobacion.getTablaDatos().changeSelection(filas, 1, true, true);
            panelBalanceComprobacion.getTablaDatos().editCellAt(filas, 1);

            //    mdtabemp.fireTableDataChanged();
            // panelBalanceComprobacion.getTablaDatos().changeSelection(mdtabemp.getRowCount() - 1, 1, true, true);
            //    panelBalanceComprobacion.getTablaDatos().editCellAt(mdtabemp.getRowCount() - 1, 1);

        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarBalanceComprobacionAPDF() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterPDF, "BalanceDeComprobacion", "pdf");
            LogicBalanceComprobacion.listaBalanceComprobacion(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "pdf");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarBalanceComprobacionADOC() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterTXT, "BalanceDeComprobacion", "docx");
            LogicBalanceComprobacion.listaBalanceComprobacion(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "docx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void generar_BC_PCGEmpresarial() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());
        String periodo = txtAno.getText();
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterXLSG, "balanceC", "xls");
            LogicBalanceComprobacion.listaBalanceComprobacionSunat(objEmpresa.getDescripcion(), objEmpresa.getRuc(), objEmpresa.getCodigo(), periodo, archivo, "xlsx");

        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void filtrarTabla(JTable tabla, ModelTablaCuenta model) {
        TableRowSorter<ModelTablaCuenta> sorter = new TableRowSorter<ModelTablaCuenta>(model);
        RowFilter<ModelTablaCuenta, Object> rf1 = null;
        RowFilter<ModelTablaCuenta, Object> rf2 = null;
        RowFilter<ModelTablaCuenta, Object> rf3 = null;
        RowFilter<ModelTablaCuenta, Object> rf4 = null;
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cbEmpresa.getModel())).getElement(cbEmpresa.getSelectedIndex());

        List<RowFilter<ModelTablaCuenta, Object>> rfs = new ArrayList<RowFilter<ModelTablaCuenta, Object>>(4);
        try {

            rf1 = RowFilter.regexFilter(txtDescri.getText(), 1);
            rf2 = RowFilter.regexFilter(txtDoc.getText(), 3);
            rf3 = RowFilter.regexFilter(txtAno.getText(), 5);
            rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf3);

            RowFilter<ModelTablaCuenta, Object> af = RowFilter.andFilter(rfs);
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
                filtrarTabla(panelBalanceComprobacion.getTablaDatos(), mdtabemp);
            } else if (txtDoc == e.getSource()) {
                filtrarTabla(panelBalanceComprobacion.getTablaDatos(), mdtabemp);
            } else if (cbEmpresa == e.getSource()) {  //filtrar por empresa
                //  filtrarTabla(panelCuenta31.getTablaDatos(), mdtabemp);
                filtrarTabla(panelBalanceComprobacion.getTablaDatos(), mdtabemp);
            } else if (txtAno == e.getSource()) {
                //filtrarTabla(panelCuenta31.getTablaDatos(), mdtabemp);
                filtrarTabla(panelBalanceComprobacion.getTablaDatos(), mdtabemp);

            }
        }
    };

    class WorkerBalanceComprobacionExcel extends SwingWorker<Boolean, Integer> {

        public WorkerBalanceComprobacionExcel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarBalanceComprobacionAEXCEL();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerImpotarCuentaExcel extends SwingWorker<Boolean, Integer> {

        public WorkerImpotarCuentaExcel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                impotarCuentaExcel();
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
                exportarBalanceComprobacionAPDF();
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
                exportarBalanceComprobacionADOC();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerBalanceGenerar extends SwingWorker<Boolean, Integer> {

        public WorkerBalanceGenerar() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                generar_BC_PCGEmpresarial();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    public FrmPanel getPanelBalanceComprobacion() {
        return panelBalanceComprobacion;
    }
}
