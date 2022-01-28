
package com.contab.datasource;

import com.contab.bean.BeanBalanceComprobacion;

import com.contab.bean.BeanSaldoCuenta42;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DatasourceSaldoCuenta42 implements JRDataSource {

  private int cursor=-1;
    private List<BeanSaldoCuenta42> BeanSaldoCuenta42= new ArrayList();
    @Override
    public boolean next() throws JRException {
        return ++cursor<BeanSaldoCuenta42.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("tabla2".equals(jrf.getName())){
            valor=BeanSaldoCuenta42.get(cursor).getTabla2();
        } 
        else if("numero".equals(jrf.getName())){
            valor=BeanSaldoCuenta42.get(cursor).getNumero();
        }else if("denominacion".equals(jrf.getName())){
            valor=BeanSaldoCuenta42.get(cursor).getDenominacion();
        }else if("monto".equals(jrf.getName())){
            valor=BeanSaldoCuenta42.get(cursor).getMonto();
        }else if("fecha".equals(jrf.getName())){
            valor=BeanSaldoCuenta42.get(cursor).getFecha();
        }else if("cuenta".equals(jrf.getName())){
            valor=BeanSaldoCuenta42.get(cursor).getCuenta();
        }
        else if("centro".equals(jrf.getName())){
            valor=BeanSaldoCuenta42.get(cursor).getCentro();
        }
        else if("preimpreso".equals(jrf.getName())){
            valor=BeanSaldoCuenta42.get(cursor).getPreimpreso();
        }
        else if("serie".equals(jrf.getName())){
            valor=BeanSaldoCuenta42.get(cursor).getSerie();
        }
         else if("tipo".equals(jrf.getName())){
            valor=BeanSaldoCuenta42.get(cursor).getTipoDoc();
        }
       
      
        return valor;
    }
    public void addBalance(BeanSaldoCuenta42 saldoCuenta42){
        this.BeanSaldoCuenta42.add(saldoCuenta42);
    }

}
