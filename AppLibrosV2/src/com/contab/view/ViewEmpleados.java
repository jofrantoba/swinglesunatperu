/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

import com.contab.bean.BeanCodigoAuxiliar;
import com.contab.bean.BeanEmpleado;
import com.contab.bean.BeanEmpresa;
import com.contab.combobox.ComboModelCodAux;
import com.contab.combobox.ComboModelEmpleado;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.logic.LogicEmpleado;
import com.contab.logic.LogicEmpresa;
import com.contab.tablemodel.ModelTablaEmpleado;
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
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Administrador
 */
public class ViewEmpleados {

    FrmPanel panelMolde = new FrmPanel(ViewLogin.picture.getIconEmpleado32(), "ACCIONISTAS Y EMPLEADOS");        
    private JLabel lblEmpresa = new JLabel("Empresa");
    private JLabel lblCodAux = new JLabel("Código Auxiliar");
    private JLabel lblCueAux = new JLabel("Cuenta Auxiliar");
    private JTextField txtEmpresa = new JTextField();
    private JTextField txtCodAux = new JTextField();
    private JTextField txtCueAux = new JTextField();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private JComboBoxAutocomplete cbCodAux = new JComboBoxAutocomplete();
    private JComboBoxAutocomplete cbCueAux = new JComboBoxAutocomplete();
    private ComboModelEmpresas mdcbempresas = new ComboModelEmpresas();
    private ComboModelCodAux mdcbcodaux = new ComboModelCodAux();
    private ComboModelEmpleado mdemp = new ComboModelEmpleado();
    private ModelTablaEmpleado mdtabemp = new ModelTablaEmpleado(panelMolde.getTablaDatos());        
    private JButton btnAdd=new JButton(ViewLogin.picture.getIconAdd());
    private JButton btnCommit=new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback=new JButton(ViewLogin.picture.getIconRollBack());
    private JLabel lblDescri=new JLabel("Descripcion  ");
    private JLabel lblDoc=new JLabel("Documento  ");
    private JTextField txtDescri=new JTextField();
    private JTextField txtDoc=new JTextField();
    public ViewEmpleados() throws SQLException {
        initComponents();
    }

