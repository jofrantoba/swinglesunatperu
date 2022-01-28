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
public class BeanSaldoDeCuenta {

    private String tabla;
    private String numero;
    private String denominacion;
    private BigDecimal monto;
    private String fechaEmision;
    private String preimpreso;
    private String cuenta;
    private String centro;
    private String serie;
    private String codCliente;
    private String TipoDoc;

    /**
     * @return the tabla
     */
    public String getTabla() {
        return tabla== null ? "" : tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero == null ? "" : numero;
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
        return denominacion == null ? "" : denominacion;
    }

    /**
     * @param denominacion the denominacion to set
     */
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    /**
     * @return the monto
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * @return the fechaEmision
     */
    public String getFechaEmision() {
        return fechaEmision == null ? "" : fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the preimpreso
     */
    public String getPreimpreso() {
        return preimpreso == null ? "" : preimpreso;
    }

    /**
     * @param preimpreso the preimpreso to set
     */
    public void setPreimpreso(String preimpreso) {
        this.preimpreso = preimpreso;
    }

    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta == null ? "" : cuenta;
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
        return centro == null ? "" : centro;
    }

    /**
     * @param centro the centro to set
     */
    public void setCentro(String centro) {
        this.centro = centro;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie == null ? "" : serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the codCliente
     */
    public String getCodCliente() {
        return codCliente == null ? "" : codCliente;
    }

    /**
     * @param codCliente the codCliente to set
     */
    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    /**
     * @return the TipoDoc
     */
    public String getTipoDoc() {
        return TipoDoc==null ? "":TipoDoc;
    }

    /**
     * @param TipoDoc the TipoDoc to set
     */
    public void setTipoDoc(String TipoDoc) {
        this.TipoDoc = TipoDoc;
    }
}
