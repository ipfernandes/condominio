package com.administradora.condominio.entity;

public class Area {
    private int identificador;
    private String nome;
    private double metragemQuadrada;
    private Condominio condominio;
    private TipoArea tipoArea;

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

    public double getMetragemQuadrada() {
        return metragemQuadrada;
    }

    public void setMetragemQuadrada(double metragemQuadrada) {
        this.metragemQuadrada = metragemQuadrada;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public TipoArea getTipoArea() {
        return tipoArea;
    }

    public void setTipoArea(TipoArea tipoArea) {
        this.tipoArea = tipoArea;
    }

    public Area(int identificador, String nome, double metragemQuadrada, Condominio condominio, TipoArea tipoArea) {
        this.identificador = identificador;
        this.nome = nome;
        this.metragemQuadrada = metragemQuadrada;
        this.condominio = condominio;
        this.tipoArea = tipoArea;
    }

    public Area() {
    }
}