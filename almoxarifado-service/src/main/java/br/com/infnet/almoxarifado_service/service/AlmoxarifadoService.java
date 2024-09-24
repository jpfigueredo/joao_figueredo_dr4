package br.com.infnet.almoxarifado_service.service;

import br.com.infnet.almoxarifado_service.domain.Almoxarifado;
import br.com.infnet.almoxarifado_service.domain.Produto;
import br.com.infnet.almoxarifado_service.repository.AlmoxarifadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlmoxarifadoService {

    @Autowired
    private AlmoxarifadoRepository almoxarifadoRepository;

    public void processarPedido(Produto produto) {
        if(produto == null){throw new RuntimeException("Produto inválido.");}
        Almoxarifado almoxarifado = almoxarifadoRepository.findById(produto.getId())
                .orElseThrow(() -> new RuntimeException("Produto esgotado."));
        almoxarifadoRepository.save(almoxarifado);
    }

    public List<Almoxarifado> getAll(){
        return almoxarifadoRepository.findAll();
    }

    public Optional<Almoxarifado> verificarStatusAlmoxarifado(Long id) throws Exception {
        Optional<Almoxarifado> almoxarifado = almoxarifadoRepository.findById(id);
        if(!almoxarifado.isPresent()){
            throw new Exception("Almoxarifado não encontrado.");
        }
        return almoxarifado;
    }

    public void adicionarAlmoxarifado(Almoxarifado almoxarifado) {
        almoxarifadoRepository.save(almoxarifado);
    }
}
