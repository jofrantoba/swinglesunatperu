
package com.contab.datasource;

import com.contab.bean.beanCaja;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class datasourceCaja implements JRDataSource {

  private int cursor=-1;
    private List<beanCaja> beanCaja= new ArrayList();
    @Override
    public boolean next() throws JRException {
        return ++cursor<beanCaja.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("haber".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getHaber();
        }else if("debe".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getDebe();
        }else if("deudor".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getDeudor();

        }else if("acreedor".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getAcreedor();
        }else if("fecha".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getFecha();

         }else if("cuenta".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getNumCuenta();
        }else if("nombreCuenta".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getNomCuenta();
        }
        else if("glosa".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getGlosa();
        }
         else if("numero".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getNumResgistro();
        }
         else if("pago".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getPago();
        }
         else if("descripcion".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getDescripcion();
        }
         else if("denominacion".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getDenominacion();
        }
         else if("num".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getNumero();
        }
         else if("saldo".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getSaldo();
        }
        else if("cuentaAgrupar".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getAgrupar();
        }
        else if("entidad".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getEntidad();
        }
         else if("tipoMoneda".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getTipoMoneda();
        }
         else if("sumaDeber".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getSumaDebe();
        }
         else if("sumaHaber".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getSumaHaber();
        }
         else if("numeroCuenta".equals(jrf.getName())){
            valor=beanCaja.get(cursor).getNumeroCuenta();
        }else if("repetir".equals(jrf.getName())){
            String a="A";
            valor=a;
        }
        return valor;
    }
    public void addCaja(beanCaja caja){
        this.beanCaja.add(caja);
    }

}
