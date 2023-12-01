package com.example.mscartoes.service;

import com.example.mscartoes.model.Cartao;
import com.example.mscartoes.model.ClienteCartao;
import com.example.mscartoes.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service

public class CartaoService {
  @Autowired
  public final CartaoRepository repository;

  public CartaoService(CartaoRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public Cartao save(Cartao cartao){
    return repository.save(cartao);
  }

  public List<Cartao> getCartoesRendamenosIgual(Long renda){
    return repository.findByRendaLessThanEqual(BigDecimal.valueOf(renda));
  }


}
