/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.generador;

import com.contab.bean.BeanTablas;
import com.contab.dao.Conexion;
import com.contab.dao.Consultas;
import com.contab.dao.ObjetoConexion;
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
        sql = "select COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE from USER_TAB_COLUMNS where TABLE_NAME='" + tabla + "'";

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
                codigo += "\npublic class Bean" + tabla + "{\n";

                codigo += " private String operacion=\"n\";";
                codigo += "public String getOperacion() { \n";
                codigo += "return operacion; \n";
                codigo += "} \n";
                codigo += " public void setOperacion(String operacion) { \n";
                codigo += "  this.operacion = operacion; \n";
                codigo += "} \n";
                codigo += " private boolean selecccion;\n";
                codigo += "public boolean isSelecccion() {\n";
                codigo += "return selecccion;\n";
                codigo += "}\n";
                codigo += "public void setSelecccion(boolean selecccion) {\n";
                codigo += "  this.selecccion = selecccion;\n";
                codigo += "}\n";

                if (rsx != null) {
                    while (rsx.next()) {
                        codigo += "private " + tipoDatos(rsx.getString(2)) + " " + rsx.getString(1) + ";" + "\n";
                        codigo += "\n\n";
                        codigo += "public " + tipoDatos(rsx.getString(2)) + " get" + rsx.getString(1) + "() {" + "\n";
                        codigo += "return " + rsx.getString(1) + ";\n";
                        codigo += "}";

                        codigo += "public void set" + rsx.getString(1) + "(" + tipoDatos(rsx.getString(2)) + " " + rsx.getString(1) + ") {" + "\n";
                        codigo += "this. " + rsx.getString(1) + "=" + rsx.getString(1) + ";\n";
                        codigo += "}";
                    }



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
        sql = "select COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE from USER_TAB_COLUMNS where  TABLE_NAME='" + tabla + "'";

        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        // ResultSet  rs ;

        try {
            if (!objCnx.getCnx().isClosed()) {
                ResultSet rsx = Consultas.listarSQLSinParametrosSinProcedimiento(sql, objCnx.getCnx());

                codigo = "package com.contab.dao; \n";
                codigo += "import java.sql.SQLException; \n";
                codigo += "import java.util.ArrayList;\n";
                codigo += "import org.openide.util.Exceptions;\n";
                codigo += "\npublic class Dao" + tabla + "{\n";
                int cantP = 0;
                String parm = "";
                if (rsx != null) {
                    while (rsx.next()) {
                        cantP = cantP + 1;
                        parm += "?,";
                    }
                }
                parm = parm + "?";
                /////////*******  consultar ***********////////////////
                String proc1 = "CONSULTA" + tabla;
                String proc2 = "MANTAN" + tabla;
                codigo += "\n public static ArrayList Consultar" + tabla + "(ArrayList param)  {\n";
                codigo += "ObjetoConexion cnxOra   =new ObjetoConexion(); \n";
                codigo += "Conexion objCnx=cnxOra.conectarORACLE(); \n";
                codigo += "ArrayList objetos = new ArrayList();\n";
                codigo += " try { \n";
                codigo += "if(!objCnx.getCnx().isClosed()){ \n";
                codigo += "String proc = ";
                codigo += "\"{call ?:=" + proc1 + "()}\"";
                codigo += "; \n";
                codigo += " objetos = Consultas.funcion(proc, param, objCnx.getCnx()); \n";
                codigo += "objetos.add(objCnx); \n";
                codigo += "   }\n";
                codigo += "   } catch (SQLException ex) {\n";
                codigo += " Exceptions.printStackTrace(ex); \n";
                codigo += "   }\n";
                codigo += "     return null;\n";
                codigo += "   }\n\n";
                ///**********fin de la consulta****************/////////

                /////////*******  Mantemiento ***********////////////////
                codigo += "\n public static ArrayList Mantenimiento" + tabla + "(ArrayList param)  {\n";
                codigo += "ObjetoConexion cnxOra   =new ObjetoConexion(); \n";
                codigo += "Conexion objCnx=cnxOra.conectarORACLE(); \n";
                codigo += "ArrayList objetos = new ArrayList();\n";
                codigo += " try { \n";
                codigo += "if(!objCnx.getCnx().isClosed()){ \n";
                codigo += "String proc = ";
                codigo += "\"{call ?:=" + proc2 + "(" + parm + ")}\"";
                codigo += "; \n";
                codigo += " objetos = Consultas.funcion(proc, param, objCnx.getCnx()); \n";
                codigo += "objetos.add(objCnx); \n";
                codigo += "   }\n";
                codigo += "   } catch (SQLException ex) {\n";
                codigo += " Exceptions.printStackTrace(ex); \n";
                codigo += "   }\n";
                codigo += "     return null;\n";
                codigo += "   }\n\n";


                codigo += "\n}";

            }
        } catch (SQLException ex) {
            rs.close();

        }

        return codigo;
    }

    public static String listarCamposLogic(String tabla) throws SQLException {
        CallableStatement cst;
        ResultSet rs = null;
        String codigo = "";
        String sql = "";
        sql = "select COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE from USER_TAB_COLUMNS where  TABLE_NAME='" + tabla + "'";

        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        // ResultSet  rs ;

        try {
            if (!objCnx.getCnx().isClosed()) {
                ResultSet rsx = Consultas.listarSQLSinParametrosSinProcedimiento(sql, objCnx.getCnx());

                codigo = "package com.contab.Logic; \n";
                codigo += "import com.contab.bean.Bean" + tabla + " ;\n";
                codigo += "import com.contab.dao.Dao" + tabla + " ;\n";
                codigo += "import java.math.BigDecimal;\n";
                codigo += "import java.sql.CallableStatement;\n";
                codigo += "import java.sql.ResultSet;\n";
                codigo += "import java.sql.SQLException;\n";
                codigo += "import java.util.ArrayList;\n";
                codigo += "import java.util.Iterator;\n";
                codigo += "import java.util.HashSet;\n";
                codigo += "import java.util.HashSet;\n";
                codigo += "import javax.swing.JOptionPane;\n";
                codigo += "import oracle.jdbc.OracleTypes;\n";

                codigo += "\npublic class Logic" + tabla + "{\n";

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
                int cantFilas = tipos.size();
                /////////*******  consultar ***********////////////////
                String proc1 = "CONSULTA" + tabla;
                String proc2 = "MANTAN" + tabla;

                codigo += "\n public static ArrayList<Bean" + tabla + "> Listar" + tabla + "()  {\n";
                codigo += "ResultSet rs = null; \n";
                codigo += "CallableStatement cst = null; \n";
                codigo += "try { \n";
                codigo += "ArrayList<Bean" + tabla + "> lista = new ArrayList();\n";
                codigo += "ArrayList param = new ArrayList(); \n";
                codigo += "param.add(OracleTypes.CURSOR); \n";
                codigo += "ArrayList objetos = Dao" + tabla + ".Consultar" + tabla + "(param);";
                codigo += "rs = (ResultSet) objetos.get(0);";
                codigo += "cst = (CallableStatement) objetos.get(1); \n";
                codigo += "if (rs != null && cst != null) { \n";
                codigo += "   while (rs.next()) { \n";
                codigo += "     Bean" + tabla + " obj = new Bean" + tabla + "();\n";
                for (int i = 1; i <= cantFilas; i++) {
                    codigo += "          obj.set" + Campos.get(i).toString() + "(rs.get" + tipoDatos(tipos.get(i).toString()) + "(" + i + "));\n";
                }
                codigo += "  lista.add(obj);\n";
                codigo += "       }\n";
                codigo += "  }\n";
                codigo += "     return lista;\n";
                codigo += " } catch (SQLException ex) {\n";
                codigo += "   return null;\n";
                codigo += "  } finally { \n";
                codigo += "try {\n";
                codigo += "     if (rs != null) {\n";
                codigo += "     rs.close();\n";
                codigo += "                     }\n";
                codigo += "   if (cst != null) {\n";
                codigo += "     cst.close();\n";
                codigo += "                     }\n";
                codigo += "    } catch (Exception ex) {\n";
                codigo += " }\n";
                codigo += " }\n";
                codigo += " }\n\n\n";
//////////***************mantemineto*****************

                codigo += "\n public static void mantenimiento" + tabla + "(HashSet<Bean" + tabla + ">" + tabla + ") throws SQLException { \n";
                codigo += "Iterator i = " + tabla + ".iterator();\n";
                codigo += "Bean" + tabla + " bean;;\n";
                codigo += "int contarEliminados = 0;\n";
                codigo += "int contarInsertados = 0;;\n";
                codigo += "int contarActualizados = 0;\n";
                codigo += "ArrayList param = new ArrayList();\n";
                codigo += "ArrayList objetos;\n";
                codigo += "CallableStatement cst = null;\n";
                codigo += "BigDecimal estado = null;\n";
                codigo += "  while (i.hasNext()) {;\n";
                codigo += "  bean = (Bean" + tabla + ") i.next();\n";
                codigo += "  if (bean.getOperacion().equals(\"e\")) {\n";

                codigo += "  param.add(OracleTypes.NUMBER);\n";
                //param.add(bean.getIdTabla03());
                for (int i = 1; i <= cantFilas; i++) {
                    codigo += "     param.add(bean.get" + Campos.get(i).toString() + "()) ;\n";
                }
                codigo += " param.add(3); \n";
                codigo += "objetos = Dao" + tabla + ".Mantenimiento" + tabla + "(param); \n";
                codigo += " estado = (BigDecimal) objetos.get(0); \n";
                codigo += " cst = (CallableStatement) objetos.get(1); \n";

                codigo += " if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {\n";
                codigo += " contarEliminados = contarEliminados + 1;\n";
                codigo += " param.clear();\n";
                codigo += " }\n";

                codigo += "  } else if (bean.getOperacion().equals(\"a\")) { \n";

                codigo += "  param.add(OracleTypes.NUMBER);\n";
                for (int i = 1; i <= cantFilas; i++) {
                    codigo += "     param.add(bean.get" + Campos.get(i).toString() + "()) ;\n";
                }
                codigo += " param.add(2); \n";
                codigo += "objetos = Dao" + tabla + ".Mantenimiento" + tabla + "(param); \n";
                codigo += " estado = (BigDecimal) objetos.get(0); \n";
                codigo += " cst = (CallableStatement) objetos.get(1); \n";
                codigo += " if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {\n";
                codigo += " contarEliminados = contarEliminados + 1;\n";
                codigo += " param.clear();\n";
                codigo += " };\n";

                codigo += "  } else if (bean.getOperacion().equals(\"a\")) { \n";
                codigo += "  param.add(OracleTypes.NUMBER);\n";
                codigo += "  param.add(OracleTypes.NUMBER);\n";

                for (int i = 2; i <= cantFilas; i++) {
                    codigo += "      param.add(bean.get" + Campos.get(i).toString() + "()) ;\n";
                }
                codigo += " param.add(1); \n";

                codigo += "objetos = Dao" + tabla + ".Mantenimiento" + tabla + "(param); \n";
                codigo += " estado = (BigDecimal) objetos.get(0); \n";
                codigo += " cst = (CallableStatement) objetos.get(1); \n";

                codigo += " if (estado.compareTo(BigDecimal.ONE) == 0) {\n";
                codigo += " contarEliminados = contarEliminados + 1;\n";
                codigo += " param.clear();\n";
                codigo += " }\n";
                codigo += " }\n";
                codigo += " }\n";

                codigo += " JOptionPane.showMessageDialog(null, \"Se han insert\"\n";
                codigo += "   + \"ado: \" + contarInsertados + \" registros \"\n";
                codigo += "     + \"Se han actualizado: \" + contarActualizados + \" registros \" \n ";
                codigo += " + \"Se han eliminado: \" + contarEliminados + \" registros\", \"Informe de Operación\", JOptionPane.INFORMATION_MESSAGE);\n";

                codigo += "         } \n";
                codigo += "  }\n\n";

            }
        } catch (SQLException ex) {
            rs.close();

        }

        return codigo;
    }

    public static String generarTableModel(String tabla) throws SQLException {
        CallableStatement cst;
        ResultSet rs = null;
        String codigo = "";
        String sql = "";
        sql = "select COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE from USER_TAB_COLUMNS where TABLE_NAME='" + tabla + "'";

        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        // ResultSet  rs ;

        try {
            if (!objCnx.getCnx().isClosed()) {
                ResultSet rsx = Consultas.listarSQLSinParametrosSinProcedimiento(sql, objCnx.getCnx());

                Hashtable Campos = new Hashtable();
                Hashtable tipos = new Hashtable();
                int cantP = 0;
                if (rsx != null) {
                    while (rsx.next()) {
                        cantP = cantP + 1;
                        tipos.put(cantP, rsx.getString(2));
                        Campos.put(cantP, rsx.getString(1));

                    }
                }
                int cantFilas = tipos.size();
                codigo = " package com.contab.tablemodel;\n";
                codigo += " import com.contab.bean.Bean" + tabla + ";\n";
                codigo += " import com.contab.util.CellRedenderImage;\n";
                codigo += " import java.util.ArrayList;\n";
                codigo += " import java.util.HashSet;\n";
                codigo += " import java.util.Iterator;\n";
                codigo += " import javax.swing.JTable;\n";
                codigo += " import javax.swing.event.EventListenerList;\n";
                codigo += " import javax.swing.event.TableModelEvent;\n";
                codigo += " import javax.swing.event.TableModelListener;\n";
                codigo += " import javax.swing.table.AbstractTableModel;\n";
                codigo += " \n\n\n";
                codigo += " public class Model" + tabla + " extends AbstractTableModel {\n";
                codigo += " private ArrayList<String> headerTable = new ArrayList();\n";
                codigo += " private ArrayList<Bean" + tabla + "> data = new ArrayList();\n";
                codigo += " private ArrayList<TableModelListener> listeners = new ArrayList();\n";
                codigo += " private JTable tablaDatos;\n";
                codigo += " private HashSet<Bean" + tabla + "> listaCommit = new HashSet();\n";
                codigo += " public Model" + tabla + "(JTable tabla) {\n";
                codigo += " tablaDatos = tabla ;\n";
                codigo += " createHeaders();\n";
                codigo += " }\n";
                codigo += " public Model" + tabla + "(ArrayList<Bean" + tabla + "> lista) {\n";
                codigo += "      data = lista;\n";
                codigo += "      createHeaders();\n";
                codigo += " }  \n\n\n";
                codigo += " private void createHeaders() {\n";
                codigo += "      headerTable.add(\"\");\n";



                for (int i = 2; i <= cantFilas; i++) {
                    codigo += "headerTable.add(\"" + Campos.get(i).toString() + "\") ;\n";
                }
                codigo += "      headerTable.add(\"\");\n";

                codigo += " } \n\n\n";

                codigo += "public void borrar(int fila) {\n";
                codigo += "  try {\n";
                codigo += "      data.remove(fila);\n";
                codigo += "      TableModelEvent evento;\n";
                codigo += "      evento = new TableModelEvent(this, fila, fila, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);\n";
                codigo += "      avisaSuscriptores(evento);\n";
                codigo += "      fireTableDataChanged();\n";
                codigo += "      } catch (Exception ex) {\n";
                codigo += " }\n";
                codigo += " }\n\n\n";
                codigo += "public void insertar(Bean" + tabla + " i) {\n";
                codigo += "      data.add(i);\n";
                codigo += "      TableModelEvent evento;\n";
                codigo += "      evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);\n";
                codigo += "      avisaSuscriptores(evento);\n";
                codigo += "      fireTableDataChanged();\n";
                codigo += " }\n\n\n";
                codigo += " public void update(Bean" + tabla + " i, int fila) {\n";
                codigo += "      data.remove(fila);\n";
                codigo += "      data.add(fila, i);\n";
                codigo += "      TableModelEvent evento;\n";
                codigo += "      evento = new TableModelEvent(this, fila, fila, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE);\n";
                codigo += "      avisaSuscriptores(evento);\n";
                codigo += "      fireTableDataChanged();\n";
                codigo += " }\n\n\n";
                codigo += "@Override\n";
                codigo += "public String getColumnName(int column) {\n";
                codigo += "      return headerTable.get(column);\n";
                codigo += " }\n\n\n";
                codigo += "@Override\n";
                codigo += "public int getRowCount() {\n";
                codigo += "      try {\n";
                codigo += "          return data.size();\n";
                codigo += "      } catch (Exception ex) {\n";
                codigo += "      return 0;\n";
                codigo += " }\n";
                codigo += " }\n\n\n";
                codigo += "@Override\n";
                codigo += "public int getColumnCount() {\n";
                codigo += "      return headerTable.size();\n";
                codigo += " }\n\n";
                codigo += "public Bean" + tabla + " getValue(int rowIndex) {\n";
                codigo += "      try {\n";
                codigo += "          return data.get(rowIndex);\n";
                codigo += "        } catch (Exception ex) {\n";
                codigo += "      return null;\n";
                codigo += " }\n";
                codigo += " }\n";
                codigo += "   @Override \n";
                codigo += "public Object getValueAt(int rowIndex, int columnIndex) {\n";
                codigo += " switch (columnIndex) {\n";
                codigo += "      case 0:\n";
                codigo += "      return data.get(rowIndex).getOperacion();\n";
                for (int i = 1; i < cantFilas; i++) {
                    codigo += "      case " + i + ": \n";

                    codigo += "     return data.get(rowIndex).get" + Campos.get(i + 1).toString() + "() ;\n";
                }
                codigo += "      case 6:\n";
                codigo += "       return data.get(rowIndex).isSelecccion();\n";
                codigo += "      default:\n";
                codigo += "      return null;\n";
                codigo += " }\n";
                codigo += " }\n\n\n";
                codigo += "@Override\n";

                codigo += "public void setValueAt(Object aValue, int rowIndex, int columnIndex) {\n";

                codigo += " String aux;\n";
                codigo += " String valor;\n";
                codigo += " Bean" + tabla + " auxEmp = data.get(rowIndex);\n";
                codigo += " boolean eliminar = false;\n";
                codigo += "  switch (columnIndex) {\n";
                codigo += "      case 0:\n";
                codigo += "          break;\n";
                int tamanofinal = cantFilas;
                for (int i = 1; i < cantFilas; i++) {

                    codigo += "      case " + i + ":\n";
                    codigo += "          valor = String.valueOf(aValue);\n";
                    codigo += "      if (!data.get(rowIndex).getOperacion().equals(\"i\")) {\n";
                    codigo += "              aux = String.valueOf(data.get(rowIndex).get" + Campos.get(i).toString() + "());\n";
                    codigo += "              data.get(rowIndex).set" + Campos.get(i).toString() + "(valor);\n";
                    codigo += "      if (aux.equals(valor)) {\n";
                    codigo += "          data.get(rowIndex).setOperacion(\"n\");\n";
                    codigo += "         } else {\n";
                    codigo += "          data.get(rowIndex).setOperacion(\"a\");\n";
                    codigo += "          }\n";
                    codigo += "      } else {\n";
                    codigo += "          data.get(rowIndex).set" + Campos.get(i).toString() + "(valor);\n";
                    codigo += "          }\n";
                    codigo += "      break;\n";
                }


                codigo += "      case " + cantFilas + " :\n";
                codigo += "      if (!data.get(rowIndex).getOperacion().equals(\"i\")) {\n";
                codigo += "          data.get(rowIndex).setSelecccion((Boolean) aValue);\n";
                codigo += "          if(data.get(rowIndex).isSelecccion()) {\n";
                codigo += "              data.get(rowIndex).setOperacion(\"e\");\n";
                codigo += "          } else {\n";
                codigo += "          data.get(rowIndex).setOperacion(\"n\");\n";
                codigo += "          }\n";
                codigo += "        } else {\n";
                codigo += "          data.remove(rowIndex);\n";
                codigo += "          listaCommit.remove(auxEmp);\n";
                codigo += "          eliminar = true;\n";
                codigo += "          }\n";
                codigo += "          break;\n";
                codigo += "          default:\n";
                codigo += "          break;\n";
                codigo += "          }\n";
                codigo += " try {\n";
                codigo += "      if (!eliminar) {\n";
                codigo += "          if (data.get(rowIndex).getOperacion().equals(\"n\")) {\n";
                codigo += "              listaCommit.remove(auxEmp);\n";
                codigo += "          } else {\n";
                codigo += "          listaCommit.remove(auxEmp);\n";
                codigo += "          listaCommit.add(data.get(rowIndex));\n";
                codigo += "          }\n";
                codigo += "          Iterator iterar = listaCommit.iterator();\n";
                codigo += "      while (iterar.hasNext()) {\n";
                codigo += "          Bean" + tabla + " bean = (Bean" + tabla + ") iterar.next();\n";
                codigo += "               }\n";
                codigo += "           }\n";
                codigo += " } catch (Exception ex) {\n";
                 codigo += " }\n\n\n";
                codigo += "TableModelEvent evento;\n";
                codigo += "evento = new TableModelEvent(this, rowIndex, rowIndex, TableModelEvent.ALL_COLUMNS, TableModelEvent.ALL_COLUMNS);\n";
                codigo += "avisaSuscriptores(evento);\n";
                codigo += "fireTableDataChanged();\n";
                codigo += " }\n\n\n";
                 
                codigo += "private void avisaSuscriptores(TableModelEvent evento) {\n";
                codigo += "      Iterator i = listeners.iterator();\n";
                codigo += "      while (i.hasNext()) {\n";
                codigo += "          ((TableModelListener) i.next()).tableChanged(evento);\n";
                codigo += " }\n";
                codigo += " }\n";
                codigo += "@Override\n";
                codigo += "public int findColumn(String columnName) {\n";
                codigo += "      for (int i = 0; i < headerTable.size(); i++) {\n";
                codigo += "              if(columnName.equals(headerTable.get(i))) {\n";
                codigo += "      return i;\n";
                codigo += " }\n";
                codigo += " }\n";
                codigo += " return -1;\n";
                codigo += "}\n\n\n";
                codigo += "@Override\n";
             
                codigo += "public Class<?> getColumnClass(int columnIndex) {\n";
                codigo += "      switch(columnIndex) {\n";
                for (int i = 0; i < cantFilas; i++) {
                    codigo += "     case " + i + ":\n";
                    codigo += "          return String.class;\n";
                }

                codigo += " case " + cantFilas + ":\n";
                codigo += "       return Boolean.class;\n";
                codigo += "      default:\n";
                codigo += "         return String.class; \n";
                codigo += " }\n";
                codigo += " }\n\n\n";
                codigo += " @Override\n";
                codigo += " public boolean isCellEditable(int rowIndex, int columnIndex) {\n";
                codigo += "      if (data.get(rowIndex).getOperacion().equals(\"e\")) {\n";
                codigo += "          switch(columnIndex) {\n";
                for (int i = 0; i <= cantFilas; i++) {
                    if(i==cantFilas){
                    codigo += "          case " + i + ":\n";
                    codigo += "          return true;\n";
                    }else{
                     codigo += "          case " + i + ":\n";
                    codigo += "          return false;\n";
                    }

                }
                codigo += "          default:\n";
                codigo += "          return false;\n";
                codigo += " }\n";
                codigo += " } else {\n";
                codigo += "      switch (columnIndex) {\n";
                for (int i = 0; i <= cantFilas; i++) {
                    codigo += "          case " + i + ":\n";
                    codigo += "          return true;\n";

                }
                codigo += "          default:\n";
                codigo += "          return false;\n";

                codigo += "          }\n";
                codigo += "      }\n";
                codigo += " }\n\n\n";
                codigo += " @Override\n";
                codigo += " public void addTableModelListener(TableModelListener l) {\n";
                codigo += "          listenerList.add(TableModelListener.class, l);\n";
                codigo += " }\n";
                codigo += " @Override\n";
                codigo += " public void removeTableModelListener(TableModelListener l) {\n";
                codigo += "          listenerList.remove(TableModelListener.class, l);\n";
                codigo += " }\n";
                codigo += " public ArrayList<Bean"+tabla+"> getData() {\n";
                codigo += "      return data;\n";
                codigo += " }\n";
                codigo += " public void setData(ArrayList<Bean"+tabla+"> data) {\n";
                codigo += "      this.data = data;\n";
                codigo += "      tablaDatos.setModel(this);\n";
                codigo += "      tablaDatos.getColumnModel().getColumn(0).setMaxWidth(20);\n";
                codigo += "      tablaDatos.getColumnModel().getColumn(0).setCellRenderer(new CellRedenderImage());\n";
                codigo += " }\n";
                codigo += "public EventListenerList getListenerList() {\n";
                codigo += "      return listenerList;\n";
                codigo += " }\n";
                codigo += "public void setListenerList(EventListenerList listenerList) {\n";
                codigo += "      this.listenerList = listenerList;\n";
                codigo += " }\n";
                codigo += " public HashSet<Bean"+tabla+"> getListaCommit() {\n";
                codigo += "      return listaCommit;\n";
                codigo += "  }\n";
                codigo += " }\n";





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
        sql = "select COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE from USER_TAB_COLUMNS where TABLE_NAME='" + tabla + "'";

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
                codigo += ")  \n";
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
                codigo += "  \n";
                codigo += "  \n";
                codigo += "  \n";
                codigo += "  CREATE OR REPLACE FUNCTION CONSULTA" + tabla + " RETURN types.ref_cursor \n";
                codigo += "   IS lista types.ref_cursor; \n";
                codigo += " BEGIN \n";
                codigo += "  OPEN lista FOR SELECT * FROM " + tabla + "; \n";
                codigo += "   RETURN(lista); \n";
                codigo += "   END CONSULTA" + tabla + "; \n";
            }
        } catch (SQLException ex) {
            rs.close();

        }

        return codigo;
    }

    public static String listarCamposVista(String tabla) throws SQLException {
        CallableStatement cst;
        ResultSet rs = null;
        String codigo = "";
        String sql = "";
        sql = "select COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE from USER_TAB_COLUMNS where  TABLE_NAME='" + tabla + "'";

        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        // ResultSet  rs ;

        try {
            if (!objCnx.getCnx().isClosed()) {
                ResultSet rsx = Consultas.listarSQLSinParametrosSinProcedimiento(sql, objCnx.getCnx());


                int cantP = 0;
                String parm = "";
                if (rsx != null) {
                    while (rsx.next()) {
                        cantP = cantP + 1;
                        parm += "?,";
                    }
                }
                parm = parm + "?";
                /////////*******  consultar ***********////////////////
                codigo += "package com.contab.view;  \n";

                codigo += "public class NewJDialog extends javax.swing.JDialog {  \n";
                codigo += "public NewJDialog(java.awt.Frame parent, boolean modal) {  \n";
                codigo += "super(parent, modal);  \n";
                codigo += "initComponents(); \n";
                codigo += "} \n";


                codigo += "@SuppressWarnings(\"unchecked\") \n";
                codigo += " private void initComponents() { \n";
                codigo += " setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); \n";
                codigo += " javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane()); \n";
                codigo += " getContentPane().setLayout(layout);  \n";
                codigo += " layout.setHorizontalGroup(  \n";
                codigo += "    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)  \n";
                codigo += "  .addGap(0, 400, Short.MAX_VALUE)  \n";
                codigo += " ); \n";
                codigo += " layout.setVerticalGroup( \n";
                codigo += "   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING) \n";
                codigo += "   .addGap(0, 300, Short.MAX_VALUE) \n";
                codigo += "); \n";
                codigo += "  pack(); \n";
                codigo += "} \n";
                codigo += "public static void main(String args[]) {  \n";
                codigo += " try {  \n";
                codigo += "  for (javax.swing.UIManager.LookAndFeelInfo info :     \n";
                codigo += "javax.swing.UIManager.getInstalledLookAndFeels()) { \n";
                codigo += "if (\"Nimbus\".equals(info.getName())) { \n";
                codigo += "javax.swing.UIManager.setLookAndFeel(info.getClassName()); \n";
                codigo += " break; \n";
                codigo += " } \n";
                codigo += " } \n";
                codigo += "} catch (ClassNotFoundException ex) { \n";
                codigo += "java.util.logging.Logger.getLogger(NewJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); \n";
                codigo += " } catch (InstantiationException ex) { ";
                codigo += "java.util.logging.Logger.getLogger(NewJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);  \n";
                codigo += " } catch (IllegalAccessException ex) {  \n";
                codigo += "java.util.logging.Logger.getLogger(NewJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);  \n";

                codigo += " } catch (javax.swing.UnsupportedLookAndFeelException ex) {  \n";
                codigo += "java.util.logging.Logger.getLogger(NewJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);  \n";
                codigo += "  }  \n";

                codigo += " java.awt.EventQueue.invokeLater(new Runnable() {  \n";

                codigo += "public void run() {  \n";
                codigo += "   NewJDialog dialog = new NewJDialog(new javax.swing.JFrame(), true);  \n";
                codigo += "   dialog.addWindowListener(new java.awt.event.WindowAdapter() {  \n";

                codigo += "    @Override  \n";
                codigo += "  public void windowClosing(java.awt.event.WindowEvent e) {  \n";
                codigo += "     System.exit(0);  \n";
                codigo += "  }  \n";
                codigo += " });  \n";
                codigo += " dialog.setVisible(true);  \n";
                codigo += " }  \n";
                codigo += " });  \n";
                codigo += "}  \n";

                codigo += "}   \n";



            }
        } catch (SQLException ex) {
            rs.close();

        }

        return codigo;
    }

    public static ArrayList<BeanTablas> listaDeTablas() throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        String sql = "select TABLE_NAME from user_tables";
        try {
            ArrayList<BeanTablas> lista = new ArrayList();
            ArrayList param = new ArrayList();

            //   ArrayList objetos = DaoEmpresas.consultaEmpresas(param);
            rs = Consultas.listarSQLSinParametrosSinProcedimiento(sql, objCnx.getCnx());

            if (rs != null) {
                while (rs.next()) {
                    BeanTablas objEmpresa = new BeanTablas();
                    objEmpresa.setTabla(rs.getString(1));

                    lista.add(objEmpresa);
                }
                rs.close();


            }
            return lista;
        } catch (SQLException ex) {
            rs.close();


            return null;
        } finally {
            rs.close();


        }

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
