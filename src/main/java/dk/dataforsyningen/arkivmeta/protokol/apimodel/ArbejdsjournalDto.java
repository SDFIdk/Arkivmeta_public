package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

public class ArbejdsjournalDto extends ProtokolDto {
    @Schema(description = "et nummer")
    private String bemaerkning;
    @Schema(description = "pdf")
    private String datatype;
    @Schema(description = "journal")
    private String filtype;

    public ArbejdsjournalDto(){}

     public ArbejdsjournalDto(
            String id,
            String arketype,
            String titel,
            LocalDateTime registreringfra,
            LocalDateTime registreringtil,
            String uniktdokumentnavn,
            String stinavn,
            String bemaerkning,
            List<String> filer,
            String datatype,
            String filtype,
            String dokumentsamling) {
        super(id,
              arketype,
              titel,
              registreringfra,
              registreringtil,
              uniktdokumentnavn,
              stinavn,
              filer,
              dokumentsamling
        );
        this.bemaerkning = bemaerkning;
        this.datatype = datatype;
        this.filtype = filtype;
    }

    public String getBemaerkning() {
        return bemaerkning;
    }

    public void setBemaerkning(String bemaerkning) {
        this.bemaerkning = bemaerkning;
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
