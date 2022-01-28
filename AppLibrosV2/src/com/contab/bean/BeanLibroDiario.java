/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author 777
 */
public class BeanLibroDiario {
    private String codigooper;
    private java.sql.Date fechaoper;
    private String glosa;
    private String cuenta;
    private String ccenter;
    private String denominacion;
    private BigDecimal debe;
    private BigDecimal haber;

    public String getCodigooper() {
        return codigooper;
    }

    public void setCodigooper(String codigooper) {
        this.codigooper = codigooper;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public Date getFechaoper() {
        return fechaoper;
    }

    public void setFechaoper(Date fechaoper) {
        this.fechaoper = fechaoper;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }

    public String getCcenter() {
        return ccenter;
    }

    public void setCcenter(String ccenter) {
        this.ccenter = ccenter;
    }
    
    
    
}
