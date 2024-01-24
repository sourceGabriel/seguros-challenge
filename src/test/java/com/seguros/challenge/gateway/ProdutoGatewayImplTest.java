package com.seguros.challenge.gateway;

import com.seguros.challenge.domain.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class ProdutoGatewayImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoGatewayImpl produtoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarOuAtualizar() {
        // Arrange
        Produto produto = new Produto("1", "Test Product", "Test Category", 100.0, 120.0);
        Mockito.when(produtoRepository.save(produto)).thenReturn(produto);

        // Act
        Produto result = produtoGateway.salvarOuAtualizar(produto);

        // Assert
        assertEquals(produto, result);
        Mockito.verify(produtoRepository, Mockito.times(1)).save(produto);
    }


    @Test
    void testListarTodos() {
        List<Produto> produtos = Arrays.asList(
                new Produto("1", "Test Product", "Test Category", 100.0, 120.0),
                new Produto("1", "Test Product", "Test Category", 100.0, 120.0)
        );
        Mockito.when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> result = produtoGateway.listarTodos();

        assertEquals(produtos.size(), result.size());
        Mockito.verify(produtoRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testProcuraPorId_Exists() {
        String productId = "1";
        Produto produto = new Produto("1", "Test Product", "Test Category", 100.0, 120.0);
        Mockito.when(produtoRepository.findById(productId)).thenReturn(Optional.of(produto));

        Optional<Produto> result = produtoGateway.procuraPorId(productId);

        assertTrue(result.isPresent());
        assertEquals(produto, result.get());
        Mockito.verify(produtoRepository, Mockito.times(1)).findById(productId);
    }

    @Test
    void testProcuraPorId_NotExists() {
        String productId = "1";
        Mockito.when(produtoRepository.findById(productId)).thenReturn(Optional.empty());

        Optional<Produto> result = produtoGateway.procuraPorId(productId);

        assertFalse(result.isPresent());
        Mockito.verify(produtoRepository, Mockito.times(1)).findById(productId);
    }
}
