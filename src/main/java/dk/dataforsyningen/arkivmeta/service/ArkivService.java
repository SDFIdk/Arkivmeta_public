package dk.dataforsyningen.arkivmeta.service;

import dk.dataforsyningen.arkivmeta.apimapper.ArketypeMapper;
import dk.dataforsyningen.arkivmeta.apimapper.KortMapper;
import dk.dataforsyningen.arkivmeta.apimodel.*;
import dk.dataforsyningen.arkivmeta.datamodel.ArketypeDB;
import dk.dataforsyningen.arkivmeta.datamodel.KortDB;
import dk.dataforsyningen.arkivmeta.repository.*;
import dk.dataforsyningen.arkivmeta.rest.ArkivApiService;
import org.apache.commons.lang3.StringUtils;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Service
public class ArkivService implements IArkivService
{
    private static final Logger logger = LoggerFactory.getLogger(ArkivApiService.class);
    // TODO: Should there be @PersistenceContext on this field?
    private final EntityManager entityManager;
    private final IArketypeRepository iArketypeRepository;
    private final IDaekningsomraadeRepository iDaekningsomraadeRepository;
    private final IKortDBRepository iKortDBRepository;
    private final IKortvaerkerRepository iKortvaerkerRepository;
    private final IMaalestokRepository iMaalestokRepository;
    private final KortMapper kortMapper;
    private final ArketypeMapper arketypeMapper;

    /**
     * Constructor injection
     * We do not have to specify @Autowired, as long as the class only have one constructor and the class itself
     * is annotated with a Spring bean, because Spring automatic make the dependency to be injected via the constructor.
     * It is used here just for readability
     * <p>
     * To understand how constructor injection works:
     * https://stackoverflow.com/questions/40620000/spring-autowire-on-properties-vs-constructor
     * https://reflectoring.io/constructor-injection/
     */
    public ArkivService(EntityManager entityManager,
                        IArketypeRepository iArketypeRepository,
                        IDaekningsomraadeRepository iDaekningsomraadeRepository,
                        IKortDBRepository iKortDBRepository,
                        IKortvaerkerRepository iKortvaerkerRepository,
                        IMaalestokRepository iMaalestokRepository,
                        KortMapper kortMapper, ArketypeMapper arketypeMapper)
    {
        this.entityManager = entityManager;
        this.iArketypeRepository = iArketypeRepository;
        this.iDaekningsomraadeRepository = iDaekningsomraadeRepository;
        this.iKortDBRepository = iKortDBRepository;
        this.iKortvaerkerRepository = iKortvaerkerRepository;
        this.iMaalestokRepository = iMaalestokRepository;
        this.kortMapper = kortMapper;
        this.arketypeMapper = arketypeMapper;
    }

    /**
     * @return list of ArketypeDto with all arketyper, arkenavn and kortvaerker belonging to each arketyper
     */
    @Override
    @Cacheable("arketyper")
    public List<ArketypeDto> getArketyper()
    {
        List<ArketypeDB> arketypeDBList = iArketypeRepository.findAll();

        List<ArketypeDto> arketypeDtoList = new ArrayList<>();
        for (ArketypeDB arketype : arketypeDBList)
        {
            arketypeDtoList.add(arketypeMapper.arketypeToArketypeDto(arketype));
        }

        return arketypeDtoList;
    }

    /**
     * Kort's id in the database consists of it's arketype and id. There can be given incorrect arketype and/or id, so
     * there is used Optional to take care if the result is null.
     * Because Gravitee does not set X-Forwarded-* headers it's needed to manually set them.
     * Need to replace the url return from concatXFHeaders and add /iiif/3 to the url because of Cantaloupe
     *
     * @param arketype
     * @param id
     * @param baseUrl
     * @return kortDto with the matching datamodel of the kort
     */
    @Override
    public KortDto getKortById(String arketype, String id, String baseUrl)
    {
        String searchId = arketype + "/" + id;

        Optional<KortDB> returnedKort = iKortDBRepository.findById(searchId);

        KortDB kort = returnedKort
                .orElseThrow(() -> new EntityNotFoundException("Intet kort matchede det givne id: " + searchId));

        String iiifPrefix = getIiifPrefix(baseUrl);

        KortDto dto = kortMapper.map(kort);

        setIiif(iiifPrefix, dto);

        return dto;
    }

    /**
     * Returns the list of DaekningsomraadeDto starting with the given search criteria and ignorering the case
     *
     * @return list of DaekningsomraadeDto of all daekningsomraader available
     */
    @Override
    @Cacheable("daekningsomraader")
    public List<DaekningsomraadeDto> getDaekningsomraader(String daekningsomraade)
    {
        List<DaekningsomraadeDto> daekningsomraadeDtoList = iDaekningsomraadeRepository
                .findByDaekningsomraadeContainingIgnoreCase(daekningsomraade);
        return daekningsomraadeDtoList;
    }

