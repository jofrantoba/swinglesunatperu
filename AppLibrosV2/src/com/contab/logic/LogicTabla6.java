/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanEmpresa;
import com.contab.bean.BeanTabla6;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoTabla6;
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
    public class LogicTabla6 {
    public static ArrayList<BeanTabla6> listaTabla6() {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList<BeanTabla6> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoTabla6.CONSULTATABLE6(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanTabla6 obj = new BeanTabla6();
                    obj.setIdUnidadArticulo(rs.getString(1));
                    obj.setUnidad(rs.getString(2));
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

      public static ResultSet ListarUnidadArticulos() throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
           
            ArrayList objetos = DaoTabla6.CONSULTATABLE6(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                return rs;
            }
            cst.close();
           // DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
        } finally {
            //  cst.close();
            DaoEmpresas.objCnx.destroy();
        }
        return rs;
    }
    
      public static BeanTabla6 buscarTabla6(ArrayList<ArrayList<BeanTabla6>> repor, String unidad) {
        BeanTabla6 bean = new BeanTabla6();
        BeanTabla6 beanaux = new BeanTabla6();
        ArrayList<BeanTabla6> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanTabla6>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanTabla6) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getUnidad().equals(unidad)) {
                  //  System.out.println("encontrado " + unidad);
                    // array.remove(beanaux);
                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;
    }

    public static ArrayList<ArrayList<BeanTabla6>> repositorio(ResultSet rs) {
        ArrayList<ArrayList<BeanTabla6>> repositorio = new ArrayList<ArrayList<BeanTabla6>>();
        int val = 30000;
        ArrayList<BeanTabla6> array;
        int contador = 0;
        int cont = 2;
        try {
            if (rs != null) {
             //   System.out.println("repositorio sunat tabla 6");
                array = new ArrayList<BeanTabla6>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                       // System.out.println("repositorio sunat tabla 6 " + cont);
                        array = new ArrayList<BeanTabla6>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanTabla6 bean = new BeanTabla6();
                    bean.setUnidad(rs.getString(2));
                    bean.setCodigoSunat(rs.getString(3));
                   
                    array.add(bean);
                }
                repositorio.add(array);
                rs.close();
                 DaoEmpresas.objCnx.destroy();
            }
        } catch (Exception ex) {
        }
        return repositorio;
    }
     public static void mantenimientoTabla06(HashSet<BeanTabla6> tabla6) throws SQLException {
        Iterator i = tabla6.iterator();
        BeanTabla6 bean;
        int contarEliminados = 0;
        int contarInsertados = 0;
        int contarActualizados = 0;
        ArrayList param = new ArrayList();
        ArrayList objetos;
        CallableStatement cst = null;
        BigDecimal estado = null;
        while (i.hasNext()) {
            bean = (BeanTabla6) i.next();
            if (bean.getOperacion().equals("e")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdUnidadArticulo());
                param.add(bean.getUnidad());
                param.add(bean.getCodigoSunat());
                param.add(3);
                objetos = DaoTabla6.MantemientoTabla6(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                    contarEliminados = contarEliminados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("a")) {
                param.add(OracleTypes.NUMBER);
               param.add(bean.getIdUnidadArticulo());
                param.add(bean.getUnidad());
                param.add(bean.getCodigoSunat());
                param.add(2);
                objetos = DaoTabla6.MantemientoTabla6(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                    contarActualizados = contarActualizados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("i")) {
                param.add(OracleTypes.NUMBER);
                param.add(OracleTypes.NUMBER);
                param.add(bean.getUnidad());
                param.add(bean.getCodigoSunat());
                param.add(1);
                objetos = DaoTabla6.MantemientoTabla6(param);
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
