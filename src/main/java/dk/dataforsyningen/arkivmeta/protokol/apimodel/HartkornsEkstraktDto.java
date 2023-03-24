package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class HartkornsEkstraktDto extends ProtokolDto {

    @Schema(description = "Navn på herredet")
    private String herredsnavn;
    @Schema(description = "Nummer")
    private BigDecimal herredsnummer;
    @Schema(description = "Yngre eller ældre type")
    private String protokoltype;

    public HartkornsEkstraktDto(){}

    public HartkornsEkstraktDto(String id, String arketype, String titel, LocalDateTime registreringfra, LocalDateTime registreringtil, String uniktdokumentnavn, String stinavn, String bemaerkning, String geometri, List<String> filer, String dokumentsamling, String protokoltype, String herredsnavn, BigDecimal herredsnummer, String protokoltype1) {
        super(id, arketype, titel, registreringfra, registreringtil, uniktdokumentnavn, stinavn, bemaerkning, geometri, filer, dokumentsamling, protokoltype);
        this.herredsnavn = herredsnavn;
        this.herredsnummer = herredsnummer;
        this.protokoltype = protokoltype1;
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
