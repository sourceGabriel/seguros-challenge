package com.seguros.challenge.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)")
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "preco_base")
    private Double precoBase;

    @Column(name = "preco_tarifado")
    private Double precoTarifado;

    public Produto(String id, String nome, String categoria, double precoBase, double precoTarifado) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.precoBase = precoBase;
        this.precoTarifado = precoTarifado;
    }

    public Produto() {
    }

}
