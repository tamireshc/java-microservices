package com.example.mscartoes.repository;

import com.example.mscartoes.model.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
  List<ClienteCartao> findByCpf(String cpf);
}
