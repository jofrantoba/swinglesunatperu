
package com.contab.datasource;

import com.contab.bean.BeanBalanceComprobacion;

import com.contab.bean.BeanCuenta40;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DatasourceCuenta40 implements JRDataSource {

  private int cursor=-1;
    private List<BeanCuenta40> BeanCuenta40= new ArrayList();
    @Override
    public boolean next() throws JRException {
        return ++cursor<BeanCuenta40.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("codigo".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getCodigo();
        } 
       else if("denominacion".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getDenominacion();
        }else if("saldoFinal".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getSaldoFinal();
        }
       
      
        return valor;
    }
    public void addBalance(BeanCuenta40 saldoCuenta42){
        this.BeanCuenta40.add(saldoCuenta42);
    }

}
