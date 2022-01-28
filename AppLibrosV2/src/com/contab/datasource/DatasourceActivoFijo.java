
package com.contab.datasource;

import com.contab.bean.BeanBalanceComprobacion;

import com.contab.bean.BeanActivoFijo;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DatasourceActivoFijo implements JRDataSource {

  private int cursor=-1;
    private List<BeanActivoFijo> BeanActivoFijo= new ArrayList();
    @Override
    public boolean next() throws JRException {
        return ++cursor<BeanActivoFijo.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("cuenta".equals(jrf.getName())){
            valor=BeanActivoFijo.get(cursor).getCuenta();
         
        
      }else if("centro".equals(jrf.getName())){
            valor=BeanActivoFijo.get(cursor).getCentro();
        }else if("jrnnumber".equals(jrf.getName())){
            valor=BeanActivoFijo.get(cursor).getJrnnumber();
        
       }
        else if("agrupar".equals(jrf.getName())){
            valor=BeanActivoFijo.get(cursor).getAgrupar();
        
       }
        else if("debe".equals(jrf.getName())){
            valor=BeanActivoFijo.get(cursor).getDebe();
        
       }
        else if("haber".equals(jrf.getName())){
            valor=BeanActivoFijo.get(cursor).getHaber();
        
       }
         else if("saldo".equals(jrf.getName())){
            valor=BeanActivoFijo.get(cursor).getSaldo();
        
       }
        else if("NombreCuenta".equals(jrf.getName())){
            valor=BeanActivoFijo.get(cursor).getNombreCuenta();
        
       }
        else if("glosa".equals(jrf.getName())){
            valor=BeanActivoFijo.get(cursor).getGlosa();
        
       }
      
        return valor;
    }
    public void addBalance(BeanActivoFijo saldoCuenta42){
        this.BeanActivoFijo.add(saldoCuenta42);
    }

}
