package org.example;


import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.example.Cliente;
import org.example.ClienteDAO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.util.List;
import java.io.File;

public class ClientePDFGenerator {
    public static void generarPDF(String rutaArchivo) throws Exception {
        List<Cliente> clientes = ClienteDAO.obtenerTodos();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream content = new PDPageContentStream(document, page);
        content.beginText();
        content.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
        content.setLeading(14.5f);
        content.newLineAtOffset(50, 700);

        content.showText("Informe de Clientes");
        content.newLine();

        for (Cliente c : clientes) {
            content.showText("ID: " + c.getId() + " - Nombre: " + c.getNombre() + " " + c.getApellido());
            content.newLine();
        }

        content.endText();
        content.close();
        document.save(new File(rutaArchivo));
        document.close();
    }
}