package com.br.femmcode.femmcode.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPasswordResetEmail(String to, String token) {
        // Monta o link que o usuário irá clicar no e-mail
        String resetLink = "http://localhost:5173/redefinir-senha/" + token;
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nao-responda@femmcode.com"); // Pode ser qualquer e-mail
        message.setTo(to);
        message.setSubject("STEM Grils - Redefinição de Senha");
        message.setText(
            "Olá,\n\n" +
            "Você solicitou a redefinição da sua senha. Por favor, clique no link abaixo para criar uma nova senha. Este link é válido por 1 hora.\n\n" + 
            resetLink + 
            "\n\nSe você não solicitou isso, por favor, ignore este e-mail.\n\n" +
            "Atenciosamente,\nEquipe STEM Grils"
        );
        
        // Envia o e-mail
        mailSender.send(message);
    }
}