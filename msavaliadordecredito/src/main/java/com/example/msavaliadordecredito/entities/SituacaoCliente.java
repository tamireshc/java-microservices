package com.example.msavaliadordecredito.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SituacaoCliente {
  private DadosCliente cliente;
  private List<CartaoCliente> cartoes;

  public SituacaoCliente(DadosCliente cliente) {
    this.cliente = cliente;
  }
}
