/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.tablemodel;

import com.contab.bean.BeanCuenta50;
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
public class ModelCuenta50 extends AbstractTableModel {

    private ArrayList<String> headerTable = new ArrayList();
    private ArrayList<BeanCuenta50> data = new ArrayList();
    private ArrayList<TableModelListener> listeners = new ArrayList();
    private JTable tablaDatos;
    private HashSet<BeanCuenta50> listaCommit = new HashSet();

    public ModelCuenta50(JTable tabla) {
        tablaDatos = tabla;
        createHeaders();
    }

    public ModelCuenta50(ArrayList<BeanCuenta50> lista) {
        data = lista;
        createHeaders();
    }

    private void createHeaders() {
        headerTable.add("");
        headerTable.add("COMPANIA");
        headerTable.add("DENOMINACIÓN");
        headerTable.add("N°DOCUMENTO");
        headerTable.add("TIPO ACCION ");
        headerTable.add("NÚMERO DE ACCIONES");
        headerTable.add("CAPITAL");
        headerTable.add("AÑO");
        headerTable.add("ELIMINAR");
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

    public void insertar(BeanCuenta50 i) {
        data.add(i);
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        avisaSuscriptores(evento);
        fireTableDataChanged();
    }

    public void update(BeanCuenta50 i, int fila) {
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

    public BeanCuenta50 getValue(int rowIndex) {
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
                return data.get(rowIndex).getCompania();
            case 2:
                return data.get(rowIndex).getDenominacion();
            case 3:
                return data.get(rowIndex).getDocumento();
            case 4:
                return data.get(rowIndex).getTipoAccion();
            case 5:
                return data.get(rowIndex).getNumeroAccion();
            case 6:
                return data.get(rowIndex).getCapital();

            case 7:
                return data.get(rowIndex).getAnno();
            case 8:
                return data.get(rowIndex).isSelecccion();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        String aux;
        String valor;
        BeanCuenta50 auxEmp = data.get(rowIndex);
        boolean eliminar = false;
        switch (columnIndex) {
            case 0:
                break;
            case 1:
                valor = String.valueOf(aValue);
                if (!data.get(rowIndex).getOperacion().equals("i")) {
                    aux = String.valueOf(data.get(rowIndex).getCompania());
                    data.get(rowIndex).setCompania(valor);
                    if (aux.equals(valor)) {
                        data.get(rowIndex).setOperacion("n");
                    } else {
                        data.get(rowIndex).setOperacion("a");
                    }
                } else {
                    data.get(rowIndex).setCompania(valor);
                }
                break;
            case 2:
                valor = String.valueOf(aValue);
                if (!data.get(rowIndex).getOperacion().equals("i")) {
                    aux = String.valueOf(data.get(rowIndex).getDenominacion());
                    data.get(rowIndex).setDenominacion(valor);
                    if (aux.equals(valor)) {
                        data.get(rowIndex).setOperacion("n");
                    } else {
                        data.get(rowIndex).setOperacion("a");
                    }
                } else {
                    data.get(rowIndex).setDenominacion(valor);
                }
                break;
            case 3:
               try {
                    BigDecimal valorP = BigDecimal.valueOf(Double.valueOf(aValue.toString()));

                    //   double valorMonial = Double.valueOf(valorP).doubleValue();
                    if (!data.get(rowIndex).getOperacion().equals("i")) {
                        aux = String.valueOf(data.get(rowIndex).getDocumento());
                        data.get(rowIndex).setDocumento(valorP);
                        if (aux.equals(valorP)) {
                            data.get(rowIndex).setOperacion("n");
                        } else {
                            data.get(rowIndex).setOperacion("a");
                        }
                    } else {
                        data.get(rowIndex).setDocumento(valorP);


                    }
                } catch (Exception ex) {
                }
                break;
            case 4:
                valor = String.valueOf(aValue);
                if (!data.get(rowIndex).getOperacion().equals("i")) {
                    aux = String.valueOf(data.get(rowIndex).getTipoAccion());
                    data.get(rowIndex).setTipoAccion(valor);
                    if (aux.equals(valor)) {
                        data.get(rowIndex).setOperacion("n");
                    } else {
                        data.get(rowIndex).setOperacion("a");
                    }
                } else {
                    data.get(rowIndex).setTipoAccion(valor);
                }
                break;
            case 5:
                try {
                    valor = String.valueOf(aValue);
                    if (!data.get(rowIndex).getOperacion().equals("i")) {
                        aux = String.valueOf(data.get(rowIndex).getNumeroAccion());
                        data.get(rowIndex).setNumeroAccion(Integer.parseInt(valor));
                        if (aux.equals(valor)) {
                            data.get(rowIndex).setOperacion("n");
                        } else {
                            data.get(rowIndex).setOperacion("a");
                        }
                    } else {
                        data.get(rowIndex).setNumeroAccion(Integer.parseInt(valor));
                    }
                } catch (Exception ex) {
                }
                break;

            case 6:
                try {
                    valor = String.valueOf(aValue);
                    double capital = Double.valueOf(valor).doubleValue();
                    if (!data.get(rowIndex).getOperacion().equals("i")) {
                        aux = String.valueOf(data.get(rowIndex).getCapital());
                        data.get(rowIndex).setCapital(BigDecimal.valueOf(capital));
                        if (aux.equals(valor)) {
                            data.get(rowIndex).setOperacion("n");
                        } else {
                            data.get(rowIndex).setOperacion("a");
                        }
                    } else {
                        data.get(rowIndex).setCapital(BigDecimal.valueOf(capital));
                    }
                } catch (Exception ex) {
                }
                break;

            case 7:
                valor = String.valueOf(aValue);
                if (!data.get(rowIndex).getOperacion().equals("i")) {
                    aux = String.valueOf(data.get(rowIndex).getAnno());
                    data.get(rowIndex).setAnno(valor);
                    if (aux.equals(valor)) {
                        data.get(rowIndex).setOperacion("n");
                    } else {
                        data.get(rowIndex).setOperacion("a");
                    }
                } else {
                    data.get(rowIndex).setAnno(valor);
                }
                break;
            case 8:
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
                    BeanCuenta50 bean = (BeanCuenta50) iterar.next();
                    System.out.println(bean.getOperacion() + " " + bean.getDenominacion());
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
                    return false;
                case 7:
                    return false;
                case 8:
                    return true;
                default:
                    return false;
            }
        } else {
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
                    return true;
                case 8:
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

    public ArrayList<BeanCuenta50> getData() {
        return data;
    }

    public void setData(ArrayList<BeanCuenta50> data) {
        this.data = data;
        tablaDatos.setModel(this);
        tablaDatos.getColumnModel().getColumn(0).setMaxWidth(20);
        tablaDatos.getColumnModel().getColumn(0).setCellRenderer(new CellRedenderImage());
        tablaDatos.getColumnModel().getColumn(1).setCellEditor(new CellEditorCbEmpresa());
        //tablaDatos.getColumnModel().getColumn(2).setCellEditor(new CellEditorCbCodAux());
        tablaDatos.getColumnModel().getColumn(4).setPreferredWidth(200);
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public HashSet<BeanCuenta50> getListaCommit() {
        return listaCommit;
    }
}
