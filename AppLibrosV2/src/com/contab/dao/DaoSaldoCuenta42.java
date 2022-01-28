/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import org.openide.util.Exceptions;

/**
 *
 * @author cixtic04
 */
public class DaoSaldoCuenta42 {
       public static ArrayList consultaSaldoCuenta42(ArrayList param) {
        ObjetoConexion cnxOra   =new ObjetoConexion();
         Conexion objCnx=cnxOra.conectarORACLE();
         ArrayList objetos=new ArrayList();
        try {
            if(!objCnx.getCnx().isClosed()){
             //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
               String proc = "{call ?:=SALDOCUENTA42_p(?,?.?)}";
               objetos= Consultas.funcion(proc, param,objCnx.getCnx());
               objetos.add(objCnx);
               return objetos;
           }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
       public static ArrayList consultaSaldoCuenta42x(ArrayList param) {
        ObjetoConexion cnxOra   =new ObjetoConexion();
         Conexion objCnx=cnxOra.conectarORACLE();
         ArrayList objetos=new ArrayList();
        try {
            if(!objCnx.getCnx().isClosed()){
             //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
               String proc = "{call ?:=SALDOCUENTA42(?,?,?)}";
               objetos= Consultas.funcion(proc, param,objCnx.getCnx());
               objetos.add(objCnx);
               return objetos;
           }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
        public static ArrayList consultaSaldoCuenta42DORALES(ArrayList param) {
        ObjetoConexion cnxOra   =new ObjetoConexion();
         Conexion objCnx=cnxOra.conectarORACLE();
         ArrayList objetos=new ArrayList();
        try {
            if(!objCnx.getCnx().isClosed()){
             //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
               String proc = "{call ?:=SALDOCUENTA42SDORALES(?,?,?)}";
               objetos= Consultas.funcion(proc, param,objCnx.getCnx());
               objetos.add(objCnx);
               return objetos;
           }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

        public static ArrayList consultaFechaEmision(ArrayList param) {
        ObjetoConexion cnxOra   =new ObjetoConexion();
         Conexion objCnx=cnxOra.conectarORACLE();
         ArrayList objetos=new ArrayList();
        try {
            if(!objCnx.getCnx().isClosed()){
             //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
               String proc = "{call ?:=fechaEmisionCuenta42(?,?)}";
               objetos= Consultas.funcion(proc, param,objCnx.getCnx());
               objetos.add(objCnx);
               return objetos;
           }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
}
