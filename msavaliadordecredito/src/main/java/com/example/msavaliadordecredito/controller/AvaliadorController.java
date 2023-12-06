package com.example.msavaliadordecredito.controller;

import com.example.msavaliadordecredito.entities.*;
import com.example.msavaliadordecredito.exceptions.DadosClienteNotFoundException;
import com.example.msavaliadordecredito.exceptions.ErroComunicacaoMicroservicesException;
import com.example.msavaliadordecredito.exceptions.ErroSolicitacaoCartaiException;
import com.example.msavaliadordecredito.service.AvaliadorCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorController {
    @Autowired
    private final AvaliadorCreditoService avaliadorCreditoService;

    public AvaliadorController(AvaliadorCreditoService avaliadorCreditoService) {
        this.avaliadorCreditoService = avaliadorCreditoService;
    }

    @GetMapping
    public String status(){
        return "ok";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCLiente(@RequestParam("cpf") String cpf){
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCLiente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados){
        try {
            RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService
              .realizrAvaliacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroservicesException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("solicitacoes-cartao")
    public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados){
        try{
            ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoService.solicitarEmissaoCartao(dados);
            System.out.println("enviado cartao");
            return ResponseEntity.ok(protocoloSolicitacaoCartao);

        }catch (ErroSolicitacaoCartaiException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
