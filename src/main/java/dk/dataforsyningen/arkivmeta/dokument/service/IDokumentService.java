package dk.dataforsyningen.arkivmeta.dokument.service;

import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentDto;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentParam;
import dk.dataforsyningen.arkivmeta.dokument.apimodel.DokumentResult;
import java.util.List;

public interface IDokumentService {
  List<String> getDokumentSamling();

  List<String> getHerredsnavn();

  List<String> getSognenavn();

  DokumentDto getDokumentById(String arketype, String id);

  DokumentResult getDokumentResult(DokumentParam dokumentParam);

}