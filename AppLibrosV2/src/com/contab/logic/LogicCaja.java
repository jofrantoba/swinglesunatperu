/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanBanco;
import com.contab.bean.BeanMovimientoCajaBanco;
import com.contab.bean.beanCaja;
import com.contab.dao.Conexion;
import com.contab.dao.DaoCaja;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoMovimientoCajaBanco;
import com.contab.datasource.datasourceCaja;
import com.contab.util.ClassCuentaSunat;
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
 * @author 777
 */
public class LogicCaja {

    public static void listaDeEmpresa(String empresa, String ruc, String compania, int inicioM, int finM, String descIMes, String descFMes, String ano, String cuentaBanco, String descripcionCuenta, String center, String tipoR, File ruta, String tipoExpor) throws SQLException, Exception {

        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            try {

                ArrayList<BeanBanco> arrayx = new ArrayList<BeanBanco>();

                int i = 0;
                if (cuentaBanco.equals("0")) {


                    arrayx = LogicCuentaBanco.listaCuentaEfectivo(compania);

                } else {
                    BeanBanco obj = new BeanBanco();
                    obj.setNumCuenta(cuentaBanco);
                    obj.setCuentaBanco(descripcionCuenta);
                    obj.setCodigoCentro(center);
                    arrayx.add(obj);

                }
                datasourceCaja dataCaja = new datasourceCaja();
                ClassFecha mes = new ClassFecha();
                String fechaI = mes.Mes(finM);
                String fechaF = mes.Mes(finM);
                ///-------------------------------------repositorio-------------------------------------------------------------- ///

                ArrayList<ArrayList<BeanMovimientoCajaBanco>> repositorio = new ArrayList<ArrayList<BeanMovimientoCajaBanco>>();
                repositorio = null;
                //if (result != null) {
                repositorio = repositorio(compania, ano, fechaI);
                ////////////------------------------fin de repositorio--------------------------------------------///

                ///-------------------------------------repositorio Quiebre-------------------------------------------------------------- ///

                ArrayList<ArrayList<BeanBanco>> repositorioQ = new ArrayList<ArrayList<BeanBanco>>();
                repositorioQ = null;
                //if (result != null) {
                repositorioQ = repositorioQ(compania, ano, fechaI);
                ////////////------------------------fin de repositorio Quiebre--------------------------------------------///


                BeanMovimientoCajaBanco BeanMovCajBan;
                while (i < arrayx.size()) {


                    cuentaBanco = arrayx.get(i).getNumCuenta();
                    descripcionCuenta = arrayx.get(i).getCuentaBanco();
                    center = arrayx.get(i).getCodigoCentro();
                    //   String fechaSI = ano + "-" + mes.Mes(inicioM) + "-01";
                    // String fechaSF = ano + "-" + mes.Mes(inicioM) + "-01";
                    //java.sql.Date ini = java.sql.Date.valueOf(fechaSI);
                    // java.sql.Date fin = java.sql.Date.valueOf(fechaSF);

                    //  BigDecimal SaldoIncial = LogicCaja.saldoInicialEfectivo(cuentaBanco, ini, fin, compania, center);
                    //   if (SaldoIncial == null) {
                    //     SaldoIncial = BigDecimal.ZERO;
                    // }
                    ArrayList param = new ArrayList();
                    ArrayList paramS = new ArrayList();
                    //  ClassFecha mes = new ClassFecha();

                    BigDecimal SaldoIncialx = BigDecimal.ZERO;

                    ///////////-------------------saldo inicial--------------------------------

                    if (center == null ? "" == null : center.equals("")) {
                        center = "0000";
                    }

                    SaldoIncialx = saldoInicial(compania, cuentaBanco, center, ano, Integer.parseInt(fechaI));
                    int ExistenciaMov = Existencia_Movimiento_Caja(fechaI, fechaF, ano, compania, cuentaBanco, center);

                    //   System.out.println("saldooooo  cuenta" + cuentaBanco + " center " + center + "  " + SaldoIncialx);

                    ////////////////-----------fin saldo incial--------------------

                    param.add(OracleTypes.CURSOR);



                    // String fechaI="01/01/2010";
                    //String fechaF="31/01/2010";
                    param.add(fechaI);//mes de inicio
                    param.add(fechaF);//mes fin
                    param.add(ano); //ano
                    param.add(compania); //numero de la empresa o compa;ia
                    param.add(cuentaBanco);
                    param.add(center);


                    ArrayList objetos = DaoCaja.consultaCaja(param);
                    rs = (ResultSet) objetos.get(0);
                    cst = (CallableStatement) objetos.get(1);


// Periodo 1, Auxiliar 2, NroVouch 3, NumDoc 4, Cuenta 5,  CentroC 6
// Moneda  7, CodeAux 8, Cuentaaux 9, Tip_Doc 10, Num_Doc 11, Fecha 12, As Debe 13,
                    //Haber  14, Glosa 15, NomCuenta 16
                    // NroVouch 17 , NroDoc 18,19 Girado,Glosa 20 , Anulado  21

                    ViewApplication.mensajeEstado.setText(null);
                    ViewApplication.mensajeEstado.setText("PROCESANDO CUENTA Nª: " + cuentaBanco + "---" + descripcionCuenta + " DEL PERIODO: " + fechaI + "/" + ano);

                    String glosa = "";
                    int Nregistro = 0;
                    BigDecimal saldo = BigDecimal.ZERO;
                    BigDecimal saldoN = BigDecimal.ZERO;
                    BigDecimal saldoDebe = BigDecimal.ZERO;
                    BigDecimal saldoHaber = BigDecimal.ZERO;
                    BeanBanco bntB = null;
                    beanCaja bnCajaM = new beanCaja();
                    bnCajaM.setAgrupar(descripcionCuenta);
                    bnCajaM.setNomCuenta("SALDO INICIAL");
                    //   bnCaja.setEntidad(descripcionCuenta);
                    bnCajaM.setNumCuenta("");
                    bnCajaM.setEntidad("");
                    bnCajaM.setFecha("");
                    bnCajaM.setNumResgistro("");
                    bnCajaM.setNumero(" ");
                    bnCajaM.setDenominacion("  ");
                    bnCajaM.setNumResgistro("");

                    if (SaldoIncialx.compareTo(BigDecimal.ZERO) == 1) { //saldo es mayor de 0 entonces 1
                        bnCajaM.setDebe(SaldoIncialx.abs());
                        saldoDebe = SaldoIncialx.abs();
                        saldoHaber = BigDecimal.ZERO;
                        bnCajaM.setHaber(BigDecimal.ZERO);
                    } else {
                        saldoDebe = BigDecimal.ZERO;

                        saldoHaber = SaldoIncialx.abs();
                        bnCajaM.setDebe(BigDecimal.ZERO);
                        bnCajaM.setHaber(SaldoIncialx.abs());
                    }

                    saldoN = saldo.add(saldoDebe.subtract(saldoHaber));
                    bnCajaM.setSumaDebe(saldoDebe.abs());
                    bnCajaM.setSumaHaber(saldoHaber.abs());

                    if (!descripcionCuenta.equals("Todos") && ExistenciaMov > 0) {
                        dataCaja.addCaja(bnCajaM);
                    }




                    if (rs != null && cst != null) {
                        while (rs.next()) {
                            ArrayList paramCa = new ArrayList();
                            beanCaja bnCaja = new beanCaja();

                            String valorVOU = rs.getString(11);
                            String valorN = rs.getString(3);
                            if (valorVOU == null) {
                                valorVOU = "0";
                            }
                            if (valorN == null) {
                                valorN = "0";
                            }
                            String tipo = rs.getString(10);
                            if (tipo == null) {
                                tipo = "";
                            } else {
                                tipo = rs.getString(10);
                            }


                            paramCa.add(OracleTypes.CURSOR);
                            paramCa.add(ano);
                            paramCa.add(compania);
                            paramCa.add(valorN);
                            paramCa.add(valorVOU);

                            BeanMovCajBan = DaoMovimientoCajaBanco.consultaMovimientoCajaBanco(paramCa);
                            //    System.out.println(valorN + "-------" + valorVOU);
                            //  BeanMovCajBan = buscarInRepositorio2(repositorio, valorN, valorVOU);
                            String Auxiliar = rs.getString(2);

                            if (Auxiliar == null) {
                                Auxiliar = "";
                            }


                            // if (!(BeanMovCajBan.getTipoMov().equals("EF") && Integer.parseInt(BeanMovCajBan.getANULADO()) != 1)) {
                            //     if (!(tipo.equals("EF") && rs.getString(5).equals("10101") && Integer.parseInt(BeanMovCajBan.getANULADO()) != 1)) {
                            if (BeanMovCajBan.getANULADO().equals("0")) {

                                if (!Auxiliar.equals("98")) {
                                    bnCaja.setSumaDebe(saldoDebe);
                                    bnCaja.setSumaHaber(saldoHaber);

                                    bnCaja.setAgrupar(descripcionCuenta);
                                    bnCaja.setNomCuenta("SALDO INICIAL");
                                    //   bnCaja.setEntidad(descripcionCuenta);
                                    bnCaja.setNumCuenta("");
                                    bnCaja.setEntidad("");
                                    bnCaja.setFecha("");
                                    bnCaja.setNumResgistro("");
                                    bnCaja.setNumero(" ");
                                    bnCaja.setDenominacion("  ");
                                    bnCaja.setNumResgistro("");
                                    //   SaldoIncialx=

                                    //  } else {
                                    bnCaja.setAgrupar(descripcionCuenta);
                                    //   bnCaja.setEntidad(descripcionCuenta);
                                      String valorR=valorN.substring(4,6)+"-"+valorN.substring(6,valorN.length());
                                    bnCaja.setNumResgistro(valorR);
                                    bnCaja.setFecha(rs.getString(12));

                                    //   bnCaja.setNomCuenta(rs.getString(9)); // nombre de quiebre nombre
                                    //   bnCaja.setNumCuenta(rs.getString(5));  // numeroo de quierbre  de cuenta





                                    Nregistro = Nregistro + 1;

                                    param.add(OracleTypes.CURSOR);


                                    if (BeanMovCajBan.getTipoMov().equals("XX")) {
                                        String valor15 = rs.getString(15);
                                        bnCaja.setGlosa(valor15);

                                        bnCaja.setDenominacion("  ");
                                    } else {
                                        bnCaja.setGlosa(BeanMovCajBan.getGIRADO());
                                        bnCaja.setDenominacion(BeanMovCajBan.getGLOSA());

                                    }
                                    String valorN1 = rs.getString(3);
                                    String valor11 = rs.getString(2);
                                    String valor2 = rs.getString(3);
                                    String valor3 = rs.getString(4);
                                    String valor4 = rs.getString(6);
                                    String valor5 = rs.getString(7);
                                    String valor6 = rs.getString(8);
                                    String valor7 = rs.getString(9);
                                    String valor8 = rs.getString(10);
                                    String valor9 = rs.getString(11);
                                    String valor10 = rs.getString(15);

                                    if (valor11 == null) {
                                        valor11 = "";
                                    }
                                    if (valor2 == null) {
                                        valor2 = "";
                                    }
                                    if (valor3 == null) {
                                        valor3 = "";
                                    }
                                    if (valor4 == null) {
                                        valor4 = "";
                                    }
                                    if (valor5 == null) {
                                        valor5 = "";
                                    }
                                    if (valor6 == null) {
                                        valor6 = "";
                                    }
                                    if (valor7 == null) {
                                        valor7 = "";
                                    }
                                    if (valor8 == null) {
                                        valor8 = "";
                                    }
                                    if (valor9 == null) {
                                        valor9 = "";
                                    }
                                    if (valor10 == null) {
                                        valor10 = "";
                                    }



                                    glosa = valor11 + " " + valor2 + " " + valor3 + " " + valor4 + " " + valor5 + " " + valor6 + " " + valor7 + "  " + " " + valor8 + " " + valor9 + " " + valor10;

                                    if (BeanMovCajBan.getTipoMov().equals("XX")) {
                                        bnCaja.setGlosa(glosa);

                                    } else {
                                        bnCaja.setGlosa(bnCaja.getGlosa() + " " + bnCaja.getDescripcion());
                                    }
                                    //}



                                    BigDecimal debe = rs.getBigDecimal(13);
                                    BigDecimal haber = rs.getBigDecimal(14);

                                    // BeanBanco bntB = new BeanBanco();
                                    String tipoMob = "";
                                    String tipoMob1 = "";

                                    if (debe.compareTo(BigDecimal.ZERO) == 1) {
                                        //PLC  tipo
                                        tipoMob = "PLC";
                                        //buscarInRepositorioQ
                                        System.out.println(valorN + "debe" + debe + "haber" + haber);
                                        if (Auxiliar != null && valorN != null && repositorioQ != null) {
                                            bntB = buscarInRepositorioQ(repositorioQ, valorN, BigDecimal.ZERO, debe);
                                        }


                                        if (!rs.getString(2).equals("98")) {
                                            if (!bntB.getCuentaBanco().equals("")) {
                                                bnCaja.setNomCuenta(bntB.getCuentaBanco());//nombnre

                                                bnCaja.setNumCuenta(bntB.getNumCuenta());


//
                                            } else {

                                                String mov = buscarInRepositorioQ2(repositorioQ, valorN, debe, haber);
                                                int fin = mov.indexOf(";");
                                                bnCaja.setNumCuenta(mov.substring(0, fin));
                                                bnCaja.setNomCuenta(mov.substring(fin + 1, mov.length()));//nombnre



                                            }

                                        }

                                    } else {
                                        tipoMob = "I";
                                        tipoMob1 = "I";
                                        System.out.println(valorN + "debe" + debe + "haber" + haber);
                                        if (Auxiliar != null && valorN != null && repositorioQ != null) {
                                            bntB = buscarInRepositorioQ(repositorioQ, valorN, haber, BigDecimal.ZERO);
                                        }
                                        if (!rs.getString(2).equals("98")) {
                                            if (!bntB.getCuentaBanco().equals("")) {

                                                bnCaja.setNomCuenta(bntB.getCuentaBanco());//nombnre

                                                bnCaja.setNumCuenta(bntB.getNumCuenta());

                                            } else {

                                                String mov = buscarInRepositorioQ2(repositorioQ, valorN, debe, haber);
                                                int fin = mov.indexOf(";");
                                                bnCaja.setNumCuenta(mov.substring(0, fin));

                                                bnCaja.setNomCuenta(mov.substring(fin + 1, mov.length()));//nombnre




                                            }

                                        }
                                    }

                                    saldo = saldo.add(debe.subtract(haber));
                                    //     saldo = saldo.add(debe.subtract(haber));
                                    if (saldo.compareTo(BigDecimal.ZERO) == 1) { //saldo es mayor de 0 entonces 1
                                        //total = total.add(subTotal);
                                        saldoDebe = saldoDebe.add(saldo);
                                        bnCaja.setDeudor(saldo);
                                        bnCaja.setAcreedor(BigDecimal.ZERO);

                                    } else {

                                        //total = total.add(subTotal);

                                        BigDecimal valor1 = saldo.abs();
                                        saldoHaber = saldoDebe.add(valor1);
                                        bnCaja.setDeudor(BigDecimal.ZERO);
                                        bnCaja.setAcreedor(valor1);


                                    }

                                    bnCaja.setSaldo(saldo);
                                    bnCaja.setDebe(debe);
                                    bnCaja.setHaber(haber);
                                    dataCaja.addCaja(bnCaja);
                                }
                            }
                        }

                        //  }
                        // }

                        rs.close();
                        cst.close();
                        DaoEmpresas.objCnx.destroy();
                        // i ++
                        i = i + 1;
                    }
                }

                repositorioQ = null;
                repositorio = null;

                Map parameters = new HashMap();
                parameters.put("empresa", empresa.toUpperCase());
                parameters.put("ruc", ruc.toUpperCase());
                parameters.put("periodo", ano.toUpperCase());

                parameters.put("inicio", fechaI.toUpperCase());
                parameters.put("fin", fechaI.toUpperCase());
                parameters.put("cuentaBanco", descripcionCuenta.toUpperCase());
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptcaja = "";
                if (tipoR.equals("C")) {
                    rutarptcaja = "/com/contab/report/caja1.jasper";
                } else {
                    rutarptcaja = "/com/contab/report/caja2.jasper";
                }
                // String rutarptcaja = "/com/contab/report/caja2.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataCaja, rutarptcaja, "Caja");
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE DEL LIBRO CAJA EFECTIVO !!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE DEL LIBRO CAJA EFECTIVO ", "alert", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            } finally {
                rs.close();
                //  System.out.println();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
        }
    }

