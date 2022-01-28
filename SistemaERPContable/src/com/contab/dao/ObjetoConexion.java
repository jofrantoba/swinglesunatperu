package com.contab.dao;

import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author JONATHAN
 */
public class ObjetoConexion {
    private Conexion sql;
    private Conexion mysql;
    private  Conexion oracle;
    private  Conexion bdTumi;
    private  Conexion bdAudit;

    public Connection conectarBDTumi() {
        try {
            bdTumi = new Conexion("127.0.0.1","5432","tumi","postgres","postgres","POSTGRES");
            boolean estado=bdTumi.conectarBD();
            if(estado){
            System.out.println("CONECTADO CORRECTAMENTE");
            return bdTumi.getCnx();
            }else{
            System.out.println("ERROR AL CONECTAR");
                return null;
            }
        } catch (Exception ex) {
            System.out.println("ERROR AL CONECTAR: "+ex.getCause());
            return null;
        }
    }
    
    public Connection conectarBDAudit() {
        try {
            bdAudit = new Conexion("127.0.0.1","5432","audit","postgres","postgres","POSTGRES");
            boolean estado=bdAudit.conectarBD();
            if(estado){
            System.out.println("CONECTADO CORRECTAMENTE");
            return bdAudit.getCnx();
            }else{
            System.out.println("ERROR AL CONECTAR");
                return null;
            }
        } catch (Exception ex) {
            System.out.println("ERROR AL CONECTAR: "+ex.getCause());
            return null;
        }
    }

    public  Connection conectarSQL() {
        try {
            sql = new Conexion("127.0.0.1","1433","Softmol","EbusiAdmin","filosofo","SQL");
            boolean estado=sql.conectarBD();
            if(estado){
            System.out.println("CONECTADO CORRECTAMENTE");
            return sql.getCnx();
            }else{
            System.out.println("ERROR AL CONECTAR");
                return null;
            }
        } catch (Exception ex) {
            System.out.println("ERROR AL CONECTAR: "+ex.getCause());
            return null;
        }
    }
    public Connection conectarMYSQL() {
        try {
            mysql = new Conexion("127.0.0.1", "3306", "tramite", "root", "root", "MYSQL");
            boolean estado=mysql.conectarBD();
            if(estado){
            System.out.println("CONECTADO CORRECTAMENTE");
            return mysql.getCnx();
            }else{
            System.out.println("ERROR AL CONECTAR");
                return null;
            }
        } catch (Exception ex) {
            System.out.println("ERROR AL CONECTAR: "+ex.getCause());
            return null;
        }
    }
     public Connection conectarORACLEN() {
        try {
            mysql = new Conexion("10.10.2.77", "1521", "LHST", "XRAYADMIN", "*719_3#5*/", "ORACLE");
            boolean estado=mysql.conectarBD();
            if(estado){
            System.out.println("CONECTADO CORRECTAMENTE");
            return mysql.getCnx();
            }else{
            System.out.println("ERROR AL CONECTAR");
                return null;
            }
        } catch (Exception ex) {
            System.out.println("ERROR AL CONECTAR: "+ex.getCause());
            return null;
        }
    }
    public  Conexion conectarORACLE() {
        try {
            oracle = new Conexion("10.10.2.77", "1521", "LHST", "XRAYADMIN", "*719_3#5*/", "ORACLE");
            boolean estado=oracle.conectarBD();
            if(estado){
           // System.out.println("CONECTADO CORRECTAMENTE");
            return oracle;            
            }else{
            System.out.println("ERROR AL CONECTAR");
                return null;
            }
        } catch (Exception ex) {
            System.out.println("ERROR AL CONECTAR t: "+ex.getCause());
          //  ViewApplication.mensajeEstado.setText("Error al conectar a la base de datos");
                JOptionPane.showMessageDialog(null, "Error en la conexion ", "alert", JOptionPane.OK_CANCEL_OPTION );
            return null;
        }
    }

    public Conexion getBdAudit() {
        return bdAudit;
    }

    public Conexion getBdTumi() {
        return bdTumi;
    }

    public Conexion getMysql() {
        return mysql;
    }

    public Conexion getOracle() {
        return oracle;
    }

    public Conexion getSql() {
        return sql;
    }

    
}
