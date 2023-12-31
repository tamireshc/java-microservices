package com.example.mscartoes.repository;

import com.example.mscartoes.model.Cartao;
import com.example.mscartoes.model.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

  List<Cartao> findByRendaLessThanEqual(BigDecimal bigDecimal);
}
