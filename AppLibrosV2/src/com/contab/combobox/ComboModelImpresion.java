/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.combobox;

import com.contab.bean.BeanImpresion;
import com.contab.bean.BeanImpresion;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.EventListenerList;

/**
 *
 * @author cixtic04
 */
public class ComboModelImpresion  extends AbstractListModel implements MutableComboBoxModel {

    private Object selectedItem;
    private ArrayList<BeanImpresion> data = new ArrayList();

    public ComboModelImpresion() {
        BeanImpresion mes1=new BeanImpresion("C","Continuo");
        data.add(mes1);
        BeanImpresion mes2=new BeanImpresion("L","Lasert");
        data.add(mes2);
      
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
        return (Object) data.get(index).getNombre();
    }

    public BeanImpresion getElement(int index) {
        return data.get(index);
    }

    @Override
    public void addElement(Object obj) {
        data.add((BeanImpresion) obj);
        int length = getSize();
        fireIntervalAdded(this, length - 1, length - 1);
    }

    @Override
    public void removeElement(Object obj) {
        int index = data.indexOf((BeanImpresion) obj);
        if (index != -1) {
            data.remove((BeanImpresion) obj);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void insertElementAt(Object obj, int index) {
        data.add(index, (BeanImpresion) obj);
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

    public ArrayList<BeanImpresion> getData() {
        return data;
    }

    public void setData(ArrayList<BeanImpresion> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
}

