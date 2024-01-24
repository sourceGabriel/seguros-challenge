package com.seguros.challenge.usecase;

import com.seguros.challenge.domain.model.Produto;
import com.seguros.challenge.gateway.ProdutoGateway;
import com.seguros.challenge.gateway.ProdutoRepository;
import com.seguros.challenge.utils.ImpostosUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculaPrecoTarifadoUseCaseImplTest {
    private static final Logger logger = LoggerFactory.getLogger(CalculaPrecoTarifadoUseCaseImplTest.class);

    @Mock
    private ImpostosUtils impostosUtils;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoGateway produtoGateway;

    @InjectMocks
    private CalculaPrecoTarifadoUseCaseImpl useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calcularPrecoTarifadoSuccess() {
        Produto produto = new Produto(null, "seguro vida","vida", 20.0,20.0);
        produto.setPrecoBase(100.0);

        double[] impostos = {0.1, 0.05, 0.07};
        double precoTarifadoEsperado = 122.0;

        when(impostosUtils.retornaImpostos(produto)).thenReturn(impostos);

        Produto resultado = useCase.calcularPrecoTarifado(produto);

        assertAll(
                () -> assertEquals(precoTarifadoEsperado, resultado.getPrecoTarifado())
        );
    }

    @Test
    void calcularPrecoTarifadoComErro() {
        Produto produto = new Produto();
        produto.setPrecoBase(100.0);

        when(impostosUtils.retornaImpostos(produto)).thenThrow(new RuntimeException("Erro ao calcular o preço tarifado para o produto"));

        assertThrows(RuntimeException.class, () -> useCase.calcularPrecoTarifado(produto));

    }

    @Test
    void testValidateRequestSuccess() {
        // Arrange
        Produto produto = new Produto();
        produto.setNome("seguro de vida");
        produto.setCategoria("vida");
        produto.setPrecoBase(100.0);

        Boolean result = useCase.validateRequest(produto);

        assertTrue(result);
    }

    @Test
    void testValidateRequestNullCategoria() {
        Produto produto = new Produto();
        produto.setNome("seguro de vida");
        produto.setPrecoBase(100.0);

        assertThrows(IllegalArgumentException.class, () -> useCase.validateRequest(produto));
    }
}
