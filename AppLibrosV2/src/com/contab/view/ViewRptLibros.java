package com.contab.view;

import com.contab.bean.BeanAsientoMayor;
import com.contab.bean.BeanBanco;
import com.contab.bean.BeanCuentaMayor;
import com.contab.bean.BeanEmpresa;
import com.contab.bean.BeanLibroMayor;
import com.contab.combobox.ComboModelCuentaBanco;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.datasource.DataSourceLibroDiario;
import com.contab.datasource.DataSourceLibroMayor;
import com.contab.logic.LogicAsientoMayor;
import com.contab.logic.LogicCaja;
import com.contab.logic.LogicCuentaMayor;
import com.contab.logic.LogicLibroMayor;
import com.contab.util.ClassFecha;
import com.contab.util.Exportar;
import com.contab.util.JFileChooserModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import org.openide.util.Exceptions;

public class ViewRptLibros {

    private JTabbedPane tabs = new JTabbedPane();
    private FrmPanel panelLibroDiario = new FrmPanel(ViewLogin.picture.getIconDiario(), "REPORTES LIBRO DIARIO");
    private FrmPanel panelLibroMayor = new FrmPanel(ViewLogin.picture.getIconLibroMayor(), "REPORTES LIBRO MAYOR");
    private FrmPanel panelLibroCaja = new FrmPanel(ViewLogin.picture.getIconLibroCaja(), "REPORTES LIBRO CAJA");
    private JPanel panelMolde = new JPanel();
    private PanelRptLibroDiario frmLibroDiario = new PanelRptLibroDiario();
    //private PanelBalanceComprobacion frmBalanceComprobacion = new PanelBalanceComprobacion();
    private formCaja panelRptLibroCaja = new formCaja();
    private PanelRPLibroMayor frmLibroMayor = new PanelRPLibroMayor();
    private JFileChooserModel fileChooser = new JFileChooserModel();
    FileNameExtensionFilter filterDOC = new FileNameExtensionFilter("docx", "docx");
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    FileNameExtensionFilter filterTXT = new FileNameExtensionFilter("txt", "txt");
    private DataSourceLibroMayor dataLibroMayorG = new DataSourceLibroMayor();

    public ViewRptLibros() throws SQLException {
        initComponents();
    }

    private void initComponents() throws SQLException {
        panelMolde.setLayout(new BorderLayout());
        panelMolde.add(tabs);
        tabs.addTab("LIBRO DIARIO", ViewLogin.picture.getIconDiario(), panelLibroDiario);
        tabs.addTab("LIBRO MAYOR", ViewLogin.picture.getIconLibroMayor(), panelLibroMayor);
        tabs.addTab("LIBRO CAJA", ViewLogin.picture.getIconLibroCaja(), panelLibroCaja);

        panelLibroDiario.getNuevo().setIcon(ViewLogin.picture.getWord());
        panelLibroDiario.getNuevo().setToolTipText("Exportar a Word");
        panelLibroDiario.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panelLibroDiario.getActualizar().setToolTipText("Exportar a Excel");
        panelLibroDiario.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panelLibroDiario.getEliminar().setToolTipText("Exportar a pdf");


        panelLibroCaja.getNuevo().setIcon(ViewLogin.picture.getWord());
        panelLibroCaja.getNuevo().setToolTipText("Exportar a txt");
        panelLibroCaja.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panelLibroCaja.getActualizar().setToolTipText("Exportar a Excel");
        panelLibroCaja.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panelLibroCaja.getEliminar().setToolTipText("Exportar a pdf");

        panelLibroMayor.getGenerar().setIcon(ViewLogin.picture.getIconGenerar());
        panelLibroMayor.getGenerar().setToolTipText("Generar libro mayor");

        panelLibroMayor.getWord().setIcon(ViewLogin.picture.getIconword32());
        panelLibroMayor.getWord().setToolTipText("Exportar a Word");
        panelLibroMayor.getWord().setEnabled(false);

        panelLibroMayor.getNuevo().setIcon(ViewLogin.picture.getIconTXT());
        panelLibroMayor.getNuevo().setToolTipText("Exportar a txt");
        panelLibroMayor.getNuevo().setEnabled(false);

        panelLibroMayor.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panelLibroMayor.getActualizar().setToolTipText("Exportar a Excel");
        panelLibroMayor.getActualizar().setEnabled(false);

        panelLibroMayor.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panelLibroMayor.getEliminar().setToolTipText("Exportar a pdf");
        panelLibroMayor.getEliminar().setEnabled(false);

        panelLibroMayor.getPanelData().setVisible(false);

        panelLibroCaja.getPanelData().setVisible(false);
        panelLibroDiario.getPanelData().setVisible(false);
        panelLibroDiario.getPanelFormulario().setLayout(new BorderLayout());
        panelLibroDiario.getPanelFormulario().add(frmLibroDiario);
        panelLibroDiario.getEliminar().addActionListener(actionListener);
        panelLibroDiario.getActualizar().addActionListener(actionListener);

        panelLibroCaja.getPanelFormulario().setLayout(new BorderLayout());

        panelLibroCaja.getPanelFormulario().add(panelRptLibroCaja);
        panelLibroMayor.getPanelFormulario().add(frmLibroMayor);


        panelLibroMayor.getGenerar().addActionListener(actionListener);
        panelLibroMayor.getWord().addActionListener(actionListener);
        panelLibroMayor.getActualizar().addActionListener(actionListener);
        panelLibroMayor.getEliminar().addActionListener(actionListener);
        panelLibroMayor.getNuevo().addActionListener(actionListener);

        panelLibroCaja.getEliminar().addActionListener(actionListener);
        panelLibroCaja.getActualizar().addActionListener(actionListener);
        panelLibroCaja.getNuevo().addActionListener(actionListener);

    }

