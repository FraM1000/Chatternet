package com.chatternet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.chatternet.model.dao")
@SpringBootApplication(scanBasePackages= {"com.chatternet","com.chatternet.controller","com.chatternet.model","com.chatternet.security","com.chatternet.images","com.chatternet.model.bean","com.chatternet.model.dto","com.chatternet.model.dao","com.chatternet.controller.service"})
public class ChatternetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatternetApplication.class, args);
	}

}
