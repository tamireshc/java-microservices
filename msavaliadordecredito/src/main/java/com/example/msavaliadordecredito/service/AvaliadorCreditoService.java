package com.example.msavaliadordecredito.service;

import com.example.msavaliadordecredito.clients.ClienteResourceClient;
import com.example.msavaliadordecredito.entities.DadosCliente;
import com.example.msavaliadordecredito.entities.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AvaliadorCreditoService {

  @Autowired
  private final ClienteResourceClient clienteResourceClient;

  public AvaliadorCreditoService(ClienteResourceClient clienteResourceClient) {
    this.clienteResourceClient = clienteResourceClient;
  }

  public SituacaoCliente obterSituacaoCLiente(String cpf){
    //obter dados msclientes
    //obter dados cartoes ms cartoes
    ResponseEntity<DadosCliente> responseEntity = clienteResourceClient.dadosCLiente(cpf);
    DadosCliente dadosCliente = responseEntity.getBody();
    SituacaoCliente dados = new SituacaoCliente(dadosCliente);
    return dados;
  }

}
