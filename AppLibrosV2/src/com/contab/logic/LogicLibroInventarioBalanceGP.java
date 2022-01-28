/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeaLlibroInventarioBalanceGP;
import com.contab.bean.BeanCuenta31;
import com.contab.bean.BeanCuenta31;

import com.contab.dao.DaoCuenta31;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoLibroInventarioBalanceGP;

import com.contab.dao.DaoTabla03;
import com.contab.datasource.DatasourceLibroInventarioBalanceGP;
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
public class LogicLibroInventarioBalanceGP {

    public static void listaLibroInventarioBalanceGP(String empresa, String ruc, String compania, String ano, File ruta, String tipoExpor) throws SQLException, Exception {


        if (ruta != null) {
            ResultSet rs70 = null;
            CallableStatement cst70 = null;
            ResultSet rs74 = null;
            CallableStatement cst74 = null;
            ResultSet rs69 = null;
            CallableStatement cst69 = null;
            ResultSet rs95 = null;
            CallableStatement cst95 = null;
            ResultSet rs94 = null;
            CallableStatement cst94 = null;
            ResultSet rs67 = null;
            CallableStatement cst67 = null;
            ResultSet rs77 = null;
            CallableStatement cst77 = null;
            ResultSet rs76 = null;
            CallableStatement cst76 = null;
            ResultSet rs73 = null;
            CallableStatement cst73 = null;
            ResultSet rs75 = null;
            CallableStatement cst75 = null;
            ResultSet rs66 = null;
            CallableStatement cst66 = null;
            ResultSet rs86 = null;
            CallableStatement cst86 = null;
            ResultSet rs88 = null;
            CallableStatement cst88 = null;

            try {

                DatasourceLibroInventarioBalanceGP BeanLibroInventarioBalanceGP = new DatasourceLibroInventarioBalanceGP();
                BeaLlibroInventarioBalanceGP ve = new BeaLlibroInventarioBalanceGP();

                BigDecimal saldo70 = BigDecimal.ZERO;
                BigDecimal saldo74 = BigDecimal.ZERO;
                BigDecimal saldo69 = BigDecimal.ZERO;
                BigDecimal saldo95 = BigDecimal.ZERO;
                BigDecimal saldo94 = BigDecimal.ZERO;
                BigDecimal saldo67 = BigDecimal.ZERO;
                BigDecimal saldo77 = BigDecimal.ZERO;
                BigDecimal saldo76 = BigDecimal.ZERO;
                BigDecimal saldo73 = BigDecimal.ZERO;
                BigDecimal saldo75 = BigDecimal.ZERO;
                BigDecimal saldo66 = BigDecimal.ZERO;
                BigDecimal saldo86 = BigDecimal.ZERO;
                BigDecimal saldo88 = BigDecimal.ZERO;

                /**************** vuenta 69  ***********************/
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                param.add(ano); //ano
                param.add("70"); //ano
                ArrayList objetos = new ArrayList();
                objetos = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param);
                rs70 = (ResultSet) objetos.get(0);
                cst70 = (CallableStatement) objetos.get(1);
                if (rs70 != null && cst70 != null) {
                    while (rs70.next()) {
                        saldo70 = rs70.getBigDecimal(2);
                        System.out.println(saldo70+"  1");

                    }
                }

                /**************** vuenta 69 70 ***********************/
                ArrayList param74 = new ArrayList();
                param74.add(OracleTypes.CURSOR);
                param74.add(compania); //numero de la empresa o compa;ia
                param74.add(ano); //ano
                param74.add("74"); //ano
                ArrayList objetos74 = new ArrayList();
                objetos74 = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param74);
                rs74 = (ResultSet) objetos74.get(0);
                cst74 = (CallableStatement) objetos74.get(1);
                if (rs74 != null && cst74 != null) {
                    while (rs74.next()) {
                        saldo74 = rs74.getBigDecimal(2);
                        System.out.println(saldo74+"  2");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param69 = new ArrayList();
                param69.add(OracleTypes.CURSOR);
                param69.add(compania); //numero de la empresa o compa;ia
                param69.add(ano); //ano
                param69.add("69"); //ano
                ArrayList objetos69 = new ArrayList();
                objetos69 = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param69);
                rs69 = (ResultSet) objetos69.get(0);
                cst69 = (CallableStatement) objetos69.get(1);
                if (rs69 != null && cst69 != null) {
                    while (rs69.next()) {
                        saldo69 = rs69.getBigDecimal(2);
                        System.out.println(saldo69+"  2");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param95 = new ArrayList();
                param95.add(OracleTypes.CURSOR);
                param95.add(compania); //numero de la empresa o compa;ia
                param95.add(ano); //ano
                param95.add("95"); //ano
                ArrayList objetos95 = new ArrayList();
                objetos95 = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param95);
                rs95 = (ResultSet) objetos95.get(0);
                cst95 = (CallableStatement) objetos95.get(1);
                if (rs95 != null && cst95 != null) {
                    while (rs95.next()) {
                        saldo95 = rs95.getBigDecimal(2);
                        System.out.println(saldo95+"  3");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param94 = new ArrayList();
                param94.add(OracleTypes.CURSOR);
                param94.add(compania); //numero de la empresa o compa;ia
                param94.add(ano); //ano
                param94.add("94"); //ano
                ArrayList objetos94 = new ArrayList();
                objetos94 = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param94);
                rs94 = (ResultSet) objetos94.get(0);
                cst94 = (CallableStatement) objetos94.get(1);
                if (rs94 != null && cst94 != null) {
                    while (rs94.next()) {
                        saldo94 = rs94.getBigDecimal(2);
                        System.out.println(saldo95+"  4");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param67 = new ArrayList();
                param67.add(OracleTypes.CURSOR);
                param67.add(compania); //numero de la empresa o compa;ia
                param67.add(ano); //ano
                param67.add("67"); //ano
                ArrayList objetos67 = new ArrayList();
                objetos67 = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param67);
                rs67 = (ResultSet) objetos67.get(0);
                cst67 = (CallableStatement) objetos67.get(1);
                if (rs67 != null && cst67 != null) {
                    while (rs67.next()) {
                        saldo67 = rs67.getBigDecimal(2);
                        System.out.println(saldo67+"  5");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param77 = new ArrayList();
                param77.add(OracleTypes.CURSOR);
                param77.add(compania); //numero de la empresa o compa;ia
                param77.add(ano); //ano
                param77.add("77"); //ano
                ArrayList objetos77 = new ArrayList();
                objetos77 = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param77);
                rs77 = (ResultSet) objetos77.get(0);
                cst77 = (CallableStatement) objetos77.get(1);
                if (rs77 != null && cst77 != null) {
                    while (rs77.next()) {
                        saldo77 = rs77.getBigDecimal(2);
                        System.out.println(saldo77+"  6");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param76 = new ArrayList();
                param76.add(OracleTypes.CURSOR);
                param76.add(compania); //numero de la empresa o compa;ia
                param76.add(ano); //ano
                param76.add("76"); //ano
                ArrayList objetos76 = new ArrayList();
                objetos76 = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param76);
                rs76 = (ResultSet) objetos76.get(0);
                cst76 = (CallableStatement) objetos76.get(1);
                if (rs76 != null && cst76 != null) {
                    while (rs76.next()) {
                        saldo76 = rs76.getBigDecimal(2);
                        System.out.println(saldo76+"  7");
                    }
                }
                //************fin **************************************/


                /**************** vuenta  69 ***********************/
                ArrayList param73 = new ArrayList();
                param73.add(OracleTypes.CURSOR);
                param73.add(compania); //numero de la empresa o compa;ia
                param73.add(ano); //ano
                param73.add("73"); //ano
                ArrayList objetos73 = new ArrayList();
                objetos73 = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param73);
                rs73 = (ResultSet) objetos73.get(0);
                cst73 = (CallableStatement) objetos73.get(1);
                if (rs73 != null && cst73 != null) {
                    while (rs73.next()) {
                        saldo73 = rs73.getBigDecimal(2);
                        System.out.println(saldo73+"  8");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param75 = new ArrayList();
                param75.add(OracleTypes.CURSOR);
                param75.add(compania); //numero de la empresa o compa;ia
                param75.add(ano); //ano
                param75.add("75"); //ano
                ArrayList objetos75 = new ArrayList();
                objetos75 = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param75);
                rs75 = (ResultSet) objetos75.get(0);
                cst75 = (CallableStatement) objetos75.get(1);
                if (rs75 != null && cst75 != null) {
                    while (rs75.next()) {
                        saldo75 = rs75.getBigDecimal(2);
                        System.out.println(saldo75+"  9");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param66 = new ArrayList();
                param66.add(OracleTypes.CURSOR);
                param66.add(compania); //numero de la empresa o compa;ia
                param66.add(ano); //ano
                param66.add("66"); //ano
                ArrayList objetos66 = new ArrayList();
                objetos66 = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param66);
                rs66 = (ResultSet) objetos66.get(0);
                cst66 = (CallableStatement) objetos66.get(1);
                if (rs66 != null && cst66 != null) {
                    while (rs66.next()) {
                        saldo66 = rs66.getBigDecimal(2);
                        System.out.println(saldo66+"  10");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param86 = new ArrayList();
                param86.add(OracleTypes.CURSOR);
                param86.add(compania); //numero de la empresa o compa;ia
                param86.add(ano); //ano
                param86.add("86"); //ano
                ArrayList objetos86 = new ArrayList();
                objetos86 = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param86);
                rs86 = (ResultSet) objetos86.get(0);
                cst86 = (CallableStatement) objetos86.get(1);
                if (rs86 != null && cst86 != null) {
                    while (rs86.next()) {
                        saldo86 = rs86.getBigDecimal(2);
                        System.out.println(saldo66+"  11");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param88 = new ArrayList();
                param88.add(OracleTypes.CURSOR);
                param88.add(compania); //numero de la empresa o compa;ia
                param88.add(ano); //ano
                param88.add("88"); //ano
                ArrayList objetos88 = new ArrayList();
                objetos88 = DaoLibroInventarioBalanceGP.libroInventarioBalanceGP(param88);
                rs88 = (ResultSet) objetos88.get(0);
                cst88 = (CallableStatement) objetos88.get(1);
                if (rs88 != null && cst88 != null) {
                    while (rs88.next()) {
                        saldo88 = rs88.getBigDecimal(2);
                        System.out.println(saldo66+"  12");
                    }
                }
                //************fin **************************************/


                ve.setVentaNeta((saldo70.add(saldo74)).abs());
                ve.setOtroIngresoOperacionales(BigDecimal.ZERO);
                ve.setCostoDeVentas(saldo69.negate());
                ve.setGastoOperacionales(BigDecimal.ZERO);
                ve.setGastosAdministracion(saldo94.negate());
                ve.setGastoVenta(saldo95.negate()); //saldo73.add(saldo75.add(saldo76)
                ve.setOtrosImgresos(BigDecimal.ZERO);
                ve.setIngresosFinanacieros(saldo77.abs());
                ve.setGastosFinacieros(saldo67.negate());
                ve.setOtrosIngresos((saldo73.add(saldo75)).abs());
                ve.setOtrosGasto(BigDecimal.ZERO);
                ve.setResultadosExposicionInflacion(BigDecimal.ZERO);
                ve.setParticipacion(saldo86.negate());


                ve.setImpuestoRenta(saldo88.abs());
                ve.setIngresoExtraordinarios(saldo76.abs());
                ve.setGastosExtraordinarios(saldo66.negate());



                BeanLibroInventarioBalanceGP.addLlibroInventarioBalanceGP(ve);



                Map parameters = new HashMap();
                parameters.put("EJERCICIO", ano);
                parameters.put("P_RUC", ruc);
                parameters.put("P_RAZON", empresa);
                parameters.put("ANNO", ano);
                  parameters.put(JRParameter.REPORT_LOCALE, Locale.US);


                String rutarptcaja = "/com/contab/report/formatoGYP.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, BeanLibroInventarioBalanceGP, rutarptcaja, "Caja");
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE CUENTA LIBRO BALANCE GANANCIA Y PERDIDAS !!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE CUENTA LIBRO BALANCE GANANCIA Y PERDIDAS", "ALERT", JOptionPane.INFORMATION_MESSAGE);

                //cnx.destroy();

            } catch (SQLException ex) {
                System.out.print(ex.getErrorCode() + "  " + ex.getNextException() + "" + ex.getMessage());

            } finally {
                rs70.close();
                cst70.close();
                rs74.close();
                cst74.close();
                rs69.close();
                cst69.close();
                rs95.close();
                cst95.close();
                rs94.close();
                cst94.close();
                rs67.close();
                cst67.close();
                //cnx.destroy();
            }


        }
    }
}
