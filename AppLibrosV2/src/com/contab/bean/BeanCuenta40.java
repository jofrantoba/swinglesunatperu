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
public class BeanCuenta40 {
 private String codigo;
 private String denominacion;
 private BigDecimal saldoFinal;

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
     * @return the saldoFinal
     */
    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    /**
     * @param saldoFinal the saldoFinal to set
     */
    public void setSaldoFinal(BigDecimal saldoFinal) {
        this.saldoFinal = saldoFinal;
    }


}
