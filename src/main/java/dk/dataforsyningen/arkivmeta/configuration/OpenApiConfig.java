package dk.dataforsyningen.arkivmeta.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDocumentationConfig
{
    /**
     *
     * @return OpenAPI custom object
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
