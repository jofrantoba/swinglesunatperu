/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

/**
 *
 * @author cixtic04
 */
public class BeanTabla12 {

    private String operacion = "n";
    private boolean selecccion;
    private int idTabla;
    private String codmov;
    private String llave;
    private String descripcion;
    private String codigoSunat;

    /**
     * @return the idUnidadArticulo
     */
    /**
     * @param idUnidadArticulo the idUnidadArticulo to set
     */
    /**
     * @return the Unidad
     */
    /**
     * @param Unidad the Unidad to set
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
     * @return the idTabla
     */
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the idTabla
     */
    public int getIdTabla() {
        return idTabla;
    }

    /**
     * @param idTabla the idTabla to set
     */
    public void setIdTabla(int idTabla) {
        this.idTabla = idTabla;
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
     * @return the codmov
     */
    public String getCodmov() {
        return codmov == null ? "" : codmov;
    }

    /**
     * @param codmov the codmov to set
     */
    public void setCodmov(String codmov) {
        this.codmov = codmov;
    }

    /**
     * @return the llave
     */
    public String getLlave() {
        return llave== null ? "" : llave;
    }

    /**
     * @param llave the llave to set
     */
    public void setLlave(String llave) {
        this.llave = llave;
    }
}
