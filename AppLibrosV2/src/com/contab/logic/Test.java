/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanKardex;
import com.contab.dao.DaoKardex;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author cixtic04
 */
public class Test {

    public static void main(String arg[]) throws SQLException {                
        ResultSet rs = null;
        CallableStatement cst = null;
        ArrayList param = new ArrayList();
        param.add(OracleTypes.CURSOR);
        param.add("03");
        param.add("03");
        try {
        /*    ArrayList objetos = DaoKardex.NUMDOCUMENTOKARDEX(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
          //  ArrayList<ArrayList<BeanKardex>> repor = LogicKardex.repositorio(rs);
            BeanKardex bean=new BeanKardex();
            BeanKardex beanaux=new BeanKardex();
            ArrayList<BeanKardex> array;
           for(int i=0;i<repor.size();i++){
               array=(ArrayList<BeanKardex>)repor.get(i);
               Iterator iterar=array.iterator();
               System.out.println("busqueda en el repositorio: "+i);
               while(iterar.hasNext()){
                   beanaux=(BeanKardex)iterar.next();
                   System.out.println(beanaux.getCodigoBusquedad());
                   if(beanaux.getCodigoBusquedad().equals("00333216")){
                       bean=beanaux;
                       array.remove(beanaux);
                    //   System.out.println("Eliminado ");
                       break;
                   }
               }
           }*/
           // System.out.println("tamaño: "+repor.size());
           // System.out.println("busqueda: "+bean.getNumero());

        } catch (Exception ex) {
            System.err.println(ex.getCause());
            System.err.println(ex.getMessage());
       }
    }
}
