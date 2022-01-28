/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.contab.tablemodel.ModelTABLA03;
import com.contab.tablemodel.ModelTABLA103;
import com.contab.tablemodel.ModelTabla06;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;

/**
 * @version 1.0 12/05/98
 */
public class FixedColumnExample extends JFrame {

    JTable fixedTable, table;
    FrmPanel panelMolde = new FrmPanel(ViewLogin.picture.getIconAdd32(), "SUNAT TABLA 03");
    private ModelTABLA03 mdtabemp = new ModelTABLA03(panelMolde.getTablaDatos());
    private ModelTabla06 mdtabemp1 = new ModelTabla06(panelMolde.getTablaDatos());

    public FixedColumnExample() {

        super("Fixed Column Example");
        setSize(400, 150);

        mdtabemp.setData(null);
        panelMolde.getTablaDatos().setModel(mdtabemp);
        panelMolde.getTablaDatos().setSelectionBackground(Color.PINK);
        panelMolde.getTablaDatos().setCellSelectionEnabled(true);

        mdtabemp1.setData(null);
        panelMolde.getTablaDatos().setModel(mdtabemp1);


        fixedTable = new JTable(mdtabemp1) {

            public void valueChanged(ListSelectionEvent e) {
                super.valueChanged(e);
                checkSelection(true);
            }
        };

        table = new JTable(mdtabemp) {

            public void valueChanged(ListSelectionEvent e) {
                super.valueChanged(e);
                checkSelection(false);
            }
        };

        fixedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        fixedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(table);
        JViewport viewport = new JViewport();
        viewport.setView(fixedTable);
        viewport.setPreferredSize(fixedTable.getPreferredSize());
        scroll.setRowHeaderView(viewport);
        scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER, fixedTable.getTableHeader());

        getContentPane().add(scroll);
        
        
    }

    private void checkSelection(boolean isFixedTable) {
        int fixedSelectedIndex = fixedTable.getSelectedRow();
        System.out.print("--" + fixedSelectedIndex);
        int selectedIndex = table.getSelectedRow();
        if (fixedSelectedIndex != selectedIndex) {
            if (isFixedTable) {
                table.setRowSelectionInterval(fixedSelectedIndex, fixedSelectedIndex);
            } else {
                fixedTable.setRowSelectionInterval(selectedIndex, selectedIndex);
            }
        }
    }

    public static void main(String[] args) {
        FixedColumnExample frame = new FixedColumnExample();
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
}