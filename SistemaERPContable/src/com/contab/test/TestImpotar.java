/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 *
 * @author 777
 */
public class TestImpotar {

    public static void main(String arg[]) throws SQLException, FileNotFoundException, IOException {

        // CsvWriter writer = new CsvWriter ("informe.csv");

        /*    HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet("Instrucciones");
        
        HSSFSheet sheet2 = wb.createSheet("BC_PCGEmpresarial");
        
        // HSSFSheet sheet3 = wb.createSheet("Planilha Três");
        
        HSSFRow row = sheet2.createRow(4);  // columna
        
        
        row.createCell((short) 3).setCellValue(20);
        row.createCell((short) 4).setCellValue(40);
        
        //  String valor = row.getCell(1).toString();
        //  System.out.print("valor" + valor + "valor2 " );
        
        
        
        wb.write(stream);*/

        // POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("E:/BalanceComprobacion-2011_v2.xls")); // 

        try {


            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("E:/balanceC.xls"));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheet("BC_PCGEmpresarial");
            Iterator rowIterator = sheet.rowIterator();

            int contar = 0;
            int contarC = 0;
            //    List cellDataList = new ArrayList();
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
                    String valorP="";
               if (!nombre.equals("SUB-TOTALES") && !nombre.equals("TOTALES") && !nombre.equals("DIFERENCIAS") && !nombre.equals(" ")) {     
                    int type = hssfRow.getCell(0).getCellType();
                    if(type!=1){
                    System.out.println(type+" type");
                    }

                   if (type == 1) {
                        valorP=cuentax.toString();
                    }else if (type == 3) {
                       double valorPx=hssfRow.getCell(1).getNumericCellValue();
                       valorP=Double.toString(valorPx);
                    }
                   System.out.println(valorP);
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


                    for (int i = 0; i < 12; i++) {
                        contar = contar + 1;



                        //  HSSFCell hssfCell = (HSSFCell) iterator.next();
                        // System.out.println(hssfCell.toString() + "salor");
                        //|| !nombre.equals("TOTALES") || !nombre.equals("DIFERENCIAS")
                        if (!nombre.equals("SUB-TOTALES") && !nombre.equals("TOTALES") && !nombre.equals("DIFERENCIAS") && !nombre.equals(" ")) {

                            if (contar == 3) {
                                //getBooleanCellValue () 

                                HSSFCell cell = hssfRow.getCell((short) 2);

                              

                                if (cell == null) {
                                    cell = hssfRow.createCell((short) 2);

                                }

                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                HSSFRichTextString str = new HSSFRichTextString("15"); //aca colocar el valor       
                                cell.setCellValue(BigDecimal.valueOf(15.5).doubleValue());
                            }

                            if (contar == 4) {



                                HSSFCell cell = hssfRow.getCell((short) 3);
                               
                                if (cell == null) {
                                    cell = hssfRow.createCell((short) 3);
                                }

                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                HSSFRichTextString str = new HSSFRichTextString("15"); //aca colocar el valor       
                                cell.setCellValue(BigDecimal.valueOf(15.5).doubleValue());
                            }

                            if (contar == 5) {


                                HSSFCell cell = hssfRow.getCell((short) 4);
                               
                                if (cell == null) {
                                    cell = hssfRow.createCell((short) 4);
                                }

                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                HSSFRichTextString str = new HSSFRichTextString("15"); //aca colocar el valor       
                                cell.setCellValue(BigDecimal.valueOf(15.5).doubleValue());
                            }
                            if (contar == 6) {


                                HSSFCell cell = hssfRow.getCell((short) 5);
                               
                                if (cell == null) {
                                    cell = hssfRow.createCell((short) 5);
                                }

                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                HSSFRichTextString str = new HSSFRichTextString("15"); //aca colocar el valor       
                                cell.setCellValue(BigDecimal.valueOf(15.5).doubleValue());
                            }
                            if (contar == 11) {


                                HSSFCell cell = hssfRow.getCell((short) 10);
                              
                                if (cell == null) {
                                    cell = hssfRow.createCell((short) 10);
                                }

                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                HSSFRichTextString str = new HSSFRichTextString("15"); //aca colocar el valor       
                                cell.setCellValue(BigDecimal.valueOf(15.5).doubleValue());
                            }
                            if (contar == 12) {


                                HSSFCell cell = hssfRow.getCell((short) 11);
                              
                                if (cell == null) {
                                    cell = hssfRow.createCell((short) 11);
                                }

                                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                HSSFRichTextString str = new HSSFRichTextString("15"); //aca colocar el valor       
                                cell.setCellValue(BigDecimal.valueOf(15.5).doubleValue());
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
            System.out.println(contarC + "contar filas ");


            //cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
            // cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            //  HSSFRichTextString str = new HSSFRichTextString("15"); //aca colocar el valor       
            //cell.setCellValue(BigDecimal.valueOf(15.5).doubleValue());

            FileOutputStream fileOut = new FileOutputStream("E:/balanceC.xls");

            wb.write(fileOut);
            //wb.
            fileOut.close();


            /* List cellDataList = new ArrayList();
            
            FileInputStream fileInputStream = new FileInputStream("E:/BalanceComprobacion-2011_v2.xls");
            POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
            
            Workbook workBook =  WorkbookFactory.create(fileInputStream);
            Sheet hssfSheet = workBook.getSheet("Instrucciones");*/

            /*  Iterator rowIterator = hssfSheet.rowIterator();
            
            int contarC = 0;-*/

            /* Row hssfRow = hssfSheet.getRow(0);
            Cell d2 = hssfRow.getCell(3);
            System.out.println(hssfRow.getCell(1));
            
            d2.setCellValue(Integer.valueOf(15).intValue());
            FileOutputStream fileX = new FileOutputStream("E:/BalanceComprobacion-2011_v2.xls");
            workBook.write(fileX);
            fileX.close();*/
            //totalRows = sheet.getPhysicalNumberOfRows(); 
//System.out.println("total no of rows >>>>"+totalRows); 
         /*   while (rowIterator.hasNext()) {  // fila
            
            HSSFRow hssfRow = (HSSFRow) rowIterator.next();
            
            Iterator iterator = hssfRow.cellIterator();
            
            List cellTempList = new ArrayList();
            contarC = contarC + 1 + 1;
            
            while (iterator.hasNext()) { // columnas 
            
            HSSFCell d2 = hssfRow.getCell(3);
            
            d2.setCellValue(Integer.valueOf(15).intValue());
            /*   HSSFCell hssfCell = (HSSFCell) iterator.next();
            //  System.out.println(hssfCell.toString() + "salor");
            //  hssfRow.createCell((short) 3).setCellValue(20); // aca quierp guardar
            
            //    cellTempList.add(hssfCell);
            
            }
            hssfRow.createCell((short) 3).setCellValue(20);
            
            cellDataList.add(cellTempList);
            
            
            } */
            //  workBook.insertChartRecord();

            //   hssfSheet.setAlternativeFormula(true);
            //workBook.(fileInputStream);




        } catch (Exception e) {
        }




    }
}
