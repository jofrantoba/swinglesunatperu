/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.datasource;

import com.contab.bean.BeanVenta;
import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author cixtic04
 */
public class DataSourceVenta implements JRDataSource {

    private int cursor = -1;
    private List<BeanVenta> Venta = new ArrayList();

    @Override
    public boolean next() throws JRException {
        return ++cursor < Venta.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("ApellDenominacion".equals(jrf.getName())) {
            valor = Venta.get(cursor).getApellDenominacion();
        } else if ("baseImponible".equals(jrf.getName())) {
            valor = Venta.get(cursor).getBaseImponible();
        } else if ("comprobante".equals(jrf.getName())) {
            valor = Venta.get(cursor).getComprobante();
        } else if ("domiciliado".equals(jrf.getName())) {
            valor = Venta.get(cursor).getDomiciliado();
        } else if ("fecha".equals(jrf.getName())) {
            valor = Venta.get(cursor).getFecha();
        } else if ("fechaEmision".equals(jrf.getName())) {
            valor = Venta.get(cursor).getFechaEmision();
        }

         else if ("tipoDpcumento".equals(jrf.getName())) {
            valor = Venta.get(cursor).getTipoDocumento();
        }
          else if ("valorFacturado".equals(jrf.getName())) {
            valor = Venta.get(cursor).getValorFacturado();
        }
            else if ("valorFacturado".equals(jrf.getName())) {
            valor = Venta.get(cursor).getValorFacturado();
        }
                  else if ("exonerada".equals(jrf.getName())) {
            valor = Venta.get(cursor).getExonerada();
        }

//exonerada

        else if ("fechaR".equals(jrf.getName())) {
            valor = Venta.get(cursor).getFechaR();
        } else if ("fechaV".equals(jrf.getName())) {
            valor = Venta.get(cursor).getFechav();
        } else if ("igv".equals(jrf.getName())) {
            valor = Venta.get(cursor).getIgv();
        } else if ("importe".equals(jrf.getName())) {
            valor = Venta.get(cursor).getImporte();
        } else if ("noAfecto".equals(jrf.getName())) {
            valor = Venta.get(cursor).getNoAfecto();
        }


        else if ("numero".equals(jrf.getName())) {
            valor = Venta.get(cursor).getNumero();
        }

        else if ("numero1".equals(jrf.getName())) {
            valor = Venta.get(cursor).getNumero1();
        }

        else if ("preImpreso".equals(jrf.getName())) {
            valor = Venta.get(cursor).getPreimpreso();
        }

        else if ("rucDni".equals(jrf.getName())) {
            valor = Venta.get(cursor).getRucDNI();
        }
        else if ("serie".equals(jrf.getName())) {
            valor = Venta.get(cursor).getSerie();
        }


        else if ("serie1".equals(jrf.getName())) {
            valor = Venta.get(cursor).getSerie1();
        }

        else if ("tabla10".equals(jrf.getName())) {
            valor = Venta.get(cursor).getTabla10();
        }

        else if ("tabla101".equals(jrf.getName())) {
            valor = Venta.get(cursor).getTabla101();
        }

        else if ("tabla2".equals(jrf.getName())) {
            valor = Venta.get(cursor).getTabla2();
        }

        else if ("tc".equals(jrf.getName())) {
            valor = Venta.get(cursor).getTc();
        }

        else if ("nombre".equals(jrf.getName())) {
            valor = Venta.get(cursor).getNombre();
        }
        return valor;
    }

    public void add(BeanVenta objCuentasxPagar) {
        this.Venta.add(objCuentasxPagar);
    }

    public List<BeanVenta> getVenta() {
        return Venta;
    }
}
