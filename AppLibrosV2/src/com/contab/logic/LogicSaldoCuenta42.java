/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanSaldoCuenta42;

import com.contab.dao.Conexion;

import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoSaldoCuenta42;

import com.contab.datasource.DatasourceSaldoCuenta42;

import com.contab.util.Exportar;
import com.contab.view.ViewApplication;

import java.io.File;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRParameter;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author cixtic04
 */
public class LogicSaldoCuenta42 {

    public static void listaSaldoCuenta42(String empresa, String ruc, String compania, String ano, String tipo, File ruta, String tipoExpor) throws SQLException, Exception {

        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            ResultSet rsD = null;
            CallableStatement cstD = null;
            Conexion cnx = null;
            Conexion cnxD = null;
            try {



                /*   ResultSet resultablaCompra = consultaFechaCuenta42(compania.trim(), ano.trim());
                ArrayList<ArrayList<BeanSaldoCuenta42>> repositorioFecha = new ArrayList<ArrayList<BeanSaldoCuenta42>>();
                repositorioFecha = null;
                // repositorioTabla6 = null;
                if (resultablaCompra != null) {
                repositorioFecha = repositorioCompra(resultablaCompra);
                }*/

                DatasourceSaldoCuenta42 dataSaldoCuenta42 = new DatasourceSaldoCuenta42();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                String fechax = "31-12-" + ano.toString();
                param.add(ano); //ano
                param.add(fechax); //ano

                ArrayList objetos = new ArrayList();
                objetos = DaoSaldoCuenta42.consultaSaldoCuenta42(param);
                rs = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                cnx = (Conexion) objetos.get(2);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO SALDO DE CUENTA 42 Nª: " + empresa + " DEL PERIODO: " + ano);

                BeanSaldoCuenta42 compraConstancia = new BeanSaldoCuenta42();
                if (rs != null && cst != null && cnx != null) {
                    while (rs.next()) {

                        BeanSaldoCuenta42 bnSaldoCuenta42 = new BeanSaldoCuenta42();
                        ViewApplication.mensajeEstado.setText("PROCESANDO SALDO DE CUENTA  42 Nª: " + empresa + " DEL PERIODO: " + ano + " numero" + rs.getString(4));


                        String cuenta = rs.getString(11);
                        String centro = rs.getString(12);

                        String documento = rs.getString(13);
                        String denominacion = rs.getString(8);
                        String preImpresion = rs.getString(5);
                        String serie = rs.getString(4);
                        String codPro = rs.getString(7);
                        String tipodoc = rs.getString(2);
                        BigDecimal monto = rs.getBigDecimal(10);
                        String fecha = rs.getString(6);
                        // documento=rs.getString(22);
                        String numDoc = "";
                        String tabla2 = "";
                        //  System.out.println("------------" + rs.getString(4));
                        if (preImpresion == null) {
                            preImpresion = " ";

                        }
                        if (serie == null) {
                            serie = " ";

                        }
                        if (codPro == null) {
                            codPro = " ";

                        }
                        //803434794  ,    785909804

                        /* if (repositorioFecha != null) {
                        compraConstancia = buscarRepositorioFecha(repositorioFecha, preImpresion.trim(), serie.trim(), codPro.trim());
                        
                        }*/

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

                        bnSaldoCuenta42.setTabla2(tabla2);
                        bnSaldoCuenta42.setSerie(serie);
                        bnSaldoCuenta42.setCuenta(cuenta);
                        bnSaldoCuenta42.setCentro(centro);
                        bnSaldoCuenta42.setPreimpreso(preImpresion);
                        bnSaldoCuenta42.setNumero(documento);
                        bnSaldoCuenta42.setTipoDoc(tipodoc);
                        bnSaldoCuenta42.setDenominacion(denominacion);
                        bnSaldoCuenta42.setMonto(monto);
                        bnSaldoCuenta42.setFecha(fecha);

                        dataSaldoCuenta42.addBalance(bnSaldoCuenta42);

                    }
                }



                Map parameters = new HashMap();
                parameters.put("P_RAZON", empresa);
                parameters.put("P_RUC", ruc);
                parameters.put("P_PERIODO", ano);
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutarptcaja = "";
                if (tipo.equals("S")) {
                    rutarptcaja = "/com/contab/report/DETALLESALDOCUENTA42.jasper";

                } else {
                    rutarptcaja = "/com/contab/report/DETALLESALDOCUENTA42N.jasper";
                }

                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataSaldoCuenta42, rutarptcaja);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE DEL SALDO DE CUENTA 42!!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE DEL SALDO DE CUENTA 42 ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
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

    public static void listaSaldoCuenta42X(String empresa, String ruc, String compania, String ano, String tipo, File ruta, String tipoExpor) throws SQLException, Exception {

        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            ResultSet rsD = null;
            CallableStatement cstD = null;
            Conexion cnx = null;
            Conexion cnxD = null;
            try {


                DatasourceSaldoCuenta42 dataSaldoCuenta42 = new DatasourceSaldoCuenta42();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                String fechax = "31-12-" + ano.toString();
                param.add(ano); //ano
                param.add(fechax); //ano
                ArrayList objetos = new ArrayList();
                objetos = DaoSaldoCuenta42.consultaSaldoCuenta42x(param);
                rs = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                cnx = (Conexion) objetos.get(2);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO SALDO DE CUENTA 42 Nª: " + empresa + " DEL PERIODO: " + ano);

                //  BeanSaldoCuenta42 compraConstancia = new BeanSaldoCuenta42();
                if (rs != null && cst != null && cnx != null) {
                    while (rs.next()) {

                        BeanSaldoCuenta42 bnSaldoCuenta42 = new BeanSaldoCuenta42();
                        ViewApplication.mensajeEstado.setText("PROCESANDO SALDO DE CUENTA  42 Nª: " + empresa + " DEL PERIODO: " + ano + " numero" + rs.getString(4));

                        String serie = rs.getString(4);
                        String preImpresion = rs.getString(5);
                        String denominacion = rs.getString(8);
                        String codPro = rs.getString(7);
                        BigDecimal monto = rs.getBigDecimal(12);
                        String fecha = rs.getString(6);
                        String cuenta = rs.getString(13);
                        String centro = rs.getString(14);
                        String documento = rs.getString(15);
                        String tipodoc = rs.getString(2);
                        // documento=rs.getString(22);
                        String numDoc = "";
                        String tabla2 = "";
                        //  System.out.println("------------" + rs.getString(4));
                        if (preImpresion == null) {
                            preImpresion = " ";

                        }
                        if (serie == null) {
                            serie = " ";

                        }
                        if (codPro == null) {
                            codPro = " ";

                        }
                        //803434794  ,    785909804



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

                        bnSaldoCuenta42.setTabla2(tabla2);
                        bnSaldoCuenta42.setSerie(serie);
                        bnSaldoCuenta42.setCuenta(cuenta);
                        bnSaldoCuenta42.setCentro(centro);
                        bnSaldoCuenta42.setTipoDoc(tipodoc);
                        bnSaldoCuenta42.setPreimpreso(preImpresion);
                        bnSaldoCuenta42.setNumero(documento);
                        bnSaldoCuenta42.setDenominacion(denominacion);
                        bnSaldoCuenta42.setMonto(monto);
                        bnSaldoCuenta42.setFecha(fecha);

                        dataSaldoCuenta42.addBalance(bnSaldoCuenta42);

                    }
                }



                Map parameters = new HashMap();
                parameters.put("P_RAZON", empresa);
                parameters.put("P_RUC", ruc);
                parameters.put("P_PERIODO", ano);
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutarptcaja = "";
                if (tipo.equals("S")) {
                    rutarptcaja = "/com/contab/report/DETALLESALDOCUENTA42.jasper";

                } else {
                    rutarptcaja = "/com/contab/report/DETALLESALDOCUENTA42N.jasper";
                }

                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataSaldoCuenta42, rutarptcaja);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE DEL SALDO DE CUENTA 42!!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE DEL SALDO DE CUENTA 42 ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
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

    public static ResultSet consultaFechaCuenta42(String compania, String anno) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(compania); //numero de la empresa o compa;ia
            param.add(anno); //ano
            ArrayList objetos = DaoSaldoCuenta42.consultaFechaEmision(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                return rs;
            }
            cst.close();
            // DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
        } finally {
            //  cst.close();
            DaoEmpresas.objCnx.destroy();
        }
        return rs;
    }

