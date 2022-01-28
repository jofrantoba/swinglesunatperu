/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.tablemodel;

import com.contab.bean.BeanTabla6;

import com.contab.util.CellEditorCbEmpresa;
import com.contab.util.CellRedenderImage;
import com.contab.view.ViewLogin;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Administrador
 */
public class ModelTabla06 extends AbstractTableModel {

    private ArrayList<String> headerTable = new ArrayList();
    private ArrayList<BeanTabla6> data = new ArrayList();
    private ArrayList<TableModelListener> listeners = new ArrayList();
    private JTable tablaDatos;
    private HashSet<BeanTabla6> listaCommit = new HashSet();

    public ModelTabla06(JTable tabla) {
        tablaDatos = tabla;
        createHeaders();
    }

    public ModelTabla06(ArrayList<BeanTabla6> lista) {
        data = lista;
        createHeaders();
    }

    private void createHeaders() {
        headerTable.add("");

        headerTable.add("DESCRIPCIÓN");
        headerTable.add("CODIGO DE SUNAT");
        headerTable.add(" ");
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

    public void insertar(BeanTabla6 i) {
        data.add(i);
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        avisaSuscriptores(evento);
        fireTableDataChanged();
    }

    public void update(BeanTabla6 i, int fila) {
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

    public BeanTabla6 getValue(int rowIndex) {
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
                return data.get(rowIndex).getOperacion();//data.get(rowIndex).getCompania();            
            case 1:
                return data.get(rowIndex).getUnidad();
            case 2:
                return data.get(rowIndex).getCodigoSunat();

            case 3:
                return data.get(rowIndex).isSelecccion();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aux;
        String valor;
        BeanTabla6 auxEmp = data.get(rowIndex);
        boolean eliminar = false;
        switch (columnIndex) {
            case 0:
                break;
            case 1:
                valor = String.valueOf(aValue);
                if (!data.get(rowIndex).getOperacion().equals("i")) {
                    aux = String.valueOf(data.get(rowIndex).getUnidad());
                    data.get(rowIndex).setUnidad(valor);
                    if (aux.equals(valor)) {
                        data.get(rowIndex).setOperacion("n");
                    } else {
                        data.get(rowIndex).setOperacion("a");
                    }
                } else {
                    data.get(rowIndex).setUnidad(valor);
                }
                break;
            case 2:
                valor = String.valueOf(aValue);
                if (!data.get(rowIndex).getOperacion().equals("i")) {
                    aux = String.valueOf(data.get(rowIndex).getCodigoSunat());
                    data.get(rowIndex).setCodigoSunat(valor);
                    if (aux.equals(valor)) {
                        data.get(rowIndex).setOperacion("n");
                    } else {
                        data.get(rowIndex).setOperacion("a");
                    }
                } else {
                    data.get(rowIndex).setCodigoSunat(valor);
                }
                break;


            case 3:
                if (!data.get(rowIndex).getOperacion().equals("i")) {
                    data.get(rowIndex).setSelecccion((Boolean) aValue);
                    if (data.get(rowIndex).isSelecccion()) {
                        data.get(rowIndex).setOperacion("e");
                    } else {
                        data.get(rowIndex).setOperacion("n");
                    }
                } else {
                    data.remove(rowIndex);
                    listaCommit.remove(auxEmp);
                    eliminar = true;
                }
                break;
            default:
                break;
        }
        try {
            if (!eliminar) {
                if (data.get(rowIndex).getOperacion().equals("n")) {
                    listaCommit.remove(auxEmp);
                } else {
                    listaCommit.remove(auxEmp);
                    listaCommit.add(data.get(rowIndex));
                }
                Iterator iterar = listaCommit.iterator();
                System.out.println("TAMAÑO: " + listaCommit.size());
                while (iterar.hasNext()) {
                    BeanTabla6 bean = (BeanTabla6) iterar.next();
                    System.out.println(bean.getOperacion() + " " + bean.getUnidad());
                }
            }
            System.out.println("TAMAÑO: " + listaCommit.size());
        } catch (Exception ex) {
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
                return Boolean.class;

            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (data.get(rowIndex).getOperacion().equals("e")) {
            switch (columnIndex) {
                case 0:
                    return false;
                case 1:
                    return false;
                case 2:
                    return false;
                case 3:
                    return true;

                default:
                    return false;
            }
        } else {
            switch (columnIndex) {
                case 0:
                    return true;
                case 1:
                    return true;
                case 2:
                    return true;
                case 3:
                    return true;
               default:
                    return false;
            }
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

    public ArrayList<BeanTabla6> getData() {
        return data;
    }

    public void setData(ArrayList<BeanTabla6> data) {
        this.data = data;
        tablaDatos.setModel(this);
        tablaDatos.getColumnModel().getColumn(0).setMaxWidth(20);
        tablaDatos.getColumnModel().getColumn(0).setCellRenderer(new CellRedenderImage());

        // tablaDatos.getColumnModel().getColumn(4).setPreferredWidth(200);
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public HashSet<BeanTabla6> getListaCommit() {
        return listaCommit;
    }
}
