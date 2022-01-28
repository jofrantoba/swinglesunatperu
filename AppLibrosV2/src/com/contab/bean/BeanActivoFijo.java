/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

import java.math.BigDecimal;

/**
 *
 * @author cixtic04
 */
public class BeanActivoFijo {
    private String cuenta;
    private String centro;
    private String jrnnumber;
    private String agrupar ;
    private String  nombreCuenta;
    private String  glosa;
    private BigDecimal debe;
    private BigDecimal haber;
    private BigDecimal saldo;

    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the centro
     */
    public String getCentro() {
        return centro;
    }

    /**
     * @param centro the centro to set
     */
    public void setCentro(String centro) {
        this.centro = centro;
    }

    /**
     * @return the jrnnumber
     */
    public String getJrnnumber() {
        return jrnnumber;
    }

    /**
     * @param jrnnumber the jrnnumber to set
     */
    public void setJrnnumber(String jrnnumber) {
        this.jrnnumber = jrnnumber;
    }

    /**
     * @return the agrupar
     */
    public String getAgrupar() {
        return agrupar;
    }

    /**
     * @param agrupar the agrupar to set
     */
    public void setAgrupar(String agrupar) {
        this.agrupar = agrupar;
    }

    /**
     * @return the debe
     */
    public BigDecimal getDebe() {
        return debe;
    }

    /**
     * @param debe the debe to set
     */
    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    /**
     * @return the haber
     */
    public BigDecimal getHaber() {
        return haber;
    }

    /**
     * @param haber the haber to set
     */
    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }

    /**
     * @return the saldo
     */
    public BigDecimal getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the nombreCuenta
     */
    public String getNombreCuenta() {
        return nombreCuenta;
    }

    /**
     * @param nombreCuenta the nombreCuenta to set
     */
    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    /**
     * @return the glosa
     */
    public String getGlosa() {
        return glosa;
    }

    /**
     * @param glosa the glosa to set
     */
    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }
    
}
