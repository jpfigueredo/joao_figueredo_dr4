package br.com.infnet.transporte_service;

import org.springframework.boot.SpringApplication;

public class TestTransporteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(TransporteServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
