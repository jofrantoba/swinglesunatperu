/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.combobox;

import com.contab.bean.BeanEmpleado;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Administrador
 */
public class ComboModelEmpleado extends AbstractListModel implements MutableComboBoxModel {

    private Object selectedItem;
    private ArrayList<BeanEmpleado> data = new ArrayList();

    public ComboModelEmpleado() {        
    }

    public ComboModelEmpleado(ArrayList<BeanEmpleado> datos) {
        data = datos;
    }

    @Override
    public int getSize() {
        try {
            return data.size();
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    @Override
    public Object getElementAt(int index) {
        return (Object) data.get(index).getDescripcion();
    }

    public BeanEmpleado getElement(int index) {
        return data.get(index);
    }
    
    public BeanEmpleado getElement(String codigo) {
        BeanEmpleado  emp=new BeanEmpleado();
        Iterator i=data.iterator();
        while(i.hasNext()){
            BeanEmpleado  b=(BeanEmpleado)i.next();
            if(b.getCuentaAuxiliar().equals(codigo)){
                emp=b;                        
                return emp;
            }
        }
        return null;
    }

    @Override
    public void addElement(Object obj) {
        data.add((BeanEmpleado) obj);
        int length = getSize();
        fireIntervalAdded(this, length - 1, length - 1);
    }

    @Override
    public void removeElement(Object obj) {
        int index = data.indexOf((BeanEmpleado) obj);
        if (index != -1) {
            data.remove((BeanEmpleado) obj);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void insertElementAt(Object obj, int index) {
        data.add(index, (BeanEmpleado) obj);
        fireIntervalAdded(this, index, index);
    }

    @Override
    public void removeElementAt(int index) {
        if (getSize() >= index) {
            data.remove(index);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = anItem;
    }            

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    public ArrayList<BeanEmpleado> getData() {
        return data;
    }

    public void setData(ArrayList<BeanEmpleado> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
}

