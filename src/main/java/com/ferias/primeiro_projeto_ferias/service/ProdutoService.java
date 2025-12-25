package com.ferias.primeiro_projeto_ferias.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ferias.primeiro_projeto_ferias.model.Produto;
import com.ferias.primeiro_projeto_ferias.repository.ProdutoRepository;

@Service
public class ProdutoService {

  private final ProdutoRepository produtoRepository;

  public ProdutoService(ProdutoRepository produtoRepository) {
    this.produtoRepository = produtoRepository;
  }

  public List<Produto> listarProdutos() {
    return produtoRepository.findAll();
  }

}
