package com.administradora.condominio.service;

import com.administradora.condominio.dataTransferObject.CondominioAlteracaoDto;
import com.administradora.condominio.dataTransferObject.CondominioDto;
import com.administradora.condominio.entity.Condominio;
import com.administradora.condominio.entity.TipoCondominio;
import com.administradora.condominio.repository.CondominioRepository;
import org.springframework.stereotype.Service;

public class CondominioService {
    private CondominioRepository condominioRepository = new CondominioRepository();

    public boolean criarCondominio(CondominioDto condominioDto) throws Exception{
        Condominio condominio = converterDtoEntity(condominioDto);
        return condominioRepository.criarCondominio(condominio);
    }

    public CondominioDto buscarCondominioPorCnpj(String cnpj) throws Exception{
        Condominio condominio = condominioRepository.buscarPorCnpj(cnpj);
        if(condominio.getIdentificador() > 0) return converterEntityToDto(condominio);
        else return null;
    }
    public void deletarCondominio(String cnpj) throws Exception{
        condominioRepository.deletarPorCnpj(cnpj);
    }

    public boolean alterarCondominio(CondominioAlteracaoDto condominioDto, String cnpj) throws Exception{
        boolean retorno = false;
        //checamos primeiro se o condomínio existe
        Condominio condominio = condominioRepository.buscarPorCnpj(cnpj);
        if(condominio.getIdentificador() > 0){
            if(!condominioDto.nome().isEmpty())condominio.setNome(condominioDto.nome());
            if(!condominioDto.tipo().isEmpty())condominio.setTipo(TipoCondominio.defineTipo(condominioDto.tipo()));
            retorno = condominioRepository.alterarCondominio(condominio);
            return retorno;
        }else{
            return retorno;
        }
    }

    //Métodos utilizados para conversão de dados de entrada e saída, padrão de projeto Data Transfer Object (DTO)
    private Condominio converterDtoEntity(CondominioDto condominioDto){
        Condominio condominio = new Condominio();
        condominio.setNome(condominioDto.nome());
        condominio.setCnpj(condominioDto.cnpj());
        condominio.setTipo(TipoCondominio.defineTipo(condominioDto.tipo()));

        return condominio;
    }

    private CondominioDto converterEntityToDto(Condominio condominio){
        CondominioDto condominioDto = new CondominioDto(
                condominio.getNome(),
                condominio.getTipo().toString(),
                condominio.getCnpj(),
                condominio.getDataCriacao()
        );
        return condominioDto;
    }
}