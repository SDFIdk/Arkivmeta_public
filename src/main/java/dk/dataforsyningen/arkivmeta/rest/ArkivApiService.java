package dk.dataforsyningen.arkivmeta.rest;

import dk.dataforsyningen.arkivmeta.apimodel.*;
import dk.dataforsyningen.arkivmeta.service.IArkivService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "ArkivApiService", description = "Arkiv metadata API")
@RestController
public class ArkivApiService
{
    private final IArkivService iArkivService;

    public ArkivApiService(IArkivService iArkivService)
    {
        this.iArkivService = iArkivService;
    }

    /**
     * Spring's default WebDataBinder is configured to split parameters lists on commas.
     * The default configuration is disabled by adding this binder initialization code in ArkivApiService.
     * URI standard has "," as a reserved keyword, but the data we are displaying and what the users can search on,
     * has commas in titles etc. So to search on multiple titles, arketyper, kortvaerker etc. you add the parameter
     * before each. Example: arketype=soekort&arketype=centimeterkort
     * <p>
     * https://datatracker.ietf.org/doc/html/rfc3986#section-3.4
     * https://stackoverflow.com/questions/23695817/requestparam-array-mapping-issues
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // FIXME: Right now it splits on "|" because swbd can't understand the multiple parameters with the same name
        binder.registerCustomEditor(
                String[].class,
                new StringArrayPropertyEditor("|"));
    }

    @GetMapping(path = "/")
    @Hidden
    @CrossOrigin
    public ResponseEntity<String> ok()
    {
        return new ResponseEntity<>("Alive and kicking!", HttpStatus.OK);
    }

    /**
     * getArketyper() returns list of ArketypeDto with all arketyper, arkenavn and kortvaerker belonging to each arketyper
     * and then this method stream and map only arketype to a List<String>
     *
     * @return list of string of all unique arketyper available
     */
    @GetMapping(path = "/metadata/arketyper")
    @Operation(summary = "Hent arketyper", description = "Leverer en liste af tilgængelige arketyper")
    @CrossOrigin
    public List<String> arketyper()
    {
        List<String> arketypeList = iArkivService.getArketyper()
                .stream()
                .map(ArketypeDto::getArketype)
                .collect(Collectors.toList());

        return arketypeList;
    }

    /**
     * @return list of ArketypeDto with all arketyper, arkenavn and kortvaerker belonging to each arketyper
     */
    @GetMapping(path = "/metadata/arketyper/kortvaerker")
    @Operation(summary = "Hent arketyper med underliggende kortværker", description = "Leverer en liste af tilgængelige arketyper, med de kortværker som hører til")
    @CrossOrigin
    public List<ArketypeDto> arketyperWithKortvaerker()
    {
        List<ArketypeDto> arketypeList = iArkivService.getArketyper();

        return arketypeList;
    }

    /**
     * @param daekningsomraade
     * @return list of string of all daekningsomraader available
     */
    @GetMapping(path = "/metadata/daekningsomraader")
    @Operation(summary = "Hent dækningsområder", description = "Leverer en liste af dækningsområder")
    @CrossOrigin
    public List<String> daekningsomraade(
            @Parameter(description = "Filtrer med søgestreng") @RequestParam(defaultValue = "") String daekningsomraade)
    {
        List<String> daekningsomraadeList = iArkivService.getDaekningsomraader(daekningsomraade)
                .stream()
                .map(DaekningsomraadeDto::getDaekningsomraade)
                .collect(Collectors.toList());
        return daekningsomraadeList;
    }

    /**
     * @param kortvaerk
     * @return list of string of all kortvaerker available
     */
    @GetMapping(path = "/metadata/kortvaerker")
    @Operation(summary = "Hent kortværker", description = "Leverer en liste af kortværker")
    @CrossOrigin
    public List<String> kortvaerk(
            @Parameter(description = "Kortværker der har følgende arketype") @RequestParam(defaultValue = "") String arketype,
            @Parameter(description = "Filtrer med søgestreng") @RequestParam(defaultValue = "") String kortvaerk)
    {
        List<String> kortvaerkerList = iArkivService.getKortvaerker(arketype, kortvaerk)
                .stream()
                .map(KortvaerkDto::getKortvaerk)
                .collect(Collectors.toList());
        return kortvaerkerList;
    }

