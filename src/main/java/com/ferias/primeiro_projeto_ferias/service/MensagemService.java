package com.ferias.primeiro_projeto_ferias.service;

import org.springframework.stereotype.Service;
import com.ferias.primeiro_projeto_ferias.repository.MensagemRepository;

@Service
public class MensagemService {

  private final MensagemRepository mensagemRepository;

  public MensagemService(MensagemRepository mensagemRepository) {
    this.mensagemRepository = mensagemRepository;
  }

  public String obterMensagem() {
    return mensagemRepository.obterMensagem();
  }

}