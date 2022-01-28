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
public class DaoBalanceInventarios {
    
    public static ArrayList consultarCuenta14(ArrayList param) {        
        boolean estado=DaoEmpresas.objCnx.conectarBD();        
        if(estado){
            ViewApplication.mensajeEstado.setText("ESTA TAREA PUEDE TARDAR VARIOS MINUTOS :)");
            String proc = "{call ?:=balancecuneta14(?,?,?)}";             
        return Consultas.funcionConsultar(proc, param,DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
    
    public static ArrayList consultarCuenta16(ArrayList param) {        
        boolean estado=DaoEmpresas.objCnx.conectarBD();        
        if(estado){
            ViewApplication.mensajeEstado.setText("ESTA TAREA PUEDE TARDAR VARIOS MINUTOS :)");
            String proc = "{call ?:=balancecuenta16(?,?)}";             
        return Consultas.funcionConsultar(proc, param,DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
}
