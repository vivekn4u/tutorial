package org.osstf.tutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Tutorial micro service
 */
@SpringBootApplication
public class TutorialApplication {

	Logger logger = LoggerFactory.getLogger(TutorialApplication.class);
	/**
	 * @return Web Client Builder
	 */
	@Bean
	public WebClient.Builder getWebClientBuilder(){
		logger.info("[getWebClientBuilder] - started");
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
