/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanActivoFijo;
import com.contab.dao.Conexion;
import com.contab.dao.DaoActivoFijo;
import com.contab.datasource.DatasourceActivoFijo;
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
public class LogicActivoFijo {
    
    public static void listaSaldoACTIVOFIJO(String empresa, String ruc, String compania, String ano, File ruta, String tipoExpor) throws SQLException, Exception {
        
        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            
            ResultSet rsC = null;
            CallableStatement cstC = null;
            Conexion cnx = null;
            try {
                DatasourceActivoFijo dataACTIVOFIJO = new DatasourceActivoFijo();
                
                
                ArrayList paramC = new ArrayList();
                paramC.add(OracleTypes.CURSOR);
                paramC.add(compania); //numero de la empresa o compa;ia

                ArrayList objetosC = new ArrayList();
                objetosC = DaoActivoFijo.ListaCuentaCaja(paramC);
                rsC = (ResultSet) objetosC.get(0);
                cstC = (CallableStatement) objetosC.get(1);
                cnx = (Conexion) objetosC.get(2);
                BigDecimal cuenta12 = BigDecimal.ZERO;
                BigDecimal cuenta16 = BigDecimal.ZERO;
                BigDecimal cuenta40 = BigDecimal.ZERO;
                BigDecimal cuenta41 = BigDecimal.ZERO;
                BigDecimal cuenta42 = BigDecimal.ZERO;
                BigDecimal cuenta46 = BigDecimal.ZERO;
                BigDecimal cuenta47 = BigDecimal.ZERO;
                BigDecimal cuenta63 = BigDecimal.ZERO;
                BigDecimal cuenta64 = BigDecimal.ZERO;
                BigDecimal cuenta65 = BigDecimal.ZERO;
                BigDecimal cuenta66 = BigDecimal.ZERO;
                BigDecimal cuenta67 = BigDecimal.ZERO;
                BigDecimal cuenta33 = BigDecimal.ZERO;
                BigDecimal cuenta31 = BigDecimal.ZERO;
                BigDecimal cuenta38 = BigDecimal.ZERO;
                BigDecimal cuenta75 = BigDecimal.ZERO;
                BigDecimal cuenta76 = BigDecimal.ZERO;
                BigDecimal cuenta77 = BigDecimal.ZERO;
                BigDecimal cuenta10 = BigDecimal.ZERO;
                
                
                
                
                BigDecimal cuenta12D = BigDecimal.ZERO;
                BigDecimal cuenta16D = BigDecimal.ZERO;
                BigDecimal cuenta40D = BigDecimal.ZERO;
                BigDecimal cuenta41D = BigDecimal.ZERO;
                BigDecimal cuenta42D = BigDecimal.ZERO;
                BigDecimal cuenta46D = BigDecimal.ZERO;
                BigDecimal cuenta47D = BigDecimal.ZERO;
                BigDecimal cuenta63D = BigDecimal.ZERO;
                BigDecimal cuenta64D = BigDecimal.ZERO;
                BigDecimal cuenta65D = BigDecimal.ZERO;
                BigDecimal cuenta66D = BigDecimal.ZERO;
                BigDecimal cuenta67D = BigDecimal.ZERO;
                BigDecimal cuenta33D = BigDecimal.ZERO;
                BigDecimal cuenta31D = BigDecimal.ZERO;
                BigDecimal cuenta38D = BigDecimal.ZERO;
                BigDecimal cuenta75D = BigDecimal.ZERO;
                BigDecimal cuenta76D = BigDecimal.ZERO;
                BigDecimal cuenta77D = BigDecimal.ZERO;
                BigDecimal cuenta10D = BigDecimal.ZERO;
                
                
                
                
                
                BigDecimal cuenta12H = BigDecimal.ZERO;
                BigDecimal cuenta16H = BigDecimal.ZERO;
                BigDecimal cuenta40H = BigDecimal.ZERO;
                BigDecimal cuenta41H = BigDecimal.ZERO;
                BigDecimal cuenta42H = BigDecimal.ZERO;
                BigDecimal cuenta46H = BigDecimal.ZERO;
                BigDecimal cuenta47H = BigDecimal.ZERO;
                BigDecimal cuenta63H = BigDecimal.ZERO;
                BigDecimal cuenta64H = BigDecimal.ZERO;
                BigDecimal cuenta65H = BigDecimal.ZERO;
                BigDecimal cuenta66H = BigDecimal.ZERO;
                BigDecimal cuenta67H = BigDecimal.ZERO;
                BigDecimal cuenta33H = BigDecimal.ZERO;
                BigDecimal cuenta31H = BigDecimal.ZERO;
                BigDecimal cuenta38H = BigDecimal.ZERO;
                BigDecimal cuenta75H = BigDecimal.ZERO;
                BigDecimal cuenta76H = BigDecimal.ZERO;
                BigDecimal cuenta77H = BigDecimal.ZERO;
                BigDecimal cuenta10H = BigDecimal.ZERO;
                
                BigDecimal cuenta1040405 = BigDecimal.ZERO;
                BigDecimal resto = BigDecimal.ZERO;
                String cuentaM = "";
                String AGRUPAR = "";
                String Jrnnumber = "";
                
                if (rsC != null && cstC != null && cnx != null) {
                    while (rsC.next()) {

                        ////-------------------//////////////////
                        ArrayList param = new ArrayList();
                        param.add(OracleTypes.CURSOR);
                        param.add(compania); //numero de la empresa o compa;ia
                        String CUENTAG = rsC.getString(1);
                        String CENTROG = rsC.getString(2);
                        String NOMBRE = rsC.getString(3);
                        param.add(CUENTAG); //CUENTA
                        param.add(CENTROG); //CENTRO
                        param.add(ano); //ano
                        ArrayList objetos = new ArrayList();
                        objetos = DaoActivoFijo.consultaActivoFijo(param);
                        rs = (ResultSet) objetos.get(0);
                        cst = (CallableStatement) objetos.get(1);
                        cnx = (Conexion) objetos.get(2);
                        ViewApplication.mensajeEstado.setText(null);
                        ViewApplication.mensajeEstado.setText("PROCESANDO  ACTIVO FIJO  Nª: " + empresa + " DEL PERIODO: " + ano);
                        BigDecimal DEBE = BigDecimal.ZERO;
                        BigDecimal HABER = BigDecimal.ZERO;
                        BigDecimal SALDO = BigDecimal.ZERO;
                        
                        
                        if (rs != null && cst != null && cnx != null) {
                            while (rs.next()) {
                                
                                BeanActivoFijo bnCuenta40 = new BeanActivoFijo();
                                ViewApplication.mensajeEstado.setText("PROCESANDO   ACTIVO FIJO  40 Nª: " + empresa + " DEL PERIODO: " + ano + " numero" + rs.getString(1));
                                
                                String CUENTA = rs.getString(4);
                                String CENTRO = rs.getString(5);
                                String cuentaC = "";
                                String cuentaC1 = "";
                                DEBE = rs.getBigDecimal(9);
                                HABER = rs.getBigDecimal(10);
                                SALDO = rs.getBigDecimal(11);
                                String glosa = rs.getString(12);
                                Jrnnumber = rs.getString(1);
                                
                              //  System.out.println("Jrnnumber " + Jrnnumber + "  " + cuentaC + "  " + CUENTAG);
                                AGRUPAR = CUENTAG + "-" + CENTROG;
                                cuentaC = CUENTA.substring(0,2);
                                System.out.println("ok CUENTA"+"---"+CUENTA+"CENTRO"+CENTROG+" "+cuentaC);
                                if (CUENTA.trim().equals(CUENTAG.trim()) && CENTRO.trim().equals(CENTROG)) {
                                    
                                    
                                    cuenta1040405 = cuenta1040405.add(SALDO);
                                } else {
                                    System.out.println("ok ingreso"+"---"+cuentaC +"--"+CUENTAG);

                                    //cuentaM = cuentaC + cuentaM + "/ ";
                                    resto = resto.add(SALDO);

                                    //cuentaC = cuentaC.trim();
                                    if (cuentaC.trim().equals("10")) {
                                        cuenta10 = cuenta10.add(SALDO);
                                        cuenta10D = cuenta10D.add(DEBE);
                                        cuenta10H = cuenta10H.add(HABER);
                                          System.out.println("ingreso  10 ");
                                        
                                    }
                                    if (cuentaC.trim().equals("12")) {
                                        System.out.println("ingreso  12 ");
                                        cuenta12 = cuenta12.add(SALDO);
                                        cuenta12D = cuenta12D.add(DEBE);
                                        cuenta12H = cuenta12H.add(HABER);
                                    }
                                    if (cuentaC.trim().equals("16")) {
                                          System.out.println("ingreso  12 ");
                                        cuenta16 = cuenta16.add(SALDO);
                                        cuenta16D = cuenta16D.add(DEBE);
                                        cuenta16H = cuenta16H.add(HABER);
                                        
                                    }
                                    if (cuentaC.trim().equals("31")) {
                                         System.out.println("ingreso  31 ");
                                        cuenta31 = cuenta31.add(SALDO);
                                        cuenta31D = cuenta31D.add(DEBE);
                                        cuenta31H = cuenta31H.add(HABER);
                                        
                                    }
                                    
                                    if (cuentaC.trim().equals("33")) {
                                         System.out.println("ingreso  33 ");
                                        cuenta33 = cuenta33.add(SALDO);
                                        cuenta33D = cuenta33D.add(DEBE);
                                        cuenta33H = cuenta33H.add(HABER);
                                        
                                    }
                                    
                                    if (cuentaC.trim().equals("38")) {
                                         System.out.println("ingreso  38 ");
                                        cuenta38 = cuenta38.add(SALDO);
                                        cuenta38D = cuenta38D.add(DEBE);
                                        cuenta38H = cuenta38H.add(HABER);
                                        
                                    }
                                    
                                    if (cuentaC.trim().equals("40")) {
                                         System.out.println("ingreso  40 ");
                                        cuenta40 = cuenta40.add(SALDO);
                                        cuenta40D = cuenta40D.add(DEBE);
                                        cuenta40H = cuenta40H.add(HABER);
                                        
                                    }
                                    
                                    if (cuentaC.trim().equals("41")) {
                                         System.out.println("ingreso  41 ");
                                        cuenta41 = cuenta41.add(SALDO);
                                        cuenta41D = cuenta41D.add(DEBE);
                                        cuenta41H = cuenta41H.add(HABER);
                                        
                                    }
                                    
                                    if (cuentaC.trim().equals("42")) {
                                        cuenta42 = cuenta42.add(SALDO);
                                        cuenta42D = cuenta42D.add(DEBE);
                                        cuenta42H = cuenta42H.add(HABER);
                                        
                                    }
                                    if (cuentaC.trim().equals("46")) {
                                         System.out.println("ingreso  46 ");
                                        cuenta46 = cuenta46.add(SALDO);
                                        cuenta46D = cuenta46D.add(DEBE);
                                        cuenta46H = cuenta46H.add(HABER);
                                        
                                    }
                                    if (cuentaC.trim().equals("47")) {
                                         System.out.println("ingreso  47 ");
                                        cuenta47 = cuenta47.add(SALDO);
                                        cuenta47D = cuenta47D.add(DEBE);
                                        cuenta47H = cuenta47H.add(HABER);
                                        
                                    }
                                    if (cuentaC.trim().equals("63")) {
                                         System.out.println("ingreso  63 ");
                                        cuenta63 = cuenta63.add(SALDO);
                                        cuenta63D = cuenta63D.add(DEBE);
                                        cuenta63H = cuenta63H.add(HABER);
                                        
                                    }
                                    if (cuentaC.trim().equals("64")) {
                                         System.out.println("ingreso  64 ");
                                        cuenta64 = cuenta64.add(SALDO);
                                        cuenta64D = cuenta64D.add(DEBE);
                                        cuenta64H = cuenta64H.add(HABER);
                                        
                                    }
                                    if (cuentaC.trim().equals("65")) {
                                        cuenta65 = cuenta65.add(SALDO);
                                        cuenta65D = cuenta65D.add(DEBE);
                                        cuenta65H = cuenta65H.add(HABER);
                                        
                                    }
                                    if (cuentaC.trim().equals("66")) {
                                        cuenta66 = cuenta66.add(SALDO);
                                        cuenta66D = cuenta66D.add(DEBE);
                                        cuenta66H = cuenta66H.add(HABER);
                                        
                                    }
                                    if (cuentaC.trim().equals("67")) {
                                        cuenta67 = cuenta67.add(SALDO);
                                        cuenta67D = cuenta67D.add(DEBE);
                                        cuenta67H = cuenta67H.add(HABER);
                                        
                                    }
                                    
                                    if (cuentaC.trim().equals("75")) {
                                        cuenta75 = cuenta75.add(SALDO);
                                        cuenta75D = cuenta75D.add(DEBE);
                                        cuenta75H = cuenta75H.add(HABER);
                                        
                                    }
                                    
                                    if (cuentaC.trim().equals("76")) {
                                        cuenta76 = cuenta76.add(SALDO);
                                        cuenta76D = cuenta76D.add(DEBE);
                                        cuenta76H = cuenta76H.add(HABER);
                                        
                                    }
                                    
                                    if (cuentaC.trim().equals("77")) {
                                        cuenta77 = cuenta77.add(SALDO);
                                        cuenta77D = cuenta77D.add(DEBE);
                                        cuenta77H = cuenta77H.add(HABER);
                                        
                                    }
                                }
                                
                                bnCuenta40.setAgrupar(AGRUPAR);
                                bnCuenta40.setCuenta(CUENTA);
                                bnCuenta40.setJrnnumber(Jrnnumber);
                                bnCuenta40.setGlosa(glosa);
                                bnCuenta40.setNombreCuenta(NOMBRE);
                                bnCuenta40.setDebe(DEBE);
                                bnCuenta40.setHaber(HABER);
                                bnCuenta40.setSaldo(SALDO);
                                
                                
                                dataACTIVOFIJO.addBalance(bnCuenta40);
                                
                                
                                
                                
                                
                            }
                        }
                        
                    }
                }
                
                BigDecimal TOTAL1 = cuenta10.add(cuenta12).add(cuenta16).add(cuenta31);
                BigDecimal TOTAL12 = cuenta33.add(cuenta38).add(cuenta40).add(cuenta41).add(cuenta42).add(cuenta46).add(cuenta47);
                BigDecimal TOTAL13 = cuenta63.add(cuenta64).add(cuenta65).add(cuenta66).add(cuenta67).add(cuenta75).add(cuenta76).add(cuenta77);
                BigDecimal TOTAL = TOTAL1.add(TOTAL12).add(TOTAL13);
                System.out.println("SUMA TOTAL: " + TOTAL);
                System.out.println("SUMA cuenta1040405: " + cuenta1040405);
                System.out.println("SUMA resto: " + resto + "  ");
                System.out.println("SUMA cuentaM: " + cuentaM + "  ");
                System.out.println("cuenta 10: " + cuenta10);
                System.out.println("cuenta 12: " + cuenta12);
                System.out.println("cuenta 16: " + cuenta16);
                System.out.println("cuenta 31: " + cuenta31);
                System.out.println("cuenta 33: " + cuenta33);
                System.out.println("cuenta 38: " + cuenta38);
                System.out.println("cuenta 40: " + cuenta40);
                System.out.println("cuenta 41: " + cuenta41);
                System.out.println("cuenta 42: " + cuenta42);
                System.out.println("cuenta 46: " + cuenta46);
                System.out.println("cuenta 47: " + cuenta47);
                System.out.println("cuenta 63: " + cuenta63);
                System.out.println("cuenta 64: " + cuenta64);
                System.out.println("cuenta 65: " + cuenta65);
                System.out.println("cuenta 66: " + cuenta66);
                System.out.println("cuenta 67: " + cuenta67);
                System.out.println("cuenta 76: " + cuenta76);
                System.out.println("cuenta 77: " + cuenta77);
                
                BigDecimal TOTALD = cuenta10D.add(cuenta12D).add(cuenta16D).add(cuenta31D).add(cuenta33D).add(cuenta38D).add(cuenta41D).add(cuenta42D).add(cuenta46D).add(cuenta47D).add(cuenta63D).add(cuenta64D).add(cuenta65D).add(cuenta66D).add(cuenta67D).add(cuenta77D).add(cuenta76D);
                
                System.out.println("SUMA TOTAL DEBE: " + TOTALD);
                System.out.println("cuenta 10D: " + cuenta10D);
                System.out.println("cuenta 12D: " + cuenta12D);
                System.out.println("cuenta 16D: " + cuenta16D);
                System.out.println("cuenta 31D: " + cuenta31D);
                System.out.println("cuenta 33D: " + cuenta33D);
                System.out.println("cuenta 38D: " + cuenta38D);
                System.out.println("cuenta 41D: " + cuenta41D);
                System.out.println("cuenta 42D: " + cuenta42D);
                System.out.println("cuenta 46D: " + cuenta46D);
                System.out.println("cuenta 47D: " + cuenta47D);
                System.out.println("cuenta 63D: " + cuenta63D);
                System.out.println("cuenta 64D: " + cuenta64D);
                System.out.println("cuenta 65D: " + cuenta65D);
                System.out.println("cuenta 66D: " + cuenta66D);
                System.out.println("cuenta 67D: " + cuenta67D);
                System.out.println("cuenta 76D: " + cuenta76D);
                System.out.println("cuenta 77D: " + cuenta77D);
                
                
                BigDecimal TOTALH = cuenta10H.add(cuenta12).add(cuenta16H).add(cuenta31H).add(cuenta33H).add(cuenta38H).add(cuenta41H).add(cuenta42H).add(cuenta46H).add(cuenta47H).add(cuenta63H).add(cuenta64H).add(cuenta65H).add(cuenta66H).add(cuenta67H).add(cuenta77H).add(cuenta76H);
                System.out.println("SUMA TOTAL HABER: " + TOTALH);
                System.out.println("cuenta 10H: " + cuenta10H);
                System.out.println("cuenta 12H: " + cuenta12H);
                System.out.println("cuenta 16H: " + cuenta16H);
                System.out.println("cuenta 31H: " + cuenta31H);
                System.out.println("cuenta 33H: " + cuenta33H);
                System.out.println("cuenta 38H: " + cuenta38H);
                System.out.println("cuenta 41H: " + cuenta41H);
                System.out.println("cuenta 42H: " + cuenta42H);
                System.out.println("cuenta 46H: " + cuenta46H);
                System.out.println("cuenta 47H: " + cuenta47H);
                System.out.println("cuenta 63H: " + cuenta63H);
                System.out.println("cuenta 64H: " + cuenta64H);
                System.out.println("cuenta 65H: " + cuenta65H);
                System.out.println("cuenta 66H: " + cuenta66H);
                System.out.println("cuenta 67H: " + cuenta67H);
                System.out.println("cuenta 76H: " + cuenta76H);
                System.out.println("cuenta 77H: " + cuenta77H);




                //  }

                
                Map parameters = new HashMap();
                parameters.put("P_RAZON", empresa);
                parameters.put("P_RUC", ruc);
                parameters.put("P_PERIODO", ano);
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                
                String rutarptcaja = "/com/contab/report/activoFijo.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataACTIVOFIJO, rutarptcaja);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO ....REPORTE ACTIVO FIJO!!");
                
                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE ACTIVO FIJO ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
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
