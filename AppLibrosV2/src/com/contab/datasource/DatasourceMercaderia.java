
package com.contab.datasource;

import com.contab.bean.BeanMercaderia;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DatasourceMercaderia implements JRDataSource {

  private int cursor=-1;
    private List<BeanMercaderia> BeanMercaderia= new ArrayList();
    @Override
    public boolean next() throws JRException {
        return ++cursor<BeanMercaderia.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("codigo".equals(jrf.getName())){
            valor=BeanMercaderia.get(cursor).getCodigo();
        } 
        else if("tipo5".equals(jrf.getName())){
            valor=BeanMercaderia.get(cursor).getTipo5();
        }else if("descripcion".equals(jrf.getName())){
            valor=BeanMercaderia.get(cursor).getDescripcion();
        }else if("tipo6".equals(jrf.getName())){
            valor=BeanMercaderia.get(cursor).getTipo6();
        }else if("cantidad".equals(jrf.getName())){
            valor=BeanMercaderia.get(cursor).getCantidad();
        }else if("costoUnitario".equals(jrf.getName())){
            valor=BeanMercaderia.get(cursor).getCostounitario();
        }
     
      
        return valor;
    }
    public void addBalance(BeanMercaderia mercaderia){
        this.BeanMercaderia.add(mercaderia);
    }

}
