/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanCuenta;
import com.contab.bean.BeanCuenta41;
import com.contab.bean.BeanEmpleado;
import com.contab.dao.Conexion;
import com.contab.dao.DaoCuenta;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoCuenta41;
import com.contab.dao.DaoEmpleado;
import com.contab.datasource.DatasourceCuenta41;
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
public class LogicCuenta41 {

    public static void listaSaldoCuenta41(String empresa, String ruc, String compania, String ano, File ruta, String tipoExpor) throws SQLException, Exception {

        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            Conexion cnx = null;
            try {

                DatasourceCuenta41 dataCuenta41 = new DatasourceCuenta41();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                param.add(ano); //ano
                ArrayList objetos = new ArrayList();
                objetos = DaoCuenta41.consultaCuenta41(param);
                rs = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                cnx = (Conexion) objetos.get(2);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO  CUENTA 41 Nª: " + empresa + " DEL PERIODO: " + ano);
                // repositorio

                ArrayList<ArrayList<BeanCuenta41>> repositorio = new ArrayList<ArrayList<BeanCuenta41>>();
                repositorio = null;
                repositorio = repositorio(compania, ano);
                ///

                ArrayList<ArrayList<BeanCuenta>> repositorioCuenta = new ArrayList<ArrayList<BeanCuenta>>();
                repositorioCuenta = null;
                repositorioCuenta = repositorioCuenta(compania);

                // listsr de empleado

                ArrayList<ArrayList<BeanEmpleado>> repositorioEmpleado = new ArrayList<ArrayList<BeanEmpleado>>();
                repositorioEmpleado = null;
                repositorioEmpleado = repositorioEmpleado(compania);

                BeanCuenta41 bncuenta41 = new BeanCuenta41();

                BeanCuenta bncuenta = new BeanCuenta();

                BeanEmpleado bnEmpleado = new BeanEmpleado();

                if (rs != null && cst != null && cnx != null) {

                    String tabla2 = "";

                    while (rs.next()) {

                        BeanCuenta41 bnCuenta41 = new BeanCuenta41();
                        ViewApplication.mensajeEstado.setText("PROCESANDO  CUENTA  41 Nª: " + empresa + " DEL PERIODO: " + ano + " numero" + rs.getString(1));

                        String c_auxaccount = rs.getString(1);  // codigo del empleado
                        //  String tipoDocumento = rs.getString(3); //tipo de documento
                        String Cuenta = rs.getString(5);  // cuenta
                        String Center = rs.getString(6);  // center de cuenta

                        if (c_auxaccount != null && Cuenta != null && Center != null) {
                            bncuenta41 = buscarInRepositorio(repositorio, c_auxaccount, Cuenta, Center);

                        }

                        if (Cuenta != null && Center != null) {
                            bncuenta = buscarInRepositorioCuenta(repositorioCuenta, Cuenta, Center);

                        }



                        if (c_auxaccount != null) {
                            bnEmpleado = buscarInRepositorioEmpleado(repositorioEmpleado, c_auxaccount.trim());
                        }

                        String apellidosNombre = rs.getString(2);
                          tabla2 = "";
                        if(bnEmpleado.getRuc()!=null){

                             System.out.println("repositorio Empleado" + bnEmpleado.getRuc());
                        if (!bnEmpleado.getRuc().equals("")) {

                            if (bnEmpleado.getRuc().length() >= 8) {
                                if (bnEmpleado.getRuc().length() == 8) {

                                    tabla2 = "01";
                                } else {
                                    tabla2 = "06";
                                }
                            }
                        }
                       

                        }




                        BigDecimal monto = rs.getBigDecimal(6);

                        bnCuenta41.setCodigo(Cuenta);
                        bnCuenta41.setDenominacion(bncuenta.getDenominacion());
                        bnCuenta41.setCodigo1(c_auxaccount.trim());
                        bnCuenta41.setApellidosNombre(apellidosNombre);

                        bnCuenta41.setTabla2(tabla2);
                        bnCuenta41.setNumero(bnEmpleado.getRuc());
                        bnCuenta41.setSaldoFinal(bncuenta41.getSaldoFinal());
                        
                       if(bnCuenta41.getSaldoFinal().compareTo(BigDecimal.ZERO) != 0 ){
                           dataCuenta41.addBalance(bnCuenta41);

                       
                       } 

                       
                        //}



                    }
                }
                //  }


                Map parameters = new HashMap();
                parameters.put("EJERCICIO", ano);
                parameters.put("P_RUC", ruc);
                parameters.put("P_RAZON", empresa);
                  parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptcaja = "/com/contab/report/cuenta41.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataCuenta41, rutarptcaja);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE CUENTA 41!!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE CUENTA 41", "ALERT", JOptionPane.INFORMATION_MESSAGE);
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

    public static ArrayList<ArrayList<BeanCuenta41>> repositorio(String CodCompania, String anno) throws SQLException {
        ArrayList<ArrayList<BeanCuenta41>> repositorio = new ArrayList<ArrayList<BeanCuenta41>>();

        ResultSet rs = null;
        CallableStatement cst = null;

        int val = 30000;
        ArrayList<BeanCuenta41> array;
        int contador = 0;
        int cont = 2;
        try {

            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(CodCompania);

            param.add(anno);

            ArrayList objetos = DaoCuenta41.MontosCuenta41(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);

            if (rs != null) {
                // System.out.println("repositorio 1");
                array = new ArrayList<BeanCuenta41>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        //   System.out.println("repositorio " + cont);
                        array = new ArrayList<BeanCuenta41>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanCuenta41 bean = new BeanCuenta41();
                    bean.setBCodigoEmpleado(rs.getString(1));
                    bean.setBCuenta(rs.getString(2));
                    bean.setBCenter(rs.getString(3));
                    bean.setSaldoFinal(rs.getBigDecimal(4));
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

    public static ArrayList<ArrayList<BeanCuenta>> repositorioCuenta(String codCompania) throws SQLException {
        ArrayList<ArrayList<BeanCuenta>> repositorio = new ArrayList<ArrayList<BeanCuenta>>();

        ResultSet rs = null;
        CallableStatement cst = null;

        int val = 30000;
        ArrayList<BeanCuenta> array;
        int contador = 0;
        int cont = 2;
        try {

            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(codCompania);


            ArrayList objetos = DaoCuenta.Repositoriocuenta(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);

            if (rs != null) {
                // System.out.println("repositorio 1");
                array = new ArrayList<BeanCuenta>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        //   System.out.println("repositorio " + cont);
                        array = new ArrayList<BeanCuenta>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanCuenta bean = new BeanCuenta();
                    bean.setCuenta(rs.getString(1));
                    bean.setCenter(rs.getString(2));
                    bean.setDenominacion(rs.getString(3));
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

    public static ArrayList<ArrayList<BeanEmpleado>> repositorioEmpleado(String codigoComania) throws SQLException {
        ArrayList<ArrayList<BeanEmpleado>> repositorio = new ArrayList<ArrayList<BeanEmpleado>>();

        ResultSet rs = null;
        CallableStatement cst = null;

        int val = 30000;
        ArrayList<BeanEmpleado> array;
        int contador = 0;
        int cont = 2;
        try {

            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(codigoComania);
            param.add("EMP");
            param.add("all");
            ArrayList objetos = DaoEmpleado.consultaEmpleado(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);

            if (rs != null) {
                // System.out.println("repositorio 1");
                array = new ArrayList<BeanEmpleado>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        System.out.println("repositorio Empleado" + rs.getString(3));
                        array = new ArrayList<BeanEmpleado>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    System.out.println("repositorio Empleado " + rs.getString(3) + " --" + rs.getString(6));
                    BeanEmpleado bean = new BeanEmpleado();
                    bean.setCodigoAuxiliar(rs.getString(3).trim());
                    bean.setRuc(rs.getString(6));

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

    public static BeanCuenta41 buscarInRepositorio(ArrayList<ArrayList<BeanCuenta41>> repor, String BcodigoEmpelado, String Cuenta, String Center) {
        BeanCuenta41 bean = new BeanCuenta41();
        BeanCuenta41 beanaux = new BeanCuenta41();
        ArrayList<BeanCuenta41> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanCuenta41>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanCuenta41) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getBCodigoEmpleado().equals(BcodigoEmpelado) && beanaux.getBCuenta().equals(Cuenta) && beanaux.getBCenter().equals(Center)) {
                    //  System.out.println("eliminado repositorio 1 " + codigo);
                    //  array.remove(beanaux);
                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;
    }

    public static BeanEmpleado buscarInRepositorioEmpleado(ArrayList<ArrayList<BeanEmpleado>> repor, String codigoEmpleado) {
        BeanEmpleado bean = new BeanEmpleado();
        BeanEmpleado beanaux = new BeanEmpleado();
        ArrayList<BeanEmpleado> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanEmpleado>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanEmpleado) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getCodigoAuxiliar().equals(codigoEmpleado)) {
                    //  System.out.println("eliminado repositorio 1 " + codigo);
                    //  array.remove(beanaux);
                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;
    }

    public static BeanCuenta buscarInRepositorioCuenta(ArrayList<ArrayList<BeanCuenta>> repor, String Cuenta, String Center) {
        BeanCuenta bean = new BeanCuenta();
        BeanCuenta beanaux = new BeanCuenta();
        ArrayList<BeanCuenta> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanCuenta>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanCuenta) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getCuenta().equals(Cuenta) && beanaux.getCenter().equals(Center)) {
                    //  System.out.println("eliminado repositorio 1 " + codigo);
                    //  array.remove(beanaux);
                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;

    }
}
