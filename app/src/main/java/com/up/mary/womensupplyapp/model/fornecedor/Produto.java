package com.up.mary.womensupplyapp.model.fornecedor;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class Produto {

    private int id;
    private float preco;
    private String nomeProduto;


    public int getId() {
        return id;
    }

    public Produto setId(int id) {
        this.id = id;
        return this;
    }

    public float getPreco() {
        return preco;
    }

    public Produto setPreco(float preco) {
        this.preco = preco;
        return this;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Produto setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
        return this;
    }
}
