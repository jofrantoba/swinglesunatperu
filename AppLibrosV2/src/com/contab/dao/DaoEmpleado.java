/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Administrador
 */
public class DaoEmpleado {
    public static ArrayList consultaEmpleado(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=LISTAEMPLEADO(?,?,?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
    
    public static ArrayList mantenimientoEmpleado(ArrayList param) {        
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=mantempleado(?,?,?,?,?,?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
}
