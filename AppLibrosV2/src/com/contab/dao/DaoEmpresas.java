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
public class DaoEmpresas {
    public static Conexion objCnx;
    public static ArrayList consultaEmpresas(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnx=cnxOra.conectarORACLE();
         if(!objCnx.getCnx().isClosed()){
             String proc = "{call ?:=LISTCOMPANY()}";
        return Consultas.funcion(proc, param,objCnx.getCnx());
         }
         return null;
    }
   
}
