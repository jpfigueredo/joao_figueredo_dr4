package br.com.infnet.transporte_service.controller;

import br.com.infnet.transporte_service.domain.Entrega;
import br.com.infnet.transporte_service.domain.Transporte;
import br.com.infnet.transporte_service.service.TransporteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transporte")
public class TransporteController {

    private final TransporteService transporteService;

    public TransporteController(TransporteService transporteService) {
        this.transporteService = transporteService;
    }

    @PostMapping("/processar")
    public void processarPedido(@RequestBody Entrega entrega) throws Exception {
        transporteService.processarPedido(entrega);
    }

    @GetMapping("/status/{id}")
    public String verificarStatusTransporte(@PathVariable Long id) {
        return transporteService.verificarStatusTransporte(id);
    }

    @PostMapping
    public void adicionarTransporte(@RequestBody Transporte transporte) {
        transporteService.adicionarTransporte(transporte);
    }
}
