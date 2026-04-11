package org.guerrer0jaguar.inventory.merger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class MergerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MergerApplication.class, args);
	}

}