package com.seguros.challenge.usecase;

import com.seguros.challenge.domain.model.Produto;

public interface CalculaPrecoTarifadoUseCase {
    Produto calcularPrecoTarifado(Produto produto);
}