package dev.mrkevr.quizapp.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import dev.mrkevr.quizapp.api.model.Question;
import dev.mrkevr.quizapp.api.service.QuestionService;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class AppConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
			.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo())
			.useDefaultResponseMessages(false);
	}

	@Bean
	public ApiInfo apiInfo() {
		final ApiInfoBuilder builder = new ApiInfoBuilder();
		return builder.build();
	}
	
//	@Bean
	public CommandLineRunner runner(QuestionService questionServ) {
		return args -> {
			List<Question> questions = questionServ.getAllById(List.of("64c3b89bf9a8ea22a4bd18dd", "64c3bb87f9a8ea22a4bd18df"));
			questions.forEach(System.out::println);
		};	
	}
}
