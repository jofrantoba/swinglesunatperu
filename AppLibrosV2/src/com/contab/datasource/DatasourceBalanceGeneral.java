
package com.contab.datasource;

import com.contab.bean.BeanBalanceGeneral;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class DatasourceBalanceGeneral implements JRDataSource {

  private int cursor=-1;
    private List<BeanBalanceGeneral> BeanBalanceGeneral= new ArrayList();
    @Override
    public boolean next() throws JRException {
        return ++cursor<BeanBalanceGeneral.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor=null;
        if("accionesInversion".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getAccionesInversion();
        } 
        else if("activosIntabgibles".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getActivosIntabgibles();
        }else if("cajaBanco".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getCajaBanco();
        }else if("capital".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getCapital();
        }else if("capitalAdicional".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getCapitalAdicional();
        }else if("contingencias".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getContingencias();
        }
        //M S T F R
        else if("cuentasCobrarComerciales".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getCuentasCobrarComerciales();
        } else if("cuentasCobrarLargoPlazo".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getCuentasCobrarLargoPlazo();
        }else if("cuentasCobrarVinculadas".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getCuentasCobrarVinculadas();
        }else if("cuentasCobrarVinculadasLargoPlazo".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getCuentasCobrarVinculadasLargoPlazo();
        }else if("cuentasPagarComerciales".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getCuentasPagarComerciales();
        }else if("cuentasPagarVinculadas".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getCuentasPagarVinculadas();
        }
        else if("cuentasPorPagarVinculadas".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getCuentasPorPagarVinculadas();
        }
        //M S T F R
      else if("deudasLargoPlazo".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getDeudasLargoPlazo();
        }else if("existencias".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getExistencias();
        }else if("gastosPagadosAnticipado".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getGastosPagadosAnticipado();
        }else if("impuestoRenta".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getImpuestoRenta();
        }


         else if("ingresosDiferidos".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getIngresosDiferidos();
        }else if("inmueblesMaquinariaEquipo".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getInmueblesMaquinariaEquipo();
        }else if("interesMinoritario".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getInteresMinoritario();
        }else if("inversionesPermanetes".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getInversionesPermanetes();
        }

         else if("otrasCuentasCobrar".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getOtrasCuentasCobrar();
        }else if("otrasCuentasCobrarLargoPlazo".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getOtrasCuentasCobrarLargoPlazo();
        }else if("otrasCuentasPagar".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getOtrasCuentasPagar();
        }else if("otrasResevas".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getOtrasResevas();
        }



         else if("otrosActivos".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getOtrosActivos();
        }else if("parteCorrienteDeudasLargoPlazo".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getParteCorrienteDeudasLargoPlazo();
        }else if("participacionesDiferidosActivo".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getParticipacionesDiferidosActivo();
        }else if("reservaLegales".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getReservaLegales();
        }

         else if("resultadosAcumulados".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getResultadosAcumulados();
        }else if("sobregirosPagaresBancarios".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getSobregirosPagaresBancarios();
        }else if("valoresNegociables".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getValoresNegociables();
        }

        else if("excedentesRevaluación".equals(jrf.getName())){
            valor=BeanBalanceGeneral.get(cursor).getExcedentesRevaluación();
        }
      
        return valor;
    }
    public void addBalance(BeanBalanceGeneral balance){
        this.BeanBalanceGeneral.add(balance);
    }

}
