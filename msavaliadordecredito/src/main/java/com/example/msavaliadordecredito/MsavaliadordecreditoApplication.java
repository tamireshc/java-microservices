package com.example.msavaliadordecredito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsavaliadordecreditoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsavaliadordecreditoApplication.class, args);
	}

}
