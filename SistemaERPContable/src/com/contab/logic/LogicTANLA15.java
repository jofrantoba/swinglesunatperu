package com.contab.Logic;

import com.contab.bean.BeanTANLA15;
import com.contab.dao.DaoTANLA15;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import java.util.HashSet;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

public class LogicTANLA15 {

    public static ArrayList<BeanTANLA15> ListarTANLA15() {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList<BeanTANLA15> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoTANLA15.ConsultarTANLA15(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanTANLA15 obj = new BeanTANLA15();
                    obj.setIDTABLA12(rs.getBigDecimal(1));
                    obj.setCODMOV(rs.getString(2));
                    obj.setLLAVE(rs.getString(3));
                    obj.setDESCRIPCION(rs.getString(4));
                    obj.setCODIGOSUNAT(rs.getString(5));
                    lista.add(obj);
                }
            }
            return lista;
        } catch (SQLException ex) {
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cst != null) {
                    cst.close();
                }
            } catch (Exception ex) {
            }
        }
    }

    public static void mantenimientoTANLA15(HashSet<BeanTANLA15> TANLA15) throws SQLException {
        Iterator i = TANLA15.iterator();
        BeanTANLA15 bean;;
        int contarEliminados = 0;
        int contarInsertados = 0;;
        int contarActualizados = 0;
        ArrayList param = new ArrayList();
        ArrayList objetos;
        CallableStatement cst = null;
        BigDecimal estado = null;
        while (i.hasNext()) {;
            bean = (BeanTANLA15) i.next();
            if (bean.getOperacion().equals("e")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIDTABLA12());
                param.add(bean.getCODMOV());
                param.add(bean.getLLAVE());
                param.add(bean.getDESCRIPCION());
                param.add(bean.getCODIGOSUNAT());
                param.add(3);
                objetos = DaoTANLA15.MantenimientoTANLA15(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                    contarEliminados = contarEliminados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("a")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIDTABLA12());
                param.add(bean.getCODMOV());
                param.add(bean.getLLAVE());
                param.add(bean.getDESCRIPCION());
                param.add(bean.getCODIGOSUNAT());
                param.add(2);
                objetos = DaoTANLA15.MantenimientoTANLA15(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                    contarEliminados = contarEliminados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("a")) {
                param.add(OracleTypes.NUMBER);
                param.add(OracleTypes.NUMBER);
                param.add(bean.getCODMOV());
                param.add(bean.getLLAVE());
                param.add(bean.getDESCRIPCION());
                param.add(bean.getCODIGOSUNAT());
                param.add(1);
                objetos = DaoTANLA15.MantenimientoTANLA15(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.ONE) == 0) {
                    contarEliminados = contarEliminados + 1;
                    param.clear();
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Se han insert"
                + "ado: " + contarInsertados + " registros "
                + "Se han actualizado: " + contarActualizados + " registros "
                + "Se han eliminado: " + contarEliminados + " registros", "Informe de Operaci�n", JOptionPane.INFORMATION_MESSAGE);
    }
}
