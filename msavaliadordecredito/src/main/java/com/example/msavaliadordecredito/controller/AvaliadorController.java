package com.example.msavaliadordecredito.controller;

import com.example.msavaliadordecredito.entities.SituacaoCliente;
import com.example.msavaliadordecredito.service.AvaliadorCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<SituacaoCliente> consultaSituacaoCLiente(@RequestParam("cpf") String cpf){
    SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCLiente(cpf);
    return ResponseEntity.ok(situacaoCliente);
  }
}
