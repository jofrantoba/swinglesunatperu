package com.contab.dao;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DaoLibroActivo {

    public static ArrayList consultaCuentas33(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=activo33(?,?,?,?,?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        } else {
            JOptionPane.showMessageDialog(null, "Error en la conexion a la base datos.. Se cerrará el Sistema!!", "alert", JOptionPane.OK_CANCEL_OPTION);
            System.exit(0);
        }
        return null;
    }

    public static ArrayList consultaActivo33(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=consultaractivo33(?,?,?,?,?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        } else {
            JOptionPane.showMessageDialog(null, "Error en la conexion a la base datos.. Se cerrará el Sistema!!", "alert", JOptionPane.OK_CANCEL_OPTION);
            System.exit(0);
        }
        return null;
    }

    public static ArrayList consultaActivo33Det(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=consultaractivo33det(?,?,?,?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        } else {
            JOptionPane.showMessageDialog(null, "Error en la conexion a la base datos.. Se cerrará el Sistema!!", "alert", JOptionPane.OK_CANCEL_OPTION);
            System.exit(0);
        }
        return null;
    }

    public static ArrayList consultaProveedor(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=consultaraproveedor(?,?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        } else {
            JOptionPane.showMessageDialog(null, "Error en la conexion a la base datos.. Se cerrará el Sistema!!", "alert", JOptionPane.OK_CANCEL_OPTION);
            System.exit(0);
        }
        return null;
    }

    public static ArrayList consultaCuentasa33Digi(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=cuenta33a3digi(?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        } else {
            JOptionPane.showMessageDialog(null, "Error en la conexion a la base datos.. Se cerrará el Sistema!!", "alert", JOptionPane.OK_CANCEL_OPTION);
            System.exit(0);
        }
        return null;
    }

    public static ArrayList jrnnumberAnulados(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=JRNNUMBERANULADOS(?,?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        } else {
            JOptionPane.showMessageDialog(null, "Error en la conexion a la base datos.. Se cerrará el Sistema!!", "alert", JOptionPane.OK_CANCEL_OPTION);
            System.exit(0);
        }
        return null;
    }
}
