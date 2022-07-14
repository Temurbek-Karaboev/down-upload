package com.example;

import com.example.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.connectionfactory.init.ConnectionFactoryInitializer;
import org.springframework.data.r2dbc.connectionfactory.init.ResourceDatabasePopulator;
import java.time.Duration;
import java.util.Arrays;

public class DownloadUploadApplication {
	private static final Logger log = LoggerFactory.getLogger(DownloadUploadApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DownloadUploadApplication.class, args);
	}


}