    public static ArrayList<ArrayList<BeanSaldoCuenta42>> repositorioCompra(ResultSet rs) {
        ArrayList<ArrayList<BeanSaldoCuenta42>> repositorio = new ArrayList<ArrayList<BeanSaldoCuenta42>>();
        int val = 30000;
        ArrayList<BeanSaldoCuenta42> array;
        int contador = 0;
        int cont = 2;
        try {
            if (rs != null) {
                //   System.out.println("repositorio sunat tabla 6");
                array = new ArrayList<BeanSaldoCuenta42>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        System.out.println("repositorio  fecha cuenta 42 " + cont);
                        array = new ArrayList<BeanSaldoCuenta42>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanSaldoCuenta42 bean = new BeanSaldoCuenta42();
                    bean.setPreimpreso(rs.getString(1));
                    bean.setSerie(rs.getString(2));
                    bean.setCodProd(rs.getString(3));
                    bean.setFecha(rs.getString(4));


                    array.add(bean);
                }
                repositorio.add(array);
                rs.close();
                DaoEmpresas.objCnx.destroy();
            }
        } catch (Exception ex) {
        }
        return repositorio;
    }

    public static BeanSaldoCuenta42 buscarRepositorioFecha(ArrayList<ArrayList<BeanSaldoCuenta42>> repor, String preImpreso, String serie, String codPro) {
        BeanSaldoCuenta42 bean = new BeanSaldoCuenta42();
        BeanSaldoCuenta42 beanaux = new BeanSaldoCuenta42();
        ArrayList<BeanSaldoCuenta42> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanSaldoCuenta42>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanSaldoCuenta42) iterar.next();

                if (beanaux.getPreimpreso().equals(preImpreso) && beanaux.getSerie().equals(serie) && beanaux.getCodProd().equals(codPro)) {


                    bean = beanaux;
                    break;
                }
            }
            if (bean == null) {

                bean.setFecha(" ");
            }
        }
        return bean;
    }
}
