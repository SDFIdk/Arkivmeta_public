package dk.dataforsyningen.arkivmeta.dokument.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;

public class DokumentDto {
  @Schema(description = "Unik id for dokumentet.")
  private String id;

  @Schema(description = "Dokumentes arketype.")
  private String arketype;

  @Schema(description = "Dokumentes titel.")
  private String titel;

  @Schema(description = "Typisk på formen sp+nummer")
  private String alternativtitel;

  @Schema(description = "Gældende fra dette tidspunkt")
  private LocalDateTime registreringfra;

  @Schema(description = "Ikke mere gældende")
  private LocalDateTime registreringtil;

  @Schema(description = "Det navn, som entydigt definerer dokumentet.")
  private String uniktdokumentnavn;

  @Schema(description = "Sti til dokumentet i filsystem")
  private String stinavn;

  @Schema(description = "Bemærkning til dokumentet")
  private String bemaerkning;

  @Schema(description = "Det geografiske område, ofte en polygon, som kortet ligger indenfor. WKT med SRS = EPSG:4326")
  private String geometri;

  @Schema(description = "Hvilket område dokumentet dækker over")
  private String omraade;

  @Schema(description = "En liste af URL-stier til kortfiler efter IIIF-specifikationen.")
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

  @Schema(description = "Nummer på sognet")
  private Long sogneid;

  @Schema(description = "Navn på sognet")
  private String sognenavn;

  public DokumentDto() {
  }

  public DokumentDto(String id, String arketype, String titel, String alternativtitel,
                     LocalDateTime registreringfra, LocalDateTime registreringtil,
                     String uniktdokumentnavn, String stinavn, String bemaerkning,
                     String geometri, String omraade, List<String> filer, String datatype,
                     String filtype, String dokumentsamling, String herredsnavn,
                     Long herredsnummer, String protokoltype, Long sogneid,
                     String sognenavn) {
    this.id = id;
    this.arketype = arketype;
    this.titel = titel;
    this.alternativtitel = alternativtitel;
    this.registreringfra = registreringfra;
    this.registreringtil = registreringtil;
    this.uniktdokumentnavn = uniktdokumentnavn;
    this.stinavn = stinavn;
    this.bemaerkning = bemaerkning;
    this.geometri = geometri;
    this.omraade = omraade;
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

  public String getArketype() {
    return arketype;
  }

  public void setArketype(String arketype) {
    this.arketype = arketype;
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

  public LocalDateTime getRegistreringfra() {
    return registreringfra;
  }

  public void setRegistreringfra(LocalDateTime registreringfra) {
    this.registreringfra = registreringfra;
  }

  public LocalDateTime getRegistreringtil() {
    return registreringtil;
  }

  public void setRegistreringtil(LocalDateTime registreringtil) {
    this.registreringtil = registreringtil;
  }

  public String getUniktdokumentnavn() {
    return uniktdokumentnavn;
  }

  public void setUniktdokumentnavn(String uniktdokumentnavn) {
    this.uniktdokumentnavn = uniktdokumentnavn;
  }

  public String getStinavn() {
    return stinavn;
  }

  public void setStinavn(String stinavn) {
    this.stinavn = stinavn;
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

  public String getOmraade() {
    return omraade;
  }

  public void setOmraade(String omraade) {
    this.omraade = omraade;
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

  public Long getSogneid() {
    return sogneid;
  }

  public void setSogneid(Long sogneid) {
    this.sogneid = sogneid;
  }

  public String getSognenavn() {
    return sognenavn;
  }

  public void setSognenavn(String sognenavn) {
    this.sognenavn = sognenavn;
  }
}