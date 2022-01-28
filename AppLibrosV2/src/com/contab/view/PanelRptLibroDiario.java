package com.contab.view;

import com.contab.bean.BeanAsiento;
import com.contab.bean.BeanEmpresa;
import com.contab.combobox.ComboModelAsiento;
import com.contab.combobox.ComboModelEmpresas;
import com.contab.datasource.DataSourceLibroDiario;
import com.contab.logic.LogicAsiento;
import com.contab.logic.LogicDiario;
import com.contab.logic.LogicEmpresa;
import com.contab.util.JComboBoxAutocomplete;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelRptLibroDiario extends JPanel {

    private JLabel lblEmpresa = new JLabel("Empresa");
    private ComboModelEmpresas modelcbempresa = new ComboModelEmpresas();
    private JComboBoxAutocomplete cbEmpresa = new JComboBoxAutocomplete();
    private JDateChooser fechainicio;
    private JDateChooser fechafin;
    private JSpinnerDateEditor txtfechainicio = new JSpinnerDateEditor();
    private JSpinnerDateEditor txtfechafin = new JSpinnerDateEditor();
    private JLabel lblInicio = new JLabel("Inicio");
    private JLabel lblFin = new JLabel("Fin");
    private JLabel lblAsiento = new JLabel("Asiento");
    private ComboModelAsiento modelcbasiento = new ComboModelAsiento();
    private JComboBoxAutocomplete cbAsiento = new JComboBoxAutocomplete();

    public PanelRptLibroDiario() throws SQLException {
        initComponets();
    }

    private void initComponets() throws SQLException {
        fechainicio = new JDateChooser(txtfechainicio);
        fechafin = new JDateChooser(txtfechafin);
        fechainicio.setDate(new Date());
        fechafin.setDate(new Date());
        modelcbempresa.setData(LogicEmpresa.listaDeEmpresa());
        cbEmpresa.setModel(modelcbempresa);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(lblEmpresa, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(cbEmpresa, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(lblAsiento, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(cbAsiento, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(lblInicio, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(fechainicio, gbc);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(lblFin, gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.add(fechafin, gbc);
        cbEmpresa.addActionListener(actionListener);
        cbAsiento.addActionListener(actionListener);
    }

    public ArrayList generarLibroDario() throws SQLException {        
        try {
            ArrayList array=new ArrayList();
            BeanEmpresa emp = ((ComboModelEmpresas) cbEmpresa.getModel()).getElement(cbEmpresa.getSelectedIndex());
            BeanAsiento asiento = ((ComboModelAsiento) cbAsiento.getModel()).getElement(cbAsiento.getSelectedIndex());
            java.sql.Date ini = new java.sql.Date(fechainicio.getDate().getTime());
            java.sql.Date fin = new java.sql.Date(fechafin.getDate().getTime());
            String periodo="Desde "+ini+" Hasta "+fin;
            DataSourceLibroDiario ds = new DataSourceLibroDiario();
            System.out.println(asiento.getCodigo());
            if (asiento.getCodigo().equals("all")) {
                ArrayList<BeanAsiento> listAsientos = ((ComboModelAsiento) cbAsiento.getModel()).getData();
                BeanAsiento obj=listAsientos.get(0);                                
                listAsientos.remove(0);                                
                Iterator iterarAsientos = listAsientos.iterator();
                while (iterarAsientos.hasNext()) {                    
                    BeanAsiento objAsiento = (BeanAsiento) iterarAsientos.next();
                    LogicDiario.libroDiario(objAsiento.getCodigo(), objAsiento.getDescripcion(), ini, fin, emp.getCodigo(), ds);
                }
                listAsientos.add(0,obj);                                
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("PREPARANDOSE PARA GENERAR REPORTE");
                array.add(ds);
                array.add(periodo);
                array.add(emp);                
                return array;
            } else {
                LogicDiario.libroDiario(asiento.getCodigo(), asiento.getDescripcion(), ini, fin, emp.getCodigo(), ds);
                array.add(ds);
                array.add(periodo);
                array.add(emp);
                return array;
            }
        } catch (ArrayIndexOutOfBoundsException arr) {
            System.err.println(arr.getCause());
            return null;
        }
    }

    private void listarAsientos() throws SQLException {
        BeanEmpresa objEmpresa = ((ComboModelEmpresas) cbEmpresa.getModel()).getElement(cbEmpresa.getSelectedIndex());
        modelcbasiento.setData(LogicAsiento.listaDeAsientos(objEmpresa.getCodigo()));
        cbAsiento.setModel(modelcbasiento);
    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cbEmpresa) {
                Object item = cbEmpresa.getSelectedItem();
                cbEmpresa.getEditor().setItem(item);
                try {
                    listarAsientos();
                } catch (SQLException ex) {
                    System.err.println(ex.getCause());
                }
            } else if (e.getSource() == cbAsiento) {
                Object item = cbAsiento.getSelectedItem();
                cbAsiento.getEditor().setItem(item);
            }
        }
    };

    public JDateChooser getFechafin() {
        return fechafin;
    }

    public JDateChooser getFechainicio() {
        return fechainicio;
    }

    public JComboBoxAutocomplete getCbAsiento() {
        return cbAsiento;
    }

    public JComboBoxAutocomplete getCbEmpresa() {
        return cbEmpresa;
    }
}