    /**
     * Returns the list of KortvaerkDto starting with the given search criteria and ignorering the case
     *
     * @return list of KortvaerkDto of all kortvaerk available
     */
    @Override
    @Cacheable("kortvaerker")
    public List<KortvaerkDto> getKortvaerker(String arketype, String kortvaerk)
    {
        if (StringUtils.isNotBlank(arketype))
        {
            List<KortvaerkDto> kortvaerkDtoList = iKortvaerkerRepository.findByArketypeAndKortvaerkContainingIgnoreCase(
                    arketype.toUpperCase(), kortvaerk);
            return kortvaerkDtoList;
        }
        else
        {
            List<KortvaerkDto> kortvaerkDtoList = iKortvaerkerRepository.findByKortvaerkContainingIgnoreCase(kortvaerk);
            return kortvaerkDtoList;
        }
    }

    /**
     * Returns the list of MaalestokDto starting with the given search criteria and ignorering the case
     *
     * @return list of MaalestokDto of all maalestok available
     */
    @Override
    @Cacheable("maalestokke")
    public List<MaalestokDto> getMaalestokke(String maalestok)
    {
        List<MaalestokDto> maalestokDtoList = iMaalestokRepository.findByMaalestokContainingIgnoreCase(maalestok);
        return maalestokDtoList;
    }

    @Cacheable(cacheNames = "kort", key = "#kortParam")
    public KortResult getKortResult(KortParam kortParam, String baseUrl)
    {
        if (kortParam.getPagesize() < 0)
        {
            throw new IllegalArgumentException("negative values not allowed for page size!");
        }

        if (kortParam.getPagesize() > 1000)
        {
            throw new IllegalArgumentException("page size must not be higher than 1000");
        }

        Set<String> sortDirections = Set.of("asc", "desc");
        if (!sortDirections.contains(kortParam.getDirection()))
        {
            throw new IllegalArgumentException("direction must be: asc or desc");
        }

        List<String> predicates = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();

        if (StringUtils.isNotBlank(kortParam.getGeometri()))
        {
            try
            {
                Geometry area = new WKTReader().read(kortParam.getGeometri());
                predicates.add("ST_Intersects(geometri, ST_SetSRID(:area, 4326))=true");
                params.put("area", area);
            }
            catch (ParseException parseException)
            {
                throw new IllegalArgumentException("Kan ikke læse geometri. Skal være som WKT med SRS = EPSG:4326",
                                                   parseException);
            }
        }

        if (kortParam.getArketype() != null && !kortParam.getArketype().isEmpty())
        {
            predicates.add("arketype IN :arketype");
            params.put("arketype", kortParam.getArketype());
        }

        if (kortParam.getDaekningsomraade() != null && !kortParam.getDaekningsomraade().isEmpty())
        {
            String predicate = "(";

            for (int i = 0; i < kortParam.getDaekningsomraade().size(); i++)
            {
                String daekningsomraade = kortParam.getDaekningsomraade().get(i);
                predicate += "daekningsomraade like :daekningsomraade" + i;
                if (i < kortParam.getDaekningsomraade().size() - 1)
                {
                    predicate += " or ";
                }
                params.put("daekningsomraade" + i, "%" + daekningsomraade + "%");
            }
            predicate += ")";
            predicates.add(predicate);
        }

        if (StringUtils.isNotBlank(kortParam.getKortbladnummer()))
        {
            predicates.add("(lower(kortbladnummer) like lower(:kortbladnummer))");
            params.put("kortbladnummer", "%" + kortParam.getKortbladnummer() + "%");
        }

        if (kortParam.getMaalestok() != null && !kortParam.getMaalestok().isEmpty())
        {
            predicates.add("maalestok IN :maalestok");
            params.put("maalestok", kortParam.getMaalestok());
        }

        if (StringUtils.isNotBlank(kortParam.getTegner()))
        {
            predicates.add("(lower(tegner) like lower(:tegner))");
            params.put("tegner", "%" + kortParam.getTegner() + "%");
        }

        if (StringUtils.isNotBlank(kortParam.getTitel()))
        {
            predicates.add("(lower(titel) like lower(:titel))");
            params.put("titel", "%" + kortParam.getTitel() + "%");
        }

        if (StringUtils.isNotBlank(kortParam.getFritekstsoegning()))
        {
            predicates.add("(fts(:fritekstsoegning) = true)");
            params.put("fritekstsoegning", kortParam.getFritekstsoegning());
        }

        if (kortParam.getKortvaerk() != null && !kortParam.getKortvaerk().isEmpty())
        {
            predicates.add("kortvaerk IN :kortvaerk");
            params.put("kortvaerk", kortParam.getKortvaerk());
        }

        if (kortParam.getGaeldendefra() > 0 & kortParam.getGaeldendetil() > 0)
        {
            predicates.add("(gaeldendeperiode_gaeldendetil >= :gaeldendefra");
            predicates.add("gaeldendeperiode_gaeldendefra <= :gaeldendetil)");
            params.put("gaeldendefra", kortParam.getGaeldendefra());
            params.put("gaeldendetil", kortParam.getGaeldendetil());
        }
        else if (kortParam.getGaeldendefra() > 0)
        {
            predicates.add(
                    "(gaeldendeperiode_gaeldendefra >= :gaeldendefra or gaeldendeperiode_gaeldendefra < :gaeldendefra and gaeldendeperiode_gaeldendetil >= :gaeldendefra)");

            params.put("gaeldendefra", kortParam.getGaeldendefra());
        }
        else if (kortParam.getGaeldendetil() > 0)
        {
            predicates.add(
                    "(gaeldendeperiode_gaeldendetil <= :gaeldendetil or gaeldendeperiode_gaeldendetil > :gaeldendetil and gaeldendeperiode_gaeldendefra <= :gaeldendetil)");

            params.put("gaeldendetil", kortParam.getGaeldendetil());
        }

        String and = String.join(" and ", predicates);
        String orderBy = getOrderBy(kortParam);

        String whereClause;

        if (and.isBlank())
        {
            whereClause = " ";
        }
        else
        {
            whereClause = " where " + and;
        }

        String jql = "FROM KortDB" + whereClause + orderBy;
        logger.info(jql);
        TypedQuery<KortDB> query = entityManager.createQuery(jql, KortDB.class);
        for (Map.Entry<String, Object> param : params.entrySet())
        {
            query.setParameter(param.getKey(), param.getValue());
        }

        query.setMaxResults(kortParam.getPagesize());
        query.setFirstResult(kortParam.getOffset());

        List<KortDB> result = query.getResultList();

        long count;

        if (result.size() >= kortParam.getPagesize())
        {
            count = findCount(whereClause, params);
        }
        else
        {
            count = result.size();
        }

        List<KortDto> res = getAllKort(result, baseUrl);
        KortResult kortresult = new KortResult(count, res);
        return kortresult;
    }

