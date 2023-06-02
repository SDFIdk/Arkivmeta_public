package dk.dataforsyningen.arkivmeta.kort.service;

import dk.dataforsyningen.arkivmeta.Kortvaerk;
import dk.dataforsyningen.arkivmeta.kort.apimodel.ArketypeDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.DaekningsomraadeDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortParam;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortResult;
import dk.dataforsyningen.arkivmeta.kort.apimodel.KortvaerkDto;
import dk.dataforsyningen.arkivmeta.kort.apimodel.MaalestokDto;
import java.util.List;

public interface IKortService {
  List<ArketypeDto> getArketyper();

  KortDto getKortById(String arketype, String id);

  KortResult getKortResult(KortParam kortParam, Kortvaerk kortvaerk);

  List<DaekningsomraadeDto> getDaekningsomraader(String daekningsomraade);

  List<KortvaerkDto> getKortvaerker(String arketype, String kortvaerk);

  List<MaalestokDto> getMaalestokke(String maalestok);
}
