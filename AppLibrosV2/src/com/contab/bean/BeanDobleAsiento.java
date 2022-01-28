/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

import java.math.BigDecimal;

/**
 *
 * @author user
 */
public class BeanDobleAsiento {

    private String cuenta;
    private String Centro;
    private String fecha;
    private BigDecimal deberSoles;
    private BigDecimal HaberSoles;
    private BigDecimal SaldoSoles;
    private BigDecimal deberDolares;
    private BigDecimal haberDolares;
    private BigDecimal saldoDolares;

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
     * @return the Centro
     */
    public String getCentro() {
        return Centro;
    }

    /**
     * @param Centro the Centro to set
     */
    public void setCentro(String Centro) {
        this.Centro = Centro;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the deberSoles
     */
    public BigDecimal getDeberSoles() {
        return deberSoles;
    }

    /**
     * @param deberSoles the deberSoles to set
     */
    public void setDeberSoles(BigDecimal deberSoles) {
        this.deberSoles = deberSoles;
    }

    /**
     * @return the HaberSoles
     */
    public BigDecimal getHaberSoles() {
        return HaberSoles;
    }

    /**
     * @param HaberSoles the HaberSoles to set
     */
    public void setHaberSoles(BigDecimal HaberSoles) {
        this.HaberSoles = HaberSoles;
    }

    /**
     * @return the SaldoSoles
     */
    public BigDecimal getSaldoSoles() {
        return SaldoSoles;
    }

    /**
     * @param SaldoSoles the SaldoSoles to set
     */
    public void setSaldoSoles(BigDecimal SaldoSoles) {
        this.SaldoSoles = SaldoSoles;
    }

    /**
     * @return the deberDolares
     */
    public BigDecimal getDeberDolares() {
        return deberDolares;
    }

    /**
     * @param deberDolares the deberDolares to set
     */
    public void setDeberDolares(BigDecimal deberDolares) {
        this.deberDolares = deberDolares;
    }

    /**
     * @return the haberDolares
     */
    public BigDecimal getHaberDolares() {
        return haberDolares;
    }

    /**
     * @param haberDolares the haberDolares to set
     */
    public void setHaberDolares(BigDecimal haberDolares) {
        this.haberDolares = haberDolares;
    }

    /**
     * @return the saldoDolares
     */
    public BigDecimal getSaldoDolares() {
        return saldoDolares;
    }

    /**
     * @param saldoDolares the saldoDolares to set
     */
    public void setSaldoDolares(BigDecimal saldoDolares) {
        this.saldoDolares = saldoDolares;
    }
}
