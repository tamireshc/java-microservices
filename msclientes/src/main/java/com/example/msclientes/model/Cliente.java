package com.example.msclientes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String cpf;
  private String nome;
  private Integer Idade;

  public Cliente(String cpf, String nome, Integer idade) {
    this.cpf = cpf;
    this.nome = nome;
    Idade = idade;
  }

  public String getCpf() {
    return cpf;
  }
}
