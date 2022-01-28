/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import com.contab.bean.BeanMovimientoCajaBanco;
import com.contab.view.ViewApplication;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author cixtic04
 */
public class DaoMovimientoCajaBanco {

    public static Conexion objCnx;

    public static BeanMovimientoCajaBanco consultaMovimientoCajaBanco(ArrayList param) throws SQLException {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            ArrayList objetos = new ArrayList();
            String proc = "{call ?:=GLOSACUENTABANCO(?,?,?,?)}";
            objetos = Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            BeanMovimientoCajaBanco obj = new BeanMovimientoCajaBanco();
            if (rs != null && cst != null) {
                if (rs.next()) {
                    obj.setNROVOUCH(rs.getString(1));
                    obj.setNRODOC(rs.getString(2));
                    obj.setGLOSA(rs.getString(3));
                    obj.setGIRADO(rs.getString(4));
                    obj.setANULADO(rs.getString(5));
                    obj.setTipoMov(rs.getString(6));
                    obj.setNumero(rs.getString(7));


                    rs.close();
                    cst.close();
                    DaoEmpresas.objCnx.destroy();
                    return obj;
                }
            }

            obj.setANULADO("0");
            obj.setTipoMov("XX");
            obj.setANULADO("0");
            //getTipoMov
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
            return obj;
        }
        JOptionPane.showMessageDialog(null, "Error en la conexion", "alert", JOptionPane.OK_CANCEL_OPTION);

        return null;
    }

    public static BeanMovimientoCajaBanco consultaMovimientoCajaBanco2(ArrayList param) throws SQLException {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            ArrayList objetos = new ArrayList();
            String proc = "{call ?:=GLOSACUENTABANCO2(?,?,?,?)}";
            objetos = Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            BeanMovimientoCajaBanco obj = new BeanMovimientoCajaBanco();
            if (rs != null && cst != null) {
                if (rs.next()) {

                    obj.setGLOSA(rs.getString(1));
                    obj.setNumero(rs.getString(2));


                    rs.close();
                    cst.close();
                    DaoEmpresas.objCnx.destroy();
                    return obj;
                }
            }

            obj.setANULADO("0");
            obj.setTipoMov("XX");
            obj.setANULADO("0");
            //getTipoMov
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
            return obj;
        }
        JOptionPane.showMessageDialog(null, "Error en la conexion", "alert", JOptionPane.OK_CANCEL_OPTION);

        return null;
    }
     public static BeanMovimientoCajaBanco consultaMovimientoCajaBancoCorriente(ArrayList param) throws SQLException {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            ArrayList objetos = new ArrayList();
            String proc = "{call ?:=GLOSACUENTABANCOC(?,?,?,?)}";
            objetos = Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            BeanMovimientoCajaBanco obj = new BeanMovimientoCajaBanco();
            if (rs != null && cst != null) {
                if (rs.next()) {

                    obj.setNROVOUCH(rs.getString(1));
                    obj.setNRODOC(rs.getString(2));
                    obj.setGLOSA(rs.getString(3));
                    obj.setGIRADO(rs.getString(4));
                    obj.setANULADO(rs.getString(5));
                    obj.setTipoMov(rs.getString(6));
                    obj.setNumero(rs.getString(7));


                    rs.close();
                    cst.close();
                    DaoEmpresas.objCnx.destroy();
                    return obj;
                }
            }

            obj.setANULADO("0");
            obj.setTipoMov("XX");
            obj.setANULADO("0");
            //getTipoMov
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
            return obj;
        }
        JOptionPane.showMessageDialog(null, "Error en la conexion", "alert", JOptionPane.OK_CANCEL_OPTION);

        return null;
    }

    public static BeanMovimientoCajaBanco consultaMovimientoCajaBancoCoriente(ArrayList param) throws SQLException {
        boolean estado = DaoEmpresas.objCnx.conectarBD();
        if (estado) {
            ArrayList objetos = new ArrayList();
            String proc = "{call ?:=GLOSACUENTABANCOCORRIENTE(?,?,?,?)}";
            objetos = Consultas.funcion(proc, param, DaoEmpresas.objCnx.getCnx());
            ResultSet rs = (ResultSet) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            BeanMovimientoCajaBanco obj = new BeanMovimientoCajaBanco();
            if (rs != null && cst != null) {
                if (rs.next()) {
                    obj.setNROVOUCH(rs.getString(1));
                    obj.setNRODOC(rs.getString(2));
                    obj.setGLOSA(rs.getString(3));
                    obj.setGIRADO(rs.getString(4));
                    obj.setANULADO(rs.getString(5));
                    obj.setTipoMov(rs.getString(6));
                    obj.setNumero(rs.getString(7));


                    rs.close();
                    cst.close();
                    DaoEmpresas.objCnx.destroy();
                    return obj;
                }
            }

            obj.setANULADO("0");
            obj.setTipoMov("XX");
            obj.setANULADO("0");
            //getTipoMov
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
            return obj;
        }
        JOptionPane.showMessageDialog(null, "Error en la conexion", "alert", JOptionPane.OK_CANCEL_OPTION);

        return null;
    }

    public static ArrayList consultaMovimientoCajaBancoN(ArrayList param) throws SQLException {
        ObjetoConexion cnxOra = new ObjetoConexion();
        objCnx = cnxOra.conectarORACLE();
        System.out.println("Cerarr conexion  base de datos  NUMDOCUMENTOKARDEX");
        if (!objCnx.getCnx().isClosed()) {
            String proc = "{call ?:=GLOSACUENTABANCON(?,?,?)}";
            return Consultas.funcion(proc, param, objCnx.getCnx());
        }
        JOptionPane.showMessageDialog(null, "REPORTE LIBRO KARDEX ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
        return null;
    }

    public static ArrayList Cuenta_Quiebre_EfectivoN(ArrayList param) throws SQLException {
        ObjetoConexion cnxOra = new ObjetoConexion();
        objCnx = cnxOra.conectarORACLE();
        System.out.println("Cerarr conexion  base de datos  NUMDOCUMENTOKARDEX");
        if (!objCnx.getCnx().isClosed()) {
            String proc = "{call ?:=Cuenta_Quiebre_EfectivoN(?,?,?)}";
            return Consultas.funcion(proc, param, objCnx.getCnx());
        }
        JOptionPane.showMessageDialog(null, "REPORTE LIBRO KARDEX ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
        return null;
    }
}
