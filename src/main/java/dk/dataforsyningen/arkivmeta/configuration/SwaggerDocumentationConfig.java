package dk.dataforsyningen.arkivmeta.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
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
        String APIinfoString = "";

        APIinfoString = getAPIinfoDescription();

        return new OpenAPI()
                .info(new Info()
                              .title("Arkivmeta")
                              .version("1.0.11")
                              .description(APIinfoString)
                              .termsOfService("https://kortforsyningen.dk/")
                              .contact(new Contact().name("SDFE Support").url(
                                      "https://kortforsyningen.dk/").email(
                                      "support@sdfe.dk"))
                              .license(new License().name("Licensbetingelser")
                                               .url("https://kortforsyningen.dk/indhold/vilkaar-og-betingelser"))
                              .license(new License().name("Rettigheder for historiske søkort")
                                      .url("https://kortforsyningen.dk/sites/default/files/soekort_disclaimer.pdf")));
    }

    private String getAPIinfoDescription()
    {
        try
        {
            File file = ResourceUtils.getFile("src/main/resources/APIinfo.md");
            String APIinfoDescription = new String(Files.readAllBytes(file.toPath()));
            return APIinfoDescription;
        }
        catch (IOException ioException)
        {
            throw new RuntimeException(ioException);
        }
    }
}
