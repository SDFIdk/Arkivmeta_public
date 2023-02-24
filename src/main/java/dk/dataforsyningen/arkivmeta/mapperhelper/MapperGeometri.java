package dk.dataforsyningen.arkivmeta.mapperhelper;

import java.util.Objects;
import org.locationtech.jts.geom.Geometry;

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
