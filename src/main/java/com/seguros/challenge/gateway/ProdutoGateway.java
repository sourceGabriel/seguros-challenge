package com.seguros.challenge.gateway;

import com.seguros.challenge.domain.model.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {
    Produto salvarOuAtualizar(Produto produto);
    List<Produto> listarTodos();

    Optional<Produto> procuraPorId(String id);

}