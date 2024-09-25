package br.com.infnet.transporte_service.infra.repository;

import br.com.infnet.transporte_service.domain.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
