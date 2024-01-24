package com.seguros.challenge.adapters.out.mapper;

import com.seguros.challenge.adapters.out.entity.ProdutoEntity;
import com.seguros.challenge.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoEntityMapper {

    public ProdutoEntity toEntity(Produto produto) {
        return new ProdutoEntity(produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getPrecoBase(),
                produto.getPrecoTarifado());
    }

    public Produto toDomain(ProdutoEntity produtoEntity) {
        return new Produto(produtoEntity.getId(),
                produtoEntity.getNome(),
                produtoEntity.getCategoria(),
                produtoEntity.getPrecoBase(),
                produtoEntity.getPrecoTarifado());
    }
}
