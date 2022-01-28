/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

//import com.contab.bean.Bean12;
//import com.contab.combobox.ComboModel12;
//import com.contab.logic.Logic12;
import com.contab.bean.BeanTabla10;
import com.contab.bean.BeanTabla12;
import com.contab.logic.LogicTabla10;
import com.contab.tablemodel.ModelTabla10;
import com.contab.tablemodel.ModelTabla12;
import java.awt.Color;
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
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class ViewTabla10 {

    FrmPanel panelMolde = new FrmPanel(ViewLogin.picture.getIconAdd32(), "SUNAT TABLA 12");
    //private ComboModel12 mdemp = new ComboModel12();
    private ModelTabla10 mdtabemp = new ModelTabla10(panelMolde.getTablaDatos());
    private JButton btnAdd = new JButton(ViewLogin.picture.getIconAdd());
    private JButton btnCommit = new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback = new JButton(ViewLogin.picture.getIconRollBack());

    public ViewTabla10() throws SQLException {
        initComponents();
    }

    private void initComponents() throws SQLException {
        btnAdd.setToolTipText("Agregar nuevo registro");
        btnCommit.setToolTipText("Confirmar operaciones");
        btnRollback.setToolTipText("Actualizar");
     
        panelMolde.getBarraBusqueda().add(btnAdd);
        panelMolde.getBarraBusqueda().add(btnCommit);
        panelMolde.getBarraBusqueda().add(btnRollback);
        mdtabemp.setData(LogicTabla10.listaTabla10());
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
                    BeanTabla10 emp = new BeanTabla10();
                    emp.setOperacion("i");
                    emp.setCodigoSunat("");
                    emp.setCodigoXray("");

                    mdtabemp.getData().add(emp);
                    mdtabemp.fireTableDataChanged();
                    panelMolde.getTablaDatos().changeSelection(mdtabemp.getRowCount() - 1, 1, true, true);
                    panelMolde.getTablaDatos().editCellAt(mdtabemp.getRowCount() - 1, 1);
                } catch (Exception ex) {
                }
            } else if (e.getSource() == btnCommit) {
                try {
                    LogicTabla10.mantenimientoTabla10(mdtabemp.getListaCommit());
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

    private void actualizarTabla12() {
        mdtabemp.setData(LogicTabla10.listaTabla10());
        mdtabemp.fireTableDataChanged();
    }

    public FrmPanel getPanelMolde() {
        return panelMolde;
    }
}
