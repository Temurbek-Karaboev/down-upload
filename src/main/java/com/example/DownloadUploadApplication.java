package com.example;

import com.example.model.User;
import com.example.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import java.time.Duration;
import java.util.Arrays;

@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
public class DownloadUploadApplication {
	private static final Logger log = LoggerFactory.getLogger(DownloadUploadApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DownloadUploadApplication.class, args);
	}

	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

		return initializer;
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {

		return (args) -> {
			// save a few customers
			repository.saveAll(Arrays.asList(new User("Jack", "Bauer"),
							new User("Chloe", "O'Brian")))
					.blockLast(Duration.ofSeconds(10));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			repository.findAll().doOnNext(customer -> {
				log.info(customer.toString());
			}).blockLast(Duration.ofSeconds(10));

			log.info("");

			// fetch an individual customer by ID
			repository.findById(1L).doOnNext(customer -> {
				log.info("Customer found with findById(1L):");
				log.info("--------------------------------");
				log.info(customer.toString());
				log.info("");
			}).block(Duration.ofSeconds(10));


//			// fetch customers by last name
//			log.info("Customer found with findByLastName('Bauer'):");
//			log.info("--------------------------------------------");
//			repository.findByLastName("Bauer").doOnNext(bauer -> {
//				log.info(bauer.toString());
//			}).blockLast(Duration.ofSeconds(10));;
//			log.info("");
		};
	}

}
