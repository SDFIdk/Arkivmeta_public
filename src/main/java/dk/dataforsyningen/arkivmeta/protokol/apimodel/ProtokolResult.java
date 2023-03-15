package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class ProtokolResult {
    @Schema(description = "Totalt antal af kort i listen.")
    public Long total;

    @Schema(description = "Liste med kort.")
    public List<ProtokolDto> kort;

    public ProtokolResult(Long total, List<ProtokolDto> kort) {
        this.total = total;
        this.kort = kort;
    }
}
