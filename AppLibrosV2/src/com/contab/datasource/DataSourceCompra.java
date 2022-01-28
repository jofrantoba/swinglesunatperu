/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.datasource;

import com.contab.bean.BeanCompra;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author cixtic04
 */
public class DataSourceCompra implements JRDataSource {

    private int cursor = -1;
    private List<BeanCompra> Compra = new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor < Compra.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("ApellDenominacion".equals(jrf.getName())) {
            valor = Compra.get(cursor).getApellDenominacion();
        } else if ("baseImponible".equals(jrf.getName())) {
            valor = Compra.get(cursor).getBaseImponible();
        } else if ("comprobante".equals(jrf.getName())) {
            valor = Compra.get(cursor).getComprobante();
        } else if ("domiciliado".equals(jrf.getName())) {
            valor = Compra.get(cursor).getDomiciliado();
        } else if ("fecha".equals(jrf.getName())) {
            valor = Compra.get(cursor).getFecha();
        } else if ("fechaEmision".equals(jrf.getName())) {
            valor = Compra.get(cursor).getFechaEmision();
        }

         else if ("tipoDpcumento".equals(jrf.getName())) {
            valor = Compra.get(cursor).getTipoDocumento();
        }


        else if ("fechaR".equals(jrf.getName())) {
            valor = Compra.get(cursor).getFechaR();
        } else if ("fechaV".equals(jrf.getName())) {
            valor = Compra.get(cursor).getFechav();
        } else if ("igv".equals(jrf.getName())) {
            valor = Compra.get(cursor).getIgv();
        } else if ("importe".equals(jrf.getName())) {
            valor = Compra.get(cursor).getImporte();
        } else if ("noAfecto".equals(jrf.getName())) {
            valor = Compra.get(cursor).getNoAfecto();
        }
        else if ("percepcion".equals(jrf.getName())) {
            valor = Compra.get(cursor).getPercepcion();
        }

        else if ("numero".equals(jrf.getName())) {
            valor = Compra.get(cursor).getNumero();
        }

        else if ("numero1".equals(jrf.getName())) {
            valor = Compra.get(cursor).getNumero1();
        }

        else if ("preImpreso".equals(jrf.getName())) {
            valor = Compra.get(cursor).getPreimpreso();
        }

        else if ("rucDni".equals(jrf.getName())) {
            valor = Compra.get(cursor).getRucDNI();
        }
        else if ("serie".equals(jrf.getName())) {
            valor = Compra.get(cursor).getSerie();
        }


        else if ("serie1".equals(jrf.getName())) {
            valor = Compra.get(cursor).getSerie1();
        }

        else if ("tabla10".equals(jrf.getName())) {
            valor = Compra.get(cursor).getTabla10();
        }

        else if ("tabla101".equals(jrf.getName())) {
            valor = Compra.get(cursor).getTabla101();
        }

        else if ("tabla2".equals(jrf.getName())) {
            valor = Compra.get(cursor).getTabla2();
        }

        else if ("tc".equals(jrf.getName())) {
            valor = Compra.get(cursor).getTc();
        }

        else if ("nombre".equals(jrf.getName())) {
            valor = Compra.get(cursor).getNombre();
        }
        return valor;
    }

    public void add(BeanCompra objCuentasxPagar) {
        this.Compra.add(objCuentasxPagar);
    }

    public List<BeanCompra> getCompra() {
        return Compra;
    }
}
