package com.contab.dao;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DaoLibroMayor {
    public static ArrayList consultasql1(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
        String proc = "{call ?:=FU_MAYORS1(?,?,?,?,?)}";
        return Consultas.funcion(proc, param,DaoEmpresas.objCnx.getCnx());
        }
        JOptionPane.showMessageDialog(null, "Error en la conexion a la base datos.. Se cerrará el Sistema!!", "alert", JOptionPane.OK_CANCEL_OPTION);
        System.exit(0);
        return null;
    }

    public static ArrayList consultasql2(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
        String proc = "{call ?:=FU_MAYORS2(?,?,?,?,?)}";
        return Consultas.funcion(proc, param,DaoEmpresas.objCnx.getCnx());
        }
        JOptionPane.showMessageDialog(null, "Error en la conexion a la base datos.. Se cerrará el Sistema!!", "alert", JOptionPane.OK_CANCEL_OPTION);
        System.exit(0);
        return null;
    }
}
