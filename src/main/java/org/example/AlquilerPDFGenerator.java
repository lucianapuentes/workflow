package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.example.Alquiler;
import org.example.AlquilerDAO;

public class AlquilerPDFGenerator {
    public static void generarPDF(String rutaArchivo) throws Exception {
        List<Alquiler> alquileres = AlquilerDAO.obtenerTodos();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream content = new PDPageContentStream(document, page);
        content.beginText();
        content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
        content.setLeading(14.5f);
        content.newLineAtOffset(50, 700);

        content.showText("Informe de Alquileres");
        content.newLine();

        for (Alquiler a : alquileres) {

            content.showText(" - Numero de operacion: " + a.getNumOperacion() + ", Cliente ID: " + a.getIdCliente() + ", Código Escritorio: "+ a.getCodigoEscritorio()+", Fecha: " + a.getFecha() + ", Hora inicio:" + a.getHoraInicio() + ", Hora final: "+ a.getHoraFin()+ ", Precio: " + a.calcularPrecio());
            content.newLine();
        }

        content.endText();
        content.close();
        document.save(new File(rutaArchivo));
        document.close();
    }
}
