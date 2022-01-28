package com.contab.dao;

import java.util.ArrayList;

public class DaoAsiento {

    public static ArrayList consultaAsiento(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=COUNTCOMPANY(?)}";
            return Consultas.funcionConsultar(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
}
