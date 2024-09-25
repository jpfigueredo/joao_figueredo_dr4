package br.com.infnet.almoxarifado_service.infra.repository;

import br.com.infnet.almoxarifado_service.domain.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
}
