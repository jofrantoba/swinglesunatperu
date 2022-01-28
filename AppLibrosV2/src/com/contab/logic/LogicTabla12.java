/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanEmpresa;

import com.contab.bean.BeanTabla12;
import com.contab.bean.BeanTabla6;
import com.contab.dao.DaoEmpresas;

import com.contab.dao.DaoTabla12;
import com.contab.dao.DaoTabla6;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author 777
 */
public class LogicTabla12 {

    public static ArrayList<BeanTabla12> listaTabla12() {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList<BeanTabla12> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoTabla12.CONSULTATABLE12(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanTabla12 obj = new BeanTabla12();
                    obj.setIdTabla(rs.getInt(1));
                    obj.setCodmov(rs.getString(2));
                    obj.setLlave(rs.getString(3));
                    obj.setDescripcion(rs.getString(4));
                    obj.setCodigoSunat(rs.getString(5));

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

    public static ResultSet ListarTabla12() throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);

            ArrayList objetos = DaoTabla12.CONSULTATABLE12(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                return rs;
            }
            cst.close();
            //  DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
        } finally {
            //  cst.close();
            DaoEmpresas.objCnx.destroy();
        }
        return rs;
    }

    public static BeanTabla12 buscarTabla12(ArrayList<ArrayList<BeanTabla12>> repor, String mov, String motivo) {
        BeanTabla12 bean = new BeanTabla12();
        BeanTabla12 beanaux = new BeanTabla12();
        ArrayList<BeanTabla12> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanTabla12>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanTabla12) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getCodmov().equals(mov) && beanaux.getLlave().equals(motivo)) {

                    //  System.out.println("encontrado " + unidad);
                    // array.remove(beanaux);
                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;
    }

    public static ArrayList<ArrayList<BeanTabla12>> repositorio(ResultSet rsR) {
        ArrayList<ArrayList<BeanTabla12>> repositorio = new ArrayList<ArrayList<BeanTabla12>>();
        int val = 30000;
        ArrayList<BeanTabla12> array;
        int contador = 0;
        int cont = 2;
        try {
            if (rsR != null) {
                //  System.out.println("repositorio sunat tabla 10");
                array = new ArrayList<BeanTabla12>();
                while (rsR.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        // System.out.println("repositorio sunat tabla 10 " + cont);
                        array = new ArrayList<BeanTabla12>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanTabla12 bean = new BeanTabla12();
                    System.out.println("repositorio sunat tabla 10 " + rsR.getString(2)+"---"+rsR.getString(3) );
                    bean.setIdTabla(rsR.getInt(1));
                    bean.setCodmov(rsR.getString(2));
                    bean.setLlave(rsR.getString(3));
                    bean.setDescripcion(rsR.getString(4));
                    bean.setCodigoSunat(rsR.getString(5));

                    array.add(bean);
                }
                repositorio.add(array);
                rsR.close();
                DaoEmpresas.objCnx.destroy();
            }
        } catch (Exception ex) {
        }
        return repositorio;
    }

    public static int registrarTabla12(BeanTabla12 bnTabla12) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        ArrayList param = new ArrayList();
        param.add(OracleTypes.NUMBER);// retorna la funcion
        param.add(OracleTypes.NUMBER);
        param.add(bnTabla12.getCodmov());
        param.add(bnTabla12.getLlave());
        param.add(bnTabla12.getDescripcion());
        param.add(bnTabla12.getCodigoSunat());
        param.add(1);

        ArrayList objetos = DaoTabla12.MantemientoTabla12(param);
        rs = (ResultSet) objetos.get(0);
        cst = (CallableStatement) objetos.get(1);
        int estadoOperacion = 0;
        if (rs != null) {
            while (rs.next()) {
                estadoOperacion = rs.getInt(1);
            }
        }

        return estadoOperacion;
    }

    public static int Actualizar(BeanTabla12 bnTabla12) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        ArrayList param = new ArrayList();
        param.add(OracleTypes.NUMBER);// retorna la funcion
        param.add(bnTabla12.getIdTabla());
        param.add(bnTabla12.getCodmov());
        param.add(bnTabla12.getLlave());
        param.add(bnTabla12.getDescripcion());
        param.add(bnTabla12.getCodigoSunat());
        param.add(2);

        ArrayList objetos = DaoTabla12.MantemientoTabla12(param);
        rs = (ResultSet) objetos.get(0);
        cst = (CallableStatement) objetos.get(1);
        int estadoOperacion = 0;
        if (rs != null) {
            while (rs.next()) {
                estadoOperacion = rs.getInt(1);
            }
        }

        return estadoOperacion;
    }

    public static int Eliminar(BeanTabla12 bnTabla12) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        ArrayList param = new ArrayList();
        param.add(OracleTypes.NUMBER);// retorna la funcion
        param.add(bnTabla12.getIdTabla());
        param.add(bnTabla12.getCodmov());
        param.add(bnTabla12.getLlave());
        param.add(bnTabla12.getDescripcion());
        param.add(bnTabla12.getCodigoSunat());
        param.add(3);

        ArrayList objetos = DaoTabla12.MantemientoTabla12(param);
        rs = (ResultSet) objetos.get(0);
        cst = (CallableStatement) objetos.get(1);
        int estadoOperacion = 0;
        if (rs != null) {
            while (rs.next()) {
                estadoOperacion = rs.getInt(1);
            }
        }

        return estadoOperacion;
    }

    public static void mantenimientoTabla12(HashSet<BeanTabla12> tabla12) throws SQLException {
        Iterator i = tabla12.iterator();
        BeanTabla12 bean;
        int contarEliminados = 0;
        int contarInsertados = 0;
        int contarActualizados = 0;
        ArrayList param = new ArrayList();
        ArrayList objetos;
        CallableStatement cst = null;
        BigDecimal estado = null;
        while (i.hasNext()) {
            bean = (BeanTabla12) i.next();
            if (bean.getOperacion().equals("e")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdTabla());
                param.add(bean.getCodmov());
                param.add(bean.getLlave());
                param.add(bean.getDescripcion());
                param.add(bean.getCodigoSunat());
                param.add(3);
                objetos = DaoTabla12.MantemientoTabla12(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                    contarEliminados = contarEliminados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("a")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdTabla());
                param.add(bean.getCodmov());
                param.add(bean.getLlave());
                param.add(bean.getDescripcion());
                param.add(bean.getCodigoSunat());
                param.add(2);
                objetos = DaoTabla12.MantemientoTabla12(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                    contarActualizados = contarActualizados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("i")) {
                param.add(OracleTypes.NUMBER);
                param.add(OracleTypes.NUMBER);
                param.add(bean.getCodmov());
                param.add(bean.getLlave());
                param.add(bean.getDescripcion());
                param.add(bean.getCodigoSunat());
                param.add(1);
                objetos = DaoTabla12.MantemientoTabla12(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.ONE) == 0) {
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
}