    public static void listarCajaCoriente(String empresa, String ruc, String compania, int inicioM, int finM, String descIMes, String descFMes, String ano, String cuentaBanco, String descripcionCuenta, String centro, String tipoR, File ruta, String tipoExpor) throws SQLException, Exception {

        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            try {

                ClassFecha mes = new ClassFecha();
                String fechaI = mes.Mes(finM);
                String fechaF = mes.Mes(finM);

                ArrayList<BeanBanco> arrayx = new ArrayList<BeanBanco>();
                ///-------------------------------------repositorio-------------------------------------------------------------- ///

                ArrayList<ArrayList<BeanMovimientoCajaBanco>> repositorio = new ArrayList<ArrayList<BeanMovimientoCajaBanco>>();
                repositorio = null;
                //if (result != null) {
                // repositorio = repositorio(compania, ano, fechaI);
                ////////////------------------------fin de repositorio--------------------------------------------///

                ///-------------------------------------repositorio Quiebre-------------------------------------------------------------- ///

                ArrayList<ArrayList<BeanBanco>> repositorioQ = new ArrayList<ArrayList<BeanBanco>>();
                repositorioQ = null;

                repositorioQ = repositorioQ(compania, ano, fechaI);
                ////////////------------------------fin de repositorio Quiebre--------------------------------------------///


                int i = 0;
                if (cuentaBanco.equals("0")) {


                    arrayx = LogicCuentaBanco.listaCuentaBanco(compania);

                } else {
                    BeanBanco obj = new BeanBanco();
                    obj.setNumCuenta(cuentaBanco);
                    obj.setCuentaBanco(descripcionCuenta);
                    obj.setCenter(centro);
                    arrayx.add(obj);

                }

                datasourceCaja dataCaja = new datasourceCaja();



                beanCaja objCuenta = new beanCaja();
                while (i < arrayx.size()) {

                    ArrayList param = new ArrayList();
                    cuentaBanco = arrayx.get(i).getNumCuenta();
                    descripcionCuenta = arrayx.get(i).getCuentaBanco();

                    String center = arrayx.get(i).getCenter();

                    param.add(OracleTypes.CURSOR);

                    param.add(fechaI);//mes de inicio
                    param.add(fechaF);//mes fin
                    param.add(ano); //ano
                    param.add(compania);//numero de la empresa o compa;ia
                    param.add(cuentaBanco);
                    ArrayList objetos = DaoCaja.consultaCajaCorriente(param);
                    rs = (ResultSet) objetos.get(0);
                    cst = (CallableStatement) objetos.get(1);
                    System.out.println(rs);
                    System.out.println(cst);
/// Periodo 1, Auxiliar 2, NroVouch 3, NumDoc 4, Cuenta 5,  CentroC 6
// Moneda  7, CodeAux 8, Cuentaaux 9, Tip_Doc 10, Num_Doc 11, Fecha 12, As Debe 13,
                    //Haber  14, Glosa 15, NomCuenta 16
                    // NroVouch 17 , NroDoc 18, Girado 19,Glosa 20 , Anulado  21

                    String glosa = "";
                    int Nregistro = 0;
                    BigDecimal saldo = BigDecimal.ZERO;
                    BigDecimal saldoN = BigDecimal.ZERO;
                    BigDecimal saldoDebe = BigDecimal.ZERO;
                    BigDecimal saldoHaber = BigDecimal.ZERO;
                    BigDecimal SaldoIncialx = BigDecimal.ZERO;
                    /// saldo inicial
                    String fechaSI = ano + "-" + mes.Mes(inicioM) + "-01";
                    String fechaSF = ano + "-" + mes.Mes(inicioM) + "-01";
                    java.sql.Date ini = java.sql.Date.valueOf(fechaSI);
                    java.sql.Date fin = java.sql.Date.valueOf(fechaSF);
                    ViewApplication.mensajeEstado.setText(null);
                    ViewApplication.mensajeEstado.setText("PROCESANDO CUENTA Nª: " + cuentaBanco + "---" + descripcionCuenta + " DEL PERIODO: " + fechaI + "/" + ano);

                    //  paramCa.add(OracleTypes.CURSOR);
                       int ExistenciaMov = Existencia_Movimiento_Caja(fechaI, fechaF, ano, compania, cuentaBanco, center);

                    objCuenta = CUENTA_NOMBRE_BANCO_CORRIENTA(compania, cuentaBanco, center);
                    System.out.println("cuenta" + cuentaBanco + "centro" + center + "ssddddddddddddddd");
                    beanCaja bnCajaM = new beanCaja();
                    bnCajaM.setAgrupar(descripcionCuenta);
                    bnCajaM.setNomCuenta("Saldo Inicial");
                    //   bnCaja.setEntidad(descripcionCuenta);
                    bnCajaM.setNumCuenta("");
                    bnCajaM.setGlosa("");
                    bnCajaM.setNumResgistro("");
                    bnCajaM.setEntidad(objCuenta.getEntidad());
                    bnCajaM.setNumeroCuenta(objCuenta.getNumeroCuenta());
                    bnCajaM.setFecha("");
                    bnCajaM.setPago("");
                    bnCajaM.setNumResgistro("");
                    bnCajaM.setNumero(" ");
                    bnCajaM.setDenominacion("  ");
                    bnCajaM.setNumResgistro("");
                    bnCajaM.setAgrupar(cuentaBanco);
                    SaldoIncialx = saldoInicial(compania, cuentaBanco.trim(), center.trim(), ano, Integer.parseInt(fechaI));
                    //  System.out.println("saldooooo  cuenta" + cuentaBanco + " center " + center + "  " + SaldoIncialx);
                    if (SaldoIncialx.compareTo(BigDecimal.ZERO) == 1) { //saldo es mayor de 0 entonces 1
                        bnCajaM.setDebe(SaldoIncialx.abs());
                        saldoDebe = SaldoIncialx.abs();
                        saldoHaber = BigDecimal.ZERO;
                        bnCajaM.setHaber(BigDecimal.ZERO);
                    } else {
                        saldoDebe = BigDecimal.ZERO;

                        saldoHaber = SaldoIncialx.abs();
                        bnCajaM.setDebe(BigDecimal.ZERO);
                        bnCajaM.setHaber(SaldoIncialx.abs());
                    }
                    saldoN = saldo.add(saldoDebe.subtract(saldoHaber));
                    bnCajaM.setSumaDebe(saldoDebe.abs());
                    bnCajaM.setSumaHaber(saldoHaber.abs());
                    if (!descripcionCuenta.equals("Todos") && ExistenciaMov > 0) {
                        dataCaja.addCaja(bnCajaM);
                    }


                    if (rs != null && cst != null) {
                        while (rs.next()) {
                            ArrayList paramCa = new ArrayList();
                            beanCaja bnCaja = new beanCaja();

                            String num = rs.getString(3);
                            String EntidadFinanciera = rs.getString(7);
                            System.out.print(EntidadFinanciera);


                            BigDecimal debe = rs.getBigDecimal(13);
                            BigDecimal haber = rs.getBigDecimal(14);


                            String valorVOU = rs.getString(11);
                            String valorN = rs.getString(3);
                            if (valorVOU == null) {
                                valorVOU = "0";
                            }
                            if (valorN == null) {
                                valorN = "0";  //numero de voucher
                            }
                            // String numeroV = rs.getString(10)+ rs.getString(11)+rs.getString(8)+rs.getString(9)+rs.getString(6)+ "-" + rs.getString(2) + "-" + rs.getString(3) + "-" + rs.getString(4);

                            //     BigDecimal SaldoIncial = LogicCaja.saldoInicial(cuentaBanco, ini, fin, compania);


                            ///////////-------------------saldo inicial--------------------------------




                            //      System.out.println("saldooooo  cuenta" + cuentaBanco + " center " + center + "  " + SaldoIncialx);

                            paramCa.add(OracleTypes.CURSOR);
                            paramCa.add(ano);
                            paramCa.add(compania);
                            paramCa.add(valorN);
                            paramCa.add(valorVOU);
                            ///////-------------------haaaaaaaa-----------------------------------------
                            BeanMovimientoCajaBanco BeanMovCajBan = DaoMovimientoCajaBanco.consultaMovimientoCajaBancoCorriente(paramCa);
                            BeanMovimientoCajaBanco BeanMovCajBanD = DaoMovimientoCajaBanco.consultaMovimientoCajaBanco2(paramCa);

                            // BeanMovimientoCajaBanco BeanMovCajBan = buscarInRepositorio2(repositorio, valorN, valorVOU);
                            //   CuentaBanco BeanQuiebre = DaoMovimientoCajaBanco.(paramCa);

                            // LogicCuentaBanco lcb=new LogicCuentaBanco();
                            String Auxiliar = rs.getString(2);
                            BeanBanco bntB = new BeanBanco();
                            //   System.out.println(rs.getString(15) + "-----" + rs.getString(18));
                            String valor11 = rs.getString(2);
                            //String valor2 = rs.getString(3);
                            String valor3 = rs.getString(4);
                            String valor4 = rs.getString(6);
                            String valor5 = rs.getString(7);
                            String valor6 = rs.getString(8);
                            String valor7 = rs.getString(9);
                            String valor8 = rs.getString(10);
                            String valor9 = rs.getString(11);
                            String valor10 = rs.getString(15);

                            if (valor11 == null) {
                                valor11 = "";
                            }

                            if (valor3 == null) {
                                valor3 = "";
                            }
                            if (valor4 == null) {
                                valor4 = "";
                            }
                            if (valor5 == null) {
                                valor5 = "";
                            }
                            if (valor6 == null) {
                                valor6 = "";
                            }
                            if (valor7 == null) {
                                valor7 = "";
                            }
                            if (valor8 == null) {
                                valor8 = "";
                            }
                            if (valor9 == null) {
                                valor9 = "";
                            }
                            if (valor10 == null) {
                                valor10 = "";
                            }



                            //glosa = valor11 + " " + valor3 + " " + valor4 + " " + valor5 + " " + valor6;
                            glosa=BeanMovCajBanD.getGLOSA();
                               String numeroTT = BeanMovCajBanD.getNumero();
                              //String numeroTT = rs.getString(7) + " " + rs.getString(11) + " " + rs.getString(17);
                            System.out.println("glosa" + glosa);

                            //   bntB = LogicCuentaBanco.Cuenta_Quiebre(compania, Auxiliar, valorN);
                            //  bntB = buscarInRepositorioQ(repositorioQ, Auxiliar, valorN, valorN);

                            if (debe.compareTo(BigDecimal.ZERO) == 1 || haber.compareTo(BigDecimal.ZERO) == 1) {
                                //      if (BeanMovCajBan == null || BeanMovCajBan.getANULADO().equals("0")  ) {
                                // if (BeanMovCajBan.getANULADO().equals("0") || BeanMovCajBan.getANULADO().equals("null")) {
                                //llena el libro caja banco
                                if (BeanMovCajBan.getANULADO().equals("0")) {
                                    if (!rs.getString(2).equals("98")) {


                                        //  } else {
                                        bnCaja.setAgrupar(cuentaBanco);
                                        bnCaja.setFecha(rs.getString(12));

                                        bnCaja.setEntidad(rs.getString(18));
                                        bnCaja.setNumeroCuenta(rs.getString(19));
                                        String valorR=valorN.substring(4,6)+"-"+valorN.substring(6,valorN.length());
                                        // valorN=valorN.substring(4,6)+"-"+valorN.substring(6,valorN.length())
                                        bnCaja.setNumResgistro(valorR);
                                        //  bnCaja.setAgrupar(rs.getString(5));
                                        // String valor15 = rs.getString(15);
                                     
                                        if (BeanMovCajBan.getTipoMov().equals("XX")) {

                                            bnCaja.setGlosa(glosa);

                                            if (numeroTT == null) {
                                                numeroTT = "";
                                            }

                                            bnCaja.setNumero(numeroTT);
                                            bnCaja.setDenominacion(rs.getString(16));
                                        } else {
                                            bnCaja.setGlosa(glosa);
                                            bnCaja.setDenominacion(BeanMovCajBan.getGLOSA());
                                            bnCaja.setNumero(numeroTT);

                                        }
                                        String codigoSun = rs.getString(10);
                                        if (codigoSun == null) {
                                            codigoSun = "";
                                        } else {
                                            codigoSun = rs.getString(10).trim();
                                        }

                                        ClassCuentaSunat codigo = new ClassCuentaSunat();
                                        bnCaja.setPago(codigo.Sunat(codigoSun));

                                        //    }
                                        //    bnCaja.setGlosa(rs.getString(20));

                                        String tipoMob = "";

                                        saldo = saldo.add(debe.subtract(haber));
                                        Nregistro = Nregistro + 1;
                                        if (saldo.compareTo(BigDecimal.ZERO) == 1) { //saldo es mayor de 0 entonces 1
                                            //total = total.add(subTotal);
                                            tipoMob = "PLC";
                                            bntB = buscarInRepositorioQ(repositorioQ, valorN, BigDecimal.ZERO, debe);
                                            // bntB = buscarInRepositorioQ(repositorioQ, Auxiliar, valorN, tipoMob);

                                            if (!bntB.getCuentaBanco().equals("")) {
                                                bnCaja.setNomCuenta(bntB.getCuentaBanco());//nombnre
                                                bnCaja.setNumCuenta(bntB.getNumCuenta()); // numero de cuenta de quiebre
                                            } else {

                                                String mov = buscarInRepositorioQ2(repositorioQ, valorN, debe, haber);
                                                int fin1 = mov.indexOf(";");

                                                bnCaja.setNomCuenta(mov.substring(fin1 + 1, mov.length()));//nombnre

                                                bnCaja.setNumCuenta(mov.substring(0, fin1));
                                            }
                                            //   bntB = buscarInRepositorioQ(repositorioQ, Auxiliar, valorN, valorN);

                                            /*
                                            String mov = buscarInRepositorioQ2(repositorioQ, valorN, debe, haber);
                                            int fin = mov.indexOf(";");

                                            bnCaja.setNomCuenta(mov.substring(fin + 1, mov.length()));//nombnre

                                            bnCaja.setNumCuenta(mov.substring(0, fin));

                                             */
                                            bnCaja.setDeudor(saldo);
                                            bnCaja.setAcreedor(BigDecimal.ZERO);
                                        } else {
                                            tipoMob = "I";
                                            bntB = buscarInRepositorioQ(repositorioQ, valorN, haber, BigDecimal.ZERO);
                                            //  bntB = buscarInRepositorioQ(repositorioQ, Auxiliar, valorN, tipoMob);
                                            if (!bntB.getCuentaBanco().equals("")) {
                                                bnCaja.setNomCuenta(bntB.getCuentaBanco());//nombnre
                                                bnCaja.setNumCuenta(bntB.getNumCuenta()); // numero de cuenta de quiebre
                                            } else {
                                                String mov = buscarInRepositorioQ2(repositorioQ, valorN, debe, haber);
                                                int fin1 = mov.indexOf(";");

                                                bnCaja.setNomCuenta(mov.substring(fin1 + 1, mov.length()));//nombnre

                                                bnCaja.setNumCuenta(mov.substring(0, fin1));
                                            }
                                            //total = total.add(subTotal);
                                            /*
                                            String mov = buscarInRepositorioQ2(repositorioQ, valorN, debe, haber);
                                            int fin = mov.indexOf(";");

                                            bnCaja.setNomCuenta(mov.substring(fin + 1, mov.length()));//nombnre

                                            bnCaja.setNumCuenta(mov.substring(0, fin));
                                             */

                                            BigDecimal valor1 = saldo.abs();
                                            bnCaja.setDeudor(BigDecimal.ZERO);
                                            bnCaja.setAcreedor(valor1);

                                        }
                                        bnCaja.setSaldo(saldo);
                                        bnCaja.setDebe(debe);
                                        bnCaja.setHaber(haber);
                                        if (cuentaBanco != null) {
                                            dataCaja.addCaja(bnCaja);
                                        }

                                    }
                                }
                            }
                            //  }
                            // }
                            //  paramCa=null;
                        }
                        rs.close();
                        cst.close();
                    }

                    i = i + 1;
                }
                Map parameters = new HashMap();
                parameters.put("empresa", empresa.toUpperCase());
                parameters.put("ruc", ruc.toUpperCase());
                parameters.put("periodo", ano);

                parameters.put("inicio", fechaI.toUpperCase());
                parameters.put("fin", fechaI.toUpperCase());
                parameters.put("cuentaBanco", descripcionCuenta.toUpperCase());
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                // parameters.put("SaldoInicial", SaldoIncial);
                //  parameters.put("SaldoFinal", saldo);

                //String f = "C://cajaCorriente.pdf";
                //File t = new File(f);
                String rutarptcaja = "";
                if (tipoR.equals("C")) {
                    rutarptcaja = "/com/contab/report/caja-banco.jasper";
                } else {
                    rutarptcaja = "/com/contab/report/caja-banco2.jasper";
                }


                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataCaja, rutarptcaja, "Caja");

                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE DEL LIBRO CAJA CORRIENTE !!");
                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE DEL LIBRO CAJA CORRIENTE ", "alert", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                System.err.println(ex.getCause());
                System.err.println(ex.getMessage());
                System.err.println(ex.getStackTrace());
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

    public static BigDecimal saldoInicial(String codemp, String cuenta, String center, String ano, int MesTipo) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        BigDecimal valor = BigDecimal.ZERO;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(codemp);
            param.add(cuenta);
            param.add(center);
            param.add(ano);
            param.add(MesTipo);
            ArrayList objetos = (ArrayList) DaoCaja.consultaSaldoInicial(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    valor = rs.getBigDecimal(5);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return valor;
        } catch (SQLException ex) {
            return null;
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }

    }

