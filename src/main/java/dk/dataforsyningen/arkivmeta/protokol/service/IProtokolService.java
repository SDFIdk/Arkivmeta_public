package dk.dataforsyningen.arkivmeta.protokol.service;

import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolParam;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolResult;
import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;

public interface IProtokolService {
  ProtokolDto getProtokolById(String arketype, String id);

  ProtokolResult getProtokolResult(ProtokolParam protokolParam);

}
