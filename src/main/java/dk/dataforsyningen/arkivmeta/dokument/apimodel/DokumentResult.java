package dk.dataforsyningen.arkivmeta.dokument.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class DokumentResult {
    @Schema(description = "Totalt antal af dokumenter i listen.")
    public Long total;

    @Schema(description = "Liste med dokumenter.")
    public List<DokumentDto> dokumenter;

    public DokumentResult(Long total, List<DokumentDto> dokumenter) {
        this.total = total;
        this.dokumenter = dokumenter;
    }
}
