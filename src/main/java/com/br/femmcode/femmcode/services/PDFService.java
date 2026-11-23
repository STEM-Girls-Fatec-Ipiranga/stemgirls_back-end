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
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Service
public class PDFService {

    public byte[] criarPDF(String usuario, String evento, String data){
        try{
            PDDocument document = new PDDocument();
            PDRectangle customSize = new PDRectangle(1121, 785);
            PDPage page = new PDPage(customSize);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            ClassPathResource fundo = new ClassPathResource("pdfs/certificado_fundo.png");
            byte[] fundoBytes;
            try (InputStream input = fundo.getInputStream()) {
                fundoBytes = input.readAllBytes();
            }
            PDImageXObject image = PDImageXObject.createFromByteArray(
                    document,
                    fundoBytes,
                    "certificado_fundo.png"
            );
            contentStream.drawImage(image, 0, 0);

            String titulo = "Certificado";
            String participante = "Raquel";

            PDFont fonte = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);

            float tamanhoTitulo = 80;
            float tamanhoDescricao = 20;
            float tamanhoNome = 40;

            PDRectangle pageSize = page.getMediaBox();

            ArrayList<String> linhas = new ArrayList<>();
            linhas.add("Certificamos que " + usuario + " participou do evento");
            linhas.add(evento + " divulgado pela plataforma/projeto STEM GIRLS,");
            linhas.add("realizado no dia " + data + ". Parabenizamos pela participação exemplar");
            linhas.add("e desejamos contínuo sucesso em suas futuras iniciativas.");
            linhas.add("__________________________________");
            linhas.add("Responsável");

            ArrayList<Float> alturas = new ArrayList<>();
            alturas.add(420f);
            alturas.add(380f);
            alturas.add(340f);
            alturas.add(300f);
            alturas.add(200f);
            alturas.add(170f);

            float tituloX = centralizarTexto(titulo, tamanhoTitulo, fonte, pageSize);
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

            for (int i = 0; i < linhas.size(); i++) {
                float x = centralizarTexto(linhas.get(i), tamanhoDescricao, fonte, pageSize);

                contentStream.beginText();
                contentStream.setFont(fonte, tamanhoDescricao);
                contentStream.newLineAtOffset(x, alturas.get(i));
                contentStream.showText(linhas.get(i));
                contentStream.endText();
            }

            contentStream.close();

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
