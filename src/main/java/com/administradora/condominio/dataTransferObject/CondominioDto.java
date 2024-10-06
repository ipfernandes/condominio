package com.administradora.condominio.dataTransferObject;

import java.time.Instant;

public record CondominioDto(
        String nome,
        String tipo,
        String cnpj,
        Instant criadoEm
) { }