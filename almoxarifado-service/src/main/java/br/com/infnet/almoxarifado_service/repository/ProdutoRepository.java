package br.com.infnet.almoxarifado_service.repository;

import br.com.infnet.almoxarifado_service.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {}