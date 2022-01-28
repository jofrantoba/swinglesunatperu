/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

import java.math.BigDecimal;

/**
 *
 * @author Administrador
 */
public class BeanDaot {
    private int contador;
    private int numEmp;
    private String numDocEmp;
    private String periodo;
    private String tipoPersona;
    private int tipoDocPersona;
    private String numDocPersona;
    private BigDecimal importe;
    private String apPaterno;
    private String apMaterno;
    private String nombre1;
    private String nombre2;
    private String razonSocial;
    private String formula;
    private String direccion;
    private String departamento;
    private String provincia;
    private String distrito;
    private String operaciones;
    
    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public String getNumDocEmp() {
        return numDocEmp;
    }

    public void setNumDocEmp(String numDocEmp) {
        this.numDocEmp = numDocEmp;
    }

    public String getNumDocPersona() {
        return numDocPersona;
    }

    public void setNumDocPersona(String numDocPersona) {
        this.numDocPersona = numDocPersona;
    }

    public int getNumEmp() {
        return numEmp;
    }

    public void setNumEmp(int numEmp) {
        this.numEmp = numEmp;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getTipoDocPersona() {
        return tipoDocPersona;
    }

    public void setTipoDocPersona(int tipoDocPersona) {
        this.tipoDocPersona = tipoDocPersona;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(String operaciones) {
        this.operaciones = operaciones;
    }        
    
}
