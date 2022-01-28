package com.contab.view;

import com.contab.util.IconTreeModel;
import com.contab.util.IconTreeRenderer;
import com.contab.util.TreeEntry;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.netbeans.validation.api.ui.ValidationPanel;

public class ViewApplication extends JFrame {

    private JMenuBar menu = new JMenuBar();
    private JMenu menuLogistica = new JMenu("MODULO CONTABLE");
    private JMenuItem menuItemListaPrecios = new JMenuItem("Reportes de Libros");
    private IconTreeModel arbolContenido = new IconTreeModel();
    private IconTreeRenderer render = new IconTreeRenderer();
    private JTree arbol = new JTree();
    private JScrollPane arbolScrollPane = new JScrollPane(arbol);
    public static JScrollPane contenidoScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JSplitPane contenido = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, arbolScrollPane, contenidoScrollPane);
    private JToolBar barra = new JToolBar();
    private JLabel msgBienvenida = new JLabel("  Bienvenido: ");
    private JLabel lblusuario = new JLabel();
    private JPanel panelEstado = new JPanel();
    public static JLabel mensajeEstado = new JLabel("Barra de Estado");
    public static PanelGenerador frmGenerador;
    private ValidationPanel pnl = new ValidationPanel();
    private JPanel panelPrincipal = new JPanel();
    public static JDialog popupMensaje = new JDialog();
    private Locale locale = new Locale("es", "es_PE");
    public static Date hoy = new Date();
    public static JProgressBar progresbar = new JProgressBar();
    public static JButton btncancel = new JButton("Cancelar");
    private JPanel panelInicio = new JPanel();

    public ViewApplication() {
        construyendo();
    }

    private void construyendo() {
        Border borde = BorderFactory.createEtchedBorder();
        DateFormat fmt = new SimpleDateFormat("dd/MMMMM/yyyy hh:mm aaa", locale);
        String fecha = fmt.format(hoy);
        this.setContentPane(pnl);
        panelPrincipal.setLayout(new BorderLayout());
        contenidoScrollPane.getViewport().setLayout(new BorderLayout());
        menu.add(menuLogistica);
        menu.setBorder(borde);
        menuLogistica.add(menuItemListaPrecios);
        menuLogistica.setMnemonic('c');
        this.setJMenuBar(menu);
        arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        arbol.setModel(arbolContenido);
        arbol.setCellRenderer(render);
        arbol.setOpaque(false);
        arbol.setRootVisible(false);
        arbol.setRowHeight(25); //cambiar el tamaño
        arbol.setExpandsSelectedPaths(true);
        arbol.setBackground(null);
        arbol.addTreeSelectionListener(listenArbol);
        contenidoScrollPane.setViewportView(panelInicio);
        contenido.setContinuousLayout(true);
        contenido.setOneTouchExpandable(true);
        contenido.setDividerLocation(200);
        contenido.setPreferredSize(new Dimension(400, 200));
        Font fuente = new Font("Monospaced", Font.BOLD, 18);
        mensajeEstado.setFont(fuente);
        msgBienvenida.setFont(fuente);
        lblusuario.setText("Administrador de Contabilidad" + "  " + fecha);
        lblusuario.setFont(fuente);
        barra.add(msgBienvenida);
        barra.add(lblusuario);
        panelPrincipal.add(barra, "North");
        panelPrincipal.add(panelEstado, "South");
        panelPrincipal.add(contenido, "Center");
        panelEstado.add(mensajeEstado);
        panelEstado.add(btncancel);
        panelEstado.add(progresbar);
        progresbar.setValue(0);
        progresbar.setStringPainted(true);
        btncancel.setVisible(false);
        progresbar.setVisible(false);
        Border etchedBdr = BorderFactory.createEtchedBorder();
        panelEstado.setBorder(etchedBdr);
        barra.setFloatable(false);
        pnl.setInnerComponent(panelPrincipal);
        this.setIconImage(ViewLogin.picture.getIcono());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setTitle("Sistema Integrado - Libros Contables");
        pack();
        this.setLocationRelativeTo(null);
    }

    private void viewGenerador() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmGenerador.getPanelGenerador());
        } catch (Exception ex) {
            frmGenerador = new PanelGenerador();
            contenidoScrollPane.setViewportView(frmGenerador.getPanelGenerador());
        }
    }
    
     private void viewCorrelativo() throws SQLException {
         try {
            VentanaP window = new VentanaP();
            window.setVisible(true);
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
            window.setSize(450, 400);
        } catch (Exception ex) {
            
        }
    }
    TreeSelectionListener listenArbol = new TreeSelectionListener() {
//// mostrar los formularios
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            TreePath tp = e.getPath();
            TreeEntry nodo = (TreeEntry) tp.getLastPathComponent();
            if (nodo.getIdentificadorUnico().equals("mcGenerador")) {
                try {
                    viewGenerador();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            }else if (nodo.getIdentificadorUnico().equals("mcCorrelativo")) {
                try {
                    viewCorrelativo();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            }
        }
        
        
    };
}
