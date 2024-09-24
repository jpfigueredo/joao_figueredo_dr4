package br.com.infnet.almoxarifado_service.controller;

import br.com.infnet.almoxarifado_service.domain.Almoxarifado;
import br.com.infnet.almoxarifado_service.domain.Produto;
import br.com.infnet.almoxarifado_service.service.AlmoxarifadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/almoxarifado")
public class AlmoxarifadoController {

    private final AlmoxarifadoService almoxarifadoService;

    public AlmoxarifadoController(AlmoxarifadoService almoxarifadoService) {
        this.almoxarifadoService = almoxarifadoService;
    }

    @PostMapping("/processar")
    public ResponseEntity<Object> processarPedido(@RequestBody Produto produto) {
        try {
            almoxarifadoService.processarPedido(produto);
            return ResponseEntity.ok("Pedido processado com sucesso."); // Retorno de sucesso
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Erro ao processar o pedido: " + e.getMessage()); // Retorno de erro
        }
    }


    @GetMapping("/estoque/{id}")
    public ResponseEntity<String> verificarEstoque(@PathVariable Long id) throws Exception {
        Optional<Almoxarifado> almoxarifado = almoxarifadoService.verificarStatusAlmoxarifado(id);
        return almoxarifado.map(value -> ResponseEntity.ok(value.toString())).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping
    public ResponseEntity<List<Almoxarifado>> verificarEstoqueCompleto() {
        List<Almoxarifado> almoxarifados = almoxarifadoService.getAll();
        if (almoxarifados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(almoxarifados);
    }

    @PostMapping
    public void adicionarAlmoxarifado(@RequestBody Almoxarifado almoxarifado) {
        almoxarifadoService.adicionarAlmoxarifado(almoxarifado);
    }

}
