package com.contab.view;

import com.contab.bean.BeanEmpresa;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.datasource.DataSourceCuenta46;
import com.contab.logic.LogicCuenta46;
import com.contab.logic.LogicEmpresa;
import com.contab.util.Exportar;
import com.contab.util.JFileChooserModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sf.jasperreports.engine.JRParameter;
import org.openide.util.Exceptions;

public class PanelFormato313 extends javax.swing.JPanel {

    private ComboModelEmpresas modelempresa = new ComboModelEmpresas();
    FrmPanel panelexportacion = new FrmPanel(ViewLogin.picture.getIconCuenta46(), "Cuenta 46");
    private JFileChooserModel fileChooser = new JFileChooserModel();
    FileNameExtensionFilter filterDOC = new FileNameExtensionFilter("docx", "docx");
    FileNameExtensionFilter filterXLS = new FileNameExtensionFilter("xlsx", "xlsx");
    //private DataSourceCuenta46 DataCuenta46 = new DataSourceCuenta46();

    /** Creates new form PanelFormato313 */
    public PanelFormato313() throws SQLException {

        panelexportacion.getWord().setIcon(ViewLogin.picture.getIconword32());
        panelexportacion.getWord().setToolTipText("Exportar a Word");

        panelexportacion.getActualizar().setIcon(ViewLogin.picture.getIconExcel());
        panelexportacion.getActualizar().setToolTipText("Exportar a Excel");
        panelexportacion.getEliminar().setIcon(ViewLogin.picture.getIconPDF32());
        panelexportacion.getEliminar().setToolTipText("Expotar a pdf");
        panelexportacion.getPanelData().setVisible(false);
       

        //panelexportacion.getPanelFormulario().setLayout(new BorderLayout());
        initComponents();
        modelempresa.setData(LogicEmpresa.listaDeEmpresa());
        cmbEmpresa.setModel(modelempresa);
        panelexportacion.getPanelFormulario().add(this);
        panelexportacion.getWord().addActionListener(actionListener);
        panelexportacion.getNuevo().addActionListener(actionListener);
        panelexportacion.getActualizar().addActionListener(actionListener);
        panelexportacion.getEliminar().addActionListener(actionListener);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbEmpresa = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtano = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbMes = new javax.swing.JComboBox();

        cmbEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEmpresaActionPerformed(evt);
            }
        });

        jLabel1.setText(org.openide.util.NbBundle.getMessage(PanelFormato313.class, "PanelFormato313.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(PanelFormato313.class, "PanelFormato313.jLabel2.text")); // NOI18N

        txtano.setText(org.openide.util.NbBundle.getMessage(PanelFormato313.class, "PanelFormato313.txtano.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(PanelFormato313.class, "PanelFormato313.jLabel3.text")); // NOI18N

        cmbMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbEmpresa, 0, 140, Short.MAX_VALUE)
                    .addComponent(txtano, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void cmbEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEmpresaActionPerformed
}//GEN-LAST:event_cmbEmpresaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbEmpresa;
    private javax.swing.JComboBox cmbMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtano;
    // End of variables declaration//GEN-END:variables

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

    private void exportarCuenta46APDF(File ruta) throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cmbEmpresa.getModel())).getElement(cmbEmpresa.getSelectedIndex());
        int ano = Integer.parseInt(txtano.getText());
        int num_mes = cmbMes.getSelectedIndex() + 1;
        Map parameters = new HashMap();
        //ClassFecha xmes = new ClassFecha();
        DataSourceCuenta46 ds = LogicCuenta46.Cuenta46("46", ano, num_mes, objEmpresa.getCodigo());
        String mes =Integer.toString(num_mes);
        if(mes.length()==1){
           mes="0"+mes;
        }
        //ds.getCuenta46().get(0).getApellidosnombre();

        try {
            if (ruta != null) {
                //mardar al reporte parametros
                //parameters.put("RUC", xmes.name_Mes(this.getCmbmes().getSelectedIndex()));
                parameters.put("RUC", objEmpresa.getRuc());
                parameters.put("Empresa", objEmpresa.getDescripcion());
                 parameters.put("EJERCICIO", mes+"/" +ano);
                //parameters.put("Empresa", PanelFormato313.getTxtano().getText());
                //parameters.put("empresa", frmLibroMayor.getObjEmpresa().getDescripcion());
                //Locale loc = new Locale("en", "US");
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutaCuenta46 = "/com/contab/report/Formato313.jasper";
                // Exportar ex = new Exportar(ruta, parameters, "docx", ds, rutaCuenta46);
                Exportar ex = new Exportar(ruta, parameters, "pdf", ds, rutaCuenta46);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("FORMATO CUENTA 46 EXPORTADO SATISFACTORIAMENTE!!");
                ex.show();
                   JOptionPane.showMessageDialog(null, "REPORTE Cuenta 46 ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            System.out.println("El error se debe al convertir el numero del mes seleccionado al formato de la tabla consultada!!:" + ex.getMessage());
        }
    }

    private void exportarCuenta46AEXCEL(File ruta) throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cmbEmpresa.getModel())).getElement(cmbEmpresa.getSelectedIndex());
        int ano = Integer.parseInt(txtano.getText());
        int num_mes = cmbMes.getSelectedIndex() + 1;
        Map parameters = new HashMap();
        //ClassFecha xmes = new ClassFecha();
        DataSourceCuenta46 ds = LogicCuenta46.Cuenta46("46", ano, num_mes, objEmpresa.getCodigo());
        //ds.getCuenta46().get(0).getApellidosnombre();
        String mes =Integer.toString(num_mes);
        if(mes.length()==1){
           mes="0"+mes;
        }
        try {
            if (ruta != null) {
                //mardar al reporte parametros
                //parameters.put("RUC", xmes.name_Mes(this.getCmbmes().getSelectedIndex()));
                 parameters.put("EJERCICIO", mes+"/" +ano);
                parameters.put("RUC", objEmpresa.getRuc());
                parameters.put("Empresa", objEmpresa.getDescripcion());
                //parameters.put("Empresa", PanelFormato313.getTxtano().getText());
                //parameters.put("empresa", frmLibroMayor.getObjEmpresa().getDescripcion());
                //Locale loc = new Locale("en", "US");
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);
                String rutaCuenta46 = "/com/contab/report/Formato313.jasper";
                // Exportar ex = new Exportar(ruta, parameters, "docx", ds, rutaCuenta46);
                Exportar ex = new Exportar(ruta, parameters, "xlsx", ds, rutaCuenta46);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("FORMATO CUENTA 46 EXPORTADO SATISFACTORIAMENTE!!");
                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE Cuenta 46 ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            System.out.println("El error se debe al convertir el numero del mes seleccionado al formato de la tabla consultada!!:" + ex.getMessage());
        }
    }

    private void exportarCuenta46AWord(File ruta) throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) (cmbEmpresa.getModel())).getElement(cmbEmpresa.getSelectedIndex());
        int ano = Integer.parseInt(txtano.getText());
        int num_mes = cmbMes.getSelectedIndex() + 1;
        Map parameters = new HashMap();
        //ClassFecha xmes = new ClassFecha();
        DataSourceCuenta46 ds = LogicCuenta46.Cuenta46("46", ano, num_mes, objEmpresa.getCodigo());
        String mes =Integer.toString(num_mes);
        if(mes.length()==1){
           mes="0"+mes;
        }
        try {
            if (ruta != null) {
                //mardar al reporte parametros
                //parameters.put("RUC", xmes.name_Mes(this.getCmbmes().getSelectedIndex()));
                parameters.put("RUC", objEmpresa.getRuc());
                parameters.put("EJERCICIO", mes+"/" +ano);
                parameters.put("Empresa", objEmpresa.getDescripcion());
                //parameters.put("Empresa", PanelFormato313.getTxtano().getText());
                //parameters.put("empresa", frmLibroMayor.getObjEmpresa().getDescripcion());


                //Locale loc = new Locale("en", "US");
                parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

                String rutaCuenta46 = "/com/contab/report/Formato313.jasper";
                // Exportar ex = new Exportar(ruta, parameters, "docx", ds, rutaCuenta46);
                Exportar ex = new Exportar(ruta, parameters, "docx", ds, rutaCuenta46);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("FORMATO CUENTA 46 EXPORTADO SATISFACTORIAMENTE!!");
                ex.show();
                JOptionPane.showMessageDialog(null, "REPORTE Cuenta 46 ", "ALERT", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
            System.out.println("El error se debe al convertir el numero del mes seleccionado al formato de la tabla consultada!!:" + ex.getMessage());
        }
    }

    class WorkerBalanceComprobacionExcel extends SwingWorker<Boolean, Integer> {

        public WorkerBalanceComprobacionExcel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                File ruta = seleccionarArchivo(filterXLS, "Cuenta46", "xlsx");
                exportarCuenta46AEXCEL(ruta);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCuenta46Word extends SwingWorker<Boolean, Integer> {

        public WorkerCuenta46Word() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                File ruta = seleccionarArchivo(filterDOC, "Cuenta46", "docx");
                exportarCuenta46AWord(ruta);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCuenta46Excel extends SwingWorker<Boolean, Integer> {

        public WorkerCuenta46Excel() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                File ruta = seleccionarArchivo(filterDOC, "Cuenta46", "xlsx");
                exportarCuenta46AEXCEL(ruta);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    class WorkerCuenta46Pdf extends SwingWorker<Boolean, Integer> {

        public WorkerCuenta46Pdf() {
        }

        @Override
        protected Boolean doInBackground() {
            try {
                File ruta = seleccionarArchivo(filterDOC, "Cuenta46", "pdf");
                exportarCuenta46APDF(ruta);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == panelexportacion.getWord()) {
                WorkerCuenta46Word doc = new WorkerCuenta46Word();
                doc.execute();
            } else if (e.getSource() == panelexportacion.getEliminar()) {
                WorkerCuenta46Pdf pdf = new WorkerCuenta46Pdf();
                pdf.execute();
            } else if (e.getSource() == panelexportacion.getActualizar()) {
                WorkerCuenta46Excel xls = new WorkerCuenta46Excel();
                xls.execute();
            }
        }
    };

    public FrmPanel getPanelCuenta46() {
        return panelexportacion;
    }
}
