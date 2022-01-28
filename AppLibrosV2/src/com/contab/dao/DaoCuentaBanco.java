/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

/**
 *
 * @author 777
 */
public class DaoCuentaBanco {

    //public static OracleConnection cnx=(OracleConnection) ObjetoConexion.conectarORACLE();
    public static ArrayList consultaCuentaBanco(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=CUENTA_BANCO1(?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;

    }

    public static ArrayList consultaCuentaEfectivo(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=CUENTA_EFECTIVO(?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;

    }

    public static ArrayList Cuenta_Quiebre(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=Cuenta_Quiebre(?,?,?)}";
            //     DaoEmpresas.objCnx.destroy();
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());

        }
        return null;

    }

    public static ArrayList Cuenta_Quiebre_Efectivo(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=Cuenta_Quiebre_Efectivo(?,?,?,?)}";
            //     DaoEmpresas.objCnx.destroy();
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());

        }
        return null;

    }

    public static ArrayList Cuenta_Banco_Sunat(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=CUENTA_BANCO_SUNAT(?,?)}";
            //     DaoEmpresas.objCnx.destroy();
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());

        }
        return null;

    }

    public static ArrayList LISTADECUENTABANCO(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=LISTADECUENTABANCO()}";
            //     DaoEmpresas.objCnx.destroy();
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());

        }
        return null;

    }
}
