package com.administradora.condominio.controller;

import com.administradora.condominio.dataTransferObject.CondominioDto;
import com.administradora.condominio.dataTransferObject.UnidadeDto;
import com.administradora.condominio.dataTransferObject.UnidadesDoCondominioDto;
import com.administradora.condominio.service.UnidadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/unidade")
public class UnidadeController {
    private UnidadeService unidadeService = new UnidadeService();
    @PostMapping
    public ResponseEntity<String> criaBloco(@RequestBody UnidadeDto unidadeDto){
        try {
            return new ResponseEntity<>(unidadeService.criarUnidade(unidadeDto), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/{cnpj}")
    public ResponseEntity<List<UnidadesDoCondominioDto>> buscaUnidadesDoCondominio(@PathVariable("cnpj") String cnpj){
        try{
            //busco por bloco, pois o bloco pertence ao condominio
            return new ResponseEntity<>(unidadeService.buscaUnidadesPorBloco(cnpj), HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}