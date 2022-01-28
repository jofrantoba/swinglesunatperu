/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanAsiento;
import com.contab.dao.DaoAsiento;
import com.contab.dao.DaoEmpresas;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author 777
 */
public class LogicAsiento {

    public static ArrayList<BeanAsiento> listaDeAsientos(String codigoEmpresa) throws SQLException {
        ResultSet rs=null;
        CallableStatement cst=null;
        try {            
            ArrayList<BeanAsiento> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(codigoEmpresa);
            ArrayList objetos = (ArrayList) DaoAsiento.consultaAsiento(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                BeanAsiento objFirst = new BeanAsiento();
                objFirst.setCodigo("all");
                objFirst.setDescripcion("Todos");
                lista.add(objFirst);
                while (rs.next()) {
                    BeanAsiento objAsiento = new BeanAsiento();
                    objAsiento.setCodigo(rs.getString(1));
                    objAsiento.setDescripcion(rs.getString(2));
                    lista.add(objAsiento);
                }                                          
                BeanAsiento asientoInicial=new BeanAsiento();
                for(int i=0;i<lista.size();i++){
                    if(lista.get(i).getCodigo().equals("98")){
                        asientoInicial=lista.get(i);
                        lista.remove(i);
                    }                    
                }
                lista.add(1,asientoInicial);
                rs.close();
                cst.close();
                DaoEmpresas.objCnx.destroy();
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }

    }
}
