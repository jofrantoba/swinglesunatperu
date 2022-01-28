
package com.contab.datasource;

import com.contab.bean.BeanBalanceComprobacion;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DatasourceBalanceComprobacion implements JRDataSource {

  private int cursor=-1;
    private List<BeanBalanceComprobacion> BeanBalanceComprobacion= new ArrayList();
    @Override
    public boolean next() throws JRException {
        return ++cursor<BeanBalanceComprobacion.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("cuenta".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getCuenta();
        } 
        else if("denominacion".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getDenominacion();
        }else if("debeMovimiento".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getDebeMovimiento();
        }else if("haberMovimiento".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getHaberMovimiento();
        }else if("debeSaldoInicial".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getDebeSaldoInicial();
        }else if("haberSaldoInicial".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getHaberSalidoInicial();
        }
        //M S T F R
        else if("flag".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getFlag();
        } else if("MDebe".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getMDebe();
        }else if("MHaber".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getMHaber();
        }else if("SDebe".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getSDebe();
        }else if("SHaber".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getSHaber();
        }else if("TDebe".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getTDebe();
        }
        else if("THaber".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getTHaber();
        }
        //M S T F R
      else if("CDebe".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getFDebe();
        }else if("CHabe".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getFHaber();
        }else if("RDebe".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getRDebe();
        }else if("RHaber".equals(jrf.getName())){
            valor=BeanBalanceComprobacion.get(cursor).getRHaber();
        }
       
      
        return valor;
    }
    public void addBalance(BeanBalanceComprobacion balance){
        this.BeanBalanceComprobacion.add(balance);
    }

}
