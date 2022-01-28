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
public class BeanMercaderia {
    private String Codigo;
    private String  Tipo5;
    private String Descripcion;
    private String  Tipo6;
    private BigDecimal Cantidad;
    private BigDecimal Costounitario;

    /**
     * @return the Codigo
     */
    public String getCodigo() {
        return Codigo;
    }

    /**
     * @param Codigo the Codigo to set
     */
    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    /**
     * @return the Tipo5
     */
    public String getTipo5() {
        return Tipo5;
    }

    /**
     * @param Tipo5 the Tipo5 to set
     */
    public void setTipo5(String Tipo5) {
        this.Tipo5 = Tipo5;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * @return the Tipo6
     */
    public String getTipo6() {
        return Tipo6;
    }

    /**
     * @param Tipo6 the Tipo6 to set
     */
    public void setTipo6(String Tipo6) {
        this.Tipo6 = Tipo6;
    }

    /**
     * @return the Cantidad
     */
    public BigDecimal getCantidad() {
        return Cantidad;
    }

    /**
     * @param Cantidad the Cantidad to set
     */
    public void setCantidad(BigDecimal Cantidad) {
        this.Cantidad = Cantidad;
    }

    /**
     * @return the Costounitario
     */
    public BigDecimal getCostounitario() {
        return Costounitario;
    }

    /**
     * @param Costounitario the Costounitario to set
     */
    public void setCostounitario(BigDecimal Costounitario) {
        this.Costounitario = Costounitario;
    }
    
    
    
}
