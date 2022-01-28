package com.contab.util;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;

import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public class Exportar {

    private File archivoDestino;
    private Map parameters = new HashMap();
    private String tipoExpor = "";
    private Connection cnx = null;
    private JRDataSource ds = null;
    private String rutaJasper;
    private String libro2="";

    public Exportar(File fileDestino, Map parametros, String tipoExportacion, Connection conexion, String rutaRptJasper) {
        archivoDestino = fileDestino;
        parameters = parametros;
        tipoExpor = tipoExportacion;
        cnx = conexion;
        rutaJasper = rutaRptJasper;
    }

    public Exportar(File fileDestino, Map parametros, String tipoExportacion, JRDataSource dataSource, String rutaRptJasper, String libro) {
        archivoDestino = fileDestino;
        parameters = parametros;
        tipoExpor = tipoExportacion;
        ds = dataSource;
        rutaJasper = rutaRptJasper;
        libro2 = libro;
    }

    public Exportar(File fileDestino, Map parametros, String tipoExportacion, JRDataSource dataSource, String rutaRptJasper) {
        archivoDestino = fileDestino;
        parameters = parametros;
        tipoExpor = tipoExportacion;
        ds = dataSource;
        rutaJasper = rutaRptJasper;
    }

    public void show() {
        try {

            OutputStream out = new FileOutputStream(archivoDestino);
            InputStream input = null;
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            InputStream entrada = this.getClass().getResourceAsStream(rutaJasper);
            JasperPrint jasper = null;
            if (cnx != null) {
                jasper = JasperFillManager.fillReport(entrada, parameters, cnx);
            } else if (ds != null) {
                jasper = JasperFillManager.fillReport(entrada, parameters, ds);
            }
            if (tipoExpor.equals("pdf")) {
                JRPdfExporter pdfexporter = new JRPdfExporter();
                pdfexporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasper);
                pdfexporter.setParameter(JRPdfExporterParameter.CHARACTER_ENCODING, "UTF-8");
               // pdfexporter.setParameter(JRPdfExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
                
                pdfexporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, out);
                pdfexporter.exportReport();
            } else if (tipoExpor.equals("xlsx")) {
                JRXlsxExporter xlsExporter = new JRXlsxExporter();
                
                if (libro2.equals("Caja")) {
                    xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasper);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE); // ajustar texto
                    xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                    xlsExporter.exportReport();

                } else {
                    xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasper);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                    xlsExporter.setParameter(JRXlsExporterParameter.CHARACTER_ENCODING, "UTF-8");
                    xlsExporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                    xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
                    xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                    xlsExporter.exportReport();
                }
            } else if (tipoExpor.equals("docx")) {
                JRDocxExporter docexporter = new JRDocxExporter();
                docexporter.setParameter(JRExporterParameter.JASPER_PRINT, jasper);
                docexporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
                //  docexporter.setParameter(JRExporterParameter., out);
                docexporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                docexporter.exportReport();
            } else if (tipoExpor.equals("txt")) {
                JRTextExporter txtexporter = new JRTextExporter();
                txtexporter.setParameter(JRTextExporterParameter.JASPER_PRINT, jasper);
                //exporter.setParameter(JRTextExporterParameter.OUTPUT_FILE, file);   
                
               // private Double CHAR_WIDTH = REPORT_WIDTH / MAX_CHAR_PER_ROW ;
               // CHAR_HEIGHT = REPORT_HEIGHT / MAX_CHAR_PER_COL 
                        //JRTextExporterParameter.
                
                txtexporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, new Integer(822));
                txtexporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, new Integer(606));
                //txtexporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(4));
                //txtexporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(8));                
                txtexporter.setParameter(JRTextExporterParameter.CHARACTER_ENCODING, "UTF-8");
                txtexporter.setParameter(JRTextExporterParameter.LINE_SEPARATOR, "\r\n");
                txtexporter.setParameter(JRTextExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
                txtexporter.setParameter(JRTextExporterParameter.BETWEEN_PAGES_TEXT, Boolean.TRUE);
                txtexporter.setParameter(JRTextExporterParameter.OUTPUT_STREAM, out);
                txtexporter.exportReport();
                
                //textExporter.setParameter (JRTextExporterParameter.ETWEEN_PAGES_TEXT, "\ f");
            }


            out.flush();
            out.close();
            Desktop.getDesktop().open(archivoDestino.getAbsoluteFile());
        } catch (IOException ex) {
            System.err.println(ex.getCause());
        } catch (JRException ex) {
            System.err.println(ex.getCause()+"sdsd"+ex.getLocalizedMessage()+ex.getMessage());
        }
    }
}
