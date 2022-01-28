/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.combobox;

import com.contab.bean.BeanMes;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.EventListenerList;

/**
 *
 * @author cixtic04
 */
public class ComboModelMes  extends AbstractListModel implements MutableComboBoxModel {

    private Object selectedItem;
    private ArrayList<BeanMes> data = new ArrayList();

    public ComboModelMes() {
        BeanMes mes1=new BeanMes("01","Enero");
        data.add(mes1);
        BeanMes mes2=new BeanMes("02","Febrero");
        data.add(mes2);
        BeanMes mes3=new BeanMes("03","Marzo");
         data.add(mes3);
        BeanMes mes4=new BeanMes("04","Abril");
         data.add(mes4);
        BeanMes mes5=new BeanMes("05","Mayo");
         data.add(mes5);
        BeanMes mes6=new BeanMes("06","Junio");
         data.add(mes6);
        BeanMes mes7=new BeanMes("07","Julio");
         data.add(mes7);
        BeanMes mes8=new BeanMes("08","Agosto");
         data.add(mes8);
        BeanMes mes9=new BeanMes("09","Setiembre");
         data.add(mes9);
        BeanMes mes10=new BeanMes("10","Octubre");
         data.add(mes10);
        BeanMes mes11=new BeanMes("11","Noviembre");
         data.add(mes11);
        BeanMes mes12=new BeanMes("12","Diciembre");
         data.add(mes12);
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

    public BeanMes getElement(int index) {
        return data.get(index);
    }

    @Override
    public void addElement(Object obj) {
        data.add((BeanMes) obj);
        int length = getSize();
        fireIntervalAdded(this, length - 1, length - 1);
    }

    @Override
    public void removeElement(Object obj) {
        int index = data.indexOf((BeanMes) obj);
        if (index != -1) {
            data.remove((BeanMes) obj);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void insertElementAt(Object obj, int index) {
        data.add(index, (BeanMes) obj);
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

    public ArrayList<BeanMes> getData() {
        return data;
    }

    public void setData(ArrayList<BeanMes> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
}

