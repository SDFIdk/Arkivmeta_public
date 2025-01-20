package dk.dataforsyningen.arkivmeta.dokument.service;

import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentParam;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentResult;
import java.util.List;
import java.util.UUID;

public interface IDokumentService {
  List<String> getDokumentSamling();

  List<String> getHerredsnavn();

  List<String> getSognenavn();

  DokumentDto getDokumentById(UUID id);

  DokumentResult getDokumentResult(DokumentParam dokumentParam);

}