package com.example.msavaliadordecredito.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoEmissaoCartao {
    private long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
