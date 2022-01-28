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
            }
        });
    }
}
