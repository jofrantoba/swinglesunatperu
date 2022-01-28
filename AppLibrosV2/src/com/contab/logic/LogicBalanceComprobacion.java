/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanBalanceComprobacion;
import com.contab.bean.BeanCuenta;
import com.contab.dao.Conexion;
import com.contab.dao.DaoBalanceComprobacion;
import com.contab.dao.DaoCuenta;
import com.contab.dao.DaoEmpresas;
import com.contab.datasource.DatasourceBalanceComprobacion;
import com.contab.util.Exportar;
import com.contab.view.ViewApplication;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRParameter;
import oracle.jdbc.OracleTypes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author cixtic04
 */
public class LogicBalanceComprobacion {

    public static void listaBalanceComprobacion(String empresa, String ruc, String compania, String ano, File ruta, String tipoExpor) throws SQLException, Exception {

        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            Conexion cnx = null;
            try {

                DatasourceBalanceComprobacion dataBalance = new DatasourceBalanceComprobacion();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                param.add(ano); //ano
                ArrayList objetos = new ArrayList();
                objetos = DaoBalanceComprobacion.consultaBalanceComprobacion(param);
                rs = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                cnx = (Conexion) objetos.get(2);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO BALANCE Y COMPROBACIÓN Nª: " + empresa + " DEL PERIODO: " + ano);


                if (rs != null && cst != null && cnx != null) {
                    while (rs.next()) {

                        BeanBalanceComprobacion bnBalanceComprobacion = new BeanBalanceComprobacion();

                        String cuenta = rs.getString(1);
                        // if(Integer.parseInt(cuenta)<891  ){
                        BeanCuenta bCuenta = new BeanCuenta();
                        bCuenta = LogicCuenta.Cuenta_Actual(cuenta, ano, compania);
                        BigDecimal debeMovimiento = rs.getBigDecimal(2);
                        BigDecimal haberMovimiento = rs.getBigDecimal(3);
                        BigDecimal debeSaldoInicial = rs.getBigDecimal(4);
                        BigDecimal HaberSaldoInicial = rs.getBigDecimal(5);
                        BigDecimal flag = rs.getBigDecimal(6);


                        bnBalanceComprobacion.setCuenta(cuenta);
                        bnBalanceComprobacion.setDenominacion(bCuenta.getDenominacion());
                        bnBalanceComprobacion.setDebeMovimiento(debeMovimiento);
                        bnBalanceComprobacion.setHaberMovimiento(haberMovimiento);
                        bnBalanceComprobacion.setDebeSaldoInicial(debeSaldoInicial);
                        bnBalanceComprobacion.setHaberSalidoInicial(HaberSaldoInicial);
                        bnBalanceComprobacion.setFlag(flag);

                        BigDecimal MDebe = debeSaldoInicial.add(debeMovimiento);
                        BigDecimal MHaber = HaberSaldoInicial.add(haberMovimiento);
                        BigDecimal condi = MDebe.subtract(MHaber);
                        BigDecimal SDebe = BigDecimal.ZERO;
                        ///DEBE  SALDO 
                        if (condi.compareTo(BigDecimal.ZERO) == 1) { //saldo es mayor de 0 entonces 1
                            SDebe = condi;

                        }
                        BigDecimal SHaber = BigDecimal.ZERO;
                        if (condi.compareTo(BigDecimal.ZERO) == -1) { //saldo es mayor de 0 entonces 1
                            SHaber = MHaber.subtract(MDebe);


                        }
                        BigDecimal TDebe = BigDecimal.ZERO;
                        BigDecimal CDebe = BigDecimal.ZERO;
                        BigDecimal THaber = BigDecimal.ZERO;
                        BigDecimal CHaber = BigDecimal.ZERO;
                        //M S T F R
                        BigDecimal condi1 = SDebe.subtract(SHaber);
                        BigDecimal condi2 = TDebe.subtract(THaber);
                        BigDecimal condi3 = condi1.add(condi2);

                        BigDecimal RDebe = BigDecimal.ZERO;
                        BigDecimal RHaber = BigDecimal.ZERO;



                        if (Integer.parseInt(cuenta) < 601) {

                            //ACTIVOS 
                            if (condi3.compareTo(BigDecimal.ZERO) == 1) { //saldo es mayor de 0 entonces 1
                                CDebe = condi;

                            }
                            //PASIVOS

                            if (condi3.compareTo(BigDecimal.ZERO) == -1) { //saldo es mayor de 0 entonces 1
                                CHaber = condi.abs();


                            }

                        } else {
                            //ACTIVOS 
                            if (condi3.compareTo(BigDecimal.ZERO) == 1) { //saldo es mayor de 0 entonces 1
                                RDebe = condi;

                            }
                            //PASIVOS

                            if (condi3.compareTo(BigDecimal.ZERO) == -1) { //saldo es mayor de 0 entonces 1
                                RHaber = condi.abs();


                            }
                        }


                        bnBalanceComprobacion.setMDebe(MDebe);
                        bnBalanceComprobacion.setMHaber(MHaber);
                        bnBalanceComprobacion.setSDebe(SDebe);
                        bnBalanceComprobacion.setSHaber(SHaber);
                        bnBalanceComprobacion.setTDebe(TDebe);
                        bnBalanceComprobacion.setTHaber(THaber);
                        bnBalanceComprobacion.setFDebe(CDebe);
                        bnBalanceComprobacion.setFHaber(CHaber);
                        bnBalanceComprobacion.setRDebe(RDebe);
                        bnBalanceComprobacion.setRHaber(RHaber);


                        dataBalance.addBalance(bnBalanceComprobacion);

                    }
                }
                //  }


                Map parameters = new HashMap();
                parameters.put("RAZON", empresa);
                parameters.put("RUC", ruc);
                parameters.put("PERIODO", ano);
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptcaja = "/com/contab/report/BalanceComprobacion1.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataBalance, rutarptcaja);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE DEL LIBRO BALANCE Y COMPROBACIÓN !!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE DEL LIBRO BALANCE Y COMPROBACIÓN ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
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

    public static void listaBalanceComprobacionSunat(String empresa, String ruc, String compania, String ano, File ruta, String tipoExpor) throws SQLException, Exception {

        try {

            System.out.println(ruta + " archivo");


            /////////////****************** lista de repositorios **************///////////////////

            ArrayList<ArrayList<BeanBalanceComprobacion>> repositorioDigito2 = new ArrayList<ArrayList<BeanBalanceComprobacion>>();
            repositorioDigito2 = null;
            //if (result != null) {
            repositorioDigito2 = repositorioDigito2(compania, ano);

            /*ArrayList<ArrayList<BeanBalanceComprobacion>> repositorioDigito3 = new ArrayList<ArrayList<BeanBalanceComprobacion>>();
            repositorioDigito3 = null;
            //if (result != null) {
            repositorioDigito3 = repositorioDigito3(compania, ano, ora);
            
            
            ArrayList<ArrayList<BeanBalanceComprobacion>> repositorioDigito4 = new ArrayList<ArrayList<BeanBalanceComprobacion>>();
            repositorioDigito4 = null;
            //if (result != null) {
            repositorioDigito4 = repositorioDigito4(compania, ano, ora);
            
            
            ArrayList<ArrayList<BeanBalanceComprobacion>> repositorioDigito5 = new ArrayList<ArrayList<BeanBalanceComprobacion>>();
            repositorioDigito5 = null;
            //if (result != null) {
            repositorioDigito5 = repositorioDigito5(compania, ano, ora);*/



/////////////////////////////////////************fin*//////////////////


            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(ruta));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheet("BC_PCGEmpresarial");


            Iterator rowIterator = sheet.rowIterator();

            int contar = 0;
            int contarC = 0;
            int contarInsert = 0;
            //    List cellDataList = new ArrayList();

            BigDecimal DebeMovimiento = BigDecimal.ZERO;
            BigDecimal HaberMovimiento = BigDecimal.ZERO;
            BigDecimal DebeSaldoInicial = BigDecimal.ZERO;
            BigDecimal HaberSalidoInicial = BigDecimal.ZERO;
            BigDecimal DebeMovimiento1 = BigDecimal.ZERO;
            BigDecimal DebeMovimiento2 = BigDecimal.ZERO;

            ArrayList<BeanBalanceComprobacion> ArrayCuentasIngresadas = new ArrayList<BeanBalanceComprobacion>();
            ArrayList<BeanBalanceComprobacion> ArrayCuentasNOIngresadas = new ArrayList<BeanBalanceComprobacion>();
            BeanBalanceComprobacion beanBuscado;


            while (rowIterator.hasNext()) {  // fila

                HSSFRow hssfRow = (HSSFRow) rowIterator.next();

                //  Iterator iterator = hssfRow.cellIterator();// filas 

                //   List cellTempList = new ArrayList();
                contarC = contarC + 1;
                //&& contarC < 100
                if (contarC > 4) {

                    //  while (iterator.hasNext()) { // columnas 

                    //  String cuenta = hssfRow.getCell(0);

                    HSSFCell cuentax = hssfRow.getCell(0);
                    String nombre = hssfRow.getCell(1).toString();
                    String valorP = "";
                    if (!nombre.equals("SUB-TOTALES") && !nombre.equals("TOTALES") && !nombre.equals("DIFERENCIAS") && !nombre.equals(" ")) {
                        int type = hssfRow.getCell(0).getCellType();

                        if (type == 1) {
                            valorP = cuentax.toString();
                        } else if (type == 3) {
                            //   System.out.println(hssfRow.getCell(0)+" holas");
                            double valorPx = hssfRow.getCell(0).getNumericCellValue();
                            valorP = Double.toString(valorPx);
                        } else if (type == 0) {
                            //  System.out.println(hssfRow.getCell(0) + " holas");
                            double valorPx = hssfRow.getCell(0).getNumericCellValue();

                            BigDecimal big = new BigDecimal(valorPx);
                            big = big.setScale(0, RoundingMode.UP);

                            valorP = big.toString();
                        }
                        // System.out.println(valorP + "cuenta" + "type " + type);
                    }


                    String valorxx = cuentax.toString();
                    // getNumericCellValue () 
                    String strx = nombre;
                    // cuentax+valorxx+" numero cuenta");

                    /*if (valorxx != null) {
                    if (nombre != null) {
                    
                    String[] tokens = strx.split(valorxx + ".");
                    //1911. Cuentas por cobrar comerciales – Terceros - Facturas, boletas y otros comprobantes por cobrar
                    if (tokens != null) {
                    System.out.println(cuentax + " nombre " + tokens[1] + " ");
                    }
                    }
                    }*/


                    beanBuscado = buscarInRepositorio(repositorioDigito2, valorP.trim());
                    //    repositorioDigito2.remove(beanBuscado);
                    if (!beanBuscado.getCuenta().equals("xxxxxxxxxxxx")) {
                        contarInsert = contarInsert + 1;
                        DebeMovimiento = beanBuscado.getDebeMovimiento();
                        HaberMovimiento = beanBuscado.getHaberMovimiento();
                        DebeSaldoInicial = beanBuscado.getDebeSaldoInicial();
                        HaberSalidoInicial = beanBuscado.getHaberSalidoInicial();
                        DebeMovimiento1 = BigDecimal.ZERO;
                        DebeMovimiento2 = BigDecimal.ZERO;
                        BeanBalanceComprobacion beanP2I = new BeanBalanceComprobacion();

                        beanP2I.setCuenta(valorP.trim());
                        ArrayCuentasIngresadas.add(beanP2I);

                    } else {
                        BeanBalanceComprobacion beanP2I2 = new BeanBalanceComprobacion();

                        beanP2I2.setCuenta(valorP.trim());
                        ArrayCuentasNOIngresadas.add(beanP2I2);

                        //  ArrayCuentasIngresadas.add(beanP2I2);

                        DebeMovimiento = BigDecimal.ZERO;
                        HaberMovimiento = BigDecimal.ZERO;
                        DebeSaldoInicial = BigDecimal.ZERO;
                        HaberSalidoInicial = BigDecimal.ZERO;
                        DebeMovimiento1 = BigDecimal.ZERO;
                        DebeMovimiento2 = BigDecimal.ZERO;
                    }






                    for (int i = 0; i < 12; i++) {
                        contar = contar + 1;


                        if (!nombre.equals("SUB-TOTALES") && !nombre.equals("TOTALES") && !nombre.equals("DIFERENCIAS") && !nombre.equals(" ")) {

                            if (contar == 3) {
                                //getBooleanCellValue () 

                                HSSFCell cell = hssfRow.getCell((short) 2);



                                if (cell == null) {
                                    cell = hssfRow.createCell((short) 2);

                                }

                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(DebeMovimiento.doubleValue());
                            }

                            if (contar == 4) {



                                HSSFCell cell = hssfRow.getCell((short) 3);

                                if (cell == null) {
                                    cell = hssfRow.createCell((short) 3);
                                }

                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                // HSSFRichTextString str = new HSSFRichTextString("15"); //aca colocar el valor       
                                cell.setCellValue(HaberMovimiento.doubleValue());
                            }

                            if (contar == 5) {


                                HSSFCell cell = hssfRow.getCell((short) 4);

                                if (cell == null) {
                                    cell = hssfRow.createCell((short) 4);
                                }

                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                // HSSFRichTextString str = new HSSFRichTextString("15"); //aca colocar el valor       
                                cell.setCellValue(DebeSaldoInicial.doubleValue());
                            }
                            if (contar == 6) {


                                HSSFCell cell = hssfRow.getCell((short) 5);

                                if (cell == null) {
                                    cell = hssfRow.createCell((short) 5);
                                }

                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(HaberSalidoInicial.doubleValue());
                            }
                            if (contar == 11) {


                                HSSFCell cell = hssfRow.getCell((short) 10);

                                if (cell == null) {
                                    cell = hssfRow.createCell((short) 10);
                                }

                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                HSSFRichTextString str = new HSSFRichTextString("15"); //aca colocar el valor       
                                cell.setCellValue(DebeMovimiento1.doubleValue());
                            }
                            if (contar == 12) {


                                HSSFCell cell = hssfRow.getCell((short) 11);

                                if (cell == null) {
                                    cell = hssfRow.createCell((short) 11);
                                }

                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                HSSFRichTextString str = new HSSFRichTextString("15"); //aca colocar el valor       
                                cell.setCellValue(DebeMovimiento2.doubleValue());
                            }

                        }

                        //  cellTempList.add(hssfCell);

                        //  }




                    }

                }



                // System.out.println(contar + "contar columnas ");

                contar = 0;
                //cellDataList.add(cellTempList);
            }

            // Set<String> set = new HashSet<String>();
            ArrayList<ArrayList<BeanBalanceComprobacion>> repositorioxxx = new ArrayList<ArrayList<BeanBalanceComprobacion>>();
            repositorioxxx = null;
            //  repositorioxxx = romverRegistro(repositorioDigito2, ArrayCuentasIngresadas);
            repositorioxxx = NoIngresadosRegistro(repositorioDigito2, ArrayCuentasIngresadas);

            //epositorioxxx.addAll(repositorioDigito2);
            String unidad = ruta.getParent();

            GuardarCuentaNoIncluidas(repositorioxxx, unidad);
            System.out.println(contarC + "contar filas ");
            sheet.setForceFormulaRecalculation(true);
            FileOutputStream fileOut = new FileOutputStream(ruta);
            wb.write(fileOut);
            fileOut.flush();


            System.out.println(ruta.getAbsolutePath() + "-----" + ruta.getParent() + "--" + ruta.getName() + " " + ruta.getAbsolutePath());
            int falta = contarC - contarInsert;

            String excelValor = "cmd /c start " + ruta.toString();

            //cmd /c start E:\\balanceC.xls"
            fileOut.close();
            Runtime.getRuntime().exec(excelValor);

            JOptionPane.showMessageDialog(null, "Total de CUENTA "
                    + "  : " + contarC + "  \n"
                    + "Se han insert: " + contarInsert + " registros \n"
                    + "Se han falta: " + falta + " registros", "Informe de Operación", JOptionPane.INFORMATION_MESSAGE);


        } catch (Exception e) {
        }
    }

    public static ArrayList<ArrayList<BeanBalanceComprobacion>> repositorioDigito2(String CodCompania, String anno) throws SQLException {
        ArrayList<ArrayList<BeanBalanceComprobacion>> repositorio = new ArrayList<ArrayList<BeanBalanceComprobacion>>();
        int val = 30000;
        ArrayList<BeanBalanceComprobacion> arrayMM;
        int contador = 0;
        int cont = 2;
        ResultSet rs = null;
        CallableStatement cst = null;
        //  repositorio=null;

        try {

            ArrayList paramMM = new ArrayList();
            paramMM.add(OracleTypes.CURSOR);
            paramMM.add(CodCompania);
            paramMM.add(anno);

            ArrayList objetos = DaoBalanceComprobacion.balancecomprobacionDigitos2(paramMM);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null) {
                //System.out.println("repositorio 2"); 1014   G/R-021-0043272 observacion
                arrayMM = new ArrayList<BeanBalanceComprobacion>();

                //  BeanBalanceComprobacion beanP2 = new BeanBalanceComprobacion();


                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(arrayMM);

                        contador = 0;
                        cont = cont + 1;
                    }

                    BeanBalanceComprobacion beanP = new BeanBalanceComprobacion();
                    BigDecimal debeMovimiento = rs.getBigDecimal(2);
                    BigDecimal haberMovimiento = rs.getBigDecimal(3);
                    BigDecimal debeSaldoInicial = rs.getBigDecimal(4);
                    BigDecimal HaberSaldoInicial = rs.getBigDecimal(5);
                    BigDecimal flag = rs.getBigDecimal(6);

                    String cuenta = rs.getString(1).trim();
                    //if(cuenta.length()>3){
                    String cuentaxx = cuenta.substring(0, 3);
                    System.out.println("cuenta R " + cuentaxx + " " + debeMovimiento);

                    BeanCuenta bCuenta = new BeanCuenta();
                    // bCuenta = LogicCuenta.Cuenta_Actual(cuenta, anno, CodCompania);

                    //if(cuenta)
                    if (Integer.parseInt(cuentaxx) < 600) {
                        beanP.setCuenta(cuenta.trim());
                        beanP.setDenominacion(bCuenta.getDenominacion());
                        beanP.setDebeMovimiento(debeMovimiento.setScale(0, BigDecimal.ROUND_HALF_UP));
                        beanP.setHaberMovimiento(haberMovimiento.setScale(0, BigDecimal.ROUND_HALF_UP));
                        beanP.setDebeSaldoInicial(debeSaldoInicial.setScale(0, BigDecimal.ROUND_DOWN));
                        beanP.setHaberSalidoInicial(HaberSaldoInicial.setScale(0, BigDecimal.ROUND_DOWN));
                        beanP.setFlag(flag);

                    } else {
                        beanP.setCuenta(cuenta.trim());
                        beanP.setDenominacion(bCuenta.getDenominacion());
                        beanP.setDebeMovimiento(BigDecimal.ZERO);
                        beanP.setHaberMovimiento(BigDecimal.ZERO);
                        beanP.setDebeSaldoInicial(debeSaldoInicial.setScale(0, BigDecimal.ROUND_DOWN));
                        beanP.setHaberSalidoInicial(HaberSaldoInicial.setScale(0, BigDecimal.ROUND_DOWN));
                        beanP.setFlag(flag);

                    }

                    arrayMM.add(beanP);
                }
                // }
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

    public static ArrayList<ArrayList<BeanCuenta>> actualizacionesCuenta(String CodCompania, String anno) throws SQLException {
        ArrayList<ArrayList<BeanCuenta>> repositorio = new ArrayList<ArrayList<BeanCuenta>>();
        int val = 30000;
        ArrayList<BeanCuenta> arrayMM;
        int contador = 0;
        int cont = 2;
        ResultSet rs = null;
        CallableStatement cst = null;
        try {

            ArrayList paramMM = new ArrayList();
            paramMM.add(OracleTypes.CURSOR);
            paramMM.add(CodCompania);
            paramMM.add(anno);

            ArrayList objetos = DaoCuenta.Cuenta_Actual(paramMM);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null) {
                //System.out.println("repositorio 2"); 1014   G/R-021-0043272 observacion
                arrayMM = new ArrayList<BeanCuenta>();

                BeanCuenta beanP2 = new BeanCuenta();


                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(arrayMM);

                        contador = 0;
                        cont = cont + 1;
                    }

                    BeanCuenta beanC = new BeanCuenta();

                    beanC.setNumero(rs.getString(2));
                    beanC.setCuenta(rs.getString(2));
                    beanC.setNuevonumero(rs.getString(2));
                    beanC.setNuevoCuenta(rs.getString(2));



                    arrayMM.add(beanC);
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

    public static ArrayList<ArrayList<BeanBalanceComprobacion>> romverRegistro(ArrayList<ArrayList<BeanBalanceComprobacion>> repor, ArrayList<BeanBalanceComprobacion> cuentasIngresadas) throws SQLException {

        ArrayList<ArrayList<BeanBalanceComprobacion>> copia = new ArrayList<ArrayList<BeanBalanceComprobacion>>();
        ArrayList<BeanBalanceComprobacion> listaNueva = new ArrayList<BeanBalanceComprobacion>();
        try {
            BeanBalanceComprobacion bean = new BeanBalanceComprobacion();

            BeanBalanceComprobacion beanaux = new BeanBalanceComprobacion();
            ArrayList<BeanBalanceComprobacion> array = null;
            int contar = -1;

            //copia.addAll(repor);
            String valorxrayCuenta = "";
            String valorExcelCuenta = "";
            for (int i = 0; i < repor.size(); i++) {

                array = (ArrayList<BeanBalanceComprobacion>) repor.get(i);
                Iterator iterar = array.iterator();
                contar = contar + 1;
                while (iterar.hasNext()) {

                    int cantidadR = 0;
                    beanaux = (BeanBalanceComprobacion) iterar.next();
                    for (int k = 0; k < cuentasIngresadas.size(); k++) {

                        BeanBalanceComprobacion beanauxXX = new BeanBalanceComprobacion();
                        beanauxXX = (BeanBalanceComprobacion) cuentasIngresadas.get(k);
                        valorxrayCuenta = beanaux.getCuenta().trim();
                        valorExcelCuenta = beanauxXX.getCuenta().trim();

                        if (valorxrayCuenta.substring(0, 3).trim().equals(valorExcelCuenta.substring(0, 3).trim())) {
                            System.out.println("eliminado cuenta " + beanaux.getCuenta() + "  " + beanauxXX.getCuenta());
                            // repor.remove(contar);
                            cantidadR = 1;
                            break;

                        } else {
                            // array.remove(beanaux);
                            // bean=null;
                            bean = beanaux;
                            //bean=null;
                            System.out.println("eliminado no  " + beanaux.getCuenta());
                            cantidadR = 0;

                        }
                    }
                    if (cantidadR == 0) {

                        if (beanaux.getCuenta().equals("")) {
                            listaNueva.add(beanaux);
                        }
                        // bean=null;
                        // copia.add(listaNueva);

                    }
                }
            }
            copia.add(listaNueva);


            //repositorio.add(array);


        } catch (Exception ex) {
        } finally {
            //  cst.close();
        }
        return copia;
    }

    public static ArrayList<ArrayList<BeanBalanceComprobacion>> NoIngresadosRegistro(ArrayList<ArrayList<BeanBalanceComprobacion>> repor, ArrayList<BeanBalanceComprobacion> cuentasIngresadas) throws SQLException {

        ArrayList<ArrayList<BeanBalanceComprobacion>> copia = new ArrayList<ArrayList<BeanBalanceComprobacion>>();
        ArrayList<BeanBalanceComprobacion> listaNueva = new ArrayList<BeanBalanceComprobacion>();

        //HashSet<BeanBalanceComprobacion> listaNOIngresados = new HashSet();  /// lista unicas

        try {
            BeanBalanceComprobacion bean = new BeanBalanceComprobacion();

            BeanBalanceComprobacion beanaux = new BeanBalanceComprobacion();
            ArrayList<BeanBalanceComprobacion> array = null;
            int contar = -1;

            //copia.addAll(repor);
            String valorxrayCuenta = "";
            String valorExcelCuenta = "";
            for (int i = 0; i < repor.size(); i++) {

                array = (ArrayList<BeanBalanceComprobacion>) repor.get(i);
                Iterator iterar = array.iterator();
                contar = contar + 1;
                while (iterar.hasNext()) {

                    int cantidadR = 0;
                    beanaux = (BeanBalanceComprobacion) iterar.next();  // repositorio de xray 
                    valorxrayCuenta = beanaux.getCuenta().trim();

                    for (int k = 0; k < cuentasIngresadas.size(); k++) {
                        BeanBalanceComprobacion beanauxXX = new BeanBalanceComprobacion();
                        beanauxXX = (BeanBalanceComprobacion) cuentasIngresadas.get(k);
                        // repositorio de xray 
                        valorExcelCuenta = beanauxXX.getCuenta().trim(); //repositori del excel ingresados 

                        if (valorxrayCuenta.equals(valorExcelCuenta)) {
                            //   System.out.println("eliminado cuenta " + beanaux.getCuenta() + "  " + beanauxXX.getCuenta());
                            // repor.remove(contar);
                            cantidadR = 1;
                            break;

                        } else {
                            // array.remove(beanaux);
                            // bean=null;
                            bean = beanaux;
                            //bean=null;
                            //  System.out.println("eliminado no  " + beanaux.getCuenta());
                            cantidadR = 0;

                        }
                    }
                    if (cantidadR == 0) {

                        if (listaNueva.contains(bean)) {

                            System.out.println("ya existe cuenta" + bean.getCuenta());
                        } else {
                            listaNueva.add(bean);
                        }
                        //    listaNOIngresados.add(bean); /// lista unica 
                        //listaNueva.add(bean);

                        // bean=null;
                        // copia.add(listaNueva);

                    }
                }
            }

            // objArray.clone();
            // ArrayList<BeanBalanceComprobacion> listaNuevacc = new ArrayList<BeanBalanceComprobacion>(listaNOIngresados);
            copia.add(listaNueva);
            //Set<String> hashset = new HashSet<String>(list); 

            //repositorio.add(array);


        } catch (Exception ex) {
        } finally {
            //  cst.close();
        }
        return copia;
    }

    public static BeanBalanceComprobacion buscarInRepositorio(ArrayList<ArrayList<BeanBalanceComprobacion>> repor, String cuenta) {
        BeanBalanceComprobacion bean = new BeanBalanceComprobacion();
        BeanBalanceComprobacion beanaux = new BeanBalanceComprobacion();
        ArrayList<BeanBalanceComprobacion> array;
        ///   System.out.println(" " + codigo + " codigoooooooooooooooo");
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanBalanceComprobacion>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanBalanceComprobacion) iterar.next();
                //   System.out.print(codigo+"--"+beanaux.getCodigoBusquedad());
                //System.out.println(beanaux.getCodigoBusquedad()+ "--"+codigo);
                if (beanaux.getCuenta().trim().equals(cuenta.trim())) {
                    //   System.out.println("eliminado repositorio 2 " + codigo);
                    array.remove(beanaux);
                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;
    }

    public static int GuardarCuentaNoIncluidas(ArrayList<ArrayList<BeanBalanceComprobacion>> repor, String unidad) {
        BeanBalanceComprobacion bean = new BeanBalanceComprobacion();
        BeanBalanceComprobacion beanaux = new BeanBalanceComprobacion();

        ArrayList<BeanBalanceComprobacion> array;
        ///   System.out.println(" " + codigo + " codigoooooooooooooooo");

        HSSFWorkbook libro = new HSSFWorkbook();
        // Se crea una hoja dentro del libro
        HSSFSheet hoja = libro.createSheet();
        // Se crea una fila dentro de la hoja

        // Se crea una celda dentro de la fila

        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanBalanceComprobacion>) repor.get(i);
            Iterator iterar = array.iterator();

            HSSFRow filax = hoja.createRow(0);


            HSSFCell celda1x = filax.createCell((short) 1);
            HSSFRichTextString texto1x = new HSSFRichTextString("Cuenta");
            celda1x.setCellValue(texto1x);
            HSSFCell celda2x = filax.createCell((short) 2);
            HSSFRichTextString texto2x = new HSSFRichTextString("DENOMINACION");
            celda2x.setCellValue(texto2x);

            HSSFCell celda2xx = filax.createCell((short) 3);
            HSSFRichTextString texto2xx = new HSSFRichTextString("SALDOS INICIALES DEBE");
            celda2xx.setCellValue(texto2xx);

            HSSFCell celda3x = filax.createCell((short) 4);
            HSSFRichTextString texto3x = new HSSFRichTextString("SALDOS INICIALES HABER");
            celda3x.setCellValue(texto3x);

            HSSFCell celda4x = filax.createCell((short) 5);
            HSSFRichTextString texto4x = new HSSFRichTextString("MOVIMIENTO DEL EJERCICIO DEBE");
            celda4x.setCellValue(texto4x);

            HSSFCell celda5x = filax.createCell((short) 6);
            HSSFRichTextString texto5x = new HSSFRichTextString("MOVIMIENTO DEL EJERCICIO HABER");
            celda5x.setCellValue(texto5x);
            int contar = 0;
            while (iterar.hasNext()) {
                contar = contar + 1;


                beanaux = (BeanBalanceComprobacion) iterar.next();
                HSSFRow fila = hoja.createRow(contar);

                HSSFCell celda1 = fila.createCell((short) 1);
                HSSFRichTextString texto1 = new HSSFRichTextString(beanaux.getCuenta());
                celda1.setCellValue(texto1);
                HSSFCell celda1X = fila.createCell((short) 2);
                HSSFRichTextString texto1X = new HSSFRichTextString(beanaux.getDenominacion());
                celda1X.setCellValue(texto1X);

                HSSFCell celda2 = fila.createCell((short) 3);
                //HSSFRichTextString texto2 = new HSSFRichTextString("hola mundo3");
                celda2.setCellValue(beanaux.getDebeMovimiento().doubleValue());

                HSSFCell celda3 = fila.createCell((short) 4);
                // HSSFRichTextString texto3 = new HSSFRichTextString("hola mundo4");
                celda3.setCellValue(beanaux.getHaberMovimiento().doubleValue());

                HSSFCell celda4 = fila.createCell((short) 5);
                // HSSFRichTextString texto4 = new HSSFRichTextString("hola mundo5");
                celda4.setCellValue(beanaux.getDebeSaldoInicial().doubleValue());

                HSSFCell celda5 = fila.createCell((short) 6);
                // HSSFRichTextString texto4 = new HSSFRichTextString("hola mundo5");
                celda5.setCellValue(beanaux.getHaberSalidoInicial().doubleValue());
                //  }





                //  System.out.println("Cuenta" + "--" + beanaux.getCuenta() + "deber " + beanaux.getDebeMovimiento());

            }
        }

        // Se crea el contenido de la celda y se mete en ella.

        // Se salva el libro.
        Date hoy = new Date();
        String valor = unidad + "RegistroNoAlmacenados" + hoy.getTime() + ".xls";
        String excelValor = "cmd /c start " + valor;
        try {
            FileOutputStream elFichero = new FileOutputStream(valor);
            libro.write(elFichero);
            elFichero.close();

            Runtime.getRuntime().exec(excelValor);
        } catch (Exception e) {
        }

        return 1;





    }
}
