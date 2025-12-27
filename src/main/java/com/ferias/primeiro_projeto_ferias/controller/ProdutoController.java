package com.ferias.primeiro_projeto_ferias.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.ferias.primeiro_projeto_ferias.service.ProdutoService;
import com.ferias.primeiro_projeto_ferias.model.Produto;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;






@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  private final ProdutoService produtoService;

  public ProdutoController(ProdutoService produtoService) {
    this.produtoService = produtoService;
  }

    @GetMapping()
  public List<Produto> listarProdutos() {
      return produtoService.listarProdutos();
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<?> buscarProduto(@PathVariable Long id) {
  
      Produto produto = produtoService.buscarPorId(id);
      return ResponseEntity.ok(produto);
    
    }

  @PostMapping
  public Produto criarProduto(@RequestBody Produto produto) {
    return produtoService.salvarProduto(produto);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
    produtoService.deletarProduto(id);
    return ResponseEntity.noContent().build();
  }
}
