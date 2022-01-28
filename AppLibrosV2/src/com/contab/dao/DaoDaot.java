/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import com.contab.view.ViewApplication;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class DaoDaot {
    
    public static ArrayList consultarCXC(ArrayList param) {        
        boolean estado=DaoEmpresas.objCnx.conectarBD();        
        if(estado){
            ViewApplication.mensajeEstado.setText("ESTA TAREA PUEDE TARDAR VARIOS MINUTOS :)");
            String proc = "{call ?:=RPTCXC(?,?,?,?)}";             
        return Consultas.funcionConsultar(proc, param,DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
    
    public static ArrayList consultarCXCResumen(ArrayList param) {        
        boolean estado=DaoEmpresas.objCnx.conectarBD();        
        if(estado){
            ViewApplication.mensajeEstado.setText("ESTA TAREA PUEDE TARDAR VARIOS MINUTOS :)");
            String proc = "{call ?:=RPTCXCRESUMEN(?,?,?,?)}";             
        return Consultas.funcionConsultar(proc, param,DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
     public static ArrayList consultarCXCPesumen(ArrayList param) {        
        boolean estado=DaoEmpresas.objCnx.conectarBD();        
        if(estado){
            ViewApplication.mensajeEstado.setText("ESTA TAREA PUEDE TARDAR VARIOS MINUTOS :)");
            String proc = "{call ?:=RPTCXPRESUMEN(?,?,?,?)}";             
        return Consultas.funcionConsultar(proc, param,DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
    
    public static ArrayList consultarCXCSunat(ArrayList param) {        
        boolean estado=DaoEmpresas.objCnx.conectarBD();        
        if(estado){
            ViewApplication.mensajeEstado.setText("ESTA TAREA PUEDE TARDAR VARIOS MINUTOS :)");
            String proc = "{call ?:=RPTCXCSUNAT(?,?,?,?)}";             
        return Consultas.funcionConsultar(proc, param,DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
    
    public static ArrayList consultarCXP(ArrayList param) {        
        boolean estado=DaoEmpresas.objCnx.conectarBD();        
        if(estado){
            ViewApplication.mensajeEstado.setText("ESTA TAREA PUEDE TARDAR VARIOS MINUTOS :)");
            String proc = "{call ?:=RPTCXP(?,?,?,?)}";             
        return Consultas.funcionConsultar(proc, param,DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
    
     public static ArrayList ListaCliente(ArrayList param) {        
        boolean estado=DaoEmpresas.objCnx.conectarBD();    
         ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        if(estado){
            ViewApplication.mensajeEstado.setText("ESTA TAREA PUEDE TARDAR VARIOS MINUTOS :)");
            String proc = "{call ?:=clientesDNI()}";             
        return Consultas.funcionConsultar(proc, param,objCnx.getCnx());
        
     
        }
        return null;
    }
    
}
