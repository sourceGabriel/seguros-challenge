package com.seguros.challenge.usecase;

import com.seguros.challenge.domain.model.Produto;
import com.seguros.challenge.gateway.ProdutoGateway;
import com.seguros.challenge.gateway.ProdutoRepository;
import com.seguros.challenge.utils.ImpostosUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CalculaPrecoTarifadoUseCaseImpl implements CalculaPrecoTarifadoUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CalculaPrecoTarifadoUseCaseImpl.class);

    private final ImpostosUtils impostosUtils;

    private final ProdutoRepository produtoRepository;
    private final ProdutoGateway produtoGateway;

    @Autowired
    public CalculaPrecoTarifadoUseCaseImpl(ImpostosUtils impostosUtils, ProdutoRepository produtoRepository, ProdutoGateway produtoGateway) {

        this.impostosUtils = impostosUtils;
        this.produtoRepository = produtoRepository;
        this.produtoGateway = produtoGateway;
    }

    @Override
    public Produto calcularPrecoTarifado(Produto produto) {
        try {

            validateRequest(produto);

            double precoBase = produto.getPrecoBase();
            double[] impostos = impostosUtils.retornaImpostos(produto);

            double iof = impostos[0];
            double pis = impostos[1];
            double cofins = impostos[2];
            double precoTarifado = precoBase + (precoBase * iof) + (precoBase * pis) + (precoBase * cofins);

            produto.setPrecoTarifado(precoTarifado);
            if (produto.getId() == null) {
                produto.setId(UUID.randomUUID().toString());
            }

            return produto;
        } catch (Exception e) {
            logger.error("Erro ao calcular o preço tarifado para o produto: {}", produto, e);
            throw e;
        }
    }

    Boolean validateRequest(Produto produto) {
        if (produto.getCategoria() == null || produto.getPrecoBase() == null || produto.getNome() == null) {
            throw new IllegalArgumentException("Campos nome, precoBase e categoria devem estar preenchidos");
        } else {
            return true;
        }
    }
}



























