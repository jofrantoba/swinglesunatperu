/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.datasource;

import com.contab.bean.BeanFormato319;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author cixtic03
 */
public class DataSourceFormato319 implements  JRDataSource{
    
     private int cursor = -1;
    private List<BeanFormato319> formato319 = new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor < formato319.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("empresa".equals(jrf.getName())) {
            valor = formato319.get(cursor).getCompania();
        } else if ("centro".equals(jrf.getName())) {
            valor = formato319.get(cursor).getCenter();
        } else if ("cuenta".equals(jrf.getName())) {
            valor = formato319.get(cursor).getCuenta();
        } else if ("nombrecuenta".equals(jrf.getName())) {
            valor = formato319.get(cursor).getDescripcion();
        } else if ("saldo".equals(jrf.getName())) {
            valor = formato319.get(cursor).getSaldo();
        } else if ("jrnnumber".equals(jrf.getName())) {
            valor = formato319.get(cursor).getJrnnumber();
        }else if ("saldodoc".equals(jrf.getName())) {
            valor = formato319.get(cursor).getSaldodoc();
        }else if ("fechacreacion".equals(jrf.getName())) {
            valor = formato319.get(cursor).getFechacreacion();
        }else if ("glosa".equals(jrf.getName())) {
            valor = formato319.get(cursor).getGlosa();
        }else if ("proveedor".equals(jrf.getName())) {
            valor = formato319.get(cursor).getProveedor();
        }
        return valor;
    }

    public void add(BeanFormato319 objCuentasxPagar) {
        this.formato319.add(objCuentasxPagar);
    }

    public List<BeanFormato319> getformato319() {
        return formato319;
    }
    
}
