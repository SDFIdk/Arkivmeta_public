package dk.dataforsyningen.arkivmeta.protokol.rest;

import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;
import dk.dataforsyningen.arkivmeta.protokol.service.IProtokolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ProtokolApi", description = "Protokol metadata API")
@RestController
@Validated
public class ProtokolApi {
  private final IProtokolService iProtokolService;

  public ProtokolApi(IProtokolService iProtokolService) {
    this.iProtokolService = iProtokolService;
  }

  @GetMapping(path = "/protokol/{arketype}/{id}")
  @Operation(summary = "Find protokol ud fra unik id")
  public ResponseEntity<ProtokolDto> protokolById(
      @Parameter(description = "arketype") @PathVariable String arketype,
      @Parameter(description = "id") @PathVariable String id) {
    ProtokolDto result = iProtokolService.getProtokolById(arketype, id);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
