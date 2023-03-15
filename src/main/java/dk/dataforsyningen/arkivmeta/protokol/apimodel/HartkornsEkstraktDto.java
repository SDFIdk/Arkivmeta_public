package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class HartkornsEkstraktDto extends ProtokolDto {

    @Schema(description = "Herredets geometri i WKB format")
    private String geometri;
    @Schema(description = "Navn på herredet")
    private String herredsnavn;
    @Schema(description = "Nummer")
    private BigDecimal herredsnummer;
    @Schema(description = "Yngre eller ældre type")
    private String protokoltype;

    public HartkornsEkstraktDto(){}

    public HartkornsEkstraktDto(
            String id,
            String arketype,
            String titel,
            LocalDateTime registreringfra,
            LocalDateTime registreringtil,
            String uniktdokumentnavn,
            String stinavn,
            String geometri,
            List<String> filer,
            String dokumentsamling,
            String herredsnavn,
            BigDecimal herredsnummer,
            String protokoltype) {
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
         this.geometri = geometri;
         this.herredsnavn = herredsnavn;
         this.herredsnummer = herredsnummer;
         this.protokoltype = protokoltype;
    }

    public String getGeometri() {
        return geometri;
    }

    public void setGeometri(String geometri) {
        this.geometri = geometri;
    }

    public String getHerredsnavn() {
        return herredsnavn;
    }

    public void setHerredsnavn(String herredsnavn) {
        this.herredsnavn = herredsnavn;
    }

    public BigDecimal getHerredsnummer() {return herredsnummer;}
    public void setHerredsnummer(BigDecimal herredsnummer) {this.herredsnummer = herredsnummer; }

    public String getProtokoltype() {
        return protokoltype;
    }

    public void setProtokoltype(String protokoltype) {
        this.protokoltype = protokoltype;
    }
}
