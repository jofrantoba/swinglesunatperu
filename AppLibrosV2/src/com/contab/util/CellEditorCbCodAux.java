/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.util;

import com.contab.combobox.ComboModelCodAux;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Administrador
 */
public class CellEditorCbCodAux extends AbstractCellEditor implements TableCellEditor{
    private JComboBoxAutocomplete cbCodAux = new JComboBoxAutocomplete();
    private ComboModelCodAux mdcbcodaux = new ComboModelCodAux();
    
    public CellEditorCbCodAux(){        
        cbCodAux.setModel(mdcbcodaux);
    }
    @Override
    public Object getCellEditorValue() {
        try{
        return mdcbcodaux.getElement(cbCodAux.getSelectedIndex()).getCodigo();
        }catch(Exception ex){
            return null;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {        
            return cbCodAux;
                
    }
    
}
