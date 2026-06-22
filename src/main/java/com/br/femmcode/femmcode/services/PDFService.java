package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.dtos.UsuarioDTO;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PDFService {
    private static String PATH = "C:/Users/talit/OneDrive/Documentos/StemGirls/stemgirls_back-end/uploads/pdfs/";
    private static String PDF_NAME = "certificado.pdf";
    private static String PDF_FUNDO = "certificado_fundo.png";

    public byte[] criarPDF(String usuario, String evento, String data){
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

            String linha1 = "Certificamos que " + usuario + " participou do evento";
            String linha2 = evento + " divulgado pela plataforma/projeto STEM GIRLS,";
            String linha3 = "realizado no dia " + data + ". Parabenizamos pela participação exemplar";
            String linha4 = "e desejamos contínuo sucesso em suas futuras iniciativas.";
            String linha5 = "__________________________________";
            String linha6 = "Responsável";
            PDFont fonte = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);

            float tamanhoTitulo = 80;
            float tamanhoDescricao = 20;
            float tamanhoNome = 40;

            PDRectangle pageSize = page.getMediaBox();

            float tituloX = centralizarTexto(titulo, tamanhoTitulo, fonte, pageSize);
            float primeiraLinhaX = centralizarTexto(linha1, tamanhoDescricao, fonte, pageSize);
            float segundaLinhaX = centralizarTexto(linha2, tamanhoDescricao, fonte, pageSize);
            float terceiraLinhaX = centralizarTexto(linha3, tamanhoDescricao, fonte, pageSize);
            float quartaLinhaX = centralizarTexto(linha4, tamanhoDescricao, fonte, pageSize);
            float quintaLinhaX = centralizarTexto(linha5, tamanhoDescricao, fonte, pageSize);
            float sextaLinhaX = centralizarTexto(linha6, tamanhoDescricao, fonte, pageSize);

            float nomeUsuario = centralizarTexto(usuario, tamanhoNome, fonte, pageSize);

            contentStream.beginText();

            contentStream.setFont(fonte, tamanhoTitulo);

            contentStream.newLineAtOffset(tituloX, 620);
            contentStream.showText(titulo);

            contentStream.endText();

            contentStream.beginText();

            contentStream.setFont(fonte, tamanhoNome);

            contentStream.newLineAtOffset(nomeUsuario, 500);
            contentStream.showText(usuario);

            contentStream.endText();

            contentStream.beginText();

            contentStream.setFont(fonte, tamanhoDescricao);

            contentStream.newLineAtOffset(primeiraLinhaX, 420);
            contentStream.showText(linha1);

            contentStream.endText();

            contentStream.beginText();

            contentStream.newLineAtOffset(segundaLinhaX, 380);
            contentStream.showText(linha2);

            contentStream.endText();

            contentStream.beginText();

            contentStream.newLineAtOffset(terceiraLinhaX, 340);
            contentStream.showText(linha3);

            contentStream.endText();

            contentStream.beginText();

            contentStream.newLineAtOffset(quartaLinhaX, 300);
            contentStream.showText(linha4);

            contentStream.endText();

            contentStream.beginText();

            contentStream.newLineAtOffset(quintaLinhaX, 200);
            contentStream.showText(linha5);

            contentStream.endText();

            contentStream.beginText();

            contentStream.newLineAtOffset(sextaLinhaX, 170);
            contentStream.showText(linha6);

            contentStream.endText();

            contentStream.close();

            //document.save(new File(PATH+PDF_NAME));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);

            document.close();

            return baos.toByteArray();

        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public float centralizarTexto(String texto, float tamanhoFonte, PDFont fonte, PDRectangle pageSize) throws IOException {
        float larguraPagina = pageSize.getWidth();
        float larguraTexto = fonte.getStringWidth(texto) / 1000 * tamanhoFonte;

        return (larguraPagina - larguraTexto) / 2;
    }
}
