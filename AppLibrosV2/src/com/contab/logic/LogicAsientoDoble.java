/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;


import com.contab.bean.BeanDobleAsiento;
import com.contab.dao.Conexion;
import com.contab.dao.ConexionPermanente;
import com.contab.dao.DaoAsientoDoble;
import com.contab.dao.DaoEmpresas;
import com.contab.datasource.DatasourceAsientoDoble;
import com.contab.util.Exportar;
import com.contab.view.ViewApplication;
import com.contab.view.ViewCuenta31;
import java.io.File;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
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
 * @author 777
 */
public class LogicAsientoDoble {

    public static ViewCuenta31 frmCuenta31;

    public static ArrayList<BeanDobleAsiento> listaCuentaDobleASiento(String com, String anno, String mes, String cuenta) {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList<BeanDobleAsiento> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(com);
            param.add(anno);
            param.add(mes);
            param.add(cuenta);
            ArrayList objetos = DaoAsientoDoble.CONSULTACUENTASIENTODOBLE(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanDobleAsiento obj = new BeanDobleAsiento();
                    // obj.setIdCuenta31(rs.getInt(1));
                    obj.setCuenta(rs.getString(1));
                    obj.setCentro(rs.getString(2));
                    obj.setFecha(rs.getString(4) + "/" + rs.getString(3));
                    obj.setDeberSoles(rs.getBigDecimal(5));
                    obj.setHaberSoles(rs.getBigDecimal(6));
                    obj.setSaldoSoles(rs.getBigDecimal(7));
                    obj.setHaberDolares(rs.getBigDecimal(8));
                    obj.setDeberDolares(rs.getBigDecimal(9));
                    obj.setSaldoDolares(rs.getBigDecimal(10));


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

    public static void listaSaldoCuenta50(String empresa, String ruc, String compania, String ano, String mes, String cuenta, File ruta, String tipoExpor) throws SQLException, Exception {


        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            Conexion cnx = null;
            try {
                //  frmCuenta50 = new ViewCuenta50();


                DatasourceAsientoDoble dataCuenta50 = new DatasourceAsientoDoble();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                param.add(ano); //ano
                param.add(mes); //ano
                param.add(cuenta); //ano


                ArrayList objetos = DaoAsientoDoble.CONSULTACUENTASIENTODOBLE(param);
                rs = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);

                ViewApplication.mensajeEstado.setText(null);
                //    ViewApplication.mensajeEstado.setText("PROCESANDO  CUENTA 50 Nª: " + empresa + " DEL PERIODO: " + ano);


                if (rs != null && cst != null) {
                    while (rs.next()) {

                        BeanDobleAsiento BeanCuenta50 = new BeanDobleAsiento();
                        //  ViewApplication.mensajeEstado.setText("PROCESANDO  CUENTA  50 Nª: " + empresa + " DEL PERIODO: " + ano + " numero" + rs.getString(1));


                        BeanCuenta50.setCuenta(rs.getString(1));
                        BeanCuenta50.setCentro(rs.getString(2));
                        BeanCuenta50.setDeberSoles(rs.getBigDecimal(5));
                        BeanCuenta50.setHaberSoles(rs.getBigDecimal(6));
                        BeanCuenta50.setSaldoSoles(rs.getBigDecimal(7));
                        BeanCuenta50.setDeberDolares(rs.getBigDecimal(8));
                        BeanCuenta50.setHaberDolares(rs.getBigDecimal(9));
                        BeanCuenta50.setSaldoDolares(rs.getBigDecimal(10));
                        dataCuenta50.addBalance(BeanCuenta50);


                    }
                }

                //  } txtValorMoninal  txtNumeroAccionesS txtNumeroAccionesP


                Map parameters = new HashMap();
                //  parameters.put("EJERCICIO", ano);
                // parameters.put("P_RUC", ruc);
                // parameters.put("P_RAZON", empresa);

                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptcaja = "/com/contab/report/BalanceCuentas.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataCuenta50, rutarptcaja, "Caja");
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE BALANCE !!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE BALANCE", "ALERT", JOptionPane.INFORMATION_MESSAGE);
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

    public static void listaSaldoActualizarBalance(String compania, String ano, String mes, String cuentax) throws SQLException, Exception {

        ConexionPermanente.cnx();
        Connection ora = ConexionPermanente.cnxOra;
        ResultSet rsD = null;
        CallableStatement cst = null;
        Conexion cnx = null;

        ResultSet rs = null;
        // CallableStatement cst = null;
        //Conexion cnx = null;
        try {
            //  frmCuenta50 = new ViewCuenta50();

            ArrayList paramx = new ArrayList();
            paramx.add(OracleTypes.CURSOR);
            paramx.add(compania.toString()); //numero de la empresa o compa;ia
            paramx.add(ano); //ano
            paramx.add(mes); //ano
            paramx.add(cuentax); //ano
            int mmes = Integer.parseInt(mes);
            System.out.println(mmes + "mes'");


            ArrayList objetos = DaoAsientoDoble.CONSULTACUENTASIENTODOBLE(paramx);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            ArrayList objetosA;
            ViewApplication.mensajeEstado.setText(null);
            //  ViewApplication.mensajeEstado.setText("PROCESANDO  Actualizar Cuentas  Nª: " + compania + " DEL PERIODO: " + ano);

            int contarActualizados = 0;
            BigDecimal estado = null;
            ArrayList paramA = new ArrayList();
            if (rs != null && cst != null) {
                while (rs.next()) {

                    BeanDobleAsiento BeanCuenta50 = new BeanDobleAsiento();

                    String cuenta = rs.getString(1);
                    String centro = rs.getString(2);
                    ViewApplication.mensajeEstado.setText("PROCESANDO  Actualizar Cuentas Nª: " + compania + " DEL PERIODO: " + ano + "mes " + mmes + " cuenta " + cuenta + " centro " + centro);
                    BigDecimal debeSoles;
                    BigDecimal haberSoles;
                    if (mmes == 1) {

                        BeanCuenta50 = LISTARASIENTODOBLESALDOINICIAL(compania, ano, cuenta, centro, ora);
                        debeSoles = rs.getBigDecimal(5).subtract(BeanCuenta50.getDeberSoles());
                        haberSoles = rs.getBigDecimal(6).subtract(BeanCuenta50.getHaberSoles());

                    } else {

                        debeSoles = rs.getBigDecimal(5);
                        haberSoles = rs.getBigDecimal(6);

                    }


                    //if(cuenta.equals("2010170") && centro.equals("2050") ){
                    paramA.add(OracleTypes.NUMBER);
                    paramA.add(cuenta);
                    paramA.add(centro);
                    paramA.add(compania);
                    paramA.add(ano);
                    paramA.add(debeSoles);
                    paramA.add(haberSoles);
                    paramA.add(mmes); // mes
                    objetosA = DaoAsientoDoble.ACTUALIZAR_BALANCE(paramA, ora);
                    estado = (BigDecimal) objetosA.get(0);
                    cst = (CallableStatement) objetosA.get(1);
                    if (estado.compareTo(BigDecimal.valueOf(1)) == 0) {
                        contarActualizados = contarActualizados + 1;
                        paramA.clear();
                    }
                    //  compania=compania;
                    //  ano=ano;
                    // mmes=mmes;

                    // }

                }
            }

            JOptionPane.showMessageDialog(null,
                    "Se han actualizado: " + contarActualizados + " registros \n", "Informe de Operación", JOptionPane.INFORMATION_MESSAGE);

            //  } txtValorMoninal  txtNumeroAccionesS txtNumeroAccionesP




        } catch (SQLException ex) {
            System.out.print(ex.getErrorCode() + "  " + ex.getNextException() + "" + ex.getMessage());
            rs.close();
            cst.close();
            cnx.destroy();
        } finally {
            rs.close();
            cst.close();
            //  cnx.destroy();
        }



    }

    public static BeanDobleAsiento LISTARASIENTODOBLESALDOINICIAL(String COMP, String anno, String CUENTA, String CENTRO, Connection cnx) throws SQLException {


        ResultSet rs = null;
        CallableStatement cst = null;
        BeanDobleAsiento bean = new BeanDobleAsiento();
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(COMP);
            param.add(anno);
            param.add(CUENTA);
            param.add(CENTRO);
            ArrayList objetos = DaoAsientoDoble.LISTARASIENTODOBLESALDOINICIAL(param, cnx);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            //  String numero = "";

            if (rs != null && cst != null) {
                while (rs.next()) {

                    bean.setDeberSoles(rs.getBigDecimal(1));
                    bean.setHaberSoles(rs.getBigDecimal(2));
                    //System.out.println("saldo inicial:///// " + rs.getBigDecimal(5) + " " + CodCompania + "//" + fecha + "item" + ItemProducto);
                    System.out.println(rs.getBigDecimal(1).toString() + "--" + rs.getBigDecimal(2).toString());
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




        return bean;
    }
}
