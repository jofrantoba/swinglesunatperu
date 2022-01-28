/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.contab.bean;

/**
 *
 * @author cixtic04
 */
public class BeanMovimientoCajaBanco {


private String NROVOUCH;
private String NRODOC;
private String GIRADO;
private String GLOSA;
private String  ANULADO;
private String  tipoMov;
private String numero;
private String SECUENCIA;
private String SECUENCIA1;

    public BeanMovimientoCajaBanco() {
    }

    /**
     * @return the NROVOUCH
     */
    public String getNROVOUCH() {
        return NROVOUCH==null?"":NROVOUCH;
    }

    /**
     * @param NROVOUCH the NROVOUCH to set
     */
    public void setNROVOUCH(String NROVOUCH) {
        this.NROVOUCH = NROVOUCH;
    }

    /**
     * @return the NRODOC
     */
    public String getNRODOC() {
        return NRODOC==null?"":NRODOC;
    }

    /**
     * @param NRODOC the NRODOC to set
     */
    public void setNRODOC(String NRODOC) {
        this.NRODOC = NRODOC;
    }

    /**
     * @return the GIRADO
     */
    public String getGIRADO() {
        return GIRADO==null?"":GIRADO;
    }

    /**
     * @param GIRADO the GIRADO to set
     */
    public void setGIRADO(String GIRADO) {
        this.GIRADO = GIRADO;
    }

    /**
     * @return the GLOSA
     */
    public String getGLOSA() {
        return GLOSA==null?"":GLOSA;
    }

    /**
     * @param GLOSA the GLOSA to set
     */
    public void setGLOSA(String GLOSA) {
        this.GLOSA = GLOSA;
    }

    /**
     * @return the ANULADO
     */
    public String getANULADO() {
        return ANULADO==null?"0":ANULADO;
    }

    /**
     * @param ANULADO the ANULADO to set
     */
    public void setANULADO(String ANULADO) {
        this.ANULADO = ANULADO;
    }

    /**
     * @return the tipoMov
     */
    public String getTipoMov() {
        return tipoMov==null?"XX":tipoMov;
    }

    /**
     * @param tipoMov the tipoMov to set
     */
    public void setTipoMov(String tipoMov) {
        this.tipoMov =tipoMov ;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero==null?"0":numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the SECUENCIA
     */
    public String getSECUENCIA() {
        return SECUENCIA==null?"0":SECUENCIA;
    }

    /**
     * @param SECUENCIA the SECUENCIA to set
     */
    public void setSECUENCIA(String SECUENCIA) {
        this.SECUENCIA = SECUENCIA;
    }

    /**
     * @return the SECUENCIA1
     */
    public String getSECUENCIA1() {
        return SECUENCIA1==null?"0":SECUENCIA1;
    }

    /**
     * @param SECUENCIA1 the SECUENCIA1 to set
     */
    public void setSECUENCIA1(String SECUENCIA1) {
        this.SECUENCIA1 = SECUENCIA1;
    }

}
