package com.ferias.primeiro_projeto_ferias.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferias.primeiro_projeto_ferias.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
  
}
