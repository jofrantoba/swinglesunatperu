/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.datasource;

import com.contab.bean.BeanBICuenta16;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Administrador
 */
public class DataSourceBICuenta16 implements JRDataSource {
    private int cursor=-1;
    private List<BeanBICuenta16> listacuenta16= new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor<listacuenta16.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("CODAUX".equals(jrf.getName())){
            valor=listacuenta16.get(cursor).getTipoper();
        }else if("TIPODOC".equals(jrf.getName())){            
            valor=listacuenta16.get(cursor).getTipodoc();
        }else if("DNI".equals(jrf.getName())){
            valor=listacuenta16.get(cursor).getDni();
        }else if("DESCRIPCION".equals(jrf.getName())){
            valor=listacuenta16.get(cursor).getDescripcion();
        }else if("MONTO".equals(jrf.getName())){
            valor=listacuenta16.get(cursor).getMonto();
        }else if("FECHADOC".equals(jrf.getName())){
            valor=listacuenta16.get(cursor).getFechadoc();
        }        
        return valor;
    }
    
    public void add(BeanBICuenta16 objCuenta16){
        this.listacuenta16.add(objCuenta16);
    }

    public List<BeanBICuenta16> getLibroDiario() {
        return listacuenta16;
    }
    
    
}
