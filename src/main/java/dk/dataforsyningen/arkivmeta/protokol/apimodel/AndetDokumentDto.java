package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public class AndetDokumentDto extends ProtokolDto {

    @Schema(description = "pdf")
    private String datatype;
    @Schema(description = "fortegnelse")
    private String filtype;

    public AndetDokumentDto() {}

    public AndetDokumentDto(String id, String arketype, String titel, LocalDateTime registreringfra, LocalDateTime registreringtil, String uniktdokumentnavn, String stinavn, String bemaerkning, String geometri, List<String> filer, String dokumentsamling, String protokoltype, String datatype, String filtype) {
        super(id, arketype, titel, registreringfra, registreringtil, uniktdokumentnavn, stinavn, bemaerkning, geometri, filer, dokumentsamling, protokoltype);
        this.datatype = datatype;
        this.filtype = filtype;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getFiltype() {
        return filtype;
    }

    public void setFiltype(String filtype) {
        this.filtype = filtype;
    }
}
