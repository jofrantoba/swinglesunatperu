/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import com.contab.bean.BeanVenta;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.openide.util.Exceptions;

/**
 *
 * @author cixtic04
 */
public class DaoVenta {

    public static ArrayList consultaVenta(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos = new ArrayList();
        try {
            if (!objCnx.getCnx().isClosed()) {
                //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
                String proc = "{call ?:=VENTA1(?,?,?)}";
                objetos = Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    public static ArrayList ListarTabla802(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos = new ArrayList();
        try {
            if (!objCnx.getCnx().isClosed()) {
                //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
                String proc = "{call ?:=tabla862()}";
                objetos = Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    public static ArrayList ReposotoriCompraConstancia(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos = new ArrayList();
        try {
            if (!objCnx.getCnx().isClosed()) {
                //  String proc = "{call ?:=BALANCE_COMPROBACION(?,?)}";
                String proc = "{call ?:=ComprasConstancia(?,?,?)}";
                objetos = Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    public static BeanVenta ComprasCredito(ArrayList param) throws SQLException {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            ArrayList objetos = new ArrayList();
            String proc = "{call ?:=ComprasCredito(?,?,?)}";
            objetos = Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            BeanVenta obj = new BeanVenta();
            if (rs != null && cst != null) {
                if (rs.next()) {
                     obj.setFechaR(rs.getString(10));
                    obj.setTabla101(rs.getString(1));
                    obj.setSerie1(rs.getString(5)); //comprobante
                    obj.setComprobante(rs.getString(6));

                    System.out.println(rs.getString(10) +rs.getString(6) );

                    rs.close();
                    cst.close();
                    DaoEmpresas.objCnx.destroy();
                    return obj;
                }
            }


            //getTipoMov
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
            return obj;
        }
        //  JOptionPane.showMessageDialog(null, "Error en la conexion", "alert", JOptionPane.OK_CANCEL_OPTION);

        return null;
    }
}
