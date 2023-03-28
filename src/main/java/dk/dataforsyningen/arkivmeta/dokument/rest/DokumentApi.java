package dk.dataforsyningen.arkivmeta.dokument.rest;

import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentParam;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentResult;
import dk.dataforsyningen.arkivmeta.dokument.service.IDokumentService;
import dk.dataforsyningen.arkivmeta.kort.apimodel.ArketypeDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortvaerkDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ProtokolApi", description = "Protokol metadata API")
@RestController
@Validated
public class DokumentApi {
  private final IDokumentService iDokumentService;

  public DokumentApi(IDokumentService iDokumentService) {
    this.iDokumentService = iDokumentService;
  }

  /**
   * @return list of string of all dokumentsamling available
   */
  @GetMapping(path = "/metadata/dokumentsamling")
  @Operation(summary = "Hent dokumentsamling", description = "Leverer en liste af dokumentsamlinger")
  @CrossOrigin
  public List<String> getDokumentSamling() {

    return iDokumentService.getDokumentSamling();
  }

  /**
   * @return the method postKort() that handles the request
   */
  @GetMapping(path = "/dokument")
  @Operation(summary = "Liste af dokumenter der matcher søgekriterierne", description = "Disse er parametrerne i DokumentParam")
  @CrossOrigin
  ResponseEntity<DokumentResult> getDokument(@Valid @ParameterObject DokumentParam dokumentParam) {
    return postDokument(dokumentParam);
  }

  /**
   * Returns all dokuments matching search criteria if given any or else returns all kort.
   * Gets the parameters from KortParam that is initialized with data from getDokument
   * <p>
   *
   * @param dokumentParam
   * @return the dokumentresult with count of all dokuments matching search criteria and all dokuments matching search criteria. If not search criteria given then it returns all dokuments and the count
   */
  @PostMapping(path = "/dokument")
  @Operation(summary = "Liste af dokumenter der matcher søgekriterierne", description = "Disse er parametrerne i DokumentParam")
  @CrossOrigin
  ResponseEntity<DokumentResult> postDokument(
      @Valid @RequestBody DokumentParam dokumentParam) {
    // For GET and POST direction, limit and offset need a default value, but it should only be set,
    // if the client did not specify them.
    if (StringUtils.isBlank(dokumentParam.getDirection())) {
      dokumentParam.setDirection("asc");
    }
    if (ObjectUtils.isEmpty(dokumentParam.getLimit())) {
      dokumentParam.setLimit(100);
    }
    if (ObjectUtils.isEmpty(dokumentParam.getOffset())) {
      dokumentParam.setOffset(0);
    }
    DokumentResult dokumentresult = iDokumentService.getDokumentResult(dokumentParam);

    return new ResponseEntity<>(dokumentresult, HttpStatus.OK);
  }

  @GetMapping(path = "/dokument/{arketype}/{id}")
  @Operation(summary = "Find dokument ud fra unik id")
  @CrossOrigin
  public ResponseEntity<DokumentDto> dokumentById(
      @Parameter(description = "arketype") @PathVariable String arketype,
      @Parameter(description = "id") @PathVariable String id) {
    DokumentDto result = iDokumentService.getDokumentById(arketype, id);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
