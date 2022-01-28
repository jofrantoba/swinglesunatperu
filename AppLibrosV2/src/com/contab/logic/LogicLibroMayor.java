package com.contab.logic;

import com.contab.bean.BeanLibroMayor;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoLibroMayor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

public class LogicLibroMayor {
    //Cod_empresa varchar2,Cod_cuenta varchar2,cod_asiento varchar2,anio varchar2,mes varchar2
    public static  ArrayList<BeanLibroMayor> libroMayorS1(String glosa,String codigo_Empresa, String codigo_cuenta,String codigo_asiento, String anio ,String num_mes) throws SQLException {
        ArrayList param = new ArrayList();
        param.add(OracleTypes.CURSOR);
        param.add(codigo_Empresa);
        param.add(codigo_cuenta);
        param.add(codigo_asiento);
        param.add(anio);
        param.add(num_mes);

        ArrayList<BeanLibroMayor> lissql1=new ArrayList<BeanLibroMayor>();

        ArrayList objetos = DaoLibroMayor.consultasql1(param);
        ResultSet rs = (ResultSet) objetos.get(0);
        OracleCallableStatement cst = (OracleCallableStatement) objetos.get(1);
        if (rs != null && cst != null) {
            while (rs.next()) {
                BeanLibroMayor objLibroMayor = new BeanLibroMayor();
                objLibroMayor.setAuxiliar(rs.getString(1));
                objLibroMayor.setVaucher(" ");
                objLibroMayor.setFecha(rs.getDate(3));
                System.out.println(rs.getDate(3)+"fecha...");
                objLibroMayor.setC_central(rs.getString(2));
                objLibroMayor.setGlosa(glosa);
                objLibroMayor.setDebe(rs.getBigDecimal(4));
                objLibroMayor.setHaber(rs.getBigDecimal(5));
                lissql1.add(objLibroMayor);
            }
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }
        return lissql1;
    }

     public static ArrayList<BeanLibroMayor> libroMayorS2(String glosa,String codigo_Empresa, String codigo_cuenta,String codigo_asiento, String anio ,String num_mes) throws SQLException {
        ArrayList param = new ArrayList();
        param.add(OracleTypes.CURSOR);
        param.add(codigo_Empresa);
        param.add(codigo_cuenta);
        param.add(codigo_asiento);
        param.add(anio);
        param.add(num_mes);

        ArrayList<BeanLibroMayor> lissql2=new ArrayList<BeanLibroMayor>();

        ArrayList objetos = DaoLibroMayor.consultasql2(param);
        ResultSet rs = (ResultSet) objetos.get(0);
        OracleCallableStatement cst = (OracleCallableStatement) objetos.get(1);
        if (rs != null && cst != null) {
            while (rs.next()) {
                BeanLibroMayor objLibroMayor = new BeanLibroMayor();
                objLibroMayor.setAuxiliar(rs.getString(1));
                objLibroMayor.setVaucher(rs.getString(2));
                objLibroMayor.setFecha(rs.getDate(3));
                  System.out.println(rs.getDate(3)+"fecha...");
                objLibroMayor.setC_central(rs.getString(4));
                if (rs.getString(5) == null) {
                    objLibroMayor.setGlosa(glosa);
                } else {
                    objLibroMayor.setGlosa(rs.getString(5));                }

                objLibroMayor.setDebe(rs.getBigDecimal(6));
                objLibroMayor.setHaber(rs.getBigDecimal(7));
                lissql2.add(objLibroMayor);
            }
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }
        return lissql2;
    }
}
