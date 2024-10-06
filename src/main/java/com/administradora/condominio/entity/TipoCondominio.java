package com.administradora.condominio.entity;

public enum TipoCondominio {
    CASA,
    APARTAMENTO;

    public static TipoCondominio defineTipo(String tipo){

        if(TipoCondominio.CASA.toString().equals(tipo.toUpperCase())) return TipoCondominio.CASA;
        else return TipoCondominio.APARTAMENTO;
    }
}