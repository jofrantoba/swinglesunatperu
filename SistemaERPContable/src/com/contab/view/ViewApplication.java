package com.contab.view;

import com.contab.util.IconTreeModel;
import com.contab.util.IconTreeRenderer;
import com.contab.util.TreeEntry;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
    // public static JScrollPane contenidoScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
    private JList dataList = new JList();
    
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
        //
        arbol.setDragEnabled(true);
        arbol.setDropMode(DropMode.ON_OR_INSERT);
        //  arbol.setTransferHandler(new TreeTransferHandler());  
        String[] data = {"one", "two", "three", "four"};
        dataList.setListData(data);
        dataList.setDragEnabled(true);
        dataList.setDropMode(DropMode.ON_OR_INSERT);
        panelInicio.add(dataList);
        //
        contenidoScrollPane.createHorizontalScrollBar();
        // panelInicio.add(menu);
        // panelEstado.seT(lista);
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
        Font fuente = new Font("Monospaced", Font.BOLD, 18); //Fuente del t
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
    
    public synchronized void drop(DropTargetDropEvent dropTargetDropEvent) {
        System.out.print("hola");
    }
    
    private void viewFrmMantemientoCorrelativo() throws SQLException {
        try {
            FrmMantemientoCorrelativo window = new FrmMantemientoCorrelativo();
            window.setVisible(true);
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension ventana = getSize();
            window.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
            window.setSize(pantalla.width - 50, pantalla.height - 50);
        } catch (Exception ex) {
        }
    }
    
    private void viewFrmTipoDeCambio() throws SQLException {
        try {
            FrmTipoDeCambio window = new FrmTipoDeCambio();
            window.setVisible(true);
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension ventana = getSize();
            window.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
            window.setSize(pantalla.width - 50, pantalla.height - 50);
        } catch (Exception ex) {
        }
    }
    
    private void viewFrmMantenimientoDeCuentaContable() throws SQLException {
        try {
            frmMantenimientoDeCuentaContable window = new frmMantenimientoDeCuentaContable();
            window.setVisible(true);
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension ventana = getSize();
            window.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
            window.setSize(pantalla.width - 50, pantalla.height - 50);
        } catch (Exception ex) {
        }
    }  //

    private void viewFrmMantenimientoPeriodoContable() throws SQLException {
        try {
            FrmMantenimientoPeriodoContable window = new FrmMantenimientoPeriodoContable();
            window.setVisible(true);
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension ventana = getSize();
            window.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
            window.setSize(pantalla.width - 50, pantalla.height - 50);
        } catch (Exception ex) {
        }
    }  //FrmMantenimientoDeAuxiliares

    private void viewFrmMantenimientoDeAuxiliares() throws SQLException {
        try {
            FrmMantenimientoDeAuxiliares window = new FrmMantenimientoDeAuxiliares();
            window.setVisible(true);
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension ventana = getSize();
            window.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
            window.setSize(pantalla.width - 50, pantalla.height - 50);
        } catch (Exception ex) {
        }
    }  // FrmMantenimientoDeAsientoTipo

    private void viewFrmMantenimientoDeAsientoTipo() throws SQLException {
        try {
            FrmMantenimientoDeAsientoTipo window = new FrmMantenimientoDeAsientoTipo();
            window.setVisible(true);
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension ventana = getSize();
            window.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
            window.setSize(pantalla.width - 50, pantalla.height - 50);
        } catch (Exception ex) {
        }
    }  // FrmMantenimientoAutoridades
    
    
     private void viewFrmMantenimientoAutoridades() throws SQLException {
        try {
            FrmMantenimientoAutoridades window = new FrmMantenimientoAutoridades();
            window.setVisible(true);
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension ventana = getSize();
            window.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
            window.setSize(pantalla.width - 50, pantalla.height - 50);
        } catch (Exception ex) {
        }
    }  // mcFrmConsultaImpresionAsiento
     
     
     
     private void viewFrmConsultaImpresionAsiento() throws SQLException {
        try {
            FrmConsultaImpresionAsiento window = new FrmConsultaImpresionAsiento();
            window.setVisible(true);
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension ventana = getSize();
            window.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
            window.setSize(pantalla.width - 50, pantalla.height - 50);
        } catch (Exception ex) {
        }
    }  // FrmConsultaImpresionAsiento
     
      private void viewFrmSaldoBalanceGeneral() throws SQLException {
        try {
            FrmSaldoBalanceGeneral window = new FrmSaldoBalanceGeneral();
            window.setVisible(true);
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension ventana = getSize();
            window.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
            window.setSize(pantalla.width - 50, pantalla.height - 50);
        } catch (Exception ex) {
        }
    }  // FrmConsultaImpresionAsiento
        private void viewFrmConsultaSaldoPorAuxiliar() throws SQLException {
        try {
            FrmConsultaSaldoPorAuxiliar window = new FrmConsultaSaldoPorAuxiliar();
            window.setVisible(true);
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension ventana = getSize();
            window.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
            window.setSize(pantalla.width - 50, pantalla.height - 50);
        } catch (Exception ex) {
        }
    }  // FrmConsultaImpresionAsiento
    
    TreeSelectionListener listenArbol = new TreeSelectionListener() {
//// mostrar los formularios

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            TreePath tp = e.getPath();
            TreeEntry nodo = (TreeEntry) tp.getLastPathComponent();
            if (nodo.getIdentificadorUnico().equals("mcFrmMantemientoCorrelativo")) {
                try {
                    viewFrmMantemientoCorrelativo();
                } catch (SQLException ex) {
                    
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcTipoDeCambio")) {
                try {
                    viewFrmTipoDeCambio();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcFrmMantenimientoPeriodoContable")) {
                try {
                    viewFrmMantenimientoPeriodoContable();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcFrmMantenimientoDeCuentaContable")) {
                try {
                    viewFrmMantenimientoDeCuentaContable();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcFrmMantenimientoDeAuxiliares")) {
                try {
                    viewFrmMantenimientoDeAuxiliares();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcFrmMantenimientoDeAsientoTipo")) {
                try {
                    viewFrmMantenimientoDeAsientoTipo();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcFrmMantenimientoAutoridades")) {
                try {
                    viewFrmMantenimientoAutoridades();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
                ////*******falta****////////
            } else if (nodo.getIdentificadorUnico().equals("mcFrmConsultaImpresionAsiento")) {
                try {
                    viewFrmConsultaImpresionAsiento();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            }
            else if (nodo.getIdentificadorUnico().equals("mcFrmSaldoBalanceGeneral")) {
                try {
                    viewFrmSaldoBalanceGeneral();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            }
            
            else if (nodo.getIdentificadorUnico().equals("mcFrmConsultaSaldoPorAuxiliar")) {
                try {
                    viewFrmConsultaSaldoPorAuxiliar();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            }
            
            //
        }
    };
   //dragGestureEvent

}
