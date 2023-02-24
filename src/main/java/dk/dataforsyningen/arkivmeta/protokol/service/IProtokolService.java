package dk.dataforsyningen.arkivmeta.protokol.service;

import dk.dataforsyningen.arkivmeta.protokol.apimodel.ProtokolDto;

public interface IProtokolService {
  ProtokolDto getProtokolById(String arketype, String id);
}
