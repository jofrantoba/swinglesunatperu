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
public class DaoCuenta50 {
    public static Conexion objCnx;
    public static ArrayList CONSULTACUENTA50(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=LISTARCUENTA50()}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }
    
    public static ArrayList MantemientoCuenta50(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=MANTANCUENTA50(?,?,?,?,?,?,?,?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }
     public static ArrayList reporteCuenta50(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=reporteCuenta50(?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }
   
}
