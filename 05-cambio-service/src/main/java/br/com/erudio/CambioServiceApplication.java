package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CambioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CambioServiceApplication.class, args);
		System.out.println("Link para testar: http://localhost:8000/cambio-service/5/USD/BRL");
		System.out.println("Link documentação: http://localhost:8000/v3/api-docs");
		System.out.println("Link Swagger: http://localhost:8000/swagger-ui/index.html");
		System.out.println("Link do ZipKin para monitorar requests: http://localhost:9411/zipkin/?lookback=15m&endTs=1633460640845&limit=10");
	}

}
