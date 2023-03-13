package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

public class SogneprotokolDto extends ProtokolDto {


    @Schema(description = "Typisk på formen sp+nummer")
    private String alternativtitel;
    @Schema(description = "Herredets geometri i WKB format")
    private String geometri;
    @Schema(description = "Navn på herredet")
    private String herredsnavn;
    @Schema(description = "Yngre eller ældre type")
    private String protokoltype;
    @Schema(description = "Navn på sognet")
    private String sognenavn;
    @Schema(description = "Nummer på sognet")
    private int sogneid;


    public SogneprotokolDto(){}

    public SogneprotokolDto(
            String id,
            String arketype,
            String titel,
            String alternativtitel,
            LocalDateTime registreringfra,
            LocalDateTime registreringtil,
            String uniktdokumentnavn,
            String stinavn,
            String geometri,
            List<String> filer,
            String dokumentsamling,
            String herredsnavn,
            String protokoltype,
            String sognenavn,
            int sogneid) {
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
        this.alternativtitel = alternativtitel;
        this.geometri = geometri;
        this.herredsnavn = herredsnavn;
        this.protokoltype = protokoltype;
        this.sognenavn = sognenavn;
        this.sogneid = sogneid;
    }

    public String getAlternativtitel() {
        return alternativtitel;
    }

    public void setAlternativtitel(String alternativtitel) {
        this.alternativtitel = alternativtitel;
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

    public int getSogneid() {return sogneid;}

    public void setSogneid(int sogneid) {this.sogneid = sogneid;}
}
