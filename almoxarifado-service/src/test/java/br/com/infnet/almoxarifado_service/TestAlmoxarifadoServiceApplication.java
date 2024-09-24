package br.com.infnet.almoxarifado_service;

import org.springframework.boot.SpringApplication;

public class TestAlmoxarifadoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(AlmoxarifadoServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
