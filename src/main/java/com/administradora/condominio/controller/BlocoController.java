package com.administradora.condominio.controller;

import com.administradora.condominio.dataTransferObject.BlocoDto;
import com.administradora.condominio.service.BlocoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/bloco")
public class BlocoController {
    private BlocoService blocoService = new BlocoService();
    @PostMapping
    public ResponseEntity<String> criaBloco(@RequestBody BlocoDto blocoDto){
        try {
            boolean response = blocoService.criarBloco(blocoDto);
            return new ResponseEntity<>("Bloco criado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/{nome}")
    public ResponseEntity<List<BlocoDto>> buscaBloco(@PathVariable("nome") String nome){
        try{
            return new ResponseEntity<>(blocoService.buscarBloco(nome), HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}