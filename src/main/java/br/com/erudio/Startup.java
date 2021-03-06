package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import br.com.erudio.config.FileStorageConfig;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties({ FileStorageConfig.class })
@ComponentScan
public class Startup {
	
	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
		
		// Forma de gerar senha criptografada, para utilizar na api
		// BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		// String result = bCryptPasswordEncoder.encode("admin123");
	}

}
