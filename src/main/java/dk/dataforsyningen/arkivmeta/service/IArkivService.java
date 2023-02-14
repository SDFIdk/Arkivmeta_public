package dk.dataforsyningen.arkivmeta.service;

import dk.dataforsyningen.arkivmeta.apimodel.ArketypeDto;
import dk.dataforsyningen.arkivmeta.apimodel.DaekningsomraadeDto;
import dk.dataforsyningen.arkivmeta.apimodel.KortDto;
import dk.dataforsyningen.arkivmeta.apimodel.KortParam;
import dk.dataforsyningen.arkivmeta.apimodel.KortResult;
import dk.dataforsyningen.arkivmeta.apimodel.KortvaerkDto;
import dk.dataforsyningen.arkivmeta.apimodel.MaalestokDto;
import java.util.List;

public interface IArkivService {
  List<ArketypeDto> getArketyper();

  KortDto getKortById(String arketype, String id);

  KortResult getKortResult(KortParam kortParam);

  List<DaekningsomraadeDto> getDaekningsomraader(String daekningsomraade);

  List<KortvaerkDto> getKortvaerker(String arketype, String kortvaerk);

  List<MaalestokDto> getMaalestokke(String maalestok);
}
