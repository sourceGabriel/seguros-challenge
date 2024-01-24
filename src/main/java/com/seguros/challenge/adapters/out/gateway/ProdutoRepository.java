package com.seguros.challenge.adapters.out.gateway;


import com.seguros.challenge.adapters.out.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String> {
}