/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanEmpleado;
import com.contab.dao.DaoEmpleado;
import com.contab.dao.DaoEmpresas;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Administrador
 */
public class LogicEmpleado {
    public static ArrayList<BeanEmpleado> listaDeEmpleado(){
        ResultSet rs=null;
        CallableStatement cst=null;
        try {
            ArrayList<BeanEmpleado> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add("all");
            param.add("all");
            param.add("all");
            ArrayList objetos = DaoEmpleado.consultaEmpleado(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanEmpleado obj = new BeanEmpleado();
                    obj.setCompania(rs.getString(1));
                    obj.setCodigoAuxiliar(rs.getString(2));
                    obj.setCuentaAuxiliar(rs.getString(3));
                    obj.setDescripcion(rs.getString(4));
                    obj.setRuc(rs.getString(6));
                    lista.add(obj);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return lista;
        } catch (SQLException ex) {            
            return null;
        } finally {
            try{
                if(rs!=null){
                 rs.close();   
                }
                if(cst!=null){
                    cst.close();
                }
                if(!DaoEmpresas.objCnx.getCnx().isClosed()){
                 DaoEmpresas.objCnx.destroy();   
                }                       
            }catch(Exception ex){
                
            }
        }

    }
    
    public static ArrayList<BeanEmpleado> listaDeEmpleado(String codemp,String codaux){        
        ResultSet rs=null;
        CallableStatement cst=null;
        try {
            ArrayList<BeanEmpleado> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(codemp);
            param.add(codaux);
            param.add("all");
            ArrayList objetos = DaoEmpleado.consultaEmpleado(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanEmpleado obj = new BeanEmpleado();
                    obj.setCompania(rs.getString(1));
                    obj.setCodigoAuxiliar(rs.getString(2));
                    obj.setCuentaAuxiliar(rs.getString(3));
                    obj.setDescripcion(rs.getString(4));
                    obj.setRuc(rs.getString(6));
                    lista.add(obj);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return lista;
        } catch (SQLException ex) {            
            return null;
        } finally {
            try{
                if(rs!=null){
                 rs.close();   
                }
                if(cst!=null){
                    cst.close();
                }
                if(!DaoEmpresas.objCnx.getCnx().isClosed()){
                 DaoEmpresas.objCnx.destroy();   
                }                       
            }catch(Exception ex){
                
            }
        }

    }
    
    public static void mantenimientoEmpleado(HashSet <BeanEmpleado> empleados){           
        Iterator i=empleados.iterator();
        BeanEmpleado bean;
        int contarEliminados=0;
        int contarInsertados=0;
        int contarActualizados=0;
        ArrayList param=new ArrayList();
        ArrayList objetos;
        CallableStatement cst=null;
        BigDecimal estado=null;
        while(i.hasNext()){
            bean=(BeanEmpleado)i.next();
            if(bean.getEstadoOperacion().equals("e")){                
                param.add(OracleTypes.NUMBER);    
                param.add(bean.getCompania());
                param.add(bean.getCodigoAuxiliar());
                param.add(bean.getCuentaAuxiliar());   
                param.add("");
                param.add("");             
                param.add(3);
                objetos=DaoEmpleado.mantenimientoEmpleado(param);      
                estado=(BigDecimal)objetos.get(0);
                cst=(CallableStatement)objetos.get(1);
                if(estado.compareTo(BigDecimal.valueOf(3))==0){
                    contarEliminados=contarEliminados+1;    
                    param.clear();                    
                }
            }else if(bean.getEstadoOperacion().equals("a")){
                param.add(OracleTypes.NUMBER);    
                param.add(bean.getCompania());
                param.add(bean.getCodigoAuxiliar());
                param.add(bean.getCuentaAuxiliar());   
                param.add(bean.getDescripcion());
                param.add(bean.getRuc());
                param.add(2);
                objetos=DaoEmpleado.mantenimientoEmpleado(param);      
                estado=(BigDecimal)objetos.get(0);
                cst=(CallableStatement)objetos.get(1);
                if(estado.compareTo(BigDecimal.valueOf(2))==0){
                    contarActualizados=contarActualizados+1;    
                    param.clear();
                }
            }else if(bean.getEstadoOperacion().equals("i")){
                param.add(OracleTypes.NUMBER);    
                param.add(bean.getCompania());
                param.add(bean.getCodigoAuxiliar());
                param.add(bean.getCuentaAuxiliar());   
                param.add(bean.getDescripcion());
                param.add(bean.getRuc());
                param.add(1);
                objetos=DaoEmpleado.mantenimientoEmpleado(param);                      
                estado=(BigDecimal)objetos.get(0);                                
                cst=(CallableStatement)objetos.get(1);                
                if(estado.compareTo(BigDecimal.ONE)==0){
                    contarInsertados=contarInsertados+1;    
                    param.clear();
                }else{
                    JOptionPane.showMessageDialog(null,"Este registro ya existe"
                    + "compania: "+bean.getCompania()+" \n"
                    + "codigo auxiliar: "+bean.getCodigoAuxiliar()+" \n"
                    + "Cuenta auxiliar: "+bean.getCuentaAuxiliar(), "Informe de Operación", JOptionPane.ERROR_MESSAGE);        
                }              
            }
            try{
                if(cst!=null){
                    cst.close();
                }                
            }catch(Exception ex){                
            }
        }            
        try{
            if(DaoEmpresas.objCnx.getCnx()!=null){
                DaoEmpresas.objCnx.destroy();   
            }            
        }catch(Exception ex){
        System.out.println("Error al guardar");
                }
            JOptionPane.showMessageDialog(null,"Se han insert"
                    + "ado: "+contarInsertados+" registros \n"
                    + "Se han actualizado: "+contarActualizados+" registros \n"
                    + "Se han eliminado: "+contarEliminados+" registros", "Informe de Operación", JOptionPane.INFORMATION_MESSAGE);        
    }
}
