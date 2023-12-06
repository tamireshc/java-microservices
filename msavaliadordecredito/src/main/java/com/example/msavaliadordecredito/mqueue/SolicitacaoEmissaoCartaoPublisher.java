package com.example.msavaliadordecredito.mqueue;

import com.example.msavaliadordecredito.entities.DadosSolicitacaoEmissaoCartao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class SolicitacaoEmissaoCartaoPublisher {
    @Autowired
    private final RabbitTemplate rabbitTemplate;
    @Autowired
    private final Queue queueEmissaoCartoes;

    public SolicitacaoEmissaoCartaoPublisher(RabbitTemplate rabbitTemplate, Queue queueEmissaoCartoes) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueEmissaoCartoes = queueEmissaoCartoes;
    }

    public void solicitarCartao(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
        var json = convertIntoJson(dados);
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);

    }

    public String convertIntoJson(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dados);
        return json;
    }
}

