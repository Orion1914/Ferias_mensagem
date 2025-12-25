package com.ferias.primeiro_projeto_ferias.service;

import java.util.List;
import com.ferias.primeiro_projeto_ferias.exceptions.RecursoNaoEncontradoExceptions;

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

  public Produto buscarPorId(Long id) {
    return produtoRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoExceptions("Produto com ID " + id + " não encontrado"));
  }

  public Produto salvarProduto(Produto produto) {
    return produtoRepository.save(produto);
  }

  public void deletarProduto(Long id) {

    if (!produtoRepository.existsById(id)) {
      throw new RecursoNaoEncontradoExceptions("Produto com ID " + id + " não encontrado");
    }
    produtoRepository.deleteById(id);
  }

}
