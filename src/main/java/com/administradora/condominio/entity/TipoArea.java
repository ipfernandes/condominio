package com.administradora.condominio.entity;

public enum TipoArea {
    COMUM,
    ESPECIFICA;

    public static TipoArea defineTipo(String tipo){

        if(TipoArea.COMUM.toString().equals(tipo.toUpperCase())) return TipoArea.COMUM;
        else return TipoArea.ESPECIFICA;
    }
}