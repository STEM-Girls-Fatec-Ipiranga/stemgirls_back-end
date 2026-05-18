package com.br.femmcode.femmcode.services;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PDFService {

    private static String PATH = "C:/Users/talit/OneDrive/Documentos/StemGirls/stemgirls_back-end/uploads/pdfs/";
    private static String PDF_NAME = "certificado.pdf";
    private static String PDF_FUNDO = "certificado_fundo.png";

    public void criarPDF(){
        try{
            PDDocument document = new PDDocument();
            PDRectangle customSize = new PDRectangle(1121, 785);
            PDPage page = new PDPage(customSize);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            Path imgPath = Paths.get(PATH+PDF_FUNDO);
            PDImageXObject image = PDImageXObject.createFromFile(imgPath.toAbsolutePath().toString(), document);
            contentStream.drawImage(image, 0, 0);

            String titulo = "Certificado";
            String participante = "Raquel";
            String descricao = "Certificamos que [Nome da Pessoa] participou do evento [nome do evento/atividade] divulgado pela plataforma/projeto STEM GIRLS, realizado no dia [data]. Parabenizamos pela participação exemplar e desejamos contínuo sucesso em suas futuras iniciativas.";

            PDFont fonte = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);

            float tamanhoTitulo = 100;
            float tamanhoDescricao = 30;

            PDRectangle pageSize = page.getMediaBox();

            float tituloX = centralizarTexto(titulo, tamanhoTitulo, fonte, pageSize);
            float descricaoX = centralizarTexto(descricao, tamanhoDescricao, fonte, pageSize);

            contentStream.setFont(fonte, tamanhoTitulo);
            contentStream.setFont(fonte, tamanhoDescricao);

            contentStream.beginText();

            contentStream.newLineAtOffset(tituloX, 620);
            contentStream.newLineAtOffset(descricaoX, 120);
            contentStream.showText(titulo);
            contentStream.showText(descricao);

            contentStream.endText();

            contentStream.close();

            document.save(new File(PATH+PDF_NAME));
            document.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public float centralizarTexto(String texto, float tamanhoFonte, PDFont fonte, PDRectangle pageSize) throws IOException {
        float larguraPagina = pageSize.getWidth();
        float larguraTexto = fonte.getStringWidth(texto) / 1000 * tamanhoFonte;

        return (larguraPagina - larguraTexto) / 2;
    }
}
