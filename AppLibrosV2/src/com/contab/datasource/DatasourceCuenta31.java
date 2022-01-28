package com.contab.datasource;

import com.contab.bean.BeanBalanceComprobacion;

import com.contab.bean.BeanCuenta40;
import com.contab.bean.BeanCuenta31;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DatasourceCuenta31 implements JRDataSource {

    private int cursor = -1;
    private List<BeanCuenta31> BeanCuenta40 = new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor < BeanCuenta40.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("tabla2".equals(jrf.getName())) {
            valor = BeanCuenta40.get(cursor).getTabla2();

        } else if ("numero".equals(jrf.getName())) {
            valor = BeanCuenta40.get(cursor).getNumero();
        } else if ("denominacionRazon".equals(jrf.getName())) {
            valor = BeanCuenta40.get(cursor).getDenominacion();
        } else if ("denominacion".equals(jrf.getName())) {
            valor = BeanCuenta40.get(cursor).getDenominacion();
        } else if ("valorMomial".equals(jrf.getName())) {
            valor = BeanCuenta40.get(cursor).getValorNominal();
        } else if ("cantidad".equals(jrf.getName())) {
            valor = BeanCuenta40.get(cursor).getCantidad();
        } else if ("costoTotal".equals(jrf.getName())) {
            valor = BeanCuenta40.get(cursor).getCostoTotal();
        } else if ("provisionTotal".equals(jrf.getName())) {
            valor = BeanCuenta40.get(cursor).getProvisionTotal();
        } else if ("totalNeto".equals(jrf.getName())) {
            valor = BeanCuenta40.get(cursor).getTotalNeto();
        }


        return valor;
    }

    public void addBalance(BeanCuenta31 saldoCuenta31) {
        this.BeanCuenta40.add(saldoCuenta31);
    }
}
