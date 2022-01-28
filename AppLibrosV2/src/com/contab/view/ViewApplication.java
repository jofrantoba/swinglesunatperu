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
    public static ViewRptLibros frmLibroDiario;
    public static PanelRptBalanceCuenta14 frmBalanceCuenta14;
    public static PanelBalanceComprobacion frmBalanceCompr;
    public static PanelDetalleCuenta10 frmDetalleCuenta10;
    public static PanelSaldoDeCuenta frmSaldoDeCuenta;
    public static PanelMercaderiaCuenta21 frmMercaderiaCuenta21;
    public static PanelSaldoCuenta42 frmSaldoCuenta42;
    public static PanelLibroBalanceGeneral frmLibroBalanceGeneral;
    public static PanelCompra frmCompra;
    public static PanelVenta frmVenta;
    public static PanelKardex frmKardex;
    public static PanelLibroInventarioBalanceGP frmLibroInventarioBalanceGP;
    public static ViewEmpleados frmEmpleado;
    public static ViewTabla12 frmTabla12;
    public static PanelRptBalanceCuenta16 frmBalanceCuenta16;
    public static ViewTabla6 frmTabla6;
    public static ViewAsientoDoble frmAsientoDoble;
    public static ViewTabla10 frmTabla10;
    public static ViewPrueba frmPrueba;
    public static PanelCuenta40 frmCuenta40;
    public static PanelCuenta41 frmCuenta41;
    public static ViewTabla03 frmTabla03;
    public static ViewCuenta50 frmCuenta50;
    public static ViewCuenta31 frmCuenta31;
    public static PanelDaot frmDaot;
    public static PanelFormato313 formato313;
    public static panelRptCuenta47 formato314;
    public static PanelFormato319 formato319;
    public static PanelSaldoCuentaActivoFijo frmActivoFijo;
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
        arbol.setRowHeight(65);
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

    private void viewRptLibroDiario() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmLibroDiario.getPanelMolde());
        } catch (Exception ex) {
            frmLibroDiario = new ViewRptLibros();
            contenidoScrollPane.setViewportView(frmLibroDiario.getPanelMolde());
        }
    }

    private void viewRptLibroBalanceComprobacion() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmBalanceCompr.getPanelBalanceComprobacion());
        } catch (Exception ex) {
            frmBalanceCompr = new PanelBalanceComprobacion();
            contenidoScrollPane.setViewportView(frmBalanceCompr.getPanelBalanceComprobacion());
        }
    }

    private void viewSaldoDeCuenta() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmSaldoDeCuenta.getSaldoDeCuenta());
        } catch (Exception ex) {
            frmSaldoDeCuenta = new PanelSaldoDeCuenta();
            contenidoScrollPane.setViewportView(frmSaldoDeCuenta.getSaldoDeCuenta());
        }
    }

    private void viewRptCuenta46() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(formato313.getPanelCuenta46());
        } catch (Exception ex) {
            formato313 = new PanelFormato313();
            contenidoScrollPane.setViewportView(formato313.getPanelCuenta46());
        }
    }

    private void viewRptCuenta47() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(formato314.getPanelCuenta47());
        } catch (Exception ex) {
            formato314 = new panelRptCuenta47();
            contenidoScrollPane.setViewportView(formato314.getPanelCuenta47());
        }
    }

    private void viewRptBalanceCuenta14() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmBalanceCuenta14.getPanelCuenta14());
        } catch (Exception ex) {
            frmBalanceCuenta14 = new PanelRptBalanceCuenta14();
            contenidoScrollPane.setViewportView(frmBalanceCuenta14.getPanelCuenta14());
        }
    }

    private void viewRptBalanceCuenta16() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmBalanceCuenta16.getPanelCuenta14());
        } catch (Exception ex) {
            frmBalanceCuenta16 = new PanelRptBalanceCuenta16();
            contenidoScrollPane.setViewportView(frmBalanceCuenta16.getPanelCuenta14());
        }
    }

    private void viewDetalleCuenta10() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmDetalleCuenta10.getDetalleCuenta10());
        } catch (Exception ex) {
            frmDetalleCuenta10 = new PanelDetalleCuenta10();
            contenidoScrollPane.setViewportView(frmDetalleCuenta10.getDetalleCuenta10());
        }
    }

    private void viewMercaderiaCuenta21() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmMercaderiaCuenta21.getMercaderiaCuenta21());
        } catch (Exception ex) {
            frmMercaderiaCuenta21 = new PanelMercaderiaCuenta21();
            contenidoScrollPane.setViewportView(frmMercaderiaCuenta21.getMercaderiaCuenta21());
        }
    }

    private void viewSaldoCuenta42() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmSaldoCuenta42.getSaldoCuenta42());
        } catch (Exception ex) {
            frmSaldoCuenta42 = new PanelSaldoCuenta42();
            contenidoScrollPane.setViewportView(frmSaldoCuenta42.getSaldoCuenta42());
        }
    }

    private void viewCuenta40() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmCuenta40.getCuenta40());
        } catch (Exception ex) {
            frmCuenta40 = new PanelCuenta40();
            contenidoScrollPane.setViewportView(frmCuenta40.getCuenta40());
        }
    }

    private void viewCuenta41() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmCuenta41.getCuenta41());
        } catch (Exception ex) {
            frmCuenta41 = new PanelCuenta41();
            contenidoScrollPane.setViewportView(frmCuenta41.getCuenta41());
        }
    }

    private void viewKardex() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmKardex.getPanelKardex());
        } catch (Exception ex) {
            frmKardex = new PanelKardex();
            contenidoScrollPane.setViewportView(frmKardex.getPanelKardex());
        }
    }

    private void viewEmpAcc() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmEmpleado.getPanelMolde());
        } catch (Exception ex) {
            frmEmpleado = new ViewEmpleados();
            contenidoScrollPane.setViewportView(frmEmpleado.getPanelMolde());
        }
    }

    private void viewDaot() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmDaot.getPanel());
        } catch (Exception ex) {
            frmDaot = new PanelDaot();
            contenidoScrollPane.setViewportView(frmDaot.getPanel());
        }
    }

    private void ViewTabla12() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmTabla12.getPanelMolde());
        } catch (Exception ex) {
            frmTabla12 = new ViewTabla12();
            contenidoScrollPane.setViewportView(frmTabla12.getPanelMolde());
        }
    }

    private void ViewPrueba() throws SQLException {
        try {
            //contenidoScrollPane.setViewportView(frmPrueba.getPanelMolde());
            System.out.print("hola ...");
            //frmPrueba = new ViewPrueba(this,true);
            //  frmPrueba.setVisible(true);
            VentanaP window = new VentanaP();
            window.setVisible(true);
            window.setLocationRelativeTo(null);
            window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
            window.setSize(450, 400);


        } catch (Exception ex) {
            //  frmPrueba = new ViewPrueba(this,true);
            //frmPrueba.setVisible(true);
            //  contenidoScrollPane.setViewportView(frmPrueba.getPanelMolde());
        }
    }

    private void ViewTabla6() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmTabla6.getPanelMolde());
        } catch (Exception ex) {
            frmTabla6 = new ViewTabla6();
            contenidoScrollPane.setViewportView(frmTabla6.getPanelMolde());
        }
    }

    private void ViewTabla10() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmTabla10.getPanelMolde());
        } catch (Exception ex) {
            frmTabla10 = new ViewTabla10();
            contenidoScrollPane.setViewportView(frmTabla10.getPanelMolde());
        }
    }

    private void ViewTabla03() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmTabla03.getPanelMolde());
        } catch (Exception ex) {
            frmTabla03 = new ViewTabla03();
            contenidoScrollPane.setViewportView(frmTabla03.getPanelMolde());
        }
    }

    private void ViewAccionista() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmTabla03.getPanelMolde());
        } catch (Exception ex) {
            frmCuenta50 = new ViewCuenta50();
            contenidoScrollPane.setViewportView(frmCuenta50.getpanelCuenta50());
        }
    }

    private void ViewCuenta31() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmCuenta31.getpanelCuenta31());
        } catch (Exception ex) {
            frmCuenta31 = new ViewCuenta31();
            contenidoScrollPane.setViewportView(frmCuenta31.getpanelCuenta31());
        }
    }

    private void ViewLibroInventarioBalanceGP() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmLibroInventarioBalanceGP.getLibroInventarioBalanceGP());
        } catch (Exception ex) {
            frmLibroInventarioBalanceGP = new PanelLibroInventarioBalanceGP();
            contenidoScrollPane.setViewportView(frmLibroInventarioBalanceGP.getLibroInventarioBalanceGP());
        }
    }

    private void ViewLibroBalanceGeneral() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmLibroBalanceGeneral.getBalanceGenerals());
        } catch (Exception ex) {
            frmLibroBalanceGeneral = new PanelLibroBalanceGeneral();
            contenidoScrollPane.setViewportView(frmLibroBalanceGeneral.getBalanceGenerals());
        }
    }

    private void ViewLibroFormato319() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(formato319.getPanelFormato319());
        } catch (Exception ex) {
            formato319 = new PanelFormato319();
            contenidoScrollPane.setViewportView(formato319.getPanelFormato319());
        }
    }

    private void ViewLibroCompra() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmCompra.getMercaderiaCuenta21());
        } catch (Exception ex) {
            frmCompra = new PanelCompra();
            contenidoScrollPane.setViewportView(frmCompra.getMercaderiaCuenta21());
        }
    }

    private void ViewLibroVenta() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmVenta.getRegistroVenta());
        } catch (Exception ex) {
            frmVenta = new PanelVenta();
            contenidoScrollPane.setViewportView(frmVenta.getRegistroVenta());
        }
    }

    private void ViewAsientoDoble() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmAsientoDoble.getpanelAsientoDoble());
        } catch (Exception ex) {
            frmAsientoDoble = new ViewAsientoDoble();
            contenidoScrollPane.setViewportView(frmAsientoDoble.getpanelAsientoDoble());
        }
    }

    private void ViewActivoFijo() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(frmActivoFijo.getSaldoCuenta42());
        } catch (Exception ex) {
            frmActivoFijo = new PanelSaldoCuentaActivoFijo();
            contenidoScrollPane.setViewportView(frmActivoFijo.getSaldoCuenta42());
        }
    }
    TreeSelectionListener listenArbol = new TreeSelectionListener() {

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            TreePath tp = e.getPath();
            TreeEntry nodo = (TreeEntry) tp.getLastPathComponent();
            if (nodo.getIdentificadorUnico().equals("mcrptlibrodiario")) {
                try {
                    viewRptLibroDiario();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcCuenta46")) {
                try {
                    viewRptCuenta46();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcCuenta47")) {
                try {
                    viewRptCuenta47();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcbalancecuenta14")) {
                try {
                    viewRptBalanceCuenta14();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcbalancecomprobacion")) {
                try {
                    viewRptLibroBalanceComprobacion();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcsaldodecuenta")) {
                try {
                    viewSaldoDeCuenta();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcbalancecuenta16")) {
                try {
                    viewRptBalanceCuenta16();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcdetallecuenta10")) {
                try {
                    viewDetalleCuenta10();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcmercaderiacuenta21")) {
                try {
                    viewMercaderiaCuenta21();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcsaldocuenta42")) {
                try {
                    viewSaldoCuenta42();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcKardex")) {
                try {
                    viewKardex();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcempacc")) {
                try {
                    viewEmpAcc();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcdaot")) {
                try {
                    viewDaot();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcTabla12")) {
                try {
                    ViewTabla12();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcTabla6")) {
                try {
                    ViewTabla6();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcTabla10")) {
                try {
                    ViewTabla10();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }

            } else if (nodo.getIdentificadorUnico().equals("mcPrueba")) {
                try {
                    System.out.print("hola ...");
                    ViewPrueba();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }


            } else if (nodo.getIdentificadorUnico().equals("mcTabla03")) {
                try {
                    ViewTabla03();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcCuenta40")) {
                try {
                    viewCuenta40();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcCuenta41")) {
                try {
                    viewCuenta41();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcAccionista")) {
                try {
                    ViewAccionista();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcCuenta31")) {
                try {
                    ViewCuenta31();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcLibroInventarioBalanceGP")) {
                try {
                    ViewLibroInventarioBalanceGP();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcLibroIBalanceGeneral")) {
                try {
                    ViewLibroBalanceGeneral();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcFormato319")) {
                try {
                    ViewLibroFormato319();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcCompra")) {
                try {
                    ViewLibroCompra();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcVenta")) {
                try {
                    ViewLibroVenta();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("mcAsientodoble")) {
                try {
                    ViewAsientoDoble();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            } //ViewActivoFijo
            else if (nodo.getIdentificadorUnico().equals("mcActivoFijo")) {
                try {
                    ViewActivoFijo();
                } catch (SQLException ex) {
                    System.err.print(ex.getCause());
                }
            }
        }
    };
}
