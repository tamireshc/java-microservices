package com.example.msavaliadordecredito.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoAprovado {
  private String cartao;
  private String bandeira;
  private BigDecimal limiteAprovado;

  public String getCartao() {
    return cartao;
  }

  public void setCartao(String cartao) {
    this.cartao = cartao;
  }

  public String getBandeira() {
    return bandeira;
  }

  public void setBandeira(String bandeira) {
    this.bandeira = bandeira;
  }

  public BigDecimal getLimiteAprovado() {
    return limiteAprovado;
  }

  public void setLimiteAprovado(BigDecimal limiteAprovado) {
    this.limiteAprovado = limiteAprovado;
  }
}
