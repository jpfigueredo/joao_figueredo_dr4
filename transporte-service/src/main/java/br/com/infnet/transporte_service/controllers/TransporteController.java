package br.com.infnet.transporte_service.controllers;

import br.com.infnet.transporte_service.domain.Entrega;
import br.com.infnet.transporte_service.domain.ManifestoDeCarga;
import br.com.infnet.transporte_service.infra.service.TransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TransporteController {

    @Autowired
    private TransporteService service;

    @GetMapping("/manifestos/{id}")
    public ManifestoDeCarga obterManifestoPorId(@PathVariable long id) {
        return service.obterPorId(id);
    }

    @PatchMapping("/manifestos/concluir-edicao/{id}")
    public ManifestoDeCarga concluirEdicao(@PathVariable long id) {
        return service.concluirEdicao(id);
    }

    @PostMapping("/entregas/concluir/{id}")
    public Entrega concluirEntrega(@PathVariable long id) {
        return service.concluirEntrega(id);
    }

}
