/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanFormato319;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoFormato319;
import com.contab.datasource.DataSourceFormato319;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author cixtic03
 */
public class LogicFormato319 {

    public static DataSourceFormato319 Formato319(int fechaAno, int fechaMes, String Company) {

        DataSourceFormato319 ds = new DataSourceFormato319();

        ArrayList param = new ArrayList();
        param.add(OracleTypes.CURSOR);
        param.add(fechaAno);
        param.add(fechaMes);
        param.add(Company);
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList objetos = DaoFormato319.consultaFormato319(param);
            rs = (ResultSet) objetos.get(0);
            cst = (OracleCallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanFormato319 objformato319 = new BeanFormato319();
                    objformato319.setCompania(rs.getString(1));
                    objformato319.setCenter(rs.getString(2));
                    objformato319.setCuenta(rs.getString(3));
                    objformato319.setDescripcion(rs.getString(4));
                    objformato319.setSaldo(rs.getBigDecimal(5));
                    objformato319.setJrnnumber(rs.getString(6));
                    objformato319.setSaldodoc(rs.getBigDecimal(7));
                    objformato319.setFechacreacion(rs.getString(8));
                    objformato319.setGlosa(rs.getString(9));
                    objformato319.setProveedor(rs.getString(10));
                    ds.add(objformato319);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
        } catch (SQLException ex) {
            System.out.println("El error de las cuentas activo se debe a:" + ex.getMessage());
        }
        return ds;
    }
}
