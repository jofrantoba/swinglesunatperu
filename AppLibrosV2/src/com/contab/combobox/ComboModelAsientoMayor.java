package com.contab.combobox;


import com.contab.bean.BeanAsientoMayor;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.EventListenerList;

public class ComboModelAsientoMayor extends AbstractListModel implements MutableComboBoxModel {

    private Object selectedItem;
    private ArrayList<BeanAsientoMayor> data = new ArrayList();

    public ComboModelAsientoMayor() {
    }

    public ComboModelAsientoMayor(ArrayList<BeanAsientoMayor> datos) {
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

    public BeanAsientoMayor getElement(int index) {
        return data.get(index);
    }

    @Override
    public void addElement(Object obj) {
        data.add((BeanAsientoMayor) obj);
        int length = getSize();
        fireIntervalAdded(this, length - 1, length - 1);
    }

    @Override
    public void removeElement(Object obj) {
        int index = data.indexOf((BeanAsientoMayor) obj);
        if (index != -1) {
            data.remove((BeanAsientoMayor) obj);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void insertElementAt(Object obj, int index) {
        data.add(index, (BeanAsientoMayor) obj);
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

    public ArrayList<BeanAsientoMayor> getData() {
        return data;
    }

    public void setData(ArrayList<BeanAsientoMayor> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

}
