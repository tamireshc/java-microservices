package com.example.mscartoes.mqueue;

import com.example.mscartoes.entities.DadosSolicitacaoEmissaoCartao;
import com.example.mscartoes.model.Cartao;
import com.example.mscartoes.model.ClienteCartao;
import com.example.mscartoes.repository.CartaoRepository;
import com.example.mscartoes.repository.ClienteCartaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component

public class EmissaoCartaoSubscriber {
    @Autowired
    private final CartaoRepository cartaoRepository;
    @Autowired
    private final ClienteCartaoRepository clienteCartaoRepository;
    private static final Logger log = LoggerFactory.getLogger(EmissaoCartaoSubscriber.class);

    public EmissaoCartaoSubscriber(CartaoRepository repository, ClienteCartaoRepository clienteCartaoRepository) {
        this.cartaoRepository = repository;
        this.clienteCartaoRepository = clienteCartaoRepository;
    }

    @RabbitListener(queues ="${mq.queues.emissao-cartoes}" )
    public void receberSolicitacaoEmissao(@Payload String payload){
        var mapper = new ObjectMapper();
        try {
            DadosSolicitacaoEmissaoCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);
            Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();
            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.getCpf());
            clienteCartao.setLimite(dados.getLimiteLiberado());
            clienteCartaoRepository.save(clienteCartao);
        } catch (JsonProcessingException e) {
           log.error("erro ao receber solicitacao de emissao de cartao:{}",e.getMessage());
        }
    }
}
