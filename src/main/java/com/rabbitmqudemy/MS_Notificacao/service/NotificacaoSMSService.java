package com.rabbitmqudemy.MS_Notificacao.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishBatchRequest;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoSMSService {

    private final AmazonSNS amazonSNS;

    public NotificacaoSMSService(AmazonSNS amazonSNS) {
        this.amazonSNS = amazonSNS;
    }


    public void notificar(String telefone, String mensagem) {
        PublishRequest publishRequest = new PublishRequest().withMessage(mensagem).withPhoneNumber(telefone);
        amazonSNS.publish(publishRequest);
    }
}
