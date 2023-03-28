package dk.dataforsyningen.arkivmeta.dokument.apimapper;

import java.util.Arrays;
import java.util.List;

public class MapperDaekningsomraade {
  public List<String> mapDaekningsomraade(String daekningsomraade) {
    return Arrays.asList(daekningsomraade.split("::"));
  }
}
