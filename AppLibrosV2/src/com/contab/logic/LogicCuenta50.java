/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanCuenta50;
import com.contab.dao.Conexion;
import com.contab.dao.DaoCuenta50;
import com.contab.dao.DaoEmpresas;

import com.contab.datasource.DatasourceCuenta50;
import com.contab.util.Exportar;
import com.contab.view.ViewApplication;
import com.contab.view.ViewCuenta50;
import java.io.File;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRParameter;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author 777
 */
public class LogicCuenta50 {

    public static ViewCuenta50 frmCuenta50;

    public static ArrayList<BeanCuenta50> listaCuenta50() {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList<BeanCuenta50> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoCuenta50.CONSULTACUENTA50(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanCuenta50 obj = new BeanCuenta50();
                    obj.setIdCuenta50(rs.getInt(1));
                    obj.setCompania(rs.getString(2));
                    obj.setDenominacion(rs.getString(3));
                    obj.setDocumento(rs.getBigDecimal(4));
                    obj.setTipoAccion(rs.getString(5));
                    obj.setNumeroAccion(rs.getInt(6));//
                    obj.setCapital(rs.getBigDecimal(7));
                    obj.setAnno(rs.getString(8));

                    lista.add(obj);
                }
            }
            return lista;
        } catch (SQLException ex) {

            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cst != null) {
                    cst.close();
                }
                if (!DaoEmpresas.objCnx.getCnx().isClosed()) {
                    DaoEmpresas.objCnx.destroy();
                }
            } catch (Exception ex) {
            }
        }

    }

    public static void mantenimientoCuenta50(HashSet<BeanCuenta50> Cuenta50) throws SQLException {
        Iterator i = Cuenta50.iterator();
        BeanCuenta50 bean;
        int contarEliminados = 0;
        int contarInsertados = 0;
        int contarActualizados = 0;
        ArrayList param = new ArrayList();
        ArrayList objetos;
        CallableStatement cst = null;
        BigDecimal estado = null;
        while (i.hasNext()) {
            bean = (BeanCuenta50) i.next();
            if (bean.getOperacion().equals("e")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdCuenta50());
                param.add(bean.getCompania());
                param.add(bean.getDenominacion());
                param.add(bean.getDocumento());
                param.add(bean.getTipoAccion());
                param.add(bean.getNumeroAccion());
                param.add(bean.getCapital());
                param.add(bean.getAnno());
                param.add(3);
                objetos = DaoCuenta50.MantemientoCuenta50(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                    contarEliminados = contarEliminados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("a")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdCuenta50());
                param.add(bean.getCompania());
                param.add(bean.getDenominacion());
                param.add(bean.getDocumento());
                param.add(bean.getTipoAccion());
                param.add(bean.getNumeroAccion());
                param.add(bean.getCapital());
                param.add(bean.getAnno());
                param.add(2);
                objetos = DaoCuenta50.MantemientoCuenta50(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                    contarActualizados = contarActualizados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("i")) {
                param.add(OracleTypes.NUMBER);
                param.add(OracleTypes.NUMBER);
                param.add(bean.getCompania());
                param.add(bean.getDenominacion());
                param.add(bean.getDocumento());
                param.add(bean.getTipoAccion());
                param.add(bean.getNumeroAccion());
                param.add(bean.getCapital());
                param.add(bean.getAnno());
                param.add(1);
                objetos = DaoCuenta50.MantemientoCuenta50(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(1)) == 0) {
                    contarInsertados = contarInsertados + 1;
                    param.clear();
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Se han insert"
                + "ado: " + contarInsertados + " registros \n"
                + "Se han actualizado: " + contarActualizados + " registros \n"
                + "Se han eliminado: " + contarEliminados + " registros", "Informe de Operación", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void listaSaldoCuenta50(String empresa, String ruc, String compania, String ano, String txtValorMoninal, String txtNumeroAccionesS, String txtNumeroAccionesP, File ruta, String tipoExpor) throws SQLException, Exception {


        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            Conexion cnx = null;
            try {
                frmCuenta50 = new ViewCuenta50();


                DatasourceCuenta50 dataCuenta50 = new DatasourceCuenta50();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                param.add(ano); //ano
                ArrayList objetos = new ArrayList();
                objetos = DaoCuenta50.reporteCuenta50(param);
                rs = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);

                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO  CUENTA 50 Nª: " + empresa + " DEL PERIODO: " + ano);

                BigDecimal saldoCapital = BigDecimal.ZERO;
                int cantidadAcciones = 0;
                int numeroAcciones = 0;
                if (rs != null && cst != null) {
                    while (rs.next()) {

                        BeanCuenta50 BeanCuenta50 = new BeanCuenta50();
                        ViewApplication.mensajeEstado.setText("PROCESANDO  CUENTA  50 Nª: " + empresa + " DEL PERIODO: " + ano + " numero" + rs.getString(1));

                        String tabla12 = rs.getString(4);
                        String idTabla12 = "";
                        if (tabla12.length() == 11) {
                            idTabla12 = "06";
                        } else {
                            idTabla12 = "01";
                        }
                        String denominacion = rs.getString(3);
                        BigDecimal capital = rs.getBigDecimal(7);

                        saldoCapital = saldoCapital.add(capital);
                        numeroAcciones = numeroAcciones + 1;

                        cantidadAcciones = cantidadAcciones + rs.getInt(6);
                        BeanCuenta50.setTabla2(idTabla12);
                        BeanCuenta50.setDocumento(rs.getBigDecimal(4));
                        BeanCuenta50.setDenominacion(denominacion);
                        BeanCuenta50.setTipoAccion(rs.getString(5));
                        BeanCuenta50.setNumeroAccion(rs.getInt(6));

                        dataCuenta50.addBalance(BeanCuenta50);





                    }
                }

                //  } txtValorMoninal  txtNumeroAccionesS txtNumeroAccionesP

                System.out.println(txtValorMoninal.toString() + "dsd" + txtNumeroAccionesS);
                Map parameters = new HashMap();
                parameters.put("EJERCICIO", ano);
                parameters.put("P_RUC", ruc);
                parameters.put("P_RAZON", empresa);
                parameters.put("ANNO", ano);
                parameters.put("capital", saldoCapital);
                parameters.put("valorMoninal", txtValorMoninal.toString());
                parameters.put("numeroAccionesS", txtNumeroAccionesS.toString());
                parameters.put("numeroAccionesP", txtNumeroAccionesP.toString());
                parameters.put("numeroAccionesSocial", numeroAcciones);
                parameters.put("numeroAcciones", cantidadAcciones);
                  parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptcaja = "/com/contab/report/cuenta50.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataCuenta50, rutarptcaja, "Caja");
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE CUENTA 50!!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE CUENTA 50", "ALERT", JOptionPane.INFORMATION_MESSAGE);
                rs.close();
                cst.close();
                //cnx.destroy();

            } catch (SQLException ex) {
                System.out.print(ex.getErrorCode() + "  " + ex.getNextException() + "" + ex.getMessage());
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
