package br.com.infnet.transporte_service.infra.service;

import br.com.infnet.transporte_service.domain.Endereco;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    public Endereco getEnderecoByCustomerId(Long customerId) {
        return new Endereco("R. São José, 90", "Rio de Janeiro", "RJ", "20010-020");
    }
}