    private void initComponents() throws SQLException {
        btnAdd.setToolTipText("Agregar nuevo registro");
        btnCommit.setToolTipText("Confirmar operaciones");
        btnRollback.setToolTipText("Actualizar");
        panelMolde.getNuevo().setIcon(ViewLogin.picture.getIconExcel());        
        panelMolde.getActualizar().setIcon(ViewLogin.picture.getIconPDF32());
        panelMolde.getBarraBusqueda().add(btnAdd);
        panelMolde.getBarraBusqueda().add(btnCommit);
        panelMolde.getBarraBusqueda().add(btnRollback);
        panelMolde.getBarraBusqueda().addSeparator();
        panelMolde.getBarraBusqueda().add(lblDescri);
        panelMolde.getBarraBusqueda().add(txtDescri);
        panelMolde.getBarraBusqueda().addSeparator();
        panelMolde.getBarraBusqueda().add(lblDoc);
        panelMolde.getBarraBusqueda().add(txtDoc);
        Dimension dim=new Dimension(200,20);
        txtDoc.setMaximumSize(dim);        
        txtDescri.setMaximumSize(dim);
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
        panelMolde.getPanelFormulario().add(cbEmpresa, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(lblCodAux, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(txtCodAux, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(cbCodAux, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(lblCueAux, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(txtCueAux, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.getPanelFormulario().add(cbCueAux, gbc);
        txtEmpresa.setColumns(10);
        txtCodAux.setColumns(10);
        txtCueAux.setColumns(10);
        Dimension d = new Dimension(300, 20);
        cbEmpresa.setPreferredSize(d);
        cbCodAux.setPreferredSize(d);
        cbCueAux.setPreferredSize(d);
        mdcbempresas.setData(LogicEmpresa.listaDeEmpresa());
        cbEmpresa.setModel(mdcbempresas);
        cbEmpresa.addActionListener(actionListener);
        txtEmpresa.addKeyListener(keyListener);
        txtCodAux.addKeyListener(keyListener);
        txtCueAux.addKeyListener(keyListener);
        cbCodAux.setModel(mdcbcodaux);
        cbCodAux.addActionListener(actionListener);
        mdtabemp.setData(LogicEmpleado.listaDeEmpleado());
        //panelMolde.getTablaDatos().setModel(mdtabemp);
        panelMolde.getTablaDatos().setCellSelectionEnabled(true);
        panelMolde.getTablaDatos().setFocusable(true);
        panelMolde.getTablaDatos().setSelectionBackground(Color.BLUE);                
        txtEmpresa.addKeyListener(keyfiltrar);
        txtCodAux.addKeyListener(keyfiltrar);
        txtCueAux.addKeyListener(keyfiltrar);
        txtDescri.addKeyListener(keyfiltrar);
        txtDoc.addKeyListener(keyfiltrar);
        txtCodAux.addCaretListener(caretListener);
        txtEmpresa.addCaretListener(caretListener);
        txtCueAux.addCaretListener(caretListener);        
        cbCueAux.addActionListener(actionListener);                
        btnAdd.addActionListener(actionListener);
        btnCommit.addActionListener(actionListener);
        btnRollback.addActionListener(actionListener);
    }
    CaretListener caretListener = new CaretListener() {

        @Override
        public void caretUpdate(CaretEvent e) {
            if (txtCodAux == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(),mdtabemp);
            } else if (txtEmpresa == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(),mdtabemp);
            }else if (txtCueAux == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(),mdtabemp);
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
                    String codemp=mdcbempresas.getElement(cbEmpresa.getSelectedIndex()).getCodigo();
                    String codaux=mdcbcodaux.getElement(cbCodAux.getSelectedIndex()).getCodigo();
                    mdemp.setData(LogicEmpleado.listaDeEmpleado(codemp,codaux));
                    cbCueAux.setModel(mdemp);                    
                    cbCueAux.updateUI();
                } catch (Exception ex) {
                }
            } else if (e.getSource() == cbCodAux) {
                cbCodAux.getEditor().setItem(cbCodAux.getSelectedItem());
                txtCodAux.setText(mdcbcodaux.getElement(cbCodAux.getSelectedIndex()).getCodigo());
                try {                    
                    String codemp=mdcbempresas.getElement(cbEmpresa.getSelectedIndex()).getCodigo();
                    String codaux=mdcbcodaux.getElement(cbCodAux.getSelectedIndex()).getCodigo();
                    mdemp.setData(LogicEmpleado.listaDeEmpleado(codemp,codaux));
                    cbCueAux.setModel(mdemp);                    
                    cbCueAux.updateUI();
                } catch (Exception ex) {
                }
            }else if (e.getSource() == cbCueAux) {
                try{
                cbCueAux.getEditor().setItem(cbCueAux.getSelectedItem());                
                txtCueAux.setText(mdemp.getElement(cbCueAux.getSelectedIndex()).getCuentaAuxiliar().toString());                  
                }catch(Exception ex){}
            }else if (e.getSource() == btnAdd) {
                try{  
                txtEmpresa.setText(null);
                txtCodAux.setText(null);
                txtCueAux.setText(null);
                BeanEmpleado emp=new BeanEmpleado();
                emp.setEstadoOperacion("i");
                mdtabemp.getData().add(emp);
                mdtabemp.fireTableDataChanged();   
                panelMolde.getTablaDatos().changeSelection(mdtabemp.getRowCount()-1, 1, true, true);
                panelMolde.getTablaDatos().editCellAt(mdtabemp.getRowCount()-1, 1);                                
                }catch(Exception ex){}
            }else if(e.getSource()==btnCommit){
                LogicEmpleado.mantenimientoEmpleado(mdtabemp.getListaCommit());
                mdtabemp.getListaCommit().clear();
                actualizarTablaEmpleado();
            }else if(e.getSource()==btnRollback){
                actualizarTablaEmpleado();
            }
        }
    };    
    
    private void actualizarTablaEmpleado(){
        mdtabemp.setData(LogicEmpleado.listaDeEmpleado());
        mdtabemp.fireTableDataChanged();
    }
    
    private void filtrarTabla(JTable tabla, ModelTablaEmpleado model) {
        TableRowSorter<ModelTablaEmpleado> sorter = new TableRowSorter<ModelTablaEmpleado>(model);
        RowFilter<ModelTablaEmpleado, Object> rf1 = null;
        RowFilter<ModelTablaEmpleado, Object> rf2 = null;
        RowFilter<ModelTablaEmpleado, Object> rf3 = null;
        RowFilter<ModelTablaEmpleado, Object> rf4 = null;
        RowFilter<ModelTablaEmpleado, Object> rf5 = null;
        List<RowFilter<ModelTablaEmpleado, Object>> rfs = new ArrayList<RowFilter<ModelTablaEmpleado, Object>>(5);
        try {
            rf1 = RowFilter.regexFilter(txtEmpresa.getText(), 1);
            rf2 = RowFilter.regexFilter(txtCodAux.getText(), 2);
            rf3 = RowFilter.regexFilter(txtCueAux.getText(), 3);
            rf4 = RowFilter.regexFilter(txtDescri.getText(), 4);
            rf5 = RowFilter.regexFilter(txtDoc.getText(), 5);
            rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf3);
            rfs.add(rf4);
            rfs.add(rf5);
            RowFilter<ModelTablaEmpleado, Object> af = RowFilter.andFilter(rfs);
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
                filtrarTabla(panelMolde.getTablaDatos(),mdtabemp);
            } else if (txtCodAux == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(),mdtabemp);
            } else if (txtCueAux == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(),mdtabemp);
            }else if (txtDescri == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(),mdtabemp);
            }else if (txtDoc == e.getSource()) {
                filtrarTabla(panelMolde.getTablaDatos(),mdtabemp);
            }
        }
    };
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
            } else if (e.getSource() == txtCodAux) {
                String item = null;
                Iterator i = mdcbcodaux.getData().iterator();
                String codigo = txtCodAux.getText();
                while (i.hasNext()) {
                    BeanCodigoAuxiliar b = (BeanCodigoAuxiliar) i.next();
                    if (b.getCodigo().equals(codigo)) {
                        item = mdcbcodaux.getElement(codigo).getDescripcion();
                        break;
                    }
                }
                if (item != null) {
                    cbCodAux.setSelectedItem(item);
                } else {
                    cbCodAux.getEditor().setItem(null);
                }
            }else if (e.getSource() == txtCueAux) {
                String item = null;
                Iterator i = mdemp.getData().iterator();
                String codigo = txtCueAux.getText();
                while (i.hasNext()) {
                    BeanEmpleado b = (BeanEmpleado) i.next();
                    if (b.getCuentaAuxiliar().equals(codigo)) {
                        item = mdemp.getElement(codigo).getDescripcion();
                        break;
                    }
                }
                if (item != null) {
                    cbCueAux.setSelectedItem(item);                    
                } else {
                    cbCueAux.getEditor().setItem(null);
                }
            }
        }
    };

    public FrmPanel getPanelMolde(){
        return panelMolde;
    }
    
}
