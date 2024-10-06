package com.administradora.condominio.entity;

public class AreaEspecifica extends Area{
    //A area específica pertence a um bloco
    private Bloco bloco;

    public AreaEspecifica(int identificador, String nome, double metragemQuadrada, Condominio condominio, Bloco bloco, TipoArea tipoArea) {
        super(identificador, nome, metragemQuadrada, condominio, tipoArea);
        this.bloco = bloco;
    }


    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }
}