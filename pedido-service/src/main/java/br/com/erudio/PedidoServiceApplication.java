package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PedidoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoServiceApplication.class, args);
		System.out.println("Link do swagger: http://localhost:8606/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/");
	}

}
