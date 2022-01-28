/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.combobox;

import com.contab.bean.BeanEmpresa;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.EventListenerList;

/**
 *
 * @author 777
 */
public class ComboModelEmpresas extends AbstractListModel implements MutableComboBoxModel {

    private Object selectedItem;
    private ArrayList<BeanEmpresa> data = new ArrayList();

    public ComboModelEmpresas() {
    }

    public ComboModelEmpresas(ArrayList<BeanEmpresa> datos) {
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

    public BeanEmpresa getElement(int index) {
        return data.get(index);
    }
    
    public BeanEmpresa getElement(String codigo) {
        BeanEmpresa  emp=new BeanEmpresa();
        Iterator i=data.iterator();
        while(i.hasNext()){
            BeanEmpresa  b=(BeanEmpresa)i.next();
            if(b.getCodigo().equals(codigo)){
                emp=b;                        
                return emp;
            }
        }
        return null;
    }

    @Override
    public void addElement(Object obj) {
        data.add((BeanEmpresa) obj);
        int length = getSize();
        fireIntervalAdded(this, length - 1, length - 1);
    }

    @Override
    public void removeElement(Object obj) {
        int index = data.indexOf((BeanEmpresa) obj);
        if (index != -1) {
            data.remove((BeanEmpresa) obj);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void insertElementAt(Object obj, int index) {
        data.add(index, (BeanEmpresa) obj);
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

    public ArrayList<BeanEmpresa> getData() {
        return data;
    }

    public void setData(ArrayList<BeanEmpresa> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
}

