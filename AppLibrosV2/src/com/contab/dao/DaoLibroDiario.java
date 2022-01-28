/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.dao;

import com.contab.view.ViewApplication;
import java.util.ArrayList;

/**
 *
 * @author 777
 */
public class DaoLibroDiario {
    
    public static ArrayList consultarDiario(ArrayList param) {        
        boolean estado=DaoEmpresas.objCnx.conectarBD();        
        if(estado){
            ViewApplication.mensajeEstado.setText("ESTA TAREA PUEDE TARDAR VARIOS MINUTOS :)");
             String proc = "{call ?:=rptbookday(?,?,?,?)}";
             
        return Consultas.funcionConsultar(proc, param,DaoEmpresas.objCnx.getCnx());
        }
        return null;
    }
    
    
}
