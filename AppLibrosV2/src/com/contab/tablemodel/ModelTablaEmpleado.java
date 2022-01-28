/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.tablemodel;

import com.contab.bean.BeanEmpleado;
import com.contab.util.CellEditorCbCodAux;
import com.contab.util.CellEditorCbEmpresa;
import com.contab.util.CellRedenderImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrador
 */
public class ModelTablaEmpleado extends AbstractTableModel {

    private ArrayList<String> headerTable = new ArrayList();
    private ArrayList<BeanEmpleado> data = new ArrayList();
    private ArrayList<TableModelListener> listeners = new ArrayList();
    private JTable tablaDatos;    
    private HashSet <BeanEmpleado> listaCommit=new HashSet ();    
    public ModelTablaEmpleado(JTable tabla) {
        tablaDatos=tabla;
        createHeaders();
    }

    public ModelTablaEmpleado(ArrayList<BeanEmpleado> lista) {
        data = lista;
        createHeaders();
    }

    private void createHeaders() {
        headerTable.add("");        
        headerTable.add("COMPANIA");
        headerTable.add("COD AUXILIAR");
        headerTable.add("CUENTA AUXILIAR");
        headerTable.add("DESCRIPCION");
        headerTable.add("RUC / DNI");
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

    public void insertar(BeanEmpleado i) {
        data.add(i);
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        avisaSuscriptores(evento);
        fireTableDataChanged();
    }

    public void update(BeanEmpleado i, int fila) {
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

    public BeanEmpleado getValue(int rowIndex) {
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
                return data.get(rowIndex).getEstadoOperacion();//data.get(rowIndex).getCompania();            
            case 1:
                return data.get(rowIndex).getCompania();
            case 2:
                return data.get(rowIndex).getCodigoAuxiliar();
            case 3:
                return data.get(rowIndex).getCuentaAuxiliar();
            case 4:
                return data.get(rowIndex).getDescripcion();
            case 5:
                return data.get(rowIndex).getRuc();
            case 6:
                return data.get(rowIndex).isSeleccion();
            default:
                return null;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) { 
        String aux;
        String valor;
        BeanEmpleado auxEmp=data.get(rowIndex);
        boolean eliminar=false;
       switch (columnIndex) {
            case 0:                
                break;
            case 1:         
                try{
                if(data.get(rowIndex).getEstadoOperacion().equals("i")){
                data.get(rowIndex).setCompania(aValue.toString());      
                }}catch(Exception ex){}
                break;
            case 2:    
                try{
                if(data.get(rowIndex).getEstadoOperacion().equals("i")){
                data.get(rowIndex).setCodigoAuxiliar(aValue.toString());
                }}catch(Exception ex){}
                break;
            case 3:                
                if(data.get(rowIndex).getEstadoOperacion().equals("i")){
                    data.get(rowIndex).setCuentaAuxiliar(aValue.toString());
                }                
                break;
            case 4:         
                valor=String.valueOf(aValue);
                if(!data.get(rowIndex).getEstadoOperacion().equals("i")){
                aux=String.valueOf(data.get(rowIndex).getDescripcion());                
                data.get(rowIndex).setDescripcion(valor);
                if(aux.equals(valor)){
                    data.get(rowIndex).setEstadoOperacion("n");
                }else{
                    data.get(rowIndex).setEstadoOperacion("a");
                }
                }else{
                    data.get(rowIndex).setDescripcion(valor);
                }
                break;
            case 5:          
                valor=String.valueOf(aValue);
                if(!data.get(rowIndex).getEstadoOperacion().equals("i")){
                aux=String.valueOf(data.get(rowIndex).getRuc());                
                data.get(rowIndex).setRuc(valor);                
                if(aux.equals(valor)){
                    data.get(rowIndex).setEstadoOperacion("n");
                }else{
                    data.get(rowIndex).setEstadoOperacion("a");
                }
                }else{
                    data.get(rowIndex).setRuc(valor);                
                }
                break;
            case 6: 
                if(!data.get(rowIndex).getEstadoOperacion().equals("i")){
                    data.get(rowIndex).setSeleccion((Boolean)aValue);
                if(data.get(rowIndex).isSeleccion()){
                data.get(rowIndex).setEstadoOperacion("e");
                }else{
                data.get(rowIndex).setEstadoOperacion("n");
                }
                }else{
                    data.remove(rowIndex);        
                    listaCommit.remove(auxEmp);   
                    eliminar=true;
                }                
                break;
            default:
                break;
        }              
       try{
           if(!eliminar){
               if(data.get(rowIndex).getEstadoOperacion().equals("n")){
               listaCommit.remove(auxEmp);
           }else{
               listaCommit.remove(auxEmp);
               listaCommit.add(data.get(rowIndex));                                              
           }
           Iterator iterar=listaCommit.iterator();
           System.out.println("TAMAÑO: "+listaCommit.size());
           while(iterar.hasNext()){
               BeanEmpleado bean=(BeanEmpleado)iterar.next();               
               System.out.println(bean.getEstadoOperacion()+" "+bean.getDescripcion());
           }
           }
           System.out.println("TAMAÑO: "+listaCommit.size());
       }catch(Exception ex){}
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
                return Boolean.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(data.get(rowIndex).getEstadoOperacion().equals("e")){
            switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return false;
            case 3:
                return false;
            case 4:
                return false;
            case 5:
                return false;
            case 6:
                return true;
            default:
                return false;
            }
        }else{
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

    public ArrayList<BeanEmpleado> getData() {
        return data;
    }

    public void setData(ArrayList<BeanEmpleado> data) {
        this.data = data;
        tablaDatos.setModel(this);                
        tablaDatos.getColumnModel().getColumn(0).setMaxWidth(20);
        tablaDatos.getColumnModel().getColumn(0).setCellRenderer(new CellRedenderImage());
        tablaDatos.getColumnModel().getColumn(1).setCellEditor(new CellEditorCbEmpresa());
        tablaDatos.getColumnModel().getColumn(2).setCellEditor(new CellEditorCbCodAux());
        tablaDatos.getColumnModel().getColumn(4).setPreferredWidth(200);          
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public HashSet<BeanEmpleado> getListaCommit() {
        return listaCommit;
    }
    
}
