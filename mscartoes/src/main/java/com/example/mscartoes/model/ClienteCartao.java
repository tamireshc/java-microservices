package com.example.mscartoes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Data
public class ClienteCartao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String cpf;
  @ManyToOne
  @JoinColumn(name="id_cartao")
  private Cartao cartao;
  private BigDecimal limite;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Cartao getCartao() {
    return cartao;
  }

  public void setCartao(Cartao cartao) {
    this.cartao = cartao;
  }

  public BigDecimal getLimite() {
    return limite;
  }

  public void setLimite(BigDecimal limite) {
    this.limite = limite;
  }
}
