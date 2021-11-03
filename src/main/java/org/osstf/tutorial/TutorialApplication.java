package org.osstf.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Tutorial micro service
 */
@SpringBootApplication
public class TutorialApplication {

	/**
	 * @return Web Client Builder
	 */
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		return WebClient.builder();
	}

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TutorialApplication.class, args);
	}

}
