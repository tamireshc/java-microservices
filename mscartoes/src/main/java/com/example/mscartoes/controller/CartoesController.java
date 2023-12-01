package com.example.mscartoes.controller;

import com.example.mscartoes.entities.CartaoSaveRequest;
import com.example.mscartoes.entities.CartoesPorClienteResponse;
import com.example.mscartoes.model.Cartao;
import com.example.mscartoes.model.ClienteCartao;
import com.example.mscartoes.service.CartaoService;
import com.example.mscartoes.service.ClienteCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
public class CartoesController {
  @Autowired
  private final CartaoService cartaoService;
  @Autowired
  private final ClienteCartaoService clienteCartaoService;

  public CartoesController(CartaoService service, ClienteCartaoService clienteCartaoService) {
    this.cartaoService = service;
    this.clienteCartaoService = clienteCartaoService;
  }

  @GetMapping
  public String status(){
    return "ok";
  }

  @PostMapping
  public ResponseEntity cadastre(@RequestBody CartaoSaveRequest request){
    Cartao cartao = request.toModel();
    cartaoService.save(cartao);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(params = "renda")
  public ResponseEntity<List<Cartao>> getCartaoTendaAte(@RequestParam("renda")Long renda){
    List<Cartao> list = cartaoService.getCartoesRendamenosIgual(renda);
    return  ResponseEntity.ok(list);
  }

  @GetMapping(params = "cpf")
  public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf){
    List<ClienteCartao> lista = clienteCartaoService.listCartoesByCpf(cpf);
    List<CartoesPorClienteResponse> resultList = lista.stream()
      .map(CartoesPorClienteResponse::fromModel)
      .collect(Collectors.toList());

    return ResponseEntity.ok(resultList);
  }
}
