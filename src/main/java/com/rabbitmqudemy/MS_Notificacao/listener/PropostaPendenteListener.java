package com.rabbitmqudemy.MS_Notificacao.listener;

import com.rabbitmqudemy.MS_Notificacao.domain.Proposta;
import com.rabbitmqudemy.MS_Notificacao.messages.MensagemDefault;
import com.rabbitmqudemy.MS_Notificacao.service.NotificacaoSMSService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaPendenteListener {

    private final NotificacaoSMSService notificacaoSMSService;

    public PropostaPendenteListener(NotificacaoSMSService notificacaoSMSService) {
        this.notificacaoSMSService = notificacaoSMSService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void PropostaPendente(Proposta proposta) {
        String mensagem = String.format(MensagemDefault.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
        notificacaoSMSService.notificar(proposta.getUsuario().getTelefone(), mensagem);
    }
}
