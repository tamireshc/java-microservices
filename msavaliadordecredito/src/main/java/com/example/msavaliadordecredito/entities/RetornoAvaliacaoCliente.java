package com.example.msavaliadordecredito.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data

public class RetornoAvaliacaoCliente {
  private List<CartaoAprovado> cartoes;

  public RetornoAvaliacaoCliente(List<CartaoAprovado> cartoes) {
    this.cartoes = cartoes;
  }
}
