package com.br.femmcode.femmcode.services;

import com.br.femmcode.femmcode.models.Empresa;
import com.br.femmcode.femmcode.models.Notificacao;
import com.br.femmcode.femmcode.repositories.EmpresaRepository;
import com.br.femmcode.femmcode.repositories.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public Notificacao criarNotificacao(Empresa empresa){
        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem("Nova empresa aguardando aprovação: ");
        notificacao.setEmpresaId(empresa.getId());
        notificacao.setLida(false);
        return notificacaoRepository.save(notificacao);
    }

    public Notificacao encontrarNotificacao(String id){
        return notificacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
    }

    public Notificacao atualizarNotificacao(Notificacao notificacao){
        return notificacaoRepository.save(notificacao);
    }

    public List<Notificacao> listarPendentes() {
        return notificacaoRepository.findAll();
    }

    public void deletarNotificacao(String idEmpresa) {
        Notificacao notif = notificacaoRepository.findByEmpresaId(idEmpresa);
        if(notif!=null)
            notificacaoRepository.deleteById(notif.getId());
    }
}
