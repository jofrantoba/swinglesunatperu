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
public class DaoSaldoDeCuenta {
     public static ArrayList consultaSaldoDeCuenta(ArrayList param) {
        ObjetoConexion cnxOra   =new ObjetoConexion();
         Conexion objCnx=cnxOra.conectarORACLE();
         ArrayList objetos=new ArrayList();
        try {
            if(!objCnx.getCnx().isClosed()){
             //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
               String proc = "{call ?:=twelvelily(?,?,?)}";   //twelvelily
               objetos= Consultas.funcion(proc, param,objCnx.getCnx());
               objetos.add(objCnx);
               return objetos;
           }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }


      public static ArrayList SALDOCUENTA12(ArrayList param) {
        ObjetoConexion cnxOra   =new ObjetoConexion();
         Conexion objCnx=cnxOra.conectarORACLE();
         ArrayList objetos=new ArrayList();
        try {
            if(!objCnx.getCnx().isClosed()){
             //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
               String proc = "{call ?:=SALDOCUENTA12(?,?,?)}";   //twelvelily
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
               String proc = "{call ?:=fechaEmisionCuenta12(?,?)}";
               objetos= Consultas.funcion(proc, param,objCnx.getCnx());
               objetos.add(objCnx);
               return objetos;
           }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

        public static ArrayList consultaFechaEmision2(ArrayList param) {
        ObjetoConexion cnxOra   =new ObjetoConexion();
         Conexion objCnx=cnxOra.conectarORACLE();
         ArrayList objetos=new ArrayList();
        try {
            if(!objCnx.getCnx().isClosed()){
             //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
               String proc = "{call ?:=fechaEmisionCuenta12V2(?,?,?,?)}";
               objetos= Consultas.funcion(proc, param,objCnx.getCnx());
               objetos.add(objCnx);
               return objetos;
           }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
         public static ArrayList consultaFechaEmision3(ArrayList param) {
        ObjetoConexion cnxOra   =new ObjetoConexion();
         Conexion objCnx=cnxOra.conectarORACLE();
         ArrayList objetos=new ArrayList();
        try {
            if(!objCnx.getCnx().isClosed()){
             //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
               String proc = "{call ?:=fechaEmisionCuenta12V3(?,?,?)}";
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
