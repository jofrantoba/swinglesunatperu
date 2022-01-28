package com.contab.datasource;

import com.contab.bean.BeanBalanceComprobacion;

import com.contab.bean.BeanKardex;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DatasourceKardex implements JRDataSource {

    private int cursor = -1;
    private List<BeanKardex> BeanKardex = new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor < BeanKardex.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("fecha".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getFecha();
        } else if ("tabla10".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getTabla10();
        } else if ("serie".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getSerie();
        } else if ("numero".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getNumero();
        } else if ("tabla12".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getTabla12();
        } else if ("cantidadE".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getCantidadE();
        } else if ("costoUE".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getCostoUE();
        } else if ("totalE".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getTotalE();
        } else if ("cantidadS".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getCantidadS();
        } else if ("costoUS".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getCostoUS();
        } else if ("totalS".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getTotalS();
        } else if ("cantidadF".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getCantidadF();
        } else if ("costoUF".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getCostoUF();
        } else if ("totalF".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getTotalF();
        }
        
         else if ("establecimiento".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getEstablecimiento();
        }
         else if ("producto".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getProducto();
        }
         else if ("itemProducto".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getItemProducto();
        }
        else if ("unidad".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getUnidad();
        }
        
         else if ("codigoAgrupacion".equals(jrf.getName())) {
            valor = BeanKardex.get(cursor).getCodigoAgrupacion();
        }


        return valor;
    }

    public void addBalance(BeanKardex saldoCuenta42) {
        this.BeanKardex.add(saldoCuenta42);
    }
}
