/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.util;

/**
 *
 * @author user
 */
public class ClassTabla10 {

    public String Mes(int valor) throws Exception {

        String Mensaje = "";

        try {

            switch (valor) {

                case 01:
                    Mensaje = "01";

                    break;

                case 03:

                    Mensaje = "02";
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mensaje;

    }
}
