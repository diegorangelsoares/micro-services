package br.com.erudio.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
		System.out.println("Link de acesso cambio: http://localhost:8765/cambio-service/8/USD/CLP");
		System.out.println("Link de acesso book: http://localhost:8765/book-service/5/BRL");
		System.out.println("Link de acesso cadastro: http://localhost:8308/cadastro-service/");
		System.out.println("Link do Swagger: http://localhost:8765/webjars/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/");
		System.out.println("Link do ZipKin para monitorar requests: http://localhost:9411/zipkin/?lookback=15m&endTs=1633460640845&limit=10");

	}

}
