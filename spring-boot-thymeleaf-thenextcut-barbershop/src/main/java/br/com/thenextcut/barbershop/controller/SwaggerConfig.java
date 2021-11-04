package br.com.thenextcut.barbershop.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/**")).build().apiInfo(apiInfo());
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.contact(new Contact("Samuel de Brito Santos", "https://www.linkedin.com/in/samuel-de-brito-santos/", "samuelsantos.ss95@gmail.com"))
				.title("The next cut Barbershop")
				.description("Documentação API The next cut Barbershop")
				.license("Apache Licence Version 2.0")
				.licenseUrl("https://apache.org")
				.version("1.0")
				.build();
		
	}

}