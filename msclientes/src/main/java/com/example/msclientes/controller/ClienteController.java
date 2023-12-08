package com.example.msclientes.controller;

import com.example.msclientes.entities.ClienteSaveRequest;
import com.example.msclientes.model.Cliente;
import com.example.msclientes.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clientes")

public class ClienteController {


  private final ClienteService service;
  private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

  public ClienteController(ClienteService service) {
    this.service = service;
  }


  @GetMapping
  public String status() {
      log.info("emitindo log");
      log.info("informação:{}", "teste info");
      log.error("error:{}", "teste erro");
      log.warn("warn:{}", "teste warn");
    return "ok";

  }

  @PostMapping
  public ResponseEntity save(@RequestBody ClienteSaveRequest request) {
    Cliente cliente = request.toModel();
    service.save(cliente);
    URI hederLocation = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .query("cpf={cpf}")
      .buildAndExpand(cliente.getCpf())
      .toUri();

    return ResponseEntity.created(hederLocation).build();
  }

  @GetMapping(params = "cpf")
  public ResponseEntity dadosCLiente(@RequestParam("cpf") String cpf) {
    Optional<Cliente> cliente = service.getByCPF(cpf);
    if (cliente.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(cliente);
  }
}
