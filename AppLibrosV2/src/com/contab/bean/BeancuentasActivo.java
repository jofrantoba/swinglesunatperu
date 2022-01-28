package com.contab.bean;

import java.math.BigDecimal;

public class BeancuentasActivo {

    private String cod_empresa;
    private String cod_centro;
    private String cod_cuenta;   
    private BigDecimal monto;


    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
   
     
    public String getCod_centro() {
        return cod_centro;
    }

    public void setCod_centro(String cod_centro) {
        this.cod_centro = cod_centro;
    }

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
}
