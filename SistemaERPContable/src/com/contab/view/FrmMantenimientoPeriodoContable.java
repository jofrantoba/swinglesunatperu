/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrmMantenimientoPeriodoContable.java
 *
 * Created on 21-jun-2012, 11:04:00
 */
package com.contab.view;

/**
 *
 * @author cixtic04
 */
public class FrmMantenimientoPeriodoContable extends javax.swing.JFrame {

    /** Creates new form FrmMantenimientoPeriodoContable */
    public FrmMantenimientoPeriodoContable() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtoolbmenu = new javax.swing.JToolBar();
        jbtnCerrar1 = new javax.swing.JButton();
        jbtnNuevo = new javax.swing.JButton();
        jbtnAceptar = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();
        jbtnCerrar = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtoolbmenu.setBackground(new java.awt.Color(255, 255, 255));
        jtoolbmenu.setFloatable(false);
        jtoolbmenu.setAutoscrolls(true);
        jtoolbmenu.setBorderPainted(false);
        jtoolbmenu.setFocusTraversalPolicyProvider(true);
        jtoolbmenu.setInheritsPopupMenu(true);
        jtoolbmenu.setOpaque(false);

        jbtnCerrar1.setBackground(new java.awt.Color(255, 255, 255));
        jbtnCerrar1.setIcon(new javax.swing.ImageIcon("E:\\sistema_ERP\\ERPSistemaContable\\src\\com\\gps\\erp\\vista\\images\\guardar.png")); // NOI18N
        jbtnCerrar1.setToolTipText(org.openide.util.NbBundle.getMessage(FrmMantenimientoPeriodoContable.class, "FrmMantenimientoPeriodoContable.jbtnCerrar1.toolTipText")); // NOI18N
        jbtnCerrar1.setActionCommand(org.openide.util.NbBundle.getMessage(FrmMantenimientoPeriodoContable.class, "FrmMantenimientoPeriodoContable.jbtnCerrar1.actionCommand")); // NOI18N
        jbtnCerrar1.setFocusable(false);
        jbtnCerrar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnCerrar1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jbtnCerrar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jtoolbmenu.add(jbtnCerrar1);

        jbtnNuevo.setBackground(new java.awt.Color(255, 255, 255));
        jbtnNuevo.setIcon(new javax.swing.ImageIcon("C:\\Users\\cixtic04\\Dropbox\\contabilidad\\src\\img\\table-add-icon.png")); // NOI18N
        jbtnNuevo.setToolTipText(org.openide.util.NbBundle.getMessage(FrmMantenimientoPeriodoContable.class, "FrmMantenimientoPeriodoContable.jbtnNuevo.toolTipText")); // NOI18N
        jbtnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnNuevo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jbtnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevoActionPerformed(evt);
            }
        });
        jtoolbmenu.add(jbtnNuevo);

        jbtnAceptar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnAceptar.setIcon(new javax.swing.ImageIcon("C:\\Users\\cixtic04\\Dropbox\\contabilidad\\src\\img\\table-delete-icon.png")); // NOI18N
        jbtnAceptar.setToolTipText(org.openide.util.NbBundle.getMessage(FrmMantenimientoPeriodoContable.class, "FrmMantenimientoPeriodoContable.jbtnAceptar.toolTipText")); // NOI18N
        jbtnAceptar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnAceptar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jbtnAceptar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAceptarActionPerformed(evt);
            }
        });
        jtoolbmenu.add(jbtnAceptar);

        jbtnEditar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnEditar.setIcon(new javax.swing.ImageIcon("C:\\Users\\cixtic04\\Dropbox\\contabilidad\\src\\img\\table-excel-icon.png")); // NOI18N
        jbtnEditar.setToolTipText(org.openide.util.NbBundle.getMessage(FrmMantenimientoPeriodoContable.class, "FrmMantenimientoPeriodoContable.jbtnEditar.toolTipText")); // NOI18N
        jbtnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnEditar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jbtnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarActionPerformed(evt);
            }
        });
        jtoolbmenu.add(jbtnEditar);

        jbtnCerrar.setBackground(new java.awt.Color(255, 255, 255));
        jbtnCerrar.setIcon(new javax.swing.ImageIcon("C:\\Users\\cixtic04\\Dropbox\\contabilidad\\src\\img\\table-conf-icon.png")); // NOI18N
        jbtnCerrar.setToolTipText(org.openide.util.NbBundle.getMessage(FrmMantenimientoPeriodoContable.class, "FrmMantenimientoPeriodoContable.jbtnCerrar.toolTipText")); // NOI18N
        jbtnCerrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jbtnCerrar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jbtnCerrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jbtnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCerrarActionPerformed(evt);
            }
        });
        jtoolbmenu.add(jbtnCerrar);

        getContentPane().add(jtoolbmenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 50));

        jTextField2.setText(org.openide.util.NbBundle.getMessage(FrmMantenimientoPeriodoContable.class, "FrmMantenimientoPeriodoContable.jTextField2.text")); // NOI18N
        jTextField2.setFocusCycleRoot(true);
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 50, -1));

        jLabel1.setText(org.openide.util.NbBundle.getMessage(FrmMantenimientoPeriodoContable.class, "FrmMantenimientoPeriodoContable.jLabel1.text")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 50, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 330, -1));

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Año", "Mes", "Periodo"
            }
        ));
        jScrollPane1.setViewportView(jXTable1);
        jXTable1.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(FrmMantenimientoPeriodoContable.class, "FrmMantenimientoPeriodoContable.jXTable1.columnModel.title0")); // NOI18N
        jXTable1.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(FrmMantenimientoPeriodoContable.class, "FrmMantenimientoPeriodoContable.jXTable1.columnModel.title1")); // NOI18N
        jXTable1.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(FrmMantenimientoPeriodoContable.class, "FrmMantenimientoPeriodoContable.jXTable1.columnModel.title2")); // NOI18N

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 450, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevoActionPerformed

    }//GEN-LAST:event_jbtnNuevoActionPerformed

    private void jbtnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAceptarActionPerformed

    }//GEN-LAST:event_jbtnAceptarActionPerformed

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed

    }//GEN-LAST:event_jbtnEditarActionPerformed

    private void jbtnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCerrarActionPerformed

    }//GEN-LAST:event_jbtnCerrarActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FrmMantenimientoPeriodoContable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoPeriodoContable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoPeriodoContable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMantenimientoPeriodoContable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new FrmMantenimientoPeriodoContable().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private org.jdesktop.swingx.JXTable jXTable1;
    private javax.swing.JButton jbtnAceptar;
    private javax.swing.JButton jbtnCerrar;
    private javax.swing.JButton jbtnCerrar1;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JToolBar jtoolbmenu;
    // End of variables declaration//GEN-END:variables
}
