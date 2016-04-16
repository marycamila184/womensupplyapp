package com.up.mary.womensupplyapp.model.empreendedor;

import com.up.mary.womensupplyapp.model.fornecedor.Fornecedor;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class Empreendedor {

    private int id;
    private String nomeEmpresa;
    private String ramoEmpresa;
    private String cnpj;
    private String endereco;
    private String bairro;
    private String cidade;
    private String uf;
    private String telefone;
    private int ranking;
    private String email;
    private String nomeResponsavel;
    private String emailResponsavel;


    public int getId() {
        return id;
    }

    public Empreendedor setId(int id) {
        this.id = id;
        return this;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public Empreendedor setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
        return this;
    }

    public String getRamoEmpresa() {
        return ramoEmpresa;
    }

    public Empreendedor setRamoEmpresa(String ramoEmpresa) {
        this.ramoEmpresa = ramoEmpresa;
        return this;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Empreendedor setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public Empreendedor setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public Empreendedor setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public Empreendedor setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getUf() {
        return uf;
    }

    public Empreendedor setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public String getTelefone() {
        return telefone;
    }

    public Empreendedor setTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Empreendedor setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public Empreendedor setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
        return this;
    }

    public String getEmailResponsavel() {
        return emailResponsavel;
    }

    public Empreendedor setEmailResponsavel(String emailResponsavel) {
        this.emailResponsavel = emailResponsavel;
        return this;
    }


    public int getRanking() {
        return ranking;
    }

    public Empreendedor setRanking(int ranking) {
        this.ranking = ranking;
        return this;
    }
}
