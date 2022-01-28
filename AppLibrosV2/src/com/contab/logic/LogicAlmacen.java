/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanAlmacen;
import com.contab.dao.DaoAlmacen;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author 777
 */
public class LogicAlmacen {

    public static ArrayList<BeanAlmacen> listaDeAlmacen(String codigo) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList<BeanAlmacen> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(codigo);
            ArrayList objetos = DaoAlmacen.listarAlmacen(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanAlmacen objAlmacen = new BeanAlmacen();
                    objAlmacen.setCodigo(rs.getString(1));
                    objAlmacen.setDescripcion(rs.getString(2));
                    objAlmacen.setDireccion(rs.getString(3));
                    lista.add(objAlmacen);
                }
                rs.close();
                cst.close();
                // DaoAlmacen.objCnx.destroy();
            }
            return lista;
        } catch (SQLException ex) {
            rs.close();
            cst.close();
            // DaoAlmacens.objCnx.destroy();
            return null;
        } finally {
            rs.close();
            cst.close();
            //DaoAlmacens.objCnx.destroy();
        }

    }
}
