/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public final class Demo2 extends JPanel implements ActionListener {

    private static JFrame frame;
    private JTextArea ta;
    private JList lista;
    private JCheckBox chkDragEnable;

    public Demo2() {
        super(new BorderLayout());
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
        pan.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ta = new JTextArea(5, 30);
        ta.setText("Prueba Prueba prueba");
        JScrollPane scrollpane = new JScrollPane(ta);
        scrollpane.setPreferredSize(new Dimension(20, 10));
       // pan.add(crearPanelCompomente(scrollpane, "JTextArea"));
         add(scrollpane, BorderLayout.CENTER);

        DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("hola 1");
        listModel.addElement("hola 2");
        listModel.addElement("hola 3");
        lista = new JList(listModel);
    //     lista.setTransferHandler(new ListaTransfer());
      //  lista.setDropMode(DropMode.ON_OR_INSERT);

        JScrollPane listView = new JScrollPane(lista);
        listView.setPreferredSize(new Dimension(300, 100));
       // JPanel panel = new JPanel(new BorderLayout());
        add(listView, BorderLayout.CENTER);
        //setBorder(BorderFactory.createTitledBorder("JList"));
        //add(panel);

        chkDragEnable = new JCheckBox("Activar drag and drop");
        chkDragEnable.setActionCommand("chkDragEnable");
        chkDragEnable.addActionListener(this);
        add(pan, BorderLayout.AFTER_LINE_ENDS);
        add(chkDragEnable, BorderLayout.AFTER_LINE_ENDS);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


    }

    protected JPanel crearPanelx() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return p;
    }

    public JPanel crearPanelCompomente(JComponent comp, String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(comp, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("chkDragEnable".equals(e.getActionCommand())) {
            ta.setDragEnabled(chkDragEnable.isSelected());
            lista.setDragEnabled(chkDragEnable.isSelected());

        }
    }

    public static void main(String[] args) {

        frame = new JFrame("drag an Drop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent comp = new Demo2();
        comp.setOpaque(true);
        frame.setContentPane(comp);
        frame.pack();
        frame.setVisible(true);
    }
}
