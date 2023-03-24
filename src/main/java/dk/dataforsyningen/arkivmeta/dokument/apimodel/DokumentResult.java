package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class ProtokolResult {
    @Schema(description = "Totalt antal af dokumenter i listen.")
    public Long total;

    @Schema(description = "Liste med dokumenter.")
    public List<ProtokolDto> dokumenter;

    public ProtokolResult(Long total, List<ProtokolDto> dokumenter) {
        this.total = total;
        this.dokumenter = dokumenter;
    }
}
