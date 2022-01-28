/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.datasource;

import com.contab.bean.BeanDaot;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Administrador
 */
public class DataSourceDaot implements JRDataSource {
    private int cursor=-1;
    private List<BeanDaot> lista= new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor<lista.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("CONTADOR".equals(jrf.getName())){
            valor=lista.get(cursor).getContador();
        }else if("D_TIPODOC".equals(jrf.getName())){            
            valor=lista.get(cursor).getNumEmp();
        }else if("D_NUMDOC".equals(jrf.getName())){
            valor=lista.get(cursor).getNumDocEmp();
        }else if("PERIODO".equals(jrf.getName())){
            valor=lista.get(cursor).getPeriodo();
        }else if("TIPO_PER".equals(jrf.getName())){
            valor=lista.get(cursor).getTipoPersona();
        }else if("TIPO_DOC".equals(jrf.getName())){
            valor=lista.get(cursor).getTipoDocPersona();
        }else if("NUMDOC".equals(jrf.getName())){
            valor=lista.get(cursor).getNumDocPersona();
        }
        else if("IMPORTE".equals(jrf.getName())){
            valor=lista.get(cursor).getImporte();
        }else if("AP_PATERNO".equals(jrf.getName())){
            valor=lista.get(cursor).getApPaterno();
        }else if("AP_MATERNO".equals(jrf.getName())){
            valor=lista.get(cursor).getApMaterno();
        }else if("NOMBRE1".equals(jrf.getName())){
            valor=lista.get(cursor).getNombre1();
        }else if("NOMBRE2".equals(jrf.getName())){
            valor=lista.get(cursor).getNombre2();
        }else if("RAZON_SOCIAL".equals(jrf.getName())){
            valor=lista.get(cursor).getRazonSocial();
        }else if("FORMULA".equals(jrf.getName())){
            valor=lista.get(cursor).getFormula();
        }
        
        return valor;
    }
    
    public void add(BeanDaot objCuenta14){
        this.lista.add(objCuenta14);
    }

    public List<BeanDaot> getLibroDiario() {
        return lista;
    }
    
    
}
