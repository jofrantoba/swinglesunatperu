package com.contab.datasource;

import com.contab.bean.BeanCuenta46;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DataSourceCuenta46 implements JRDataSource {

    private int cursor = -1;
    private List<BeanCuenta46> Cuenta46 = new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor < Cuenta46.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("tipo".equals(jrf.getName())) {
            valor = Cuenta46.get(cursor).getTipo();
        } else if ("numero".equals(jrf.getName())) {
            //SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
            valor = Cuenta46.get(cursor).getNumero();
        } else if ("apellidos".equals(jrf.getName())) {
            valor = Cuenta46.get(cursor).getApellidosnombre();
        } else if ("descripcion".equals(jrf.getName())) {
            valor = Cuenta46.get(cursor).getDescripcion();
        } else if ("fechaemision".equals(jrf.getName())) {
            valor = Cuenta46.get(cursor).getFechaemision();
        } else if ("montobase".equals(jrf.getName())) {
            valor = Cuenta46.get(cursor).getMontobase();
        } else if ("cuenta".equals(jrf.getName())) {
            valor = Cuenta46.get(cursor).getCuenta();
        } else if ("numcuenta".equals(jrf.getName())) {
            valor = Cuenta46.get(cursor).getNumcuenta();
        } else if ("descuenta".equals(jrf.getName())) {
            valor = Cuenta46.get(cursor).getDescuenta();
        } else if ("dc".equals(jrf.getName())) {
            valor = Cuenta46.get(cursor).getDc();
        } else if ("descripcion".equals(jrf.getName())) {
            valor = Cuenta46.get(cursor).getDescripcion();
        } else if ("apellidosnombre".equals(jrf.getName())) {
            valor = Cuenta46.get(cursor).getApellidosnombre();
        } else if ("numero".equals(jrf.getName())) {
            valor = Cuenta46.get(cursor).getNumero()
                    ;
        }
        return valor;
    }

    public void add(BeanCuenta46 objCuentasxPagar) {
        this.Cuenta46.add(objCuentasxPagar);
    }

    public List<BeanCuenta46> getCuenta46() {
        return Cuenta46;
    }
}