    /**
     * @param maalestok
     * @return list of string of all maalestok available
     */
    @GetMapping(path = "/metadata/maalestok")
    @Operation(summary = "Hent målestoksforhold", description = "Leverer en liste af målestoksforhold")
    @CrossOrigin
    List<String> maalestok(
            @Parameter(description = "Filtrer med søgestreng") @RequestParam(defaultValue = "") String maalestok)
    {
        List<String> maalestokList = iArkivService.getMaalestokke(maalestok)
                .stream()
                .map(MaalestokDto::getMaalestok)
                .collect(Collectors.toList());
        return maalestokList;
    }

    /**
     * @param arketype         type of kort
     * @param daekningsomraade
     * @param titel
     * @param kortvaerk
     * @param gaeldendefra
     * @param gaeldendetil
     * @param geometri
     * @param kortbladnummer
     * @param maalestok
     * @param offset
     * @param pagesize
     * @param sort
     * @param direction
     * @param requestHeaders   the clients request header
     * @return the method postkort() that handles the request
     */
    @GetMapping(path = "/kort")
    @Operation(summary = "Liste af kort der matcher søgekriterierne", description = "Hvis gaeldendefra og gaeldendetil bliver brugt samtidig, er det alle kort, der er indenfor gyldighedperioden eller har været gældende fra eller gældende til i perioden")
    @CrossOrigin
    ResponseEntity<KortResult> getKort(
            @Parameter(description = "Arketype, se /metadata/arketyper. Hvis der ønskes at søge på flere arketyper på en gang, skal man adskille hvert søgekriterie ved at bruge |. " +
                    "At bruge | er en midlertidig løsning, så den vil blive fjernet uden varsel, når produktionsmiljøet kan håndtere at angive samme parameter flere gange i URL'en. " +
                    "Eksempel: arketype=matrikelkort|centimeterkort.")
            @RequestParam(required = false) String[] arketype,

            @Parameter(description = "Dækningsområde, se /metadata/daekningsomraader. Hvis der ønskes at søge på flere dækningsområde på en gang, skal man adskille hvert søgekriterie ved at bruge |. " +
                    "At bruge | er en midlertidig løsning, så den vil blive fjernet uden varsel, når produktionsmiljøet kan håndtere at angive samme parameter flere gange i URL'en. " +
                    "Eksempel: daekningsomraade=Slesvig|Danmark")
            @RequestParam(required = false) String[] daekningsomraade,

            @Parameter(description = "Sorteringsretning, 'asc' for stigende, 'desc' for faldende")
            @RequestParam(required = false, defaultValue = "asc") String direction,

            @Parameter(description = "Fritekstsøgning")
            @RequestParam(required = false) String fritekstsoegning,

            @Parameter(description = "Gyldig i år (åååå), eks. 1966")
            @RequestParam(required = false, defaultValue = "-1") int gaeldendefra,

            @Parameter(description = "Gyldig i år (åååå), eks. 1966")
            @RequestParam(required = false, defaultValue = "-1") int gaeldendetil,

            @Parameter(description = "Geometri angives som WKT med SRS = EPSG:4326. Bruges til at finde kort der indeholder denne polygon.")
            @RequestParam(required = false) String geometri,

            @Parameter(description = "Kortbladnummer")
            @RequestParam(required = false) String kortbladnummer,

            @Parameter(description = "Kortværk. Hvis der ønskes at søge på flere kortværker på en gang, skal man adskille hvert søgekriterie ved at bruge |. " +
                    "At bruge | er en midlertidig løsning, så den vil blive fjernet uden varsel, når produktionsmiljøet kan håndtere at angive samme parameter flere gange i URL'en. " +
                    "Eksempel: kortvaerk=Trap, tegnede kort|Mejer")
            @RequestParam(required = false) String[] kortvaerk,

            @Parameter(description = "Målestoksforhold. Hvis der ønskes at søge på flere målestoksforhold på en gang, skal man adskille hvert søgekriterie ved at bruge |. " +
                    "At bruge | er en midlertidig løsning, så den vil blive fjernet uden varsel, når produktionsmiljøet kan håndtere at angive samme parameter flere gange i URL'en. " +
                    "Eksempel: maalestok=1:40000|1:180000")
            @RequestParam(required = false) String[] maalestok,

            @Parameter(description = "Offset, dvs. fra hvilken post")
            @RequestParam(required = false, defaultValue = "0") int offset,

            @Parameter(description = "Sidestørrelse, dvs. hvor mange poster pr. side. Default er 100")
            @RequestParam(required = false, defaultValue = "100") int pagesize,

            @Parameter(description = "Sorteringsfelt, kan sortere på følgende typer: arketype, daekningsomraade, gaeldendefra, gaeldendetil, id, kortvaerk, maalestok, titel")
            @RequestParam(required = false) String sort,

            @Parameter(description = "Tegner")
            @RequestParam(required = false) String tegner,

            @Parameter(description = "Titel")
            @RequestParam(required = false) String titel,

            @RequestHeader HttpHeaders requestHeaders)
    {
        return postKort(
                new KortParam(
                        list(arketype),
                        list(daekningsomraade),
                        direction,
                        fritekstsoegning,
                        gaeldendefra,
                        gaeldendetil,
                        geometri,
                        kortbladnummer,
                        list(kortvaerk),
                        list(maalestok),
                        offset,
                        pagesize,
                        sort,
                        tegner,
                        titel
                ),
                requestHeaders
        );
    }

