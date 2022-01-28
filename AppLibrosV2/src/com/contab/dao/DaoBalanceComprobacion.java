/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import org.openide.util.Exceptions;

/**
 *
 * @author cixtic04
 */
public class DaoBalanceComprobacion {

    public static ArrayList consultaBalanceComprobacion(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos = new ArrayList();
        try {
            if (!objCnx.getCnx().isClosed()) {
                //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
                String proc = "{call ?:=balancecomprobacion(?,?)}";
                objetos = Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    public static ArrayList balancecomprobacionDigitos2(ArrayList param) {

       
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos = new ArrayList();
        try {
            if (!objCnx.getCnx().isClosed()) {
                //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
                String proc = "{call ?:=balancecomprobacionDigitos2P(?,?)}";
                objetos = Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    public static ArrayList balancecomprobacionDigitos3(ArrayList param, Connection cnx) {

        String proc = "{call ?:=balancecomprobacionDigitos3(?,?)}";
        return Consultas.funcion(proc, param, cnx);
    }

    public static ArrayList balancecomprobacionDigitos4(ArrayList param, Connection cnx) {

        String proc = "{call ?:=balancecomprobacionDigitos4(?,?)}";
        return Consultas.funcion(proc, param, cnx);

    }

    public static ArrayList balancecomprobacionDigitos5(ArrayList param, Connection cnx) {


        String proc = "{call ?:=balancecomprobacionDigitos5(?,?)}";
        return Consultas.funcion(proc, param, cnx);

    }
}
