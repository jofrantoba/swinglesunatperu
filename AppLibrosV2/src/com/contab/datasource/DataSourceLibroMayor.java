package com.contab.datasource;

import com.contab.bean.BeanLibroMayor;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSourceLibroMayor implements JRDataSource {

    private int cursor = -1;
    private List<BeanLibroMayor> beanlibromayor = new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor < beanlibromayor.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("auxiliar".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getAuxiliar();
        } else if ("vaucher".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getVaucher();
        } else if ("fecha".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getFecha();

        } else if ("centro".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getC_central();
        } else if ("glosa".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getGlosa();

        } else if ("debe".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getDebe();
        } else if ("haber".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getHaber();
        } else if ("deudor".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getDeudor();
        } else if ("acreedor".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getAcreedor();
        } else if ("cuenta".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getCuenta();
        } else if ("codigo_cuenta".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getCodigo_cuenta();
        } else if ("vienedebe".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getVienedebe();
        } else if ("vienehaber".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getVienehaber();
        } else if ("vienedeudor".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getVienedeudor();
        } else if ("vieneacreedor".equals(jrf.getName())) {
            valor = beanlibromayor.get(cursor).getVieneacreedor();
        }
        return valor;
    }

    public void add(BeanLibroMayor objLibroMayor) {
        this.beanlibromayor.add(objLibroMayor);
    }

    public List<BeanLibroMayor> getLibroMayor() {
        return beanlibromayor;
    }
}
