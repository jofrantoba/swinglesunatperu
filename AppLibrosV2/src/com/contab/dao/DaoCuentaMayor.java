package com.contab.dao;

import java.util.ArrayList;

public class DaoCuentaMayor {
    public static ArrayList consultaAsiento(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
        String proc = "{call ?:=FU_CUENTAS(?)}";
        return Consultas.funcionConsultar(proc, param,DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
}
