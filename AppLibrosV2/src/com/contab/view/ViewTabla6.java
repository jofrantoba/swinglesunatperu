/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

//import com.contab.bean.Bean12;
//import com.contab.combobox.ComboModel12;
//import com.contab.logic.Logic12;
import com.contab.bean.BeanTabla12;
import com.contab.bean.BeanTabla6;
import com.contab.logic.LogicTabla12;
import com.contab.logic.LogicTabla6;
import com.contab.tablemodel.ModelTabla06;
import com.contab.tablemodel.ModelTabla12;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JTable;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class ViewTabla6 {

    FrmPanel panelMolde = new FrmPanel(ViewLogin.picture.getIconAdd32(), "SUNAT TABLA 06");
    private JButton btnAdd = new JButton(ViewLogin.picture.getIconAdd());
    private JButton btnCommit = new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback = new JButton(ViewLogin.picture.getIconRollBack());
    private ModelTabla06 mdtabemp = new ModelTabla06(panelMolde.getTablaDatos());
    private JTable data;
    private final int rows = 2;
    private final int rowses = 5;

    public ViewTabla6() throws SQLException {
        initComponents();
    }

    private void initComponents() throws SQLException {
        btnAdd.setToolTipText("Agregar nuevo registro");
        btnCommit.setToolTipText("Confirmar operaciones");
        btnRollback.setToolTipText("Actualizar");
     
        panelMolde.getBarraBusqueda().add(btnAdd);
        panelMolde.getBarraBusqueda().add(btnCommit);
        panelMolde.getBarraBusqueda().add(btnRollback);

        mdtabemp.setData(LogicTabla6.listaTabla6());
        panelMolde.getTablaDatos().setModel(mdtabemp);
        panelMolde.getTablaDatos().setSelectionBackground(Color.PINK);
        panelMolde.getTablaDatos().setCellSelectionEnabled(true);
        
        btnAdd.addActionListener(actionListener);
        btnCommit.addActionListener(actionListener);
        btnRollback.addActionListener(actionListener);


    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAdd) {

                try {
                    BeanTabla6 emp = new BeanTabla6();
                    emp.setOperacion("i");
                    emp.setCodigoSunat("");
                    emp.setUnidad("");

                    mdtabemp.getData().add(emp);
                    mdtabemp.fireTableDataChanged();
                    panelMolde.getTablaDatos().changeSelection(mdtabemp.getRowCount() - 1, 1, true, true);
                    panelMolde.getTablaDatos().editCellAt(mdtabemp.getRowCount() - 1, 1);
                } catch (Exception ex) {
                }
            } else if (e.getSource() == btnCommit) {
                try {
                    LogicTabla6.mantenimientoTabla06(mdtabemp.getListaCommit());
                } catch (SQLException ex) {
                    Exceptions.printStackTrace(ex);
                }
                mdtabemp.getListaCommit().clear();
                actualizarTabla06();
            } else if (e.getSource() == btnRollback) {
                actualizarTabla06();
            }
        }
    };
    
    private void actualizarTabla06() {
        mdtabemp.setData(LogicTabla6.listaTabla6());
        mdtabemp.fireTableDataChanged();
    }

    public void keyReleased(KeyEvent e) {
        try {
            int fila = data.getSelectedRow();
            int columna = data.getSelectedColumn();
            int rowModel = data.convertRowIndexToModel(fila);
            int columnModel = data.convertColumnIndexToModel(columna);
            if (columnModel == 2 || columnModel == 3) {
                ActualizarTabla(rowModel);
            }
        } catch (Exception ex) {
        }
    }

    private void ActualizarTabla(int rowModel) {
        String id = (String) mdtabemp.getValueAt(rowModel, 1);
        String unidad = (String) mdtabemp.getValueAt(rowModel, 2);
        String codigoSunta = (String) mdtabemp.getValueAt(rowModel, 3);

        BeanTabla6 bnTabla6= new BeanTabla6();
        bnTabla6.setIdUnidadArticulo(id);
        bnTabla6.setUnidad(unidad);
        bnTabla6.setCodigoSunat(codigoSunta);

        //  mdtabemp.update(bnTabla12, rows);
    }

    public FrmPanel getPanelMolde() {
        return panelMolde;
    }
}
