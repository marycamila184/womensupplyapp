package com.up.mary.womensupplyapp.model.fornecedor;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class Produtos {

    private int id;
    private float preco;
    private int idFornecedor;


    public int getId() {
        return id;
    }

    public Produtos setId(int id) {
        this.id = id;
        return this;
    }

    public float getPreco() {
        return preco;
    }

    public Produtos setPreco(float preco) {
        this.preco = preco;
        return this;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public Produtos setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
        return this;
    }
}
