package org.example;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;
import java.util.List;

public class InformePDFGenerator {

    public static void generarInformeCompleto(String rutaArchivo) throws IOException {
        List<Cliente> clientes = ClienteDAO.obtenerTodos();
        List<Alquiler> alquileres = AlquilerDAO.obtenerTodos();

        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 14);
            content.beginText();
            content.setLeading(16f);
            content.newLineAtOffset(50, 750);
            content.showText("Informe del Sistema de Alquileres");
            content.newLine();
            content.newLine();

            // Sección Clientes
            content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            content.showText("Clientes:");
            content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
            for (Cliente c : clientes) {
                content.newLine();
                content.showText(" - " + c.getId() + ": " + c.getNombre() + " " + c.getApellido() + ", " + c.getEmail());
            }

            content.newLine();
            content.newLine();

            // Sección Alquileres
            content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            content.showText("Alquileres:");
            content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
            for (Alquiler a : alquileres) {
                content.newLine();
                content.showText(" - Numero de operacion: " + a.getNumOperacion() + ", Cliente ID: " + a.getIdCliente() + ", Fecha: " + a.getFecha() + ", Hora inicio:" + a.getHoraInicio() + ", Hora final: "+ a.getHoraFin()+ ", Precio: " + a.getPrecio());
            }

            content.endText();
            content.close();

            doc.save(rutaArchivo);
        }
    }
}

