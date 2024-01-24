package com.seguros.challenge.gateway;

import com.seguros.challenge.adapters.out.entity.ProdutoEntity;
import com.seguros.challenge.adapters.out.gateway.ProdutoGatewayImpl;
import com.seguros.challenge.adapters.out.gateway.ProdutoRepository;
import com.seguros.challenge.adapters.out.mapper.ProdutoEntityMapper;
import com.seguros.challenge.domain.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoGatewayImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoEntityMapper produtoEntityMapper;

    @InjectMocks
    private ProdutoGatewayImpl produtoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvarOuAtualizar_DeveRetornarProdutoEntitySalvo() {
        Produto produto = new Produto("1", "Seguro de vida", "vida", 100.0, 120.0);
        ProdutoEntity produtoEntity = new ProdutoEntity();

        when(produtoEntityMapper.toEntity(produto)).thenReturn(produtoEntity);
        when(produtoRepository.save(produtoEntity)).thenReturn(produtoEntity);

        ProdutoEntity result = produtoGateway.salvarOuAtualizar(produto);

        assertNotNull(result);
        assertEquals(produtoEntity, result);

        verify(produtoEntityMapper, times(1)).toEntity(produto);
        verify(produtoRepository, times(1)).save(produtoEntity);
    }

    @Test
    void procuraPorId_QuandoIdExistente_DeveRetornarProduto() {
        // Arrange
        String id = "123";
        ProdutoEntity produtoEntity = new ProdutoEntity();
        Produto produto = new Produto("1", "Seguro de vida", "vida", 100.0, 120.0);

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoEntity));
        when(produtoEntityMapper.toDomain(produtoEntity)).thenReturn(produto);

        Produto result = produtoGateway.procuraPorId(id);

        assertNotNull(result);
        assertEquals(produto, result);

        verify(produtoRepository, times(1)).findById(id);
        verify(produtoEntityMapper, times(1)).toDomain(produtoEntity);
    }

    @Test
    void procuraPorId_QuandoIdNaoExistente_DeveLancarExcecao() {
        String id = "456";

        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> produtoGateway.procuraPorId(id));

        verify(produtoRepository, times(1)).findById(id);
        verify(produtoEntityMapper, never()).toDomain(any());
    }
}
