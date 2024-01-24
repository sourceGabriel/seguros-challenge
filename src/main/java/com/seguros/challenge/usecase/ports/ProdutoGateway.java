package com.seguros.challenge.usecase.ports;

import com.seguros.challenge.adapters.out.entity.ProdutoEntity;
import com.seguros.challenge.domain.model.Produto;

import java.util.Optional;

public interface ProdutoGateway {
    ProdutoEntity salvarOuAtualizar(Produto produto);

    Produto procuraPorId(String id);

}