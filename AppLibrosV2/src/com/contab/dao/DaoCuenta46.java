package com.contab.dao;

import com.contab.view.ViewApplication;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DaoCuenta46 {

    public static ArrayList consultarCuenta46(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            ViewApplication.mensajeEstado.setText("ESTA TAREA PUEDE TARDAR VARIOS MINUTOS :)");
            String proc = "{call ?:=FUNCION313(?,?,?,?)}";
            return Consultas.funcionConsultar(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        JOptionPane.showMessageDialog(null, "Error en la conexion a la base datos.. Se cerrará el Sistema!!", "alert", JOptionPane.OK_CANCEL_OPTION);
        System.exit(0);
        return null;
    }
}