/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import oracle.sql.ArrayDescriptor;

/**
 *
 * @author 777
 */
public class DaoCuenta {
     public static Conexion objCnxX;

    public static ArrayList consultaGlosa(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=GLOSAACCOUNT(?,?,?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
    
    public static ArrayList Cuenta_Actual(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=CUENTA_ACTUALES(?,?,?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
     public static ArrayList Cuenta_ActualNuevo(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=CUENTA_ACTUALESN(?,?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
    public static ArrayList Cuenta_ActualNUEVO(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=CUENTA_ACTUALESNUEVO()}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }

    public static ArrayList Repositoriocuenta(ArrayList param) {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=LISTACUENTAREPOSITORIO(?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
    
  /*  public static ArrayList MantemientoCuenta(ArrayList param)  {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            String proc = "{call ?:=MANTEMIENTOCUENTA(?,?,?,?,?,?)}";
            return Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }*/
    
      public static ArrayList MantemientoCuenta(ArrayList param) throws SQLException {
         ObjetoConexion cnxOra   =new ObjetoConexion();
         objCnxX=cnxOra.conectarORACLE();
         if(!objCnxX.getCnx().isClosed()){
             String proc = "{call ?:=MANTEMIENTOCUENTA(?,?,?,?,?,?)}";
        return Consultas.funcion(proc, param,objCnxX.getCnx());
         }
         return null;
    }
      
       public static ArrayList MantemientoCuentaArray(ArrayList param) throws SQLException {
           
         ObjetoConexion cnxOra   =new ObjetoConexion();
        ArrayDescriptor descriptor =ArrayDescriptor.createDescriptor( "NUM_ARRAY", cnxOra.conectarORACLEN() );
         objCnxX=cnxOra.conectarORACLE();
         if(!objCnxX.getCnx().isClosed()){
             String proc = "{call ?:=MANTEMIENTOCUENTA(?,?,?,?,?,?)}";
        return Consultas.funcion(proc, param,objCnxX.getCnx());
         }
         return null;
    }
      
    
}
