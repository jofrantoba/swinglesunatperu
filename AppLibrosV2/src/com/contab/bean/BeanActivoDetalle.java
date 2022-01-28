package com.contab.bean;

public class BeanActivoDetalle {
   
    private String cod_AuxiliarCodigo;
    private String cod_AuxiliarCuenta;
    private String tipo_DocRef;
    private String num_serie;
    private String num_preimpreso;

    public String getCod_AuxiliarCodigo() {
        return cod_AuxiliarCodigo;
    }

    public void setCod_AuxiliarCodigo(String cod_AuxiliarCodigo) {
        this.cod_AuxiliarCodigo = cod_AuxiliarCodigo;
    }

    public String getCod_AuxiliarCuenta() {
        return cod_AuxiliarCuenta;
    }

    public void setCod_AuxiliarCuenta(String cod_AuxiliarCuenta) {
        this.cod_AuxiliarCuenta = cod_AuxiliarCuenta;
    }

    public String getNum_preimpreso() {
        return num_preimpreso;
    }

    public void setNum_preimpreso(String num_preimpreso) {
        this.num_preimpreso = num_preimpreso;
    }

    public String getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(String num_serie) {
        this.num_serie = num_serie;
    }

    public String getTipo_DocRef() {
        return tipo_DocRef;
    }

    public void setTipo_DocRef(String tipo_DocRef) {
        this.tipo_DocRef = tipo_DocRef;
    } 
    
    
}
