package dk.dataforsyningen.arkivmeta.kort.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class KortResult {
  @Schema(description = "Totalt antal af kort i listen.")
  public Long total;

  @Schema(description = "Liste med kort.")
  public List<KortDto> kort;

  public KortResult(Long total, List<KortDto> kort) {
    this.total = total;
    this.kort = kort;
  }
}
