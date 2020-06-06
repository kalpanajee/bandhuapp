package com.fisglobal.config;

import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerDocumentationConfig {
	
	@Bean
	public Docket newsApi() {

		return new Docket(DocumentationType.SWAGGER_2).groupName("Nvty_Svc").apiInfo(apiInfo()).select()
				.paths(regex("/v1.0.0/Nvlty/NvltyService.*")).build();
	}
	
	// /swagger-ui.html
		private ApiInfo apiInfo() {
			return new ApiInfoBuilder().title("Novelty with Swagger").description("Novelty Rest API with Swagger")
					.termsOfServiceUrl("http://www-03.ibm.com/software/sla/sladb.nsf/sla/bm?Open").contact("FIS IT")
					.license("Apache License Version 2.0")
					.licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE").version("2.0")
					.build();
		}
}
