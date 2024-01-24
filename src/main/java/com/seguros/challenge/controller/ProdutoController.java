package com.seguros.challenge.controller;

import com.seguros.challenge.domain.model.Produto;
import com.seguros.challenge.gateway.ProdutoGateway;
import com.seguros.challenge.usecase.CalculaPrecoTarifadoUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

    private final CalculaPrecoTarifadoUseCase calcularPrecoTarifadoUseCase;

    @Autowired
    private ProdutoGateway produtoGateway;

    @Autowired
    public ProdutoController(CalculaPrecoTarifadoUseCase calcularPrecoTarifadoUseCase, ProdutoGateway produtoGateway) {
        this.calcularPrecoTarifadoUseCase = calcularPrecoTarifadoUseCase;
        this.produtoGateway = produtoGateway;
    }

    @GetMapping
    public List<Produto> listaProdutos(){
        return produtoGateway.listarTodos();
    }

    @PostMapping
    public ResponseEntity<?> criarProduto(@RequestBody Produto produto) {
        try {
            produto = calcularPrecoTarifadoUseCase.calcularPrecoTarifado(produto);
            produtoGateway.salvarOuAtualizar(produto);
            return ResponseEntity.ok(produto);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable String id, @Validated @RequestBody Produto novoProduto) {
        try {

            Optional<Produto> produtoExistente = produtoGateway.procuraPorId(id);
            if (produtoExistente.isPresent()) {
                Produto produtoAtualizado = produtoExistente.get();
                produtoAtualizado.setNome(novoProduto.getNome());
                produtoAtualizado.setCategoria(novoProduto.getCategoria());
                produtoAtualizado.setPrecoBase(novoProduto.getPrecoBase());

                produtoAtualizado = calcularPrecoTarifadoUseCase.calcularPrecoTarifado(produtoAtualizado);

                produtoGateway.salvarOuAtualizar(produtoAtualizado);

                return ResponseEntity.ok(produtoAtualizado);
            } else {
                throw new IllegalArgumentException("Id não encontrado");
            }
        } catch (Exception e) {
            logger.error("Erro ao atualizar o produto com ID {}: {}", id, e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}