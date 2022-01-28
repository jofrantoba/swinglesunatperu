/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

import com.lowagie.text.Rectangle;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.openide.util.Exceptions;

/**
 *
 * @author JONA
 */
public class Main {

    public static void main(String arg[]) throws Exception {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                ViewLogin GUI_login = new ViewLogin();
                GUI_login.setVisible(true);

                //   this.setVisible(false);
           /* ViewApplication app = new ViewApplication();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Rectangle r = new Rectangle(screenSize.width, screenSize.height - 40);
                // app.setBounds(r);
                app.setLocationRelativeTo(null);
                app.setVisible(true);
                app.setExtendedState(6);*/
               try {
                    try {
                        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    } catch (UnsupportedLookAndFeelException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
