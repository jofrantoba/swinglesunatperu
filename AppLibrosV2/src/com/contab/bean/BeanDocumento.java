/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.bean;

/**
 *
 * @author Administrador
 */
public class BeanDocumento {
    private String  tipoPersona;
    private int tipoDocumento;
    private String documento;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    
    
}
