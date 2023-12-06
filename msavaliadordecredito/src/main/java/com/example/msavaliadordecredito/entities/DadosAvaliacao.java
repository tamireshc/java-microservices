package com.example.msavaliadordecredito.entities;

import lombok.Data;

@Data
public class DadosAvaliacao {
  private String cpf;
  private Long renda;

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public Long getRenda() {
    return renda;
  }

  public void setRenda(Long renda) {
    this.renda = renda;
  }
}
