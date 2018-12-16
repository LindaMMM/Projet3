package fr.bicomat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;



@EnableJpaRepositories(basePackages = {"fr.bicomat.dao","fr.bicomat.Auth.dao"})
@SpringBootApplication
@EnableScheduling
public class AgenceApplication {

	private static final Logger log = LoggerFactory.getLogger(AgenceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AgenceApplication.class, args);
	}

}
