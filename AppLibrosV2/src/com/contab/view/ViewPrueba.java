/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

//import com.contab.bean.Bean12;
//import com.contab.combobox.ComboModel12;
//import com.contab.logic.Logic12;
import com.contab.bean.BeanBanco;
import com.contab.bean.BeanEmpleado;
import com.contab.bean.BeanKardex;
import com.contab.bean.BeanTabla03;
import com.contab.bean.BeanTabla12;
import com.contab.combobox.ComboModelBanco;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.logic.LogicCuentaBanco;
import com.contab.logic.LogicEmpresa;
import com.contab.logic.LogicTabla03;
import com.contab.logic.LogicTabla12;
import com.contab.logic.LogicTabla6;
import com.contab.tablemodel.ModelTabla03;
import com.contab.tablemodel.ModelTabla12;

import com.contab.util.JComboBoxAutocomplete;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.TableRowSorter;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class ViewPrueba extends  javax.swing.JFrame {

    FrmPanel panelMolde = new FrmPanel(ViewLogin.picture.getIconAdd32(), "SUNAT TABLA 03");
   
    private JLabel lblEmpresa = new JLabel("Empresa");
    private JTextField txtEmpresa = new JTextField();
    private JLabel lblCuenta = new JLabel("BANCO");
    private JTextField txtCuenta = new JTextField();
    private JLabel lblNumeroCuenta = new JLabel("Numero cuenta");
    private JTextField txtNumeroCuenta = new JTextField();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private ComboModelEmpresas mdcbempresas = new ComboModelEmpresas();
    //
    private JComboBoxAutocomplete cbCuentaBanco = new JComboBoxAutocomplete();
    private ComboModelBanco mdcCuentaBanco = new ComboModelBanco();
    //private ComboModel12 mdemp = new ComboModel12();
    private ModelTabla03 mdtabemp = new ModelTabla03(panelMolde.getTablaDatos());
    private JToolBar barra = new JToolBar();
    private JButton btnAdd = new JButton(ViewLogin.picture.getIconAdd());
    private JButton btnCommit = new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback = new JButton(ViewLogin.picture.getIconRollBack());
    private JTable data;

    public ViewPrueba() throws SQLException  {
        //super(parent, modal);
        initComponents();
    }

    private void initComponents() throws SQLException {

        //  panelMolde.get
      //  btnAdd.setToolTipText("Agregar nuevo registro");
        //btnCommit.setToolTipText("Confirmar operaciones");
        //btnRollback.setToolTipText("Actualizar");

       panelMolde.getBarraBusqueda().add(btnAdd);
        panelMolde.getBarraBusqueda().add(btnCommit);
        panelMolde.getBarraBusqueda().add(btnRollback);

        //panelMolde.getBarraBusqueda().add(ViewLogin.picture.getIconActualizar());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(lblEmpresa, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(txtEmpresa, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        mdcbempresas.setData(LogicEmpresa.listaDeEmpresa());
        cbEmpresa.setModel(mdcbempresas);
        panelMolde.getPanelFormulario().add(cbEmpresa, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(lblCuenta, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(txtCuenta, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        mdcCuentaBanco.setData(LogicCuentaBanco.listaDeCuentaBanco());
        cbCuentaBanco.setModel(mdcCuentaBanco);
        panelMolde.getPanelFormulario().add(cbCuentaBanco, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(lblNumeroCuenta, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(txtNumeroCuenta, gbc);

        txtEmpresa.setColumns(10);

        txtCuenta.setColumns(10);
        txtNumeroCuenta.setColumns(10);

        Dimension d = new Dimension(300, 20);
        mdtabemp.setData(LogicTabla03.listaTabla03());
        panelMolde.getTablaDatos().setModel(mdtabemp);
        panelMolde.getTablaDatos().setSelectionBackground(Color.PINK);
        panelMolde.getTablaDatos().setCellSelectionEnabled(true);

        panelMolde.getTablaDatos().setCellSelectionEnabled(true);
        panelMolde.getTablaDatos().setFocusable(true);
        panelMolde.getTablaDatos().setSelectionBackground(Color.BLUE);
        txtEmpresa.addKeyListener(keyfiltrar);
        txtCuenta.addKeyListener(keyfiltrar);
        txtNumeroCuenta.addKeyListener(keyfiltrar);
        ///
        txtEmpresa.addCaretListener(caretListener);
        txtCuenta.addCaretListener(caretListener);
        //  txtCuenta.addCaretListener(caretListener);
        // txtEmpresa.addCaretListener(caretListener);
        //      cbCueAux.addActionListener(actionListener);                
        btnAdd.addActionListener(actionListener);
        btnCommit.addActionListener(actionListener);
        btnRollback.addActionListener(actionListener);

        cbEmpresa.addActionListener(actionListener);

        cbCuentaBanco.addActionListener(actionListener);


    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new ViewPrueba().setVisible(true);
                } catch (SQLException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        });
    }
    CaretListener caretListener = new CaretListener() {

        @Override
        public void caretUpdate(CaretEvent e) {
            if (txtEmpresa == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(), mdtabemp);
            } else if (txtCuenta == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(), mdtabemp);
                // }else if (txtCueAux == e.getSource()) {
                // filtrarTabla(panelMolde.getTablaDatos(),mdtabemp);
            }
        }
    };
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAdd) {

                try {
                    BeanTabla03 emp = new BeanTabla03();
                    emp.setOperacion("i");
                    emp.setCodigoSunat("");
                    emp.setNumeroCuenta("");

                    mdtabemp.getData().add(emp);
                    mdtabemp.fireTableDataChanged();
                    panelMolde.getTablaDatos().changeSelection(mdtabemp.getRowCount() - 1, 1, true, true);
                    panelMolde.getTablaDatos().editCellAt(mdtabemp.getRowCount() - 1, 1);
                } catch (Exception ex) {
                }
            } else if (e.getSource() == btnCommit) {
                try {
                    LogicTabla03.mantenimientoTabla03(mdtabemp.getListaCommit());


                } catch (SQLException ex) {
                    Exceptions.printStackTrace(ex);
                }
                mdtabemp.getListaCommit().clear();
                actualizarTabla03();
            } else if (e.getSource() == btnRollback) {
                actualizarTabla03();
            } else if (e.getSource() == cbEmpresa) {
                cbEmpresa.getEditor().setItem(cbEmpresa.getSelectedItem());
                txtEmpresa.setText(mdcbempresas.getElement(cbEmpresa.getSelectedIndex()).getCodigo());
                try {
                    //String codemp=mdcbempresas.getElement(cbEmpresa.getSelectedIndex()).getCodigo();
                    //String codaux=mdcbcodaux.getElement(cbCodAux.getSelectedIndex()).getCodigo();
                    // mdemp.setData(LogicEmpleado.listaDeEmpleado(codemp,codaux));
                    mdcbempresas.setData(LogicEmpresa.listaDeEmpresa());
                    cbEmpresa.setModel(mdcbempresas);
                    cbEmpresa.updateUI();
                } catch (Exception ex) {
                }
            } else if (e.getSource() == cbCuentaBanco) {  //lista de banco
                cbCuentaBanco.getEditor().setItem(cbCuentaBanco.getSelectedItem());
                txtCuenta.setText(mdcCuentaBanco.getElement(cbCuentaBanco.getSelectedIndex()).getLlave());
                try {
                    //mdcCuentaBanco
                    //String codemp=mdcbempresas.getElement(cbEmpresa.getSelectedIndex()).getCodigo();
                    //String codaux=mdcbcodaux.getElement(cbCodAux.getSelectedIndex()).getCodigo();
                    // mdemp.setData(LogicEmpleado.listaDeEmpleado(codemp,codaux));
                    mdcCuentaBanco.setData(LogicCuentaBanco.listaDeCuentaBanco());
                    cbCuentaBanco.setModel(mdcCuentaBanco);
                    cbCuentaBanco.updateUI();
                } catch (Exception ex) {
                }
            }

        }
    };
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
                filtrarTabla(panelMolde.getTablaDatos(), mdtabemp);
            } else if (txtCuenta == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(), mdtabemp);
            } else if (txtNumeroCuenta == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(), mdtabemp);
            }
        }
    };

    private void filtrarTabla(JTable tabla, ModelTabla03 model) {
        TableRowSorter<ModelTabla03> sorter = new TableRowSorter<ModelTabla03>(model);
        RowFilter<ModelTabla03, Object> rf1 = null;
        RowFilter<ModelTabla03, Object> rf2 = null;

        RowFilter<ModelTabla03, Object> rf4 = null;
        List<RowFilter<ModelTabla03, Object>> rfs = new ArrayList<RowFilter<ModelTabla03, Object>>(3);
        try {
            rf1 = RowFilter.regexFilter(txtEmpresa.getText(), 1);
            rf2 = RowFilter.regexFilter(txtCuenta.getText(), 2);
            rf4 = RowFilter.regexFilter(txtNumeroCuenta.getText(), 4);
            rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf4);
            RowFilter<ModelTabla03, Object> af = RowFilter.andFilter(rfs);
            sorter.setRowFilter(af);
            tabla.setRowSorter(sorter);
        } catch (Exception ex) {
            return;
        }
    }

    private void actualizarTabla03() {
        mdtabemp.setData(LogicTabla03.listaTabla03());
        mdtabemp.fireTableDataChanged();
    }

    public FrmPanel getPanelMolde() {
        return panelMolde;
    }
}
