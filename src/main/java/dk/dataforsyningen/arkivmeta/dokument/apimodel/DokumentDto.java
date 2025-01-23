package dk.dataforsyningen.arkivmeta.dokument.apimodel;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

public class DokumentDto {
  @Schema(description = "Unik id for dokumentet.")
  private String id;

  @Schema(description = "Dokumenters kortgruppe.")
  private String kortgruppe;

  @Schema(description = "Dokumenters titel.")
  private String titel;

  @Schema(description = "Typisk på formen sp+nummer")
  private String alternativtitel;

  @Schema(description = "Bemærkning til dokumentet")
  private String bemaerkning;

  @Schema(description = "Det geografiske område, ofte en polygon, som kortet ligger indenfor. WKT med SRS = EPSG:4326")
  private String geometri;

  @ArraySchema(arraySchema = @Schema(description = "En liste af geografisk områder, som dokumentet dækker helt eller delvist. For eksempel Danmark, Slesvig. "))
  private List<String> daekningsomraade;

  @ArraySchema(arraySchema = @Schema(description = "En liste af URL-stier til kortfiler efter IIIF-specifikationen."))
  private List<String> filer;

  @Schema(description = "Hvilket data format dokumentet er i")
  private String datatype;

  @Schema(description = "fortegnelse")
  private String filtype;

  @Schema(description = "Typen af dokumenterne.")
  private String dokumentsamling;

  @Schema(description = "Navn på herredet")
  private String herredsnavn;

  @Schema(description = "Nummeret på herredet")
  private Long herredsnummer;

  @Schema(description = "Gældende eller fra før udskiftning")
  private String protokoltype;

  @ArraySchema(arraySchema = @Schema(description = "En liste af numre på sogne dokumentet tilhører"))
  private List<Long> sogneid;

  @ArraySchema(arraySchema = @Schema(description = "En liste af navne på sogne dokumentet tilhører"))
  private List<String> sognenavn;

  public DokumentDto() {
  }

  public DokumentDto(String id, String kortgruppe, String titel, String alternativtitel,
                     String bemaerkning, String geometri, List<String> daekningsomraade,
                     List<String> filer, String datatype, String filtype, String dokumentsamling,
                     String herredsnavn, Long herredsnummer, String protokoltype,
                     List<Long> sogneid,
                     List<String> sognenavn) {
    this.id = id;
    this.kortgruppe = kortgruppe;
    this.titel = titel;
    this.alternativtitel = alternativtitel;
    this.bemaerkning = bemaerkning;
    this.geometri = geometri;
    this.daekningsomraade = daekningsomraade;
    this.filer = filer;
    this.datatype = datatype;
    this.filtype = filtype;
    this.dokumentsamling = dokumentsamling;
    this.herredsnavn = herredsnavn;
    this.herredsnummer = herredsnummer;
    this.protokoltype = protokoltype;
    this.sogneid = sogneid;
    this.sognenavn = sognenavn;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getKortgruppe() {
    return kortgruppe;
  }

  public void setKortgruppe(String kortgruppe) {
    this.kortgruppe = kortgruppe;
  }

  public String getTitel() {
    return titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  public String getAlternativtitel() {
    return alternativtitel;
  }

  public void setAlternativtitel(String alternativtitel) {
    this.alternativtitel = alternativtitel;
  }

  public String getBemaerkning() {
    return bemaerkning;
  }

  public void setBemaerkning(String bemaerkning) {
    this.bemaerkning = bemaerkning;
  }

  public String getGeometri() {
    return geometri;
  }

  public void setGeometri(String geometri) {
    this.geometri = geometri;
  }

  public List<String> getDaekningsomraade() {
    return daekningsomraade;
  }

  public void setDaekningsomraade(List<String> daekningsomraade) {
    this.daekningsomraade = daekningsomraade;
  }

  public List<String> getFiler() {
    return filer;
  }

  public void setFiler(List<String> filer) {
    this.filer = filer;
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

  public String getDokumentsamling() {
    return dokumentsamling;
  }

  public void setDokumentsamling(String dokumentsamling) {
    this.dokumentsamling = dokumentsamling;
  }

  public String getHerredsnavn() {
    return herredsnavn;
  }

  public void setHerredsnavn(String herredsnavn) {
    this.herredsnavn = herredsnavn;
  }

  public Long getHerredsnummer() {
    return herredsnummer;
  }

  public void setHerredsnummer(Long herredsnummer) {
    this.herredsnummer = herredsnummer;
  }

  public String getProtokoltype() {
    return protokoltype;
  }

  public void setProtokoltype(String protokoltype) {
    this.protokoltype = protokoltype;
  }

  public List<Long> getSogneid() {
    return sogneid;
  }

  public void setSogneid(List<Long> sogneid) {
    this.sogneid = sogneid;
  }

  public List<String> getSognenavn() {
    return sognenavn;
  }

  public void setSognenavn(List<String> sognenavn) {
    this.sognenavn = sognenavn;
  }
}