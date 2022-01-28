package com.contab.datasource;



import com.contab.bean.BeaLlibroInventarioBalanceGP;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class DatasourceLibroInventarioBalanceGP implements JRDataSource {

    private int cursor = -1;
    private List<BeaLlibroInventarioBalanceGP> BeanLibroInventarioBalanceGP = new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor < BeanLibroInventarioBalanceGP.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("ventaNeta".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getVentaNeta();

        } else if ("otroIngresoOperacionales".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getOtroIngresoOperacionales();
        } else if ("costoDeVentas".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getCostoDeVentas();
        } else if ("gastoOperacionales".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getGastoOperacionales();
        } else if ("gastosAdministracion".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getGastosAdministracion();
        } else if ("gastoVenta".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getGastoVenta();
        } else if ("otrosImgresos".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getOtrosImgresos();
        } else if ("ingresosFinanacieros".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getIngresosFinanacieros();
        } else if ("gastosFinacieros".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getGastosFinacieros();
        }else if ("otrosIngresos".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getOtrosIngresos();
        }
        else if ("otrosGastos".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getOtrosGasto();
        }
        else if ("resultadosExposicionInflacion".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getResultadosExposicionInflacion();
        }
         else if ("participacion".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getParticipacion();
        }
         else if ("impuestoRenta".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getImpuestoRenta();
        }
         else if ("ingresoExtraordinarios".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getIngresoExtraordinarios();
        }
         else if ("gastosExtraordinarios".equals(jrf.getName())) {
            valor = BeanLibroInventarioBalanceGP.get(cursor).getGastosExtraordinarios();
        }


        return valor;
    }

    public void addLlibroInventarioBalanceGP(BeaLlibroInventarioBalanceGP LibroInventarioBalanceGP) {
        this.BeanLibroInventarioBalanceGP.add(LibroInventarioBalanceGP);
    }
}
