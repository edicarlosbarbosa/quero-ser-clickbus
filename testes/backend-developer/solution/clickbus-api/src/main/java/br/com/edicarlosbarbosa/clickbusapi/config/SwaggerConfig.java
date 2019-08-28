package br.com.edicarlosbarbosa.clickbusapi.config;

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
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/v1/**"))
                .build();
    }

    private ApiInfo metaData() {
        Contact contact = new Contact("Edicarlos Barbosa", "https://www.clickbus.com.br/", "edicarlosbarbosa@gmail.com");
        return new ApiInfoBuilder()
                .title("ClickBus API")
                .description("API for ClickBus Test")
                .version("1.0.0")
                .license("Apache 2.0")
                .contact(contact)
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

}
