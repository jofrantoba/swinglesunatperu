package com.contab.combobox;

import com.contab.bean.BeanCuentaMayor;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.EventListenerList;

public class ComboModelCuentaMayor extends AbstractListModel implements MutableComboBoxModel{
    
    private Object selectedItem;
    private ArrayList<BeanCuentaMayor> data = new ArrayList();

    public ComboModelCuentaMayor() {
    }

    public ComboModelCuentaMayor(ArrayList<BeanCuentaMayor> datos) {
        this.data=datos;
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
        return (Object) data.get(index).getNombrecuenta();
    }
    
     public BeanCuentaMayor getElement(int index) {
        return data.get(index);
    }

    @Override
    public void addElement(Object obj) {
        data.add((BeanCuentaMayor) obj);
        int length = getSize();
        fireIntervalAdded(this, length - 1, length - 1);
    }

    @Override
    public void removeElement(Object obj) {
        int index = data.indexOf((BeanCuentaMayor) obj);
        if (index != -1) {
            data.remove((BeanCuentaMayor) obj);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void insertElementAt(Object obj, int index) {
        data.add(index, (BeanCuentaMayor) obj);
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
    
    public ArrayList<BeanCuentaMayor> getData() {
        return data;
    }

    public void setData(ArrayList<BeanCuentaMayor> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
    
}
