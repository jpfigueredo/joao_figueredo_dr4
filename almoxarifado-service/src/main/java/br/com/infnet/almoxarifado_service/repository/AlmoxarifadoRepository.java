package br.com.infnet.almoxarifado_service.repository;

import br.com.infnet.almoxarifado_service.domain.Almoxarifado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlmoxarifadoRepository extends JpaRepository<Almoxarifado, Long> {}
