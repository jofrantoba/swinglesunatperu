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
public class DaoAsientoDoble {
    public static Conexion objCnx;
    public static ArrayList CONSULTACUENTASIENTODOBLE(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=sysadm(?,?,?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }
    
    public static ArrayList ACTUALIZAR_BALANCE(ArrayList param,Connection cnx) throws SQLException {
        
             String proc = "{call ?:=ACTUALIZAR_BALANCE(?,?,?,?,?,?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
        
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

      public static ArrayList LISTARASIENTODOBLESALDOINICIAL(ArrayList param,Connection cnx) throws SQLException {
       
             String proc = "{call ?:=LISTARASIENTODOBLESALDOINICIAL(?,?,?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         
    }
   
}
