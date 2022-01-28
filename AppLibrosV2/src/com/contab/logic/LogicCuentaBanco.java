/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanBanco;
import com.contab.bean.BeanCuentaBanco;
import com.contab.dao.DaoCuentaBanco;
import com.contab.dao.DaoEmpresas;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author 777
 */
public class LogicCuentaBanco {

    public static ArrayList<BeanBanco> listaCuentaBanco(String codemp) throws SQLException {
        ArrayList<BeanBanco> lista = new ArrayList();
        ArrayList param = new ArrayList();
        param.add(OracleTypes.CURSOR);
        param.add(codemp);
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList objetos = DaoCuentaBanco.consultaCuentaBanco(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            BeanBanco objBanco1 = new BeanBanco();
            objBanco1.setNumCuenta("0");
            objBanco1.setCuentaBanco("Todos");
             lista.add(objBanco1);

            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanBanco objBanco = new BeanBanco();
                    objBanco.setNumCuenta(rs.getString(1));
                    objBanco.setCuentaBanco(rs.getString(2));
                    objBanco.setCenter(rs.getString(3));
                    //  objEmpresa.setDescripcion(rs.getString(2));
                    //objEmpresa.setRuc(rs.getString(3));
                    lista.add(objBanco);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return lista;
        } catch (SQLException ex) {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
            return null;
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }
    }

    public static ArrayList<BeanBanco> listaCuentaEfectivo(String codemp) throws SQLException {
        ArrayList<BeanBanco> lista = new ArrayList();
        ArrayList param = new ArrayList();
        param.add(OracleTypes.CURSOR);
        param.add(codemp);
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList objetos = DaoCuentaBanco.consultaCuentaEfectivo(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
             BeanBanco objBanco1 = new BeanBanco();
              objBanco1.setNumCuenta("0");
            objBanco1.setCuentaBanco("Todos");
               lista.add(objBanco1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanBanco objBanco = new BeanBanco();
                    objBanco.setNumCuenta(rs.getString(1));
                    objBanco.setCuentaBanco(rs.getString(2));
                    objBanco.setCodigoCentro(rs.getString(3));
                    //  objEmpresa.setDescripcion(rs.getString(2));
                    //objEmpresa.setRuc(rs.getString(3));
                    lista.add(objBanco);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return lista;
        } catch (SQLException ex) {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
            return null;
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }

    }

    public static BeanBanco Cuenta_Quiebre(String codemp, String Auxiliar, String voucher) throws SQLException {
        //ArrayList<BeanBanco> lista = new ArrayList();
        ArrayList param = new ArrayList();
        param.add(OracleTypes.CURSOR);
        param.add(codemp);
        param.add(Auxiliar);
        param.add(voucher);
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList objetos = DaoCuentaBanco.Cuenta_Quiebre(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            BeanBanco objBanco = new BeanBanco();
            if (rs != null && cst != null) {
                while (rs.next()) {

                    objBanco.setNumCuenta(rs.getString(1));
                    objBanco.setCuentaBanco(rs.getString(3));

                    //lista.add(objBanco);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return objBanco;
        } catch (SQLException ex) {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
            return null;
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }

    }

     public static BeanBanco Cuenta_Quiebre_Efectivo(String codemp, String Auxiliar, String voucher,String tipo) throws SQLException {
        //ArrayList<BeanBanco> lista = new ArrayList();
        ArrayList param = new ArrayList();
        param.add(OracleTypes.CURSOR);
        param.add(codemp);
        param.add(Auxiliar);
        param.add(voucher);
         param.add(tipo);
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList objetos = DaoCuentaBanco.Cuenta_Quiebre_Efectivo(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            BeanBanco objBanco = new BeanBanco();
            if (rs != null && cst != null) {
                while (rs.next()) {

                    objBanco.setNumCuenta(rs.getString(1));
                    objBanco.setCuentaBanco(rs.getString(3));

                    //lista.add(objBanco);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return objBanco;
        } catch (SQLException ex) {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
            return null;
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }

    }
     
      public static BeanBanco Cuenta_Banco_Sunat(String codemp, String account) throws SQLException {
        //ArrayList<BeanBanco> lista = new ArrayList();
        ArrayList param = new ArrayList();
        param.add(OracleTypes.CURSOR);
        param.add(codemp);
        param.add(account);
       
       
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList objetos = DaoCuentaBanco.Cuenta_Banco_Sunat(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            BeanBanco objBanco = new BeanBanco();
            if (rs != null && cst != null) {
                while (rs.next()) {

                   // objBanco.setNumeroCuentaBanco(rs.getString(1)); //numero de cuenta  000-8122105
                 //   objBanco.setNombreBanco(rs.getString(2));// nombre de banco
                    objBanco.setCodigoCuentaSunat(rs.getString(1)); // codigo la sunat

                    //lista.add(objBanco);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return objBanco;
        } catch (SQLException ex) {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
            return null;
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }

    }
      
       public static ArrayList<BeanCuentaBanco> listaDeCuentaBanco() throws SQLException {
        ResultSet rs=null;
        CallableStatement cst=null;
        try {
            ArrayList<BeanCuentaBanco> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoCuentaBanco.LISTADECUENTABANCO(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanCuentaBanco objEmpresa = new BeanCuentaBanco();
                    objEmpresa.setLlave(rs.getString(1));
                    objEmpresa.setNombre(rs.getString(2));
                   
                    lista.add(objEmpresa);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return lista;
        } catch (SQLException ex) {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
            return null;
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }

    }
}
