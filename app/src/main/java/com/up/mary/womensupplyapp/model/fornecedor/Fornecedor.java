package com.up.mary.womensupplyapp.model.fornecedor;

import java.io.Serializable;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class Fornecedor implements Serializable {

    private int id;
    private String nomeEmpresa;
    private String ramoEmpresa;
    private String cnpj;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String telefone;
    private String email;
    private int ranking;
    private String nomeResponsavel;
    private String emailResponsavel;


    public int getId() {
        return id;
    }

    public Fornecedor setId(int id) {
        this.id = id;
        return this;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public Fornecedor setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
        return this;
    }

    public String getRamoEmpresa() {
        return ramoEmpresa;
    }

    public Fornecedor setRamoEmpresa(String ramoEmpresa) {
        this.ramoEmpresa = ramoEmpresa;
        return this;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Fornecedor setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public Fornecedor setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Fornecedor setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public Fornecedor setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getUf() {
        return uf;
    }

    public Fornecedor setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Fornecedor setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Fornecedor setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public Fornecedor setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
        return this;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public Fornecedor setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
        return this;
    }


    public int getRanking() {
        return ranking;
    }

    public Fornecedor setRanking(int ranking) {
        this.ranking = ranking;
        return this;
    }
}
