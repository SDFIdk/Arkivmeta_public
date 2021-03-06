package dk.dataforsyningen.arkivmeta.apimodel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Is abstract so we can encapsulate common functionality (code reuse)
 */
@JsonSubTypes({
        @JsonSubTypes.Type(value = CentimeterkortDto.class,
                name = "dk.kortforsyningen.arkivmeta.apimodel.CentimeterkortDto"),
        @JsonSubTypes.Type(value = AeldretopografiskekortDto.class,
                name = "dk.kortforsyningen.arkivmeta.apimodel.AeldretopografiskekortDto"),
        @JsonSubTypes.Type(value = FaeroesketopokortDto.class,
                name = "dk.kortforsyningen.arkivmeta.apimodel.FaeroesketopokortDto"),
        @JsonSubTypes.Type(value = GroenlandtopokortDto.class,
                name = "dk.kortforsyningen.arkivmeta.apimodel.GroenlandtopokortDto"),
        @JsonSubTypes.Type(value = HistoriskeflyfotoDto.class,
                name = "dk.kortforsyningen.arkivmeta.apimodel.HistoriskeflyfotoDto"),
        @JsonSubTypes.Type(value = LandoekonomiskekortDto.class,
                name = "dk.kortforsyningen.arkivmeta.apimodel.LandoekonomiskekortDto"),
        @JsonSubTypes.Type(value = MaalebordsbladeDto.class,
                name = "dk.kortforsyningen.arkivmeta.apimodel.MaalebordsbladeDto"),
        @JsonSubTypes.Type(value = MatrikelkortDto.class,
                name = "dk.kortforsyningen.arkivmeta.apimodel.MatrikelkortDto"),
        @JsonSubTypes.Type(value = SoekortDto.class,
                name = "dk.kortforsyningen.arkivmeta.apimodel.SoekortDto"),
        @JsonSubTypes.Type(value = TematiskekortDto.class,
                name = "dk.kortforsyningen.arkivmeta.apimodel.TematiskekortDto")

})
public abstract class KortDto
{
    @Schema(description = "Unik id for kortet.")
    protected String id;

    @Schema(description = "En eventuel anden titel kortet kan have.")
    protected String alternativtitel;

    @Schema(description = "Kortets arketype.")
    protected String arketype;

    @Schema(description = "Yderligere kommentarer, f.eks. vedr. fremstilling eller placering ved kendt gods eller lign.")
    protected String bemaerkning;

    @Schema(description = "Geografisk omr??de, som kortet d??kker helt eller delvist. For eksempel Danmark, Gr??nland. Et kort kan have flere d??kningsomr??der.For pr??vekort og lignende vil d??kningsomr??det kunne angives som intet.")
    protected List<String> daekningsomraade;

    @Schema(description = "En liste af URL-stier til kortfiler efter IIIF-specifikationen.")
    protected List<String> filer;

    @Schema(description = "Starttid for kortets gyldighedsperiode. Angives i hele ??r. Gyldighedsperiodens starttid er et korts trykke-, tegne-, optage- eller opm??lings??r ??? dvs. det ??r hvor kortet kan siges at v??re nyeste kort.")
    protected Integer gaeldendefra;

    @Schema(description = "Sluttid for kortets gyldighedsperiode. Angives i hele ??r. Typisk fordi kortet erstattes af et nyere. Hvis der ikke er fundet en specifik g??ldende til periode angives et ??rstal der ligger 50 ??r efter g??ldende fra.")
    protected Integer gaeldendetil;

    @Schema(description = "Det geografiske omr??de, ofte en polygon, som kortet ligger indenfor. WKT med SRS = EPSG:4326")
    protected String geometri;

    @Schema(description = "Angivelse af geografisk omr??de, f.eks. indenfor kortbladsinddelingen eller ejerlav. For topografiske kort er inddelingen et overordnet grid hvorimod matrikelkort er indelt efter ejerlav. Kan ogs?? v??re f.eks. administrative inddelinger.")
    protected String kortbladnummer;

    @Schema(description = "Navnet p?? en logisk samling af skannede kort som kortet h??rer til, f.eks. atlasblade.")
    protected String kortvaerk;

    @Schema(description = "St??rrelsesforholdet mellem landskabet og kortets repr??sentation heraf.")
    protected String maalestok;

    // The original name has a typo
    @Schema(description = "Den projektion (afbildning af jorden p?? en plan flade) det oprindelige kort blev defineret i, f.eks. GS for Generalstabens projektion.")
    protected String originalkortprojektion;

    @Schema(description = "Kortets hj??rnekoordinater i den oprindelige kortprojektion. Beskrevet som x1,y1,x2,y2....")
    protected String originalehjoernekoordinater;

    @Schema(description = "<mangler beskrivelse>")
    protected LocalDateTime registreringfra;