    public static int Existencia_Movimiento_Caja(String fechaI, String fechaF, String ano, String compania, String cuentaBanco, String center) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        int valor = 0;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(fechaI);//mes de inicio
            param.add(fechaF);//mes fin
            param.add(ano); //ano
            param.add(compania); //numero de la empresa o compa;ia
            param.add(cuentaBanco);
            param.add(center);
            ArrayList objetos = (ArrayList) DaoCaja.Existencia_Movimiento_Caja(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    valor = rs.getInt(1);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return valor;
        } catch (SQLException ex) {
            return 0;
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }

    }

    public static BigDecimal saldoInicialEfectivo(String cuenta, java.sql.Date fechaini, java.sql.Date fechafin, String codemp, String center) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        BigDecimal valor = BigDecimal.ZERO;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(cuenta);
            param.add(fechaini);
            param.add(fechafin);
            param.add(codemp);
            param.add(center);
            ArrayList objetos = (ArrayList) DaoCaja.consultaSaldoInicialEfectivo(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    valor = rs.getBigDecimal(3);
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return valor;
        } catch (SQLException ex) {
            return null;
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }

    }

    public static beanCaja CUENTA_NOMBRE_BANCO_CORRIENTA(String compania, String cuenta, String center) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        String valor = "";
        beanCaja obj = new beanCaja();
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(compania);
            param.add(cuenta);
            param.add(center);
            ArrayList objetos = (ArrayList) DaoCaja.CUENTA_NOMBRE_BANCO_CORRIENTA(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    valor = rs.getString(8);
                    System.out.println(valor + "banco oooooooooooooooooooooooooooCorrienteeeeeeeeeeeeeeeeeeeee");
                    obj.setEntidad(rs.getString(8));
                    obj.setNumeroCuenta(rs.getString(3));
                }
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return obj;
        } catch (SQLException ex) {
            return null;
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }

    }

    public static void listaDatelleCuenta10(String empresa, String ruc, String compania, String ano, String tipo, File ruta, String tipoExpor) throws SQLException, Exception {

        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            Conexion cnx = null;
            try {

                datasourceCaja datalleCaja = new datasourceCaja();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                param.add(ano); //ano
                param.add(tipo); //ano
                ArrayList objetos = new ArrayList();
                objetos = DaoCaja.DetalleCuenta10(param);
                rs = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                // cnx = (Conexion) objetos.get(2);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO DETALLE DE CUENTA 10 Nª: " + empresa + " DEL PERIODO: " + ano);


                //    if (rs != null && cst != null && cnx != null) {
                while (rs.next()) {

                    beanCaja bnCaja = new beanCaja();
                    BeanBanco bnbanco = new BeanBanco();
                    String Numerocuenta = rs.getString(1);
                    System.out.println(Numerocuenta + "as");

                    String denominacion = rs.getString(2);
                    String tipoMoneda = rs.getString(4);
                    BigDecimal saldo = rs.getBigDecimal(7);

                    if (!BigDecimal.ZERO.equals(saldo)) {
                        String cuenta15 = "";
                        //if (rs.getString(5) == null) {
                        //cuenta15 = cuenta(denominacion);
                        //s

                        // } else {
                        cuenta15 = rs.getString(5);
                        ///   if(cuenta15.equals("")){
                        //  cuenta15="XXX";
                        //  }
                        //}

                        bnbanco = LogicCuentaBanco.Cuenta_Banco_Sunat(compania, cuenta15);
                        if (bnbanco.getCodigoCuentaSunat().equals("0")) {
                            bnCaja.setEntidad(" ");  //numero sunat tabla
                            bnCaja.setNumCuenta(" ");
                        } else {

                            bnCaja.setEntidad(bnbanco.getCodigoCuentaSunat());  //numero sunat tabla
                            bnCaja.setNumCuenta(cuenta15);

                        }
                        // nmumero de cuenta del banco

                        bnCaja.setNumResgistro(Numerocuenta);
                        bnCaja.setDenominacion(denominacion);

                        BigDecimal Acreedor = BigDecimal.ZERO;
                        BigDecimal Deudor = BigDecimal.ZERO;
                        if (saldo.compareTo(BigDecimal.ZERO) == 1) {

                            Acreedor = saldo.abs();
                        } else {

                            Deudor = saldo.abs();
                        }
                        if (tipo.endsWith("S/.")) {
                            tipoMoneda = "01";
                        } else {
                            tipoMoneda = "02";
                        }
                        bnCaja.setTipoMoneda(tipoMoneda);
                        bnCaja.setAcreedor(Acreedor);
                        bnCaja.setDeudor(Deudor);

                        datalleCaja.addCaja(bnCaja);

                    }

                }
                // }



                Map parameters = new HashMap();
                parameters.put("P_RAZON", empresa);
                parameters.put("P_RUC", ruc);
                parameters.put("P_PERIODO", ano);
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptcaja = "/com/contab/report/detalleCuenta10.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, datalleCaja, rutarptcaja);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE DEL LIBRO DETALLE DE CUENTA 10 !!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE DEL LIBRO DETALLE DE CUENTA 10 ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
                rs.close();
                cst.close();
                // cnx.destroy();

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

    public static ArrayList<ArrayList<BeanMovimientoCajaBanco>> repositorio(String CodCompania, String anno, String mes) throws SQLException {
        ArrayList<ArrayList<BeanMovimientoCajaBanco>> repositorio = new ArrayList<ArrayList<BeanMovimientoCajaBanco>>();

        ResultSet rs = null;
        CallableStatement cst = null;

        int val = 30000;
        ArrayList<BeanMovimientoCajaBanco> array;
        int contador = 0;
        int cont = 2;
        try {

            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(CodCompania);
            param.add(anno);
            param.add(mes);

            ArrayList objetos = DaoMovimientoCajaBanco.consultaMovimientoCajaBancoN(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);

            if (rs != null) {
                System.out.println("repositorio 1");
                array = new ArrayList<BeanMovimientoCajaBanco>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        System.out.println("repositorio " + cont);
                        array = new ArrayList<BeanMovimientoCajaBanco>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanMovimientoCajaBanco obj = new BeanMovimientoCajaBanco();
                    obj.setNROVOUCH(rs.getString(1));
                    obj.setNRODOC(rs.getString(2));
                    obj.setGLOSA(rs.getString(3));
                    obj.setGIRADO(rs.getString(4));
                    obj.setANULADO(rs.getString(5));
                    obj.setTipoMov(rs.getString(6));
                    obj.setSECUENCIA(rs.getString(8));
                    obj.setSECUENCIA1(rs.getString(9));

                    array.add(obj);
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

    public static BeanMovimientoCajaBanco buscarInRepositorio2(ArrayList<ArrayList<BeanMovimientoCajaBanco>> repor, String NROVOUCH, String secuencia) {
        BeanMovimientoCajaBanco bean = new BeanMovimientoCajaBanco();
        BeanMovimientoCajaBanco beanaux = new BeanMovimientoCajaBanco();
        ArrayList<BeanMovimientoCajaBanco> array;
        String valor = "0";
        if (NROVOUCH == null) {
            NROVOUCH = "";
        }
        if (secuencia == null) {
            secuencia = "";
        }

        //   System.out.println(" " + NROVOUCH + " anular");
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanMovimientoCajaBanco>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanMovimientoCajaBanco) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getNROVOUCH().equals(NROVOUCH) && (beanaux.getSECUENCIA().equals(secuencia) || beanaux.getSECUENCIA1().equals(secuencia))) {
                    //   System.out.println("eliminado repositorio 2 " + codigo);
                    //  array.remove(beanaux);
                    valor = "1";
                    bean = beanaux;
                    break;
                }

            }


        }
        if (valor.equals("0")) {

            bean.setANULADO("0");
            bean.setTipoMov("XX");
            //   beanaux.setANULADO("0");
        }
        return bean;
    }

    //BeanBanco
    public static ArrayList<ArrayList<BeanBanco>> repositorioQ(String CodCompania, String ano, String mes) throws SQLException {
        ArrayList<ArrayList<BeanBanco>> repositorio = new ArrayList<ArrayList<BeanBanco>>();

        ResultSet rs = null;
        CallableStatement cst = null;

        int val = 30000;
        ArrayList<BeanBanco> array;
        int contador = 0;
        int cont = 2;
        try {

            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(CodCompania);
            param.add(ano);
            param.add(mes);


            ArrayList objetos = DaoMovimientoCajaBanco.Cuenta_Quiebre_EfectivoN(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);

            if (rs != null) {
                //System.out.println("repositorio Q");
                array = new ArrayList<BeanBanco>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        //   System.out.println("repositorio Q" + cont);
                        array = new ArrayList<BeanBanco>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanBanco obj = new BeanBanco();
                    //getVaucher   getDeudor  getCuentaBanco
                    obj.setVaucher(rs.getString(1));
                    obj.setNumCuenta(rs.getString(2));
                    obj.setCuentaBanco(rs.getString(3));
                    obj.setDeudor(rs.getBigDecimal(4));
                    obj.setAcreedor(rs.getBigDecimal(5));
                    //    System.out.println("repositorio Q" + rs.getBigDecimal(7)+"----"+rs.getBigDecimal(8));

                    array.add(obj);
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

    public static BeanBanco buscarInRepositorioQ(ArrayList<ArrayList<BeanBanco>> repor, String vaucher, BigDecimal deudor, BigDecimal acreedor) {

        //  AND sysadm.jrnfile.C_JRNTYPE =Auxiliar
//AND sysadm.jrnfile.C_JRNNUMBER = vaucher
//AND sysadm.jrnfile.N_NUM_SERIE=tipo
        BeanBanco bean = new BeanBanco();
        BeanBanco beanaux = new BeanBanco();
        ArrayList<BeanBanco> array;
        //  System.out.println(" " + Auxiliar + "-- QUIEBRE--- " + vaucher + " ---" + tipo);

        //System.out.print(vaucher + "--" + deudor+"-----"+acreedor);
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanBanco>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanBanco) iterar.next();
                // System.out.print(vaucher + "--" + deudor+"-----"+acreedor);
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getVaucher().equals(vaucher) && beanaux.getDeudor().equals(deudor) && beanaux.getAcreedor().equals(acreedor)) {
                    //   System.out.println("eliminado repositorio 2 " + codigo);

                    System.out.println(" " + vaucher + " anular");
                    array.remove(beanaux);
                    bean = beanaux;
                    break;
                }

            }
        }

        if (bean == null) {
            // getCuentaBanco
            bean.setCuentaBanco(" ");
            bean.setNumCuenta(" ");


        }



        return bean;
    }

    public static String buscarInRepositorioQ2(ArrayList<ArrayList<BeanBanco>> repor, String vaucher, BigDecimal deudor, BigDecimal acreedor) {

        //  AND sysadm.jrnfile.C_JRNTYPE =Auxiliar
//AND sysadm.jrnfile.C_JRNNUMBER = vaucher
//AND sysadm.jrnfile.N_NUM_SERIE=tipo
        BeanBanco bean = new BeanBanco();
        BeanBanco beanaux = new BeanBanco();
        ArrayList<BeanBanco> array;
        //  System.out.println(" " + Auxiliar + "-- QUIEBRE--- " + vaucher + " ---" + tipo);

        //System.out.print(vaucher + "--" + deudor+"-----"+acreedor);
        String valor = "";
        String valor1 = "";
        String valor3 = "";
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanBanco>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanBanco) iterar.next();

                if (beanaux.getVaucher().equals(vaucher) && (!beanaux.getDeudor().equals(deudor) || !beanaux.getAcreedor().equals(acreedor))) {
                    //   System.out.println("eliminado repositorio 2 " + codigo);

                    System.out.println(" " + vaucher + "-------- anular------------");
                    //array.remove(beanaux);
                    valor = valor + beanaux.getNumCuenta() + "/";  //numero de cuenta
                    valor1 = valor1 + beanaux.getCuentaBanco() + "/";  ///nombre
                    // bean = beanaux;
                    continue;
                }


            }



        }
        valor3 = valor + ";" + valor1;






        return valor3;
    }

    public static String cuenta(String cuenta) {

        String Mensaje = "";
        if (cuenta.trim().equals("CTA.AH. 0200002200 BCO.CONT.ME.")) {
            Mensaje = "0279-0200002200";


        }

        if (cuenta.trim().equals("CTA.CTE.305-1124247-1-47 M.E.- GENÉRICO")) {
            Mensaje = "305-1124247-1-47";


        }

        if (cuenta.trim().equals("CTA.CTE.305-1124247-1-47 M.E.- GENÉRICO")) {
            Mensaje = "305-1124247-1-47";


        }
        if (cuenta.trim().equals("CTA.CTE. BCO. CONTINENTAL 0011-0279-0200002197 M.E")) {
            Mensaje = "279-0200002197";


        }
        if (cuenta.trim().equals("CTA.CTE. BCO.CONTI. 0011-0279-0100013744-76 ME")) {
            Mensaje = "0011-0279-0100013744-76";


        }
        if (cuenta.trim().equals("BANCO CREDITO CTA.CTE. 305-1730749-1-29 ME")) {
            Mensaje = "305-1730749-1-29";


        }
        if (cuenta.trim().equals("CTA. AHORROS INTERBANK 700-3020014297 (ME)")) {
            Mensaje = "700-3020014297";


        }
        if (cuenta.trim().equals("CTA. AHORROS INTERBANK  700-3020014297 (ME)")) {
            Mensaje = "700-3020014297";


        }

        if (cuenta.trim().equals("SOCTIABANK 000-3328685 (ME)")) {
            Mensaje = "000-3328685";


        }

        if (cuenta.trim().equals("BCO.CONTINENTAL CTA.CTE:285-0100042283 ME.")) {
            Mensaje = "285-0100042283";


        }

        if (cuenta.trim().equals("BCO DE CREDITO CTA.CTE. 570-1454461-1-09 (ME) - GENÉRICO")) {
            Mensaje = "570-1454461-1-09";


        }

        if (cuenta.trim().equals("BCO DE CREDITO CTA.CTE. 305-1428296-1-49  ME  - -")) {
            Mensaje = "570-1454461-1-09";


        }

        if (cuenta.trim().equals("FONDOS MUTUOS  0011-0287-07-8003002635  (ME) - GENÉRICO")) {
            Mensaje = "0011-0287-07-8003002635";


        }
        if (cuenta.trim().equals("INTER AHOR 700-3020014424 (ME)")) {
            Mensaje = "0011-0287-07-8003002635";


        }

        if (cuenta.trim().equals("BCO INTERBANK 700-3028892870 (ME) - GENÉRICO")) {
            Mensaje = "700-3028892870";


        }
        if (cuenta.trim().equals("CTA AH. 279-0200003738 BCO CONT. M.E")) {
            Mensaje = "279-0200003738";


        }
        if (cuenta.trim().equals("F.M  279-8003003735 (ME) - GENÉRICO")) {
            Mensaje = "279-8003003735";


        }

        if (cuenta.trim().equals("CTA CTE Nº 550-1446268-0-21 (MN) BEBIDAS")) {
            Mensaje = "550-1446268-0-21";


        }
        if (cuenta.length() > 27) {
            System.out.println("asd----" + cuenta.substring(0, 27));

            if (cuenta.substring(0, 27).equals("CTA CTE Nº 550-1446268-0-21")) {
                Mensaje = "550-1446268-0-21";


            }
            if (cuenta.substring(0, 27).equals("CTA.CTE.305-1124247-1-47 M.")) {
                Mensaje = "305-1124247-1-47";


            }

        }

        if (cuenta.trim().equals("BCP CTA CTE 305-1730749-1-29 (ME)")) {
            Mensaje = "305-1730749-1-29";


        }


        if (cuenta.trim().equals("BBVA 0279-0100013744-76 (ME)")) {
            Mensaje = "0279-0100013744-76";


        }
        if (cuenta.trim().equals("BCO INTERB. 700-3020014297 (ME)")) {
            Mensaje = "700-3020014297";


        }
        if (cuenta.trim().equals("BCO SCOTIAB CTA CTE .000-3328685 (ME)")) {
            Mensaje = "000-3328685";


        }
        if (cuenta.trim().equals("CTA AHORROS 700-3028892870 (ME) ABARROTES - GENÉRI")) {
            Mensaje = "700-3028892870";


        }

        if (cuenta.trim().equals("CTA AHORROS 700-3028892870 (ME) ABARROTES - GENÉRI")) {
            Mensaje = "700-3028892870";


        }

        if (cuenta.trim().equals("BCO INTERB.CTA CTE 700-3013811840 (ME)")) {
            Mensaje = "700-3013811840";


        }

        if (cuenta.trim().equals("BBVA 0279-0100013744-76 (ME)")) {
            Mensaje = "0279-0100013744-76";


        }


        if (cuenta.trim().equals("BBVA AHORR. 0279-0200002200 (ME)")) {
            Mensaje = "0279-0200002200";


        }
        if (cuenta.trim().equals("BCO INTERB.CTA CTE 700-3013811840 (ME)")) {
            Mensaje = "700-3013811840";


        }

        if (cuenta.trim().equals("BCP CTA CTE 305-1124247-1-47 (ME)")) {
            Mensaje = "700-3013811840";


        }

        if (cuenta.trim().equals("BCO DE CREDITO CTA.CTE. 305-1428296-1-49 ME - GENÉRICO")) {
            Mensaje = "305-1428296-1-49";


        }
        if (cuenta.trim().equals("INTERB.AHOR CTA 700-301381184-0 (ME")) {
            Mensaje = "700-301381184";


        }
        if (cuenta.trim().equals("CTA.CTE.305-1124247-1-47 M.E.- GENÉRICO")) {
            Mensaje = "305-1124247-1-47";


        }



        /*CTA.CTE.305-1124247-1-47 M.E.- GENÉRICO
        CTA CTE Nº 550-1446268-0-21 (MN) BEBIDAS
        BCP  CTA CTE 305-1156490-0-24 (MN)
        BBVA 0279-0100013744-76 (ME)
        CMAC PIURA 210-01-6276330 (MN)
        BCO DE CREDITO CTA.CTE. 570-1454461-1-09 (ME)  - GENÉRICO
        BCO.CONTINENTAL CTA.CTE:285-0100042283 ME. - GENÉRICO
        
        BCO SCOTIABANK CTA CTE 000-8122105 - GENÉRICO
        INTERBANK  AHOR 700-3020014424 (ME) - GENÉRICO
        INTERB.AHOR CTA 700-301381184-0 (ME)
        CTA CTE Nº 550-1446268-0-21 (MN) BEBIDAS*/
        //CTA CTE Nº 550-1446268-0-21 (MN) BEBIDAS
        //CTA.CTE.305-1124247-1-47 M.E.- GENÉRICO

        return Mensaje;

    }
}
