package br.com.infnet.almoxarifado_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlmoxarifadoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlmoxarifadoServiceApplication.class, args);
	}

}