    private List<String> list(String[] arketype)
    {
        if (arketype == null)
        {
            return List.of();
        }
        return List.of(arketype);
    }

    /**
     * Returns all kort matching search criteria if given any or else returns all kort.
     * Gets the parameters from KortParam that is initialized with data from getkort
     * <p>
     *
     * @param kortParam
     * @param requestHeaders the clients request header
     * @return the kortresult with count of all kort matching search criteria and all kort matching search criteria. If not search criteria given then it returns all kort and the count
     */
    @PostMapping(path = "/kort")
    @Operation(summary = "Liste af kort der matcher søgekriterierne")
    @CrossOrigin
    ResponseEntity<KortResult> postKort(
            @Parameter(description = "kortParam er en pladsholder, der ikke benyttes, benyt samme parametre ved POST som ved GET")
            @RequestBody KortParam kortParam,
            @RequestHeader HttpHeaders requestHeaders)
    {
        KortResult kortresult = iArkivService.getKortResult(kortParam, concatXFHeaders(requestHeaders));

        return new ResponseEntity<>(kortresult, HttpStatus.OK);
    }


    /**
     * Returns the kort matching with specified {arketype}/{id} as JSON.
     * <p>
     *
     * @param arketype       type of kort
     * @param id             the kort's id
     * @param requestHeaders the clients request header
     * @return the kort with the arketype and id specified
     */
    @GetMapping(path = "/kort/{arketype}/{id}")
    @Operation(summary = "Vis kort ud fra unik id")
    @CrossOrigin
    ResponseEntity<KortDto> kortById(@Parameter(description = "arketype") @PathVariable String arketype,
                                     @Parameter(description = "id") @PathVariable String id,
                                     @RequestHeader HttpHeaders requestHeaders)
    {
        KortDto result = iArkivService.getKortById(arketype, id, concatXFHeaders(requestHeaders));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Helper method to concatXFHeaders
//    private String header(String name, HttpHeaders httpHeaders)
//    {
//        List<String> value = httpHeaders.get(name);
//
//        if (value == null || value.isEmpty())
//        {
//            return null;
//        }
//
//        return value.get(0);
//    }

    private String concatXFHeaders(HttpHeaders httpHeaders)
    {
//        String protokol = header("X-Forwarded-Proto", httpHeaders);
//
//        if (Strings.isBlank(protokol))
//        {
//            protokol = "http";
//        }
//
//        String portnummer = header("X-Forwarded-Port", httpHeaders);
//
//        if (Strings.isBlank(protokol))
//        {
//            protokol = "80";
//        }
//
//        String port = "";
//
//        if ((protokol.equals("https") && Objects.equals(portnummer, "443")) ||
//                (protokol.equals("http") && Objects.equals(portnummer, "80")))
//        {
//            port = "";
//        }
//        else
//        {
//            port = portnummer;
//        }
//
//        String host = header("X-Forwarded-Host", httpHeaders);
//
//        if (Strings.isBlank(protokol))
//        {
//            protokol = "localhost";
//        }
//
//        String path = header("X-Forwarded-Path", httpHeaders);
//
//        if (Strings.isBlank(protokol))
//        {
//            protokol = "";
//        }

        // FIXME: hardcoded value since Gravitee setup doesn't set headers
        //return protokol + "://" + host + port + path;
        return "https://api.dataforsyningen.dk/rest/arkivkort";
    }
}
