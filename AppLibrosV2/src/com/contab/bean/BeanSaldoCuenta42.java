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
public class BeanSaldoCuenta42 {
    
    private String tabla2;
    private String numero;
    private String Denominacion;
    private BigDecimal monto;
    private String fecha;
    private String cuenta;
    private String centro;
    private String preimpreso;
    private String serie;
    private String codProd;
      private String tipoDoc;


    /**
     * @return the tabla2
     */
    public String getTabla2() {
        return tabla2==null?"":tabla2;
    }

    /**
     * @param tabla2 the tabla2 to set
     */
    public void setTabla2(String tabla2) {
        this.tabla2 = tabla2;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero==null?"":numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the Denominacion
     */
    public String getDenominacion() {
        return Denominacion==null?"":Denominacion;
    }

    /**
     * @param Denominacion the Denominacion to set
     */
    public void setDenominacion(String Denominacion) {
        this.Denominacion = Denominacion;
    }

    /**
     * @return the monto
     */
    public BigDecimal getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha==null?"":fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the cuenta
     */
    public String getCuenta() {
        return cuenta==null?"":cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * @return the centro
     */
    public String getCentro() {
        return centro==null?"":centro;
    }

    /**
     * @param centro the centro to set
     */
    public void setCentro(String centro) {
        this.centro = centro;
    }

    /**
     * @return the preimpreso
     */
    public String getPreimpreso() {
        return preimpreso==null?"":preimpreso;
    }

    /**
     * @param preimpreso the preimpreso to set
     */
    public void setPreimpreso(String preimpreso) {
        this.preimpreso = preimpreso;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie==null?"":serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * @return the codProd
     */
    public String getCodProd() {
        return codProd==null?"":codProd;
    }

    /**
     * @param codProd the codProd to set
     */
    public void setCodProd(String codProd) {
        this.codProd = codProd;
    }

    /**
     * @return the tipoDoc
     */
    public String getTipoDoc() {
        return tipoDoc;
    }

    /**
     * @param tipoDoc the tipoDoc to set
     */
    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }
    
    
    
}
