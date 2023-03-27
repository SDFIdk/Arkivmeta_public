package dk.dataforsyningen.arkivmeta.kort.rest;

import dk.dataforsyningen.arkivmeta.kort.apimodel.ArketypeDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.DaekningsomraadeDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortParam;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortResult;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortvaerkDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.MaalestokDto;
import dk.dataforsyningen.arkivmeta.kort.service.IKortService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "KortApi", description = "Kort metadata API")
@RestController
public class KortApi {
  private final IKortService iKortService;

  public KortApi(IKortService iKortService) {
    this.iKortService = iKortService;
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
    List<String> arketypeList = iKortService.getArketyper()
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
    List<ArketypeDto> arketypeList = iKortService.getArketyper();

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
    List<String> daekningsomraadeList = iKortService.getDaekningsomraader(daekningsomraade)
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
    List<String> kortvaerkerList = iKortService.getKortvaerker(arketype, kortvaerk)
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
    List<String> maalestokList = iKortService.getMaalestokke(maalestok)
        .stream()
        .map(MaalestokDto::getMaalestok)
        .collect(Collectors.toList());
    return maalestokList;
  }

  /**
   * @return the method postKort() that handles the request
   */
  @GetMapping(path = "/kort")
  @Operation(summary = "Liste af kort der matcher søgekriterierne", description = "Hvis gaeldendefra og gaeldendetil bliver brugt samtidig, er det alle kort, der er indenfor gyldighedsperioden eller har været gældende fra eller gældende til, i perioden")
  @CrossOrigin
  ResponseEntity<KortResult> getKort(@Valid @ParameterObject KortParam kortParam) {
    return postKort(kortParam);
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
  @Operation(summary = "Liste af kort der matcher søgekriterierne", description = "Hvis gaeldendefra og gaeldendetil bliver brugt samtidig, er det alle kort, der er indenfor gyldighedsperioden eller har været gældende fra eller gældende til, i perioden")
  @CrossOrigin
  ResponseEntity<KortResult> postKort(
      @Valid @RequestBody @ParameterObject KortParam kortParam) {
    // For GET and POST direction, limit and offset need a default value, but it should only be set,
    // if the client did not specify them.
    if (StringUtils.isBlank(kortParam.getDirection())) {
      kortParam.setDirection("asc");
    }
    if (ObjectUtils.isEmpty(kortParam.getLimit())) {
      kortParam.setLimit(100);
    }
    if (ObjectUtils.isEmpty(kortParam.getOffset())) {
      kortParam.setOffset(0);
    }
    KortResult kortresult = iKortService.getKortResult(kortParam);

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
    KortDto result = iKortService.getKortById(arketype, id);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
