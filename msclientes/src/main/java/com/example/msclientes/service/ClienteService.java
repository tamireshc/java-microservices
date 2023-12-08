package com.example.msclientes.service;

import com.example.msclientes.model.Cliente;
import com.example.msclientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class ClienteService {
     @Autowired
  private final ClienteRepository repository;

  public ClienteService(ClienteRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public Cliente save(Cliente cliente){
    return repository.save(cliente);
  }

  public Optional<Cliente> getByCPF(String cpf){
    return repository.findByCpf(cpf);
  }
}
