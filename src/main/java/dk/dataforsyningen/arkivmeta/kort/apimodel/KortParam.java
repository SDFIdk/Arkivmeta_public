package dk.dataforsyningen.arkivmeta.kort.apimodel;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import java.util.List;

public class KortParam {

  @Parameter(description =
      "Arketype, se /metadata/arketyper. Hvis der ønskes at søge på flere arketyper på en gang, skal man adskille hvert søgekriterie ved at bruge komma `,`. " +
          "Eksempel: `arketype=matrikelkort,centimeterkort.`")
  @Schema(description = "Kortets arketype.")
  private List<String> arketype;

  @Parameter(description =
      "Dækningsområde, se /metadata/daekningsomraader. Hvis der ønskes at søge på flere dækningsområde på en gang, skal man adskille hvert søgekriterie ved at bruge `,`. " +
          "Eksempel: `daekningsomraade=Slesvig,Danmark`")
  @Schema(description = "Geografisk område, som kortet dækker helt eller delvist. For eksempel Danmark, Grønland. Et kort kan have flere dækningsområder.For prøvekort og lignende vil dækningsområdet kunne angives som intet.")
  private List<String> daekningsomraade;

  @Schema(description = "Sorteringsretning, `asc` for stigende, `desc` for faldende", defaultValue = "asc")
  @Pattern(regexp = "asc|desc")
  // How @Pattern works: https://stackoverflow.com/questions/4922655/javax-validation-to-validate-list-of-values
  private String direction;

  @Parameter(description = "Fritekstsøgning")
  @Schema(description = "Fritekstsøgning")
  private String fritekstsoegning;

  @Schema(description = "Starttid for kortets gyldighedsperiode. Angives i hele år, eksempel `1966`. Gyldighedsperiodens starttid er et korts trykke-, tegne-, optage- eller opmålingsår – dvs. det år hvor kortet kan siges at være nyeste kort.")
  private Integer gaeldendefra;

  @Schema(description = "Sluttid for kortets gyldighedsperiode. Angives i hele år, eksempel `1966`. Typisk fordi kortet erstattes af et nyere. Hvis der ikke er fundet en specifik gældende til periode angives et årstal der ligger 50 år efter gældende fra.")
  private Integer gaeldendetil;

  @Schema(description = "Geometri angives som WKT med SRS = EPSG:4326. Det geografiske område, ofte en polygon, som kortet ligger indenfor.")
  private String geometri;

  @Schema(description = "Angivelse af geografisk område, f.eks. indenfor kortbladsinddelingen eller ejerlav. For topografiske kort er inddelingen et overordnet grid hvorimod matrikelkort er inddelt efter ejerlav. Kan også være f.eks. administrative inddelinger.")
  private String kortbladnummer;

  @Parameter(description =
      "Kortværk. Hvis der ønskes at søge på flere kortværker på en gang, skal man angive `kortvaerk` query parameteren for hvert eneste en kortværk man vil søge efter." +
          "Eksempel: `kortvaerk=Trap, tegnede kort`&`kortvaerk=Mejer`")
  @Schema(description = "Navnet på en logisk samling af skannede kort som kortet hører til, f.eks. atlasblade.")
  private List<String> kortvaerk;

  @Parameter(description =
      "Målestoksforhold. Hvis der ønskes at søge på flere målestoksforhold på en gang, skal man adskille hvert søgekriterie ved at bruge `,`. " +
          "Eksempel: `maalestok=1:40000,1:180000`")
  @Schema(description = "Størrelsesforholdet mellem landskabet og kortets repræsentation heraf.")
  private List<String> maalestok;

  @Schema(description = "Sidestørrelse, dvs. hvor mange poster pr. side", defaultValue = "100")
  @Min(1)
  @Max(1000)
  private Integer limit;

  @Schema(description = "Offset, dvs. fra hvilken post", defaultValue = "0")
  private Integer offset;

  @Schema(description = "Sorteringsfelt, kan sortere på følgende typer: arketype, daekningsomraade, gaeldendefra, gaeldendetil, id, kortvaerk, maalestok, titel")
  private String sort;

  @Schema(description = "Tegner på kortet.")
  private String tegner;

  @Schema(description = "Titlen på kortet.")
  private String titel;

  public KortParam(List<String> arketype, List<String> daekningsomraade, String direction,
                   String fritekstsoegning, Integer gaeldendefra, Integer gaeldendetil,
                   String geometri, String kortbladnummer, List<String> kortvaerk,
                   List<String> maalestok, Integer limit, Integer offset, String sort,
                   String tegner,
                   String titel) {
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
}
