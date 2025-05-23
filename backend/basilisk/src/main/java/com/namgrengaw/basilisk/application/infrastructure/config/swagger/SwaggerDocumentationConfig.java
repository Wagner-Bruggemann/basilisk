package com.namgrengaw.basilisk.application.infrastructure.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDocumentationConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Basilisk")
                        .description("Documentation platform for endpoints available in the Basilisk API's")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Wagner Brüggemann")
                                .email("wagner.bruggemann@hotmail.com")
                                .url("https://namgrengaw.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }

}
