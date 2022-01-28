/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.util;

/**
 *
 * @author user
 */
public class ClassFecha {

    public String Mes(int valor) throws Exception {

        String Mensaje = "";

        try {


            switch (valor) {

                case 0:
                    Mensaje = "01";
                    break;

                case 1:
                    Mensaje = "02";
                    break;
                case 2:
                    Mensaje = "03";
                    break;
                case 3:
                    Mensaje = "04";
                    break;
                case 4:
                    Mensaje = "05";
                    break;
                case 5:
                    Mensaje = "06";
                    break;

                case 6:
                    Mensaje = "07";
                    break;
                case 7:
                    Mensaje = "08";
                    break;
                case 8:
                    Mensaje = "09";
                    break;
                case 9:
                    Mensaje = "10";
                    break;

                case 10:
                    Mensaje = "11";
                    break;
                case 11:
                    Mensaje = "12";
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mensaje;

    }

    public String Dia(int valor, int anno) throws Exception {

        String Mensaje = "";

        try {


            switch (valor) {

                case 1:
                    Mensaje = "31";
                    break;

                case 2:
                    if ((anno % 4 == 0) && ((anno % 100 != 0) || (anno % 400 == 0))) {
                        Mensaje = "29";
                        System.out.println("El año es bisiesto");
                    } else {
                        System.out.println("El año no es bisiesto");
                        Mensaje = "28";
                    }

                    break;
                case 3:
                    Mensaje = "31";
                    break;
                case 4:
                    Mensaje = "30";
                    break;
                case 5:
                    Mensaje = "31";
                    break;
                case 6:
                    Mensaje = "30";
                    break;

                case 7:
                    Mensaje = "31";
                    break;
                case 8:
                    Mensaje = "31";
                    break;
                case 9:
                    Mensaje = "30";
                    break;
                case 10:
                    Mensaje = "31";
                    break;

                case 11:
                    Mensaje = "30";
                    break;
                case 12:
                    Mensaje = "31";
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mensaje;

    }

    public String name_Mes(int valor) throws Exception {

        String Mensaje = "";

        try {


            switch (valor) {

                case 0:
                    Mensaje = "Enero";
                    break;

                case 1:
                    Mensaje = "Febrero";
                    break;
                case 2:
                    Mensaje = "Marzo";
                    break;
                case 3:
                    Mensaje = "Abril";
                    break;
                case 4:
                    Mensaje = "Mayo";
                    break;
                case 5:
                    Mensaje = "Junio";
                    break;

                case 6:
                    Mensaje = "Julio";
                    break;
                case 7:
                    Mensaje = "Agosto";
                    break;
                case 8:
                    Mensaje = "Septiembre";
                    break;
                case 9:
                    Mensaje = "Octubre";
                    break;

                case 10:
                    Mensaje = "Noviembre";
                    break;
                case 11:
                    Mensaje = "Diciembre";
                    break;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mensaje;

    }
}
