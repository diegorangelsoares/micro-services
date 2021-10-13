package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuscacepServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuscacepServiceApplication.class, args);
		System.out.println("Link teste: http://localhost:8404/book-service/5/BRL");
		System.out.println("Link documentação: http://localhost:8404/v3/api-docs");
		System.out.println("Link Swagger: http://localhost:8765/webjars/swagger-ui/index.html?configUrl=%2Fv3%2Fapi-docs%2Fswagger-config&urls.primaryName=cadastro-service#/");
		System.out.println("Link do ZipKin para monitorar requests: http://localhost:9411/zipkin/?lookback=15m&endTs=1633460640845&limit=10");
	}

}
