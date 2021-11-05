package dk.dataforsyningen.arkivmeta.service;

import dk.dataforsyningen.arkivmeta.apimodel.*;

import java.util.List;

public interface IArkivService
{
    List<ArketypeDto> getArketyper();

    KortDto getKortById(String arketype, String id, String baseUrl);

    KortResult getKortResult(KortParam kortParam, String baseUrl);

    List<DaekningsomraadeDto> getDaekningsomraader(String daekningsomraade);

    List<KortvaerkDto> getKortvaerker(String arketype, String kortvaerk);

    List<MaalestokDto> getMaalestokke(String maalestok);
}
