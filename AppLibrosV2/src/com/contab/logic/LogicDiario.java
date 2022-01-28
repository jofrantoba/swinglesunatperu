/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanLibroDiario;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoLibroDiario;
import com.contab.datasource.DataSourceLibroDiario;
import com.contab.view.ViewApplication;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author 777
 */
public class LogicDiario {

    public static void libroDiario(String journalType, String glosa, java.sql.Date fechaInicio, java.sql.Date fechaFin, String codigoEmpresa, DataSourceLibroDiario ds) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(journalType);
            param.add(fechaInicio);
            param.add(fechaFin);
            param.add(codigoEmpresa);
            ArrayList objetos = DaoLibroDiario.consultarDiario(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("CUENTAS DEL ASIENTO: " + glosa + " DESDE " + fechaInicio + " HASTA " + fechaFin);
                //BigDecimal totalDebe = BigDecimal.ZERO;
                //BigDecimal totalHaber = BigDecimal.ZERO;
                //int contador = 0;
                while (rs.next()) {
                    BeanLibroDiario objLibroDiario = new BeanLibroDiario();
                    objLibroDiario.setCuenta(rs.getString(1).toString());
                    objLibroDiario.setCcenter(rs.getString(2).toString());
                    BigDecimal debe = rs.getBigDecimal(4);
                    objLibroDiario.setDebe(debe);
                    //totalDebe = totalDebe.add(debe);
                    BigDecimal haber = rs.getBigDecimal(5);
                    objLibroDiario.setHaber(haber);
                    //totalHaber = totalHaber.add(haber);
                    objLibroDiario.setCodigooper(journalType);
                    objLibroDiario.setGlosa(glosa);
                    objLibroDiario.setFechaoper(fechaFin);
                    String denom = rs.getString(3);
                    objLibroDiario.setDenominacion(denom);
                    if(debe.equals(BigDecimal.ZERO)&&haber.equals(BigDecimal.ZERO)){
                       System.out.println("Cero Eliminado");
                    }else{
                        ds.add(objLibroDiario);   
                    }                    
                    //contador = contador + 1;
                }
                //if (contador > 0) {
                    //BeanLibroDiario objLibroDiario = new BeanLibroDiario();
                    //objLibroDiario.setDenominacion("CENTRALIZA: " + glosa);
                    //objLibroDiario.setDebe(totalDebe);
                    //objLibroDiario.setHaber(totalHaber);
                    //objLibroDiario.setCodigooper("");
                    //objLibroDiario.setGlosa("");
                    //ds.add(objLibroDiario);
                //}
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
}
