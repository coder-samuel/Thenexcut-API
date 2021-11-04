package br.com.thenextcut.barbershop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ThenextcutBarbershopApplication {

	private static Logger logger = LoggerFactory.getLogger(ThenextcutBarbershopApplication.class);
	public static void main(String[] args) {
		logger.info("########################### - Iniciando aplicacao - ###########################");
		SpringApplication.run(ThenextcutBarbershopApplication.class, args);
	}

}
