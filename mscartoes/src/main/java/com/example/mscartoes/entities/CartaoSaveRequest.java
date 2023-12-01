package com.example.mscartoes.entities;

import com.example.mscartoes.enuns.BandeiraCartao;
import com.example.mscartoes.model.Cartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoSaveRequest {
  private String nome;
  private BandeiraCartao bandeira;
  private BigDecimal renda;
  private BigDecimal limite;

  public Cartao toModel(){
    return new Cartao(nome,bandeira,renda,limite);
  }
}
