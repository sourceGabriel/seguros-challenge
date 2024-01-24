package com.seguros.challenge.gateway;

import com.seguros.challenge.domain.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ProdutoGatewayImpl implements ProdutoGateway {
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoGatewayImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto salvarOuAtualizar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public Optional<Produto> procuraPorId(String id) {
        return produtoRepository.findById(id);
    }
}