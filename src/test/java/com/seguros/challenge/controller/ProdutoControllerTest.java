package com.seguros.challenge.controller;

import com.seguros.challenge.domain.model.Produto;
import com.seguros.challenge.gateway.ProdutoGateway;
import com.seguros.challenge.usecase.CalculaPrecoTarifadoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    @Mock
    private CalculaPrecoTarifadoUseCase calcularPrecoTarifadoUseCase;

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListarProdutos() {
        // Arrange
        List<Produto> produtos = new ArrayList<>();
        when(produtoGateway.listarTodos()).thenReturn(produtos);

        // Act
        List<Produto> result = produtoController.listaProdutos();

        // Assert
        assertEquals(produtos, result);
    }

    @Test
    void testCriarProduto_Success() {
        // Arrange
        Produto produto = new Produto("1", "seguro de vida", "vida", 100.0, 120.0);
        Mockito.when(calcularPrecoTarifadoUseCase.calcularPrecoTarifado(produto)).thenReturn(produto);
        Mockito.when(produtoGateway.salvarOuAtualizar(produto)).thenReturn(produto);

        // Act
        ResponseEntity<?> responseEntity = produtoController.criarProduto(produto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(produto, responseEntity.getBody());
    }

    @Test
    void testCriarProduto_InvalidInput() {
        // Arrange
        Produto produto = new Produto();
        when(calcularPrecoTarifadoUseCase.calcularPrecoTarifado(any())).thenThrow(new IllegalArgumentException("Invalid input"));

        // Act
        ResponseEntity<?> result = produtoController.criarProduto(produto);

        // Assert
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input"), result);
        verify(produtoGateway, never()).salvarOuAtualizar(any());
    }

    @Test
    void testAtualizarProduto_Success() {
        // Arrange
        String id = "123";
        Produto novoProduto = new Produto();
        Produto produtoExistente = new Produto();
        Optional<Produto> produtoExistenteOptional = Optional.of(produtoExistente);
        when(produtoGateway.procuraPorId(id)).thenReturn(produtoExistenteOptional);
        when(calcularPrecoTarifadoUseCase.calcularPrecoTarifado(any())).thenReturn(novoProduto);

        // Act
        ResponseEntity<?> result = produtoController.atualizarProduto(id, novoProduto);

        // Assert
        assertEquals(ResponseEntity.ok(novoProduto), result);
        verify(produtoGateway, times(1)).salvarOuAtualizar(novoProduto);
    }

    @Test
    void testAtualizarProduto_IdNotFound() {
        // Arrange
        String id = "123";
        Produto novoProduto = new Produto();
        when(produtoGateway.procuraPorId(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> result = produtoController.atualizarProduto(id, novoProduto);

        // Assert
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não encontrado"), result);
        verify(produtoGateway, never()).salvarOuAtualizar(any());
    }

    @Test
    void testAtualizarProduto_Exception() {
        // Arrange
        String id = "123";
        Produto novoProduto = new Produto();
        when(produtoGateway.procuraPorId(id)).thenReturn(Optional.of(new Produto()));
        when(calcularPrecoTarifadoUseCase.calcularPrecoTarifado(any())).thenThrow(new RuntimeException("Some exception"));

        // Act
        ResponseEntity<?> result = produtoController.atualizarProduto(id, novoProduto);

        // Assert
        assertEquals(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some exception"), result);
        verify(produtoGateway, never()).salvarOuAtualizar(any());
    }
}
