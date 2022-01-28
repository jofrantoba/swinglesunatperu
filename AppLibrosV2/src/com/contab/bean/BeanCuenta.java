/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

/**
 *
 * @author 777
 */
public class BeanCuenta {

    private String idCuenta;
    private String glosa;
    private String numero;
    private String Nuevonumero;
    private String denominacion;
    private String Cuenta;
    private String NuevoCuenta;
    private String Center;
      private String compania;
    private int Anno;
    private String operacion = "n";
    private boolean selecccion;

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    /**
     * @return the numero
     */
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
     * @return the denominacion
     */
    public String getDenominacion() {
        return denominacion;
    }

    /**
     * @param denominacion the denominacion to set
     */
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    /**
     * @return the Cuenta
     */
    public String getCuenta() {
        return Cuenta;
    }

    /**
     * @param Cuenta the Cuenta to set
     */
    public void setCuenta(String Cuenta) {
        this.Cuenta = Cuenta;
    }

    /**
     * @return the Center
     */
    public String getCenter() {
        return Center;
    }

    /**
     * @param Center the Center to set
     */
    public void setCenter(String Center) {
        this.Center = Center;
    }

    /**
     * @return the Nuevonumero
     */
    public String getNuevonumero() {
        return Nuevonumero;
    }

    /**
     * @param Nuevonumero the Nuevonumero to set
     */
    public void setNuevonumero(String Nuevonumero) {
        this.Nuevonumero = Nuevonumero;
    }

    /**
     * @return the NuevoCuenta
     */
    public String getNuevoCuenta() {
        return NuevoCuenta;
    }

    /**
     * @param NuevoCuenta the NuevoCuenta to set
     */
    public void setNuevoCuenta(String NuevoCuenta) {
        this.NuevoCuenta = NuevoCuenta;
    }

    /**
     * @return the operacion
     */
    public String getOperacion() {
        return operacion;
    }

    /**
     * @param operacion the operacion to set
     */
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    /**
     * @return the selecccion
     */
    public boolean isSelecccion() {
        return selecccion;
    }

    /**
     * @param selecccion the selecccion to set
     */
    public void setSelecccion(boolean selecccion) {
        this.selecccion = selecccion;
    }

    /**
     * @return the idCuenta
     */
    public String getIdCuenta() {
        return idCuenta;
    }

    /**
     * @param idCuenta the idCuenta to set
     */
    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * @return the Anno
     */
    public int getAnno() {
        return Anno;
    }

    /**
     * @param Anno the Anno to set
     */
    public void setAnno(int Anno) {
        this.Anno = Anno;
    }

    /**
     * @return the compania
     */
    public String getCompania() {
        return compania;
    }

    /**
     * @param compania the compania to set
     */
    public void setCompania(String compania) {
        this.compania = compania;
    }
}
