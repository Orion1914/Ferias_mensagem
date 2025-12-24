package com.ferias.primeiro_projeto_ferias.controller;

@RestController
@RequestMapping("/api")
public class MensagemController {

  private final MensagemService mensagemService;

  public MensagemController(MensagemService mensagemService) {
    this.mensagemService = mensagemService;
  }

  @GetMapping("/mensagem")
  public String mensagem() {
    return mensagemService.obterMensagem();
  }

}