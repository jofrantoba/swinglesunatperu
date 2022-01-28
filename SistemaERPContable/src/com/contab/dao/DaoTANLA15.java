package com.contab.dao; 
import java.sql.SQLException; 
import java.util.ArrayList;
import org.openide.util.Exceptions;

public class DaoTANLA15{

 public static ArrayList ConsultarTANLA15(ArrayList param)  {
ObjetoConexion cnxOra   =new ObjetoConexion(); 
Conexion objCnx=cnxOra.conectarORACLE(); 
ArrayList objetos = new ArrayList();
 try { 
if(!objCnx.getCnx().isClosed()){ 
String proc = "{call ?:=CONSULTATANLA15()}"; 
 objetos = Consultas.funcion(proc, param, objCnx.getCnx()); 
objetos.add(objCnx); 
   }
   } catch (SQLException ex) {
 Exceptions.printStackTrace(ex); 
   }
     return null;
   }


 public static ArrayList MantenimientoTANLA15(ArrayList param)  {
ObjetoConexion cnxOra   =new ObjetoConexion(); 
Conexion objCnx=cnxOra.conectarORACLE(); 
ArrayList objetos = new ArrayList();
 try { 
if(!objCnx.getCnx().isClosed()){ 
String proc = "{call ?:=MANTANTANLA15(?,?,?,?,?,?)}"; 
 objetos = Consultas.funcion(proc, param, objCnx.getCnx()); 
objetos.add(objCnx); 
   }
   } catch (SQLException ex) {
 Exceptions.printStackTrace(ex); 
   }
     return null;
   }


}