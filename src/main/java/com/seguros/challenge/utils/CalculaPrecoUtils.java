package com.seguros.challenge.utils;

import com.seguros.challenge.adapters.out.entity.ProdutoEntity;
import com.seguros.challenge.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class CalculaPrecoUtils {

    private final ImpostosUtils impostosUtils;

    public CalculaPrecoUtils(ImpostosUtils impostosUtils) {
        this.impostosUtils = impostosUtils;
    }

    public Double calculaPrecoTarifado(Produto produto) {
        double precoBase = produto.getPrecoBase();
        double[] impostos = impostosUtils.retornaImpostos(produto);

        double iof = impostos[0];
        double pis = impostos[1];
        double cofins = impostos[2];
        double precoTarifado = precoBase + (precoBase * iof) + (precoBase * pis) + (precoBase * cofins);
        return precoTarifado;
    }
}