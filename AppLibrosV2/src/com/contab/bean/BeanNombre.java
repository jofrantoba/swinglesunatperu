/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

/**
 *
 * @author Administrador
 */
public class BeanNombre {
    private String nombre1;
    private String nombre2;
    private String appaterno;
    private String apmaterno;

    public String getApmaterno() {
        return apmaterno== null ? "" : apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }

    public String getAppaterno() {
        return appaterno== null ? "" : appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getNombre1() {
        return nombre1== null ? "" : nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2== null ? "" : nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
    
    
}
