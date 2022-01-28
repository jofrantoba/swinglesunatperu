/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.logic;

import com.contab.bean.BeanDaot;
import com.contab.bean.BeanDocumento;
import com.contab.bean.BeanNombre;
import com.contab.dao.DaoDaot;
import com.contab.dao.DaoEmpresas;
import com.contab.datasource.DataSourceDaot;
import com.contab.datasource.DataSourceRptSunat;
import com.contab.view.ViewApplication;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Administrador
 */
public class LogicDaot {

    public static void generandoDaot(String codemp, String ruc, String periodo, java.sql.Date ini, java.sql.Date fin, BigDecimal uit, DataSourceDaot ds, String opcion) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {

            ResultSet resulCliente = ListarClienteDNI();
            ArrayList<ArrayList<BeanDaot>> repositorioCliente = new ArrayList<ArrayList<BeanDaot>>();
            repositorioCliente = null;
            // repositorioTabla6 = null;
            if (resulCliente != null) {
                repositorioCliente = repositorio(resulCliente);
            }


            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(codemp);
            param.add(ini);
            param.add(fin);
            param.add(uit);
            ArrayList objetos = new ArrayList();
            if (opcion.equals("detalle")) {
                objetos = DaoDaot.consultarCXC(param);
            } else if (opcion.equals("resumen")) {
                objetos = DaoDaot.consultarCXCResumen(param);
            }
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("CARGANDO ...");
                int contador = 0;
                int auxruc = 6;
                BeanDocumento b;
                BeanNombre nom = new BeanNombre();
                BeanDaot tabla = new BeanDaot();
                String Documento = "";
                String valor = "";
                while (rs.next()) {
                    contador = contador + 1;
                    BeanDaot obj = new BeanDaot();
                    Documento = rs.getString(2);
                    System.out.println(Documento);

                    tabla = buscarTablaCliente(repositorioCliente, Documento.trim());
                    obj.setContador(contador);
                    obj.setNumEmp(auxruc);
                    obj.setNumDocEmp(ruc);
                    obj.setPeriodo(periodo);
                    b = validarDocumento(tabla.getNumDocEmp().trim());
                    obj.setTipoPersona(b.getTipoPersona());
                    obj.setTipoDocPersona(b.getTipoDocumento());
                    // if (b.getTipoDocumento()==1) {

                    obj.setNumDocPersona(tabla.getNumDocEmp());
                    // } else {
                    // obj.setNumDocPersona(b.getDocumento());
                    //  }


                    if (b.getTipoPersona().equals("01")) {
                        nom = nombreCliente(rs.getString(3));
                        obj.setApPaterno(nom.getAppaterno());
                        obj.setApMaterno(nom.getApmaterno());
                        obj.setNombre1(nom.getNombre1());
                        obj.setNombre2(nom.getNombre2());

                        valor = "";
                    } else if (b.getTipoPersona().equals("02")) {
                        nom.setAppaterno("");
                        nom.setApmaterno("");
                        nom.setNombre1("");
                        nom.setNombre2("");
                        obj.setRazonSocial(rs.getString(3));
                        valor = rs.getString(3);
                    }
                    BigDecimal val = rs.getBigDecimal(5);

                    obj.setImporte(val.setScale(0, RoundingMode.UP));
                    obj.setFormula(contador + "|" + auxruc + "|" + ruc + "|" + periodo + "|" + b.getTipoPersona() + "|" + b.getTipoDocumento() + "|" + b.getDocumento() + "|" + val.setScale(0, RoundingMode.UP) + "|" + nom.getAppaterno() + "|" + nom.getApmaterno() + "|" + nom.getNombre1() + "|" + nom.getNombre2() + "|" + valor + "|");
                    ds.add(obj);
                }
            }
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }
    }

    public static void generandoCxpDaot(String codemp, String ruc, String periodo, java.sql.Date ini, java.sql.Date fin, BigDecimal uit, DataSourceDaot ds, String opcion) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(codemp);
            param.add(ini);
            param.add(fin);
            param.add(uit);
            ArrayList objetos = new ArrayList();
            if (opcion.equals("detalle")) {
                objetos = DaoDaot.consultarCXP(param);
            } else if (opcion.equals("resumen")) {
                objetos = DaoDaot.consultarCXCPesumen(param);
            }
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("CARGANDO ...");
                int contador = 0;
                int auxruc = 6;
                BeanDocumento b;
                BeanNombre nom = new BeanNombre();
                String valor = "";
                while (rs.next()) {
                    contador = contador + 1;
                    BeanDaot obj = new BeanDaot();
                    obj.setContador(contador);
                    obj.setNumEmp(auxruc);
                    obj.setNumDocEmp(ruc);
                    obj.setPeriodo(periodo);
                    b = validarDocumento(rs.getString(2));
                    obj.setTipoPersona(b.getTipoPersona());
                    obj.setTipoDocPersona(b.getTipoDocumento());
                    obj.setNumDocPersona(b.getDocumento());
                    if (b.getTipoPersona().equals("01")) {
                        nom = nombreCliente(rs.getString(3));
                        obj.setApPaterno(nom.getAppaterno());
                        obj.setApMaterno(nom.getApmaterno());
                        obj.setNombre1(nom.getNombre1());
                        obj.setNombre2(nom.getNombre2());
                        valor = "";
                    } else if (b.getTipoPersona().equals("02")) {
                        obj.setRazonSocial(rs.getString(3));
                        nom.setAppaterno("|");
                        nom.setApmaterno("|");
                        nom.setNombre1("|");
                        nom.setNombre2("|");
                        valor = rs.getString(3);
                    }


                    BigDecimal val = rs.getBigDecimal(5);
                    obj.setImporte(val.setScale(0, RoundingMode.UP));
                    obj.setFormula(contador + "|" + auxruc + "|" + ruc + "|" + periodo + "|" + b.getTipoPersona() + "|" + b.getTipoDocumento() + "|" + b.getDocumento() + "|" + val.setScale(0, RoundingMode.UP) + "|" + nom.getAppaterno() + "|" + nom.getApmaterno() + "|" + nom.getNombre1() + "|" + nom.getNombre2() + "|" + valor + "|");
                    ds.add(obj);
                }
            }
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }
    }

    public static void generandoDaotSunat(String codemp, String ruc, String periodo, java.sql.Date ini, java.sql.Date fin, BigDecimal uit, DataSourceRptSunat ds) throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(codemp);
            param.add(ini);
            param.add(fin);
            param.add(uit);
            ArrayList objetos = DaoDaot.consultarCXCSunat(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("CARGANDO ...");
                int contador = 0;
                int auxruc = 6;
                BeanDocumento b;
                BeanNombre nom = new BeanNombre();
                String valor = "";
                while (rs.next()) {
                    contador = contador + 1;
                    BeanDaot obj = new BeanDaot();
                    obj.setContador(contador);
                    obj.setNumEmp(auxruc);
                    obj.setNumDocEmp(ruc);
                    obj.setPeriodo(periodo);
                    b = validarDocumento(rs.getString(2));
                    obj.setTipoPersona(b.getTipoPersona());
                    obj.setTipoDocPersona(b.getTipoDocumento());
                    obj.setNumDocPersona(b.getDocumento());
                    if (b.getTipoPersona().equals("01")) {
                        nom = nombreCliente(rs.getString(3));
                        obj.setApPaterno(nom.getAppaterno());
                        obj.setApMaterno(nom.getApmaterno());
                        obj.setNombre1(nom.getNombre1());
                        obj.setNombre2(nom.getNombre2());
                    } else if (b.getTipoPersona().equals("02")) {
                        obj.setRazonSocial(rs.getString(3));
                    }
                    obj.setDireccion(rs.getString(4));
                    BigDecimal val = rs.getBigDecimal(5).add(rs.getBigDecimal(6));
                    obj.setImporte(val.setScale(2, BigDecimal.ROUND_HALF_UP));
                    obj.setOperaciones(rs.getString(7));
                    obj.setDepartamento(rs.getString(8));
                    obj.setProvincia(rs.getString(9));
                    obj.setDistrito(rs.getString(10));
                    ds.add(obj);
                }
            }
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        } finally {
            rs.close();
            cst.close();
            DaoEmpresas.objCnx.destroy();
        }
    }

    private static BeanDocumento validarDocumento(String doc) {
        BeanDocumento obj = new BeanDocumento();
        if (doc.length() == 8) {
            obj.setTipoDocumento(1);
            obj.setTipoPersona("01");
            try {
                obj.setDocumento(doc);
            } catch (Exception ex) {
                obj.setDocumento(doc);
            }
        } else if (doc.length() == 11) {
            try {
                int ruc = Integer.parseInt(doc.substring(0, 2));
                String person = doc.substring(0, 2);
                person = person.trim();
                if (person.equals("10")) {
                    obj.setTipoDocumento(6);
                    obj.setTipoPersona("01");
                    obj.setDocumento(doc);
                } else if (person.equals("20")) {
                    obj.setTipoDocumento(6);
                    obj.setTipoPersona("02");
                    obj.setDocumento(doc);
                } else {
                    obj.setTipoDocumento(6);
                    obj.setTipoPersona("00");
                    obj.setDocumento(doc);
                }
            } catch (Exception ex) {
                String dni = doc.substring(3, 10);
                obj.setTipoPersona("01");
                obj.setTipoDocumento(1);
                obj.setDocumento(dni);
            }
        } else {
            obj.setTipoDocumento(0);
            obj.setDocumento("0");
            obj.setTipoPersona("00");
        }
        return obj;
    }

    public static BeanNombre nombreCliente(String cliente) {
        BeanNombre nom = new BeanNombre();
        try {
            int inicio = 0;
            int fin = cliente.indexOf(" ", inicio);
            String paterno;
            String materno;
            String primer;
            String segundo;
            paterno = cliente.substring(inicio, fin);
            nom.setAppaterno(paterno);
            inicio = fin + 1;
            fin = cliente.indexOf(" ", inicio);
            if (paterno.equalsIgnoreCase("del") || paterno.equalsIgnoreCase("de")) {
                paterno = paterno + " " + cliente.substring(inicio, fin);
                nom.setAppaterno(paterno);
                inicio = fin + 1;
                fin = cliente.indexOf(" ", inicio);
                if (paterno.equalsIgnoreCase("del la")
                        || paterno.equalsIgnoreCase("del los")
                        || paterno.equalsIgnoreCase("de la")
                        || paterno.equalsIgnoreCase("de los")) {
                    paterno = paterno + " " + cliente.substring(inicio, fin);
                    nom.setAppaterno(paterno);
                    inicio = fin + 1;
                    fin = cliente.indexOf(" ", inicio);
                }
            }
            if (fin == -1) {
                materno = cliente.substring(inicio);
                nom.setNombre1(materno);
                return nom;
            } else {
                materno = cliente.substring(inicio, fin);
                nom.setApmaterno(materno);
                inicio = fin + 1;
                fin = cliente.indexOf(" ", inicio);
            }
            if (materno.equalsIgnoreCase("del") || materno.equalsIgnoreCase("de")) {
                materno = materno + " " + cliente.substring(inicio, fin);
                nom.setApmaterno(materno);
                inicio = fin + 1;
                fin = cliente.indexOf(" ", inicio);
                if (materno.equalsIgnoreCase("del la") || materno.equalsIgnoreCase("del los")
                        || materno.equalsIgnoreCase("de la") || materno.equalsIgnoreCase("de los")) {
                    materno = materno + " " + cliente.substring(inicio, fin);
                    nom.setApmaterno(materno);
                    inicio = fin + 1;
                    fin = cliente.indexOf(" ", inicio);
                }
            }
            if (fin == -1) {
                primer = cliente.substring(inicio);
                nom.setNombre1(primer);
                inicio = -1;
                return nom;
            } else {
                primer = cliente.substring(inicio, fin);
                nom.setNombre1(primer);
                inicio = fin + 1;
            }
            //fin=cliente.indexOf(" ", inicio);
            segundo = cliente.substring(inicio);
            nom.setNombre2(segundo);
        } catch (Exception ex) {
        }
        return nom;
    }

    public static ResultSet ListarClienteDNI() throws SQLException {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoDaot.ListaCliente(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                return rs;
            }
            cst.close();
            // DaoEmpresas.objCnx.destroy();
        } catch (SQLException ex) {
        } finally {
            //  cst.close();
            DaoEmpresas.objCnx.destroy();
        }
        return rs;
    }

    public static ArrayList<ArrayList<BeanDaot>> repositorio(ResultSet rs) {
        ArrayList<ArrayList<BeanDaot>> repositorio = new ArrayList<ArrayList<BeanDaot>>();
        int val = 30000;
        ArrayList<BeanDaot> array;
        int contador = 0;
        int cont = 2;
        try {
            if (rs != null) {
                //   System.out.println("repositorio sunat tabla 6");
                array = new ArrayList<BeanDaot>();
                while (rs.next()) {
                    contador += 1;
                    if (val == contador) {
                        repositorio.add(array);
                        System.out.println("repositorio cliente " + cont);
                        array = new ArrayList<BeanDaot>();
                        contador = 0;
                        cont = cont + 1;
                    }
                    BeanDaot bean = new BeanDaot();
                    bean.setNumDocPersona(rs.getString(1));
                    bean.setNumDocEmp(rs.getString(2));

                    array.add(bean);
                }
                repositorio.add(array);
                rs.close();
                DaoEmpresas.objCnx.destroy();
            }
        } catch (Exception ex) {
        }
        return repositorio;
    }

    public static BeanDaot buscarTablaCliente(ArrayList<ArrayList<BeanDaot>> repor, String codigo) {
        BeanDaot bean = new BeanDaot();
        BeanDaot beanaux = new BeanDaot();
        ArrayList<BeanDaot> array;
        for (int i = 0; i < repor.size(); i++) {
            array = (ArrayList<BeanDaot>) repor.get(i);
            Iterator iterar = array.iterator();
            while (iterar.hasNext()) {
                beanaux = (BeanDaot) iterar.next();
                if (beanaux.getNumDocPersona().equals(codigo.trim())) {

                    bean = beanaux;
                    break;
                }
            }
        }
        return bean;
    }
}
