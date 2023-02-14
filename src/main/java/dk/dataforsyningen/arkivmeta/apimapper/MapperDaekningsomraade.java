package dk.dataforsyningen.arkivmeta.apimapper;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

public class MapperDaekningsomraade {
  public List<String> mapDaekningsomraade(String daekningsomraade) {
    return Arrays.asList(daekningsomraade.split("::"));
  }
}
