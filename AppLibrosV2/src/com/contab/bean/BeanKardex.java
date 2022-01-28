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
public class BeanKardex {

    private String fecha;
    private String tabla10;
    private String serie;
    private String numero;
    private String tabla12;
    private String establecimiento;
    private String itemProducto;
    private String codigoBusquedad;
    private String producto;
    private String unidad;
    private String codigoAgrupacion;
    private BigDecimal cantidadE;
    private BigDecimal costoUE;
    private BigDecimal totalE;
    private BigDecimal cantidadS;
    private BigDecimal costoUS;
    private BigDecimal totalS;
    private BigDecimal cantidadF;
    private BigDecimal costoUF;
    private BigDecimal totalF;
    private int cantidadFilas;

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
     * @return the tabla10
     */
    public String getTabla10() {
        return tabla10 == null ? "" : tabla10;
    }

    /**
     * @param tabla10 the tabla10 to set
     */
    public void setTabla10(String tabla10) {
        this.tabla10 = tabla10;
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
     * @return the tabla12
     */
    public String getTabla12() {
        return tabla12 == null ? "" : tabla12;
    }

    /**
     * @param tabla12 the tabla12 to set
     */
    public void setTabla12(String tabla12) {
        this.tabla12 = tabla12;
    }

    /**
     * @return the cantidadE
     */
    public BigDecimal getCantidadE() {
        return cantidadE == null ? BigDecimal.ZERO : cantidadE;
    }

    /**
     * @param cantidadE the cantidadE to set
     */
    public void setCantidadE(BigDecimal cantidadE) {
        this.cantidadE = cantidadE;
    }

    /**
     * @return the costoUE
     */
    public BigDecimal getCostoUE() {
        return costoUE == null ? BigDecimal.ZERO : costoUE;
    }

    /**
     * @param costoUE the costoUE to set
     */
    public void setCostoUE(BigDecimal costoUE) {
        this.costoUE = costoUE;
    }

    /**
     * @return the totalE
     */
    public BigDecimal getTotalE() {
        return totalE == null ? BigDecimal.ZERO : totalE;
    }

    /**
     * @param totalE the totalE to set
     */
    public void setTotalE(BigDecimal totalE) {
        this.totalE = totalE;
    }

    /**
     * @return the cantidadS
     */
    public BigDecimal getCantidadS() {
        return cantidadS == null ? BigDecimal.ZERO : cantidadS;
    }

    /**
     * @param cantidadS the cantidadS to set
     */
    public void setCantidadS(BigDecimal cantidadS) {
        this.cantidadS = cantidadS;
    }

    /**
     * @return the costoUS
     */
    public BigDecimal getCostoUS() {
        return costoUS == null ? BigDecimal.ZERO : costoUS;
    }

    /**
     * @param costoUS the costoUS to set
     */
    public void setCostoUS(BigDecimal costoUS) {
        this.costoUS = costoUS;
    }

    /**
     * @return the totalS
     */
    public BigDecimal getTotalS() {
        return totalS;
    }

    /**
     * @param totalS the totalS to set
     */
    public void setTotalS(BigDecimal totalS) {
        this.totalS = totalS;
    }

    /**
     * @return the cantidadF
     */
    public BigDecimal getCantidadF() {
        return cantidadF == null ? BigDecimal.ZERO : cantidadF;
    }

    /**
     * @param cantidadF the cantidadF to set
     */
    public void setCantidadF(BigDecimal cantidadF) {
        this.cantidadF = cantidadF;
    }

    /**
     * @return the costoUF
     */
    public BigDecimal getCostoUF() {
        return costoUF == null ? BigDecimal.ZERO : costoUF;
    }

    /**
     * @param costoUF the costoUF to set
     */
    public void setCostoUF(BigDecimal costoUF) {
        this.costoUF = costoUF;
    }

    /**
     * @return the totalF
     */
    public BigDecimal getTotalF() {
        return totalF;
    }

    /**
     * @param totalF the totalF to set
     */
    public void setTotalF(BigDecimal totalF) {
        this.totalF = totalF;
    }

    /**
     * @return the establecimiento
     */
    public String getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    /**
     * @return the producto
     */
    public String getProducto() {
        return producto;
    }

    /**
     * @param producto the producto to set
     */
    public void setProducto(String producto) {
        this.producto = producto;
    }

    /**
     * @return the itemProducto
     */
    public String getItemProducto() {
        return itemProducto == null ? "" : itemProducto;
    }

    /**
     * @param itemProducto the itemProducto to set
     */
    public void setItemProducto(String itemProducto) {
        this.itemProducto = itemProducto;
    }

    /**
     * @return the codigoBusquedad
     */
    public String getCodigoBusquedad() {
        return codigoBusquedad == null ? "" : codigoBusquedad;
    }

    /**
     * @param codigoBusquedad the codigoBusquedad to set
     */
    public void setCodigoBusquedad(String codigoBusquedad) {
        this.codigoBusquedad = codigoBusquedad;
    }

    /**
     * @return the unidad
     */
    public String getUnidad() {
        return unidad == null ? "" : unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the codigoAgrupacion
     */
    public String getCodigoAgrupacion() {
        return codigoAgrupacion == null ? "" : codigoAgrupacion;
    }

    /**
     * @param codigoAgrupacion the codigoAgrupacion to set
     */
    public void setCodigoAgrupacion(String codigoAgrupacion) {
        this.codigoAgrupacion = codigoAgrupacion;
    }

    /**
     * @return the cantidadFilas
     */
    public int getCantidadFilas() {
        return cantidadFilas;
    }

    /**
     * @param cantidadFilas the cantidadFilas to set
     */
    public void setCantidadFilas(int cantidadFilas) {
        this.cantidadFilas = cantidadFilas;
    }
}
