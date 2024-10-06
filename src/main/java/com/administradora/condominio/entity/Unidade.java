package com.administradora.condominio.entity;

public class Unidade {
    private int identificador;
    private String nome;
    private double metragemQuarada;

    //Agregação: a unidade pertence ao bloco, por isso, ela existe se o bloco também existir.
    private Bloco bloco;

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

    public double getMetragemQuarada() {
        return metragemQuarada;
    }

    public void setMetragemQuarada(double metragemQuarada) {
        this.metragemQuarada = metragemQuarada;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public Unidade(int identificador, String nome, double metragemQuarada, Bloco bloco) {
        this.identificador = identificador;
        this.nome = nome;
        this.metragemQuarada = metragemQuarada;
        this.bloco = bloco;
    }

    public Unidade() {
    }
}