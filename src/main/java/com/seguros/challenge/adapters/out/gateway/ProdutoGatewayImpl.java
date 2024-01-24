package com.seguros.challenge.adapters.out.gateway;

import com.seguros.challenge.adapters.out.entity.ProdutoEntity;
import com.seguros.challenge.adapters.out.mapper.ProdutoEntityMapper;
import com.seguros.challenge.domain.model.Produto;
import com.seguros.challenge.usecase.ports.ProdutoGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProdutoGatewayImpl implements ProdutoGateway {
    private final ProdutoRepository produtoRepository;
    private final ProdutoEntityMapper produtoEntityMapper;

    @Autowired
    public ProdutoGatewayImpl(ProdutoRepository produtoRepository, ProdutoEntityMapper produtoEntityMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    @Override
    public ProdutoEntity salvarOuAtualizar(Produto produto) {
        ProdutoEntity produtoEntity = produtoEntityMapper.toEntity(produto);
        return produtoRepository.save(produtoEntity);
    }

    @Override
    public Produto procuraPorId(String id) {
        Optional<ProdutoEntity> produtoEntity = produtoRepository.findById(id);
        if (produtoEntity.isPresent()){
            return produtoEntityMapper.toDomain(produtoEntity.get());
        } else {
            throw new IllegalArgumentException("id não encontrado");
        }
    }
}