package com.example.mscartoes.controller;

import com.example.mscartoes.entities.CartaoSaveRequest;
import com.example.mscartoes.model.Cartao;
import com.example.mscartoes.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cartoes")
public class CartoesController {
  @Autowired
  private final CartaoService service;

  public CartoesController(CartaoService service) {
    this.service = service;
  }

  @GetMapping
  public String status(){
    return "ok";
  }

  @PostMapping
  public ResponseEntity cadastre(@RequestBody CartaoSaveRequest request){
    Cartao cartao = request.toModel();
    service.save(cartao);
    return ResponseEntity.status(HttpStatus.CREATED).build();

  }
}
