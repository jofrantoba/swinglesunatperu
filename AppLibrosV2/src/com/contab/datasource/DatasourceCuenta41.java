package com.contab.datasource;

import com.contab.bean.BeanBalanceComprobacion;

import com.contab.bean.BeanCuenta41;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DatasourceCuenta41 implements JRDataSource {

    private int cursor = -1;
    private List<BeanCuenta41> BeanCuenta41 = new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor < BeanCuenta41.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("codigo".equals(jrf.getName())) {
            valor = BeanCuenta41.get(cursor).getCodigo();
        } else if ("denominacion".equals(jrf.getName())) {
            valor = BeanCuenta41.get(cursor).getDenominacion();
        } else if ("codigo1".equals(jrf.getName())) {
            valor = BeanCuenta41.get(cursor).getCodigo1();
        } else if ("apellidosNombre".equals(jrf.getName())) {
            valor = BeanCuenta41.get(cursor).getApellidosNombre();
        } else if ("tabla2".equals(jrf.getName())) {
            valor = BeanCuenta41.get(cursor).getTabla2();
        } else if ("numero".equals(jrf.getName())) {
            valor = BeanCuenta41.get(cursor).getNumero();
        } else if ("saldoFinal".equals(jrf.getName())) {
            valor = BeanCuenta41.get(cursor).getSaldoFinal();
        }


        return valor;
    }

    public void addBalance(BeanCuenta41 saldoCuenta42) {
        this.BeanCuenta41.add(saldoCuenta42);
    }
}
