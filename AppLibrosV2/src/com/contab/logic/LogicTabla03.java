/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;


import com.contab.bean.BeanBanco;
import com.contab.bean.BeanTabla03;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoTabla03;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author 777
 */
public class LogicTabla03 {

    public static ArrayList<BeanTabla03> listaTabla03() {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList<BeanTabla03> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoTabla03.CONSULTATABLE03(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanTabla03 obj = new BeanTabla03();
                    obj.setIdTabla03(rs.getInt(1));
                    obj.setCompania(rs.getString(2));
                    obj.setBanco(rs.getString(3));
                    obj.setTipo(rs.getString(6));
                    obj.setNumeroCuenta(rs.getString(4));
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

    public static ResultSet ListarTabla03() throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);

            ArrayList objetos = DaoTabla03.CONSULTATABLE03(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                return rs;
            }
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
        } finally {
            //  cst.close();
            DaoEmpresas.objCnx.destroy();
        }
        return rs;
    }

    public static BeanBanco buscarTabla03(ArrayList<ArrayList<BeanBanco>> repor, String unidad) {
        BeanBanco bean = new BeanBanco();
        BeanBanco beanaux = new BeanBanco();
        ArrayList<BeanBanco> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanBanco>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanBanco) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getDescripcion().equals(unidad)) {
                    System.out.println("encontrado " + unidad);
                    // array.remove(beanaux);
                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;
    }

    public static ArrayList<ArrayList<BeanBanco>> repositorio(ResultSet rsR) {
        ArrayList<ArrayList<BeanBanco>> repositorio = new ArrayList<ArrayList<BeanBanco>>();
        int val = 30000;
        ArrayList<BeanBanco> array;
        int contador = 0;
        int cont = 2;
        try {
            if (rsR != null) {
                System.out.println("repositorio sunat tabla 10");
                array = new ArrayList<BeanBanco>();
                while (rsR.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        System.out.println("repositorio sunat tabla 10 " + cont);
                        array = new ArrayList<BeanBanco>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanBanco bean = new BeanBanco();

                    bean.setIdTabla3(rsR.getInt(1));
                    bean.setDescripcion(rsR.getString(2));
                    bean.setNumCuenta(rsR.getString(3));
                    bean.setCenter(rsR.getString(4));
                    bean.setDescripcion(rsR.getString(5));
                    bean.setNumCuenta(rsR.getString(6));
                    bean.setNombreBanco(rsR.getString(7));
                    bean.setCodigoCuentaSunat(rsR.getString(8));


                    array.add(bean);
                }
                repositorio.add(array);
                rsR.close();
            }
        } catch (Exception ex) {
        }
        return repositorio;
    }

    public static int registrarTabla03(BeanBanco bnTabla03) throws SQLException {

        ArrayList param = new ArrayList();
        param.add(OracleTypes.NUMBER);// retorna la funcion
        param.add(OracleTypes.NUMBER);
        param.add(bnTabla03.getCompania());
        param.add(bnTabla03.getCenter());
        param.add(bnTabla03.getCuentaBanco());
        param.add(bnTabla03.getDescripcion());
        param.add(bnTabla03.getNumeroCuentaBanco());
        param.add(bnTabla03.getNombreBanco());
        param.add(bnTabla03.getCodigoCuentaSunat());
        param.add(1);


        ArrayList objetos = DaoTabla03.MantemientoTabla03(param);
        int estadoOperacion = 0;
        try {
            BigDecimal estado = (BigDecimal) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            estadoOperacion = Integer.parseInt(estado.toString());
        } catch (Exception ex) {
            System.out.println(ex);

        }

        return estadoOperacion;
    }

    public static int ActualizarTabla03(BeanBanco bnTabla03) throws SQLException {

        ArrayList param = new ArrayList();
        param.add(OracleTypes.NUMBER);// ret
        param.add(bnTabla03.getIdTabla3());// retorna la funcion
        param.add(bnTabla03.getCompania());
        param.add(bnTabla03.getCuentaBanco());
        param.add(bnTabla03.getCenter());
        param.add(bnTabla03.getDescripcion());
        param.add(bnTabla03.getNumeroCuentaBanco());
        param.add(bnTabla03.getNombreBanco());
        param.add(bnTabla03.getCodigoCuentaSunat());
        param.add(2);

        ArrayList objetosM = DaoTabla03.MantemientoTabla03(param);

        int estadoOperacion = 0;
        try {
            BigDecimal estado = (BigDecimal) objetosM.get(0);
            CallableStatement cst = (CallableStatement) objetosM.get(1);
            estadoOperacion = Integer.parseInt(estado.toString());
        } catch (Exception ex) {
            System.out.println(ex);

        }

        return estadoOperacion;
    }

    public static int EliminarTabla03(BeanBanco bnTabla03) throws SQLException {

        ArrayList param = new ArrayList();
        param.add(OracleTypes.NUMBER);// retorna la funcion
        param.add(bnTabla03.getIdTabla3());// retorna la funcion
        param.add(bnTabla03.getCompania());
        param.add(bnTabla03.getCuentaBanco());
        param.add(bnTabla03.getCenter());
        param.add(bnTabla03.getDescripcion());
        param.add(bnTabla03.getNumeroCuentaBanco());
        param.add(bnTabla03.getNombreBanco());
        param.add(bnTabla03.getCodigoCuentaSunat());
        param.add(3);

        ArrayList objetos = DaoTabla03.MantemientoTabla03(param);
        int estadoOperacion = 0;
        try {
            BigDecimal estado = (BigDecimal) objetos.get(0);
            CallableStatement cst = (CallableStatement) objetos.get(1);
            estadoOperacion = Integer.parseInt(estado.toString());
        } catch (Exception ex) {
            System.out.println(ex);

        }

        return estadoOperacion;
    }

    public static void mantenimientoTabla03(HashSet<BeanTabla03> tabla03) throws SQLException {
        Iterator i = tabla03.iterator();
        BeanTabla03 bean;
        int contarEliminados = 0;
        int contarInsertados = 0;
        int contarActualizados = 0;
        ArrayList param = new ArrayList();
        ArrayList objetos;
        CallableStatement cst = null;
        BigDecimal estado = null;
        while (i.hasNext()) {
            bean = (BeanTabla03) i.next();
            if (bean.getOperacion().equals("e")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdTabla03());
                param.add(bean.getCompania());
                param.add(bean.getBanco());
                param.add(bean.getNumeroCuenta());
                param.add(bean.getCodigoSunat());
                param.add(bean.getTipo());
                param.add(3);
                objetos = DaoTabla03.MantemientoTabla03(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                    contarEliminados = contarEliminados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("a")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdTabla03());
                param.add(bean.getCompania());
                param.add(bean.getBanco());
                param.add(bean.getNumeroCuenta());
                param.add(bean.getCodigoSunat());
                param.add(bean.getTipo());
                param.add(2);
                objetos = DaoTabla03.MantemientoTabla03(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                    contarActualizados = contarActualizados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("i")) {
                param.add(OracleTypes.NUMBER);
                param.add(OracleTypes.NUMBER);
                param.add(bean.getCompania());
                param.add(bean.getBanco());
                param.add(bean.getNumeroCuenta());
                param.add(bean.getCodigoSunat());
                param.add(bean.getTipo());
                param.add(1);
                objetos = DaoTabla03.MantemientoTabla03(param);
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
