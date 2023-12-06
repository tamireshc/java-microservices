package com.example.msavaliadordecredito.entities;

import lombok.Data;

@Data
public class ProtocoloSolicitacaoCartao {
    private String protocolo;

    public ProtocoloSolicitacaoCartao(String protocolo) {
        this.protocolo = protocolo;
    }
}
