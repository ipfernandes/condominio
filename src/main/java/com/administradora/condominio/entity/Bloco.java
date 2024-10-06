package com.administradora.condominio.entity;

public class Bloco {
    private int identificador;
    private String nome;
    private Condominio condominio;

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public Bloco(int identificador, String nome, Condominio condominio) {
        this.identificador = identificador;
        this.nome = nome;
        this.condominio = condominio;
    }

    public Bloco() {
    }
}