/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanBICuenta14;
import com.contab.bean.BeanBICuenta16;
import com.contab.dao.DaoBalanceInventarios;
import com.contab.dao.DaoEmpresas;
import com.contab.datasource.DataSourceBICuenta14;
import com.contab.datasource.DataSourceBICuenta16;
import com.contab.view.ViewApplication;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Administrador
 */
public class LogicBalanceInventarios {

    public static void BICuenta14(String anno, String codcompany, String tipo, DataSourceBICuenta14 ds) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(anno);
            param.add(codcompany);
            param.add(tipo);
            ArrayList objetos = DaoBalanceInventarios.consultarCuenta14(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("CARGANDO ...");
                while (rs.next()) {
                    BeanBICuenta14 objcuenta14 = new BeanBICuenta14();
                    objcuenta14.setTipoper(rs.getString(1));

                    if (rs.getString(3) != null) {
                        ArrayList<String> doc = DniORuc(rs.getString(3));
                        objcuenta14.setTipodoc(doc.get(0));
                        objcuenta14.setDni(doc.get(1));
                    }

                    objcuenta14.setDescripcion(rs.getString(4));
                    objcuenta14.setMonto(rs.getBigDecimal(5));
                    objcuenta14.setFechadoc(rs.getDate(6));
                    ds.add(objcuenta14);
                }
            }
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }
    }
    
    public static void BICuenta16(String anno, String codcompany, DataSourceBICuenta16 ds) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(anno);
            param.add(codcompany);            
            ArrayList objetos = DaoBalanceInventarios.consultarCuenta16(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("CARGANDO ...");
                while (rs.next()) {
                    BeanBICuenta16 objcuenta16 = new BeanBICuenta16();
                    objcuenta16.setTipoper(rs.getString(1));
                    if (rs.getString(3) != null) {
                        ArrayList<String> doc = DniORuc(rs.getString(3));
                        objcuenta16.setTipodoc(doc.get(0));
                        objcuenta16.setDni(doc.get(1));
                    }

                    objcuenta16.setDescripcion(rs.getString(4));
                    objcuenta16.setMonto(rs.getBigDecimal(5));
                    objcuenta16.setFechadoc(rs.getDate(6));
                    ds.add(objcuenta16);
                }
            }
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }
    }

    private static ArrayList<String> DniORuc(String param) {
        ArrayList val = new ArrayList();
        String doc = param.trim();
        String op = "1";
        if (doc.length() == 11) {
            val.add("6");
            val.add(doc);
            op = "0";
        }

        if (doc.length() == 8) {
            val.add("1");
            val.add(doc);
            op = "0";
        }


        if (!op.equals("0")) {
            val.add(" ");
            val.add(" ");
        }

//        
//        try{
//            BigDecimal ruc =BigDecimal.valueOf(Double.valueOf(param));
//            val.add("RUC");
//            val.add(ruc.toString());
//        }catch(Exception ex){
//            try{
//            String dni=param.substring(2,param.length()-1);
//            System.out.println(dni);
//            val.add("DNI");
//            val.add(dni);
//            }catch(Exception e){
//               val.add("DNI/RUC");
//               val.add("NO DATA"); 
//            }
//        }        
        return val;
    }
}
