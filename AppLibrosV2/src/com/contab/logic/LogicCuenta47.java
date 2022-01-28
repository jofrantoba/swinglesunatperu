/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanCuenta47;
import com.contab.dao.DaoCuenta47;
import com.contab.dao.DaoEmpresas;
import com.contab.datasource.DataSourceCuenta47;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author cixtic04
 */
public class LogicCuenta47 {

    public static DataSourceCuenta47 Cuenta47(int fechaAno, int fechaMes, String Company) {

        DataSourceCuenta47 ds = new DataSourceCuenta47();

        ArrayList param = new ArrayList();
        param.add(OracleTypes.CURSOR);      
        param.add(fechaAno);
        param.add(fechaMes);
        param.add(Company);
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList objetos = DaoCuenta47.cuenta47(param);
            rs = (ResultSet) objetos.get(0);
            cst = (OracleCallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanCuenta47 objcuentas47 = new BeanCuenta47();
                   /* objcuentas47.setNumcuenta(rs.getString(1));
                    objcuentas47.setNombrecuenta(rs.getString(4));*/
                    objcuentas47.setTiposunat(rs.getString(10));
                    objcuentas47.setRucdni(rs.getString(9));
                    objcuentas47.setNombreapellido(rs.getString(4));
                    objcuentas47.setSaldofinal(rs.getBigDecimal(7));
                    ds.add(objcuentas47);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
        } catch (SQLException ex) {
            System.out.println("El error se debe a:" + ex.getMessage());
        }
        return ds;
    }
}
