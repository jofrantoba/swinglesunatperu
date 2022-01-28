/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.combobox;

import com.contab.bean.BeanCodigoAuxiliar;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Administrador
 */
public class ComboModelCodAux extends AbstractListModel implements MutableComboBoxModel {

    private Object selectedItem;
    private ArrayList<BeanCodigoAuxiliar> data = new ArrayList();

    public ComboModelCodAux() {
        BeanCodigoAuxiliar bean1=new BeanCodigoAuxiliar();
        BeanCodigoAuxiliar bean2=new BeanCodigoAuxiliar();
        bean1.setCodigo("EMP");
        bean1.setDescripcion("EMPLEADOS");
        bean2.setCodigo("ACC");
        bean2.setDescripcion("ACCIONISTAS");
        data.add(bean1);
        data.add(bean2);
    }

    public ComboModelCodAux(ArrayList<BeanCodigoAuxiliar> datos) {
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

    public BeanCodigoAuxiliar getElement(int index) {
        return data.get(index);
    }
    
    public BeanCodigoAuxiliar getElement(String codigo) {
        BeanCodigoAuxiliar  emp=new BeanCodigoAuxiliar();
        Iterator i=data.iterator();
        while(i.hasNext()){
            BeanCodigoAuxiliar  b=(BeanCodigoAuxiliar)i.next();
            if(b.getCodigo().equals(codigo)){
                emp=b;                        
                return emp;
            }
        }
        return null;
    }

    @Override
    public void addElement(Object obj) {
        data.add((BeanCodigoAuxiliar) obj);
        int length = getSize();
        fireIntervalAdded(this, length - 1, length - 1);
    }

    @Override
    public void removeElement(Object obj) {
        int index = data.indexOf((BeanCodigoAuxiliar) obj);
        if (index != -1) {
            data.remove((BeanCodigoAuxiliar) obj);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void insertElementAt(Object obj, int index) {
        data.add(index, (BeanCodigoAuxiliar) obj);
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

    public ArrayList<BeanCodigoAuxiliar> getData() {
        return data;
    }

    public void setData(ArrayList<BeanCodigoAuxiliar> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
}

