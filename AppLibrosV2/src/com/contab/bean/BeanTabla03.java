/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

/**
 *
 * @author cixtic04
 */
public class BeanTabla03 {
    
    private int idTabla03;
    private String compania;
    private String banco;
    private String  numeroCuenta;
    private String codigoSunat;
    private String tipo;
    
      private String operacion="n";
     private boolean selecccion;

    /**
     * @return the idTabla03
     */
    public int getIdTabla03() {
        return idTabla03;
    }

    /**
     * @param idTabla03 the idTabla03 to set
     */
    public void setIdTabla03(int idTabla03) {
        this.idTabla03 = idTabla03;
    }

    /**
     * @return the compania
     */
    public String getCompania() {
        return compania;
    }

    /**
     * @param compania the compania to set
     */
    public void setCompania(String compania) {
        this.compania = compania;
    }

    /**
     * @return the banco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * @return the codigoSunat
     */
    public String getCodigoSunat() {
        return codigoSunat;
    }

    /**
     * @param codigoSunat the codigoSunat to set
     */
    public void setCodigoSunat(String codigoSunat) {
        this.codigoSunat = codigoSunat;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    
    
}
