package com.contab.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import org.openide.util.Exceptions;

public class DaoTABLA03 {

    public static ArrayList ConsultarTABLA03(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos = new ArrayList();
        try {
            if (!objCnx.getCnx().isClosed()) {
                String proc = "{call ?:=CONSULTATABLA03()}";
                objetos = Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    public static ArrayList MantenimientoTABLA03(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos = new ArrayList();
        try {
            if (!objCnx.getCnx().isClosed()) {
                String proc = "{call ?:=MANTANTABLA03(?,?,?,?,?,?,?)}";
                objetos = Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
}