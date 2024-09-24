package br.com.infnet.transporte_service.repository;

import br.com.infnet.transporte_service.domain.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, Long> {}
