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
        TreeEntry moduloContable = new TreeEntry("MODULO CONTABLE", picture.getIconContabilidad48(), "mc");
        ArrayList<TreeEntry> hijosRoot = new ArrayList();
        hijosRoot.add(moduloContable);
        root.setHijos(hijosRoot);
        /*Fin Modulos*/
        TreeEntry dmc = new TreeEntry("Diario/Mayor/Caja", picture.getIconLibros32(), "mcrptlibrodiario");
        ArrayList<TreeEntry> hijosModuloContable = new ArrayList();
        hijosModuloContable.add(dmc);
        TreeEntry balances = new TreeEntry("Balances e Inventarios", picture.getIconBalance32(), "mcnodobalances");
        hijosModuloContable.add(balances);
        moduloContable.setHijos(hijosModuloContable);
        ArrayList<TreeEntry> hijosBalance = new ArrayList();
        TreeEntry cuenta14 = new TreeEntry("FORMATO 3.4 Cuenta 14", picture.getIconCuenta14(), "mcbalancecuenta14");
        TreeEntry balanceComprobacion = new TreeEntry("FORMATO 3.17 Balance de Comprobación ", picture.getBalanceComprobacion(), "mcbalancecomprobacion");

        TreeEntry saldoCuentas = new TreeEntry("FORMATO 3.3 Saldo De Cuenta de Cliente 12 ", picture.getDetalleCuenta12(), "mcsaldodecuenta");
        TreeEntry detalleCuenta10 = new TreeEntry("FORMATO 3.2 Detalle Cuenta 10  ", picture.getDetalleCuenta10(), "mcdetallecuenta10");
        TreeEntry cuenta16 = new TreeEntry("FORMATO 3.5 Cuenta 16", picture.getIconCuenta14(), "mcbalancecuenta16");
        TreeEntry mercaderiaCuenta21 = new TreeEntry("FORMATO 3.7  Mercadería Cuenta 20 ", picture.getCuenta21(), "mcmercaderiacuenta21");

        TreeEntry cuenta40 = new TreeEntry("FORMATO 3.10 Cuenta 40  ", picture.getIconCuenta14(), "mcCuenta40");

        TreeEntry Cuenta41 = new TreeEntry("FORMATO 3.11 Saldo Cuenta 41  ", picture.getIconCuenta14(), "mcCuenta41");
        TreeEntry saldoCuenta42 = new TreeEntry("FORMATO 3.12 Saldo Cuenta 42  ", picture.getIconCuenta14(), "mcsaldocuenta42");

//        hijosBalance.add(balanceComprobacion);
//        TreeEntry activo33 = new TreeEntry("Libro Activo", picture.getIconActivo33(), "mcactivo");
//        hijosBalance.add(activo33);
//
//        TreeEntry Cuenta19 = new TreeEntry("Cuenta 19", picture.getIconCuenta19(), "mcCuenta19");
//        hijosBalance.add(Cuenta19);

        TreeEntry formato319 = new TreeEntry("Formato 3.19 ESTADO DE CAMBIOS EN EL PATRIMONIO NETO", picture.getIconFormato319(), "mcFormato319");


        TreeEntry Cuenta46 = new TreeEntry("Formato 3.13 Cuenta 46 ", picture.getIconCuenta46(), "mcCuenta46");


        TreeEntry Cuenta47 = new TreeEntry("Formato 3.14 Cuenta 47 ", picture.getIconCuenta47(), "mcCuenta47");

        TreeEntry accionista = new TreeEntry("FORMATO 3.16 ACCIONISTA ", picture.getCuenta50(), "mcAccionista");
        TreeEntry cuenta31 = new TreeEntry("FORMATO 3.8 DETALLE CUENTA 31 ", picture.getCuenta50(), "mcCuenta31");
        TreeEntry LibroInventarioBalanceGP = new TreeEntry("FORMATO 3.20 Libro Inventario Balance GYP ", picture.getCuenta50(), "mcLibroInventarioBalanceGP");
        TreeEntry LibroBalanceGeneral = new TreeEntry("FORMATO 3.1 Libro Balance General  ", picture.getCuenta50(), "mcLibroIBalanceGeneral");


        hijosBalance.add(LibroBalanceGeneral);  //formato 3.1
        hijosBalance.add(detalleCuenta10);  // formato 3.2
        hijosBalance.add(saldoCuentas);  //formato 3.3
        hijosBalance.add(cuenta14);  // formato 3.4
        hijosBalance.add(cuenta16); //formato 3.5
        /// falta cuenta 19
        hijosBalance.add(mercaderiaCuenta21);  // formato 3.7
        hijosBalance.add(cuenta31);  // formato  3.8
        hijosBalance.add(cuenta40);  // formato 3.10
        hijosBalance.add(Cuenta41);  // formato 3.11
        hijosBalance.add(saldoCuenta42); /// formato 3.12

        hijosBalance.add(Cuenta46);  // formato 3.13
        hijosBalance.add(Cuenta47); /// formato 3.14
        hijosBalance.add(accionista);
        hijosBalance.add(balanceComprobacion);
        hijosBalance.add(formato319);

        hijosBalance.add(LibroInventarioBalanceGP);



        balances.setHijos(hijosBalance);

        TreeEntry kardex = new TreeEntry("FORMATO 13.1 Kardex ", picture.getIconKardex(), "mcKardex");
        TreeEntry compra = new TreeEntry("FORMATO 8.1: REGISTRO DE COMPRAS", picture.getCompra(), "mcCompra");
           TreeEntry venta = new TreeEntry("FORMATO 14.1: REGISTRO DE VENTA", picture.getVenta(), "mcVenta");
            TreeEntry activoFijo = new TreeEntry("FORMATO 14.1: ACTIVO FIJO", picture.getCompra(), "mcActivoFijo");
        hijosModuloContable.add(kardex);
        hijosModuloContable.add(compra);
         hijosModuloContable.add(venta);
            hijosModuloContable.add(activoFijo);

        TreeEntry emp = new TreeEntry("EMP/ACC", picture.getIconEmpleado32(), "mcempacc");
        TreeEntry daot = new TreeEntry("DAOT", picture.getIconEmpleado32(), "mcdaot");

        hijosModuloContable.add(emp);
        hijosModuloContable.add(daot);
        TreeEntry asientodoble = new TreeEntry("ACTUALIZAR BALANCE", picture.getIconEmpleado32(), "mcAsientodoble");
        hijosModuloContable.add(asientodoble);

        TreeEntry sunat = new TreeEntry("Tablas Sunat", picture.getIconSunat(), "mcSunat");
        hijosModuloContable.add(sunat);

        ArrayList<TreeEntry> hijosSunat = new ArrayList();

        TreeEntry tabla12 = new TreeEntry(" Tabla 12", picture.getTabla12(), "mcTabla12");


        TreeEntry tabla6 = new TreeEntry(" Tabla 06", picture.getTabla06(), "mcTabla6");
        TreeEntry tabla10 = new TreeEntry(" Tabla 10", picture.getTabla10(), "mcTabla10");
        TreeEntry tabla03 = new TreeEntry(" Tabla 03", picture.getTabla03(), "mcTabla03");
        TreeEntry prueba = new TreeEntry(" view Prueba", picture.getTabla03(), "mcPrueba");
        hijosSunat.add(tabla03);
        hijosSunat.add(tabla6);
        hijosSunat.add(tabla10);
        hijosSunat.add(tabla12);
             hijosSunat.add(prueba);



        sunat.setHijos(hijosSunat);

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