    private File seleccionarArchivo(FileNameExtensionFilter tipoFiltro, String nombreLibro, String tipoExportacion) {
        fileChooser.setFileFilter(tipoFiltro);
        Date hoy = new Date();
        File archivo = new File(nombreLibro + hoy.getTime() + "." + tipoExportacion);
        fileChooser.setSelectedFile(archivo);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            JOptionPane.showMessageDialog(null, "No seleccionó ningún fichero", "Diálogo cerrado o cancelado", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }

    private void generarReporteCNX(File archivo, String tipoExportacion, Map parameters, Connection cnx, String rutaJasper) {
        Exportar ex = new Exportar(archivo, parameters, tipoExportacion, cnx, rutaJasper);
        ex.show();
    }

    private void generarReporteDS(File archivo, String tipoExportacion, Map parameters, JRDataSource cnx, String rutaJasper) {
        Exportar ex = new Exportar(archivo, parameters, tipoExportacion, cnx, rutaJasper);
        ex.show();
    }

    private void exportarLibroDiarioAPDF() throws SQLException {
        File archivo = seleccionarArchivo(filterPDF, "LibroDiario", "pdf");
        if (archivo != null) {
            ArrayList array = frmLibroDiario.generarLibroDario();
            if (array != null) {
                DataSourceLibroDiario ds = (DataSourceLibroDiario) array.get(0);
                String periodo = array.get(1).toString();
                BeanEmpresa emp = (BeanEmpresa) array.get(2);
                Map parameters = new HashMap();
                parameters.put("P_PERIODO", periodo);
                parameters.put("P_RUC", emp.getRuc());
                parameters.put("P_RAZON", emp.getDescripcion());
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                BigDecimal inidebe = null;
                BigDecimal inihaber = null;
                while (inidebe == null) {
                    String debeini = JOptionPane.showInputDialog(panelMolde, "Debe del mes anterior", "DEBE INICIAL", JOptionPane.QUESTION_MESSAGE);
                    try {
                        inidebe = BigDecimal.valueOf(new Double(debeini));
                    } catch (Exception ex) {
                        inidebe = null;
                    }
                }
                while (inihaber == null) {
                    String haberini = JOptionPane.showInputDialog(panelMolde, "Haber del mes anterior", "HABER INICIAL", JOptionPane.QUESTION_MESSAGE);
                    try {
                        inihaber = BigDecimal.valueOf(new Double(haberini));
                    } catch (Exception ex) {
                        inihaber = null;
                    }
                }
                parameters.put("P_DEBEINI", inidebe);
                parameters.put("P_HABERINI", inihaber);
                String rutaJasper = "/com/contab/report/rptbookday.jasper";
                generarReporteDS(archivo, "pdf", parameters, ds, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");
            }
        }

    }

    private void exportarLibroDiarioAEXCEL() throws SQLException {
        File archivo = seleccionarArchivo(filterXLS, "LibroDiario", "xlsx");
        if (archivo != null) {
            ArrayList array = frmLibroDiario.generarLibroDario();
            if (array != null) {
                DataSourceLibroDiario ds = (DataSourceLibroDiario) array.get(0);
                String periodo = array.get(1).toString();
                BeanEmpresa emp = (BeanEmpresa) array.get(2);
                Map parameters = new HashMap();
                parameters.put("P_PERIODO", periodo);
                parameters.put("P_RUC", emp.getRuc());
                parameters.put("P_RAZON", emp.getDescripcion());
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                BigDecimal inidebe = null;
                BigDecimal inihaber = null;
                while (inidebe == null) {
                    String debeini = JOptionPane.showInputDialog(panelMolde, "Debe del mes anterior", "DEBE INICIAL", JOptionPane.QUESTION_MESSAGE);
                    try {
                        inidebe = BigDecimal.valueOf(new Double(debeini));
                    } catch (Exception ex) {
                        inidebe = null;
                    }
                }
                while (inihaber == null) {
                    String haberini = JOptionPane.showInputDialog(panelMolde, "Haber del mes anterior", "HABER INICIAL", JOptionPane.QUESTION_MESSAGE);
                    try {
                        inihaber = BigDecimal.valueOf(new Double(haberini));
                    } catch (Exception ex) {
                        inihaber = null;
                    }
                }
                parameters.put("P_DEBEINI", inidebe);
                parameters.put("P_HABERINI", inihaber);
                String rutaJasper = "/com/contab/report/rptbookday.jasper";
                generarReporteDS(archivo, "xlsx", parameters, ds, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");
            }
        }

    }

    private void generarlibromayor() throws SQLException {
        if (!frmLibroMayor.getTxtano().getText().isEmpty() && frmLibroMayor.getCmbEmpresa().getSelectedIndex() != -1
                && frmLibroMayor.getCmbasientos().getSelectedIndex() != -1 && frmLibroMayor.getCmbcuenta().getSelectedIndex() != -1) {

            panelLibroMayor.getGenerar().setEnabled(false);
            panelLibroMayor.getEliminar().setEnabled(false);
            panelLibroMayor.getWord().setEnabled(false);
            panelLibroMayor.getActualizar().setEnabled(false);
            panelLibroMayor.getNuevo().setEnabled(false);

            ViewApplication.mensajeEstado.setText(null);
            ViewApplication.mensajeEstado.setText("PREPARANDODOSE PARA GENERAR EL LIBRO MAYOR!!");

            //arrary de asientos que deben compararse, para agruparlos
            String asientosexplicitos[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "42", "82"};

            //clase que me permite obtener el nombre del mes
            ClassFecha xmes = new ClassFecha();

            //recibir parametros enviados desde el formulario

            String anio = frmLibroMayor.getTxtano().getText();

            int num_mes = frmLibroMayor.getCmbmes().getSelectedIndex();

            String cod_empresa = frmLibroMayor.getObjEmpresa().getCodigo();

            //Llamar a las listas de cuentas y asientos, pasandoles como parametro la empresa.
            ArrayList<BeanCuentaMayor> objCuentas = LogicCuentaMayor.listarFucuentas(cod_empresa);
            ArrayList<BeanAsientoMayor> objAsientos = LogicAsientoMayor.listarFuAsientos(cod_empresa);

            //declaramos el datasource que contedra todos los registros
            DataSourceLibroMayor dataLibroMayor = new DataSourceLibroMayor();
            int y = 0;
            String cod_cuenta = frmLibroMayor.getObjFucuentas().getCodigocuenta();
            int cuentaselect = frmLibroMayor.getCmbcuenta().getSelectedIndex();
            int tamcmbcuenta = frmLibroMayor.getCmbcuenta().getItemCount();
            int cmbcuentaiter = 0;
            //while (Integer.parseInt(objCuentas.get(y).getCodigocuenta()) <= Integer.parseInt(cod_cuenta)) {
            while (cmbcuentaiter <= cuentaselect) {
                int ban = 0;
                //declaramos e inicializamos las variables debe, haber, deudoracreedor para los viene
                BigDecimal sumadebe = BigDecimal.ZERO;
                BigDecimal sumahaber = BigDecimal.ZERO;
                BigDecimal DeudorAcreedor = BigDecimal.ZERO;

                //INICIO DEL BUCLE PARA SACAR LAS SUMAS DEL VIENE
                int i = 0;
                while (i < num_mes) {
                    ViewApplication.mensajeEstado.setText(null);

                    try {
                        ViewApplication.mensajeEstado.setText("CALCULANDO ÚLTIMO VALOR DEL DEUDOR Y ACREEDOR DEL MES DE ENERO HASTA " + xmes.name_Mes(num_mes - 1).toUpperCase() + " DE LA CUENTA: " + objCuentas.get(y).getNombrecuenta());
                    } catch (Exception ex) {
                        Exceptions.printStackTrace(ex);
                        System.out.println("ERROR AL CONVERTIR EL NÚMERO DEL MES ASU NOMBRE" + ex.getMessage());
                    }

                    int j = 0;
                    while (j < objAsientos.size()) {
                        String sql1 = "s2";
                        int ap = 0;
                        while (ap < asientosexplicitos.length) {
                            if (asientosexplicitos[ap].equals(objAsientos.get(j).getCodigo())) {
                                sql1 = "s1";
                                break;
                            }
                            ap += 1;
                        }

                        ArrayList<BeanLibroMayor> objLibroMayor = new ArrayList<BeanLibroMayor>();
                        if (sql1.equals("s1")) {
                            String glosa;
                            try {
                                glosa = "Centralización Mes:" + xmes.name_Mes(i) + " " + frmLibroMayor.getTxtano().getText() + " " + objAsientos.get(j).getDescripcion();
                                objLibroMayor = LogicLibroMayor.libroMayorS1(glosa, frmLibroMayor.getObjEmpresa().getCodigo(), objCuentas.get(y).getCodigocuenta(), objAsientos.get(j).getCodigo(), anio, xmes.Mes(i));
                                sql1 = "s2";
                            } catch (Exception ex) {
                                Exceptions.printStackTrace(ex);
                                System.out.println("Error al ejecutar la consulta del sql1!!:" + ex.getMessage());
                            }
                        } else {

                            try {
                                String glosa = objAsientos.get(j).getDescripcion();
                                objLibroMayor = LogicLibroMayor.libroMayorS2(glosa, frmLibroMayor.getObjEmpresa().getCodigo(), objCuentas.get(y).getCodigocuenta(), objAsientos.get(j).getCodigo(), anio, xmes.Mes(i));

                            } catch (Exception ex) {
                                Exceptions.printStackTrace(ex);
                                System.out.println("Error al ejecutar la consulta del sql2!!:" + ex.getMessage());
                            }
                        }

                        //hacer las sumas
                        int x = 0;
                        while (x < objLibroMayor.size()) {
                            BeanLibroMayor objbeanmayor = new BeanLibroMayor();
                            objbeanmayor.setDebe(objLibroMayor.get(x).getDebe());
                            objbeanmayor.setHaber(objLibroMayor.get(x).getHaber());

                            //----
                            BigDecimal debe = (BigDecimal) objLibroMayor.get(x).getDebe();
                            BigDecimal haber = (BigDecimal) objLibroMayor.get(x).getHaber();

                            sumadebe = sumadebe.add(debe);
                            sumahaber = sumahaber.add(haber);
                            DeudorAcreedor = DeudorAcreedor.add(debe.subtract(haber));

                            x += 1;
                        }
                        j += 1;
                    }
                    i += 1;
                }


                //INICIO DEL BUCLE PARA MOSTRAR EN EL REPORTE

                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PROCESANDO CUENTA: " + objCuentas.get(y).getNombrecuenta());


                BigDecimal deudor = BigDecimal.ZERO;
                BigDecimal acreedor = BigDecimal.ZERO;
                if (DeudorAcreedor != BigDecimal.ZERO) {
                    if (DeudorAcreedor.compareTo(BigDecimal.ZERO) == 1) {
                        deudor = DeudorAcreedor;
                        acreedor = BigDecimal.ZERO;
                    } else {
                        deudor = BigDecimal.ZERO;
                        acreedor = DeudorAcreedor.abs();
                    }
                }

                BigDecimal deudoracreedor = DeudorAcreedor;

                int a = 0;
                int asientoselect = frmLibroMayor.getCmbasientos().getSelectedIndex();
                int cmbasientotal = frmLibroMayor.getCmbasientos().getItemCount();
                int asientoiterator = 0;
                //while (Integer.parseInt(objAsientos.get(a).getCodigo()) <= Integer.parseInt(frmLibroMayor.getObjFuAsientos().getCodigo())) {
                while (asientoiterator <= asientoselect) {
                    //loop para comparar hasta que asiento por cuenta se deben generar el libro mayor
                    ViewApplication.mensajeEstado.setText(null);
                    ViewApplication.mensajeEstado.setText("PROCESANDO CUENTA: " + objCuentas.get(y).getNombrecuenta() + " EN EL ASIENTO: " + objAsientos.get(a).getDescripcion());

                    String sql2 = "s2";
                    int ap = 0;
                    while (ap < asientosexplicitos.length) {
                        if (asientosexplicitos[ap].equals(objAsientos.get(a).getCodigo())) {
                            sql2 = "s1";
                            break;
                        }
                        ap += 1;
                    }

                    //condicional para si el asiento proviene de los que se exclueyen y se suman
                    //se diven en dos consultas plsql
                    ArrayList<BeanLibroMayor> objBeanLibroMayor = new ArrayList<BeanLibroMayor>();
                    if (sql2.equals("s1")) {
                        //sacar la glosa
                        String glosa;
                        try {
                            glosa = "Centralización Mes:" + xmes.name_Mes(num_mes) + " " + anio + " " + objAsientos.get(a).getDescripcion();
                            objBeanLibroMayor = LogicLibroMayor.libroMayorS1(glosa, frmLibroMayor.getObjEmpresa().getCodigo(), objCuentas.get(y).getCodigocuenta(), objAsientos.get(a).getCodigo(), anio, xmes.Mes(num_mes));
                            sql2 = "s2";
                        } catch (Exception ex) {
                            Exceptions.printStackTrace(ex);
                            System.out.println("Error al ejecutar la conuslta sql1.:" + ex.getMessage());
                        }

                    } else {
                        try {
                            String glosa = objAsientos.get(a).getDescripcion();
                            objBeanLibroMayor = LogicLibroMayor.libroMayorS2(glosa, frmLibroMayor.getObjEmpresa().getCodigo(), objCuentas.get(y).getCodigocuenta(), objAsientos.get(a).getCodigo(), anio, xmes.Mes(num_mes));

                        } catch (Exception ex) {
                            Exceptions.printStackTrace(ex);
                            System.out.println("Error al ejecutar la conuslta sql2:" + ex.getMessage());
                        }

                    }

                    //ejecutar el objmayor
                    int x = 0;
                    while (objBeanLibroMayor.size() > x) {
                        ban = 1;
                        BeanLibroMayor objmayordt = new BeanLibroMayor();
                        objmayordt.setAuxiliar(objBeanLibroMayor.get(x).getAuxiliar());
                        objmayordt.setVaucher(objBeanLibroMayor.get(x).getVaucher());
                        objmayordt.setFecha(objBeanLibroMayor.get(x).getFecha());
                        objmayordt.setC_central(objBeanLibroMayor.get(x).getC_central());
                        objmayordt.setGlosa(objBeanLibroMayor.get(x).getGlosa());
                        objmayordt.setDebe(objBeanLibroMayor.get(x).getDebe());
                        objmayordt.setHaber(objBeanLibroMayor.get(x).getHaber());

                        objmayordt.setCuenta(objCuentas.get(y).getNombrecuenta());
                        objmayordt.setCodigo_cuenta(objCuentas.get(y).getCodigocuenta());

                        objmayordt.setVienedebe(sumadebe);
                        objmayordt.setVienehaber(sumahaber);

                        objmayordt.setVienedeudor(deudor);
                        objmayordt.setVieneacreedor(acreedor);

                        //----
                        BigDecimal debe = (BigDecimal) objBeanLibroMayor.get(x).getDebe();
                        BigDecimal haber = (BigDecimal) objBeanLibroMayor.get(x).getHaber();

                        deudoracreedor = deudoracreedor.add(debe.subtract(haber));

                        if (deudoracreedor.compareTo(BigDecimal.ZERO) == 1) {
                            objmayordt.setDeudor(deudoracreedor);
                            objmayordt.setAcreedor(BigDecimal.ZERO);
                        } else {
                            objmayordt.setAcreedor(deudoracreedor.abs());
                            objmayordt.setDeudor(BigDecimal.ZERO);
                        }

                        dataLibroMayor.add(objmayordt);
                        x += 1;
                    }
                    a += 1;
                    asientoiterator++;
                }

                if (ban != 1 && (sumadebe.compareTo(BigDecimal.ZERO) == 1 || sumahaber.compareTo(BigDecimal.ZERO) == 1)) {
                    BeanLibroMayor objmayordt = new BeanLibroMayor();
                    objmayordt.setAuxiliar("");
                    objmayordt.setVaucher("");
                    objmayordt.setFecha(null); // error  aca
                    objmayordt.setC_central("");
                    objmayordt.setGlosa("");
                    objmayordt.setDebe(BigDecimal.ZERO);
                    objmayordt.setHaber(BigDecimal.ZERO);
                    objmayordt.setCuenta(objCuentas.get(y).getNombrecuenta());
                    objmayordt.setCodigo_cuenta(objCuentas.get(y).getCodigocuenta());
                    objmayordt.setVienedebe(sumadebe);
                    objmayordt.setVienehaber(sumahaber);
                    objmayordt.setVienedeudor(deudor);
                    objmayordt.setVieneacreedor(acreedor);
                    objmayordt.setAcreedor(BigDecimal.ZERO);
                    objmayordt.setDeudor(BigDecimal.ZERO);
                    dataLibroMayor.add(objmayordt);
                }
                y += 1;
                cmbcuentaiter++;
            }

            if (!dataLibroMayor.getLibroMayor().isEmpty()) {
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE DEL LIBRO MAYOR POR MES GENERADO SATISFACTORIAMENTE!!");
                dataLibroMayorG = dataLibroMayor;

                panelLibroMayor.getEliminar().setEnabled(true);
                panelLibroMayor.getWord().setEnabled(true);
                panelLibroMayor.getActualizar().setEnabled(true);
                panelLibroMayor.getNuevo().setEnabled(true);
                panelLibroMayor.getGenerar().setEnabled(true);
            } else {
                panelLibroMayor.getGenerar().setEnabled(true);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("No hay datos para este año");
            }
        } else {
            ViewApplication.mensajeEstado.setText(null);
            ViewApplication.mensajeEstado.setText("Todos los campos son obligatorios!!");
        }
    }

    private void exportarLibroMayorAWord(File ruta) throws SQLException {
        Map parameters = new HashMap();
        ClassFecha xmes = new ClassFecha();
        try {
            if (ruta != null) {
                //mardar al reporte parametros
            parameters.put("P_PERIODO", xmes.name_Mes(frmLibroMayor.getCmbmes().getSelectedIndex()) + "del" + 2010 + frmLibroMayor.getTxtano().getText());
                parameters.put("name_mes", xmes.name_Mes(frmLibroMayor.getCmbmes().getSelectedIndex()));
                parameters.put("anio", frmLibroMayor.getTxtano().getText());
                parameters.put("name_empresa", frmLibroMayor.getObjEmpresa().getDescripcion());
                parameters.put("num_ruc", frmLibroMayor.getObjEmpresa().getRuc());

                Locale loc = new Locale("en", "US");
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptlibromayor = "/com/contab/report/rptlibromayor2.jasper";
                Exportar ex = new Exportar(ruta, parameters, "docx", dataLibroMayorG, rutarptlibromayor);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE DEL LIBRO MAYOR EXPORTADO SATISFACTORIAMENTE!!");
                ex.show();
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            System.out.println("El error se debe al convertir el numero del mes seleccionado al formato de la tabla consultada!!:" + ex.getMessage());
        }
    }

    private void exportarLibroMayorATexto(File ruta) throws SQLException {
        Map parameters = new HashMap();
        ClassFecha xmes = new ClassFecha();
        try {
            if (ruta != null) {
                //mardar al reporte parametros
                   parameters.put("P_PERIODO", xmes.name_Mes(frmLibroMayor.getCmbmes().getSelectedIndex()) + "del" + 2010 + frmLibroMayor.getTxtano().getText());
                parameters.put("name_mes", xmes.name_Mes(frmLibroMayor.getCmbmes().getSelectedIndex()));
                parameters.put("anio", frmLibroMayor.getTxtano().getText());
                parameters.put("name_empresa", frmLibroMayor.getObjEmpresa().getDescripcion());
                parameters.put("num_ruc", frmLibroMayor.getObjEmpresa().getRuc());

                Locale loc = new Locale("en", "US");
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptlibromayor = "/com/contab/report/rptlibromayor2.jasper";
                Exportar ex = new Exportar(ruta, parameters, "txt", dataLibroMayorG, rutarptlibromayor);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE DEL LIBRO MAYOR EXPORTADO SATISFACTORIAMENTE!!");
                ex.show();
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            System.out.println("El error se debe al convertir el numero del mes seleccionado al formato de la tabla consultada!!:" + ex.getMessage());
        }
    }

    private void exportarLibroMayorAExcel(File ruta) throws SQLException {
        Map parameters = new HashMap();
        ClassFecha xmes = new ClassFecha();
        try {
            if (ruta != null) {
                //mardar al reporte parametros
                parameters.put("P_PERIODO", xmes.name_Mes(frmLibroMayor.getCmbmes().getSelectedIndex()) + "del" + 2010 + frmLibroMayor.getTxtano().getText());
                parameters.put("name_mes", xmes.name_Mes(frmLibroMayor.getCmbmes().getSelectedIndex()));
                parameters.put("anio", frmLibroMayor.getTxtano().getText());
                parameters.put("name_empresa", frmLibroMayor.getObjEmpresa().getDescripcion());
                parameters.put("num_ruc", frmLibroMayor.getObjEmpresa().getRuc());

                Locale loc = new Locale("en", "US");
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptlibromayor = "/com/contab/report/rptlibromayor2.jasper";
                Exportar ex = new Exportar(ruta, parameters, "xlsx", dataLibroMayorG, rutarptlibromayor);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE DEL LIBRO MAYOR EXPORTADO SATISFACTORIAMENTE!!");
                ex.show();
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            System.out.println("El error se debe al convertir el numero del mes seleccionado al formato de la tabla consultada!!:" + ex.getMessage());
        }
    }

    private void exportarLibroMayorAPDF(File ruta) throws SQLException {
        Map parameters = new HashMap();
        ClassFecha xmes = new ClassFecha();
        try {
            if (ruta != null) {
                //mardar al reporte parametros
                   parameters.put("P_PERIODO", xmes.name_Mes(frmLibroMayor.getCmbmes().getSelectedIndex()) + " del "  + frmLibroMayor.getTxtano().getText());
                parameters.put("name_mes", xmes.name_Mes(frmLibroMayor.getCmbmes().getSelectedIndex()));
                parameters.put("anio", frmLibroMayor.getTxtano().getText());
                parameters.put("name_empresa", frmLibroMayor.getObjEmpresa().getDescripcion());
                parameters.put("num_ruc", frmLibroMayor.getObjEmpresa().getRuc());

                Locale loc = new Locale("en", "US");
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutarptlibromayor = "/com/contab/report/rptlibromayor2.jasper";
                Exportar ex = new Exportar(ruta, parameters, "pdf", dataLibroMayorG, rutarptlibromayor);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE DEL LIBRO MAYOR EXPORTADO SATISFACTORIAMENTE!!");
                ex.show();
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            System.out.println("El error se debe al convertir el numero del mes seleccionado al formato de la tabla consultada!!:" + ex.getMessage());
        }
    }

    private void exportarLibroCajaAPDF() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (panelRptLibroCaja.getEmpresa().getModel())).getElement(panelRptLibroCaja.getEmpresa().getSelectedIndex());

        BeanBanco objBanco = ((ComboModelCuentaBanco) (panelRptLibroCaja.getCuentaBanco().getModel())).getElement(panelRptLibroCaja.getCuentaBanco().getSelectedIndex());

        LogicCaja caja = new LogicCaja();
        int inicioM = panelRptLibroCaja.getInicioFecha().getSelectedIndex();
        // int finM = panelRptLibroCaja.getFinFecha().getSelectedIndex();
        String empresas = objEmpresa.getDescripcion();
        String ruc = objEmpresa.getRuc();
        String compania = objEmpresa.getCodigo();


        String ano = panelRptLibroCaja.getTxtAno().getText();
        int tipo = panelRptLibroCaja.getCmbImpresora().getSelectedIndex();
        String tipoR = "";
        if (tipo == 0) {
            tipoR = "C";
        } else {
            tipoR = "L";
        }

        String descIMes = panelRptLibroCaja.getInicioFecha().getSelectedItem().toString();
        //    String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        String cuentaBanco = objBanco.getNumCuenta();
        String descripcion = objBanco.getCuentaBanco();
        String center = objBanco.getCodigoCentro();
        String centro1 = objBanco.getCenter();


        try {
            File archivo = seleccionarArchivo(filterPDF, "LibroCaja", "pdf");
            LogicCaja.listaDeEmpresa(empresas, ruc, compania, inicioM, inicioM, descIMes, descIMes, ano, cuentaBanco, descripcion, center, tipoR, archivo, "pdf");

            // LogicCaja.listaDeEmpresa(empresas, ruc, compania, inicioM, ano,cuentaBanco,descripcion, archivo);
        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause() + ex.getLocalizedMessage());
        }
    }

    private void exportarLibroCajaAEXCEL() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (panelRptLibroCaja.getEmpresa().getModel())).getElement(panelRptLibroCaja.getEmpresa().getSelectedIndex());

        BeanBanco objBanco = ((ComboModelCuentaBanco) (panelRptLibroCaja.getCuentaBanco().getModel())).getElement(panelRptLibroCaja.getCuentaBanco().getSelectedIndex());

        LogicCaja caja = new LogicCaja();
        int inicioM = panelRptLibroCaja.getInicioFecha().getSelectedIndex();
        // int finM = panelRptLibroCaja.getFinFecha().getSelectedIndex();
        String empresas = objEmpresa.getDescripcion();
        String ruc = objEmpresa.getRuc();
        String compania = objEmpresa.getCodigo();

        String ano = panelRptLibroCaja.getTxtAno().getText();
        String descIMes = panelRptLibroCaja.getInicioFecha().getSelectedItem().toString();
        //    String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        int tipo = panelRptLibroCaja.getCmbImpresora().getSelectedIndex();
        String tipoR = "";
        if (tipo == 0) {
            tipoR = "C";
        } else {
            tipoR = "L";
        }
        System.out.println("preuba------" + tipoR);
        String cuentaBanco = objBanco.getNumCuenta();
        String descripcion = objBanco.getCuentaBanco();
        String center = objBanco.getCodigoCentro();
        try {
            File archivo = seleccionarArchivo(filterXLS, "LibroCaja", "xlsx");
            LogicCaja.listaDeEmpresa(empresas, ruc, compania, inicioM, inicioM, descIMes, descIMes, ano, cuentaBanco, descripcion, center, tipoR, archivo, "xlsx");
            // LogicCaja.listaDeEmpresa(empresas, ruc, compania, inicioM, ano,cuentaBanco,descripcion, archivo);
        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarLibroCajaADOC() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (panelRptLibroCaja.getEmpresa().getModel())).getElement(panelRptLibroCaja.getEmpresa().getSelectedIndex());

        BeanBanco objBanco = ((ComboModelCuentaBanco) (panelRptLibroCaja.getCuentaBanco().getModel())).getElement(panelRptLibroCaja.getCuentaBanco().getSelectedIndex());

        LogicCaja caja = new LogicCaja();
        int inicioM = panelRptLibroCaja.getInicioFecha().getSelectedIndex();
        // int finM = panelRptLibroCaja.getFinFecha().getSelectedIndex();
        String empresas = objEmpresa.getDescripcion();
        String ruc = objEmpresa.getRuc();
        String compania = objEmpresa.getCodigo();

        String ano = panelRptLibroCaja.getTxtAno().getText();
        String descIMes = panelRptLibroCaja.getInicioFecha().getSelectedItem().toString();
        int tipo = panelRptLibroCaja.getCmbImpresora().getSelectedIndex();
        String tipoR = "";
        if (tipo == 0) {
            tipoR = "C";
        } else {
            tipoR = "L";
        }
        //    String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        String cuentaBanco = objBanco.getNumCuenta();
        String descripcion = objBanco.getCuentaBanco();
        String center = objBanco.getCodigoCentro();
        try {
            File archivo = seleccionarArchivo(filterDOC, "LibroCaja", "docx");

            LogicCaja.listaDeEmpresa(empresas, ruc, compania, inicioM, inicioM, descIMes, descIMes, ano, cuentaBanco, descripcion, center, tipoR, archivo, "docx");
            // LogicCaja.listaDeEmpresa(empresas, ruc, compania, inicioM, ano,cuentaBanco,descripcion, archivo);
        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarLibroCajaCorrienteAPDF() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (panelRptLibroCaja.getEmpresa().getModel())).getElement(panelRptLibroCaja.getEmpresa().getSelectedIndex());
        //   LogicCaja caja=new LogicCaja();
        BeanBanco objBanco = ((ComboModelCuentaBanco) (panelRptLibroCaja.getBtnBanco().getModel())).getElement(panelRptLibroCaja.getBtnBanco().getSelectedIndex());
        int inicioM = panelRptLibroCaja.getInicioFecha().getSelectedIndex();
        //  int finM = panelRptLibroCaja.getFinFecha().getSelectedIndex();
        String empresas = objEmpresa.getDescripcion();
        String ruc = objEmpresa.getRuc();
        String compania = objEmpresa.getCodigo();
        String center = objBanco.getCenter();

        String ano = panelRptLibroCaja.getTxtAno().getText();
        String descIMes = panelRptLibroCaja.getInicioFecha().getSelectedItem().toString();
        String Numcuenta = objBanco.getNumCuenta();
        String descripcioncuenta = objBanco.getCuentaBanco();
        String centro = objBanco.getCenter();
        int tipo = panelRptLibroCaja.getCmbImpresora().getSelectedIndex();
        System.out.println("centror ------" + centro);
        String tipoR = "";
        if (tipo == 0) {
            tipoR = "C";
        } else {
            tipoR = "L";
        }
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterPDF, "LibroCajaCorriente", "pdf");
            LogicCaja.listarCajaCoriente(empresas, ruc, compania, inicioM, inicioM, descIMes, descIMes, ano, Numcuenta, descripcioncuenta, centro, tipoR, archivo, "pdf");
            //  LogicCaja.listarCajaCoriente(empresas, ruc, compania, inicioM, inicioM, descIMes, descIMes, ano, archivo);
        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarLibroCajaCorrienteAEXCEL() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (panelRptLibroCaja.getEmpresa().getModel())).getElement(panelRptLibroCaja.getEmpresa().getSelectedIndex());
        //   LogicCaja caja=new LogicCaja();
        BeanBanco objBanco = ((ComboModelCuentaBanco) (panelRptLibroCaja.getBtnBanco().getModel())).getElement(panelRptLibroCaja.getBtnBanco().getSelectedIndex());
        int inicioM = panelRptLibroCaja.getInicioFecha().getSelectedIndex();
        //  int finM = panelRptLibroCaja.getFinFecha().getSelectedIndex();
        String empresas = objEmpresa.getDescripcion();
        String ruc = objEmpresa.getRuc();
        String compania = objEmpresa.getCodigo();
        int pruena = panelRptLibroCaja.getCmbImpresora().getSelectedIndex();


        String ano = panelRptLibroCaja.getTxtAno().getText();
        String descIMes = panelRptLibroCaja.getInicioFecha().getSelectedItem().toString();
        String Numcuenta = objBanco.getNumCuenta();
        String descripcioncuenta = objBanco.getCuentaBanco();
        int tipo = panelRptLibroCaja.getCmbImpresora().getSelectedIndex();
        String centro = objBanco.getCenter();
        System.out.println("centror ------" + centro);
        String tipoR = "";
        if (tipo == 0) {
            tipoR = "C";
        } else {
            tipoR = "L";
        }
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterXLS, "LibroCajaCorriente", "xlsx");
            LogicCaja.listarCajaCoriente(empresas, ruc, compania, inicioM, inicioM, descIMes, descIMes, ano, Numcuenta, descripcioncuenta, centro, tipoR, archivo, "xlsx");
            //  LogicCaja.listarCajaCoriente(empresas, ruc, compania, inicioM, inicioM, descIMes, descIMes, ano, archivo);
        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }

    private void exportarLibroCajaCorrienteADoc() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (panelRptLibroCaja.getEmpresa().getModel())).getElement(panelRptLibroCaja.getEmpresa().getSelectedIndex());
        //   LogicCaja caja=new LogicCaja();
        BeanBanco objBanco = ((ComboModelCuentaBanco) (panelRptLibroCaja.getBtnBanco().getModel())).getElement(panelRptLibroCaja.getBtnBanco().getSelectedIndex());
        int inicioM = panelRptLibroCaja.getInicioFecha().getSelectedIndex();
        //  int finM = panelRptLibroCaja.getFinFecha().getSelectedIndex();
        String empresas = objEmpresa.getDescripcion();
        String ruc = objEmpresa.getRuc();
        String compania = objEmpresa.getCodigo();
        int pruena = panelRptLibroCaja.getCmbImpresora().getSelectedIndex();
        System.out.println("preuba------" + pruena);

        String ano = panelRptLibroCaja.getTxtAno().getText();
        String descIMes = panelRptLibroCaja.getInicioFecha().getSelectedItem().toString();
        String Numcuenta = objBanco.getNumCuenta();
        String descripcioncuenta = objBanco.getCuentaBanco();
        int tipo = panelRptLibroCaja.getCmbImpresora().getSelectedIndex();
        String centro = objBanco.getCenter();
        System.out.println("centror ------" + centro);
        String tipoR = "";
        if (tipo == 0) {
            tipoR = "C";
        } else {
            tipoR = "L";
        }
        //  String descfMes = panelRptLibroCaja.getFinFecha().getSelectedItem().toString();
        try {
            File archivo = seleccionarArchivo(filterDOC, "LibroCajaCorriente", "docx");
            LogicCaja.listarCajaCoriente(empresas, ruc, compania, inicioM, inicioM, descIMes, descIMes, ano, Numcuenta, descripcioncuenta, centro, tipoR, archivo, "docx");
            //  LogicCaja.listarCajaCoriente(empresas, ruc, compania, inicioM, inicioM, descIMes, descIMes, ano, archivo);
        } catch (SQLException ex) {
            System.err.print(ex.getCause());
        } catch (Exception ex) {
            System.err.print(ex.getCause());
        }
    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == panelLibroDiario.getEliminar()) {
                WorkerDiarioPDF pdf = new WorkerDiarioPDF();
                pdf.execute();
            } else if (e.getSource() == panelLibroDiario.getActualizar()) {
                WorkerDiarioExcel xls = new WorkerDiarioExcel();
                xls.execute();
            } else if (e.getSource() == panelLibroCaja.getEliminar()) {
                int opcion = panelRptLibroCaja.getjComboBox1().getSelectedIndex();
                if (opcion == 0) {
                    //exportarLibroCajaAPDF();
                    WorkerCajaEfectivoPDF work = new WorkerCajaEfectivoPDF();
                    work.execute();
                } else {
                    //exportarLibroCajaCorrienteAPDF();
                    WorkerCajaCorrientePDF work1 = new WorkerCajaCorrientePDF();
                    work1.execute();
                }
            } else if (e.getSource() == panelLibroCaja.getActualizar()) {
                int opcion = panelRptLibroCaja.getjComboBox1().getSelectedIndex();
                if (opcion == 0) {
                    //exportarLibroCajaAPDF();
                    WorkerCajaEfectivoExcel work = new WorkerCajaEfectivoExcel();
                    work.execute();
                } else {
                    //exportarLibroCajaCorrienteAPDF();
                    WorkerCajaCorrienteExcel work1 = new WorkerCajaCorrienteExcel();
                    work1.execute();
                }
            } else if (e.getSource() == panelLibroCaja.getNuevo()) {
                int opcion = panelRptLibroCaja.getjComboBox1().getSelectedIndex();
                if (opcion == 0) {
                    //exportarLibroCajaAPDF();
                    WorkerCajaEfectivoDOC work = new WorkerCajaEfectivoDOC();
                    work.execute();
                } else {
                    //exportarLibroCajaCorrienteAPDF();
                    WorkerCajaCorrienteDoc work1 = new WorkerCajaCorrienteDoc();
                    work1.execute();
                }
            } else if (e.getSource() == panelLibroMayor.getGenerar()) {
                WorkerGenerarMayor xgenerar = new WorkerGenerarMayor();
                xgenerar.execute();
            } else if (e.getSource() == panelLibroMayor.getNuevo()) {
                WorkerMayorTexto txt = new WorkerMayorTexto();
                txt.execute();
            } else if (e.getSource() == panelLibroMayor.getEliminar()) {
                WorkerMayorPDF pdf = new WorkerMayorPDF();
                pdf.execute();
            } else if (e.getSource() == panelLibroMayor.getActualizar()) {
                WorkerMayorExcel xls = new WorkerMayorExcel();
                xls.execute();
            } else if (e.getSource() == panelLibroMayor.getWord()) {
                WorkerMayorWord xls = new WorkerMayorWord();
                xls.execute();
            }
        }
    };

    public JPanel getPanelMolde() {
        return panelMolde;
    }

    class WorkerDiarioExcel extends SwingWorker<Boolean, Integer> {

        public WorkerDiarioExcel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroDiarioAEXCEL();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerDiarioPDF extends SwingWorker<Boolean, Integer> {

        public WorkerDiarioPDF() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroDiarioAPDF();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerGenerarMayor extends SwingWorker<Boolean, Integer> {

        public WorkerGenerarMayor() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                generarlibromayor();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerMayorWord extends SwingWorker<Boolean, Integer> {

        public WorkerMayorWord() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                File ruta = seleccionarArchivo(filterDOC, "LibroMayor", "docx");
                exportarLibroMayorAWord(ruta);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerMayorTexto extends SwingWorker<Boolean, Integer> {

        public WorkerMayorTexto() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                File ruta = seleccionarArchivo(filterTXT, "LibroMayor", "txt");
                exportarLibroMayorATexto(ruta);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerMayorExcel extends SwingWorker<Boolean, Integer> {

        public WorkerMayorExcel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                File ruta = seleccionarArchivo(filterXLS, "LibroMayor", "xlsx");
                exportarLibroMayorAExcel(ruta);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerMayorPDF extends SwingWorker<Boolean, Integer> {

        public WorkerMayorPDF() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                File ruta = seleccionarArchivo(filterPDF, "LibroMayor", "pdf");
                exportarLibroMayorAPDF(ruta);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCajaCorrientePDF extends SwingWorker<Boolean, Integer> {

        public WorkerCajaCorrientePDF() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroCajaCorrienteAPDF();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCajaCorrienteExcel extends SwingWorker<Boolean, Integer> {

        public WorkerCajaCorrienteExcel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroCajaCorrienteAEXCEL();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCajaEfectivoPDF extends SwingWorker<Boolean, Integer> {

        public WorkerCajaEfectivoPDF() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroCajaAPDF();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCajaEfectivoDOC extends SwingWorker<Boolean, Integer> {

        public WorkerCajaEfectivoDOC() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroCajaADOC();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCajaCorrienteDoc extends SwingWorker<Boolean, Integer> {

        public WorkerCajaCorrienteDoc() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroCajaCorrienteADoc();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCajaEfectivoExcel extends SwingWorker<Boolean, Integer> {

        public WorkerCajaEfectivoExcel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                exportarLibroCajaAEXCEL();
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }
}
