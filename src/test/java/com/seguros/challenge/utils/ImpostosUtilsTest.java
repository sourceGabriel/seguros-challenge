package com.seguros.challenge.utils;

import com.seguros.challenge.domain.model.Produto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImpostosUtilsTest {

    @Test
    public void testRetornaImpostosParaVida() {
        // Arrange
        ImpostosUtils impostosUtils = new ImpostosUtils();
        Produto produto = new Produto();
        produto.setCategoria("VIDA");

        // Act
        double[] impostos = impostosUtils.retornaImpostos(produto);

        // Assert
        assertArrayEquals(new double[]{0.01, 0.022, 0.0}, impostos);
    }

    @Test
    public void testRetornaImpostosParaAuto() {
        // Arrange
        ImpostosUtils impostosUtils = new ImpostosUtils();
        Produto produto = new Produto();
        produto.setCategoria("AUTO");

        // Act
        double[] impostos = impostosUtils.retornaImpostos(produto);

        // Assert
        assertArrayEquals(new double[]{0.055, 0.04, 0.01}, impostos);
    }

    @Test
    public void testRetornaImpostosParaViagem() {
        // Arrange
        ImpostosUtils impostosUtils = new ImpostosUtils();
        Produto produto = new Produto();
        produto.setCategoria("VIAGEM");

        // Act
        double[] impostos = impostosUtils.retornaImpostos(produto);

        // Assert
        assertArrayEquals(new double[]{0.02, 0.04, 0.01}, impostos);
    }

    @Test
    public void testRetornaImpostosParaResidencial() {
        // Arrange
        ImpostosUtils impostosUtils = new ImpostosUtils();
        Produto produto = new Produto();
        produto.setCategoria("RESIDENCIAL");

        // Act
        double[] impostos = impostosUtils.retornaImpostos(produto);

        // Assert
        assertArrayEquals(new double[]{0.04, 0.0, 0.03}, impostos);
    }

    @Test
    public void testRetornaImpostosParaPatrimonial() {
        // Arrange
        ImpostosUtils impostosUtils = new ImpostosUtils();
        Produto produto = new Produto();
        produto.setCategoria("PATRIMONIAL");

        // Act
        double[] impostos = impostosUtils.retornaImpostos(produto);

        // Assert
        assertArrayEquals(new double[]{0.05, 0.03, 0.0}, impostos);
    }

    @Test
    public void testRetornaImpostosParaCategoriaDesconhecida() {
        // Arrange
        ImpostosUtils impostosUtils = new ImpostosUtils();
        Produto produto = new Produto();
        produto.setCategoria("CATEGORIA_DESCONHECIDA");

        // Assert
        assertThrows(IllegalArgumentException.class, () -> impostosUtils.retornaImpostos(produto));
    }
}