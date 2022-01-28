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
public class DaoTabla6 {
    public static Conexion objCnx;
    public static ArrayList CONSULTATABLE6(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=CONSULTATABLE6()}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }
    
    public static ArrayList MantemientoTabla6(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=mantanTabla06(?,?,?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }
   
}
