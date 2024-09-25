package br.com.infnet.transporte_service.controllers;

import br.com.infnet.transporte_service.domain.Entrega;
import br.com.infnet.transporte_service.domain.ManifestoDeCarga;
import br.com.infnet.transporte_service.infra.service.TransporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transportes")
public class TransporteController {

    @Autowired
    private TransporteService transporteService;

    @PatchMapping("/cargas/encerrar-edicao/{id}")
    public ManifestoDeCarga encerrarEdicao(@PathVariable long id) {
        return transporteService.encerrarEdicao(id);
    }

    @GetMapping("/cargas/{id}")
    public ManifestoDeCarga findManifestoDeCargaById(@PathVariable long id) {
        return transporteService.findManifestoDeCargaById(id);
    }

    @PostMapping("/entregas/encerrar/{id}")
    public Entrega encerrarEntrega(@PathVariable long id) {
        return transporteService.encerrarEntrega(id);
    }


}
