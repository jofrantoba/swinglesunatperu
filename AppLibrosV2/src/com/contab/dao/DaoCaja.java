/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import com.contab.view.ViewApplication;
import java.util.ArrayList;

/**
 *
 * @author 777
 */
public class DaoCaja {

    public static ArrayList consultaCaja(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            ViewApplication.mensajeEstado.setText("ESTA TAREA PUEDE TARDAR VARIOS MINUTOS :)");
           String proc = "{call ?:=Libro_Caja(?,?,?,?,?,?)}";
            return Consultas.funcionConsultar(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }

    public static ArrayList consultaCajaCorriente(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            ViewApplication.mensajeEstado.setText("ESTA TAREA PUEDE TARDAR VARIOS MINUTOS :)");
          String proc = "{call ?:=Libro_Caja_Cuenta(?,?,?,?,?)}";
            return Consultas.funcionConsultar(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
     public static ArrayList consultaSaldoInicial(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=saldoInicialCuenta10(?,?,?,?,?)}";
            return Consultas.funcionConsultar(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
      public static ArrayList Existencia_Movimiento_Caja(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=Existencia_Movimiento_Caja(?,?,?,?,?,?)}";
            return Consultas.funcionConsultar(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
      public static ArrayList consultaSaldoInicialEfectivo(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=SALDOINIEFECTIVO(?,?,?,?,?)}";
            return Consultas.funcionConsultar(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
      
       public static ArrayList DetalleCuenta10(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=SALDODETALLECUENTA10(?,?,?)}";
            return Consultas.funcionConsultar(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }

       public static ArrayList CUENTA_NOMBRE_BANCO_CORRIENTA(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=CUENTA_NOMBRE_BANCO_CORRIENTA(?,?,?)}";
            return Consultas.funcionConsultar(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }

// Periodo -0, Auxiliar -1,  NumDoc--2,  Cuenta--3,CentroC--4 , Moneda--5, CodeAux--6,  Cuentaaux--7, Tip_Doc--8,  Num_Doc--8, Fecha--9, As Debe--10, Haber--11, Glosa--12,  Preimpreso
}
