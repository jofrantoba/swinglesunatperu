/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import com.contab.view.ViewApplication;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 777
 */
public class DaoKardex {

    public static Conexion objCnx;   
    
     public static ArrayList listarProducto(ArrayList param) throws SQLException {
        ObjetoConexion cnxOra = new ObjetoConexion();
        objCnx = cnxOra.conectarORACLE();
        if (!objCnx.getCnx().isClosed()) {
             System.out.println("onexion  base de datos  listarProducto");
            String proc = "{call ?:=ListarProductos(?,?,?,?)}";
            return Consultas.funcion(proc, param, objCnx.getCnx());
        }
          JOptionPane.showMessageDialog(null, "REPORTE LIBRO KARDEX ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
        return null;
    }

    
   
        
        //ListarMovimientoInventario1
        
      public static ArrayList listarMovimientoProducto(ArrayList param,Connection cnx) throws SQLException {
        //ObjetoConexion cnxOra = new ObjetoConexion();
        //objCnx = cnxOra.conectarORACLE();
        //if (!objCnx.getCnx().isClosed()) {
          //     System.out.println("Cerarr conexion  base de datos  listarMovimientoProducto");
            String proc = "{call ?:=ListarMovimientoInventario1(?,?,?,?,?)}";
            return Consultas.funcion(proc, param, cnx);
        
        
        
        //JOptionPane.showMessageDialog(null, "REPORTE LIBRO KARDEX ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
        //return null;
    }
      
       public static ArrayList promedioKardex(ArrayList param,Connection cnx) throws SQLException {
        //ObjetoConexion cnxOra = new ObjetoConexion();
        //objCnx = cnxOra.conectarORACLE();
        //if (!objCnx.getCnx().isClosed()) {
          //     System.out.println("Cerarr conexion  base de datos  listarMovimientoProducto");
            String proc = "{call ?:=promedioKardex(?,?,?,?)}";
            return Consultas.funcion(proc, param, cnx);
        
        
        
        //JOptionPane.showMessageDialog(null, "REPORTE LIBRO KARDEX ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
        //return null;
    }
      
      
   public static ArrayList ListarAlmacenXCompania(ArrayList param) throws SQLException {
        ObjetoConexion cnxOra = new ObjetoConexion();
        objCnx = cnxOra.conectarORACLE();
        if (!objCnx.getCnx().isClosed()) {
            String proc = "{call ?:=ListarAlmacenXCompania(?)}";
            return Consultas.funcion(proc, param, objCnx.getCnx());
        }
            JOptionPane.showMessageDialog(null, "REPORTE LIBRO KARDEX ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
        return null;
    }
    public static ArrayList SaldoInicialXEmpresaXAlmacen(ArrayList param,Connection cnx) throws SQLException {
      //  ObjetoConexion cnxOra = new ObjetoConexion();
      //  objCnx = cnxOra.conectarORACLE();
      //  if (!objCnx.getCnx().isClosed()) {
            String proc = "{call ?:=AlmacenInicialXEmpresa(?,?,?,?)}";
            return Consultas.funcion(proc, param, cnx);
       // }
        //JOptionPane.showMessageDialog(null, "REPORTE LIBRO KARDEX ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
        //return null;
    }
    public static ArrayList cantidadRegistroProductoKardex(ArrayList param,Connection cnx) throws SQLException {
      //  ObjetoConexion cnxOra = new ObjetoConexion();
      //  objCnx = cnxOra.conectarORACLE();
      //  if (!objCnx.getCnx().isClosed()) {
            String proc = "{call ?:=CANTIDADREGISTROPK(?,?,?,?,?)}";
            return Consultas.funcion(proc, param, cnx);
       // }
        //JOptionPane.showMessageDialog(null, "REPORTE LIBRO KARDEX ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
        //return null;
    }
  
    
      public static ArrayList NUMDOCUMENTOKARDEX(ArrayList param) throws SQLException {
        ObjetoConexion cnxOra = new ObjetoConexion();
        objCnx = cnxOra.conectarORACLE();
          System.out.println("Cerarr conexion  base de datos  NUMDOCUMENTOKARDEX");
        if (!objCnx.getCnx().isClosed()) {
            String proc = "{call ?:=NUMDOCUMENTOKARDEX(?,?,?,?)}";
            return Consultas.funcion(proc, param, objCnx.getCnx());
        }
          JOptionPane.showMessageDialog(null, "REPORTE LIBRO KARDEX ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
        return null;
    }


        public static ArrayList NUMDOCUMENTOKARDEX2(ArrayList param) throws SQLException {
        ObjetoConexion cnxOra = new ObjetoConexion();
        objCnx = cnxOra.conectarORACLE();
          System.out.println("Cerarr conexion  base de datos  NUMDOCUMENTOKARDEX");
        if (!objCnx.getCnx().isClosed()) {
            String proc = "{call ?:=NUMEROSERIEKARDEX(?,?,?)}";
            return Consultas.funcion(proc, param, objCnx.getCnx());
        }
          JOptionPane.showMessageDialog(null, "REPORTE LIBRO KARDEX ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
        return null;
    }
      
}
