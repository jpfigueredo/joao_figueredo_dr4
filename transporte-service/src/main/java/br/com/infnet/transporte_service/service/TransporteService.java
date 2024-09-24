package br.com.infnet.transporte_service.service;

import br.com.infnet.transporte_service.domain.Entrega;
import br.com.infnet.transporte_service.domain.Transporte;
import br.com.infnet.transporte_service.repository.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository transporteRepository;

    public void processarPedido(Entrega entrega) {
        Optional<Transporte> transporteOptional = transporteRepository.findById(entrega.getTransporteId());
        if (transporteOptional.isPresent()) {
            Transporte transporte = transporteOptional.get();
            transporte.getEntregas().add(entrega);
            transporte.setDisponivel(false);
            transporteRepository.save(transporte);
            System.out.println("Entrega processada com sucesso.");
        } else {
            System.out.println("Transporte não encontrado.");
        }
    }


    public String verificarStatusTransporte(Long id) {
        Transporte transporte = transporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrega não identificada."));
        return "Entregas disponíveis: " + transporte.getEntregas();
    }

    public void adicionarTransporte(Transporte transporte) {
        transporteRepository.save(transporte);
    }
}
