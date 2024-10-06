package com.administradora.condominio.dataTransferObject;

public record UnidadeDto(String nome,
                         double metragemQuadrada,
                         String bloco,
                         String cnpjCondominio) {
}