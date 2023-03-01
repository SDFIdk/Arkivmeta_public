package dk.dataforsyningen.arkivmeta.protokol.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public class HartkornsEkstrakt extends ProtokolDto {
  @Schema(description = "skal udfyldes")
  private Integer herredsnummer;

  public HartkornsEkstrakt(Integer herredsnummer) {
    this.herredsnummer = herredsnummer;
  }

  public HartkornsEkstrakt(String id, String arketype, String titel, String alternativtitel,
                           LocalDateTime registreringfra,
                           LocalDateTime registreringtil,
                           String uniktdokumentnavn, String stinavn, String bemaerkning,
                           String geometri, String filer, String datatype, String filtype,
                           String dokumentsamling, String herredsnavn, String protokoltype,
                           String sognenavn, Integer herredsnummer) {
    super(id, arketype, titel, alternativtitel, registreringfra, registreringtil, uniktdokumentnavn,
        stinavn, bemaerkning, geometri, filer, datatype, filtype, dokumentsamling, herredsnavn,
        protokoltype, sognenavn);
    this.herredsnummer = herredsnummer;
  }

  public Integer getHerredsnummer() {
    return herredsnummer;
  }

  public void setHerredsnummer(Integer herredsnummer) {
    this.herredsnummer = herredsnummer;
  }
}
