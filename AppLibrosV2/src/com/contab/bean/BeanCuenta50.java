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
public class BeanCuenta50 {

    private int idCuenta50;
    private String compania;
     private String tabla2;
    private String denominacion;
      private String anno;
    private int numero;
       private BigDecimal documento;
    private String tipoAccion;
    private int numeroAccion;
      private BigDecimal capital;
    private String operacion = "n";
    private boolean selecccion;

    /**
     * @return the idCuenta50
     */
    public int getIdCuenta50() {
        return idCuenta50;
    }

    /**
     * @param idCuenta50 the idCuenta50 to set
     */
    public void setIdCuenta50(int idCuenta50) {
        this.idCuenta50 = idCuenta50;
    }

    /**
     * @return the idCompania
     */
    /**
     * @return the denominacion
     */
    public String getDenominacion() {
        return denominacion;
    }

    /**
     * @param denominacion the denominacion to set
     */
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
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
     * @return the tipoAccion
     */
    public String getTipoAccion() {
        return tipoAccion;
    }

    /**
     * @param tipoAccion the tipoAccion to set
     */
    public void setTipoAccion(String tipoAccion) {
        this.tipoAccion = tipoAccion;
    }

    /**
     * @return the numeroAccion
     */
    public int getNumeroAccion() {
        return numeroAccion;
    }

    /**
     * @param numeroAccion the numeroAccion to set
     */
    public void setNumeroAccion(int numeroAccion) {
        this.numeroAccion = numeroAccion;
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
     * @return the capital
     */
    public BigDecimal getCapital() {
        return capital==null?BigDecimal.ZERO:capital;
        
    }

    /**
     * @param capital the capital to set
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
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
     * @return the documento
     */
    public BigDecimal getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(BigDecimal documento) {
        this.documento = documento;
    }
}
