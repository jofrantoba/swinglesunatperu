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
public class BeanBalanceComprobacion {

    private String cuenta;
    private String denominacion;
    private BigDecimal debeSaldoInicial;
    private BigDecimal haberSalidoInicial;
    private BigDecimal debeMovimiento;
    private BigDecimal haberMovimiento;
    private BigDecimal Flag;
    private BigDecimal MDebe;
    private BigDecimal MHaber;
    private BigDecimal SDebe;
    private BigDecimal SHaber;
    private BigDecimal TDebe;
    private BigDecimal THaber;
    private BigDecimal FDebe;
    private BigDecimal FHaber;
    private BigDecimal RDebe;
    private BigDecimal RHaber;

    //M S T F R
    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta==null?"xxxxxxxxxxxx":cuenta ;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the debeSaldoInicial
     */
    public BigDecimal getDebeSaldoInicial() {
        return debeSaldoInicial== null ? BigDecimal.ZERO : debeSaldoInicial;
    }

    /**
     * @param debeSaldoInicial the debeSaldoInicial to set
     */
    public void setDebeSaldoInicial(BigDecimal debeSaldoInicial) {
        this.debeSaldoInicial = debeSaldoInicial;
    }

    /**
     * @return the haberSalidoInicial
     */
    public BigDecimal getHaberSalidoInicial() {
        return haberSalidoInicial== null ? BigDecimal.ZERO : haberSalidoInicial;
    }

    /**
     * @param haberSalidoInicial the haberSalidoInicial to set
     */
    public void setHaberSalidoInicial(BigDecimal haberSalidoInicial) {
        this.haberSalidoInicial = haberSalidoInicial;
    }

    /**
     * @return the debeMovimiento
     */
    public BigDecimal getDebeMovimiento() {
        return debeMovimiento== null ? BigDecimal.ZERO : debeMovimiento;
    }

    /**
     * @param debeMovimiento the debeMovimiento to set
     */
    public void setDebeMovimiento(BigDecimal debeMovimiento) {
        this.debeMovimiento = debeMovimiento;
    }

    /**
     * @return the haberMovimiento
     */
    public BigDecimal getHaberMovimiento() {
        return haberMovimiento== null ? BigDecimal.ZERO : haberMovimiento;
    }

    /**
     * @param haberMovimiento the haberMovimiento to set
     */
    public void setHaberMovimiento(BigDecimal haberMovimiento) {
        this.haberMovimiento = haberMovimiento;
    }

    /**
     * @return the Flag
     */
    public BigDecimal getFlag() {
        return Flag;
    }

    /**
     * @param Flag the Flag to set
     */
    public void setFlag(BigDecimal Flag) {
        this.Flag = Flag;
    }

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
     * @return the MDebe
     */
    public BigDecimal getMDebe() {
        return MDebe;
    }

    /**
     * @param MDebe the MDebe to set
     */
    public void setMDebe(BigDecimal MDebe) {
        this.MDebe = MDebe;
    }

    /**
     * @return the MHaber
     */
    public BigDecimal getMHaber() {
        return MHaber;
    }

    /**
     * @param MHaber the MHaber to set
     */
    public void setMHaber(BigDecimal MHaber) {
        this.MHaber = MHaber;
    }

    /**
     * @return the SDebe
     */
    public BigDecimal getSDebe() {
        return SDebe;
    }

    /**
     * @param SDebe the SDebe to set
     */
    public void setSDebe(BigDecimal SDebe) {
        this.SDebe = SDebe;
    }

    /**
     * @return the SHaber
     */
    public BigDecimal getSHaber() {
        return SHaber;
    }

    /**
     * @param SHaber the SHaber to set
     */
    public void setSHaber(BigDecimal SHaber) {
        this.SHaber = SHaber;
    }

    /**
     * @return the TDebe
     */
    public BigDecimal getTDebe() {
        return TDebe;
    }

    /**
     * @param TDebe the TDebe to set
     */
    public void setTDebe(BigDecimal TDebe) {
        this.TDebe = TDebe;
    }

    /**
     * @return the THaber
     */
    public BigDecimal getTHaber() {
        return THaber;
    }

    /**
     * @param THaber the THaber to set
     */
    public void setTHaber(BigDecimal THaber) {
        this.THaber = THaber;
    }

    /**
     * @return the FDebe
     */
    public BigDecimal getFDebe() {
        return FDebe;
    }

    /**
     * @param FDebe the FDebe to set
     */
    public void setFDebe(BigDecimal FDebe) {
        this.FDebe = FDebe;
    }

    /**
     * @return the FHaber
     */
    public BigDecimal getFHaber() {
        return FHaber;
    }

    /**
     * @param FHaber the FHaber to set
     */
    public void setFHaber(BigDecimal FHaber) {
        this.FHaber = FHaber;
    }

    /**
     * @return the RDebe
     */
    public BigDecimal getRDebe() {
        return RDebe;
    }

    /**
     * @param RDebe the RDebe to set
     */
    public void setRDebe(BigDecimal RDebe) {
        this.RDebe = RDebe;
    }

    /**
     * @return the RHaber
     */
    public BigDecimal getRHaber() {
        return RHaber;
    }

    /**
     * @param RHaber the RHaber to set
     */
    public void setRHaber(BigDecimal RHaber) {
        this.RHaber = RHaber;
    }
}
