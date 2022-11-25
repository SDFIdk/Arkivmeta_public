package dk.dataforsyningen.arkivmeta.apimapper;

import java.util.Objects;
import org.locationtech.jts.geom.Geometry;
import org.springframework.stereotype.Component;

@Component
public class MapperGeometri {
  public String mapGeometri(Geometry geometri) {
    String convertedGeometri;
    if (Objects.nonNull(geometri)) {
      convertedGeometri = geometri.toText();
    } else {
      convertedGeometri = null;
    }
    return convertedGeometri;
  }
}
