package com.seguros.challenge.usecase.ports;

import com.seguros.challenge.domain.model.Produto;

public interface CalculaPrecoTarifadoUseCase {
    Produto inserePrecoTarifado(Produto produto);

    Produto atualizaPrecoTarifado(Produto produto);
}