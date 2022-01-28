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
public class BeaLlibroInventarioBalanceGP {

    private BigDecimal ventaNeta;
    private BigDecimal otroIngresoOperacionales;
    private BigDecimal costoDeVentas;
    private BigDecimal gastoOperacionales;
    private BigDecimal gastosAdministracion;
    private BigDecimal gastoVenta;
    private BigDecimal otrosImgresos;
    private BigDecimal otrosGasto;
    private BigDecimal ingresosFinanacieros;
    private BigDecimal gastosFinacieros;
    private BigDecimal otrosIngresos;
    private BigDecimal resultadosExposicionInflacion;
    private BigDecimal participacion;
    private BigDecimal impuestoRenta;
    private BigDecimal ingresoExtraordinarios;
    private BigDecimal gastosExtraordinarios;

    /**
     * @return the ventaNeta
     */
    public BigDecimal getVentaNeta() {
        return ventaNeta == null ? BigDecimal.ZERO : ventaNeta;
    }

    /**
     * @param ventaNeta the ventaNeta to set
     */
    public void setVentaNeta(BigDecimal ventaNeta) {
        this.ventaNeta = ventaNeta;
    }

    /**
     * @return the otroIngresoOperacionales
     */
    public BigDecimal getOtroIngresoOperacionales() {
        return otroIngresoOperacionales == null ? BigDecimal.ZERO : otroIngresoOperacionales;
    }

    /**
     * @param otroIngresoOperacionales the otroIngresoOperacionales to set
     */
    public void setOtroIngresoOperacionales(BigDecimal otroIngresoOperacionales) {
        this.otroIngresoOperacionales = otroIngresoOperacionales;
    }

    /**
     * @return the costoDeVentas
     */
    public BigDecimal getCostoDeVentas() {
        return costoDeVentas == null ? BigDecimal.ZERO : costoDeVentas;
    }

    /**
     * @param costoDeVentas the costoDeVentas to set
     */
    public void setCostoDeVentas(BigDecimal costoDeVentas) {
        this.costoDeVentas = costoDeVentas;
    }

    /**
     * @return the gastoOperacionales
     */
    public BigDecimal getGastoOperacionales() {
        return gastoOperacionales == null ? BigDecimal.ZERO : gastoOperacionales;
    }

    /**
     * @param gastoOperacionales the gastoOperacionales to set
     */
    public void setGastoOperacionales(BigDecimal gastoOperacionales) {
        this.gastoOperacionales = gastoOperacionales;
    }

    /**
     * @return the gastosAdministracion
     */
    public BigDecimal getGastosAdministracion() {
        return gastosAdministracion == null ? BigDecimal.ZERO : gastosAdministracion;
    }

    /**
     * @param gastosAdministracion the gastosAdministracion to set
     */
    public void setGastosAdministracion(BigDecimal gastosAdministracion) {
        this.gastosAdministracion = gastosAdministracion;
    }

    /**
     * @return the gastoVenta
     */
    public BigDecimal getGastoVenta() {
        return gastoVenta == null ? BigDecimal.ZERO : gastoVenta;
    }

    /**
     * @param gastoVenta the gastoVenta to set
     */
    public void setGastoVenta(BigDecimal gastoVenta) {
        this.gastoVenta = gastoVenta;
    }

    /**
     * @return the otrosImgresos
     */
    public BigDecimal getOtrosImgresos() {
        return otrosImgresos == null ? BigDecimal.ZERO : otrosImgresos;
    }

    /**
     * @param otrosImgresos the otrosImgresos to set
     */
    public void setOtrosImgresos(BigDecimal otrosImgresos) {
        this.otrosImgresos = otrosImgresos;
    }

    /**
     * @return the ingresosFinanacieros
     */
    public BigDecimal getIngresosFinanacieros() {
        return ingresosFinanacieros == null ? BigDecimal.ZERO : ingresosFinanacieros;
    }

    /**
     * @param ingresosFinanacieros the ingresosFinanacieros to set
     */
    public void setIngresosFinanacieros(BigDecimal ingresosFinanacieros) {
        this.ingresosFinanacieros = ingresosFinanacieros;
    }

    /**
     * @return the gastosFinacieros
     */
    public BigDecimal getGastosFinacieros() {
        return gastosFinacieros == null ? BigDecimal.ZERO : gastosFinacieros;
    }

    /**
     * @param gastosFinacieros the gastosFinacieros to set
     */
    public void setGastosFinacieros(BigDecimal gastosFinacieros) {
        this.gastosFinacieros = gastosFinacieros;
    }

    /**
     * @return the otrosIngresos
     */
    public BigDecimal getOtrosIngresos() {
        return otrosIngresos == null ? BigDecimal.ZERO : otrosIngresos;
    }

    /**
     * @param otrosIngresos the otrosIngresos to set
     */
    public void setOtrosIngresos(BigDecimal otrosIngresos) {
        this.otrosIngresos = otrosIngresos;
    }

    /**
     * @return the resultadosExposicionInflacion
     */
    public BigDecimal getResultadosExposicionInflacion() {
        return resultadosExposicionInflacion == null ? BigDecimal.ZERO : resultadosExposicionInflacion;
    }

    /**
     * @param resultadosExposicionInflacion the resultadosExposicionInflacion to set
     */
    public void setResultadosExposicionInflacion(BigDecimal resultadosExposicionInflacion) {
        this.resultadosExposicionInflacion = resultadosExposicionInflacion;
    }

    /**
     * @return the participacion
     */
    public BigDecimal getParticipacion() {
        return participacion;
    }

    /**
     * @param participacion the participacion to set
     */
    public void setParticipacion(BigDecimal participacion) {
        this.participacion = participacion;
    }

    /**
     * @return the impuestoRenta
     */
    public BigDecimal getImpuestoRenta() {
        return impuestoRenta;
    }

    /**
     * @param impuestoRenta the impuestoRenta to set
     */
    public void setImpuestoRenta(BigDecimal impuestoRenta) {
        this.impuestoRenta = impuestoRenta;
    }

    /**
     * @return the ingresoExtraordinarios
     */
    public BigDecimal getIngresoExtraordinarios() {
        return ingresoExtraordinarios;
    }

    /**
     * @param ingresoExtraordinarios the ingresoExtraordinarios to set
     */
    public void setIngresoExtraordinarios(BigDecimal ingresoExtraordinarios) {
        this.ingresoExtraordinarios = ingresoExtraordinarios;
    }

    /**
     * @return the gastosExtraordinarios
     */
    public BigDecimal getGastosExtraordinarios() {
        return gastosExtraordinarios;
    }

    /**
     * @param gastosExtraordinarios the gastosExtraordinarios to set
     */
    public void setGastosExtraordinarios(BigDecimal gastosExtraordinarios) {
        this.gastosExtraordinarios = gastosExtraordinarios;
    }

    /**
     * @return the otrosGasto
     */
    public BigDecimal getOtrosGasto() {
        return otrosGasto;
    }

    /**
     * @param otrosGasto the otrosGasto to set
     */
    public void setOtrosGasto(BigDecimal otrosGasto) {
        this.otrosGasto = otrosGasto;
    }
}
