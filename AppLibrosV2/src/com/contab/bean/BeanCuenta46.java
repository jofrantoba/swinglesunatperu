package com.contab.bean;

import java.math.BigDecimal;
import java.util.Date;

public class BeanCuenta46 {
    private String tipo;
    private String numero;
    private String apellidosnombre;
    private String descripcion;
    private Date fechaemision;
    private BigDecimal montobase;
    private String cuenta;
    private String numcuenta;
    private BigDecimal dc;

    public String getDescuenta() {
        return descuenta;
    }

    public BigDecimal getDc() {
        return dc;
    }

    public void setDc(BigDecimal dc) {
        this.dc = dc;
    }

    public void setDescuenta(String descuenta) {
        this.descuenta = descuenta;
    }
    private String descuenta;

    public String getApellidosnombre() {
        return apellidosnombre;
    }

    public void setApellidosnombre(String apellidosnombre) {
        this.apellidosnombre = apellidosnombre;
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

    public Date getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(Date fechaemision) {
        this.fechaemision = fechaemision;
    }

    public BigDecimal getMontobase() {
        return montobase;
    }

    public void setMontobase(BigDecimal montobase) {
        this.montobase = montobase;
    }

    public String getNumcuenta() {
        return numcuenta;
    }

    public void setNumcuenta(String numcuenta) {
        this.numcuenta = numcuenta;
    }

    public String getNumero() {
        return numero==null?"":numero;
        //== null ? "" : fecha;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   
}
