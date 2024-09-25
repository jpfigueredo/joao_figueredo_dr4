package br.com.infnet.transporte_service.infra.repository;

import br.com.infnet.transporte_service.domain.ManifestoDeCarga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ManifestoDeTransporteRepository extends JpaRepository<ManifestoDeCarga, Long> {
    @Query("SELECT m FROM ManifestoDeCarga m WHERE m.emEdicao = true")
    Optional<ManifestoDeCarga> findEmEdicao();
}