    private String getOrderBy(KortParam kortParam)
    {
        Map<String, String> sortColumns = Map.of(
                "arketype",
                "arketype",
                "daekningsomraade",
                "daekningsomraade",
                "gaeldendefra",
                "gaeldendeperiode_gaeldendefra",
                "gaeldendetil",
                "gaeldendeperiode_gaeldendetil",
                "id",
                "id",
                "kortvaerk",
                "kortvaerk",
                "maalestok",
                "maalestok",
                "titel",
                "titel"
        );

        List<String> orderby = new LinkedList<>();

        if (StringUtils.isNotBlank(kortParam.getSort()))
        {
            if (!sortColumns.containsKey(kortParam.getSort()))
            {
                throw new IllegalArgumentException("sort must be one of " + sortColumns.keySet());
            }

            String sortBy = sortColumns.get(kortParam.getSort());
            orderby.add(String.format("%s %s", sortBy, kortParam.getDirection()));
        }

        if (StringUtils.isNotBlank(kortParam.getFritekstsoegning()))
        {
            orderby.add("rnk(:fritekstsoegning)");
        }

        // Added id to orderby, so sorting is always consistent
        orderby.add("id");

        return " order by " + StringUtils.join(orderby, ',');
    }

    /**
     * Gets the object of KortDB mapped to their right dto. The baseurl gets changed so it can be used to fetch
     * images from Cantaloupe
     *
     * @param kortDB
     * @param baseUrl
     * @return list of KortDto
     */
    private List<KortDto> getAllKort(List<KortDB> kortDB, String baseUrl)
    {
        List<KortDto> resultlist = new ArrayList<>();

        String iiifPrefix = getIiifPrefix(baseUrl);

        for (KortDB kort : kortDB)
        {
            KortDto dto = kortMapper.map(kort);

            setIiif(iiifPrefix, dto);
            resultlist.add(dto);
        }

        return resultlist;
    }

    /**
     * Change the baseurl, so it can be the url Cantaloupe gets
     *
     * @param baseUrl
     * @return url of String
     */
    private String getIiifPrefix(String baseUrl)
    {
        return baseUrl.replace("arkivmeta", "arkivkort") + "/iiif/3";
    }

    /**
     * Sets the iiifPrefix in the list of filer
     *
     * @param iiifPrefix
     * @param dto
     */
    private void setIiif(String iiifPrefix, KortDto dto)
    {
        for (int i = 0; i < dto.getFiler().size(); i++)
        {
            String fil = iiifPrefix + dto.getFiler().get(i);
            dto.getFiler().set(i, fil);
        }
    }

    /**
     * Find the count of kort in the list
     *
     * @param whereClause
     * @param params
     * @return
     */
    private Long findCount(String whereClause, Map<String, Object> params)
    {
        String jql = "select count(k) from KortDB k" + whereClause;
        Query query = entityManager.createQuery(jql);
        for (Map.Entry<String, Object> param : params.entrySet())
        {
            query.setParameter(param.getKey(), param.getValue());
        }
        return (Long) query.getSingleResult();
    }
}
