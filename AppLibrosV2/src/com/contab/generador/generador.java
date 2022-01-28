/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.generador;

import com.contab.dao.Conexion;
import com.contab.dao.Consultas;
import com.contab.dao.ObjetoConexion;
import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author cixtic04
 */
public class generador {

    public static String listarCamposBean(String tabla) throws SQLException {
        CallableStatement cst;
        ResultSet rs = null;
        String codigo = "";
        String sql = "";
        sql = "select COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE from USER_TAB_COLUMNS where TABLE_NAME='TABLE10'";

        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        // ResultSet  rs ;

        try {
            if (!objCnx.getCnx().isClosed()) {
                ResultSet rsx = Consultas.listarSQLSinParametrosSinProcedimiento(sql, objCnx.getCnx());
                //rs = (ResultSet) objetos.get(0);
                //cst = (CallableStatement) objetos.get(1);
                codigo = "package com.contab.bean;";

                codigo += "import java.math.BigDecimal;";
                codigo += "\npublic class " + tabla + "{\n";
                if (rsx != null) {
                    while (rsx.next()) {
                        codigo += "private " + tipoDatos(rsx.getString(2)) + " " + rsx.getString(1) + ";" + "\n";
                        codigo += "\n\n";
                        codigo += "public " + tipoDatos(rsx.getString(2)) + " get" + rsx.getString(1) + "() {" + "\n";
                        codigo += "return " + rsx.getString(1) + ";\n";
                        codigo += "}";

                        codigo += "public void get" + rsx.getString(1) + "(" + tipoDatos(rsx.getString(2)) + " " + rsx.getString(1) + ") {" + "\n";
                        codigo += "this. " + rsx.getString(1) + "=" + rsx.getString(1) + ";\n";
                        codigo += "}";
                    }



                    //
                  /*
                    
                     *  codigo += "public " + tipoDatos(rsx.getString(2)) +" get"+rsx.getString(1) + "() "  + "\n";
                    codigo += "return "+rsx.getString(1);
                     */

                    /*  while (rsx.next()) {
                    codigo += "public " + tipoDatos(rsx.getString(2)) +" get"+rsx.getString(1) + "() "  + "\n";
                    codigo += "return "+rsx.getString(1);
                    }*/


                    codigo += "\n}";
                }
            }
        } catch (SQLException ex) {
            rs.close();

        }

        return codigo;
    }

    public static String listarCamposDao(String tabla) throws SQLException {
        CallableStatement cst;
        ResultSet rs = null;
        String codigo = "";
        String sql = "";
        sql = "select COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE from USER_TAB_COLUMNS where TABLE_NAME='TABLE10'";

        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        // ResultSet  rs ;

        try {
            if (!objCnx.getCnx().isClosed()) {
                ResultSet rsx = Consultas.listarSQLSinParametrosSinProcedimiento(sql, objCnx.getCnx());

                codigo = "package com.contab.dao;";
                codigo += "import java.sql.SQLException;";
                codigo += "import java.util.ArrayList;";
                codigo += "\npublic class " + tabla + "{\n";
                int cantP = 0;
                String parm = "";
                if (rsx != null) {
                    while (rsx.next()) {
                        cantP = cantP + 1;
                        parm += "?,";
                    }
                }
                parm = parm + ",?";
                /////////*******  consultar ***********////////////////
                codigo += "\npublic static ArrayList Consultar" + tabla + "(ArrayList param) throws SQLException {\n";
                codigo += "ObjetoConexion cnxOra   =new ObjetoConexion(); \n";
                codigo += "objCnx=cnxOra.conectarORACLE(); \n";
                codigo += "if(!objCnx.getCnx().isClosed()){ \n";
                //   codigo += "" String proc = "{call ?:=CONSULTATABLE10()}"; \n";
                codigo += "if(!objCnx.getCnx().isClosed()){ \n";
                codigo += "return Consultas.funcion(proc, param,objCnx.getCnx()); \n";
                codigo += "   }\n";
                codigo += "return null;\n";
                codigo += "   }\n\n";
                ///**********fin de la consulta****************/////////

                /////////*******  Mantemiento ***********////////////////
                codigo += "\npublic static ArrayList Mantemiento" + tabla + "(ArrayList param) throws SQLException {\n";
                codigo += "ObjetoConexion cnxOra   =new ObjetoConexion(); \n";
                codigo += "objCnx=cnxOra.conectarORACLE(); \n";
                codigo += "if(!objCnx.getCnx().isClosed()){ \n";
                //   codigo += "" String proc = "{call ?:=CONSULTATABLE10()}"; \n";
                codigo += "if(!objCnx.getCnx().isClosed()){ \n";
                codigo += "return Consultas.funcion(proc, param,objCnx.getCnx()); \n";
                codigo += "   }\n";
                codigo += "return null;\n";
                codigo += "   }\n\n";
                ///**********fin de la consulta****************/////////


                codigo += "\n}";

            }
        } catch (SQLException ex) {
            rs.close();

        }

        return codigo;
    }

