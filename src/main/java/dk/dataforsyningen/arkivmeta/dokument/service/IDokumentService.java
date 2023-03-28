package dk.dataforsyningen.arkivmeta.dokument.service;

import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentParam;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentResult;

public interface IDokumentService {
  DokumentDto getDokumentById(String arketype, String id);

  DokumentResult getDokumentResult(DokumentParam dokumentParam);

}
