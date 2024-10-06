package com.administradora.condominio.service;

import com.administradora.condominio.dataTransferObject.UnidadeDto;
import com.administradora.condominio.dataTransferObject.UnidadesDoCondominioDto;
import com.administradora.condominio.entity.Bloco;
import com.administradora.condominio.entity.Condominio;
import com.administradora.condominio.entity.Unidade;
import com.administradora.condominio.repository.BlocoRepository;
import com.administradora.condominio.repository.CondominioRepository;
import com.administradora.condominio.repository.UnidadeRepository;

import java.util.ArrayList;
import java.util.List;

public class UnidadeService {
    private BlocoRepository blocoRepository = new BlocoRepository();
    private CondominioRepository condominioRepository = new CondominioRepository();

    private UnidadeRepository unidadeRepository = new UnidadeRepository();
    public String criarUnidade(UnidadeDto unidadeDto) throws Exception{
        //Primeiro vejo se o condominio informado existe
        Condominio condominio = condominioRepository.buscarPorCnpj(unidadeDto.cnpjCondominio());
        if(condominio.getNome() == null) return "O condominio informado nao existe";
        else {
            //Então, faço a checagem se o bloco informado pertence ao condomínio
            Bloco bloco = blocoRepository.buscarBlocodoCondominio(unidadeDto.bloco(), condominio.getIdentificador());
            if(bloco.getNome() == null) return "O bloco informado nao pertence ao condominio informado";
            else {
                //Após garantir a consistência do relacionamento entre o bloco e o condomínio, posso inserir a unidade
                Unidade unidade = converterDtoEntity(unidadeDto, bloco);
                if(unidadeRepository.criarUnidade(unidade) > 0) return "Unidade criada com sucesso";
                else return "Ocorreu um erro ao salvar a unidade no banco de dados";
            }
        }
    }

    public List<UnidadesDoCondominioDto> buscaUnidadesPorBloco(String cnpjCondominio) throws Exception{
        Condominio condominio = condominioRepository.buscarPorCnpj(cnpjCondominio);
        List<UnidadesDoCondominioDto> unidadesDoCondominioDto = new ArrayList<>();
        if(condominio.getNome() == null) return null;
        else{
            List<Bloco> blocos = blocoRepository.buscarTodosBlocosDoCondominio(condominio.getIdentificador());
            if(blocos.isEmpty()) return null;
            else{
                for(Bloco b : blocos){
                    //enriquecimento
                    b.setCondominio(condominio);
                    //descobrir as unidades do bloco
                    List<Unidade> unidades = unidadeRepository.buscarUnidadesDoBloco(b.getIdentificador());
                    for(Unidade u : unidades){
                        u.setBloco(b);
                        unidadesDoCondominioDto.add(converterEntityToDto(u));
                    }
                }
            }
        }
        return unidadesDoCondominioDto;
    }

    private Unidade converterDtoEntity(UnidadeDto unidadeDto, Bloco bloco){
        Unidade unidade = new Unidade();
        unidade.setNome(unidadeDto.nome());
        unidade.setMetragemQuarada(unidadeDto.metragemQuadrada());
        unidade.setBloco(bloco);

        return unidade;
    }

    private UnidadesDoCondominioDto converterEntityToDto(Unidade unidade){
        UnidadesDoCondominioDto retorno = new UnidadesDoCondominioDto(
                unidade.getNome(),
                unidade.getMetragemQuarada(),
                unidade.getBloco().getNome()
        );
        return retorno;
    }
}