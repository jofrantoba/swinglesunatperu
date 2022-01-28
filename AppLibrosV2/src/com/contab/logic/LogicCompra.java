/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanCompra;
import com.contab.bean.BeanTabla6;
import com.contab.dao.Conexion;
import com.contab.dao.DaoCompra;
import com.contab.dao.DaoEmpresas;
import com.contab.datasource.DataSourceCompra;
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
public class LogicCompra {

    public static void listaSaldoCompra(String empresa, String ruc, String compania, String ano, String mes, File ruta, String tipoExpor) throws SQLException, Exception {

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

                ResultSet resultablaCompra = ListarCompraConstancia(compania, fechaI, fechaF);
                ArrayList<ArrayList<BeanCompra>> repositorioCompra = new ArrayList<ArrayList<BeanCompra>>();
                repositorioCompra = null;
                // repositorioTabla6 = null;
                if (resultablaCompra != null) {
                    repositorioCompra = repositorioCompra(resultablaCompra);
                }

                DataSourceCompra dataCompra = new DataSourceCompra();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                param.add(ano); //ano
                param.add(mes); //ano
                ArrayList objetos = new ArrayList();
                objetos = DaoCompra.consultaCompra(param);
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
                BeanCompra compraConstancia = new BeanCompra();
                BeanCompra BeanCompraF;
                if (rs != null && cst != null && cnx != null) {
                    while (rs.next()) {

                        BeanCompra bnCompra = new BeanCompra();
                        BeanTabla6 tabla = new BeanTabla6();
                        String tipoDocumento = rs.getString(6);

                        if (tipoDocumento == null) {
                            tipoDocumento = "";

                        }
                        tabla = buscarTabla862(repositorioTabla6, tipoDocumento);

                        /// Compra constancia


                        // String Jrnnumber = rs.getString(6);
                        String PROVEEDORDCP = rs.getString(8);
                        String SECUENCIADCP = rs.getString(22);
                        String FECHAVENCIMEINTO = rs.getString(25);

                        if (SECUENCIADCP == null) {
                            SECUENCIADCP = "";
                        }

                        if (PROVEEDORDCP == null) {
                            PROVEEDORDCP = "";
                        }


                        String valorN = rs.getString(7);
                        //  System.out.println(rs.getString(7));
                        //   110106000502
                        if (valorN.length() > 4) {
                            if (valorN != null) {
                                valorR = valorN.substring(4, 6) + "-" + valorN.substring(6, valorN.length());

                            }
                        }

                        ///System.out.println(rs.getString(7)+"--"+valorR);
                        //  System.out.println("provvedor " + PROVEEDORDCP + "Secuencai " + SECUENCIADCP);

                        if (repositorioCompra != null) {
                            compraConstancia = buscarRepositorioCompra(repositorioCompra, PROVEEDORDCP, SECUENCIADCP);

                        }

                        //


                        ViewApplication.mensajeEstado.setText("PROCESANDO  COMPRA Nª: " + empresa + " DEL PERIODO: " + ano + " numero" + rs.getString(1));

                        Serie = rs.getString(3);
                        //  Numero = rs.getString(4);
                        tabla10 = rs.getString(5);
                        preImpreso = rs.getString(4);
                        apellinombre = rs.getString(9);
                        ruc1 = rs.getString(10);
                        tc = rs.getString(16);

                        if (ruc1 != null) {
                            if (!ruc1.equals("")) {

                                if (ruc1.length() >= 8) {
                                    if (ruc1.length() == 8) {

                                        tabla2 = "01";
                                    } else {
                                        tabla2 = "06";
                                    }
                                }
                            }
                        }

                        bnCompra.setNumero(valorR);
                        bnCompra.setFechav(FECHAVENCIMEINTO);
                        bnCompra.setNombre(tabla.getIdUnidadArticulo());
                        bnCompra.setSerie(Serie);
                        //  bnCompra.setNumero(Numero);
                        bnCompra.setTabla10(tabla10);
                        bnCompra.setTabla2(tabla2);
                        bnCompra.setPreimpreso(preImpreso);
                        bnCompra.setApellDenominacion(apellinombre);
                        bnCompra.setRucDNI(ruc1);
                        bnCompra.setTc(tc);
                        bnCompra.setNumero1(compraConstancia.getPreimpreso());
                        bnCompra.setFechaEmision(compraConstancia.getFMCP_TRANSACCION());
                        String secuenciaX = rs.getString(22);
                        String CodProv = rs.getString(8);

                        // if (monto.compareTo(BigDecimal.ZERO) != 0) {
                        bnCompra.setTipoDocumento(tipoDocumento);
                        bnCompra.setFecha(rs.getString(2));
                        System.out.println(CodProv + "--" + tipoDocumento);
                        if (tipoDocumento == null) {
                            System.out.println(null + "--" + tipoDocumento);
                            tipoDocumento = "";
                        }
                        if (tipoDocumento.equals("07")) {
                            ArrayList paramCa = new ArrayList();
                            paramCa.add(OracleTypes.CURSOR);
                            paramCa.add(compania);
                            paramCa.add(CodProv);
                            paramCa.add(secuenciaX);


                            BeanCompraF = DaoCompra.ComprasCredito(paramCa);
                            bnCompra.setBaseImponible((rs.getBigDecimal(12).setScale(2, BigDecimal.ROUND_HALF_UP)).multiply(BigDecimal.ONE.negate()));
                            bnCompra.setIgv((rs.getBigDecimal(13).setScale(2, BigDecimal.ROUND_HALF_UP)).multiply(BigDecimal.ONE.negate()));

                            if (CodProv.equals("1021") || CodProv.equals("1018") || CodProv.equals("08967")) {
                                bnCompra.setNoAfecto(BigDecimal.ZERO);
                                bnCompra.setPercepcion((rs.getBigDecimal(14).setScale(2, BigDecimal.ROUND_HALF_UP)).multiply(BigDecimal.ONE.negate()));
                            } else {
                                bnCompra.setNoAfecto((rs.getBigDecimal(14).setScale(2, BigDecimal.ROUND_HALF_UP)).multiply(BigDecimal.ONE.negate()));
                                bnCompra.setPercepcion(BigDecimal.ZERO);
                            }



                            bnCompra.setImporte((rs.getBigDecimal(15).setScale(2, BigDecimal.ROUND_HALF_EVEN)).multiply(BigDecimal.ONE.negate()));
                            bnCompra.setTabla101(BeanCompraF.getTabla101());
                            bnCompra.setFechaR(BeanCompraF.getFechaR());
                            bnCompra.setSerie1(BeanCompraF.getSerie1());

                            bnCompra.setComprobante(BeanCompraF.getComprobante());


                        } else {

                            bnCompra.setBaseImponible(rs.getBigDecimal(12).setScale(2, BigDecimal.ROUND_HALF_UP));
                            bnCompra.setIgv(rs.getBigDecimal(13).setScale(2, BigDecimal.ROUND_HALF_UP));

                   
                             if (CodProv.equals("1021") || CodProv.equals("1018") || CodProv.equals("08967")) {
                                bnCompra.setNoAfecto(BigDecimal.ZERO);
                                bnCompra.setPercepcion((rs.getBigDecimal(14).setScale(2, BigDecimal.ROUND_HALF_UP)));
                            } else {
                                bnCompra.setNoAfecto((rs.getBigDecimal(14).setScale(2, BigDecimal.ROUND_HALF_UP)));
                                bnCompra.setPercepcion(BigDecimal.ZERO);
                            }
                            bnCompra.setImporte(rs.getBigDecimal(15).setScale(2, BigDecimal.ROUND_HALF_EVEN));


                        }


                        dataCompra.add(bnCompra);

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

                String rutarptcaja = "/com/contab/report/cuentaCOMPRAS.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataCompra, rutarptcaja);

                //Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataSaldoDeCuenta, rutarptcaja);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE COMPRA!!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE COMPRA", "ALERT", JOptionPane.INFORMATION_MESSAGE);
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

            ArrayList objetos = DaoCompra.ListarTabla862(param);
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

    public static ArrayList<ArrayList<BeanCompra>> repositorioCompra(ResultSet rs) {
        ArrayList<ArrayList<BeanCompra>> repositorio = new ArrayList<ArrayList<BeanCompra>>();
        int val = 30000;
        ArrayList<BeanCompra> array;
        int contador = 0;
        int cont = 2;
        try {
            if (rs != null) {
                //   System.out.println("repositorio sunat tabla 6");
                array = new ArrayList<BeanCompra>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        System.out.println("repositorio  compra " + cont);
                        array = new ArrayList<BeanCompra>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanCompra bean = new BeanCompra();
                    bean.setC_Jrnnumber(rs.getString(1));
                    bean.setCMCP_PROVEEDORDCP(rs.getString(2));
                    bean.setCMCP_SECUENCIADCP(rs.getString(3));
                    bean.setPreimpreso(rs.getString(4));
                    bean.setFMCP_TRANSACCION(rs.getString(5));
                    System.out.println("repositorio  compra " + cont);

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

    public static BeanCompra buscarRepositorioCompra(ArrayList<ArrayList<BeanCompra>> repor, String PROVEEDORDCP, String SECUENCIADCP) {
        BeanCompra bean = new BeanCompra();
        BeanCompra beanaux = new BeanCompra();
        ArrayList<BeanCompra> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanCompra>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanCompra) iterar.next();
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
