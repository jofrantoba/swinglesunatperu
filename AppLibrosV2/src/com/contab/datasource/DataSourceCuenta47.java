/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.datasource;

import com.contab.bean.BeanCuenta47;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author cixtic04
 */
public class DataSourceCuenta47 implements JRDataSource {

    private int cursor = -1;
    private List<BeanCuenta47> Cuenta47 = new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor < Cuenta47.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("tipo".equals(jrf.getName())) {
            valor = Cuenta47.get(cursor).getTiposunat();
        } else if ("numero".equals(jrf.getName())) {
            valor = Cuenta47.get(cursor).getRucdni();
        } else if ("nombre".equals(jrf.getName())) {
            valor = Cuenta47.get(cursor).getNombreapellido();
        } else if ("saldofinal".equals(jrf.getName())) {
            valor = Cuenta47.get(cursor).getSaldofinal();
        } else if ("numcuenta".equals(jrf.getName())) {
            valor = Cuenta47.get(cursor).getNumcuenta();
        } else if ("nombrecuenta".equals(jrf.getName())) {
            valor = Cuenta47.get(cursor).getNombrecuenta();
        }
        return valor;
    }

    public void add(BeanCuenta47 objCuentasxPagar) {
        this.Cuenta47.add(objCuentasxPagar);
    }

    public List<BeanCuenta47> getCuenta47() {
        return Cuenta47;
    }
}
