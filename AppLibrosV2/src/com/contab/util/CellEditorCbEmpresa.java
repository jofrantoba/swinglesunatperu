/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.util;

import com.contab.combobox.ComboModelEmpresas;
import com.contab.logic.LogicEmpresa;
import java.awt.Component;
import java.sql.SQLException;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class CellEditorCbEmpresa extends AbstractCellEditor implements TableCellEditor{
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private ComboModelEmpresas mdcbempresas = new ComboModelEmpresas();
    public CellEditorCbEmpresa(){
        try {
            mdcbempresas.setData(LogicEmpresa.listaDeEmpresa());
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        cbEmpresa.setModel(mdcbempresas);
    }
    @Override
    public Object getCellEditorValue() {
        try{
            return mdcbempresas.getElement(cbEmpresa.getSelectedIndex()).getCodigo();
        }catch(Exception ex){
            return null;
        }        
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {        
            return cbEmpresa;
                
    }
    
}
