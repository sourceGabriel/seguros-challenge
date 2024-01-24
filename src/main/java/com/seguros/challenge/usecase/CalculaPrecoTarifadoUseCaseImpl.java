package com.seguros.challenge.usecase;

import com.seguros.challenge.domain.model.Produto;
import com.seguros.challenge.usecase.ports.CalculaPrecoTarifadoUseCase;
import com.seguros.challenge.usecase.ports.ProdutoGateway;
import com.seguros.challenge.utils.CalculaPrecoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CalculaPrecoTarifadoUseCaseImpl implements CalculaPrecoTarifadoUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CalculaPrecoTarifadoUseCaseImpl.class);
    private final CalculaPrecoUtils calculaPrecoUtils;

    private final ProdutoGateway produtoGateway;

    @Autowired
    public CalculaPrecoTarifadoUseCaseImpl(CalculaPrecoUtils calculaPrecoUtils, ProdutoGateway produtoGateway) {
        this.calculaPrecoUtils = calculaPrecoUtils;
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto inserePrecoTarifado(Produto produto) {

        validateRequest(produto);
        Double precoTarifado = calculaPrecoUtils.calculaPrecoTarifado(produto);

        produto.setPrecoTarifado(precoTarifado);
        if (produto.getId() == null) {
            produto.setId(UUID.randomUUID().toString());
        }
        produtoGateway.salvarOuAtualizar(produto);

        return produto;

    }
    @Override
    public Produto atualizaPrecoTarifado(Produto produto) {

        validateRequest(produto);
        Produto produtoExistente = produtoGateway.procuraPorId(produto.getId());

        Double precoTarifado = calculaPrecoUtils.calculaPrecoTarifado(produto);
        produto.setPrecoTarifado(precoTarifado);

        produtoGateway.salvarOuAtualizar(produto);

        return produto;

    }

    Boolean validateRequest(Produto produto) {
        if (produto.getCategoria() == null || produto.getPrecoBase() == null || produto.getNome() == null) {
            throw new IllegalArgumentException("Campos nome, precoBase e categoria devem estar preenchidos");
        } else {
            return true;
        }
    }
}



























