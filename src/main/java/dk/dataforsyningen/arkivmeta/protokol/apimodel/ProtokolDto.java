package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public abstract class ProtokolDto {
  @Schema(description = "Unik id for protokollen.")
  protected String id;

  @Schema(description = "Protokollens arketype.")
  protected String arketype;

  @Schema(description = "Protokollens titel.")
  protected String titel;

  @Schema(description = "En eventuel anden titel protokollen kan have.")
  protected String alternativtitel;

  @Schema(description = "Gældende fra dette tidspunkt")
  protected LocalDateTime registreringfra;

  @Schema(description = "Ikke mere gældende")
  protected LocalDateTime registreringtil;
  @Schema(description = "Det navn, som entydigt definerer protokollen.")
  protected String uniktdokumentnavn;
  @Schema(description = "Sti til protokollen i filsystem")
  protected String stinavn;

  @Schema(description = "Det geografiske område, ofte en polygon, som kortet ligger indenfor. WKT med SRS = EPSG:4326")
  protected String geometri;

  @Schema(description = "En liste af URL-stier til kortfiler efter IIIF-specifikationen.")
  protected List<String> filer;

  @Schema(description = "Typen af dokumenterne.")
  protected String dokumentsamling;
  @Schema(description = "Gældende eller fra før udskiftning")
  protected String protokoltype;

  public ProtokolDto() {
  }

  public ProtokolDto(String id) {
    this.id = id;
  }


  public ProtokolDto(
          String id,
          String arketype,
          String titel,
          LocalDateTime registreringfra,
          LocalDateTime registreringtil,
          String uniktdokumentnavn,
          String stinavn,
          List<String> filer,
          String dokumentsamling
    ) {
    this.id = id;
    this.arketype = arketype;
    this.titel = titel;
    this.registreringfra = registreringfra;
    this.registreringtil = registreringtil;
    this.uniktdokumentnavn = uniktdokumentnavn;
    this.stinavn = stinavn;
    this.filer = filer;
    this.dokumentsamling = dokumentsamling;
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

   public LocalDateTime getRegistreringfra() {
    return registreringfra;
  }

  public void setRegistreringfra(LocalDateTime registreringfra) {
    this.registreringfra = registreringfra;
  }

  public LocalDateTime getRegistreringtil() {return registreringtil;}

  public void setRegistreringtil(LocalDateTime registreringtil) {this.registreringtil = registreringtil;}

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
  public List<String> getFiler() {return filer;}
  public void setFiler(List<String> filer) {
    this.filer = filer;  }

  public String getDokumentsamling() {
    return dokumentsamling;
  }

  public void setDokumentsamling(String dokumentsamling) {
    this.dokumentsamling = dokumentsamling;
  }
}