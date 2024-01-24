package com.seguros.challenge.adapters.in.controller;

import com.seguros.challenge.adapters.out.entity.ProdutoEntity;
import com.seguros.challenge.adapters.out.mapper.ProdutoEntityMapper;
import com.seguros.challenge.domain.model.Produto;
import com.seguros.challenge.usecase.ports.CalculaPrecoTarifadoUseCase;
import com.seguros.challenge.usecase.ports.ProdutoGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
    private final CalculaPrecoTarifadoUseCase calcularPrecoTarifadoUseCase;
    private final ProdutoEntityMapper produtoEntityMapper;

    @Autowired
    public ProdutoController(CalculaPrecoTarifadoUseCase calcularPrecoTarifadoUseCase, ProdutoEntityMapper produtoEntityMapper) {
        this.calcularPrecoTarifadoUseCase = calcularPrecoTarifadoUseCase;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {

            produto = calcularPrecoTarifadoUseCase.inserePrecoTarifado(produto);

            return ResponseEntity.ok(produto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable String id, @Validated @RequestBody Produto produto) {
        produto.setId(id);

        Produto produtoTarfifado = calcularPrecoTarifadoUseCase.atualizaPrecoTarifado(produto);

        return ResponseEntity.ok(produtoTarfifado);
    }
}