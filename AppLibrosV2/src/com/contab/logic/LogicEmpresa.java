/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanEmpresa;
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
public class LogicEmpresa {

    public static ArrayList<BeanEmpresa> listaDeEmpresa() throws SQLException {
        ResultSet rs=null;
        CallableStatement cst=null;
        try {
            ArrayList<BeanEmpresa> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoEmpresas.consultaEmpresas(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanEmpresa objEmpresa = new BeanEmpresa();
                    objEmpresa.setCodigo(rs.getString(1));
                    objEmpresa.setDescripcion(rs.getString(2));
                    objEmpresa.setRuc(rs.getString(3));
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
