package dk.dataforsyningen.arkivmeta.apimapper;

import java.util.Arrays;
import java.util.List;

public class MapperKortvaerk {
  public List<String> mapKortvaerk(String kortvaerk) {
    return Arrays.asList(kortvaerk.split(";;"));
  }
}
