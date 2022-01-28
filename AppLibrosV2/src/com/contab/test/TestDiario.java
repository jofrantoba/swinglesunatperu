/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.test;

import com.contab.datasource.DataSourceLibroDiario;

import com.contab.logic.LogicEmpresa;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

/**
 *
 * @author 777
 */
public class TestDiario {

    public static void main(String arg[]) throws SQLException, FileNotFoundException {
        // LogicEmpresa.listaDeEmpresa();
        // DataSourceLibroDiario ds=new DataSourceLibroDiario();
        //java.sql.Date ini=java.sql.Date.valueOf("2010-01-01");
        //java.sql.Date fin=java.sql.Date.valueOf("2010-01-01");
        FileOutputStream out; // declare a file output object
        PrintStream p; // declare a print stream object   
        //try {
        //String decimal = "4201.28105";  
        //paso el string a un BigDecimal  
        //BigDecimal bigDecimal = new BigDecimal(decimal);  
        // BigDecimal bigDecimalRedondeado = bigDecimal.setScale(2, RoundingMode.CEILING);  
        //System.out.println("Decimal redondeado: " + bigDecimalRedondeado); 
        //  $fichero = fopen("../Bean/Bean$tablas.php","w");

       // out = new FileOutputStream("../bean/ct.xt");
       // out = new FileOutputStream("E:\sistema-contable-final\AppLibrosV2\src\com\contab\bean/p.txt");

        // Connect print stream to the output stream
       // p = new PrintStream(out);

       // p.println("This is written to a file");
      //  p.close();

        //  }catch(NumberFormatException ex){
        //System.out.println("No es un número");
        //}
        //   BigDecimal x2 = x.setScale(2, RoundingMode.FLOOR);
        //  BigDecimal y2 = y.setScale(2, RoundingMode.UP);
        //  System.out.println(x+" x "+x2+"  -"+y2);
        /*LogicDiario.libroDiario("12", "GLOSA", ini, fin, "02",ds);
        Iterator i=ds.getLibroDiario().iterator();
        while(i.hasNext()){
        BeanLibroDiario obj=(BeanLibroDiario)i.next();
        System.out.println(obj.getDenominacion());
        }
         */
        // System.out.println(LogicCaja.saldoInicial("1040403", ini, fin, "02"));
    }
}
