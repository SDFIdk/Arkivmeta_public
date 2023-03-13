package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public class KortfortegnelseDto extends ProtokolDto {

    @Schema(description = "pdf")
    private String datatype;
    @Schema(description = "fortegnelse")
    private String filtype;

    public KortfortegnelseDto() {}

    public KortfortegnelseDto(
            String id,
            String arketype,
            String titel,
            LocalDateTime registreringfra,
            LocalDateTime registreringtil,
            String uniktdokumentnavn,
            String stinavn,
            List<String> filer,
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
