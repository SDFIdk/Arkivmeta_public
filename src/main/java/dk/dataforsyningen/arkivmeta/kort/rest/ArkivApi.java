package dk.dataforsyningen.arkivmeta.kort.rest;

import dk.dataforsyningen.arkivmeta.kort.apimodel.ArketypeDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.DaekningsomraadeDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortParam;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortResult;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortvaerkDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.MaalestokDto;
import dk.dataforsyningen.arkivmeta.kort.service.IArkivService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ArkivApiService", description = "Arkiv metadata API")
@RestController
@Validated
public class ArkivApi {
  private final IArkivService iArkivService;

  public ArkivApi(IArkivService iArkivService) {
    this.iArkivService = iArkivService;
  }

  /**
   * Endpoint to check if the API is up and can answer a simple request
   *
   * @return HTTP response with headers, body, and status with type String with a PONG message
   */
  @GetMapping(path = "/ping", produces = "application/json")
  @Operation(
      summary = "ping",
      description = "Liveliness/readiness probe.",
      tags = {"Liveliness/Readiness"})
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Success",
              content = @Content(schema = @Schema(implementation = String.class)))
      })
  public ResponseEntity<String> ping() {
    return new ResponseEntity<>("{\"message\": \"PONG\"}", HttpStatus.OK);
  }

  /**
   * getArketyper() returns list of ArketypeDto with all arketyper, arkenavn and kortvaerker belonging to each arketyper
   * and then this method stream and map only arketype to a List<String>
   *
   * @return list of string of all unique arketyper available
   */
  @GetMapping(path = "/metadata/arketyper")
  @Operation(summary = "Hent arketyper", description = "Leverer en liste af tilgængelige arketyper")
  @CrossOrigin
  public List<String> arketyper() {
    List<String> arketypeList = iArkivService.getArketyper()
        .stream()
        .map(ArketypeDto::getArketype)
        .collect(Collectors.toList());

    return arketypeList;
  }

  /**
   * @return list of ArketypeDto with all arketyper, arkenavn and kortvaerker belonging to each arketyper
   */
  @GetMapping(path = "/metadata/arketyper/kortvaerker")
  @Operation(summary = "Hent arketyper med underliggende kortværker", description = "Leverer en liste af tilgængelige arketyper, med de kortværker som hører til")
  @CrossOrigin
  public List<ArketypeDto> arketyperWithKortvaerker() {
    List<ArketypeDto> arketypeList = iArkivService.getArketyper();

    return arketypeList;
  }

  /**
   * @param daekningsomraade
   * @return list of string of all daekningsomraader available
   */
  @GetMapping(path = "/metadata/daekningsomraader")
  @Operation(summary = "Hent dækningsområder", description = "Leverer en liste af dækningsområder")
  @CrossOrigin
  public List<String> daekningsomraade(
      @Parameter(description = "Filtrer med søgestreng") @RequestParam(defaultValue = "")
          String daekningsomraade) {
    List<String> daekningsomraadeList = iArkivService.getDaekningsomraader(daekningsomraade)
        .stream()
        .map(DaekningsomraadeDto::getDaekningsomraade)
        .collect(Collectors.toList());
    return daekningsomraadeList;
  }

  /**
   * @param kortvaerk
   * @return list of string of all kortvaerker available
   */
  @GetMapping(path = "/metadata/kortvaerker")
  @Operation(summary = "Hent kortværker", description = "Leverer en liste af kortværker")
  @CrossOrigin
  public List<String> kortvaerk(
      @Parameter(description = "Kortværker der har følgende arketype")
      @RequestParam(defaultValue = "") String arketype,
      @Parameter(description = "Filtrer med søgestreng") @RequestParam(defaultValue = "")
          String kortvaerk) {
    List<String> kortvaerkerList = iArkivService.getKortvaerker(arketype, kortvaerk)
        .stream()
        .map(KortvaerkDto::getKortvaerk)
        .collect(Collectors.toList());
    return kortvaerkerList;
  }

  /**
   * @param maalestok
   * @return list of string of all maalestok available
   */
  @GetMapping(path = "/metadata/maalestok")
  @Operation(summary = "Hent målestoksforhold", description = "Leverer en liste af målestoksforhold")
  @CrossOrigin
  List<String> maalestok(
      @Parameter(description = "Filtrer med søgestreng") @RequestParam(defaultValue = "")
          String maalestok) {
    List<String> maalestokList = iArkivService.getMaalestokke(maalestok)
        .stream()
        .map(MaalestokDto::getMaalestok)
        .collect(Collectors.toList());
    return maalestokList;
  }

  /**
   * @param arketype         type of kort
   * @param daekningsomraade
   * @param titel
   * @param kortvaerk
   * @param gaeldendefra
   * @param gaeldendetil
   * @param geometri
   * @param kortbladnummer
   * @param maalestok
   * @param offset
   * @param limit
   * @param sort
   * @param direction
   * @param requestHeaders   the clients request header
   * @return the method postkort() that handles the request
   */
  @GetMapping(path = "/kort")
  @Operation(summary = "Liste af kort der matcher søgekriterierne", description = "Hvis gaeldendefra og gaeldendetil bliver brugt samtidig, er det alle kort, der er indenfor gyldighedperioden eller har været gældende fra eller gældende til i perioden")
  @CrossOrigin
  ResponseEntity<KortResult> getKort(
      @Parameter(description =
          "Arketype, se /metadata/arketyper. Hvis der ønskes at søge på flere arketyper på en gang, skal man adskille hvert søgekriterie ved at bruge komma , . " +
              "Eksempel: arketype=matrikelkort,centimeterkort.")
      @RequestParam(required = false) List<String> arketype,

      @Parameter(description =
          "Dækningsområde, se /metadata/daekningsomraader. Hvis der ønskes at søge på flere dækningsområde på en gang, skal man adskille hvert søgekriterie ved at bruge , . " +
              "Eksempel: daekningsomraade=Slesvig,Danmark")
      @RequestParam(required = false) List<String> daekningsomraade,

      @Parameter(description = "Sorteringsretning, 'asc' for stigende, 'desc' for faldende")
      @Pattern(regexp = "asc|desc")
      @RequestParam(required = false, defaultValue = "asc") String direction,

      @Parameter(description = "Fritekstsøgning")
      @RequestParam(required = false) String fritekstsoegning,

      @Parameter(description = "Gyldig i år (åååå), eks. 1966")
      @RequestParam(required = false) Integer gaeldendefra,

      @Parameter(description = "Gyldig i år (åååå), eks. 1966")
      @RequestParam(required = false) Integer gaeldendetil,

      @Parameter(description = "Geometri angives som WKT med SRS = EPSG:4326. Bruges til at finde kort der indeholder denne polygon.")
      @RequestParam(required = false) String geometri,

      @Parameter(description = "Kortbladnummer")
      @RequestParam(required = false) String kortbladnummer,

      @Parameter(description =
          "Kortværk. Hvis der ønskes at søge på flere kortværker på en gang, skal man angive `kortvaerk` query parameteren for hvert eneste en kortværk man vil søge efter." +
              "Eksempel: kortvaerk=Trap, tegnede kort&kortvaerk=Mejer")
      @RequestParam(required = false) List<String> kortvaerk,

      @Parameter(description =
          "Målestoksforhold. Hvis der ønskes at søge på flere målestoksforhold på en gang, skal man adskille hvert søgekriterie ved at bruge , . " +
              "Eksempel: maalestok=1:40000,1:180000")
      @RequestParam(required = false) List<String> maalestok,

      @Parameter(description = "Sidestørrelse, dvs. hvor mange poster pr. side. Maximum = 1000")
      @Min(1)
      @Max(1000)
      @RequestParam(required = false, defaultValue = "100") int limit,

      @Parameter(description = "Offset, dvs. fra hvilken post")
      @RequestParam(required = false, defaultValue = "0") int offset,

      @Parameter(description = "Sorteringsfelt, kan sortere på følgende typer: arketype, daekningsomraade, gaeldendefra, gaeldendetil, id, kortvaerk, maalestok, titel")
      @RequestParam(required = false) String sort,

      @Parameter(description = "Tegner")
      @RequestParam(required = false) String tegner,

      @Parameter(description = "Titel")
      @RequestParam(required = false) String titel,

      @RequestHeader HttpHeaders requestHeaders) {
    return postKort(
        new KortParam(
            arketype,
            daekningsomraade,
            direction,
            fritekstsoegning,
            gaeldendefra,
            gaeldendetil,
            geometri,
            kortbladnummer,
            kortvaerk,
            maalestok,
            limit,
            offset,
            sort,
            tegner,
            titel
        )
    );
  }

  /**
   * Returns all kort matching search criteria if given any or else returns all kort.
   * Gets the parameters from KortParam that is initialized with data from getkort
   * <p>
   *
   * @param kortParam
   * @return the kortresult with count of all kort matching search criteria and all kort matching search criteria. If not search criteria given then it returns all kort and the count
   */
  @PostMapping(path = "/kort")
  @Operation(summary = "Liste af kort der matcher søgekriterierne")
  @CrossOrigin
  ResponseEntity<KortResult> postKort(
      @Parameter(description = "kortParam er en pladsholder, der ikke benyttes, benyt samme parametre ved POST som ved GET")
      @Valid @RequestBody KortParam kortParam) {
    KortResult kortresult = iArkivService.getKortResult(kortParam);

    return new ResponseEntity<>(kortresult, HttpStatus.OK);
  }

  /**
   * Returns the kort matching with specified {arketype}/{id} as JSON.
   * <p>
   *
   * @param arketype type of kort
   * @param id       the kort's id
   * @return the kort with the arketype and id specified
   */
  @GetMapping(path = "/kort/{arketype}/{id}")
  @Operation(summary = "Vis kort ud fra unik id")
  @CrossOrigin
  ResponseEntity<KortDto> kortById(
      @Parameter(description = "arketype") @PathVariable String arketype,
      @Parameter(description = "id") @PathVariable String id) {
    KortDto result = iArkivService.getKortById(arketype, id);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
