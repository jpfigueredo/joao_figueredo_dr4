package br.com.infnet.transporte_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class  TransporteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransporteServiceApplication.class, args);
	}

}
