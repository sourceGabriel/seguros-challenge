package com.seguros.challenge.utils;

import com.seguros.challenge.domain.model.CategoriaSeguro;
import com.seguros.challenge.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ImpostosUtils implements CategoriaSeguro {
    @Override
    public double[] retornaImpostos(Produto produto) {
        switch (produto.getCategoria().toUpperCase()) {
            case "VIDA":
                return new double[]{0.01, 0.022, 0.0};
            case "AUTO":
                return new double[]{0.055, 0.04, 0.01};
            case "VIAGEM":
                return new double[]{0.02, 0.04, 0.01};
            case "RESIDENCIAL":
                return new double[]{0.04, 0.0, 0.03};
            case "PATRIMONIAL":
                return new double[]{0.05, 0.03, 0.0};
            default:
                // Categoria desconhecida
                throw new IllegalArgumentException("Categoria de seguro desconhecida: " + produto.getCategoria());
        }
    }
}