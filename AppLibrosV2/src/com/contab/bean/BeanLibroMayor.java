package com.contab.bean;

import java.math.BigDecimal;
import java.sql.Date;

public class BeanLibroMayor {

    private String Auxiliar;
    private String vaucher;
    private java.sql.Date fecha;
    private String fechaN;
    private String c_central;
    private String glosa;
    private BigDecimal debe;
    private BigDecimal haber;
    private BigDecimal deudor;
    private BigDecimal acreedor;
    private String cuenta;
    private String codigo_cuenta;
    private BigDecimal vienedebe;
    private BigDecimal vienehaber;

    public String getAuxiliar() {
        return Auxiliar;
    }

    public void setAuxiliar(String Auxiliar) {
        this.Auxiliar = Auxiliar;
    }

    public BigDecimal getAcreedor() {
        return acreedor;
    }

    public void setAcreedor(BigDecimal acreedor) {
        this.acreedor = acreedor;
    }

    public String getC_central() {
        return c_central;
    }

    public void setC_central(String c_central) {
        this.c_central = c_central;
    }

    public String getCodigo_cuenta() {
        return codigo_cuenta;
    }

    public void setCodigo_cuenta(String codigo_cuenta) {
        this.codigo_cuenta = codigo_cuenta;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getDeudor() {
        return deudor;
    }

    public void setDeudor(BigDecimal deudor) {
        this.deudor = deudor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }

    public String getVaucher() {
        return vaucher;
    }

    public void setVaucher(String vaucher) {
        this.vaucher = vaucher;
    }

    public BigDecimal getVieneacreedor() {
        return vieneacreedor;
    }

    public void setVieneacreedor(BigDecimal vieneacreedor) {
        this.vieneacreedor = vieneacreedor;
    }

    public BigDecimal getVienedebe() {
        return vienedebe;
    }

    public void setVienedebe(BigDecimal vienedebe) {
        this.vienedebe = vienedebe;
    }

    public BigDecimal getVienedeudor() {
        return vienedeudor;
    }

    public void setVienedeudor(BigDecimal vienedeudor) {
        this.vienedeudor = vienedeudor;
    }

    public BigDecimal getVienehaber() {
        return vienehaber;
    }

    public void setVienehaber(BigDecimal vienehaber) {
        this.vienehaber = vienehaber;
    }
     private BigDecimal vienedeudor;
    private BigDecimal vieneacreedor;

    /**
     * @return the fechaN
     */
    public String getFechaN() {
        return fechaN;
    }

    /**
     * @param fechaN the fechaN to set
     */
    public void setFechaN(String fechaN) {
        this.fechaN = fechaN;
    }


}
