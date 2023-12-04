package com.example.msavaliadordecredito.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {
  private List<CartaoAprovado> cartoes;
}
