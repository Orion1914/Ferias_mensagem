package com.ferias.primeiro_projeto_ferias.repository;

import org.springframework.stereotype.Repository;

@Repository
public class MensagemRepository {

  public String obterMensagem() {
    return "Hello! This message is from the Repository.";
  }

}
