/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.datasource;

import com.contab.bean.BeanBICuenta14;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Administrador
 */
public class DataSourceBICuenta14 implements JRDataSource {
    private int cursor=-1;
    private List<BeanBICuenta14> listacuenta14= new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor<listacuenta14.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("CODAUX".equals(jrf.getName())){
            valor=listacuenta14.get(cursor).getTipoper();
        }else if("TIPODOC".equals(jrf.getName())){            
            valor=listacuenta14.get(cursor).getTipodoc();
        }else if("DNI".equals(jrf.getName())){
            valor=listacuenta14.get(cursor).getDni();
        }else if("DESCRIPCION".equals(jrf.getName())){
            valor=listacuenta14.get(cursor).getDescripcion();
        }else if("MONTO".equals(jrf.getName())){
            valor=listacuenta14.get(cursor).getMonto();
        }else if("FECHADOC".equals(jrf.getName())){
            valor=listacuenta14.get(cursor).getFechadoc();
        }        
        return valor;
    }
    
    public void add(BeanBICuenta14 objCuenta14){
        this.listacuenta14.add(objCuenta14);
    }

    public List<BeanBICuenta14> getLibroDiario() {
        return listacuenta14;
    }
    
    
}
