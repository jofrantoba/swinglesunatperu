/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.contab.bean;

/**
 *
 * @author user
 */
public class BeanImpresion {
       private String numero;
    private String nombre;

    /**
     * @return the numero
     */

     public BeanImpresion(String num,String desc){
        numero=num;
        nombre=desc;
    }
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
