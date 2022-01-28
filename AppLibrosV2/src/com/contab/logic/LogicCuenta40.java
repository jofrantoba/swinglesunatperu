/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanCuenta40;
import com.contab.dao.Conexion;
import com.contab.dao.DaoCuenta40;
import com.contab.datasource.DatasourceCuenta40;
import com.contab.util.Exportar;
import com.contab.view.ViewApplication;
import java.io.File;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRParameter;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author cixtic04
 */
public class LogicCuenta40 {

    public static void listaSaldoCuenta40(String empresa, String ruc, String compania, String ano, File ruta, String tipoExpor) throws SQLException, Exception {

        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            Conexion cnx = null;
            try {
                DatasourceCuenta40 dataCuenta40 = new DatasourceCuenta40();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                param.add(ano); //ano
                ArrayList objetos = new ArrayList();
                objetos = DaoCuenta40.consultaCuenta40(param);
                rs = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                cnx = (Conexion) objetos.get(2);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO  CUENTA 40 Nª: " + empresa + " DEL PERIODO: " + ano);


                if (rs != null && cst != null && cnx != null) {
                    while (rs.next()) {

                        BeanCuenta40 bnCuenta40 = new BeanCuenta40();
                        ViewApplication.mensajeEstado.setText("PROCESANDO  CUENTA  40 Nª: " + empresa + " DEL PERIODO: " + ano + " numero" + rs.getString(1));

                        String codigo = rs.getString(1);
                        String denominacion = rs.getString(3);
                        BigDecimal monto = rs.getBigDecimal(7);

                        if (monto.compareTo(BigDecimal.ZERO) != 0) {
                            bnCuenta40.setCodigo(codigo);
                            bnCuenta40.setDenominacion(denominacion);
                            bnCuenta40.setSaldoFinal(monto);

                            dataCuenta40.addBalance(bnCuenta40);

                        }



                    }
                }
                //  }


                Map parameters = new HashMap();
                parameters.put("P_RAZON", empresa);
                parameters.put("P_RUC", ruc);
                parameters.put("P_PERIODO", ano);
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptcaja = "/com/contab/report/cuenta40.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataCuenta40, rutarptcaja);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE CUENTA 40!!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE CUENTA 40", "ALERT", JOptionPane.INFORMATION_MESSAGE);
                rs.close();
                cst.close();
                cnx.destroy();

            } catch (SQLException ex) {
                rs.close();
                cst.close();
                cnx.destroy();
            } finally {
                rs.close();
                cst.close();
                cnx.destroy();
            }


        }
    }
}
