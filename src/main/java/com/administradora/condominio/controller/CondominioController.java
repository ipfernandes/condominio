package com.administradora.condominio.controller;

import com.administradora.condominio.dataTransferObject.CondominioAlteracaoDto;
import com.administradora.condominio.dataTransferObject.CondominioDto;
import com.administradora.condominio.service.CondominioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/condominio")
public class CondominioController {
    private CondominioService condominioService = new CondominioService();

    @PostMapping
    public ResponseEntity<String> criaCondominio(@RequestBody CondominioDto condominioDto){
        try {
            boolean response = condominioService.criarCondominio(condominioDto);
            return new ResponseEntity<>("Condominio criado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<CondominioDto> buscaCondominioPorCnpj(@PathVariable("cnpj") String cnpj){
        try{
            return new ResponseEntity<>(condominioService.buscarCondominioPorCnpj(cnpj), HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Void> deletarCondominio(@PathVariable("cnpj") String cnpj){
        try{
            condominioService.deletarCondominio(cnpj);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{cnpj}")
    public ResponseEntity<String> alteraCondominio(@PathVariable("cnpj") String cnpj,
                                                   @RequestBody CondominioAlteracaoDto condominioAlteracaoDto){
        try {
            boolean response = condominioService.alterarCondominio(condominioAlteracaoDto, cnpj);
            return new ResponseEntity<>("Condominio alterado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}