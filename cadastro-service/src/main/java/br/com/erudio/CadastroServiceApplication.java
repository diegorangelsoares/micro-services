package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CadastroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroServiceApplication.class, args);
		System.out.println("Link do swagger: http://localhost:8308/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/");
	}

}
