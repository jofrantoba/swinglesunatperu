package com.contab.bean;

import java.math.BigDecimal;
import java.sql.Date;


public class BeanConsultaActivo33 {
 
    private String cod_empresa;
    private String cod_jnrtype;
    private String cod_jnrnumber;
    private String cod_cuenta;
    private Date fecha_creacion;
    private BigDecimal monto;
    private String glosa;

    public String getCod_cuenta() {
        return cod_cuenta;
    }

    public void setCod_cuenta(String cod_cuenta) {
        this.cod_cuenta = cod_cuenta;
    }

    public String getCod_empresa() {
        return cod_empresa;
    }

    public void setCod_empresa(String cod_empresa) {
        this.cod_empresa = cod_empresa;
    }

    public String getCod_jnrnumber() {
        return cod_jnrnumber;
    }

    public void setCod_jnrnumber(String cod_jnrnumber) {
        this.cod_jnrnumber = cod_jnrnumber;
    }

    public String getCod_jnrtype() {
        return cod_jnrtype;
    }

    public void setCod_jnrtype(String cod_jnrtype) {
        this.cod_jnrtype = cod_jnrtype;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
}
