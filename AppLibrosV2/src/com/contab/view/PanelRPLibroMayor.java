package com.contab.view;

import com.contab.bean.BeanEmpresa;
import com.contab.bean.BeanAsientoMayor;
import com.contab.bean.BeanCuentaMayor;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.combobox.ComboModelAsientoMayor;
import com.contab.combobox.ComboModelCuentaMayor;
import com.contab.logic.LogicEmpresa;
import com.contab.logic.LogicAsientoMayor;
import com.contab.logic.LogicCuentaMayor;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.openide.util.Exceptions;

public class PanelRPLibroMayor extends javax.swing.JPanel {

    private ComboModelEmpresas modelcbempresa = new ComboModelEmpresas(LogicEmpresa.listaDeEmpresa());
    BeanEmpresa objEmpresa;
    BeanAsientoMayor objFuAsientos;
    BeanCuentaMayor objFucuentas;

    /** Creates new form PanelRPLibroMayor */
    public PanelRPLibroMayor() throws SQLException {
        initComponents();
        asignarEmpresa();
    }

    public void asignarEmpresa() throws SQLException {
        cmbEmpresa.setModel(modelcbempresa);
    }

    public void asignarAsiento() throws SQLException {
        ComboModelAsientoMayor modeloFuasientos = new ComboModelAsientoMayor(LogicAsientoMayor.listarFuAsientos(objEmpresa.getCodigo()));
        cmbasientos.setModel(modeloFuasientos);
    }

    public void asignarCuentas() throws SQLException {
        ComboModelCuentaMayor modeloFucuentas = new ComboModelCuentaMayor(LogicCuentaMayor.listarFucuentas(objEmpresa.getCodigo()));
        cmbcuenta.setModel(modeloFucuentas);
    }

    public JComboBox getCmbEmpresa() {
        return cmbEmpresa;
    }

    public JComboBox getCmbasientos() {
        return cmbasientos;
    }

    public JComboBox getCmbcuenta() {
        return cmbcuenta;
    }

    public JComboBox getCmbmes() {
        return cmbmes;
    }

 
    public JLabel getjLabel1() {
        return jLabel1;
    }

    public JLabel getjLabel2() {
        return jLabel2;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public JLabel getjLabel5() {
        return jLabel5;
    }

    public JLabel getjLabel7() {
        return jLabel7;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public JPanel getjPanel2() {
        return jPanel2;
    }

    public ComboModelEmpresas getModelcbempresa() {
        return modelcbempresa;
    }

    public BeanEmpresa getObjEmpresa() {
        return objEmpresa;
    }

    public BeanAsientoMayor getObjFuAsientos() {
        return objFuAsientos;
    }

    public BeanCuentaMayor getObjFucuentas() {
        return objFucuentas;
    }

    public JTextField getTxtano() {
        return txtano;
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        cmbEmpresa = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cmbcuenta = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cmbasientos = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cmbmes = new javax.swing.JComboBox();
        txtano = new javax.swing.JTextField();

        jLabel3.setText(org.openide.util.NbBundle.getMessage(PanelRPLibroMayor.class, "PanelRPLibroMayor.jLabel3.text_1")); // NOI18N

        cmbEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEmpresaActionPerformed(evt);
            }
        });

        jLabel1.setText(org.openide.util.NbBundle.getMessage(PanelRPLibroMayor.class, "PanelRPLibroMayor.jLabel1.text_1")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(PanelRPLibroMayor.class, "PanelRPLibroMayor.jPanel1.border.border.border.title_1"))))); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(PanelRPLibroMayor.class, "PanelRPLibroMayor.jLabel5.text_1")); // NOI18N

        cmbcuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbcuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbcuenta, 0, 506, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(cmbcuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel2.setText(org.openide.util.NbBundle.getMessage(PanelRPLibroMayor.class, "PanelRPLibroMayor.jLabel2.text_1")); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(PanelRPLibroMayor.class, "PanelRPLibroMayor.jPanel2.border.title_1"))); // NOI18N

        cmbasientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbasientosActionPerformed(evt);
            }
        });

        jLabel7.setText(org.openide.util.NbBundle.getMessage(PanelRPLibroMayor.class, "PanelRPLibroMayor.jLabel7.text_1")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(cmbasientos, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbasientos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbmes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto ", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        txtano.setText(org.openide.util.NbBundle.getMessage(PanelRPLibroMayor.class, "PanelRPLibroMayor.txtano.text_1")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtano, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cmbmes, javax.swing.GroupLayout.Alignment.LEADING, 0, 122, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(131, 131, 131))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEmpresaActionPerformed
        try {
            // TODO add your handling code here:
            objEmpresa = ((ComboModelEmpresas) (this.cmbEmpresa.getModel())).getElement(cmbEmpresa.getSelectedIndex());
            asignarAsiento();
            asignarCuentas();
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
}//GEN-LAST:event_cmbEmpresaActionPerformed

    private void cmbasientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbasientosActionPerformed
        // TODO add your handling code here:        asignarAsiento();
        objFuAsientos = ((ComboModelAsientoMayor) (this.cmbasientos.getModel())).getElement(cmbasientos.getSelectedIndex());
    }//GEN-LAST:event_cmbasientosActionPerformed

    private void cmbcuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbcuentaActionPerformed
        // TODO add your handling code here:
        objFucuentas = ((ComboModelCuentaMayor) (this.cmbcuenta.getModel())).getElement(cmbcuenta.getSelectedIndex());
    }//GEN-LAST:event_cmbcuentaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbEmpresa;
    private javax.swing.JComboBox cmbasientos;
    private javax.swing.JComboBox cmbcuenta;
    private javax.swing.JComboBox cmbmes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtano;
    // End of variables declaration//GEN-END:variables
}
