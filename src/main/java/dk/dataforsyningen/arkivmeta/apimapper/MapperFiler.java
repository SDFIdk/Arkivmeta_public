package dk.dataforsyningen.arkivmeta.apimapper;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperFiler
{
    /**
     * Uses Base64url encoding so there is no / in the encoded String but instead _
     * https://www.baeldung.com/java-base64-encode-and-decode#3-java-8-url-encoding
     *
     * @param kortFiler
     * @return
     */
    public List<String> mapFiler(String kortFiler)
    {
        List<String> filer = List.of();

        if (kortFiler != null)
        {
            filer = List.of(kortFiler.split("::"));
        }

        List<String> resultMapFiler = filer.stream()
                .map(s -> s.replace('\\', '/'))
                .map(s -> '/' + Base64.getUrlEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8)))
                .collect(Collectors.toList());

        return resultMapFiler;
    }
}
