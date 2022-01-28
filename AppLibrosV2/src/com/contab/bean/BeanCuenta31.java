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
public class BeanCuenta31 {

    private int idCuenta31;
    private String denominacionrazon;
    private String idCompania;
    private int numero;
     private BigDecimal Documento;
    private String denominacion;
    private BigDecimal valorNominal;
    private int cantidad;
    private BigDecimal CostoTotal;
    private BigDecimal provisionTotal;
    private BigDecimal totalNeto;
     private String tabla2;
       private String anno;
    private String operacion = "n";
    private boolean selecccion;
    /**
     * @return the idCuenta31
     */
    public int getIdCuenta31() {
        return idCuenta31;
    }

    /**
     * @param idCuenta31 the idCuenta31 to set
     */
    public void setIdCuenta31(int idCuenta31) {
        this.idCuenta31 = idCuenta31;
    }

    /**
     * @return the denominacionrazon
     */
    public String getDenominacionrazon() {
        return denominacionrazon==null?"":denominacionrazon;
    }

    /**
     * @param denominacionrazon the denominacionrazon to set
     */
    public void setDenominacionrazon(String denominacionrazon) {
        this.denominacionrazon = denominacionrazon;
    }

    /**
     * @return the idCompania
     */
    public String getIdCompania() {
        return idCompania==null?"":idCompania;
    }

    /**
     * @param idCompania the idCompania to set
     */
    public void setIdCompania(String idCompania) {
        this.idCompania = idCompania;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
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
     * @return the valorNominal
     */
    public BigDecimal getValorNominal() {
        return valorNominal==null?BigDecimal.ZERO:valorNominal;
    }

    /**
     * @param valorNominal the valorNominal to set
     */
    public void setValorNominal(BigDecimal valorNominal) {
        this.valorNominal = valorNominal;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the CostoTotal
     */
    public BigDecimal getCostoTotal() {
        return CostoTotal==null?BigDecimal.ZERO:CostoTotal;
    }

    /**
     * @param CostoTotal the CostoTotal to set
     */
    public void setCostoTotal(BigDecimal CostoTotal) {
        this.CostoTotal = CostoTotal;
    }

    /**
     * @return the provisionTotal
     */
    public BigDecimal getProvisionTotal() {
        return provisionTotal==null?BigDecimal.ZERO:provisionTotal;
    }

    /**
     * @param provisionTotal the provisionTotal to set
     */
    public void setProvisionTotal(BigDecimal provisionTotal) {
        this.provisionTotal = provisionTotal;
    }

    /**
     * @return the totalNeto
     */
    public BigDecimal getTotalNeto() {
        return totalNeto==null?BigDecimal.ZERO:totalNeto;
    }

    /**
     * @param totalNeto the totalNeto to set
     */
    public void setTotalNeto(BigDecimal totalNeto) {
        this.totalNeto = totalNeto;
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
     * @return the tabla2
     */
    public String getTabla2() {
        return tabla2;
    }

    /**
     * @param tabla2 the tabla2 to set
     */
    public void setTabla2(String tabla2) {
        this.tabla2 = tabla2;
    }

    /**
     * @return the anno
     */
    public String getAnno() {
        return anno;
    }

    /**
     * @param anno the anno to set
     */
    public void setAnno(String anno) {
        this.anno = anno;
    }

    /**
     * @return the Documento
     */
    public BigDecimal getDocumento() {
        return Documento;
    }

    /**
     * @param Documento the Documento to set
     */
    public void setDocumento(BigDecimal Documento) {
        this.Documento = Documento;
    }


}
