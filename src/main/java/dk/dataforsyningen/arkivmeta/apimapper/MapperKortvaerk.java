package dk.dataforsyningen.arkivmeta.apimapper;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;

public class MapperKortvaerk {
  public List<String> mapKortvaerk(String kortvaerk) {
    return Arrays.asList(kortvaerk.split(";;"));
  }
}
