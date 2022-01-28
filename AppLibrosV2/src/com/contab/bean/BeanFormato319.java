/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

import java.math.BigDecimal;

/**
 *
 * @author cixtic03
 */
public class BeanFormato319 {

    private String compania;
    private String center;
    private String cuenta;
    private String descripcion;
    private BigDecimal saldo;
    private String jrnnumber;
    private BigDecimal saldodoc;
    private String fechacreacion;
    private String glosa;
    private String proveedor;

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public String getJrnnumber() {
        return jrnnumber;
    }

    public void setJrnnumber(String jrnnumber) {
        this.jrnnumber = jrnnumber;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getSaldodoc() {
        return saldodoc;
    }

    public void setSaldodoc(BigDecimal saldodoc) {
        this.saldodoc = saldodoc;
    }
}
