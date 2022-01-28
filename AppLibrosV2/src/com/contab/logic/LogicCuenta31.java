/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanCuenta31;
import com.contab.bean.BeanCuenta31;
import com.contab.dao.Conexion;
import com.contab.dao.DaoCuenta31;
import com.contab.dao.DaoEmpresas;

import com.contab.dao.DaoTabla03;
import com.contab.datasource.DatasourceCuenta31;
import com.contab.util.Exportar;
import com.contab.view.ViewApplication;
import com.contab.view.ViewCuenta31;
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
public class LogicCuenta31 {

    public static ViewCuenta31 frmCuenta31;

    public static ArrayList<BeanCuenta31> listaCuenta31() {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList<BeanCuenta31> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoCuenta31.CONSULTACuenta31(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanCuenta31 obj = new BeanCuenta31();
                    obj.setIdCuenta31(rs.getInt(1));
                    obj.setIdCompania(rs.getString(2));
                    obj.setDenominacionrazon(rs.getString(3));
                    obj.setDocumento(rs.getBigDecimal(4));
                    obj.setDenominacion(rs.getString(5));
                    obj.setValorNominal(rs.getBigDecimal(6));
                    obj.setCantidad(rs.getInt(7));
                    obj.setCostoTotal(rs.getBigDecimal(8));
                    obj.setProvisionTotal(rs.getBigDecimal(9));
                    obj.setTotalNeto(rs.getBigDecimal(10));
                    obj.setAnno(rs.getString(11));


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

    public static void mantenimientoCuenta31(HashSet<BeanCuenta31> Cuenta31) throws SQLException {
        Iterator i = Cuenta31.iterator();
        BeanCuenta31 bean;
        int contarEliminados = 0;
        int contarInsertados = 0;
        int contarActualizados = 0;
        ArrayList param = new ArrayList();
        ArrayList objetos;
        CallableStatement cst = null;
        BigDecimal estado = null;
        while (i.hasNext()) {
            bean = (BeanCuenta31) i.next();
            if (bean.getOperacion().equals("e")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdCuenta31());
                param.add(bean.getIdCompania());
                param.add(bean.getDenominacionrazon());
                param.add(bean.getDocumento());
                param.add(bean.getDenominacion());
                param.add(bean.getValorNominal());
                param.add(bean.getCantidad());
                param.add(bean.getCostoTotal());
                param.add(bean.getProvisionTotal());
                param.add(bean.getTotalNeto());
                param.add(bean.getAnno());
                param.add(3);
                objetos = DaoCuenta31.MantemientoCuenta31(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                    contarEliminados = contarEliminados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("a")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdCuenta31());
                param.add(bean.getIdCompania());
                param.add(bean.getDenominacionrazon());
                param.add(bean.getDocumento());
                param.add(bean.getDenominacion());
                param.add(bean.getValorNominal());
                param.add(bean.getCantidad());
                param.add(bean.getCostoTotal());
                param.add(bean.getProvisionTotal());
                param.add(bean.getTotalNeto());
                param.add(bean.getAnno());
                param.add(2);
                objetos = DaoCuenta31.MantemientoCuenta31(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                    contarActualizados = contarActualizados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("i")) {
                param.add(OracleTypes.NUMBER);
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIdCompania());
                param.add(bean.getDenominacionrazon());
                param.add(bean.getDocumento());
                param.add(bean.getDenominacion());
                param.add(bean.getValorNominal());
                param.add(bean.getCantidad());
                param.add(bean.getCostoTotal());
                param.add(bean.getProvisionTotal());
                param.add(bean.getTotalNeto());
                param.add(bean.getAnno());
                param.add(1);
                objetos = DaoCuenta31.MantemientoCuenta31(param);
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

    public static void listaSaldoCuenta31(String empresa, String ruc, String compania, String ano, File ruta, String tipoExpor) throws SQLException, Exception {


        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            Conexion cnx = null;
            try {
                frmCuenta31 = new ViewCuenta31();
                DatasourceCuenta31 dataCuenta31 = new DatasourceCuenta31();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                param.add(ano); //ano
                ArrayList objetos = new ArrayList();
                objetos = DaoCuenta31.reporteCuenta31(param);
                rs = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);

                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO  CUENTA 31 Nª: " + empresa + " DEL PERIODO: " + ano);

                BigDecimal saldoCapital = BigDecimal.ZERO;
                int cantidadAcciones = 0;
                int numeroAcciones = 0;
                if (rs != null && cst != null) {
                    while (rs.next()) {

                        BeanCuenta31 BeanCuenta31 = new BeanCuenta31();
                        ViewApplication.mensajeEstado.setText("PROCESANDO  CUENTA  31 Nª: " + empresa + " DEL PERIODO: " + ano + " numero" + rs.getString(1));

                        String tabla12 = rs.getString(4);
                        String idTabla12 = "";
                        if (tabla12.length() == 11) {
                            idTabla12 = "06";
                        } else {
                            idTabla12 = "01";
                        }

                        BeanCuenta31.setTabla2(idTabla12);
                        BeanCuenta31.setDenominacionrazon(rs.getString(3));
                        BeanCuenta31.setDocumento(rs.getBigDecimal(4));
                        BeanCuenta31.setDenominacion(rs.getString(5));
                        BeanCuenta31.setValorNominal(rs.getBigDecimal(6));
                        BeanCuenta31.setCantidad(rs.getInt(7));
                        BeanCuenta31.setCostoTotal(rs.getBigDecimal(8));
                        BeanCuenta31.setProvisionTotal(rs.getBigDecimal(9));
                        BeanCuenta31.setTotalNeto(rs.getBigDecimal(10));
                        dataCuenta31.addBalance(BeanCuenta31);


                    }
                }

                //  } txtValorMoninal  txtNumeroAccionesS txtNumeroAccionesP


                Map parameters = new HashMap();
                parameters.put("EJERCICIO", ano);
                parameters.put("P_RUC", ruc);
                parameters.put("P_RAZON", empresa);
                parameters.put("ANNO", ano);
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);


                String rutarptcaja = "/com/contab/report/cuenta31.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataCuenta31, rutarptcaja, "Caja");
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE CUENTA 31!!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE CUENTA 31", "ALERT", JOptionPane.INFORMATION_MESSAGE);
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
