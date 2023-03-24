package dk.dataforsyningen.arkivmeta.dokument.service;

import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentParam;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentResult;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;

public interface IDokumentService {
  DokumentDto getDokumentById(String arketype, String id);

  DokumentResult getDokumentResult(DokumentParam dokumentParam);

}