    @Schema(description = "<mangler beskrivelse>")
    protected LocalDateTime registreringtil;

    @Schema(description = "Titlen p?? kortet.")
    protected String titel;

    @Schema(description = "Det navn, som entydigt definerer kortet.")
    protected String uniktkortnavn;

    public KortDto()
    {
    }

    public KortDto(String id,
                   String alternativtitel,
                   String arketype,
                   String bemaerkning,
                   List<String> daekningsomraade,
                   List<String> filer,
                   Integer gaeldendefra,
                   Integer gaeldendetil,
                   String geometri,
                   String kortbladnummer,
                   String kortvaerk,
                   String maalestok,
                   String originalkortprojektion,
                   String originalehjoernekoordinater,
                   LocalDateTime registreringfra,
                   LocalDateTime registreringtil,
                   String titel,
                   String uniktkortnavn)
    {
        this.id = id;
        this.alternativtitel = alternativtitel;
        this.arketype = arketype;
        this.bemaerkning = bemaerkning;
        this.daekningsomraade = daekningsomraade;
        this.filer = filer;
        this.gaeldendefra = gaeldendefra;
        this.gaeldendetil = gaeldendetil;
        this.geometri = geometri;
        this.kortbladnummer = kortbladnummer;
        this.kortvaerk = kortvaerk;
        this.maalestok = maalestok;
        this.originalkortprojektion = originalkortprojektion;
        this.originalehjoernekoordinater = originalehjoernekoordinater;
        this.registreringfra = registreringfra;
        this.registreringtil = registreringtil;
        this.titel = titel;
        this.uniktkortnavn = uniktkortnavn;
    }

    public String getId()
    {
        return id;
    }

    public String getAlternativtitel()
    {
        return alternativtitel;
    }

    public String getArketype()
    {
        return arketype;
    }

    public String getBemaerkning()
    {
        return bemaerkning;
    }

    public List<String> getDaekningsomraade()
    {
        return daekningsomraade;
    }

    public List<String> getFiler()
    {
        return filer;
    }

    public Integer getGaeldendefra()
    {
        return gaeldendefra;
    }

    public Integer getGaeldendetil()
    {
        return gaeldendetil;
    }

    public String getGeometri()
    {
        return geometri;
    }

    public String getKortbladnummer()
    {
        return kortbladnummer;
    }

    public String getKortvaerk()
    {
        return kortvaerk;
    }

    public String getMaalestok()
    {
        return maalestok;
    }

    public String getOriginalkortprojektion()
    {
        return originalkortprojektion;
    }

    public String getOriginalehjoernekoordinater()
    {
        return originalehjoernekoordinater;
    }

    public LocalDateTime getRegistreringfra()
    {
        return registreringfra;
    }

    public LocalDateTime getRegistreringtil()
    {
        return registreringtil;
    }

    public String getTitel()
    {
        return titel;
    }

    public String getUniktkortnavn()
    {
        return uniktkortnavn;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setAlternativtitel(String alternativtitel)
    {
        this.alternativtitel = alternativtitel;
    }

    public void setArketype(String arketype)
    {
        this.arketype = arketype;
    }

    public void setBemaerkning(String bemaerkning)
    {
        this.bemaerkning = bemaerkning;
    }

    public void setDaekningsomraade(List<String> daekningsomraade)
    {
        this.daekningsomraade = daekningsomraade;
    }

    public void setFiler(List<String> filer)
    {
        this.filer = filer;
    }

    public void setGaeldendefra(Integer gaeldendefra)
    {
        this.gaeldendefra = gaeldendefra;
    }

    public void setGaeldendetil(Integer gaeldendetil)
    {
        this.gaeldendetil = gaeldendetil;
    }

    public void setGeometri(String geometri)
    {
        this.geometri = geometri;
    }

    public void setKortbladnummer(String kortbladnummer)
    {
        this.kortbladnummer = kortbladnummer;
    }

    public void setKortvaerk(String kortvaerk)
    {
        this.kortvaerk = kortvaerk;
    }

    public void setMaalestok(String maalestok)
    {
        this.maalestok = maalestok;
    }

    public void setOriginalkortprojektion(String originalkortprojektion)
    {
        this.originalkortprojektion = originalkortprojektion;
    }

    public void setOriginalehjoernekoordinater(String originalehjoernekoordinater)
    {
        this.originalehjoernekoordinater = originalehjoernekoordinater;
    }

    public void setRegistreringfra(LocalDateTime registreringfra)
    {
        this.registreringfra = registreringfra;
    }

    public void setRegistreringtil(LocalDateTime registreringtil)
    {
        this.registreringtil = registreringtil;
    }

    public void setTitel(String titel)
    {
        this.titel = titel;
    }

    public void setUniktkortnavn(String uniktkortnavn)
    {
        this.uniktkortnavn = uniktkortnavn;
    }
}
