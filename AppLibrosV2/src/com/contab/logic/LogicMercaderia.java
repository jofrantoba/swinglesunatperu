/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanBalanceComprobacion;
import com.contab.bean.BeanCuenta;
import com.contab.bean.BeanMercaderia;
import com.contab.bean.BeanTabla6;
import com.contab.dao.Conexion;
import com.contab.dao.DaoBalanceComprobacion;
import com.contab.dao.DaoCuenta;
import com.contab.dao.DaoEmpresas;
import com.contab.dao.DaoMercaderia;
import com.contab.datasource.DatasourceBalanceComprobacion;
import com.contab.datasource.DatasourceMercaderia;
import com.contab.util.ClassFecha;
import com.contab.util.Exportar;
import com.contab.view.ViewApplication;
import java.io.File;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRParameter;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author cixtic04
 */
public class LogicMercaderia {

    public static void listaMercaderia(String empresa, String ruc, String compania, String ano,String Ejercicio,String metodo, File ruta, String tipoExpor) throws SQLException, Exception {

        if (ruta != null) {
            ResultSet rs = null;
            CallableStatement cst = null;
            Conexion cnx = null;
            try {

                DatasourceMercaderia dataMercaderia = new DatasourceMercaderia();
                ArrayList param = new ArrayList();
                param.add(OracleTypes.CURSOR);
                param.add(compania); //numero de la empresa o compa;ia
                String annno=ano;
                ano=ano+"-12";
                param.add(ano); //ano
                ArrayList objetos = new ArrayList();
                objetos = DaoMercaderia.consultaMercaderia(param);
                rs = (ResultSet) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                cnx = (Conexion) objetos.get(2);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO MERCADERÍA Nª: " + empresa + " DEL PERIODO: " + ano);

                 ResultSet resultabla6 = LogicTabla6.ListarUnidadArticulos();
                       ArrayList<ArrayList<BeanTabla6>> repositorioTabla6 = new ArrayList<ArrayList<BeanTabla6>>();
                       
                        repositorioTabla6 = null;
                // repositorioTabla6 = null;
                if (resultabla6 != null) {
                    repositorioTabla6 = LogicTabla6.repositorio(resultabla6);
                }
                   BeanTabla6 beanBuscadoTabla6;

                if (rs != null && cst != null && cnx != null) {
                    while (rs.next()) {

                        BeanMercaderia bnMercaderia = new BeanMercaderia();

                        String codigo = rs.getString(3);
                        if(!codigo.equals("000000")){

                        String descripcion = rs.getString(4);
                        String tipo5 = "01";
                        String tipo6 = rs.getString(6);
          
                        
                        beanBuscadoTabla6 = LogicTabla6.buscarTabla6(repositorioTabla6, tipo6);

                        BigDecimal cantidad = rs.getBigDecimal(5);
                        BigDecimal costoUnitario = rs.getBigDecimal(7);
                      //  System.out.print(cantidad+"//"+costoUnitario);

                        bnMercaderia.setCodigo(codigo);
                        bnMercaderia.setDescripcion(descripcion);
                        bnMercaderia.setTipo5(tipo5);
                        bnMercaderia.setTipo6(beanBuscadoTabla6.getCodigoSunat());
                        bnMercaderia.setCantidad(cantidad);
                        bnMercaderia.setCostounitario(costoUnitario);



                        dataMercaderia.addBalance(bnMercaderia);

                    }}
                }
                //  }


                Map parameters = new HashMap();
                //EJERCICIO
                parameters.put("EJERCICIO", annno);
                parameters.put("P_RAZON", empresa);
                parameters.put("P_RUC", ruc);
                parameters.put("P_PERIODO", ano);
                parameters.put("METODO", "COSTO PROMEDIO PONDERADO");
                  parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptcaja = "/com/contab/report/mercaderia_cuenta21.jasper";
                Exportar ex = new Exportar(ruta, parameters, tipoExpor, dataMercaderia, rutarptcaja);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE DEL LIBRO MERCADERÍA !!");

                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE DEL LIBRO MERCADERÍA ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
                rs.close();
                cst.close();
                cnx.destroy();

            } catch (SQLException ex) {
                rs.close();
                cst.close();
                cnx.destroy();
            } finally {
                rs.close();
                cst.close();
                cnx.destroy();
            }


        }
    }
}
