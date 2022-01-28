
package com.contab.datasource;

import com.contab.bean.BeanBalanceComprobacion;

import com.contab.bean.BeanCuenta40;
import com.contab.bean.BeanCuenta50;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DatasourceCuenta50 implements JRDataSource {

  private int cursor=-1;
    private List<BeanCuenta50> BeanCuenta40= new ArrayList();
    @Override
    public boolean next() throws JRException {
        return ++cursor<BeanCuenta40.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("tabla2".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getTabla2();
        } 
       else if("numero".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getDocumento();
        }else if("denominacion".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getDenominacion();
        }
        else if("tipoAcciones".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getTipoAccion();
        }
         else if("numeroAcciones".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getNumeroAccion();
        }
       
      
        return valor;
    }
    public void addBalance(BeanCuenta50 saldoCuenta50){
        this.BeanCuenta40.add(saldoCuenta50);
    }

}
