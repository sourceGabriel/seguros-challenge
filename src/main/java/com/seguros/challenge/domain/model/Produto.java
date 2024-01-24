package com.seguros.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@Data
public class Produto {

    private String id;
    private String nome;
    private String categoria;
    private Double precoBase;
    private Double precoTarifado;
}
