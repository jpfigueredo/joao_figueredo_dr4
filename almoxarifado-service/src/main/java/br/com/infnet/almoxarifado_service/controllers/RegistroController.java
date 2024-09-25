package br.com.infnet.almoxarifado_service.controllers;

import br.com.infnet.almoxarifado_service.domain.Registro;
import br.com.infnet.almoxarifado_service.infra.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/registros")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @GetMapping("/{id}")
    public Registro findById(@PathVariable(value = "id") Long id){return registroService.findById(id);}

    @GetMapping
    public List<Registro> findAll(){return registroService.findAll();}

    @PatchMapping("/concluir/{id}")
    public Registro concluirRegistro(@PathVariable(value = "id") Long id){return registroService.concluir(id);}
}
