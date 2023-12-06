package com.example.mscartoes.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoEmissaoCartao {
    private long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;

    public long getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(long idCartao) {
        this.idCartao = idCartao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getLimiteLiberado() {
        return limiteLiberado;
    }

    public void setLimiteLiberado(BigDecimal limiteLiberado) {
        this.limiteLiberado = limiteLiberado;
    }
}
