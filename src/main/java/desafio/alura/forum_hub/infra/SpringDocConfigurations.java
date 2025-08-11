package desafio.alura.forum_hub.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fórum Hub API")
                        .description(
                                "API Rest da aplicação Fórum Hub, contendo as funcionalidades de CRUD de usuários, cursos e tópicos.")
                        .contact(new Contact()
                                .name("Robson Durães"))
                        .license(new License()
                                .name("Apache 2.0")));
    }

}
