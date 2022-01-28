package com.contab.logic;

import com.contab.bean.BeanCuentaMayor;
import com.contab.dao.DaoCuentaMayor;
import com.contab.dao.DaoEmpresas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class LogicCuentaMayor {

    public static ArrayList<BeanCuentaMayor> listarFucuentas(String codigoEmpresa) throws SQLException {
        ArrayList<BeanCuentaMayor> lista = new ArrayList();
        ArrayList param = new ArrayList();
        param.add(OracleTypes.CURSOR);
        param.add(codigoEmpresa);
        ArrayList objetos = (ArrayList) DaoCuentaMayor.consultaAsiento(param);
        ResultSet rs = (ResultSet) objetos.get(0);
        OracleCallableStatement cst = (OracleCallableStatement) objetos.get(1);
        if (rs != null && cst != null) {
            BeanCuentaMayor objFirst = new BeanCuentaMayor();
//            objFirst.setCodigocuenta("all");
//            objFirst.setNombrecuenta("Todos");
            //lista.add(objFirst);
            while (rs.next()) {
                BeanCuentaMayor objcuenta = new BeanCuentaMayor();
                objcuenta.setCodigocuenta(rs.getString(1));
                objcuenta.setNombrecuenta(rs.getString(2));
                lista.add(objcuenta);
            }
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }
        return lista;
    }
}
