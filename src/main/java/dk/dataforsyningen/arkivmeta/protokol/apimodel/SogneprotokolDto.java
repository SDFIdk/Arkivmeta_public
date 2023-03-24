package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SogneprotokolDto extends ProtokolDto {

    @Schema(description = "Typisk på formen sp+nummer")
    private String alternativtitel;
    @Schema(description = "Navn på herredet")
    private String herredsnavn;
    @Schema(description = "Yngre eller ældre type")
    private String protokoltype;
    @Schema(description = "Navn på sognet")
    private String sognenavn;
    @Schema(description = "Nummer på sognet")
    private BigDecimal sogneid;

    public SogneprotokolDto(){}

    public SogneprotokolDto(String id, String arketype, String titel, LocalDateTime registreringfra, LocalDateTime registreringtil, String uniktdokumentnavn, String stinavn, String bemaerkning, String geometri, List<String> filer, String dokumentsamling, String protokoltype, String alternativtitel, String herredsnavn, String protokoltype1, String sognenavn, BigDecimal sogneid) {
        super(id, arketype, titel, registreringfra, registreringtil, uniktdokumentnavn, stinavn, bemaerkning, geometri, filer, dokumentsamling, protokoltype);
        this.alternativtitel = alternativtitel;
        this.herredsnavn = herredsnavn;
        this.protokoltype = protokoltype1;
        this.sognenavn = sognenavn;
        this.sogneid = sogneid;
    }

    public String getAlternativtitel() {
        return alternativtitel;
    }

    public void setAlternativtitel(String alternativtitel) {
        this.alternativtitel = alternativtitel;
    }

    public String getHerredsnavn() {
        return herredsnavn;
    }

    public void setHerredsnavn(String herredsnavn) {
        this.herredsnavn = herredsnavn;
    }

    public String getProtokoltype() {
        return protokoltype;
    }

    public void setProtokoltype(String protokoltype) {
        this.protokoltype = protokoltype;
    }

    public String getSognenavn() {
        return sognenavn;
    }

    public void setSognenavn(String sognenavn) {
        this.sognenavn = sognenavn;
    }

    public BigDecimal getSogneid() {return sogneid;}

    public void setSogneid(BigDecimal sogneid) {this.sogneid = sogneid;}
}
