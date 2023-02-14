package dk.dataforsyningen.arkivmeta.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Objects;

public class KortParam {
  @Schema(description = "Kortets arketype.")
  private List<String> arketype;

  @Schema(description = "Geografisk område, som kortet dækker helt eller delvist. For eksempel Danmark, Grønland. Et kort kan have flere dækningsområder.For prøvekort og lignende vil dækningsområdet kunne angives som intet.")
  private List<String> daekningsomraade;

  @Schema(description = "Sorteringsretning, 'asc' for stigende, 'desc' for faldende")
  private String direction;

  @Schema(description = "Fritekstsøgning")
  private String fritekstsoegning;

  @Schema(description = "Starttid for kortets gyldighedsperiode. Angives i hele år. Gyldighedsperiodens starttid er et korts trykke-, tegne-, optage- eller opmålingsår – dvs. det år hvor kortet kan siges at være nyeste kort.")
  private Integer gaeldendefra;

  @Schema(description = "Sluttid for kortets gyldighedsperiode. Angives i hele år. Typisk fordi kortet erstattes af et nyere. Hvis der ikke er fundet en specifik gældende til periode angives et årstal der ligger 50 år efter gældende fra.")
  private Integer gaeldendetil;

  @Schema(description = "Det geografiske område, ofte en polygon, som kortet ligger indenfor. WKT med SRS = EPSG:4326")
  private String geometri;

  @Schema(description = "Angivelse af geografisk område, f.eks. indenfor kortbladsinddelingen eller ejerlav. For topografiske kort er inddelingen et overordnet grid hvorimod matrikelkort er indelt efter ejerlav. Kan også være f.eks. administrative inddelinger.")
  private String kortbladnummer;

  @Schema(description = "Navnet på en logisk samling af skannede kort som kortet hører til, f.eks. atlasblade.")
  private List<String> kortvaerk;

  @Schema(description = "Størrelsesforholdet mellem landskabet og kortets repræsentation heraf.")
  private List<String> maalestok;

  @Schema(description = "Sidestørrelse, dvs. hvor mange poster pr. side")
  private int limit;

  @Schema(description = "Offset, dvs. fra hvilken post")
  private int offset;

  @Schema(description = "Sorteringsfelt, kan sortere på følgende typer: arketype, daekningsomraade, gaeldendefra, gaeldendetil, id, kortvaerk, maalestok, titel")
  private String sort;

  @Schema(description = "Tegner på kortet.")
  private String tegner;

  @Schema(description = "Titlen på kortet.")
  private String titel;

  public KortParam(List<String> arketype, List<String> daekningsomraade, String direction,
                   String fritekstsoegning, Integer gaeldendefra, Integer gaeldendetil, String geometri,
                   String kortbladnummer, List<String> kortvaerk, List<String> maalestok,
                   int limit, int offset, String sort, String tegner, String titel) {
    this.arketype = arketype;
    this.daekningsomraade = daekningsomraade;
    this.direction = direction;
    this.fritekstsoegning = fritekstsoegning;
    this.gaeldendefra = gaeldendefra;
    this.gaeldendetil = gaeldendetil;
    this.geometri = geometri;
    this.kortbladnummer = kortbladnummer;
    this.kortvaerk = kortvaerk;
    this.maalestok = maalestok;
    this.limit = limit;
    this.offset = offset;
    this.sort = sort;
    this.tegner = tegner;
    this.titel = titel;
  }

  public List<String> getArketype() {
    return arketype;
  }

  public void setArketype(List<String> arketype) {
    this.arketype = arketype;
  }

  public List<String> getDaekningsomraade() {
    return daekningsomraade;
  }

  public void setDaekningsomraade(List<String> daekningsomraade) {
    this.daekningsomraade = daekningsomraade;
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

  public Integer getGaeldendefra() {
    return gaeldendefra;
  }

  public void setGaeldendefra(Integer gaeldendefra) {
    this.gaeldendefra = gaeldendefra;
  }

  public Integer getGaeldendetil() {
    return gaeldendetil;
  }

  public void setGaeldendetil(Integer gaeldendetil) {
    this.gaeldendetil = gaeldendetil;
  }

  public String getGeometri() {
    return geometri;
  }

  public void setGeometri(String geometri) {
    this.geometri = geometri;
  }

  public String getKortbladnummer() {
    return kortbladnummer;
  }

  public void setKortbladnummer(String kortbladnummer) {
    this.kortbladnummer = kortbladnummer;
  }

  public List<String> getKortvaerk() {
    return kortvaerk;
  }

  public void setKortvaerk(List<String> kortvaerk) {
    this.kortvaerk = kortvaerk;
  }

  public List<String> getMaalestok() {
    return maalestok;
  }

  public void setMaalestok(List<String> maalestok) {
    this.maalestok = maalestok;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public String getTegner() {
    return tegner;
  }

  public void setTegner(String tegner) {
    this.tegner = tegner;
  }

  public String getTitel() {
    return titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    KortParam kortParam = (KortParam) o;
    return gaeldendefra == kortParam.gaeldendefra
        && gaeldendetil == kortParam.gaeldendetil
        && limit == kortParam.limit
        && offset == kortParam.offset
        && Objects.equals(arketype, kortParam.arketype)
        && Objects.equals(daekningsomraade, kortParam.daekningsomraade)
        && Objects.equals(direction, kortParam.direction)
        && Objects.equals(fritekstsoegning, kortParam.fritekstsoegning)
        && Objects.equals(geometri, kortParam.geometri)
        && Objects.equals(kortbladnummer, kortParam.kortbladnummer)
        && Objects.equals(kortvaerk, kortParam.kortvaerk)
        && Objects.equals(maalestok, kortParam.maalestok)
        && Objects.equals(sort, kortParam.sort)
        && Objects.equals(tegner, kortParam.tegner)
        && Objects.equals(titel, kortParam.titel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(arketype, daekningsomraade, direction, fritekstsoegning, gaeldendefra,
        gaeldendetil,
        geometri, kortbladnummer, kortvaerk, maalestok, limit, offset, sort, tegner, titel);
  }
}
