package com.dio.clientaddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ClientAddressApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientAddressApplication.class, args);
	}

}
