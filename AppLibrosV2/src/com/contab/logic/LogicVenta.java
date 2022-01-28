/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanVenta;
import com.contab.bean.BeanTabla6;
import com.contab.dao.Conexion;
import com.contab.dao.DaoCompra;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoVenta;
import com.contab.datasource.DataSourceVenta;
import com.contab.util.ClassFecha;

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
public class LogicVenta {

    public static void listaSaldoVenta(String empresa, String ruc, String compania, String ano, String mes, File ruta, String tipoExpor) throws SQLException, Exception {

        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            Conexion cnx = null;
            try {
                int anno = 0;
                int mmes = Integer.parseInt(mes);
                String numCadena = "";
                int mesS = 1;

                if (mmes == 12) {
                    numCadena = "01";
                    mesS = 1;
                    anno = Integer.parseInt(ano) + 1;

                } else {
                    anno = Integer.parseInt(ano);
                    mesS = mmes + 1;
                    numCadena = Integer.toString(mesS);

                    if (numCadena.length() == 1) {
                        numCadena = "0" + numCadena;
                    }

                }



                ClassFecha dia = new ClassFecha();

                String dias = dia.Dia(mesS, anno);

                String fechaI = "01/" + mes + "/" + ano;
                String fechaF = dias + "/" + numCadena + "/" + Integer.toString(anno);

                System.out.println(fechaI + "--" + fechaF);

                ResultSet resultabla6 = ListarUnidadArticulos();
                ArrayList<ArrayList<BeanTabla6>> repositorioTabla6 = new ArrayList<ArrayList<BeanTabla6>>();
                repositorioTabla6 = null;
                // repositorioTabla6 = null;
                if (resultabla6 != null) {
                    repositorioTabla6 = repositorio(resultabla6);
                }

                /* ResultSet resultablaCompra = ListarCompraConstancia(compania, fechaI, fechaF);
                ArrayList<ArrayList<BeanVenta>> repositorioVenta = new ArrayList<ArrayList<BeanVenta>>();
                repositorioVenta = null;
                // repositorioTabla6 = null;
                if (resultablaCompra != null) {
                repositorioVenta = repositorioVenta(resultablaCompra);
                }*/

                DataSourceVenta dataVenta = new DataSourceVenta();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                param.add(ano); //ano
                param.add(mes); //ano
                ArrayList objetos = new ArrayList();
                objetos = DaoVenta.consultaVenta(param);
                rs = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                cnx = (Conexion) objetos.get(2);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO  COMPRA Nª: " + empresa + " DEL PERIODO: " + ano);

                String Serie = "";
                String Numero = "";
                String preImpreso = "";
                String apellinombre = "";
                String ruc1 = "";
                String tc = "";
                String tabla10 = "";
                String tabla2 = "";
                String valorR = " ";
                String valorN = "";
                // BeanVenta compraConstancia = new BeanVenta();
                //BeanVenta BeanVentaF;
                if (rs != null && cst != null && cnx != null) {
                    while (rs.next()) {

                        BeanVenta bnVenta = new BeanVenta();
                        String FECHAVENCIMEINTO = rs.getString(3);
                        String tipoDocumento = rs.getString(4);
                        BeanTabla6 tabla = new BeanTabla6();

                        System.out.println(rs.getString(1) + "--------error-----");
                        if (tipoDocumento == null) {
                            tipoDocumento = "";

                        }
                        if (FECHAVENCIMEINTO == null) {
                            FECHAVENCIMEINTO = "";

                        }
                        tabla = buscarTabla862(repositorioTabla6, tipoDocumento);

                        /// Compra constancia


                        // String Jrnnumber = rs.getString(6);
                        //String PROVEEDORDCP = rs.getString(8);
                        // String SECUENCIADCP = rs.getString(22);


                        /*    if (SECUENCIADCP == null) {
                        SECUENCIADCP = " ";
                        }
                        
                        if (PROVEEDORDCP == null) {
                        PROVEEDORDCP = " ";
                        }*/


                        valorN = rs.getString(1);

                        //   110106000502
                        if (valorN != null) {
                            System.out.println(valorN + "error" + "yyyy");
                            if (valorN.trim().equals("E")) {
                                valorR = "ANULADA";
                                System.out.println(valorN + "ANULADA");
                            } else {
                                if (valorN.length() > 6) {
                                    valorR = valorN.substring(4, 6) + "-" + valorN.substring(6, valorN.length());
                                }
                            }

                        }

                        ///System.out.println(rs.getString(7)+"--"+valorR);
                        //  System.out.println("provvedor " + PROVEEDORDCP + "Secuencai " + SECUENCIADCP);

                        // if (repositorioVenta != null) {
                        //   compraConstancia = buscarrepositorioVenta(repositorioVenta, PROVEEDORDCP, SECUENCIADCP);

                        // }

                        //


                        ViewApplication.mensajeEstado.setText("PROCESANDO  VENTA Nª: " + empresa + " DEL PERIODO: " + ano + " numero" + rs.getString(1));


                        //  Numero = rs.getString(4);
                        tabla10 = rs.getString(4);
                        Serie = rs.getString(5).trim();
                        preImpreso = rs.getString(6);
                        apellinombre = rs.getString(9);
                        ruc1 = rs.getString(8);
                        // tc = rs.getString(16);

                        if (ruc1 != null) {
                            if (!ruc1.equals("")) {
                                System.out.println(ruc1 + "ingresao");
                                if (ruc1.length() >= 8) {
                                    if (ruc1.length() == 8) {

                                        tabla2 = "01";
                                    } else {
                                        tabla2 = "06";
                                    }
                                }
                            }
                        } else {
                            ruc1 = " ";
                            tabla2 = " ";
                        }
                        String Numero1 = rs.getString(6);
                        //  String preImpreso = rs.getString(6);
                        String FechaEmision = rs.getString(2);

                        bnVenta.setNumero(valorR);
                        bnVenta.setFechav(FECHAVENCIMEINTO);
                        bnVenta.setNombre(tabla.getIdUnidadArticulo());
                        bnVenta.setSerie(Serie);
                        //  bnVenta.setNumero(Numero);
                        bnVenta.setTabla10(tabla10);
                        bnVenta.setTabla2(tabla2);
                        bnVenta.setPreimpreso(preImpreso);
                        bnVenta.setApellDenominacion(apellinombre);
                        bnVenta.setRucDNI(ruc1);
                        //   bnVenta.setTc(tc);
                        bnVenta.setNumero1(preImpreso);
                        bnVenta.setFechaEmision(FechaEmision);
                        //String secuenciaX = rs.getString(22);
                        //String CodProv = rs.getString(8);

                        String TablaR = rs.getString(22);
                        if (TablaR == null) {
                            TablaR = "";
                        }
                        String FechaR = rs.getString(21);
                        if (FechaR == null) {
                            FechaR = "";
                        }
                        String SerieR = rs.getString(23);
                        if (SerieR == null) {
                            SerieR = "";
                        }
                        String ComprobanteR = rs.getString(24);
                        if (ComprobanteR == null) {
                            ComprobanteR = "";
                        }


                        // if (monto.compareTo(BigDecimal.ZERO) != 0) {
                        bnVenta.setTipoDocumento(tipoDocumento);
                        System.out.println(tipoDocumento + "--tipo de docuemnto");
                        bnVenta.setFecha(rs.getString(2));

                        bnVenta.setTc(rs.getString(20));


                        bnVenta.setTabla101(TablaR);
                        bnVenta.setFechaR(FechaR);
                        bnVenta.setSerie1(SerieR);
                        bnVenta.setComprobante(ComprobanteR);

                        if (tipoDocumento.trim().equals("05")) {
                            /*  ArrayList paramCa = new ArrayList();
                            paramCa.add(OracleTypes.CURSOR);
                            paramCa.add(compania);
                            paramCa.add(CodProv);
                            paramCa.add(secuenciaX);
                            System.out.println(CodProv + "--" + secuenciaX);
                            
                            BeanVentaF = DaoVenta.ComprasCredito(paramCa);*/

                            if (compania.equals("01")) {
                                bnVenta.setValorFacturado((rs.getBigDecimal(10)).multiply(BigDecimal.ONE.negate()));
                                bnVenta.setExonerada((rs.getBigDecimal(13)).multiply(BigDecimal.ONE.negate()));
                            } else {
                                bnVenta.setValorFacturado(BigDecimal.ZERO);
                                bnVenta.setExonerada(BigDecimal.ZERO);
                            }


                            bnVenta.setBaseImponible((rs.getBigDecimal(11).setScale(2)).multiply(BigDecimal.ONE.negate()));


                            bnVenta.setIgv((rs.getBigDecimal(16)).multiply(BigDecimal.ONE.negate()));
                            bnVenta.setNoAfecto((rs.getBigDecimal(14)).multiply(BigDecimal.ONE.negate()));
                            bnVenta.setImporte((rs.getBigDecimal(19)).multiply(BigDecimal.ONE.negate()));





                        } else {

                            if (compania.equals("01")) {
                                bnVenta.setValorFacturado((rs.getBigDecimal(10)).multiply(BigDecimal.ONE.negate()));
                                bnVenta.setExonerada((rs.getBigDecimal(13)).multiply(BigDecimal.ONE.negate()));
                            } else {
                                bnVenta.setValorFacturado(BigDecimal.ZERO);
                                bnVenta.setExonerada(BigDecimal.ZERO);
                            }



                            bnVenta.setBaseImponible(rs.getBigDecimal(11));
                            bnVenta.setIgv(rs.getBigDecimal(16));
                            bnVenta.setNoAfecto(rs.getBigDecimal(14));
                            bnVenta.setImporte(rs.getBigDecimal(19));


                        }



                        dataVenta.add(bnVenta);
                        // }



                    }
                }
                //  }

                String periodo = mes + "/" + ano;
                Map parameters = new HashMap();
                parameters.put("P_RAZON", empresa);
                parameters.put("P_RUC", ruc);
                parameters.put("EJERCICIO", periodo);
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptcaja = "/com/contab/report/cuenta-VENTAS.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataVenta, rutarptcaja);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE VENTA CARGANDO ...... ESPERE POR FAVOR ...!!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE VENTA", "ALERT", JOptionPane.INFORMATION_MESSAGE);
                 ViewApplication.mensajeEstado.setText("REPORTE VENTA ..!");
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

    public static ResultSet ListarUnidadArticulos() throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);

