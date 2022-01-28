/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

import com.contab.util.CellRedenderImage;
/**
 *
 * @author Administrador
 */
public class BeanEmpleado {    
    private String estadoOperacion="n";
    private boolean seleccion;
    private String compania;
    private String codigoAuxiliar;
    private String cuentaAuxiliar;
    private String descripcion;
    private String ruc;

    public String getCodigoAuxiliar() {
        return codigoAuxiliar;
    }

    public void setCodigoAuxiliar(String codigoAuxiliar) {
        this.codigoAuxiliar = codigoAuxiliar;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getCuentaAuxiliar() {
        return cuentaAuxiliar;
    }

    public void setCuentaAuxiliar(String cuentaAuxiliar) {
        this.cuentaAuxiliar = cuentaAuxiliar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(String estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }       
        
}
