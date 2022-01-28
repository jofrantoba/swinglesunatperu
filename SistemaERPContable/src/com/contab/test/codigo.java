/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.test;

import com.contab.generador.generador;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

/**
 *
 * @author 777
 */
public class codigo {

    public static void main(String arg[]) throws SQLException, FileNotFoundException {


        String archivoBean = "E:\\sistema-contable-final\\AppLibrosV2\\src\\com\\contab\\bean\\BeanMiFichero.java";
        File ficheroBean = new File(archivoBean);
        String archivoDao = "E:\\sistema-contable-final\\AppLibrosV2\\src\\com\\contab\\dao\\DaoMiFichero.java";
        File ficheroDao = new File(archivoDao);
        String archivoScript = "E:\\sistema-contable-final\\AppLibrosV2\\src\\com\\contab\\dao\\Script.sql";
        File ficheroScript = new File(archivoScript);
        try {
            ///*********************Generacion delos Bean *************************/////
            if (ficheroBean.createNewFile()) {
                System.out.println("El fichero se ha creado correctamente Bean");
            } else {
                System.out.println("No ha podido ser creado el fichero Bean");
            }
            RandomAccessFile archivBean = new RandomAccessFile(archivoBean, "rw");
            String codigoBean = generador.listarCamposBean("BeanMiFichero");
            archivBean.writeBytes(codigoBean);

            ///////////////////fin Bean *********************///////////////////////////

            ///*********************Generacion delos Dao *************************/////         

            if (ficheroDao.createNewFile()) {
                System.out.println("El fichero se ha creado correctamente Dao");
            } else {
                System.out.println("No ha podido ser creado el fichero Dao");
            }
            RandomAccessFile archivDao = new RandomAccessFile(archivoDao, "rw");
            String codigoDao = generador.listarCamposDao("DaoMiFichero");
            archivDao.writeBytes(codigoDao);
            ///////////////////fin Dao *********************///////////////////////////
            
            ///*********************Generacion de lo Script *************************/////         
            if (ficheroScript.createNewFile()) {
                System.out.println("El fichero se ha creado correctamente Script");
            } else {
                System.out.println("No ha podido ser creado el fichero Script");
            }
            RandomAccessFile archivScript = new RandomAccessFile(archivoScript, "rw");
            String codigoSci = generador.generarScript("SQLMiFichero");
            archivScript.writeBytes(codigoSci);
            ///////////////////fin Script *********************///////////////////////////


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }



    }
}
