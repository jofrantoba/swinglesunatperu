/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 777
 */
public class DaoCuenta31 {
    public static Conexion objCnx;
    public static ArrayList CONSULTACuenta31(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=LISTARCUENTA31()}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }
    
    public static ArrayList MantemientoCuenta31(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=MANTANCUENTA31(?,?,?,?,?,?,?,?,?,?,?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }
     public static ArrayList reporteCuenta31(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=reporteCuenta31(?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }
   
}
