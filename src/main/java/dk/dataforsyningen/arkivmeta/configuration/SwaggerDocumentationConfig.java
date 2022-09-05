package dk.dataforsyningen.arkivmeta.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
public class SwaggerDocumentationConfig
{
    /**
     * How to add setting for serverUrl: https://github.com/springdoc/springdoc-openapi/issues/89
     *
     * @return
     */
    @Bean
    public OpenAPI customImplementation()
    {
        return new OpenAPI()
                .info(new Info()
                              .title("Arkivmeta")
                              .version("1.0.11"))
                // Components section defines Security Scheme "mySecretHeader"
                .components(new Components()
                                .addSecuritySchemes("ApiKeyAuth", new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.QUERY)
                                        .name("token")))
                // AddSecurityItem section applies created scheme globally
                .addSecurityItem(new SecurityRequirement().addList("ApiKeyAuth"));
    }
}
