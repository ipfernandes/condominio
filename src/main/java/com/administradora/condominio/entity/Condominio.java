package com.administradora.condominio.entity;

import java.time.Instant;

public class Condominio {
    private int identificador;
    private String cnpj;
    private String nome;
    private TipoCondominio tipo;
    private Instant dataCriacao;

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCondominio getTipo() {
        return tipo;
    }

    public void setTipo(TipoCondominio tipo) {
        this.tipo = tipo;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Condominio(int identificador, String cnpj, String nome, TipoCondominio tipo, Instant dataCriacao) {
        this.identificador = identificador;
        this.cnpj = cnpj;
        this.nome = nome;
        this.tipo = tipo;
        this.dataCriacao = dataCriacao;
    }

    public Condominio() {
    }

}