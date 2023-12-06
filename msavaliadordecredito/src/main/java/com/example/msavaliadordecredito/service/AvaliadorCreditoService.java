package com.example.msavaliadordecredito.service;

import com.example.msavaliadordecredito.clients.CartoesResourceClient;
import com.example.msavaliadordecredito.clients.ClienteResourceClient;
import com.example.msavaliadordecredito.entities.*;
import com.example.msavaliadordecredito.exceptions.DadosClienteNotFoundException;
import com.example.msavaliadordecredito.exceptions.ErroComunicacaoMicroservicesException;
import com.example.msavaliadordecredito.exceptions.ErroSolicitacaoCartaiException;
import com.example.msavaliadordecredito.mqueue.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliadorCreditoService {
    @Autowired
    private final ClienteResourceClient clienteResourceClient;
    @Autowired
    private final CartoesResourceClient cartoesResourceClient;
    @Autowired
    private final SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher;

    public AvaliadorCreditoService(ClienteResourceClient clienteResourceClient, CartoesResourceClient cartoesResourceClient, SolicitacaoEmissaoCartaoPublisher emissaoCartaoPublisher) {
        this.clienteResourceClient = clienteResourceClient;
        this.cartoesResourceClient = cartoesResourceClient;
        this.emissaoCartaoPublisher = emissaoCartaoPublisher;
    }

    public SituacaoCliente obterSituacaoCLiente(String cpf)
      throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        //obter dados msclientes
        //obter dados cartoes ms cartoes
        try {
            ResponseEntity<DadosCliente> responseEntityDadosCliente = clienteResourceClient.dadosCLiente(cpf);
            DadosCliente dadosCliente = responseEntityDadosCliente.getBody();

            ResponseEntity<List<CartaoCliente>> responseEntityListCartoesClient = cartoesResourceClient.getCartoesByCliente(cpf);
            List<CartaoCliente> listCaroesCliente = responseEntityListCartoesClient.getBody();

            SituacaoCliente dados = new SituacaoCliente(dadosCliente, listCaroesCliente);
            return dados;
        }catch (FeignException.FeignClientException e) {
            int status = e.status();
            if(status == HttpStatus.NOT_FOUND.value()){
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }catch (feign.RetryableException e){
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), e.status());
        }
    }

    public RetornoAvaliacaoCliente realizrAvaliacao(String cpf, long renda)
      throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException{

        try {
            ResponseEntity<DadosCliente> responseEntityDadosCliente = clienteResourceClient.dadosCLiente(cpf);
            DadosCliente dadosCliente = responseEntityDadosCliente.getBody();

            ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourceClient.getCartaoTendaAte(renda);
            List<Cartao> cartoes = cartoesResponse.getBody();
            var listaCartoesAprovados = cartoes.stream().map(cartao->{

                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                BigDecimal fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado =  fator.multiply(limiteBasico);

                CartaoAprovado cartaoAprovado = new CartaoAprovado();
                cartaoAprovado.setCartao(cartao.getNome());
                cartaoAprovado.setBandeira(cartao.getBandeira());
                cartaoAprovado.setLimiteAprovado(limiteAprovado);
                return  cartaoAprovado;
            }).collect(Collectors.toList());

            return  new RetornoAvaliacaoCliente(listaCartoesAprovados);

        }catch (FeignException.FeignClientException e) {
            int status = e.status();
            if(status == HttpStatus.NOT_FOUND.value()){
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }catch (feign.RetryableException e){
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), e.status());
        }
    }
    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaoCartao dados) throws ErroSolicitacaoCartaiException {
        try{
            emissaoCartaoPublisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        } catch (Exception e){
            throw new ErroSolicitacaoCartaiException(e.getMessage());
    }
    }
}
