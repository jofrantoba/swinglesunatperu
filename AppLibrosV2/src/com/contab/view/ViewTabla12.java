/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

//import com.contab.bean.Bean12;
//import com.contab.combobox.ComboModel12;
//import com.contab.logic.Logic12;
import com.contab.bean.BeanTabla12;
import com.contab.logic.LogicTabla12;
import com.contab.tablemodel.ModelTabla12;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import java.awt.event.ItemEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class ViewTabla12 {

    FrmPanel panelMolde = new FrmPanel(ViewLogin.picture.getIconAdd32(), "SUNAT TABLA 12");
    //private ComboModel12 mdemp = new ComboModel12();
    private ModelTabla12 mdtabemp = new ModelTabla12(panelMolde.getTablaDatos());
    private JButton btnAdd = new JButton(ViewLogin.picture.getIconAdd());
    private JButton btnCommit = new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback = new JButton(ViewLogin.picture.getIconRollBack());
    private JLabel lblCodMov = new JLabel("codigo Movimiento");
    private JTextField txtCodMOv = new JTextField();
    private JLabel lblMotivo = new JLabel("Motivo");
    private JTextField txtMotivo = new JTextField();

    public ViewTabla12() throws SQLException {
        initComponents();
    }

    private void initComponents() throws SQLException {
        btnAdd.setToolTipText("Agregar nuevo registro");
        btnCommit.setToolTipText("Confirmar operaciones");
        btnRollback.setToolTipText("Actualizar");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(lblCodMov, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(txtCodMOv, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(lblMotivo, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(txtMotivo, gbc);

        txtCodMOv.setColumns(10);
        txtMotivo.setColumns(10);
        panelMolde.getBarraBusqueda().add(btnAdd);
        panelMolde.getBarraBusqueda().add(btnCommit);
        panelMolde.getBarraBusqueda().add(btnRollback);
        mdtabemp.setData(LogicTabla12.listaTabla12());
        panelMolde.getTablaDatos().setModel(mdtabemp);
        panelMolde.getTablaDatos().setSelectionBackground(Color.PINK);
        panelMolde.getTablaDatos().setCellSelectionEnabled(true);

        txtMotivo.addKeyListener(keyfiltrar);
        txtCodMOv.addKeyListener(keyfiltrar);
        btnAdd.addActionListener(actionListener);
        btnCommit.addActionListener(actionListener);
        btnRollback.addActionListener(actionListener);



    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAdd) {

                try {
                    BeanTabla12 emp = new BeanTabla12();
                    emp.setOperacion("i");
                    emp.setCodigoSunat("");
                    emp.setDescripcion("");
                    mdtabemp.getData().add(emp);
                    mdtabemp.fireTableDataChanged();
                    panelMolde.getTablaDatos().changeSelection(mdtabemp.getRowCount() - 1, 1, true, true);
                    panelMolde.getTablaDatos().editCellAt(mdtabemp.getRowCount() - 1, 1);
                } catch (Exception ex) {
                }
            } else if (e.getSource() == btnCommit) {
                try {
                    LogicTabla12.mantenimientoTabla12(mdtabemp.getListaCommit());
                } catch (SQLException ex) {
                    Exceptions.printStackTrace(ex);
                }
                mdtabemp.getListaCommit().clear();
                actualizarTabla12();
            } else if (e.getSource() == btnRollback) {
                actualizarTabla12();
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
            if (txtCodMOv == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(), mdtabemp);
            } else if (txtMotivo == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(), mdtabemp);
            }
        }
    };

    private void filtrarTabla(JTable tabla, ModelTabla12 model) {
        TableRowSorter<ModelTabla12> sorter = new TableRowSorter<ModelTabla12>(model);
        RowFilter<ModelTabla12, Object> rf1 = null;
        RowFilter<ModelTabla12, Object> rf2 = null;
        List<RowFilter<ModelTabla12, Object>> rfs = new ArrayList<RowFilter<ModelTabla12, Object>>(3);
        try {
            rf1 = RowFilter.regexFilter(txtCodMOv.getText(), 1);
            rf2 = RowFilter.regexFilter(txtMotivo.getText(), 2);

            rfs.add(rf1);
            rfs.add(rf2);
    
            RowFilter<ModelTabla12, Object> af = RowFilter.andFilter(rfs);
            sorter.setRowFilter(af);
            tabla.setRowSorter(sorter);
        } catch (Exception ex) {
            return;
        }
    }

    private void actualizarTabla12() {
        mdtabemp.setData(LogicTabla12.listaTabla12());
        mdtabemp.fireTableDataChanged();
    }

    public FrmPanel getPanelMolde() {
        return panelMolde;
    }
}
