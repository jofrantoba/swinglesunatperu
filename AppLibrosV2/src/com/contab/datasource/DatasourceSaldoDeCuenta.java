
package com.contab.datasource;

import com.contab.bean.BeanBalanceComprobacion;

import com.contab.bean.BeanSaldoDeCuenta;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DatasourceSaldoDeCuenta implements JRDataSource {

  private int cursor=-1;
    private List<BeanSaldoDeCuenta> BeanSaldoDeCuenta= new ArrayList();
    @Override
    public boolean next() throws JRException {
        return ++cursor<BeanSaldoDeCuenta.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("tabla".equals(jrf.getName())){
            valor=BeanSaldoDeCuenta.get(cursor).getTabla();
        } 
        else if("numero".equals(jrf.getName())){
            valor=BeanSaldoDeCuenta.get(cursor).getNumero();
        }else if("denominacion".equals(jrf.getName())){
            valor=BeanSaldoDeCuenta.get(cursor).getDenominacion();
        }else if("monto".equals(jrf.getName())){
            valor=BeanSaldoDeCuenta.get(cursor).getMonto();
        }else if("fecha".equals(jrf.getName())){
            valor=BeanSaldoDeCuenta.get(cursor).getFechaEmision();
        }else if("preimpreso".equals(jrf.getName())){
            valor=BeanSaldoDeCuenta.get(cursor).getPreimpreso();
        }else if("cuenta".equals(jrf.getName())){
            valor=BeanSaldoDeCuenta.get(cursor).getCuenta();
        }else if("centro".equals(jrf.getName())){
            valor=BeanSaldoDeCuenta.get(cursor).getCentro();
        }else if("serie".equals(jrf.getName())){
            valor=BeanSaldoDeCuenta.get(cursor).getSerie();
        }
       
      
        return valor;
    }
    public void addBalance(BeanSaldoDeCuenta balance){
        this.BeanSaldoDeCuenta.add(balance);
    }

}
