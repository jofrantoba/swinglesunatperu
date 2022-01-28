/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contab.view;

import javax.swing.*;
import java.awt.datatransfer.*;

public class ListaTransfer extends TransferHandler {

    public boolean canImport(TransferHandler.TransferSupport info) {


        if (!info.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            return false;
        }
        JList.DropLocation dl = (JList.DropLocation) info.getDropLocation();
        if (dl.getIndex() == -1) {
            return false;
        }
        return true;
    }

    public boolean importData(TransferHandler.TransferSupport info) {

        if (!info.isDrop()) {
            return false;
        }
        if (!info.isDataFlavorSupported(DataFlavor.imageFlavor)) {
            JOptionPane.showMessageDialog(null, "error");
            return false;
        }
        JList lista = (JList) info.getComponent();

        JList.DropLocation dl = (JList.DropLocation) info.getDropLocation();
        DefaultListModel listModel = new DefaultListModel();

        int index = dl.getIndex();
        boolean insert = dl.isInsert();
        Transferable t = info.getTransferable();
        String data;
        try {
            data = (String) t.getTransferData(DataFlavor.imageFlavor);
        } catch (Exception e) {
            return false;
        }

        String valorSoltado = "\'" + data + "\' soltado";

        if (dl.isInsert()) {
            if (dl.getIndex() == 0) {
                MostrarUbicacionDelSoltado(valorSoltado + "al inicio de la lista");

            } else if (dl.getIndex() >= lista.getModel().getSize()) {

                MostrarUbicacionDelSoltado(valorSoltado + "al fianl de la lista");
            } else {
                String valor1 = (String) lista.getModel().getElementAt(index - 1);
                String valor2 = (String) lista.getModel().getElementAt(index);
                MostrarUbicacionDelSoltado(valorSoltado + " entre " + valor1 + " y " + valor2);
            }
        } else {
            String valor = (String) lista.getModel().getElementAt(index);

            MostrarUbicacionDelSoltado(valorSoltado + "encima de " + valor);
        }

        if (insert) {
            listModel.add(index, data);

        } else {
            listModel.set(index, data);
        }
        
        return true;

    }

    private void MostrarUbicacionDelSoltado(final String string) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JOptionPane.showMessageDialog(null, "error");
            }
        });
    }
}