    public static String generarScript(String tabla) throws SQLException {
        CallableStatement cst;
        ResultSet rs = null;
        String codigo = "";
        String sql = "";
        sql = "select COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE from USER_TAB_COLUMNS where TABLE_NAME='TABLE10'";

        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        // ResultSet  rs ;

        try {
            if (!objCnx.getCnx().isClosed()) {
                ResultSet rsx = Consultas.listarSQLSinParametrosSinProcedimiento(sql, objCnx.getCnx());

                codigo = "create or replace ";
                codigo += "function MANTAN" + tabla + "(";
                Hashtable Campos = new Hashtable();
                Hashtable tipos = new Hashtable();
                int cantP = 0;
                String parm = "";
                if (rsx != null) {
                    while (rsx.next()) {
                        cantP = cantP + 1;
                        tipos.put(cantP, rsx.getString(2));
                        Campos.put(cantP, rsx.getString(1));

                    }
                }

                //CodigoH.get(i)
                int cantFilas = tipos.size();

                for (int i = 1; i <= cantFilas; i++) {

                    if (i == cantFilas) {
                        codigo += Campos.get(i).toString() + " in " + tipos.get(i).toString();
                    } else {
                        codigo += Campos.get(i).toString() + " in " + tipos.get(i).toString() + ",";
                    }

                }
                codigo += ",operacion in number";
                codigo += ") return number \n";
                codigo += " AS \n";
                codigo += " begin \n";
                codigo += " if(operacion = 1)then \n";
                codigo += " INSERT INTO " + tabla + " (";
                for (int j = 1; j <= cantFilas; j++) {
                    if (j == cantFilas) {
                        codigo += Campos.get(j).toString();
                    } else {
                        codigo += Campos.get(j).toString() + ",";
                    }
                }
                codigo += ") values ( ";

                for (int k = 1; k <= cantFilas; k++) {
                    if (k == cantFilas) {
                        codigo += Campos.get(k).toString();
                    } else {
                        codigo += Campos.get(k).toString() + ",";
                    }
                }
                codigo += ") ; \n";
                codigo += "commit; \n";
                codigo += "return 1; \n";
                codigo += " elsif(operacion = 2)then \n";
                codigo += " UPDATE " + tabla + " SET ";

                for (int k = 2; k <= cantFilas; k++) {
                    if (k == cantFilas) {
                        codigo += Campos.get(k).toString() + " = " + Campos.get(k).toString();
                    } else {
                        codigo += Campos.get(k).toString() + " = " + Campos.get(k).toString() + ",";
                    }
                }
                codigo += " where " + Campos.get(1).toString() + " = " + Campos.get(1).toString();
                codigo += " ; \n";
                codigo += "commit; \n";
                codigo += "return 2; \n";
                codigo += " elsif(operacion = 3)then \n";
                codigo += "DELETE from " + tabla;
                codigo += " where " + Campos.get(1).toString() + " = " + Campos.get(1).toString();
                codigo += " ; \n";
                codigo += "commit; \n";
                codigo += "return 3; \n";
                codigo += " else \n";
                codigo += "return 0; \n";
                codigo += "end if; \n";
                codigo += "exception \n";
                codigo += "WHEN NO_DATA_FOUND THEN \n";
                codigo += "return -1; \n";
                codigo += "WHEN OTHERS THEN \n";
                codigo += "return -1; \n";
                codigo += "end MANTAN" + tabla + ";";
              

            }
        } catch (SQLException ex) {
            rs.close();

        }

        return codigo;
    }

    public static String tipoDatos(String type) {

        String typep = "";

        if (type.equals("NUMBER")) {
            typep = "BigDecimal";
        } else if (type.equals("VARCHAR2")) {
            typep = "String";
        }


        return typep;

    }
}
