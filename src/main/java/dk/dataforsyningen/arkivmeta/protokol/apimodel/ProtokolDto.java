package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;

public abstract class ProtokolDto {
  @Schema(description = "Unik id for protokollen.")
  protected String id;

  protected String arketype;
  protected String titel;
  protected String alternativtitel;
  protected String registreringfra;
  protected String registreringtil;
  protected String uniktdokumentnavn;
  protected String stinavn;
  protected String bemaerkning;
  protected String geometri;
  protected String filer;
  protected String datatype;
  protected String filtype;
  protected String dokumentsamling;
  protected String protokoltype;

}
