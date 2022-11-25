package dk.dataforsyningen.arkivmeta.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  final String securitySchemeNameQuery = "QueryToken";
  final String securitySchemeNameHeader = "HeaderToken";


  /**
   * @return OpenAPI custom object
   */
  @Bean
  public OpenAPI customImplementation() {
    return new OpenAPI()
        .info(new Info()
            .title("Arkivmeta")
            .version("1.0.11")
            .description("""
                APIet __Arkivmeta__ giver adgang til at søge i metadata for en større samling historiske kort og benytte resultatet til at fremvise det skannede materiale.
                                        
                Til adgang benyttes Kortforsyningens brugeradgang som ved andre tjenester.
                                        
                Stier til kortfiler følger [IIIF specifikationen](https://iiif.io/) og kan vises med en viser, der understøtter dette.
                """))
        // AddSecurityItem section applies created scheme/paths globally
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeNameHeader))
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeNameQuery))
        // Components section defines Security Scheme
        .components(new Components()
            .addSecuritySchemes(securitySchemeNameHeader, new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("token"))
            .addSecuritySchemes(securitySchemeNameQuery, new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.QUERY)
                .name("token")));
  }
}