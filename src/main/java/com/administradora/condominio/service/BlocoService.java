package com.administradora.condominio.service;

import com.administradora.condominio.dataTransferObject.BlocoDto;
import com.administradora.condominio.entity.Bloco;
import com.administradora.condominio.repository.BlocoRepository;
import com.administradora.condominio.repository.CondominioRepository;

import java.util.ArrayList;
import java.util.List;

public class BlocoService {
    private BlocoRepository blocoRepository = new BlocoRepository();
    private CondominioRepository condominioRepository = new CondominioRepository();
    public boolean criarBloco(BlocoDto blocoDto) throws Exception{
        Bloco bloco = converterDtoEntity(blocoDto);
        bloco.setCondominio(condominioRepository.buscarPorCnpj(blocoDto.cnpjCondominio()));
        return blocoRepository.criarBloco(bloco);
    }
    public List<BlocoDto> buscarBloco(String nome) throws Exception{
        List<Bloco> blocos = blocoRepository.buscarBloco(nome);
        if(!blocos.isEmpty()){
            List<BlocoDto> retorno = new ArrayList<>();
            for(Bloco b : blocos){
                //enriquecimento
                b.setCondominio(condominioRepository.buscarPorIdentificador(b.getCondominio().getIdentificador()));

                retorno.add(converterEntityToDto(b));
            }
            return retorno;
        }
        else return null;
    }
    private Bloco converterDtoEntity(BlocoDto blocoDto){
        Bloco bloco = new Bloco();
        bloco.setNome(blocoDto.nome());

        return bloco;
    }
    private BlocoDto converterEntityToDto(Bloco bloco){
        BlocoDto blocoDto = new BlocoDto(
                bloco.getNome(),
                bloco.getCondominio().getCnpj(),
                bloco.getCondominio().getNome()
        );
        return blocoDto;
    }
}