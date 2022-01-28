/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanBalanceGeneral;

import com.contab.dao.DaoBalanceGeneral;

import com.contab.datasource.DatasourceBalanceGeneral;
import com.contab.util.Exportar;
import com.contab.view.ViewApplication;

import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.CallableStatement;
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
public class LogicBalanceGeneral {

    public static void listaLibroBalanceGeneral(String empresa, String ruc, String compania, String ano, File ruta, String tipoExpor) throws SQLException, Exception {


        if (ruta != null) {
            ResultSet rs10 = null;
            CallableStatement cst10 = null;
            ResultSet rs20 = null;
            CallableStatement cst20 = null;

            ResultSet rs14 = null;
            CallableStatement cst14 = null;
            ResultSet rs17 = null;
            CallableStatement cst17 = null;
            
            ResultSet rs18 = null;
            CallableStatement cst18= null;
            
            ResultSet rs31 = null;
            CallableStatement cst31 = null;

            ResultSet rs12 = null;
            CallableStatement cst12 = null;

            ResultSet rs28 = null;
            CallableStatement cst28 = null;

            ResultSet rs129 = null;
            CallableStatement cst129 = null;

            ResultSet rs120 = null;
            CallableStatement cst120 = null;
            ResultSet rs121 = null;
            CallableStatement cst121 = null;
            ResultSet rs122 = null;
            CallableStatement cst122 = null;
            ResultSet rs123 = null;
            CallableStatement cst123 = null;

            ResultSet rs124 = null;
            CallableStatement cst124 = null;
            ResultSet rs125 = null;
            CallableStatement cst125 = null;
            ResultSet rs126 = null;
            CallableStatement cst126 = null;
            ResultSet rs127 = null;
            CallableStatement cst127 = null;
            ResultSet rs128 = null;
            CallableStatement cst128 = null;

            ResultSet rs16 = null;
            CallableStatement cst16 = null;
            ResultSet rs2 = null;
            CallableStatement cst2 = null;

            ResultSet rs29 = null;
            CallableStatement cst29 = null;

            ResultSet rs48 = null;
            CallableStatement cst48 = null;

            ResultSet rs49 = null;
            CallableStatement cst49 = null;

            ResultSet rs33 = null;
            CallableStatement cst33 = null;

            ResultSet rs39 = null;
            CallableStatement cst39 = null;
            ResultSet rs34 = null;
            CallableStatement cst34 = null;

            ResultSet rs42 = null;
            CallableStatement cst42 = null;
             ResultSet rs45 = null;
            CallableStatement cst45 = null;

            ResultSet rs46 = null;
            CallableStatement cst46 = null;


            ResultSet rs50 = null;
            CallableStatement cst50 = null;
            ResultSet rs56 = null;
            CallableStatement cst56 = null;
            ResultSet rs57 = null;
            CallableStatement cst57 = null;
            ResultSet rs58 = null;
            CallableStatement cst58 = null;
            ResultSet rs59 = null;
            CallableStatement cst59 = null;
            ResultSet rs40 = null;
            CallableStatement cst40 = null;
            ResultSet rs41 = null;
            CallableStatement cst41 = null;
            ResultSet rs47 = null;
            CallableStatement cst47 = null;
            ResultSet rs66 = null;
            CallableStatement cst19 = null;
            ResultSet rs19 = null;

            ResultSet rsACU = null;
            CallableStatement cstACU = null;

            ResultSet rs38 = null;
            CallableStatement cst38 = null;

            // ResultSet rs16= null;


            try {

                DatasourceBalanceGeneral LibroBalanceGeneral = new DatasourceBalanceGeneral();
                BeanBalanceGeneral ve = new BeanBalanceGeneral();
                BigDecimal saldo10 = BigDecimal.ZERO;
                BigDecimal saldo14 = BigDecimal.ZERO;
                BigDecimal saldo31 = BigDecimal.ZERO;
                BigDecimal saldo12 = BigDecimal.ZERO;
                BigDecimal saldo129 = BigDecimal.ZERO;
                BigDecimal saldo16 = BigDecimal.ZERO;
                BigDecimal saldo17 = BigDecimal.ZERO;
                BigDecimal saldo18 = BigDecimal.ZERO;

                BigDecimal saldo2 = BigDecimal.ZERO;
                BigDecimal saldo20 = BigDecimal.ZERO;
                BigDecimal saldo29 = BigDecimal.ZERO;

                BigDecimal saldo33 = BigDecimal.ZERO;
                BigDecimal saldo38 = BigDecimal.ZERO;
                BigDecimal saldo39 = BigDecimal.ZERO;

                BigDecimal saldo34 = BigDecimal.ZERO;
                BigDecimal saldo42 = BigDecimal.ZERO;
                  BigDecimal saldo45 = BigDecimal.ZERO;
                BigDecimal saldo46 = BigDecimal.ZERO;


                BigDecimal saldo50 = BigDecimal.ZERO;
                BigDecimal saldo28 = BigDecimal.ZERO;
                BigDecimal saldo56 = BigDecimal.ZERO;
                BigDecimal saldo57 = BigDecimal.ZERO;
                BigDecimal saldo58 = BigDecimal.ZERO;
                BigDecimal saldo59 = BigDecimal.ZERO;
                BigDecimal saldo120 = BigDecimal.ZERO;
                BigDecimal saldo40P = BigDecimal.ZERO;
                BigDecimal saldo40A = BigDecimal.ZERO;
                BigDecimal saldo41 = BigDecimal.ZERO;
                BigDecimal saldo47 = BigDecimal.ZERO;
                BigDecimal saldo121 = BigDecimal.ZERO;
                BigDecimal saldo122 = BigDecimal.ZERO;
                BigDecimal saldo123 = BigDecimal.ZERO;
                BigDecimal saldo124 = BigDecimal.ZERO;
                BigDecimal saldo125 = BigDecimal.ZERO;
                BigDecimal saldo126 = BigDecimal.ZERO;
                BigDecimal saldo127 = BigDecimal.ZERO;
                BigDecimal saldo128 = BigDecimal.ZERO;
                BigDecimal saldo19 = BigDecimal.ZERO;
                BigDecimal saldoACU = BigDecimal.ZERO;

                BigDecimal saldo48 = BigDecimal.ZERO;
                BigDecimal saldo49 = BigDecimal.ZERO;


                /**************** vuenta 10  ***********************/
                ArrayList param10 = new ArrayList();
                param10.add(OracleTypes.CURSOR);
                param10.add(compania); //numero de la empresa o compa;ia
                param10.add(ano); //ano
                param10.add("10"); //ano
                ArrayList objetos = new ArrayList();
                objetos = DaoBalanceGeneral.libroBalanceGeneral2(param10);
                rs10 = (ResultSet) objetos.get(0);
                cst10 = (CallableStatement) objetos.get(1);
                if (rs10 != null && cst10 != null) {
                    while (rs10.next()) {
                        saldo10 = rs10.getBigDecimal(4);
                        System.out.println(saldo10 + "  10");

                    }
                }

                ArrayList param14 = new ArrayList();
                param14.add(OracleTypes.CURSOR);
                param14.add(compania); //numero de la empresa o compa;ia
                param14.add(ano); //ano
                param14.add("14"); //ano
                ArrayList objetos14 = new ArrayList();
                objetos14 = DaoBalanceGeneral.libroBalanceGeneral2(param14);
                rs14 = (ResultSet) objetos14.get(0);
                cst14 = (CallableStatement) objetos14.get(1);
                if (rs14 != null && cst14 != null) {
                    while (rs14.next()) {
                        saldo14 = rs14.getBigDecimal(4);
                        System.out.println(saldo14 + "  14");

                    }
                }


                ArrayList param16 = new ArrayList();
                param16.add(OracleTypes.CURSOR);
                param16.add(compania); //numero de la empresa o compa;ia
                param16.add(ano); //ano
                param16.add("16"); //ano
                ArrayList objetos16 = new ArrayList();
                objetos16 = DaoBalanceGeneral.libroBalanceGeneral2(param16);
                rs16 = (ResultSet) objetos16.get(0);
                cst16 = (CallableStatement) objetos16.get(1);
                if (rs16 != null && cst16 != null) {
                    while (rs16.next()) {
                        saldo16 = rs16.getBigDecimal(4);
                        System.out.println(saldo16 + "  16");

                    }
                }
                
                 ArrayList param17 = new ArrayList();
                param17.add(OracleTypes.CURSOR);
                param17.add(compania); //numero de la empresa o compa;ia
                param17.add(ano); //ano
                param17.add("17"); //ano
                ArrayList objetos17 = new ArrayList();
                objetos17 = DaoBalanceGeneral.libroBalanceGeneral2(param17);
                rs17 = (ResultSet) objetos17.get(0);
                cst17 = (CallableStatement) objetos17.get(1);
                if (rs17 != null && cst17 != null) {
                    while (rs17.next()) {
                        saldo17 = rs17.getBigDecimal(4);
                        System.out.println(saldo17 + "  17");

                    }
                }
                
                  ArrayList param18 = new ArrayList();
                param18.add(OracleTypes.CURSOR);
                param18.add(compania); //numero de la empresa o compa;ia
                param18.add(ano); //ano
                param18.add("18"); //ano
                ArrayList objetos18 = new ArrayList();
                objetos18 = DaoBalanceGeneral.libroBalanceGeneral2(param18);
                rs18 = (ResultSet) objetos18.get(0);
                cst18 = (CallableStatement) objetos18.get(1);
                if (rs18 != null && cst18 != null) {
                    while (rs18.next()) {
                        saldo18 = rs18.getBigDecimal(4);
                        System.out.println(saldo18 + "  18");

                    }
                }

                /**************** vuenta 31 ***********************/
                ArrayList param31 = new ArrayList();
                param31.add(OracleTypes.CURSOR);
                param31.add(compania); //numero de la empresa o compa;ia
                param31.add(ano); //ano
                param31.add("31"); //ano
                ArrayList objetos31 = new ArrayList();
                objetos31 = DaoBalanceGeneral.libroBalanceGeneral2(param31);
                rs31 = (ResultSet) objetos31.get(0);
                cst31 = (CallableStatement) objetos31.get(1);
                if (rs31 != null && cst31 != null) {
                    while (rs31.next()) {
                        saldo31 = rs31.getBigDecimal(4);
                        System.out.println(saldo31 + "  31");
                    }
                }

                ArrayList param38 = new ArrayList();
                param38.add(OracleTypes.CURSOR);
                param38.add(compania); //numero de la empresa o compa;ia
                param38.add(ano); //ano
                param38.add("38"); //ano
                ArrayList objetos38 = new ArrayList();
                objetos38 = DaoBalanceGeneral.libroBalanceGeneral2(param38);
                rs38 = (ResultSet) objetos38.get(0);
                cst38 = (CallableStatement) objetos38.get(1);
                if (rs38 != null && cst38 != null) {
                    while (rs38.next()) {
                        saldo38 = rs38.getBigDecimal(4);
                        System.out.println(saldo38 + "  38");
                    }
                }

                ArrayList param48 = new ArrayList();
                param48.add(OracleTypes.CURSOR);
                param48.add(compania); //numero de la empresa o compa;ia
                param48.add(ano); //ano
                param48.add("48"); //ano
                ArrayList objetos48 = new ArrayList();
                objetos48 = DaoBalanceGeneral.libroBalanceGeneral2(param48);
                rs48 = (ResultSet) objetos48.get(0);
                cst48 = (CallableStatement) objetos48.get(1);
                if (rs48 != null && cst48 != null) {
                    while (rs48.next()) {
                        saldo48 = rs48.getBigDecimal(4);
                        System.out.println(saldo38 + "  48");
                    }
                }

                ArrayList param49 = new ArrayList();
                param49.add(OracleTypes.CURSOR);
                param49.add(compania); //numero de la empresa o compa;ia
                param49.add(ano); //ano
                param49.add("49"); //ano
                ArrayList objetos49 = new ArrayList();
                objetos49 = DaoBalanceGeneral.libroBalanceGeneral2(param49);
                rs49 = (ResultSet) objetos49.get(0);
                cst49 = (CallableStatement) objetos49.get(1);
                if (rs49 != null && cst48 != null) {
                    while (rs49.next()) {
                        saldo49 = rs49.getBigDecimal(4);
                        System.out.println(saldo49 + "  49");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  12 ***********************/
                ArrayList param12 = new ArrayList();
                param12.add(OracleTypes.CURSOR);
                param12.add(compania); //numero de la empresa o compa;ia
                param12.add(ano); //ano
                param12.add("12"); //ano
                ArrayList objetos12 = new ArrayList();
                objetos12 = DaoBalanceGeneral.libroBalanceGeneral2(param12);
                rs12 = (ResultSet) objetos12.get(0);
                cst12 = (CallableStatement) objetos12.get(1);
                if (rs12 != null && cst12 != null) {
                    while (rs12.next()) {
                        saldo12 = rs12.getBigDecimal(4);
                        System.out.println(saldo12 + "  12");
                    }
                }
                //************fin **************************************/

                ArrayList param20 = new ArrayList();
                param20.add(OracleTypes.CURSOR);
                param20.add(compania); //numero de la empresa o compa;ia
                param20.add(ano); //ano
                param20.add("20"); //ano
                ArrayList objetos20 = new ArrayList();
                objetos20 = DaoBalanceGeneral.libroBalanceGeneral2(param20);
                rs20 = (ResultSet) objetos20.get(0);
                cst20 = (CallableStatement) objetos20.get(1);
                if (rs20 != null && cst20 != null) {
                    while (rs20.next()) {
                        saldo20 = rs20.getBigDecimal(4);
                        System.out.println(saldo20 + "  20");
                    }
                }
                ArrayList param28 = new ArrayList();
                param28.add(OracleTypes.CURSOR);
                param28.add(compania); //numero de la empresa o compa;ia
                param28.add(ano); //ano
                param28.add("28"); //ano
                ArrayList objetos28 = new ArrayList();
                objetos28 = DaoBalanceGeneral.libroBalanceGeneral2(param28);
                rs28 = (ResultSet) objetos28.get(0);
                cst28 = (CallableStatement) objetos28.get(1);
                if (rs28 != null && cst28 != null) {
                    while (rs28.next()) {
                        saldo28 = rs28.getBigDecimal(4);
                        System.out.println(saldo28 + "  28");
                    }
                }


                /**************** vuenta  129 ***********************/
                ArrayList param129 = new ArrayList();
                param129.add(OracleTypes.CURSOR);
                param129.add(compania); //numero de la empresa o compa;ia
                param129.add(ano); //ano
                param129.add("129"); //ano
                ArrayList objetos129 = new ArrayList();
                objetos129 = DaoBalanceGeneral.libroBalanceGeneeral3(param129);
                rs129 = (ResultSet) objetos129.get(0);
                cst129 = (CallableStatement) objetos129.get(1);
                if (rs129 != null && cst129 != null) {
                    while (rs129.next()) {
                        saldo129 = rs129.getBigDecimal(4);
                        System.out.println(saldo12 + "  29");
                    }
                }

                ArrayList param121 = new ArrayList();
                param121.add(OracleTypes.CURSOR);
                param121.add(compania); //numero de la empresa o compa;ia
                param121.add(ano); //ano
                param121.add("121"); //ano
                ArrayList objetos121 = new ArrayList();
                objetos121 = DaoBalanceGeneral.libroBalanceGeneeral3(param121);
                rs121 = (ResultSet) objetos121.get(0);
                cst121 = (CallableStatement) objetos121.get(1);
                if (rs121 != null && cst121 != null) {
                    while (rs121.next()) {
                        saldo121 = rs121.getBigDecimal(4);
                        System.out.println(saldo121 + "  121");
                    }
                }

                ArrayList param122 = new ArrayList();
                param122.add(OracleTypes.CURSOR);
                param122.add(compania); //numero de la empresa o compa;ia
                param122.add(ano); //ano
                param122.add("122"); //ano
                ArrayList objetos122 = new ArrayList();
                objetos122 = DaoBalanceGeneral.libroBalanceGeneeral3(param122);
                rs122 = (ResultSet) objetos122.get(0);
                cst122 = (CallableStatement) objetos122.get(1);
                if (rs122 != null && cst122 != null) {
                    while (rs122.next()) {
                        saldo122 = rs122.getBigDecimal(4);
                        System.out.println(saldo122 + "  122");
                    }
                }


                ArrayList param123 = new ArrayList();
                param123.add(OracleTypes.CURSOR);
                param123.add(compania); //numero de la empresa o compa;ia
                param123.add(ano); //ano
                param123.add("123"); //ano
                ArrayList objetos123 = new ArrayList();
                objetos123 = DaoBalanceGeneral.libroBalanceGeneeral3(param123);
                rs123 = (ResultSet) objetos123.get(0);
                cst123 = (CallableStatement) objetos123.get(1);
                if (rs123 != null && cst123 != null) {
                    while (rs123.next()) {
                        saldo123 = rs123.getBigDecimal(4);
                        System.out.println(saldo123 + "  123");
                    }
                }


                ArrayList param124 = new ArrayList();
                param124.add(OracleTypes.CURSOR);
                param124.add(compania); //numero de la empresa o compa;ia
                param124.add(ano); //ano
                param124.add("124"); //ano
                ArrayList objetos124 = new ArrayList();
                objetos124 = DaoBalanceGeneral.libroBalanceGeneeral3(param124);
                rs124 = (ResultSet) objetos124.get(0);
                cst124 = (CallableStatement) objetos124.get(1);
                if (rs124 != null && cst124 != null) {
                    while (rs124.next()) {
                        saldo124 = rs124.getBigDecimal(4);
                        System.out.println(saldo124 + "  124");
                    }
                }

                ArrayList param125 = new ArrayList();
                param125.add(OracleTypes.CURSOR);
                param125.add(compania); //numero de la empresa o compa;ia
                param125.add(ano); //ano
                param125.add("125"); //ano
                ArrayList objetos125 = new ArrayList();
                objetos125 = DaoBalanceGeneral.libroBalanceGeneeral3(param125);
                rs125 = (ResultSet) objetos125.get(0);
                cst125 = (CallableStatement) objetos125.get(1);
                if (rs125 != null && cst125 != null) {
                    while (rs125.next()) {
                        saldo125 = rs125.getBigDecimal(4);
                        System.out.println(saldo125 + "  124");
                    }
                }

                ArrayList param126 = new ArrayList();
                param126.add(OracleTypes.CURSOR);
                param126.add(compania); //numero de la empresa o compa;ia
                param126.add(ano); //ano
                param125.add("126"); //ano
                ArrayList objetos126 = new ArrayList();
                objetos126 = DaoBalanceGeneral.libroBalanceGeneeral3(param125);
                rs126 = (ResultSet) objetos126.get(0);
                cst126 = (CallableStatement) objetos126.get(1);
                if (rs126 != null && cst126 != null) {
                    while (rs126.next()) {
                        saldo126 = rs126.getBigDecimal(4);
                        System.out.println(saldo126 + "  126");
                    }
                }

                ArrayList param127 = new ArrayList();
                param127.add(OracleTypes.CURSOR);
                param127.add(compania); //numero de la empresa o compa;ia
                param127.add(ano); //ano
                param127.add("127"); //ano
                ArrayList objetos127 = new ArrayList();
                objetos127 = DaoBalanceGeneral.libroBalanceGeneeral3(param127);
                rs127 = (ResultSet) objetos127.get(0);
                cst127 = (CallableStatement) objetos127.get(1);
                if (rs127 != null && cst127 != null) {
                    while (rs127.next()) {
                        saldo127 = rs127.getBigDecimal(4);
                        System.out.println(saldo127 + "  127");
                    }
                }

                ArrayList param128 = new ArrayList();
                param128.add(OracleTypes.CURSOR);
                param128.add(compania); //numero de la empresa o compa;ia
                param128.add(ano); //ano
                param128.add("128"); //ano
                ArrayList objetos128 = new ArrayList();
                objetos128 = DaoBalanceGeneral.libroBalanceGeneeral3(param128);
                rs128 = (ResultSet) objetos128.get(0);
                cst128 = (CallableStatement) objetos128.get(1);
                if (rs128 != null && cst128 != null) {
                    while (rs128.next()) {
                        saldo128 = rs128.getBigDecimal(4);
                        System.out.println(saldo128 + "  127");
                    }
                }

                BigDecimal saldoActivo = saldo121.add(saldo122.add(saldo123.add(saldo124.add(saldo125.add(saldo126.add(saldo127.add(saldo128)))))));




                //************fin **************************************/

                /**************** clases  2 ***********************/
                ArrayList param2 = new ArrayList();
                param2.add(OracleTypes.CURSOR);
                param2.add(compania); //numero de la empresa o compa;ia
                param2.add(ano); //ano
                param2.add("2"); //ano
                ArrayList objetos2 = new ArrayList();
                objetos2 = DaoBalanceGeneral.libroBalanceGeneeral1(param2);
                rs2 = (ResultSet) objetos2.get(0);
                cst2 = (CallableStatement) objetos2.get(1);
                if (rs2 != null && cst2 != null) {
                    while (rs2.next()) {
                        saldo2 = rs2.getBigDecimal(4);
                        System.out.println(saldo2 + "  2");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  29 ***********************/
                ArrayList param29 = new ArrayList();
                param29.add(OracleTypes.CURSOR);
                param29.add(compania); //numero de la empresa o compa;ia
                param29.add(ano); //ano
                param29.add("29"); //ano
                ArrayList objetos29 = new ArrayList();
                objetos29 = DaoBalanceGeneral.libroBalanceGeneral2(param29);
                rs29 = (ResultSet) objetos29.get(0);
                cst29 = (CallableStatement) objetos29.get(1);
                if (rs29 != null && cst29 != null) {
                    while (rs29.next()) {
                        saldo29 = rs29.getBigDecimal(4);
                        System.out.println(saldo29 + "  29");
                    }
                }

                ArrayList param19 = new ArrayList();
                param19.add(OracleTypes.CURSOR);
                param19.add(compania); //numero de la empresa o compa;ia
                param19.add(ano); //ano
                param19.add("19"); //ano
                ArrayList objetos19 = new ArrayList();
                objetos19 = DaoBalanceGeneral.libroBalanceGeneral2(param19);
                rs19 = (ResultSet) objetos19.get(0);
                cst19 = (CallableStatement) objetos19.get(1);
                if (rs19 != null && cst19 != null) {
                    while (rs19.next()) {
                        saldo19 = rs19.getBigDecimal(4);
                        System.out.println(saldo19 + "  19");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  33 ***********************/
                ArrayList param33 = new ArrayList();
                param33.add(OracleTypes.CURSOR);
                param33.add(compania); //numero de la empresa o compa;ia
                param33.add(ano); //ano
                param33.add("33"); //ano
                ArrayList objetos33 = new ArrayList();
                objetos33 = DaoBalanceGeneral.libroBalanceGeneral2(param33);
                rs33 = (ResultSet) objetos33.get(0);
                cst33 = (CallableStatement) objetos33.get(1);
                if (rs33 != null && cst33 != null) {
                    while (rs33.next()) {
                        saldo33 = rs33.getBigDecimal(4);
                        System.out.println(saldo33 + "  33");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param39 = new ArrayList();
                param39.add(OracleTypes.CURSOR);
                param39.add(compania); //numero de la empresa o compa;ia
                param39.add(ano); //ano
                param39.add("39"); //ano
                ArrayList objetos39 = new ArrayList();
                objetos39 = DaoBalanceGeneral.libroBalanceGeneral2(param39);
                rs39 = (ResultSet) objetos39.get(0);
                cst39 = (CallableStatement) objetos39.get(1);
                if (rs39 != null && cst39 != null) {
                    while (rs39.next()) {
                        saldo39 = rs39.getBigDecimal(4);
                        System.out.println(saldo39 + "  39");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param34 = new ArrayList();
                param34.add(OracleTypes.CURSOR);
                param34.add(compania); //numero de la empresa o compa;ia
                param34.add(ano); //ano
                param34.add("34"); //ano
                ArrayList objetos34 = new ArrayList();
                objetos34 = DaoBalanceGeneral.libroBalanceGeneral2(param34);
                rs34 = (ResultSet) objetos34.get(0);
                cst34 = (CallableStatement) objetos34.get(1);
                if (rs34 != null && cst34 != null) {
                    while (rs34.next()) {
                        saldo34 = rs34.getBigDecimal(4);
                        System.out.println(saldo34 + "  34");
                    }
                }
                //************fin **************************************/


                /**************** vuenta  42 ***********************/
                ArrayList param42 = new ArrayList();
                param42.add(OracleTypes.CURSOR);
                param42.add(compania); //numero de la empresa o compa;ia
                param42.add(ano); //ano
                param42.add("42"); //ano
                ArrayList objetos42 = new ArrayList();
                objetos42 = DaoBalanceGeneral.libroBalanceGeneral2(param42);
                rs42 = (ResultSet) objetos42.get(0);
                cst42 = (CallableStatement) objetos42.get(1);
                if (rs42 != null && cst42 != null) {
                    while (rs42.next()) {
                        saldo42 = rs42.getBigDecimal(4);
                        System.out.println(saldo42 + "  42");
                    }
                }
                //************fin **************************************/
                
                ArrayList param45 = new ArrayList();
                param45.add(OracleTypes.CURSOR);
                param45.add(compania); //numero de la empresa o compa;ia
                param45.add(ano); //ano
                param45.add("45"); //ano
                ArrayList objetos45 = new ArrayList();
                objetos45 = DaoBalanceGeneral.libroBalanceGeneral2(param45);
                rs45 = (ResultSet) objetos45.get(0);
                cst45 = (CallableStatement) objetos45.get(1);
                if (rs45 != null && cst45 != null) {
                    while (rs45.next()) {
                        saldo45 = rs45.getBigDecimal(4);
                        System.out.println(saldo45 + "  45");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param46 = new ArrayList();
                param46.add(OracleTypes.CURSOR);
                param46.add(compania); //numero de la empresa o compa;ia
                param46.add(ano); //ano
                param46.add("46"); //ano
                ArrayList objetos46 = new ArrayList();
                objetos46 = DaoBalanceGeneral.libroBalanceGeneral2(param46);
                rs46 = (ResultSet) objetos46.get(0);
                cst46 = (CallableStatement) objetos46.get(1);
                if (rs46 != null && cst46 != null) {
                    while (rs46.next()) {
                        saldo46 = rs46.getBigDecimal(4);
                        System.out.println(saldo46 + "  saldo 46");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  50 ***********************/
                ArrayList param52 = new ArrayList();
                param52.add(OracleTypes.CURSOR);
                param52.add(compania); //numero de la empresa o compa;ia
                param52.add(ano); //ano
                param52.add("50"); //ano
                ArrayList objetos50 = new ArrayList();
                objetos50 = DaoBalanceGeneral.libroBalanceGeneral2(param52);
                rs50 = (ResultSet) objetos50.get(0);
                cst50 = (CallableStatement) objetos50.get(1);
                if (rs50 != null && cst50 != null) {
                    while (rs50.next()) {
                        saldo50 = rs50.getBigDecimal(4);
                        System.out.println(saldo50 + "  50");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  69 ***********************/
                ArrayList param56 = new ArrayList();
                param56.add(OracleTypes.CURSOR);
                param56.add(compania); //numero de la empresa o compa;ia
                param56.add(ano); //ano
                param56.add("56"); //ano
                ArrayList objetos56 = new ArrayList();
                objetos56 = DaoBalanceGeneral.libroBalanceGeneral2(param56);
                rs56 = (ResultSet) objetos56.get(0);
                cst56 = (CallableStatement) objetos56.get(1);
                if (rs56 != null && cst56 != null) {
                    while (rs56.next()) {
                        saldo56 = rs56.getBigDecimal(4);
                        System.out.println(saldo56 + "  56");
                    }
                }
                //************fin **************************************/

                /**************** vuenta  57 ***********************/
                ArrayList param57 = new ArrayList();
                param57.add(OracleTypes.CURSOR);
                param57.add(compania); //numero de la empresa o compa;ia
                param57.add(ano); //ano
                param57.add("88"); //ano
                ArrayList objetos57 = new ArrayList();
                objetos57 = DaoBalanceGeneral.libroBalanceGeneral2(param57);
                rs57 = (ResultSet) objetos57.get(0);
                cst57 = (CallableStatement) objetos57.get(1);
                if (rs57 != null && cst57 != null) {
                    while (rs57.next()) {
                        saldo57 = rs57.getBigDecimal(4);
                        System.out.println(saldo57 + "  57");
                    }
                }
                //************fin **************************************/



                ArrayList param58 = new ArrayList();
                param58.add(OracleTypes.CURSOR);
                param58.add(compania); //numero de la empresa o compa;ia
                param58.add(ano); //ano
                param58.add("58"); //ano
                ArrayList objetos58 = new ArrayList();
                objetos58 = DaoBalanceGeneral.libroBalanceGeneral2(param58);
                rs58 = (ResultSet) objetos58.get(0);
                cst58 = (CallableStatement) objetos58.get(1);
                if (rs58 != null && cst58 != null) {
                    while (rs58.next()) {
                        saldo58 = rs58.getBigDecimal(4);
                        System.out.println(saldo58 + "  58");
                    }
                }
                //************fin **************************************/



                ArrayList param59 = new ArrayList();
                param59.add(OracleTypes.CURSOR);
                param59.add(compania); //numero de la empresa o compa;ia
                param59.add(ano); //ano
                param59.add("59"); //ano
                ArrayList objetos59 = new ArrayList();
                objetos59 = DaoBalanceGeneral.libroBalanceGeneral2(param59);
                rs59 = (ResultSet) objetos59.get(0);
                cst59 = (CallableStatement) objetos59.get(1);
                if (rs59 != null && cst59 != null) {
                    while (rs59.next()) {
                        saldo59 = rs59.getBigDecimal(4);
                        System.out.println(saldo59 + "  59");
                    }
                }
                //************fin ******************

                //************fin **************************************/



                ArrayList param120 = new ArrayList();
                param120.add(OracleTypes.CURSOR);
                param120.add(compania); //numero de la empresa o compa;ia
                param120.add(ano); //ano
                param120.add("120"); //ano
                ArrayList objetos120 = new ArrayList();
                objetos120 = DaoBalanceGeneral.libroBalanceGeneeral3(param120);
                rs120 = (ResultSet) objetos120.get(0);
                cst120 = (CallableStatement) objetos120.get(1);
                if (rs120 != null && cst120 != null) {
                    while (rs120.next()) {
                        saldo120 = rs120.getBigDecimal(4);
                        System.out.println(saldo120 + "  120");
                    }
                }
                //************fin ******************


                ArrayList param40 = new ArrayList();
                param40.add(OracleTypes.CURSOR);
                param40.add(compania); //numero de la empresa o compa;ia
                param40.add(ano); //ano
                param40.add("40"); //ano
                ArrayList objetos40 = new ArrayList();
                objetos40 = DaoBalanceGeneral.libroBalanceGeneeral4(param40);
                rs40 = (ResultSet) objetos40.get(0);
                cst40 = (CallableStatement) objetos40.get(1);

                if (rs40 != null && cst40 != null) {
                    while (rs40.next()) {

                        // saldo40A =;
                        if (rs40.getBigDecimal(4).compareTo(BigDecimal.ZERO) == 1) {
                            saldo40A = (rs40.getBigDecimal(4)).add(saldo40A);
                        } else {

                            saldo40P = (rs40.getBigDecimal(4)).add(saldo40P);
                        }

                        //System.out.println(saldo120 + "  40");
                    }
                    System.out.println(saldo40P + "  40P");
                    System.out.println(saldo40A + "  40A");
                }

                ArrayList param41 = new ArrayList();
                param41.add(OracleTypes.CURSOR);
                param41.add(compania); //numero de la empresa o compa;ia
                param41.add(ano); //ano
                param41.add("41"); //ano
                ArrayList objetos41 = new ArrayList();
                objetos41 = DaoBalanceGeneral.libroBalanceGeneral2(param41);
                rs41 = (ResultSet) objetos41.get(0);
                cst41 = (CallableStatement) objetos40.get(1);
                if (rs41 != null && cst41 != null) {
                    while (rs41.next()) {
                        saldo41 = rs41.getBigDecimal(4);
                        System.out.println(saldo41 + "  41");
                    }
                }

                ArrayList param47 = new ArrayList();
                param47.add(OracleTypes.CURSOR);
                param47.add(compania); //numero de la empresa o compa;ia
                param47.add(ano); //ano
                param47.add("47"); //ano
                ArrayList objetos47 = new ArrayList();
                objetos47 = DaoBalanceGeneral.libroBalanceGeneral2(param47);
                rs47 = (ResultSet) objetos47.get(0);
                cst47 = (CallableStatement) objetos47.get(1);
                if (rs47 != null && cst47 != null) {
                    while (rs47.next()) {
                        saldo47 = rs47.getBigDecimal(4);
                        System.out.println(saldo47 + "  47");
                    }
                }


                ArrayList paramACU = new ArrayList();
                paramACU.add(OracleTypes.CURSOR);
                paramACU.add(compania); //numero de la empresa o compa;ia
                paramACU.add(ano); //ano
                //   paramACU.add("XX"); //ano
                ArrayList objetosACU = new ArrayList();
                objetosACU = DaoBalanceGeneral.SaldoAcumulado(paramACU);
                rsACU = (ResultSet) objetosACU.get(0);
                cstACU = (CallableStatement) objetosACU.get(1);
                if (rsACU != null && cstACU != null) {
                    while (rsACU.next()) {
                        saldoACU = (rsACU.getBigDecimal(4)).add(saldoACU);

                    }
                    System.out.println(saldoACU + "  SALDO ACUMULADO");
                }


                BigDecimal saldo12222 = saldo120.add(saldo129);

                ve.setCajaBanco(saldo10);
                ve.setValoresNegociables(BigDecimal.ZERO);
                ve.setCuentasCobrarComerciales(saldo12.add(saldo19));
                ve.setCuentasCobrarVinculadas(saldo17);
                ve.setOtrasCuentasCobrar(saldo16.add(saldo14));
                ve.setExistencias(saldo20.add(saldo28.add(saldo29)));
                ve.setGastosPagadosAnticipado(saldo38.add(saldo18));
                /////////////******************************///////////////////

                ve.setCuentasCobrarLargoPlazo(BigDecimal.ZERO);
                ve.setCuentasCobrarVinculadasLargoPlazo(BigDecimal.ZERO);
                ve.setOtrasCuentasCobrarLargoPlazo(BigDecimal.ZERO);
                ve.setInversionesPermanetes(saldo31);
                ve.setInmueblesMaquinariaEquipo(saldo33.add(saldo39));
                ve.setActivosIntabgibles(saldo34);
                ve.setParticipacionesDiferidosActivo(BigDecimal.ZERO);
                ve.setOtrosActivos(saldo40A);

                //////******//////////////////////////
                ve.setSobregirosPagaresBancarios(BigDecimal.ZERO);
                ve.setCuentasPagarComerciales(saldo42.negate());
                ve.setCuentasPagarVinculadas(BigDecimal.ZERO);
                ve.setOtrasCuentasPagar((saldo40P.add(saldo41.add(saldo46.add(saldo47)))).negate());
                ve.setParteCorrienteDeudasLargoPlazo(saldo45.negate());
                ///***/////////////

                ////****************//
                ve.setDeudasLargoPlazo(BigDecimal.ZERO);
                ve.setCuentasPorPagarVinculadas(BigDecimal.ZERO);
                ve.setIngresosDiferidos(BigDecimal.ZERO);
                ve.setImpuestoRenta(BigDecimal.ZERO);
                ///***//////////////

                ve.setContingencias((saldo48.add(saldo49)).negate()); //saldo73.add(saldo75.add(saldo76)
                ve.setInteresMinoritario(BigDecimal.ZERO);

                ////////////////************//////////////////////////
                ve.setCapital(saldo50.negate());
                ve.setCapitalAdicional(saldo56);
                ve.setAccionesInversion(BigDecimal.ZERO);
                ve.setExcedentesRevaluación(BigDecimal.ZERO);
                ve.setReservaLegales(saldo58.negate());
                ve.setOtrasResevas(BigDecimal.ZERO);
                ve.setResultadosAcumulados((saldo59.subtract(saldoACU)).negate());


                /****************/////////////
                LibroBalanceGeneral.addBalance(ve);



                Map parameters = new HashMap();
                parameters.put("EJERCICIO", ano);
                parameters.put("P_RUC", ruc);
                parameters.put("P_RAZON", empresa);
                parameters.put("ANNO", ano);
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);


                String rutarptcaja = "/com/contab/report/formatoBalanceGeneral.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, LibroBalanceGeneral, rutarptcaja, "Caja");
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("BALANCE GENERAL!!");

                ex.show();
                JOptionPane.showMessageDialog(null, "BALANCE GENERAL!!", "ALERT", JOptionPane.INFORMATION_MESSAGE);

                //cnx.destroy();

            } catch (SQLException ex) {
                System.out.print(ex.getErrorCode() + "  " + ex.getNextException() + "" + ex.getMessage());

            } finally {
                //cnx.destroy();
            }


        }
    }
}
