package com.contab.logic;

import com.contab.bean.BeanCuenta46;
import com.contab.dao.DaoCuenta46;
import com.contab.dao.DaoEmpresas;
import com.contab.datasource.DataSourceCuenta46;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class LogicCuenta46 {

    public static DataSourceCuenta46 Cuenta46(String cuenta, int fechaAno, int fechaMes, String Company) {
        
        DataSourceCuenta46 ds = new DataSourceCuenta46();
        
        ArrayList param = new ArrayList();
        param.add(OracleTypes.CURSOR);
        param.add(cuenta);
        param.add(fechaAno);
        param.add(fechaMes);
        param.add(Company);
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList objetos = DaoCuenta46.consultarCuenta46(param);
            rs = (ResultSet) objetos.get(0);
            cst = (OracleCallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    String documento = rs.getString(12);
                    String tabla2="";
                     if (documento != null) {
                                if (!documento.equals("")) {

                                    if (documento.length() >= 8) {
                                        if (documento.length() == 8) {

                                            tabla2 = "01";
                                        } else {
                                            tabla2 = "06";
                                        }
                                    }
                                }
                            }
                    BeanCuenta46 objcuentas46 = new BeanCuenta46();
                    objcuentas46.setTipo(tabla2);
                    objcuentas46.setFechaemision(rs.getDate(8));
                    objcuentas46.setMontobase(rs.getBigDecimal(5));
                    objcuentas46.setCuenta(rs.getString(4));
                    objcuentas46.setNumcuenta(rs.getString(3));
                    objcuentas46.setDc(rs.getBigDecimal(7));
                    objcuentas46.setApellidosnombre(rs.getString(10));
                    objcuentas46.setDescripcion(rs.getString(9));
                    objcuentas46.setNumero(rs.getString(12));
                    ds.add(objcuentas46);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
        } catch (SQLException ex) {
            System.out.println("El error de las cuentas activo se debe a:" + ex.getMessage());
        } /*finally {
            try {
                rs.close();
                cst.close();
                DaoCuenta46.objCnx.destroy();
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }*/
        return ds;
    }
}
