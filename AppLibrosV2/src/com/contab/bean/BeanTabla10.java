/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

/**
 *
 * @author cixtic04
 */
public class BeanTabla10 {

    private int idTabla10;
    private String codigoXray;
    private String codigoSunat;
    private String operacion="n";
     private boolean selecccion;
    

    /**
     * @return the idUnidadArticulo
     */
    /**
     * @return the codigoSunat
     */
    public String getCodigoSunat() {
        return codigoSunat == null ? "" : codigoSunat;
    }

    /**
     * @param codigoSunat the codigoSunat to set
     */
    public void setCodigoSunat(String codigoSunat) {
        this.codigoSunat = codigoSunat;
    }

    /**
     * @return the idTabla10
     */
    /**
     * @return the codigoXray
     */
    public String getCodigoXray() {
        return codigoXray;
    }

    /**
     * @param codigoXray the codigoXray to set
     */
    public void setCodigoXray(String codigoXray) {
        this.codigoXray = codigoXray;
    }

    /**
     * @return the idTabla10
     */
    public int getIdTabla10() {
        return idTabla10;
    }

    /**
     * @param idTabla10 the idTabla10 to set
     */
    public void setIdTabla10(int idTabla10) {
        this.idTabla10 = idTabla10;
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
