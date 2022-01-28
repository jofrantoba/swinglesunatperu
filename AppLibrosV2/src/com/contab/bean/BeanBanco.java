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
public class BeanBanco {

    private int idTabla3;
    private String compania;
    private String cuentaBanco;
    private String center;
    private String numCuenta;
    private String Descripcion;
    private String codigoCentro;
    private String numeroCuentaBanco;
    private String codigoCuentaSunat;
    private String nombreBanco;
    private String operacion = "n";
    private boolean selecccion;
    private String Auxiliar;
    private String vaucher;
    private String tipo;
    private BigDecimal deudor;
    private BigDecimal acreedor;
    

    /**
     * @return the compania
     */
    public String getCompania() {
        return compania==null?"":compania;
    }

    /**
     * @param compania the compania to set
     */
    public void setCompania(String compania) {
        this.compania = compania;
    }

    /**
     * @return the cuentaBanco
     */
    public String getCuentaBanco() {
        return cuentaBanco==null?"":cuentaBanco;
    }

    /**
     * @param cuentaBanco the cuentaBanco to set
     */
    public void setCuentaBanco(String cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    /**
     * @return the numCuenta
     */
    public String getNumCuenta() {
        return numCuenta==null?"":numCuenta;
    }

    /**
     * @param numCuenta the numCuenta to set
     */
    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    /**
     * @return the codigoCentro
     */
    public String getCodigoCentro() {
        return codigoCentro==null?"":codigoCentro;
    }

    /**
     * @param codigoCentro the codigoCentro to set
     */
    public void setCodigoCentro(String codigoCentro) {
        this.codigoCentro = codigoCentro;
    }

    /**
     * @return the numeroCuentaBanco
     */
    public String getNumeroCuentaBanco() {
        return numeroCuentaBanco==null?"":numeroCuentaBanco;
    }

    /**
     * @param numeroCuentaBanco the numeroCuentaBanco to set
     */
    public void setNumeroCuentaBanco(String numeroCuentaBanco) {
        this.numeroCuentaBanco = numeroCuentaBanco;
    }

    /**
     * @return the codigoCuentaSunat
     */
    public String getCodigoCuentaSunat() {
        return codigoCuentaSunat == null ? "0" : codigoCuentaSunat;
    }

    /**
     * @param codigoCuentaSunat the codigoCuentaSunat to set
     */
    public void setCodigoCuentaSunat(String codigoCuentaSunat) {
        this.codigoCuentaSunat = codigoCuentaSunat;
    }

    /**
     * @return the nombreBanco
     */
    public String getNombreBanco() {
        return nombreBanco==null?"":nombreBanco;
    }

    /**
     * @param nombreBanco the nombreBanco to set
     */
    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    /**
     * @return the idTabla3
     */
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
     * @return the center
     */
    public String getCenter() {
        return center==null?"":center;
    }

    /**
     * @param center the center to set
     */
    public void setCenter(String center) {
        this.center = center;
    }

    /**
     * @return the idTabla3
     */
    public int getIdTabla3() {
        return idTabla3;
    }

    /**
     * @param idTabla3 the idTabla3 to set
     */
    public void setIdTabla3(int idTabla3) {
        this.idTabla3 = idTabla3;
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
     * @return the Auxiliar
     */
    public String getAuxiliar() {
        return Auxiliar==null?"":Auxiliar;
    }

    /**
     * @param Auxiliar the Auxiliar to set
     */
    public void setAuxiliar(String Auxiliar) {
        this.Auxiliar = Auxiliar;
    }

    /**
     * @return the vaucher
     */
    public String getVaucher() {
        return vaucher==null?"":vaucher;
    }

    /**
     * @param vaucher the vaucher to set
     */
    public void setVaucher(String vaucher) {
        this.vaucher = vaucher;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo==null?"":tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the deudor
     */
    public BigDecimal getDeudor() {
        return deudor;
    }

    /**
     * @param deudor the deudor to set
     */
    public void setDeudor(BigDecimal deudor) {
        this.deudor = deudor;
    }

    /**
     * @return the acreedor
     */
    public BigDecimal getAcreedor() {
        return acreedor;
    }

    /**
     * @param acreedor the acreedor to set
     */
    public void setAcreedor(BigDecimal acreedor) {
        this.acreedor = acreedor;
    }
}
