/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import java.sql.Connection;

/**
 *
 * @author cixtic04
 */
public class ConexionPermanente {
    public static Connection cnxOra;
    public static void cnx(){
        Connection cnx=null;
        ObjetoConexion obj=new ObjetoConexion();
        Conexion c=obj.conectarORACLE();
        cnx=c.getCnx();
        cnxOra=cnx;
    }          
}
