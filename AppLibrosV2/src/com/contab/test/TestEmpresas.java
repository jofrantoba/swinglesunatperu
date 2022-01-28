/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.test;

import com.contab.bean.BeanAsiento;
import com.contab.dao.DaoEmpresas;
import com.contab.logic.LogicAsiento;
import com.contab.logic.LogicCuenta;
import com.contab.logic.LogicEmpresa;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author 777
 */
public class TestEmpresas {
    public static void main(String arg[]) throws SQLException{
        LogicEmpresa.listaDeEmpresa();
        ArrayList<BeanAsiento> lista=LogicAsiento.listaDeAsientos("02");
        Iterator itarar=lista.iterator();
        while(itarar.hasNext()){
            BeanAsiento obj=(BeanAsiento)itarar.next();
            System.out.print(obj.getCodigo());
           System.out.println(obj.getDescripcion());
             System.out.println("458535,21143");
        BigDecimal x = new BigDecimal("458535,21143");
        BigDecimal y = new BigDecimal("3.6");
        BigDecimal x2 = x.setScale(0, RoundingMode.FLOOR);
        BigDecimal y2 = y.setScale(0, RoundingMode.UP);
        System.out.print(x+" x "+x2+"  -"+y2);
        }
        System.out.print("----------------");
        
          System.out.print("458535,21143");
        BigDecimal x = new BigDecimal("458535,21143");
        BigDecimal y = new BigDecimal("3.6");
        BigDecimal x2 = x.setScale(0, RoundingMode.FLOOR);
        BigDecimal y2 = y.setScale(0, RoundingMode.UP);
        System.out.print(x+" x "+x2+"  -"+y2);
        //int contador=0;
        /*while(true){
        System.out.println(LogicCuenta.glosaDeCuenta("02", "10101", "2010"));
        contador=contador+1;        
        }*/
    }
}
