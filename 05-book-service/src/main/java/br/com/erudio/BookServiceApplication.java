package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
		System.out.println("Link teste: http://localhost:8100/book-service/5/BRL");
		System.out.println("Link documentação: http://localhost:8100/v3/api-docs");
		System.out.println("Link Swagger: http://localhost:8100/swagger-ui/index.html");
		System.out.println("Link do ZipKin para monitorar requests: http://localhost:9411/zipkin/?lookback=15m&endTs=1633460640845&limit=10");

	}

}
