
package com.contab.datasource;

import com.contab.bean.BeanBalanceComprobacion;

import com.contab.bean.BeanCuenta40;
import com.contab.bean.BeanDobleAsiento;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DatasourceAsientoDoble implements JRDataSource {

  private int cursor=-1;
    private List<BeanDobleAsiento> BeanCuenta40= new ArrayList();
    @Override
    public boolean next() throws JRException {
        return ++cursor<BeanCuenta40.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("cuenta".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getCuenta();
        } 
       else if("centro".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getCentro();
        }else if("debeSoles".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getDeberSoles();
        }
        else if("haberSoles".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getHaberSoles();
        }
         else if("saldoActualSoles".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getSaldoSoles();
        }

        else if("deberDolares".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getDeberDolares();
        }
        else if("haberDolares".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getHaberDolares();
        }
         else if("saldoActualDolares".equals(jrf.getName())){
            valor=BeanCuenta40.get(cursor).getSaldoDolares();
        }
       
      
        return valor;
    }
    public void addBalance(BeanDobleAsiento saldoCuenta50){
        this.BeanCuenta40.add(saldoCuenta50);
    }

}
