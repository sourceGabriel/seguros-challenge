package com.seguros.challenge.usecase;

import com.seguros.challenge.adapters.out.entity.ProdutoEntity;
import com.seguros.challenge.domain.model.Produto;
import com.seguros.challenge.usecase.ports.ProdutoGateway;
import com.seguros.challenge.utils.CalculaPrecoUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculaPrecoTarifadoUseCaseImplTest {

    @Mock
    private CalculaPrecoUtils calculaPrecoUtils;

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private CalculaPrecoTarifadoUseCaseImpl calculaPrecoTarifadoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void inserePrecoTarifado_DeveInserirPrecoTarifadoNoProdutoEChamarSalvarOuAtualizar() {
        // Arrange
        Produto produto = new Produto("1", "Seguro de vida", "vida", 100.0, 120.0);
        produto.setNome("Produto Teste");
        produto.setPrecoBase(50.0);
        produto.setCategoria("Categoria Teste");
        ProdutoEntity produtoEntity = new ProdutoEntity();
        when(calculaPrecoUtils.calculaPrecoTarifado(produto)).thenReturn(55.0);
        when(produtoGateway.salvarOuAtualizar(produto)).thenReturn(produtoEntity);

        // Act
        Produto result = calculaPrecoTarifadoUseCase.inserePrecoTarifado(produto);

        // Assert
        assertNotNull(result);
        assertEquals(55.0, result.getPrecoTarifado());

        // Verify
        verify(calculaPrecoUtils, times(1)).calculaPrecoTarifado(produto);
        verify(produtoGateway, times(1)).salvarOuAtualizar(produto);
    }

    @Test
    void atualizaPrecoTarifado_DeveAtualizarPrecoTarifadoNoProdutoEChamarSalvarOuAtualizar() {
        // Arrange
        Produto produto = new Produto("1", "Seguro de vida", "vida", 100.0, 120.0);
        produto.setId(UUID.randomUUID().toString());
        produto.setNome("Produto Teste");
        produto.setPrecoBase(50.0);
        produto.setCategoria("Categoria Teste");
        ProdutoEntity produtoEntity = new ProdutoEntity();
        when(calculaPrecoUtils.calculaPrecoTarifado(produto)).thenReturn(55.0);
        when(produtoGateway.procuraPorId(produto.getId())).thenReturn(produto);
        when(produtoGateway.salvarOuAtualizar(produto)).thenReturn(produtoEntity);

        // Act
        Produto result = calculaPrecoTarifadoUseCase.atualizaPrecoTarifado(produto);

        // Assert
        assertNotNull(result);
        assertEquals(55.0, result.getPrecoTarifado());

        // Verify
        verify(calculaPrecoUtils, times(1)).calculaPrecoTarifado(produto);
        verify(produtoGateway, times(1)).procuraPorId(produto.getId());
        verify(produtoGateway, times(1)).salvarOuAtualizar(produto);
    }

    @Test
    void validateRequest_QuandoCamposInvalidos_DeveLancarExcecao() {
        // Arrange
        Produto produto = new Produto("1", "Seguro de vida", null, 100.0, 120.0);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> calculaPrecoTarifadoUseCase.validateRequest(produto));
    }

    @Test
    void validateRequest_QuandoCamposValidos_DeveRetornarTrue() {
        // Arrange
        Produto produto = new Produto("1", "Seguro de vida", "vida", 100.0, 120.0);
        produto.setNome("Produto Teste");
        produto.setPrecoBase(50.0);
        produto.setCategoria("Categoria Teste");

        // Act
        Boolean result = calculaPrecoTarifadoUseCase.validateRequest(produto);

        // Assert
        assertTrue(result);
    }
}
