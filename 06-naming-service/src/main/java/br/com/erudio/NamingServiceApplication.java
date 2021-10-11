package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class NamingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamingServiceApplication.class, args);
		System.out.println("Link do Eureka: http://localhost:8761/");
	}

}