            ArrayList objetos = DaoVenta.ListarTabla802(param);
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

    public static ArrayList<ArrayList<BeanTabla6>> repositorio(ResultSet rs) {
        ArrayList<ArrayList<BeanTabla6>> repositorio = new ArrayList<ArrayList<BeanTabla6>>();
        int val = 30000;
        ArrayList<BeanTabla6> array;
        int contador = 0;
        int cont = 2;
        try {
            if (rs != null) {
                //   System.out.println("repositorio sunat tabla 6");
                array = new ArrayList<BeanTabla6>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        // System.out.println("repositorio sunat tabla 6 " + cont);
                        array = new ArrayList<BeanTabla6>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanTabla6 bean = new BeanTabla6();
                    bean.setCodigoSunat(rs.getString(2));
                    bean.setIdUnidadArticulo(rs.getString(3));

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

    public static BeanTabla6 buscarTabla862(ArrayList<ArrayList<BeanTabla6>> repor, String codigo) {
        BeanTabla6 bean = new BeanTabla6();
        BeanTabla6 beanaux = new BeanTabla6();
        ArrayList<BeanTabla6> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanTabla6>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanTabla6) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getCodigoSunat().equals(codigo)) {

                    //  System.out.println("encontrado " + unidad);
                    // array.remove(beanaux);
                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;
    }

    public static ResultSet ListarCompraConstancia(String compania, String mesI, String mesF) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(compania); //numero de la empresa o compa;ia
            param.add(mesI); //ano
            param.add(mesF); //ano

            ArrayList objetos = DaoCompra.ReposotoriCompraConstancia(param);
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

    public static ArrayList<ArrayList<BeanVenta>> repositorioVenta(ResultSet rs) {
        ArrayList<ArrayList<BeanVenta>> repositorio = new ArrayList<ArrayList<BeanVenta>>();
        int val = 30000;
        ArrayList<BeanVenta> array;
        int contador = 0;
        int cont = 2;
        try {
            if (rs != null) {
                //   System.out.println("repositorio sunat tabla 6");
                array = new ArrayList<BeanVenta>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        System.out.println("repositorio  ventas " + cont);
                        array = new ArrayList<BeanVenta>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanVenta bean = new BeanVenta();
                    bean.setC_Jrnnumber(rs.getString(1));
                    bean.setCMCP_PROVEEDORDCP(rs.getString(2));
                    bean.setCMCP_SECUENCIADCP(rs.getString(3));
                    bean.setPreimpreso(rs.getString(4));
                    bean.setFMCP_TRANSACCION(rs.getString(5));
                    System.out.println("repositorio  ventas " + cont);

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

    public static BeanVenta buscarrepositorioVenta(ArrayList<ArrayList<BeanVenta>> repor, String PROVEEDORDCP, String SECUENCIADCP) {
        BeanVenta bean = new BeanVenta();
        BeanVenta beanaux = new BeanVenta();
        ArrayList<BeanVenta> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanVenta>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanVenta) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getCMCP_PROVEEDORDCP().equals(PROVEEDORDCP) && beanaux.getCMCP_SECUENCIADCP().equals(SECUENCIADCP)) {

                    //  System.out.println("encontrado " + unidad);
                    // array.remove(beanaux);
                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;
    }
}
