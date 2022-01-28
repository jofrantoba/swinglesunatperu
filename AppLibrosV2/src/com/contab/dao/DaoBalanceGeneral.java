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
public class DaoBalanceGeneral {
    public static Conexion objCnx;
   
     public static ArrayList libroBalanceGeneral2(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=libroBalanceGeneral2(?,?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }
      public static ArrayList libroBalanceGeneeral3(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=libroBalanceGeneral3(?,?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }

       public static ArrayList libroBalanceGeneeral1(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=libroBalanceGeneral(?,?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }

         public static ArrayList libroBalanceGeneeral4(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=libroBalanceGeneral4(?,?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }

          public static ArrayList SaldoAcumulado(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=libroBalanceGeneral5(?,?)}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }
   
}
