/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

/**
 *
 * @author cixtic04
 */
public class BeanTabla6 {

    private String idUnidadArticulo;
    private String Unidad;
    private String codigoSunat;
    private String operacion = "n";
    private boolean selecccion;

    /**
     * @return the idUnidadArticulo
     */
    public String getIdUnidadArticulo() {
        return idUnidadArticulo== null ? "" : idUnidadArticulo;
    }

    /**
     * @param idUnidadArticulo the idUnidadArticulo to set
     */
    public void setIdUnidadArticulo(String idUnidadArticulo) {
        this.idUnidadArticulo = idUnidadArticulo;
    }

    /**
     * @return the Unidad
     */
    public String getUnidad() {
        return Unidad == null ? "" : Unidad;
    }

    /**
     * @param Unidad the Unidad to set
     */
    public void setUnidad(String Unidad) {
        this.Unidad = Unidad;
    }

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
