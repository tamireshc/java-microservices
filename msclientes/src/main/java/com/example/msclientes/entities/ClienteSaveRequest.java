package com.example.msclientes.entities;

import com.example.msclientes.model.Cliente;
import lombok.Data;

@Data
public class ClienteSaveRequest {
  private String cpf;
  private String nome;
  private Integer idade;

  public Cliente toModel(){
    return new Cliente(cpf, nome, idade);
  }

}
