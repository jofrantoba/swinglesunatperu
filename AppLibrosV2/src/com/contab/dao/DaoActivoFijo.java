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
public class DaoActivoFijo {

    public static ArrayList consultaActivoFijo(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos = new ArrayList();
        try {
            if (!objCnx.getCnx().isClosed()) {
                //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
                String proc = "{call ?:=MOVIMIENTOACTIVOFIJO(?,?,?,?)}";
                objetos = Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
     public static ArrayList ListaCuentaCaja(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos = new ArrayList();
        try {
            if (!objCnx.getCnx().isClosed()) {
                //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
                String proc = "{call ?:=LISTA_CUENTA_CAJA(?)}";
                objetos = Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
}
