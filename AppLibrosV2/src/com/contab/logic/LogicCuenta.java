/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanCuenta;
import com.contab.dao.DaoCuenta;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.ObjetoConexion;
import com.sun.org.apache.bcel.internal.generic.NEW;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

/**
 *
 * @author 777
 */
public class LogicCuenta {

    private static String ORACLE_STRUCT = "T_TYPE ";
    private static String ORACLE_ARRAY = "TB_T_TYPE";

    public static String glosaDeCuenta(String codigoEmpresa, String codigoCuenta, String center) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(codigoEmpresa);
            param.add(codigoCuenta);
            param.add(center);
            ArrayList objetos = DaoCuenta.consultaGlosa(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            String glosa = "";
            if (rs != null && cst != null) {
                while (rs.next()) {
                    glosa = rs.getString(1);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return glosa;
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

    public static BeanCuenta Cuenta_Actual(String cuenta, String ano, String codEmpresa) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        BeanCuenta bCuenta = new BeanCuenta();
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(cuenta);
            param.add(ano);
            param.add(codEmpresa);
            ArrayList objetos = DaoCuenta.Cuenta_Actual(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            String numero = "";
            if (rs != null && cst != null) {
                while (rs.next()) {

                    bCuenta.setNumero(rs.getString(1));
                    bCuenta.setDenominacion(rs.getString(2));

                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }

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
        return bCuenta;
    }

    public static ArrayList<BeanCuenta> listaCuentaActuales() {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList<BeanCuenta> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoCuenta.Cuenta_ActualNUEVO(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanCuenta obj = new BeanCuenta();
                    obj.setIdCuenta(rs.getString(1));
                    obj.setNumero(rs.getString(2));
                    obj.setCuenta(rs.getString(3));
                    obj.setNuevonumero(rs.getString(4));
                    obj.setNuevoCuenta(rs.getString(7));
                    obj.setAnno(rs.getInt(5));

                    lista.add(obj);
                }
            }
            return lista;
        } catch (SQLException ex) {

            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cst != null) {
                    cst.close();
                }
                if (!DaoEmpresas.objCnx.getCnx().isClosed()) {
                    DaoEmpresas.objCnx.destroy();
                }
            } catch (Exception ex) {
            }
        }

    }

    public static void mantenimientoCuenta(HashSet<BeanCuenta> Cuenta) throws SQLException {
        Iterator i = Cuenta.iterator();
        BeanCuenta bean;
        int contarEliminados = 0;
        int contarInsertados = 0;
        int contarActualizados = 0;
        ArrayList param = new ArrayList();
        ArrayList objetos;
        CallableStatement cst = null;
        BigDecimal estado = null;
        while (i.hasNext()) {
            bean = (BeanCuenta) i.next();
            if (bean.getOperacion().equals("e")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdCuenta());
                param.add(bean.getNumero());
                param.add(bean.getCuenta());
                param.add(bean.getNuevonumero());
                param.add(bean.getNuevoCuenta());
                param.add(3);
                objetos = DaoCuenta.MantemientoCuenta(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                    contarEliminados = contarEliminados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("a")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdCuenta());
                param.add(bean.getNumero());
                param.add(bean.getCuenta());
                param.add(bean.getNuevonumero());
                param.add(bean.getNuevoCuenta());
                param.add(2);
                objetos = DaoCuenta.MantemientoCuenta(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                    contarActualizados = contarActualizados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("i")) {
                param.add(OracleTypes.NUMBER);
                param.add(OracleTypes.NUMBER);
                param.add(bean.getNumero());
                param.add(bean.getCuenta());
                param.add(bean.getNuevonumero());
                param.add(bean.getNuevoCuenta());
                param.add(1);
                objetos = DaoCuenta.MantemientoCuenta(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(1)) == 0) {
                    contarInsertados = contarInsertados + 1;
                    param.clear();
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Se han insert"
                + "ado: " + contarInsertados + " registros \n"
                + "Se han actualizado: " + contarActualizados + " registros \n"
                + "Se han eliminado: " + contarEliminados + " registros", "Informe de Operación", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void insertAllArray(HashSet<BeanCuenta> Cuenta) {
        Connection conn = null;
        StructDescriptor structDescriptor = null;
        ArrayDescriptor arrayDescriptor = null;
        int iSize = Cuenta.size();
        Object[] arrObj = null;
        Object[][] recObj = null;
        try {

            ObjetoConexion cnxOra = new ObjetoConexion();
            conn = cnxOra.conectarORACLEN();
            //   log.debug("got connection from jdbcTemplate ");
            structDescriptor = StructDescriptor.createDescriptor(ORACLE_STRUCT, conn.getMetaData().getConnection());
            arrayDescriptor = ArrayDescriptor.createDescriptor(ORACLE_ARRAY, conn.getMetaData().getConnection());

            arrObj = new Object[iSize];
            recObj = new Object[iSize][6];

            Iterator i = Cuenta.iterator();
            BeanCuenta bean;
            int j = 0;
            while (i.hasNext()) {
                bean = (BeanCuenta) i.next();
                j = j + 1;
                recObj[j][0] = bean.getNumero();
                recObj[j][1] = bean.getCuenta();
                recObj[j][2] = bean.getNuevonumero();
                recObj[j][3] = bean.getAnno();
                recObj[j][4] = bean.getCompania();
                recObj[j][5] = bean.getNuevoCuenta();

                arrObj[j] = new STRUCT(structDescriptor, conn.getMetaData().getConnection(), recObj[j]);
            }
            ARRAY arr = new ARRAY(arrayDescriptor, conn.getMetaData().getConnection(), recObj);
            PreparedStatement preparedStatement = conn.prepareStatement("{call MANTEMINETOARRAYCUENTA(?)}");
            preparedStatement.setArray(1, arr);
            preparedStatement.execute();
            //   log.debug("Stored procedure POPULATE_ TABLE_TEST is executed");
        } catch (Exception e) {
            //  throw new DaoException("Error while doing bulk insert using POPULATE_ TABLE_TEST Stored procedure", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    // log.debug("connection from jdbcTemplate is closed");
                }
            } catch (SQLException e2) {
                //   log.debug("problem while closing connection");
            }
        }
    }
}
