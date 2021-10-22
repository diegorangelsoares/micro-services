package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PedidoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidoServiceApplication.class, args);
		System.out.println("Link do swagger: http://localhost:8606/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/");
		System.out.println("Link do swagger Gateway: http://localhost:8765/webjars/swagger-ui/index.html?configUrl=%2Fv3%2Fapi-docs%2Fswagger-config&urls.primaryName=pedido-service");

	}

}
