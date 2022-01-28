/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanSaldoDeCuenta;
import com.contab.dao.Conexion;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoSaldoDeCuenta;
import com.contab.datasource.DatasourceSaldoDeCuenta;
import com.contab.util.ClassCuentaSunat;
import com.contab.util.Exportar;
import com.contab.view.ViewApplication;
import java.io.File;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
public class LogicSaldoDeCuenta {

    public static void listaSaldoDeCuenta(String empresa, String ruc, String compania, String anno, String tipo, File ruta, String tipoExpor) throws SQLException, Exception {

        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            Conexion cnx = null;
            try {
                /*  ResultSet resultablaCompra = consultaFechaCuenta12(compania.trim(), anno.trim());
                ArrayList<ArrayList<BeanSaldoDeCuenta>> repositorioFecha = new ArrayList<ArrayList<BeanSaldoDeCuenta>>();
                repositorioFecha = null;
                // repositorioTabla6 = null;
                if (resultablaCompra != null) {
                repositorioFecha = repositorioCompra(resultablaCompra);
                }*/

                DatasourceSaldoDeCuenta dataSaldoDeCuenta = new DatasourceSaldoDeCuenta();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                String fechax = "31-12-" + anno.toString();
                param.add(anno); //ano
                param.add(fechax); //ano

                ArrayList objetos = new ArrayList();
                objetos = DaoSaldoDeCuenta.SALDOCUENTA12(param);
                rs = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                cnx = (Conexion) objetos.get(2);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO SALDO DE CUENTA Nª: " + empresa + " DEL PERIODO: ");
                // BeanSaldoDeCuenta compraConstancia = new BeanSaldoDeCuenta();
                //  BeanSaldoDeCuenta obtenerFecha = new BeanSaldoDeCuenta();

                if (rs != null && cst != null && cnx != null) {
                    while (rs.next()) {

                        BeanSaldoDeCuenta bnSaldoDeCuenta = new BeanSaldoDeCuenta();
                        ViewApplication.mensajeEstado.setText("PROCESANDO SALDO DE CUENTA 12 Nª: " + empresa + " DEL PERIODO: " + " numero" + rs.getString(5));

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

                        String numDoc = "";



                        if (documento != null) {
                            if(documento.length()>3){
                            if (documento.substring(0, 3).equals("DNI")) {
                                numDoc = "01";
                                documento = documento.substring(3, documento.length());
                            } else if (documento.substring(0, 3).equals("ABC")) {

                                numDoc = "00";
                            } else if (documento.length() == 11) {
                                numDoc = "06";

                            } else {
                                numDoc = "00";
                            }
                            }
                             numDoc = "00";
                        }
                        //  obtenerFecha = buscarFechaEmisionPreImpreso(compania,anno, codRef);

                        bnSaldoDeCuenta.setCuenta(cuenta);
                        bnSaldoDeCuenta.setCentro(centro);
                        bnSaldoDeCuenta.setSerie(serie);
                        bnSaldoDeCuenta.setTipoDoc(tipodoc);
                        bnSaldoDeCuenta.setTabla(numDoc);
                        bnSaldoDeCuenta.setNumero(documento);
                        bnSaldoDeCuenta.setDenominacion(denominacion);
                        bnSaldoDeCuenta.setMonto(monto);
                        bnSaldoDeCuenta.setPreimpreso(preImpresion);
                        bnSaldoDeCuenta.setFechaEmision(fecha);
                        // bnSaldoDeCuenta.setFechaEmision(obtenerFecha.getFechaEmision());

                        dataSaldoDeCuenta.addBalance(bnSaldoDeCuenta);

                    }
                    Map parameters = new HashMap();
                    parameters.put("P_RAZON", empresa);
                    parameters.put("P_RUC", ruc);
                    parameters.put("P_PERIODO", anno);
                    parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                    String rutarptcaja = "";
                    if (tipo.equals("S")) {
                        rutarptcaja = "/com/contab/report/detalleSaldoCliente.jasper";

                    } else {
                        rutarptcaja = "/com/contab/report/detalleSaldoClienteN.jasper";
                    }

                    Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataSaldoDeCuenta, rutarptcaja);
                    ViewApplication.mensajeEstado.setText(null);
                    ViewApplication.mensajeEstado.setText("REPORTE DEL SALDO DE CUENTA!!");

                    ex.show();

                } else {

                    JOptionPane.showMessageDialog(null, "NO HAY DATOS  ", "ALERT", JOptionPane.INFORMATION_MESSAGE);

                }
                //  }



                JOptionPane.showMessageDialog(null, "REPORTE DEL SALDO DE CUENTA ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
                rs.close();
                cst.close();
                cnx.destroy();

            } catch (SQLException ex) {
                System.out.println(ex.getErrorCode());
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

    public static ResultSet consultaFechaCuenta12(String compania, String anno) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(compania); //numero de la empresa o compa;ia
            param.add(anno); //ano
            ArrayList objetos = DaoSaldoDeCuenta.consultaFechaEmision(param);
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

    public static ArrayList<ArrayList<BeanSaldoDeCuenta>> repositorioCompra(ResultSet rs) {
        ArrayList<ArrayList<BeanSaldoDeCuenta>> repositorio = new ArrayList<ArrayList<BeanSaldoDeCuenta>>();
        int val = 30000;
        ArrayList<BeanSaldoDeCuenta> array;
        int contador = 0;
        int cont = 2;
        try {
            if (rs != null) {
                //   System.out.println("repositorio sunat tabla 6");
                array = new ArrayList<BeanSaldoDeCuenta>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        System.out.println("repositorio  fecha cuenta 12 " + cont);
                        array = new ArrayList<BeanSaldoDeCuenta>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanSaldoDeCuenta bean = new BeanSaldoDeCuenta();
                    bean.setSerie(rs.getString(1));
                    bean.setPreimpreso(rs.getString(2));
                    bean.setCodCliente(rs.getString(3));
                    bean.setFechaEmision(rs.getString(4));


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

    public static BeanSaldoDeCuenta buscarRepositorioFecha(ArrayList<ArrayList<BeanSaldoDeCuenta>> repor, String preImpreso, String serie, String codCiente) {
        BeanSaldoDeCuenta bean = new BeanSaldoDeCuenta();
        BeanSaldoDeCuenta beanaux = new BeanSaldoDeCuenta();
        ArrayList<BeanSaldoDeCuenta> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanSaldoDeCuenta>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanSaldoDeCuenta) iterar.next();

                if (beanaux.getPreimpreso().equals(preImpreso) && beanaux.getSerie().equals(serie) && beanaux.getCodCliente().equals(codCiente)) {
                    //  array.remove(beanaux);

                    bean = beanaux;
                    break;
                }
            }
            if (bean == null) {

                bean.setFechaEmision(" ");
            }
        }
        return bean;
    }

    public static BeanSaldoDeCuenta buscarFechaEmision(String compania, String preImpreso, String serie, String codCiente) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        BeanSaldoDeCuenta bean = new BeanSaldoDeCuenta();
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(compania); //numero de la empresa o compa;ia
            param.add(preImpreso); //numero de la empresa o compa;ia
            param.add(preImpreso); //numero de la empresa o compa;ia
            param.add(serie); //ano
            param.add(codCiente); //ano
            ArrayList objetos = DaoSaldoDeCuenta.consultaFechaEmision2(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);


            if (rs != null && cst != null) {
                while (rs.next()) {

                    bean.setSerie(rs.getString(1));
                    bean.setPreimpreso(rs.getString(2));
                    bean.setCodCliente(rs.getString(3));
                    bean.setFechaEmision(rs.getString(4));

                }
            }
            cst.close();
            // DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
        } finally {
            //  cst.close();
            DaoEmpresas.objCnx.destroy();
        }
        return bean;
    }

    public static BeanSaldoDeCuenta buscarFechaEmisionPreImpreso(String compania, String anno, String codRef) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        BeanSaldoDeCuenta bean = new BeanSaldoDeCuenta();
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(compania); //numero de la empresa o compa;ia
            param.add(anno); //numero de la empresa o compa;ia
            param.add(codRef); //numero de la empresa o compa;ia
            //  param.add(serie); //ano
            //  param.add(codCiente); //ano
            ArrayList objetos = DaoSaldoDeCuenta.consultaFechaEmision3(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);


            if (rs != null && cst != null) {
                while (rs.next()) {


                    bean.setPreimpreso(rs.getString(5));
                    bean.setSerie(rs.getString(6));
                    bean.setCodCliente(rs.getString(7));
                    bean.setFechaEmision(rs.getString(12));

                }
            }
            cst.close();
            // DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
        } finally {
            //  cst.close();
            DaoEmpresas.objCnx.destroy();
        }
        return bean;
    }
}
