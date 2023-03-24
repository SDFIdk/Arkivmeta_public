package dk.dataforsyningen.arkivmeta.dokument.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class DokumentParam {

    @Schema(description = "Herredets navn.")
    private String herredsnavn;
    @Schema(description = "Herredets nummer.")
    private Integer herredsnummer;
    @Schema(description = "Sognenavn.")
    private String sognenavn;
    @Schema(description = "Sogneid.")
    private Integer sogneid;
    @Schema(description = "De dokumenttyper, der skal vises. En kommasepareret liste af typer. " +
                    "Eksempel: `Hartkornsekstrakter, Sogneprotokoller.`")
    private List<String> dokumentsamling;

    @Schema(description = "Geometri angives som WKT med SRS = EPSG:4326. Det geografiske område, ofte en polygon, som kortet ligger indenfor.")
    private String geometri;

    @Schema(description = "Sidestørrelse, dvs. hvor mange poster pr. side", defaultValue = "100")
    @Min(1)
    @Max(1000)
    private Integer limit;

    @Schema(description = "Offset, dvs. fra hvilken post", defaultValue = "0")
    private Integer offset;
    @Schema(description = "Sorteringsfelt, kan sortere på følgende typer: arketype, daekningsomraade, gaeldendefra, gaeldendetil, id, kortvaerk, maalestok, titel")
    private String sort;
    @Schema(description = "Sorteringsretning, `asc` for stigende, `desc` for faldende", defaultValue = "asc")
    @Pattern(regexp = "asc|desc")
    // How @Pattern works: https://stackoverflow.com/questions/4922655/javax-validation-to-validate-list-of-values
    private String direction;


    public DokumentParam(String herredsnavn, Integer herredsnummer, String sognenavn, Integer sogneid, List<String> dokumentsamling,
                         String geometri, Integer limit, Integer offset, String sort, String direction) {
        this.herredsnavn = herredsnavn;
        this.herredsnummer = herredsnummer;
        this.sognenavn = sognenavn;
        this.sogneid = sogneid;
        this.dokumentsamling = dokumentsamling;
        this.geometri = geometri;
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
        this.direction = direction;
    }

    public String getHerredsnavn() {
        return herredsnavn;
    }
    public void setHerredsnavn(String herredsnavn) {
        this.herredsnavn = herredsnavn;
    }
    public Integer getHerredsnummer() {return herredsnummer;}
    public void setHerredsnummer(Integer herredsnummer) {
        this.herredsnummer = herredsnummer;
    }
    public String getSognenavn() {
        return sognenavn;
    }
    public void setSognenavn(String sognenavn) {
        this.sognenavn = sognenavn;
    }
    public Integer getSogneid() {
        return sogneid;
    }
    public void setSogneid(Integer sogneid) {
        this.sogneid = sogneid;
    }
    public List<String> getDokumentsamling() {
        return dokumentsamling;
    }
    public void setDokumentsamling(List<String> dokumentsamling) {
        this.dokumentsamling = dokumentsamling;
    }
    public String getGeometri() {
        return geometri;
    }
    public void setGeometri(String geometri) {
        this.geometri = geometri;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
}
