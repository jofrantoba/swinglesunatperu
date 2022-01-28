/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanKardex;
import com.contab.bean.BeanTabla10;
import com.contab.bean.BeanTabla12;
import com.contab.bean.BeanTabla6;
import com.contab.dao.Conexion;
import com.contab.dao.ConexionPermanente;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoKardex;
import com.contab.datasource.DatasourceKardex;
import com.contab.util.Exportar;
import com.contab.view.ViewApplication;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
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
public class LogicKardex {

    public static void listaKardex(String empresa, String ruc, String compania, String codAlmacen, String direccion, String ano, String mes, String numeroMes, String tipoR, File ruta, String tipoExpor) throws SQLException, Exception {
        if (ruta != null) {
            ConexionPermanente.cnx();
            Connection ora = ConexionPermanente.cnxOra;
            ResultSet rsD = null;
            CallableStatement cst = null;
            Conexion cnx = null;

            try {
                // System.out.print(compania + "cod almacen" + codAlmacen);
                DatasourceKardex dataKardex = new DatasourceKardex();

                ResultSet resultabla6 = LogicTabla6.ListarUnidadArticulos();
                ResultSet resultabla10 = LogicTabla10.ListarTabla10R();

                ResultSet resultabla12 = LogicTabla12.ListarTabla12();

                ArrayList<ArrayList<BeanTabla6>> repositorioTabla6 = new ArrayList<ArrayList<BeanTabla6>>();
                ArrayList<ArrayList<BeanTabla10>> repositorioTabla10 = new ArrayList<ArrayList<BeanTabla10>>();
                ArrayList<ArrayList<BeanTabla12>> repositorioTabla12 = new ArrayList<ArrayList<BeanTabla12>>();

                repositorioTabla6 = null;
                // repositorioTabla6 = null;
                if (resultabla6 != null) {
                    repositorioTabla6 = LogicTabla6.repositorio(resultabla6);
                }

                repositorioTabla10 = null;
                // repositorioTabla6 = null;
                if (resultabla10 != null) {
                    repositorioTabla10 = LogicTabla10.repositorio(resultabla10);
                }



                repositorioTabla12 = null;
                // repositorioTabla6 = null;
                if (resultabla12 != null) {
                    repositorioTabla12 = LogicTabla12.repositorio(resultabla12);
                }

                // ResultSet result = NUMDOCUMENTOKARDEX(compania, codAlmacen, ano, numeroMes);

                ArrayList<ArrayList<BeanKardex>> repositorio = new ArrayList<ArrayList<BeanKardex>>();
                repositorio = null;
                //if (result != null) {
                repositorio = repositorio(compania, codAlmacen, ano, numeroMes);
                // }


                //    ResultSet result2K = NUMDOCUMENTOKARDEX2(compania, ano, numeroMes);
                ArrayList<ArrayList<BeanKardex>> repositorio2M = new ArrayList<ArrayList<BeanKardex>>();
                repositorio2M = null;
                //  if (result2K != null) {
                repositorio2M = repositorio2(compania, ano, numeroMes);
                // }

                BeanTabla6 beanBuscadoTabla6;

                BeanTabla12 beanBuscadoTabla12;
                BeanTabla10 beanBuscadoTabla10;
                BeanKardex beanBuscado;
                BeanKardex beanBuscado2;

                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compañia
                param.add(codAlmacen); //llave de almacen por empresa
                param.add(ano); // Año
                param.add(numeroMes); // mes

                ArrayList objetos = new ArrayList();
                objetos = DaoKardex.listarProducto(param);  // lista de almacen por compañia
                rsD = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);

                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO KARDEX Nª: " + empresa + " DEL PERIODO: " + ano);

                String annono = ano + "-" + numeroMes;

                //  System.out.print("asd"+cst.getFetchSize());
                // lista de producto
                int cantidad2 = 0;
                String Valor = "";
                BigDecimal totalCantidad = BigDecimal.ZERO;
                BigDecimal totalF = BigDecimal.ZERO;
                BigDecimal costoUnitario1 = BigDecimal.ZERO;

                if (rsD != null && cst != null) {
                    ResultSet rsP = null;
                    CallableStatement cstP = null;
                    //int ultimo=rsD.;
                    //int current = rsD.getRow();
                    // rsD.last();
                    //  int count = rsD.getcolumnscount();
                    // rsD.first();
                    //rsD.relative(current);
                    int count15 = 0;

                    // count15 = data.(); 
                    while (rsD.next()) {
                        Valor = "OK";
                        //  System.out.print("cantidad filas  " + count15x + " zas" + annono);
                        cantidad2 = cantidad2 + 1;
                        //  System.out.println(cantidad2 + "cantidad de productos");

                        ViewApplication.mensajeEstado.setText("PROCESANDO KARDEXª: " + empresa + " DEL PERIODO: " + ano + " numero" + rsD.getString(1));

                        String Item = rsD.getString(1);
                        String unidad = rsD.getString(3);
                        String producto = rsD.getString(2);
                        // sacar las unidad

                        beanBuscadoTabla6 = LogicTabla6.buscarTabla6(repositorioTabla6, unidad);



                        ArrayList paramP = new ArrayList();


                        paramP.add(OracleTypes.CURSOR);

                        paramP.add(compania); //numero de la empresa o compañia
                        paramP.add(codAlmacen); //llave de almacen por empresa
                        paramP.add(Item); // codigo de producto de item
                        paramP.add(ano); // Año
                        paramP.add(numeroMes); // mes
                        ArrayList objetosP = new ArrayList();
                        objetosP = DaoKardex.listarMovimientoProducto(paramP, ora);
                        rsP = (ResultSet) objetosP.get(0);
                        cstP = (CallableStatement) objetosP.get(1);
                        //    System.out.println("conexion  base de datos  movimiento de Producto");


                        String AnnoAnterio = "";
                        int MesAnterior = Integer.parseInt(numeroMes) - 1;
                        String MesAnteripsdp = String.valueOf(MesAnterior);

                        if (MesAnterior == 0) {
                            int anos = Integer.parseInt(ano) - 1;

                            AnnoAnterio = anos + "-12";
                        } else {

                            if (MesAnteripsdp.length() == 1) {

                                AnnoAnterio = ano + "-0" + MesAnteripsdp;
                            } else {

                                AnnoAnterio = ano + "-" + MesAnterior;
                            }
                        }
                        // lista de movimiento de un producto

                        if (rsP != null && cstP != null) {
                            String fecha = "";
                            String serie = "";
                            String Movimiento = "";
                            String tipo12 = "";
                            String motivo = "";
                            String tipo10 = "";
                            String numeroS = "";
                            String REF = "";
                            BigDecimal salida = BigDecimal.ZERO;
                            BigDecimal cantidad = BigDecimal.ZERO;
                            BigDecimal monto = BigDecimal.ZERO;
                            BigDecimal costoUnitario = null;
                            BigDecimal totalE = BigDecimal.ZERO;
                            BigDecimal totalS = BigDecimal.ZERO;
                            BeanKardex bnKardex4 = new BeanKardex();
                            BeanKardex bnkardexFilas = new BeanKardex();

                            // saldo inicial de cada producto
                            //   System.out.println("conexion  base de datos inicial");
                            bnKardex4 = SaldoInicial(compania, codAlmacen, Item, AnnoAnterio, ora);
                            //************************************ cantidad de registro de kardex*************        //
                            bnkardexFilas = cantidadRegistroProductoKardex(compania, codAlmacen, Item, ano, numeroMes, ora);
                        //    System.out.println("cantidad de registro " + bnkardexFilas.getCantidadFilas() + " codAlmacen " + codAlmacen + " item " + Item);
                            ////////////*****************************************************************//////////////////////////////////
                            totalCantidad = bnKardex4.getCantidadF();
                            //if (bnKardex4 != null) {
                            //   System.out.println("Saldo  inicial recibir " + bnKardex4.getCantidadF() + " " + compania + "   " + codAlmacen + "   " + Item + "   " + AnnoAnterio);
                            bnKardex4.setProducto(producto);
                            bnKardex4.setUnidad(beanBuscadoTabla6.getCodigoSunat());
                            bnKardex4.setCodigoAgrupacion(Item);
                            bnKardex4.setFecha("01-" + numeroMes + "-" + ano);
                            bnKardex4.setTabla12("16");
                            bnKardex4.setTabla10(" ");
                            bnKardex4.setNumero(" ");
                            bnKardex4.setSerie(" ");
                            bnKardex4.setCantidadE(BigDecimal.ZERO);
                            bnKardex4.setCostoUE(BigDecimal.ZERO);
                            bnKardex4.setTotalE(BigDecimal.ZERO);
                            bnKardex4.setCantidadS(BigDecimal.ZERO);
                            bnKardex4.setCostoUS(BigDecimal.ZERO);
                            bnKardex4.setTotalS(BigDecimal.ZERO);
                            bnKardex4.setCantidadF(totalCantidad);
                            bnKardex4.setCostoUF(bnKardex4.getCostoUF());
                            bnKardex4.setTotalF(totalCantidad.multiply(bnKardex4.getCostoUF()));

                            dataKardex.addBalance(bnKardex4);
                            //  }
                            BigDecimal costoCambioQ = bnKardex4.getCostoUF();

                            String idTabla = "";
                            String seriesM = "";
                            String numeroM = "";
                            String Observacion = "";

                            totalF = totalCantidad.multiply(bnKardex4.getCostoUF()).setScale(5, BigDecimal.ROUND_HALF_UP);
                            int contadorC = 1;
                            Hashtable CodigoH = new Hashtable();
                            CodigoH.put(0, costoCambioQ);
                            // listar  movimiento por  producto
                            int contar = 0;
                            int count15x = rsP.getConcurrency();
                            while (rsP.next()) {
                                //        System.out.println("hola");
                                contar = contar + 1;
                                //System.out.println("cantidad" + count15x + "contar" + contar);
                                // ViewApplication.mensajeEstado.setText("PROCESANDO KARDEXª: " + empresa + " DEL PERIODO: " + ano + " numero" + rs.getString(4));
                                BeanKardex bnKardex = new BeanKardex();
                                // codigo de producto
                                //  String producto = rsP.getString(2); // nombre de producto por ejemplo leche
                                //    bnKardex.setProducto(producto);

                                fecha = rsP.getString(1);
                                serie = null;
                                tipo12 = rsP.getString(13);

                                motivo = rsP.getString(15);
                                if (motivo == null) {
                                    motivo = "";
                                }
                                numeroS = rsP.getString(4);
                                REF = rsP.getString(16);
                                //     System.out.println("TUPO 12" + tipo12 + "MOTIVO" + motivo);
                                Movimiento = rsP.getString(13);
                                String Umed = rsP.getString(14);
                                if (Umed == null) {
                                    Umed = "";
                                }
                                BigDecimal valor12 = rsP.getBigDecimal(11);
                                //Umed
                                salida = rsP.getBigDecimal(4);  //RoundingMode.DOWN   BigDecimal.ROUND_DOWN
                                cantidad = rsP.getBigDecimal(12);  // BigDecimal.ROUND_HALF_DOWN
                                //monto = rsP.getBigDecimal(4);

                                beanBuscadoTabla12 = LogicTabla12.buscarTabla12(repositorioTabla12, tipo12, motivo);

                                Observacion = rsP.getString(15);
                                System.out.println(numeroS+"buscar---");
                                
                               //   beanBuscado2 = buscarInRepositorio(repositorio, numeroS.trim());
                                //   bnKardex.setNumero(beanBuscado2.getNumero());
                                //    bnKardex.setSerie(beanBuscado2.getSerie());
                                  
                                if (numeroS != null && REF != null) {
                                   beanBuscado = buscarInRepositorio2(repositorio2M, REF);
                                    beanBuscadoTabla10 = LogicTabla10.buscarTabla10(repositorioTabla10, beanBuscado.getTabla10());
                                    bnKardex.setTabla10(beanBuscadoTabla10.getCodigoSunat());
                                    bnKardex.setNumero(beanBuscado.getNumero());
                                    bnKardex.setSerie(beanBuscado.getSerie());

                                    idTabla = beanBuscado.getTabla10();
                                    seriesM = beanBuscado.getNumero();
                                    numeroM = beanBuscado.getSerie();
                                } else {
                                   beanBuscado2 = buscarInRepositorio(repositorio, numeroS);
                                    idTabla = beanBuscado2.getTabla10();
                                    seriesM = beanBuscado2.getNumero();
                                    numeroM = beanBuscado2.getSerie();

                                    beanBuscadoTabla10 = LogicTabla10.buscarTabla10(repositorioTabla10, beanBuscado2.getTabla10());
                                    bnKardex.setTabla10(beanBuscadoTabla10.getCodigoSunat());
                                    bnKardex.setNumero(beanBuscado2.getNumero());
                                    bnKardex.setSerie(beanBuscado2.getSerie());

                                }
                                //G/R-021-0043272 observacion

                                //  System.out.println(Observacion.substring(3, Observacion.length()));
                                if (REF != null) {

                                    if (REF.equals("MM")) {
                                        bnKardex.setTabla10(idTabla);
                                        bnKardex.setNumero(seriesM);
                                        bnKardex.setSerie(numeroM);
                                    }
                                }
                                costoUnitario = rsP.getBigDecimal(9);
                                bnKardex.setTabla12(beanBuscadoTabla12.getCodigoSunat());

                                bnKardex.setFecha(fecha);
                                bnKardex.setItemProducto(Item);

                                totalE = BigDecimal.ZERO;
                                totalS = BigDecimal.ZERO;
                                BigDecimal costoUF = BigDecimal.ZERO;

                                bnKardex.setProducto(producto);
                                bnKardex.setUnidad(beanBuscadoTabla6.getCodigoSunat());
                                bnKardex.setCodigoAgrupacion(Item);

                                if (Movimiento.substring(0, 1).equals("I")) {

                                    if (Umed.equals("XXX")) {

                                        bnKardex.setCantidadE(BigDecimal.ZERO);
                                        bnKardex.setCostoUE(BigDecimal.ZERO); //setScale(6)
                                        totalE = valor12;
                                        //  System.out.println(totalE);
                                        bnKardex.setTotalE(totalE);
                                        bnKardex.setCantidadS(BigDecimal.ZERO);
                                        bnKardex.setCostoUS(BigDecimal.ZERO);
                                        bnKardex.setTotalS(BigDecimal.ZERO);
                                        totalCantidad = (totalCantidad.add(cantidad));


                                    } else {
                                        //entradas
                                        bnKardex.setCantidadE(cantidad);
                                        bnKardex.setCostoUE(costoUnitario);

                                        BigDecimal bd = new BigDecimal(CodigoH.get(contar - 1).toString()); //.setScale(6)
                                        totalE = cantidad.multiply(costoUnitario);
                                        // System.out.println(totalE);
                                        bnKardex.setTotalE(totalE);
                                        bnKardex.setCantidadS(BigDecimal.ZERO);
                                        bnKardex.setCostoUS(BigDecimal.ZERO);
                                        bnKardex.setTotalS(BigDecimal.ZERO);
                                        totalCantidad = (totalCantidad.add(cantidad));

                                    }
                                    // saldo = saldo.add(debe.subtract(haber));

                                } else {

                                    totalCantidad = (totalCantidad.subtract(cantidad));
                                    //errror  costoCambioQ
                                    bnKardex.setCantidadE(BigDecimal.ZERO);
                                    bnKardex.setCostoUE(BigDecimal.ZERO);
                                    bnKardex.setTotalE(BigDecimal.ZERO);
                                    //System.out.print(CodigoH.get(1)+"arrrya-------------");

                                    //  costoCambioQ=BigDecimal.valueOf(CodigoH.get(1).toString());
                                    BigDecimal bd = new BigDecimal(CodigoH.get(contar - 1).toString()); //.setScale(6)
                                    totalS = (cantidad.multiply(bd));
                                    bnKardex.setCantidadS(cantidad);
                                    bnKardex.setCostoUS(bd);
                                    //  costoCambioQ=BigDecimal.ZERO;
                                    bnKardex.setTotalS(totalS);

                                }

                                totalF = (totalF.add(totalE.subtract(totalS)));

                                if (Movimiento.substring(0, 1).equals("S")) {

                                    bnKardex.setCostoUE(BigDecimal.ZERO);
                                    if (totalCantidad.compareTo(BigDecimal.ZERO) != 0) {
                                        costoUF = (totalF.divide(totalCantidad, 5));
                                    } else {
                                        costoUF = BigDecimal.ZERO;
                                    }
                                    //  costoUF = totalF.divide(totalCantidad, 2);
                                    bnKardex.setCostoUS(costoUF);

                                }

                                //   totalF=totalF.

                                if (totalCantidad.compareTo(BigDecimal.ZERO) != 0) {
                                    costoUnitario1 = totalF.divide(totalCantidad, 5);
                                    //costoUnitario1 =costoUnitario1.setScale(5, BigDecimal.ROUND_HALF_UP);

                                } else {
                                    costoUnitario1 = BigDecimal.ZERO;
                                }
                                //  costoUnitario1 = totalF.divide(totalCantidad, 2, RoundingMode.HALF_UP);
                                // el problema .setScale(2)

                                // System.out.println(totalF.toString()+"---" + costoUnitario1.toString()+"---" + totalCantidad.toString());
                                bnKardex.setCantidadF(totalCantidad);
                                CodigoH.put(contar, costoUnitario1);
                                // contadorC ++;
                                //     DecimalFormat df = new DecimalFormat("#,##0.00000");
                                //  String str=df.format(costoUnitario1);
                                //Number n = df.parse(str);
                                // BigDecimal bd= new BigDecimal (str); 
                                //      BigDecimal number1 = new BigDecimal(n.floatValue());

                                //*******************************************************//////////////////////////////
                                if (bnkardexFilas.getCantidadFilas() == contar) {

                                    BeanKardex bnKardexxx = promedioKardex(compania, codAlmacen.trim(), Item.trim(), annono, ora);
                                    System.out.println(bnKardexxx.getCostoUF() + " obtener " + Item + "cantidad de registro contados" + contar);
                                    bnKardex.setCostoUF(bnKardexxx.getCostoUF());
                                    costoUnitario1 = bnKardexxx.getCostoUF();
                                } else {

                                    bnKardex.setCostoUF(costoUnitario1);
                                }
                                // costoCambioQ = costoUnitario1;
                                // totalF=costoUnitario1.multiply(totalCantidad);
                                // BigDecimal valor = costoUnitario1.setScale(5, RoundingMode.DOWN);

                                //System.out.print(str + "unitarioa "+n+" "+number1.setScale(5));
                                bnKardex.setTotalF(costoUnitario1.multiply(totalCantidad));
                                dataKardex.addBalance(bnKardex);
                            }
                            contar = 0;

                            CodigoH.clear();
                            if (rsP != null) {
                                rsP.close();
                            }
                            if (cstP != null) {
                                cstP.close();
                            }
                        }

                        //  System.out.println("Cerarr conexion  base de datos  movimiento de Producto");
                        rsP.close();
                        cstP.close();
                    }


                }
                if (!Valor.equals("OK")) {

                    JOptionPane.showMessageDialog(null, "NO HOY MOVIMIENTO DE PRODUCTO  MES  " + numeroMes + "AÑO " + ano, "ALERT", JOptionPane.INFORMATION_MESSAGE);
                }


                repositorio = null;

                repositorioTabla6 = null;
                // }


                Map parameters = new HashMap();
                parameters.put("P_RAZON", empresa.toUpperCase());
                parameters.put("P_RUC", ruc);
                parameters.put("P_PERIODO", mes.toUpperCase() + " DEL " + ano.toUpperCase());
                parameters.put("ESTABLECIMIENTO", direccion.toUpperCase());
                parameters.put("TIPO5", "01  MERCADERIAS");
                //  parameters.put("DESCRIPCION", ano);
                //   parameters.put("CODIGO", empresa);
                parameters.put("METODO", "COSTO PROMEDIO PONDERADO");
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutarptKardex = "";
                if (tipoR.equals("C")) {
                    rutarptKardex = "/com/contab/report/LibroKardex1.jasper";
                } else {
                    rutarptKardex = "/com/contab/report/LibroKardex2.jasper";
                }

                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataKardex, rutarptKardex, "Caja");
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText(".....PROCESANDO.....!!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE LIBRO KARDEX ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
                  ViewApplication.mensajeEstado.setText(".....REPORTE LIBRO KARDEX.....!!");

            } catch (SQLException ex) {
                System.out.print("necio: " + ex.getErrorCode() + ex.getLocalizedMessage());
            } finally {

                cnx.destroy();
                if (rsD != null) {
                    cst.close();
                }
                // if (cst != null) {
                //     cst.close();
                // }
                if (!DaoEmpresas.objCnx.getCnx().isClosed()) {
                    DaoEmpresas.objCnx.destroy();
                }
                ora.close();
            }


        }
    }

    public static BeanKardex SaldoInicial(String CodCompania, String codAlmacen, String ItemProducto, String fecha, Connection cnx) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        BeanKardex bnKardex = new BeanKardex();
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(CodCompania);
            param.add(codAlmacen);
            param.add(ItemProducto);
            param.add(fecha);
            ArrayList objetos = DaoKardex.SaldoInicialXEmpresaXAlmacen(param, cnx);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            //  String numero = "";
            //  System.out.println("saldo inicial " + CodCompania + "  " + fecha + "item " + ItemProducto);
            if (rs != null && cst != null) {
                while (rs.next()) {

                    bnKardex.setCantidadF(rs.getBigDecimal(5));
                    bnKardex.setCostoUF(rs.getBigDecimal(7));
                    //        System.out.println("saldo inicial:///// " + rs.getBigDecimal(5) + " " + CodCompania + "//" + fecha + "item" + ItemProducto);

                }
                rs.close();
                cst.close();
                //    DaoEmpresas.objCnx.destroy();
            }

        } catch (SQLException ex) {
            //  JOptionPane.showMessageDialog(null, "Error en la conexion", "alert", JOptionPane.OK_CANCEL_OPTION);
            // System.out.print("necio: " + ex.getErrorCode());
            rs.close();
            cst.close();
            //  DaoEmpresas.objCnx.destroy();
            return null;
        } finally {
            //    System.out.println("Cerrar conexion base de datos Saldo inicial");
            if (rs != null) {
                rs.close();
            }
            if (cst != null) {
                cst.close();
            }
            if (!DaoEmpresas.objCnx.getCnx().isClosed()) {
                DaoEmpresas.objCnx.destroy();
            }
        }
        return bnKardex;
    }

    public static BeanKardex promedioKardex(String CodCompania, String codAlmacen, String ItemProducto, String fecha, Connection cnx) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        BeanKardex bnKardex = new BeanKardex();
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(CodCompania);
            param.add(codAlmacen);
            param.add(ItemProducto);
            param.add(fecha.trim());
            ArrayList objetos = DaoKardex.promedioKardex(param, cnx);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            //  String numero = "";
            //  System.out.println("saldo inicial " + CodCompania + "  " + fecha + "item " + ItemProducto);
            if (rs != null && cst != null) {
                while (rs.next()) {

                    bnKardex.setCostoUF(rs.getBigDecimal(7));
                    //   bnKardex.setCostoUF(rs.getBigDecimal(7));
                //    System.out.println("saldo final:///// " + rs.getBigDecimal(7) + " item" + ItemProducto + "codAlmacen" + codAlmacen + "fecha" + fecha);

                }
                rs.close();
                cst.close();
                //    DaoEmpresas.objCnx.destroy();
            }

        } catch (SQLException ex) {
            //  JOptionPane.showMessageDialog(null, "Error en la conexion", "alert", JOptionPane.OK_CANCEL_OPTION);
            // System.out.print("necio: " + ex.getErrorCode());
            rs.close();
            cst.close();
            //  DaoEmpresas.objCnx.destroy();
            return null;
        } finally {
            //    System.out.println("Cerrar conexion base de datos Saldo inicial");
            if (rs != null) {
                rs.close();
            }
            if (cst != null) {
                cst.close();
            }
            if (!DaoEmpresas.objCnx.getCnx().isClosed()) {
                DaoEmpresas.objCnx.destroy();
            }
        }
        return bnKardex;
    }

    public static BeanKardex cantidadRegistroProductoKardex(String CodCompania, String codAlmacen, String ItemProducto, String fecha, String mes, Connection cnx) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        BeanKardex bnKardex = new BeanKardex();
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(CodCompania);
            param.add(codAlmacen);
            param.add(ItemProducto);
            param.add(fecha);
            param.add(mes);
            ArrayList objetos = DaoKardex.cantidadRegistroProductoKardex(param, cnx);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            //  String numero = "";
           // System.out.println("saldo inicial " + CodCompania + "  " + fecha + "item " + ItemProducto);
            if (rs != null && cst != null) {
                while (rs.next()) {

                    bnKardex.setCantidadFilas(rs.getInt(1));
                    //   bnKardex.setCostoUF(rs.getBigDecimal(7));
                    //System.out.println("saldo inicial:///// " + rs.getBigDecimal(5) + " " + CodCompania + "//" + fecha + "item" + ItemProducto);

                }
                rs.close();
                cst.close();
                //    DaoEmpresas.objCnx.destroy();
            }

        } catch (SQLException ex) {
            //  JOptionPane.showMessageDialog(null, "Error en la conexion", "alert", JOptionPane.OK_CANCEL_OPTION);
            // System.out.print("necio: " + ex.getErrorCode());
            rs.close();
            cst.close();
            //  DaoEmpresas.objCnx.destroy();
            return null;
        } finally {
            //    System.out.println("Cerrar conexion base de datos Saldo inicial");
            if (rs != null) {
                rs.close();
            }
            if (cst != null) {
                cst.close();
            }
            if (!DaoEmpresas.objCnx.getCnx().isClosed()) {
                DaoEmpresas.objCnx.destroy();
            }
        }
        return bnKardex;
    }

    public static ResultSet NUMDOCUMENTOKARDEX(String CodCompania, String codAlamcen, String anno, String mes) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(CodCompania);
            param.add(codAlamcen);
            param.add(anno);
            param.add(mes);
            ArrayList objetos = DaoKardex.NUMDOCUMENTOKARDEX(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                return rs;
            }
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
            System.out.println(ex.getStackTrace());
        } finally {
            //  cst.close();
            if (rs != null) {
                rs.close();
            }
            if (cst != null) {
                cst.close();
            }
            if (!DaoEmpresas.objCnx.getCnx().isClosed()) {
                DaoEmpresas.objCnx.destroy();
            }
        }
        return rs;
    }

    public static ResultSet NUMDOCUMENTOKARDEX2(String CodCompania, String anno, String mes) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList paramMM = new ArrayList();
            paramMM.add(OracleTypes.CURSOR);
            paramMM.add(CodCompania);
            paramMM.add(anno);
            paramMM.add(mes);

            ArrayList objetos = DaoKardex.NUMDOCUMENTOKARDEX2(paramMM);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                return rs;
            }
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
            System.out.println(ex.getStackTrace());
        } finally {
            //  cst.close();
            if (rs != null) {
                rs.close();
            }
            if (cst != null) {
                cst.close();
            }
            if (!DaoEmpresas.objCnx.getCnx().isClosed()) {
                DaoEmpresas.objCnx.destroy();
            }
        }
        return rs;
    }

    private static BeanKardex buscarBean(HashSet<BeanKardex> lista, String codigo) {
        BeanKardex bean = new BeanKardex();
        BeanKardex beanaux = new BeanKardex();
        Iterator i = lista.iterator();
        while (i.hasNext()) {
            beanaux = (BeanKardex) i.next();
            if (beanaux.getCodigoBusquedad().equals(codigo)) {
                bean = beanaux;
                break;
            }
        }
        return bean;
    }

    public static ArrayList<ArrayList<BeanKardex>> repositorio(String CodCompania, String codAlamcen, String anno, String mes) throws SQLException {
        ArrayList<ArrayList<BeanKardex>> repositorio = new ArrayList<ArrayList<BeanKardex>>();

        ResultSet rs = null;
        CallableStatement cst = null;

        int val = 30000;
        ArrayList<BeanKardex> array;
        int contador = 0;
        int cont = 2;
        try {

            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(CodCompania);
            param.add(codAlamcen);
            param.add(anno);
            param.add(mes);
            ArrayList objetos = DaoKardex.NUMDOCUMENTOKARDEX(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);

            if (rs != null) {
                // System.out.println("repositorio 1");
                array = new ArrayList<BeanKardex>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        //   System.out.println("repositorio " + cont);
                        array = new ArrayList<BeanKardex>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanKardex bean = new BeanKardex();
                    bean.setTabla10(rs.getString(1));
                    bean.setSerie(rs.getString(2));
                    bean.setNumero(rs.getString(3));
                    bean.setCodigoBusquedad(rs.getString(4));
                    array.add(bean);
                }
                repositorio.add(array);
                rs.close();
            }
        } catch (Exception ex) {
        } finally {
            //  cst.close();
            if (rs != null) {
                rs.close();
            }
            if (cst != null) {
                cst.close();
            }
            if (!DaoEmpresas.objCnx.getCnx().isClosed()) {
                DaoEmpresas.objCnx.destroy();
            }
        }
        return repositorio;
    }

    public static ArrayList<ArrayList<BeanKardex>> repositorio2(String CodCompania, String anno, String mes) throws SQLException {
        ArrayList<ArrayList<BeanKardex>> repositorio = new ArrayList<ArrayList<BeanKardex>>();
        int val = 30000;
        ArrayList<BeanKardex> arrayMM;
        int contador = 0;
        int cont = 2;
        ResultSet rs = null;
        CallableStatement cst = null;
        try {

            ArrayList paramMM = new ArrayList();
            paramMM.add(OracleTypes.CURSOR);
            paramMM.add(CodCompania);
            paramMM.add(anno);
            paramMM.add(mes);

            ArrayList objetos = DaoKardex.NUMDOCUMENTOKARDEX2(paramMM);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null) {
                //System.out.println("repositorio 2"); 1014   G/R-021-0043272 observacion
                arrayMM = new ArrayList<BeanKardex>();

                BeanKardex beanP2 = new BeanKardex();
                beanP2.setTabla10("01");
                beanP2.setSerie("021");
                beanP2.setNumero("0043272");
                beanP2.setCodigoBusquedad("3633");
                arrayMM.add(beanP2);
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(arrayMM);
                        //   System.out.println("repositorio ---" + cont);
                        //   arrayMM = new ArrayList<BeanKardex>();
                        contador = 0;
                        cont = cont + 1;
                    }

                    //   System.out.println("ok");
                    BeanKardex beanP = new BeanKardex();
                    beanP.setTabla10(rs.getString(1));
                    beanP.setSerie(rs.getString(2));
                    beanP.setNumero(rs.getString(3));
                    beanP.setCodigoBusquedad(rs.getString(4).trim());
                    arrayMM.add(beanP);
                }
                repositorio.add(arrayMM);
                rs.close();
            }
        } catch (Exception ex) {
        } finally {
            //  cst.close();
            if (rs != null) {
                rs.close();
                //    rs.AFTER_TRANSACTION();
            }
            if (cst != null) {
                cst.close();
            }
            if (!DaoEmpresas.objCnx.getCnx().isClosed()) {
                DaoEmpresas.objCnx.destroy();
                //    DaoEmpresas.objCnx.destroy();
            }
        }
        return repositorio;
    }

    public static BeanKardex buscarInRepositorio(ArrayList<ArrayList<BeanKardex>> repor, String codigo) {
        BeanKardex bean = new BeanKardex();
        BeanKardex beanaux = new BeanKardex();
        ArrayList<BeanKardex> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanKardex>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanKardex) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getCodigoBusquedad().equals(codigo)) {
                    //  System.out.println("eliminado repositorio 1 " + codigo);
                    //  array.remove(beanaux);
                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;
    }

    public static BeanKardex buscarInRepositorio2(ArrayList<ArrayList<BeanKardex>> repor, String codigo) {
        BeanKardex bean = new BeanKardex();
        BeanKardex beanaux = new BeanKardex();
        ArrayList<BeanKardex> array;
        ///   System.out.println(" " + codigo + " codigoooooooooooooooo");
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanKardex>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanKardex) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getCodigoBusquedad().equals(codigo)) {
                    //   System.out.println("eliminado repositorio 2 " + codigo);
                    //  array.remove(beanaux);
                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;
    }
}
