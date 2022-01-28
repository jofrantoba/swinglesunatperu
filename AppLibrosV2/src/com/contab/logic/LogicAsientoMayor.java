package com.contab.logic;

import com.contab.bean.BeanAsientoMayor;
import com.contab.dao.DaoAsientoMayor;
import com.contab.dao.DaoEmpresas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;


public class LogicAsientoMayor {

    public static ArrayList<BeanAsientoMayor> listarFuAsientos(String codigoEmpresa) throws SQLException{
        ArrayList<BeanAsientoMayor> lista=new ArrayList();
        ArrayList param=new ArrayList();
        param.add(OracleTypes.CURSOR);
        param.add(codigoEmpresa);
        ArrayList objetos=(ArrayList)DaoAsientoMayor.consultaAsiento(param);
        ResultSet rs=(ResultSet)objetos.get(0);
        OracleCallableStatement cst = (OracleCallableStatement) objetos.get(1);
        if(rs!=null && cst !=null){            
            while(rs.next()){
            BeanAsientoMayor objAsiento=new BeanAsientoMayor();
            objAsiento.setCodigo(rs.getString(1));
            objAsiento.setDescripcion(rs.getString(2));
            lista.add(objAsiento);
        }
        rs.close();
        cst.close();
        DaoEmpresas.objCnx.destroy();
        }
        return lista;
    }
}
