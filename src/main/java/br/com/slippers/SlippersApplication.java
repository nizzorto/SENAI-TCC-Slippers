package br.com.slippers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@EnableCaching
@EnableSpringDataWebSupport
@SpringBootApplication
public class SlippersApplication {

	
	@Bean
	public WebClient webClientCep(WebClient.Builder builder) {
		return builder
			.baseUrl("https://viacep.com.br/ws/")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SlippersApplication.class, args);
	}
}