package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.models.Empresa;
import com.br.femmcode.femmcode.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /*original*/
    /*public void sendPasswordResetEmail(String to, String token) {
        String resetLink = "http://localhost:5173/redefinir-senha/" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("femmcode4@gmail.com"); // Pode ser qualquer e-mail
        message.setTo(to);
        message.setSubject("STEM Grils - Redefinição de Senha");
        message.setText(
                "Olá,\n\n" +
                        "Você solicitou a redefinição da sua senha. Por favor, clique no link abaixo para criar uma nova senha. Este link é válido por 1 hora.\n\n" +
                        resetLink +
                        "\n\nSe você não solicitou isso, por favor, ignore este e-mail.\n\n" +
                        "Atenciosamente,\nEquipe STEM Grils"
        );

        mailSender.send(message);
    }*/

    public void sendPasswordResetEmail(Usuario usuario, String token) {
        String assunto = "Redefinição de Senha - FemmCode 💜";
        String linkRedefinicao = "http://localhost:5173/reset-password?token=" + token;

        String mensagem = "Olá, " + usuario.getNomeCompleto() + "!\n\n"
                + "Recebemos uma solicitação para redefinir sua senha.\n"
                + "Clique no link abaixo para criar uma nova senha:\n"
                + linkRedefinicao + "\n\n"
                + "Se você não solicitou isso, ignore este e-mail.\n\n"
                + "Com carinho,\nEquipe FemmCode 💫";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(usuario.getEmail());
        email.setSubject(assunto);
        email.setText(mensagem);

        mailSender.send(email);
    }

    public void sendEmpresaApprovalEmail(Empresa empresa) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nao-responda@femmcode.com");
        message.setTo("femmcode4@gmail.com");
        message.setSubject("STEM Girls - Nova empresa aguardando aprovação");
        message.setText(
                "Uma nova empresa se cadastrou e aguarda sua aprovação:\n\n" +
                        "Nome Empresa: " + empresa.getNomeEmpresa() + "\n" +
                        "CNPJ: " + empresa.getCnpj() + "\n" +
                        "E-mail: " + empresa.getEmail() + "\n" +
                        "Telefone: " + empresa.getTelefone() + "\n" +
                        "ID da empresa: " + empresa.getId() + "\n\n" +
                        "Use o endpoint PUT /empresa/{id}/aprovar no backend para liberar o acesso."
        );
        mailSender.send(message);
    }

    public void sendEmpresaReprovadaEmail(Empresa empresa) {
        String assunto = "Status da sua solicitação - Reprovada";
        String mensagem = String.format(
                "Olá, %s!\n\n" +
                        "Infelizmente sua solicitação de cadastro na plataforma StTEM Girls foi reprovada. " +
                        "Caso deseje revisar seus dados e tentar novamente, entre em contato com nossa equipe.\n\n" +
                        "Atenciosamente,\nEquipe STEM Girls 💜",
                empresa.getNomeEmpresa()
        );

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(empresa.getEmail());
        email.setSubject(assunto);
        email.setText(mensagem);

        mailSender.send(email);
    }

    public void sendEmpresaAprovadaEmail(Empresa empresa) {
        String assunto = "Parabéns! Seu cadastro foi aprovado 💜";
        String mensagem = String.format(
                "Olá, %s!\n\n" +
                        "Analisamos seus dados e seu cadastro foi aprovado na plataforma STEM Girls! 🎉\n\n" +
                        "Agora você já pode acessar sua conta e aproveitar todos os recursos disponíveis para empresas.\n" +
                        "Acesse: http://localhost:5173/login\n\n" +
                        "Bem-vinda à nossa comunidade!\n\n" +
                        "Com carinho,\nEquipe STEM Girls 💫",
                empresa.getNomeEmpresa()
        );

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(empresa.getEmail());
        email.setSubject(assunto);
        email.setText(mensagem);

        mailSender.send(email);
    }

}
