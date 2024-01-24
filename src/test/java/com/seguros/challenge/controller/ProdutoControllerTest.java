package com.seguros.challenge.controller;

import com.seguros.challenge.adapters.in.controller.ProdutoController;
import com.seguros.challenge.adapters.out.entity.ProdutoEntity;
import com.seguros.challenge.adapters.out.mapper.ProdutoEntityMapper;
import com.seguros.challenge.domain.model.Produto;
import com.seguros.challenge.usecase.ports.CalculaPrecoTarifadoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    @Mock
    private CalculaPrecoTarifadoUseCase calcularPrecoTarifadoUseCase;

    @Mock
    private ProdutoEntityMapper produtoEntityMapper;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarProduto_DeveRetornarProdutoAoInserir() {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        Produto produto = new Produto("1", "Seguro de vida", "vida", 100.0, 120.0);
        when(calcularPrecoTarifadoUseCase.inserePrecoTarifado(any(Produto.class))).thenReturn(produto);
        when(produtoEntityMapper.toDomain(any(ProdutoEntity.class))).thenReturn(produto);

        ResponseEntity<?> responseEntity = produtoController.criarProduto(produto);

        assertNotNull(responseEntity);
        assertEquals(ResponseEntity.ok(produto), responseEntity);
        verify(calcularPrecoTarifadoUseCase, times(1)).inserePrecoTarifado(any(Produto.class));
    }

    @Test
    void atualizarProduto_DeveRetornarProdutoAtualizado() {
        String id = "123";
        ProdutoEntity produtoEntity = new ProdutoEntity();
        Produto produto = new Produto("1", "Seguro de vida", "vida", 100.0, 120.0);
        produto.setId(id);
        when(calcularPrecoTarifadoUseCase.atualizaPrecoTarifado(any(Produto.class))).thenReturn(produto);
        when(produtoEntityMapper.toDomain(any(ProdutoEntity.class))).thenReturn(produto);

        ResponseEntity<?> responseEntity = produtoController.atualizarProduto(id, produto);

        assertNotNull(responseEntity);
        assertEquals(ResponseEntity.ok(produto), responseEntity);
        verify(calcularPrecoTarifadoUseCase, times(1)).atualizaPrecoTarifado(any(Produto.class));
    }
}