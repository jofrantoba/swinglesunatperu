/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.tablemodel;

import com.contab.bean.BeanDobleAsiento;
import com.contab.util.CellEditorCbCodAux;
import com.contab.util.CellEditorCbEmpresa;
import com.contab.util.CellRedenderImage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrador
 */
public class ModelAsientoDoble extends AbstractTableModel {

    private ArrayList<String> headerTable = new ArrayList();
    private ArrayList<BeanDobleAsiento> data = new ArrayList();
    private ArrayList<TableModelListener> listeners = new ArrayList();
    private JTable tablaDatos;
    private HashSet<BeanDobleAsiento> listaCommit = new HashSet();

     public ModelAsientoDoble() {
        createHeaders();
    }

    public ModelAsientoDoble(JTable tabla) {
        tablaDatos = tabla;
        createHeaders();
    }

    public ModelAsientoDoble(ArrayList<BeanDobleAsiento> lista) {
        data = lista;
        createHeaders();
    }

    private void createHeaders() {
        headerTable.add("");
        headerTable.add("CUENTA");
        headerTable.add("CENTRO");
        headerTable.add("FECHA");
        headerTable.add("DEBE SOLES");
        headerTable.add("HABER SOLES ");
        headerTable.add("SALDO SOLES");
        headerTable.add("DEBE DOLARES");
        headerTable.add("HABER DOLARES ");
        headerTable.add("SALDO DOLARES");

    }

    public void borrar(int fila) {
        try {
            data.remove(fila);
            TableModelEvent evento;
            evento = new TableModelEvent(this, fila, fila, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
            avisaSuscriptores(evento);
            fireTableDataChanged();
        } catch (Exception ex) {
        }
    }

    public void insertar(BeanDobleAsiento i) {
        data.add(i);
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        avisaSuscriptores(evento);
        fireTableDataChanged();
    }

    public void update(BeanDobleAsiento i, int fila) {
        data.remove(fila);
        data.add(fila, i);
        TableModelEvent evento;
        evento = new TableModelEvent(this, fila, fila, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE);
        avisaSuscriptores(evento);
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return headerTable.get(column);
    }

    @Override
    public int getRowCount() {
        try {
            return data.size();
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return headerTable.size();
    }

    public BeanDobleAsiento getValue(int rowIndex) {
        try {
            return data.get(rowIndex);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
            //  return data.get(rowIndex).getOperacion();//data.get(rowIndex).getCompania();
            case 1:
                return data.get(rowIndex).getCuenta();
            case 2:
                return data.get(rowIndex).getCentro();
            case 3:
                return data.get(rowIndex).getFecha();
            case 4:
                return data.get(rowIndex).getDeberSoles();
            case 5:
                return data.get(rowIndex).getHaberSoles();
            case 6:
                return data.get(rowIndex).getSaldoSoles();
            case 7:
                return data.get(rowIndex).getDeberDolares();
            case 8:
                return data.get(rowIndex).getHaberDolares();
            case 9:
                return data.get(rowIndex).getSaldoDolares();

            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aux;
        String valor;
        BigDecimal valorP;
        //  BeanDobleAsiento auxEmp = data.get(rowIndex);
        boolean eliminar = false;
        switch (columnIndex) {
            case 0:
                break;

            case 1:
                valor = String.valueOf(aValue);
                data.get(rowIndex).setCuenta(valor);
                break;
            case 2:
                valor = String.valueOf(aValue);
                data.get(rowIndex).setCentro(valor);
                break;
            case 3:
                valor = String.valueOf(aValue);
                data.get(rowIndex).setFecha(valor);
                break;
            case 4:
                valorP = BigDecimal.valueOf(Double.valueOf(aValue.toString()));
                data.get(rowIndex).setDeberSoles(valorP);
                break;
            case 5:
                valorP = BigDecimal.valueOf(Double.valueOf(aValue.toString()));
                data.get(rowIndex).setHaberSoles(valorP);
                break;
            case 6:
                valorP = BigDecimal.valueOf(Double.valueOf(aValue.toString()));
                data.get(rowIndex).setSaldoSoles(valorP);
                break;
            case 7:
                valorP = BigDecimal.valueOf(Double.valueOf(aValue.toString()));
                data.get(rowIndex).setDeberDolares(valorP);  //setDeberDolares
                break;
            case 8:
                valorP = BigDecimal.valueOf(Double.valueOf(aValue.toString()));
                data.get(rowIndex).setHaberSoles(valorP);
                break;
            case 9:
                valorP = BigDecimal.valueOf(Double.valueOf(aValue.toString()));
                data.get(rowIndex).setSaldoDolares(valorP);
                break;


        }

        TableModelEvent evento;
        evento = new TableModelEvent(this, rowIndex, rowIndex, TableModelEvent.ALL_COLUMNS, TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores(evento);
        fireTableDataChanged();
    }

    private void avisaSuscriptores(TableModelEvent evento) {
        Iterator i = listeners.iterator();
        while (i.hasNext()) {
            ((TableModelListener) i.next()).tableChanged(evento);
        }
    }

    @Override
    public int findColumn(String columnName) {
        for (int i = 0; i < headerTable.size(); i++) {
            if (columnName.equals(headerTable.get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            case 8:
                return String.class;
            case 9:
                return String.class;


            default:
                return String.class;
        }
    }

     @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

            switch (columnIndex) {
                case 0:
                    return false;
                case 1:
                    return true;
                case 2:
                    return true;
                case 3:
                    return true;
                case 4:
                    return true;
                case 5:
                    return true;
                case 6:
                    return true;
                case 7:
                    return false;
                case 8:
                    return true;
                case 9:
                    return false;
               

                default:
                    return false;
            }

    }


    @Override
    public void addTableModelListener(TableModelListener l) {
        listenerList.add(TableModelListener.class, l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listenerList.remove(TableModelListener.class, l);
    }

    public ArrayList<BeanDobleAsiento> getData() {
        return data;
    }

    public void setData(ArrayList<BeanDobleAsiento> data) {
        this.data = data;
        fireTableDataChanged();
        tablaDatos.setModel(this);
        tablaDatos.getColumnModel().getColumn(0).setMaxWidth(20);

        //tablaDatos.getColumnModel().getColumn(0).setCellRenderer(new CellRedenderImage());
        //tablaDatos.getColumnModel().getColumn(1).setCellEditor(new CellEditorCbEmpresa());
        //tablaDatos.getColumnModel().getColumn(2).setCellEditor(new CellEditorCbCodAux());
      //  tablaDatos.getColumnModel().getColumn(4).setPreferredWidth(200);
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public HashSet<BeanDobleAsiento> getListaCommit() {
        return listaCommit;
    }

    public void setTablaDatos(JTable tablaDatos) {
        this.tablaDatos = tablaDatos;
    }

    public JTable getTablaDatos() {
        return tablaDatos;
    }

    
    
}
