package com.example.mscartoes.service;

import com.example.mscartoes.model.ClienteCartao;
import com.example.mscartoes.repository.ClienteCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteCartaoService {
  @Autowired
  private final ClienteCartaoRepository repository;
  public ClienteCartaoService(ClienteCartaoRepository repository) {
    this.repository = repository;
  }

  public List<ClienteCartao> listCartoesByCpf(String cpf){
    return repository.findByCpf(cpf);
  }

}
