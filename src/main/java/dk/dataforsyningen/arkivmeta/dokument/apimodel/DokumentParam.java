package dk.dataforsyningen.arkivmeta.dokument.apimodel;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import java.util.List;

public class DokumentParam {
  @Schema(description = "Sorteringsretning, `asc` for stigende, `desc` for faldende", defaultValue = "asc")
  @Pattern(regexp = "asc|desc")
  // How @Pattern works: https://stackoverflow.com/questions/4922655/javax-validation-to-validate-list-of-values
  private String direction;

  @Schema(description = "Fritekstsøgning")
  private String fritekstsoegning;

  @Schema(description = "Geometri angives som WKT med SRS = EPSG:4326. Det geografiske område, ofte en polygon, som kortet ligger indenfor.")
  private String geometri;

  @Schema(description = "Herredets navn.")
  private String herredsnavn;

  @Schema(description = "Herredets nummer.")
  private Integer herredsnummer;

  @ArraySchema(arraySchema = @Schema(description =
      "De dokumenttyper, der skal vises. En kommasepareret liste af typer. Eksempel: `Hartkornsekstrakt, Sogneprotokol.`"))
  private List<String> kortgruppe;

  @Schema(description = "Sidestørrelse, dvs. hvor mange poster pr. side", defaultValue = "100")
  @Min(1)
  @Max(1000)
  private Integer limit;

  @Min(0)
  @Schema(description = "Offset, dvs. fra hvilken post", defaultValue = "0")
  private Integer offset;

  @Schema(description = "Sogneid.")
  private Integer sogneid;

  @Schema(description = "Sognenavn.")
  private String sognenavn;

  @Schema(description = "Sorteringsfelt, kan sortere på følgende typer: dokumentsamling, herredsnavn, herredsnummer, sogneid, sognenavn, titel")
  private String sort;

  @Schema(description = "Titel på dokumentet.")
  private String titel;

  public DokumentParam(String direction, String fritekstsoegning, String geometri,
                       String herredsnavn,
                       Integer herredsnummer, List<String> kortgruppe, Integer limit,
                       Integer offset,
                       Integer sogneid, String sognenavn, String sort, String titel) {
    this.direction = direction;
    this.fritekstsoegning = fritekstsoegning;
    this.geometri = geometri;
    this.herredsnavn = herredsnavn;
    this.herredsnummer = herredsnummer;
    this.kortgruppe = kortgruppe;
    this.limit = limit;
    this.offset = offset;
    this.sogneid = sogneid;
    this.sognenavn = sognenavn;
    this.sort = sort;
    this.titel = titel;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String getFritekstsoegning() {
    return fritekstsoegning;
  }

  public void setFritekstsoegning(String fritekstsoegning) {
    this.fritekstsoegning = fritekstsoegning;
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

  public Integer getHerredsnummer() {
    return herredsnummer;
  }

  public void setHerredsnummer(Integer herredsnummer) {
    this.herredsnummer = herredsnummer;
  }

  public List<String> getKortgruppe() {
    return kortgruppe;
  }

  public void setKortgruppe(List<String> kortgruppe) {
    this.kortgruppe = kortgruppe;
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

  public Integer getSogneid() {
    return sogneid;
  }

  public void setSogneid(Integer sogneid) {
    this.sogneid = sogneid;
  }

  public String getSognenavn() {
    return sognenavn;
  }

  public void setSognenavn(String sognenavn) {
    this.sognenavn = sognenavn;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public String getTitel() {
    return titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }
}
