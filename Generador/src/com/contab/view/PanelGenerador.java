package com.contab.view;

import com.contab.bean.BeanTablas;
import com.contab.combobox.ComboModelTabla;
import com.contab.generador.generador;
import com.contab.util.JComboBoxAutocomplete;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.openide.util.Exceptions;

public class PanelGenerador extends JPanel {

    private JLabel lblEmpresa = new JLabel("Tabla");
    private ComboModelTabla modelTabla = new ComboModelTabla();
    private JComboBoxAutocomplete cbTabla = new JComboBoxAutocomplete();
    private FrmPanel panelGenerador = new FrmPanel(ViewLogin.picture.getVenta(), "Generador de Codigo ");
    private JPanel panelMolde = new JPanel();
    private JButton btnAdd = new JButton(ViewLogin.picture.getIconAdd());

    public PanelGenerador() throws SQLException {
        initComponets();
    }

    private void initComponets() throws SQLException {
        panelGenerador.getNuevo().setIcon(ViewLogin.picture.getVenta());
        panelGenerador.getNuevo().setToolTipText("Generadar");
        panelGenerador.getPanelData().setVisible(false);
        panelGenerador.getPanelFormulario().setLayout(new BorderLayout());
        panelMolde.setLayout(new GridBagLayout());
        modelTabla.setData(generador.listaDeTablas());
        cbTabla.setModel(modelTabla);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(lblEmpresa, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(cbTabla, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        panelMolde.add(btnAdd, gbc);

        btnAdd.addActionListener(actionListener);
        panelGenerador.add(panelMolde);

    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnAdd) {
                BeanTablas objTablas = ((ComboModelTabla) (cbTabla.getModel())).getElement(cbTabla.getSelectedIndex());
                String archivoBean = "E:\\sistema-contable-final\\Generador\\src\\com\\contab\\bean\\Bean" + objTablas.getTabla() + ".java";
                File ficheroBean = new File(archivoBean);
                String archivoDao = "E:\\sistema-contable-final\\Generador\\src\\com\\contab\\dao\\Dao" + objTablas.getTabla() + ".java";
                File ficheroDao = new File(archivoDao);
                String archivoLogic = "E:\\sistema-contable-final\\Generador\\src\\com\\contab\\logic\\Logic" + objTablas.getTabla() + ".java";
                File ficheroLogic = new File(archivoLogic);

                String archivoVista = "E:\\sistema-contable-final\\Generador\\src\\com\\contab\\view\\NewJDialog.java";
                File ficheroVista = new File(archivoVista);

                String archivoTableModel = "E:\\sistema-contable-final\\Generador\\src\\com\\contab\\tablemodel\\Model" + objTablas.getTabla() + ".java";
                File ficheroTableModel = new File(archivoTableModel);

                String archivoScript = "E:\\sistema-contable-final\\Generador\\src\\com\\contab\\sql\\Script" + objTablas.getTabla() + ".sql";
                File ficheroScript = new File(archivoScript);
                try {
                    ///*********************Generacion delos Bean *************************/////
                    if (ficheroBean.createNewFile()) {
                        System.out.println("El fichero se ha creado correctamente Bean");
                    } else {
                        System.out.println("No ha podido ser creado el fichero Bean");
                    }
                    RandomAccessFile archivBean = new RandomAccessFile(archivoBean, "rw");
                    String codigoBean;
                    try {
                        codigoBean = generador.listarCamposBean(objTablas.getTabla());
                        archivBean.writeBytes(codigoBean);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }


                    ///////////////////fin Bean *********************///////////////////////////

                    ///*********************Generacion delos Dao *************************/////         

                    if (ficheroDao.createNewFile()) {
                        System.out.println("El fichero se ha creado correctamente Dao");
                    } else {
                        System.out.println("No ha podido ser creado el fichero Dao");
                    }
                    RandomAccessFile archivDao = new RandomAccessFile(archivoDao, "rw");
                    String codigoDao;
                    try {
                        codigoDao = generador.listarCamposDao(objTablas.getTabla());
                        archivDao.writeBytes(codigoDao);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }

                    ///////////////////fin Dao *********************///////////////////////////

                    ///*********************Generacion delos LOGIC *************************/////         

                    if (ficheroLogic.createNewFile()) {
                        System.out.println("El fichero se ha creado correctamente logic");
                    } else {
                        System.out.println("No ha podido ser creado el fichero logic");
                    }
                    RandomAccessFile archivLogic = new RandomAccessFile(archivoLogic, "rw");
                    String codigoLogic;
                    try {
                        codigoLogic = generador.listarCamposLogic(objTablas.getTabla());
                        archivLogic.writeBytes(codigoLogic);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }

                    ///////////////////fin logiv *********************///////////////////////////


                    ///*********************Generacion delos vista *************************/////         

                  /*  if (ficheroVista.createNewFile()) {
                        System.out.println("El fichero se ha creado correctamente vista");
                    } else {
                        System.out.println("No ha podido ser creado el fichero vista");
                    }
                    RandomAccessFile archivVista = new RandomAccessFile(archivoVista, "rw");
                    String codigoVista;
                    try {
                      codigoVista = generador.listarCamposVista(objTablas.getTabla());
                        archivVista.writeBytes(codigoVista);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }*/

                    ///*********************Generacion delos Table model *************************/////         

                    if (ficheroTableModel.createNewFile()) {
                        System.out.println("El fichero se ha creado correctamente table model");
                    } else {
                        System.out.println("No ha podido ser creado el fichero table model");
                    }
                    RandomAccessFile archivTableModel = new RandomAccessFile(archivoTableModel, "rw");
                    String codigoTableModel;
                    try {
                        codigoTableModel = generador.generarTableModel(objTablas.getTabla());
                        archivTableModel.writeBytes(codigoTableModel);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }



                    ///////////////////fin table model *********************///////////////////////////
                    ///*********************Generacion de lo Script *************************/////         
                    if (ficheroScript.createNewFile()) {
                        System.out.println("El fichero se ha creado correctamente Script");
                    } else {
                        System.out.println("No ha podido ser creado el fichero Script");
                    }
                    RandomAccessFile archivScript = new RandomAccessFile(archivoScript, "rw");
                    String codigoSci;
                    try {
                        codigoSci = generador.generarScript(objTablas.getTabla());
                        archivScript.writeBytes(codigoSci);
                    } catch (SQLException ex) {
                        Exceptions.printStackTrace(ex);
                    }

                    ///////////////////fin Script *********************///////////////////////////


                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            }
        }
    };

    public FrmPanel getPanelGenerador() {
        return panelGenerador;
    }
}
