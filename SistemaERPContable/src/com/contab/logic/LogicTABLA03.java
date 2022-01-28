package com.contab.Logic;

import com.contab.bean.BeanTABLA03;
import com.contab.dao.DaoTABLA03;
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

public class LogicTABLA03 {

    public static ArrayList<BeanTABLA03> ListarTABLA03() {
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            ArrayList<BeanTABLA03> lista = new ArrayList();
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoTABLA03.ConsultarTABLA03(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (rs != null && cst != null) {
                while (rs.next()) {
                    BeanTABLA03 obj = new BeanTABLA03();
                    obj.setIDTABLA03(rs.getString(1));
                    obj.setCOMPANIA(rs.getString(2));
                    obj.setBANCO(rs.getString(3));
                    obj.setNUMEROCUENTA(rs.getString(4));
                    obj.setCODSUNAT(rs.getString(5));
                    obj.setTIPO(rs.getString(6));
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

    public static void mantenimientoTABLA03(HashSet<BeanTABLA03> TABLA03) throws SQLException {
        Iterator i = TABLA03.iterator();
        BeanTABLA03 bean;;
        int contarEliminados = 0;
        int contarInsertados = 0;;
        int contarActualizados = 0;
        ArrayList param = new ArrayList();
        ArrayList objetos;
        CallableStatement cst = null;
        BigDecimal estado = null;
        while (i.hasNext()) {;
            bean = (BeanTABLA03) i.next();
            if (bean.getOperacion().equals("e")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIDTABLA03());
                param.add(bean.getCOMPANIA());
                param.add(bean.getBANCO());
                param.add(bean.getNUMEROCUENTA());
                param.add(bean.getCODSUNAT());
                param.add(bean.getTIPO());
                param.add(3);
                objetos = DaoTABLA03.MantenimientoTABLA03(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                    contarEliminados = contarEliminados + 1;
                    param.clear();
                }
            } else if (bean.getOperacion().equals("a")) {
                param.add(OracleTypes.NUMBER);
                param.add(bean.getIDTABLA03());
                param.add(bean.getCOMPANIA());
                param.add(bean.getBANCO());
                param.add(bean.getNUMEROCUENTA());
                param.add(bean.getCODSUNAT());
                param.add(bean.getTIPO());
                param.add(2);
                objetos = DaoTABLA03.MantenimientoTABLA03(param);
                estado = (BigDecimal) objetos.get(0);
                cst = (CallableStatement) objetos.get(1);
                if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                    contarEliminados = contarEliminados + 1;
                    param.clear();
                };
            } else if (bean.getOperacion().equals("a")) {
                param.add(OracleTypes.NUMBER);
                param.add(OracleTypes.NUMBER);
                param.add(bean.getCOMPANIA());
                param.add(bean.getBANCO());
                param.add(bean.getNUMEROCUENTA());
                param.add(bean.getCODSUNAT());
                param.add(bean.getTIPO());
                param.add(1);
                objetos = DaoTABLA03.MantenimientoTABLA03(param);
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
