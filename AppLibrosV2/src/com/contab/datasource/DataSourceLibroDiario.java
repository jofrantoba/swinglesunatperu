/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.datasource;

import com.contab.bean.BeanLibroDiario;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author 777
 */
public class DataSourceLibroDiario implements JRDataSource {
    private int cursor=-1;
    private List<BeanLibroDiario> libroDiario= new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor<libroDiario.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("codigo".equals(jrf.getName())){
            valor=libroDiario.get(cursor).getCodigooper();
        }else if("fechaoper".equals(jrf.getName())){
            //SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
            valor=libroDiario.get(cursor).getFechaoper();
        }else if("glosa".equals(jrf.getName())){
            valor=libroDiario.get(cursor).getGlosa();
        }else if("cuenta".equals(jrf.getName())){
            valor=libroDiario.get(cursor).getCuenta();
        }else if("denominacion".equals(jrf.getName())){
            valor=libroDiario.get(cursor).getDenominacion();
        }else if("debe".equals(jrf.getName())){
            valor=libroDiario.get(cursor).getDebe();
        }else if("haber".equals(jrf.getName())){
            valor=libroDiario.get(cursor).getHaber();
        }else if("ccenter".equals(jrf.getName())){
            valor=libroDiario.get(cursor).getCcenter();
        }        
        return valor;
    }
    
    public void add(BeanLibroDiario objLibroDiario){
        this.libroDiario.add(objLibroDiario);
    }

    public List<BeanLibroDiario> getLibroDiario() {
        return libroDiario;
    }
    
    
}
