package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.models.Empresa;
import com.br.femmcode.femmcode.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

     public void sendPasswordResetEmail(Usuario usuario, String token) {
        try {
            String frontendUrl = "http://localhost:5173"; // coloque seu domínio aqui
            String linkRedefinicao = frontendUrl + "/redefinir-senha/" + token;

            String assunto = "Redefinição de Senha - StemGirls 💜";

            String mensagemHtml =
                    "<p>Olá, <strong>" + usuario.getNome() + "</strong>!</p>" +
                    "<p>Recebemos uma solicitação para redefinir sua senha.</p>" +
                    "<p>Clique no botão abaixo para criar uma nova senha:</p>" +
                    "<p><a href=\"" + linkRedefinicao + "\" " +
                    "style=\"display:inline-block;padding:10px 18px;background:#8a2be2;color:white;text-decoration:none;border-radius:8px;\">" +
                    "Redefinir Senha</a></p>" +
                    "<p>Se você não solicitou isso, apenas ignore este e-mail.</p>" +
                    "<p>Com carinho,<br>Equipe StemGirls 💫</p>";

            // Criar e-mail HTML
            jakarta.mail.internet.MimeMessage email = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(email, true);

            helper.setTo(usuario.getEmail());
            helper.setSubject(assunto);
            helper.setText(mensagemHtml, true); // <- true ATIVA HTML

            mailSender.send(email);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao enviar e-mail de redefinição.");
        }
    }

    public void sendEmpresaApprovalEmail(Empresa empresa) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nao-responda@femmcode.com");
        message.setTo("femmcode4@gmail.com");
        message.setSubject("STEM Girls - Nova empresa aguardando aprovação");
        message.setText(
                "Uma nova empresa se cadastrou e aguarda sua aprovação:\n\n" +
                        "Nome Empresa: " + empresa.getNome() + "\n" +
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
                        "Infelizmente sua solicitação de cadastro na plataforma Stem Girls foi reprovada. " +
                        "Caso deseje revisar seus dados e tentar novamente, entre em contato com nossa equipe.\n\n" +
                        "Atenciosamente,\nEquipe STEM Girls 💜",
                empresa.getNome()
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
                empresa.getNome()
        );

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(empresa.getEmail());
        email.setSubject(assunto);
        email.setText(mensagem);

        mailSender.send(email);
    }

}
