package dk.dataforsyningen.arkivmeta.mapperhelper;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class MapperFiler {

  public MapperFiler() {
  }

  /**
   * Uses Base64url encoding so there is no / in the encoded String but instead _
   * https://www.baeldung.com/java-base64-encode-and-decode#3-java-8-url-encoding
   *
   * @param kortFiler
   * @return
   */
  public List<String> mapFiler(String kortFiler) {
    List<String> filer = List.of();

    if (kortFiler != null) {
      filer = List.of(kortFiler.split("::"));
    }

    List<String> resultMapFiler = filer.stream()
        .map(s -> s.replace('\\', '/'))
        .map(s -> '/' + Base64.getUrlEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8)))
        .map(s -> System.getenv().get("ARKIVKORT_URL") + s)
        .collect(Collectors.toList());

    return resultMapFiler;
  }
}
