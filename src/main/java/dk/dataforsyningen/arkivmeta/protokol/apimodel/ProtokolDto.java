package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public abstract class ProtokolDto {
  @Schema(description = "Unik id for protokollen.")
  protected String id;

  @Schema(description = "Protokollens arketype.")
  protected String arketype;

  @Schema(description = "Protokollens titel.")
  protected String titel;

  @Schema(description = "En eventuel anden titel protokollen kan have.")
  protected String alternativtitel;

  @Schema(description = "<mangler beskrivelse>")
  protected LocalDateTime registreringfra;

  @Schema(description = "<mangler beskrivelse>")
  protected LocalDateTime registreringtil;
  @Schema(description = "Det navn, som entydigt definerer protokollen.")
  protected String uniktdokumentnavn;

  @Schema(description = "<mangler beskrivelse>")
  protected String stinavn;

  @Schema(description = "Yderligere kommentarer")
  protected String bemaerkning;

  @Schema(description = "Det geografiske område, ofte en polygon, som kortet ligger indenfor. WKT med SRS = EPSG:4326")
  protected String geometri;

  @Schema(description = "En liste af URL-stier til kortfiler efter IIIF-specifikationen.")
  protected String filer;
  @Schema(description = "Angiver om protokollen findes som pdf.")
  protected String datatype;

  @Schema(description = "Hvis pdf, om filen er en fortegnelse eller en journal")
  protected String filtype;

  @Schema(description = "Typen af dokumenterne.")
  protected String dokumentsamling;

  @Schema(description = "Herredets navn.")
  protected String herredsnavn;

  @Schema(description = "Gældende eller fra før udskiftning")
  protected String protokoltype;

  @Schema(description = "Sognets navn.")
  protected String sognenavn;

  public ProtokolDto() {
  }

  public ProtokolDto(String id) {
    this.id = id;
  }


  public ProtokolDto(
          String id,
          String arketype,
          String titel,
          String alternativtitel,
          LocalDateTime registreringfra,
          LocalDateTime registreringtil,
          String uniktdokumentnavn,
          String stinavn,
          String bemaerkning,
          String geometri,
          String filer,
          String datatype,
          String filtype,
          String dokumentsamling,
          String herredsnavn,
          String protokoltype,
          String sognenavn
  ) {
    this.id = id;
    this.arketype = arketype;
    this.titel = titel;
    this.alternativtitel = alternativtitel;
    this.registreringfra = registreringfra;
    this.registreringtil = registreringfra;
    this.uniktdokumentnavn = uniktdokumentnavn;
    this.stinavn = stinavn;
    this.bemaerkning = bemaerkning;
    this.geometri = geometri;
    this.filer = filer;
    this.datatype = datatype;
    this.filtype = filtype;
    this.dokumentsamling = dokumentsamling;
    this.herredsnavn = herredsnavn;
    this.protokoltype = protokoltype;
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

  public String getFiler() {
    return filer;
  }

  public void setFiler(String filer) {
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

}
