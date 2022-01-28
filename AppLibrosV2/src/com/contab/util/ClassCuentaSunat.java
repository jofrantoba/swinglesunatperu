/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.contab.util;

/**
 *
 * @author cixtic04
 */
public class ClassCuentaSunat {

      public String Sunat(String valor) throws Exception {

        String Mensaje = "";

        try {
            if(valor.equals("BO")){
             Mensaje="001";
            }
            else if(valor.equals("CA")){
             Mensaje="003";
            }
            else if(valor.equals("CHD")){
             Mensaje="007";
            }
            else if(valor.equals("CHQ")){
             Mensaje="007";
            }
            else if(valor.equals("CON")){
             Mensaje="003";
            }
            else if(valor.equals("DB")){
             Mensaje="003";
            }
            else if(valor.equals("EF")){
             Mensaje="";
            }  else if(valor.equals("TLC")){
             Mensaje="003";
            }
            else{
            Mensaje="";

            }

            }

         catch (Exception e) {
            e.printStackTrace();
        }
        return Mensaje;

    }
      
       public String Codigo_Saldo_Cliente_Sunat(String valor) throws Exception {

        String Mensaje = "";

        try {
            if(valor.equals("01")){
                //factura
             Mensaje="F/";
            }
            else if(valor.equals("02")){
                //boleta de venta
             Mensaje="BV/";
            }
            else if(valor.equals("03")){
                // letra
             Mensaje="L";
            }
            else if(valor.equals("04")){
                // anticipo
             Mensaje="A";
            }
            else if(valor.equals("05")){
                //nota de crédito
             Mensaje="N/C";
            }
             else if(valor.equals("06")){
                //nota de Débito
             Mensaje="N/D";
            }
             else if(valor.equals("07")){
                //extorno
             Mensaje="E";
            }
             else if(valor.equals("08")){
                //pago por exceso
             Mensaje="P/E";
            }
             else if(valor.equals("09")){
                //nota de débito por extorno
             Mensaje="N/DE";
            }
            
           

            }

         catch (Exception e) {
            e.printStackTrace();
        }
        return Mensaje;

    }

}
