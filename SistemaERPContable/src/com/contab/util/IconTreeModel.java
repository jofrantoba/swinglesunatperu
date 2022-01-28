package com.contab.util;

import java.util.ArrayList;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class IconTreeModel implements TreeModel {

    private TreeEntry root;
    protected EventListenerList listenerList = new EventListenerList();

    public IconTreeModel() {
        root = new TreeEntry("_ROOT_", null, "root");
        Imagenes picture = new Imagenes();
        /*Modulos */
        TreeEntry moduloContable = new TreeEntry("Xray", picture.getIconContabilidad48(), "mc");

        ArrayList<TreeEntry> hijosRoot = new ArrayList();
        hijosRoot.add(moduloContable);
        root.setHijos(hijosRoot);
        ArrayList<TreeEntry> hijosModuloContable = new ArrayList();
        moduloContable.setHijos(hijosModuloContable);



/////////////********************* Mantemiento *////////////////////
        TreeEntry itemMatemiento = new TreeEntry("Mantemiento", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
        hijosModuloContable.add(itemMatemiento);

        ArrayList<TreeEntry> hijosMantemiento = new ArrayList();

        TreeEntry LibroBalanceGeneral = new TreeEntry("Correlativo ", picture.getIconPaquete(), "mcFrmMantemientoCorrelativo");
        hijosMantemiento.add(LibroBalanceGeneral);

        itemMatemiento.setHijos(hijosMantemiento);
        // fin 

        /////////////********************* Mantemiento *////////////////////
        TreeEntry itemContabilidadGeneral = new TreeEntry("Contabilidad General", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
        hijosModuloContable.add(itemContabilidadGeneral);
        ArrayList<TreeEntry> hijosContabilidadGeneral = new ArrayList();

            TreeEntry itemContabilidadGeneralM = new TreeEntry("Mantenimiento", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
            hijosContabilidadGeneral.add(itemContabilidadGeneralM);

                ArrayList<TreeEntry> nietoContabilidadGeneral = new ArrayList();
                TreeEntry TipoCambio = new TreeEntry("Tipo de Cambio ", picture.getIconPaquete(), "mcTipoDeCambio");
                nietoContabilidadGeneral.add(TipoCambio);
                TreeEntry PeriodoContable = new TreeEntry("Períodos Contables ", picture.getIconPaquete(), "mcFrmMantenimientoPeriodoContable");
                nietoContabilidadGeneral.add(PeriodoContable);
                TreeEntry CuentaContable = new TreeEntry("Cuentas Contables ", picture.getIconPaquete(), "mcFrmMantenimientoDeCuentaContable");
                nietoContabilidadGeneral.add(CuentaContable);
                TreeEntry Auxiliares = new TreeEntry("Auxiliares ", picture.getIconPaquete(), "mcFrmMantenimientoDeAuxiliares");
                nietoContabilidadGeneral.add(Auxiliares);
                TreeEntry Asientostipo = new TreeEntry("Asientos tipo ", picture.getIconPaquete(), "mcFrmMantenimientoDeAsientoTipo");
                nietoContabilidadGeneral.add(Asientostipo);
                TreeEntry Autoridades = new TreeEntry("Autoridades ", picture.getIconPaquete(), "mcFrmMantenimientoAutoridades");
                nietoContabilidadGeneral.add(Autoridades);

            itemContabilidadGeneralM.setHijos(nietoContabilidadGeneral);

            // fin mantenimienti

            TreeEntry itemContabilidadGeneraProceso = new TreeEntry("Proceso", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
            hijosContabilidadGeneral.add(itemContabilidadGeneraProceso);
            ArrayList<TreeEntry> nietoContabilidadGeneralProceso = new ArrayList();
            
                TreeEntry Asientos = new TreeEntry("Asientos ", picture.getIconPaquete(), "mcCorrelativo");
                nietoContabilidadGeneralProceso.add(Asientos);

            itemContabilidadGeneraProceso.setHijos(nietoContabilidadGeneralProceso);

         // fin Proceso
        
            TreeEntry itemConsultaReporte= new TreeEntry("Consulta y Reporte", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
            hijosContabilidadGeneral.add(itemConsultaReporte);
            ArrayList<TreeEntry> nietoConsultaReporte = new ArrayList();
            
                TreeEntry AsientosRC = new TreeEntry("Asientos ", picture.getIconPaquete(), "mcFrmConsultaImpresionAsiento");
                nietoConsultaReporte.add(AsientosRC);
                TreeEntry SaldoRC = new TreeEntry("Saldo ", picture.getIconPaquete(), "mcFrmSaldoBalanceGeneral");
                nietoConsultaReporte.add(SaldoRC);
                TreeEntry SaldoXAuxiliarRC = new TreeEntry("Saldo por Auxiliar ", picture.getIconPaquete(), "mcFrmConsultaSaldoPorAuxiliar");
                nietoConsultaReporte.add(SaldoXAuxiliarRC);
                TreeEntry AnalisisCuentaAuxiliarRC = new TreeEntry("Análisis de Cuenta Auxiliar ", picture.getIconPaquete(), "mcAnalisisCuentaAuxiliarRC");
                nietoConsultaReporte.add(AnalisisCuentaAuxiliarRC);
                TreeEntry AnalisisCuentaCentroRC= new TreeEntry("Análisis de Ctas Centros ", picture.getIconPaquete(), "mcAnalisisCuentaCentroRC");
                nietoConsultaReporte.add(AnalisisCuentaCentroRC);

            itemConsultaReporte.setHijos(nietoConsultaReporte);

         // fin conuslta y reporte
            
            TreeEntry itemReporteOficiales= new TreeEntry("Reporte Oficiales", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
            hijosContabilidadGeneral.add(itemReporteOficiales);
            ArrayList<TreeEntry> nietoReporteOficiales = new ArrayList();
            
                TreeEntry diario = new TreeEntry("Diario ", picture.getIconPaquete(), "mcCorrelativo");
                nietoReporteOficiales.add(diario);

            itemReporteOficiales.setHijos(nietoReporteOficiales);

         // Reporte Oficiales   
            
            TreeEntry itemCargaInicial= new TreeEntry("Carga Inicial", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
            hijosContabilidadGeneral.add(itemCargaInicial);
            ArrayList<TreeEntry> nietoCargaInicial = new ArrayList();
            
                TreeEntry cargaDocumento = new TreeEntry("Carga de Documentos ", picture.getIconPaquete(), "mcCorrelativo");
                nietoCargaInicial.add(cargaDocumento);

            itemCargaInicial.setHijos(nietoCargaInicial);

         // Carga Inicial  


       itemContabilidadGeneral.setHijos(hijosContabilidadGeneral);
       
        // fin  Contabilidad General

        /////////////********************* Cuenta por Cobrar *////////////////////
        TreeEntry itemCunetaxCobrar = new TreeEntry("Cuenta por Cobrar", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
        hijosModuloContable.add(itemCunetaxCobrar);
        ArrayList<TreeEntry> hijosCuentaxCpbrar = new ArrayList();
        
             TreeEntry itemCuentaxcobrar = new TreeEntry("Mantemiento", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
             hijosCuentaxCpbrar.add(itemCuentaxcobrar);
             ArrayList<TreeEntry> nietoCuentaxcobrarM = new ArrayList();
            
                TreeEntry aperturacierredocumentos = new TreeEntry("Asientos ", picture.getIconPaquete(), "mcCorrelativo");
                nietoCuentaxcobrarM.add(aperturacierredocumentos);
                itemCuentaxcobrar.setHijos(nietoCuentaxcobrarM);
           
             TreeEntry itemDocumentos = new TreeEntry("Documentos", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
             hijosCuentaxCpbrar.add(itemDocumentos);
             ArrayList<TreeEntry> nietoDocumentos = new ArrayList();
            
                TreeEntry documentosxcobrar = new TreeEntry("Documentos por Cobrar ", picture.getIconPaquete(), "mcCorrelativo");
                nietoDocumentos.add(documentosxcobrar);
                itemDocumentos.setHijos(nietoDocumentos);            
        
        itemCunetaxCobrar.setHijos(hijosCuentaxCpbrar);
        //// fin
        
        /////////////********************* Cuenta por pagar *////////////////////
        TreeEntry itemCuentaxPagar = new TreeEntry("Cuenta por Pagar", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
        hijosModuloContable.add(itemCuentaxPagar);
        ArrayList<TreeEntry> hijosCuentaxPagar = new ArrayList();
        
             TreeEntry itemCuentaxPagarM = new TreeEntry("Mantenimiento", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
             hijosCuentaxPagar.add(itemCuentaxPagarM);
             ArrayList<TreeEntry> nietoCuentaxPagarM = new ArrayList();
            
                TreeEntry registroRetenciones = new TreeEntry("Registro Retenciones ", picture.getIconPaquete(), "mcCorrelativo");
                nietoCuentaxPagarM.add(registroRetenciones);
                itemCuentaxPagarM.setHijos(nietoCuentaxPagarM);
           
             TreeEntry itemDocumentosP = new TreeEntry("Documentos", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
             hijosCuentaxPagar.add(itemDocumentosP);
             ArrayList<TreeEntry> nietoDocumentosP = new ArrayList();
            
                TreeEntry documentosxpagar = new TreeEntry("Documentos por Pagar ", picture.getIconPaquete(), "mcCorrelativo");
                nietoDocumentosP.add(documentosxpagar);
                itemDocumentosP.setHijos(nietoDocumentos);            
        
        itemCuentaxPagar.setHijos(hijosCuentaxPagar);
        //// fin
        
           /////////////********************* Caja y Banco *////////////////////
        TreeEntry itemCajaBanco = new TreeEntry("Caja y Banco", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
        hijosModuloContable.add(itemCajaBanco);
        ArrayList<TreeEntry> hijosCajaBanco = new ArrayList();
        
             TreeEntry itemCajaBancoM = new TreeEntry("Mantenimiento", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
             hijosCajaBanco.add(itemCajaBancoM);
             ArrayList<TreeEntry> nietoCajaBancoM = new ArrayList();
            
                TreeEntry cuentaBancarias = new TreeEntry("Cuentas Bancarias ", picture.getIconPaquete(), "mcCorrelativo");
                nietoCajaBancoM.add(cuentaBancarias);
                itemCajaBancoM.setHijos(nietoCajaBancoM);
           
             TreeEntry itemIngreso = new TreeEntry("Ingreso", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
             hijosCajaBanco.add(itemIngreso);
             ArrayList<TreeEntry> nietoIngreso = new ArrayList();
            
                TreeEntry ingresoBanco = new TreeEntry("Ingreso Banco ", picture.getIconPaquete(), "mcCorrelativo");
                nietoIngreso.add(ingresoBanco);
                itemIngreso.setHijos(nietoDocumentos);            
        
        itemCajaBanco.setHijos(hijosCuentaxPagar);
        //// fin
        
        
         /////////////********************* Caja y Banco *////////////////////
        TreeEntry itemPresupuesto= new TreeEntry("Presupuesto", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
        hijosModuloContable.add(itemPresupuesto);
        ArrayList<TreeEntry> hijosPresupuesto = new ArrayList();
        
             TreeEntry item = new TreeEntry("Mantenimiento", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
             hijosPresupuesto.add(item);
                     
        itemPresupuesto.setHijos(hijosPresupuesto);
        //// fin
        
        TreeEntry itemFlujoCaja= new TreeEntry("Flujo Caja", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
        hijosModuloContable.add(itemFlujoCaja);
        ArrayList<TreeEntry> hijosFlujoCaja = new ArrayList();
        
             TreeEntry item1 = new TreeEntry("Mantenimiento", picture.getIconPaquete(), "mcMantemiento"); /// comienzo
             hijosFlujoCaja.add(item);
                     
        itemFlujoCaja.setHijos(hijosFlujoCaja);

    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((TreeEntry) parent).get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((TreeEntry) parent).size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((TreeEntry) node).size() == 0;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((TreeEntry) parent).indexOf(child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listenerList.add(TreeModelListener.class, l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listenerList.remove(TreeModelListener.class, l);
    }
}
