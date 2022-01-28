/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanEmpresa;
import com.contab.bean.BeanTabla10;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoTabla10;
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
public class LogicTabla10 {

   public static ArrayList<BeanTabla10> listaTabla10() {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList<BeanTabla10> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoTabla10.CONSULTATABLE10(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanTabla10 obj = new BeanTabla10();
                    obj.setIdTabla10(rs.getInt(1));
                    obj.setCodigoXray(rs.getString(2));
                    obj.setCodigoSunat(rs.getString(3));
                    

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

    

    public static ResultSet ListarTabla10R() throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);

            ArrayList objetos = DaoTabla10.CONSULTATABLE10(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                return rs;
            }
            cst.close();
         //   DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
        } finally {
            //  cst.close();
            DaoEmpresas.objCnx.destroy();
        }
        return rs;
    }

    public static BeanTabla10 buscarTabla10(ArrayList<ArrayList<BeanTabla10>> repor, String unidad) {
        BeanTabla10 bean = new BeanTabla10();
        BeanTabla10 beanaux = new BeanTabla10();
        ArrayList<BeanTabla10> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanTabla10>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanTabla10) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getCodigoXray().equals(unidad)) {
                //    System.out.println("encontrado " + unidad);
                    // array.remove(beanaux);
                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;
    }

    public static ArrayList<ArrayList<BeanTabla10>> repositorio(ResultSet rs) {
        ArrayList<ArrayList<BeanTabla10>> repositorio = new ArrayList<ArrayList<BeanTabla10>>();
        int val = 30000;
        ArrayList<BeanTabla10> array;
        int contador = 0;
        int cont = 2;
        try {
            if (rs != null) {
               // System.out.println("repositorio sunat tabla 10");
                array = new ArrayList<BeanTabla10>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                      //  System.out.println("repositorio sunat tabla 10" + cont);
                        array = new ArrayList<BeanTabla10>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanTabla10 bean = new BeanTabla10();
                    bean.setCodigoXray(rs.getString(2));
                    bean.setCodigoSunat(rs.getString(3));

                    array.add(bean);
                }
                repositorio.add(array);
                rs.close();
            }
        } catch (Exception ex) {
        }
        return repositorio;
    }

    public static void mantenimientoTabla10(HashSet<BeanTabla10> tabla10) throws SQLException {
        Iterator i = tabla10.iterator();
        BeanTabla10 bean;
        int contarEliminados = 0;
        int contarInsertados = 0;
        int contarActualizados = 0;
        ArrayList param = new ArrayList();
        ArrayList objetos;
        CallableStatement cst = null;
        BigDecimal estado = null;
        while (i.hasNext()) {
            bean = (BeanTabla10) i.next();
            if (bean.getOperacion().equals("e")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdTabla10());
                param.add(bean.getCodigoXray());
                param.add(bean.getCodigoSunat());
                param.add(3);
                objetos = DaoTabla10.MantemientoTabla10(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                    contarEliminados = contarEliminados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("a")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdTabla10());
                param.add(bean.getCodigoXray());
                param.add(bean.getCodigoSunat());
                param.add(2);
                objetos = DaoTabla10.MantemientoTabla10(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                    contarActualizados = contarActualizados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("i")) {
                param.add(OracleTypes.NUMBER);
                param.add(OracleTypes.NUMBER);
                param.add(bean.getCodigoXray());
                param.add(bean.getCodigoSunat());
                param.add(1);
                objetos = DaoTabla10.MantemientoTabla10(param);
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
