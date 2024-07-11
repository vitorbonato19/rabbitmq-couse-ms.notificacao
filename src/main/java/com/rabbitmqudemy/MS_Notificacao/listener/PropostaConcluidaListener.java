package com.rabbitmqudemy.MS_Notificacao.listener;

import com.amazonaws.services.iot.model.StreamInfo;
import com.rabbitmqudemy.MS_Notificacao.domain.Proposta;
import com.rabbitmqudemy.MS_Notificacao.messages.MensagemDefault;
import com.rabbitmqudemy.MS_Notificacao.service.NotificacaoSMSService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    private final NotificacaoSMSService notificacaoSMSService;

    public PropostaConcluidaListener(NotificacaoSMSService notificacaoSMSService) {
        this.notificacaoSMSService = notificacaoSMSService;
    }

    @RabbitListener(queues = "rabbitmq.queue.proposta.concluida")
    public void PropostaConcluida(Proposta proposta) {
        if (proposta.getAprovada()) {
            String mensagemAprovada = String.format(MensagemDefault.PROPOSTA_CONCLUIDA);
            notificacaoSMSService.notificar(proposta.getUsuario().getTelefone(), mensagemAprovada);
        } else {
            String mensagemRecusada = String.format(MensagemDefault.PROPOSTA_RECUSADA);
            notificacaoSMSService.notificar(proposta.getUsuario().getTelefone(), mensagemRecusada);
        }
    }
}
