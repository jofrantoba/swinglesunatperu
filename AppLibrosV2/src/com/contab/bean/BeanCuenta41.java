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
public class BeanCuenta41 {
    private String codigo;
    private String denominacion;
    private String codigo1;
    private String apellidosNombre;
    private String tabla2;
    private String numero;

    private BigDecimal saldoFinal;
    private String BCuenta;
    private String BCenter;
    private String BCodigoEmpleado;

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
        return denominacion==null?"":denominacion;
    }

    /**
     * @param denominacion the denominacion to set
     */
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    /**
     * @return the codigo1
     */
    public String getCodigo1() {
        return codigo1==null?"":codigo1;
    }

    /**
     * @param codigo1 the codigo1 to set
     */
    public void setCodigo1(String codigo1) {
        this.codigo1 = codigo1;
    }

    /**
     * @return the apellidosNombre
     */
    public String getApellidosNombre() {
        return apellidosNombre==null?"":apellidosNombre;
    }

    /**
     * @param apellidosNombre the apellidosNombre to set
     */
    public void setApellidosNombre(String apellidosNombre) {
        this.apellidosNombre = apellidosNombre;
    }

    /**
     * @return the tabla2
     */
    public String getTabla2() {
        return tabla2==null?"":tabla2;
    }

    /**
     * @param tabla2 the tabla2 to set
     */
    public void setTabla2(String tabla2) {
        this.tabla2 = tabla2;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero==null?"":numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
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

    /**
     * @return the BCuenta
     */
    public String getBCuenta() {
        return BCuenta;
    }

    /**
     * @param BCuenta the BCuenta to set
     */
    public void setBCuenta(String BCuenta) {
        this.BCuenta = BCuenta;
    }

    /**
     * @return the BCenter
     */
    public String getBCenter() {
        return BCenter;
    }

    /**
     * @param BCenter the BCenter to set
     */
    public void setBCenter(String BCenter) {
        this.BCenter = BCenter;
    }

    /**
     * @return the BCodigoEmpleado
     */
    public String getBCodigoEmpleado() {
        return BCodigoEmpleado;
    }

    /**
     * @param BCodigoEmpleado the BCodigoEmpleado to set
     */
    public void setBCodigoEmpleado(String BCodigoEmpleado) {
        this.BCodigoEmpleado = BCodigoEmpleado;
    }

}
