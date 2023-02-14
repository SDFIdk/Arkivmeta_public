package dk.dataforsyningen.arkivmeta.service;

import dk.dataforsyningen.arkivmeta.apimodel.ArketypeDto;
import dk.dataforsyningen.arkivmeta.apimodel.DaekningsomraadeDto;
import dk.dataforsyningen.arkivmeta.apimodel.KortDto;
import dk.dataforsyningen.arkivmeta.apimodel.KortParam;
import dk.dataforsyningen.arkivmeta.apimodel.KortResult;
import dk.dataforsyningen.arkivmeta.apimodel.KortvaerkDto;
import dk.dataforsyningen.arkivmeta.apimodel.MaalestokDto;
import dk.dataforsyningen.arkivmeta.dao.IArketypeDao;
import dk.dataforsyningen.arkivmeta.dao.IDaekningsomraadeDao;
import dk.dataforsyningen.arkivmeta.dao.IKortDBDao;
import dk.dataforsyningen.arkivmeta.dao.IKortvaerkerDao;
import dk.dataforsyningen.arkivmeta.dao.IMaalestokDao;
import dk.dataforsyningen.arkivmeta.rest.ArkivApiService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ArkivService implements IArkivService {
  private static final Logger logger = LoggerFactory.getLogger(ArkivApiService.class);
  private final IArketypeDao iArketypeDao;
  private final IDaekningsomraadeDao iDaekningsomraadeDao;
  private final IKortDBDao iKortDBDao;
  private final IKortvaerkerDao iKortvaerkerDao;
  private final IMaalestokDao iMaalestokDao;

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
  public ArkivService(@Qualifier("arketypeDao") IArketypeDao iArketypeDao,
                      @Qualifier("daekningsomraadeDao") IDaekningsomraadeDao iDaekningsomraadeDao,
                      @Qualifier("kortDBDao") IKortDBDao iKortDBDao,
                      @Qualifier("kortvaerkerDao") IKortvaerkerDao iKortvaerkerDao,
                      @Qualifier("maalestokDao") IMaalestokDao iMaalestokDao) {
    this.iArketypeDao = iArketypeDao;
    this.iDaekningsomraadeDao = iDaekningsomraadeDao;
    this.iKortDBDao = iKortDBDao;
    this.iKortvaerkerDao = iKortvaerkerDao;
    this.iMaalestokDao = iMaalestokDao;
  }

  /**
   * @return list of ArketypeDto with all arketyper, arkenavn and kortvaerker belonging to each arketyper
   */
  @Override
  @Cacheable("arketyper")
  public List<ArketypeDto> getArketyper() {
    return iArketypeDao.getAllArketyper();
  }

  /**
   * Kort's id in the database consists of it's arketype and id. There can be given incorrect arketype and/or id, so
   * there is used Optional to take care if the result is null.
   * Because Gravitee does not set X-Forwarded-* headers it's needed to manually set them.
   * Need to replace the url return from concatXFHeaders and add /iiif/3 to the url because of Cantaloupe
   *
   * @param arketype
   * @param id
   * @return kortDto with the matching datamodel of the kort
   */
  @Override
  public KortDto getKortById(String arketype, String id) {
    String searchId = arketype + "/" + id;

    Optional<KortDto> returnedKort = iKortDBDao.getKortById(searchId);

    return returnedKort.orElseThrow(
        () -> new NoSuchElementException("Intet kort matchede det givne id: " + searchId));
  }

  /**
   * Returns the list of DaekningsomraadeDto starting with the given search criteria and ignorering the case
   *
   * @return list of DaekningsomraadeDto of all daekningsomraader available
   */
  @Override
  @Cacheable("daekningsomraader")
  public List<DaekningsomraadeDto> getDaekningsomraader(String daekningsomraade) {
    List<DaekningsomraadeDto> daekningsomraadeDtoList = iDaekningsomraadeDao
        .getDaekningsomraade(daekningsomraade);
    return daekningsomraadeDtoList;
  }

  /**
   * Returns the list of KortvaerkDto starting with the given search criteria and ignorering the case
   *
   * @return list of KortvaerkDto of all kortvaerk available
   */
  @Override
  @Cacheable("kortvaerker")
  public List<KortvaerkDto> getKortvaerker(String arketype, String kortvaerk) {
    if (StringUtils.isNotBlank(arketype)) {
      List<KortvaerkDto> kortvaerkDtoList =
          iKortvaerkerDao.getArketypeAndKortvaerk(
              arketype.toUpperCase(), kortvaerk);
      return kortvaerkDtoList;
    } else {
      List<KortvaerkDto> kortvaerkDtoList =
          iKortvaerkerDao.getKortvaerk(kortvaerk);
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
  public List<MaalestokDto> getMaalestokke(String maalestok) {
    List<MaalestokDto> maalestokDtoList =
        iMaalestokDao.getMaalestok(maalestok);
    return maalestokDtoList;
  }

  //@Cacheable(cacheNames = "kort", key = "#kortParam")
  public KortResult getKortResult(KortParam kortParam) {
    if (kortParam.getLimit() < 0) {
      throw new IllegalArgumentException("negative values not allowed for limit!");
    }

    if (kortParam.getLimit() > 1000) {
      throw new IllegalArgumentException("limit must not be higher than 1000");
    }

    Set<String> sortDirections = Set.of("asc", "desc");
    if (!sortDirections.contains(kortParam.getDirection())) {
      throw new IllegalArgumentException("direction must be: asc or desc");
    }

    List<String> predicates = new ArrayList<>();
    Map<String, Object> params = new HashMap<>();

//    if (StringUtils.isNotBlank(kortParam.getGeometri())) {
//      try {
//        Geometry area = new WKTReader().read(kortParam.getGeometri());
//        predicates.add("ST_Intersects(geometri, ST_SetSRID(:area, 4326))=true");
//        params.put("area", area);
//      } catch (ParseException parseException) {
//        throw new IllegalArgumentException(
//            "Kan ikke læse geometri. Skal være som WKT med SRS = EPSG:4326",
//            parseException);
//      }
//    }
    Geometry area = new GeometryFactory().createGeometry(null);
    if (StringUtils.isNotBlank(kortParam.getGeometri())) {
      try {
        area = new WKTReader().read(kortParam.getGeometri());
      } catch (ParseException parseException) {
        throw new IllegalArgumentException(
            "Could not read geometry. Should be a WKT with SRS = EPSG:4326", parseException);
      }
    }

    // For using SIMILAR TO in sql
    String daekningsomraade = StringUtils.join(kortParam.getDaekningsomraade(), "|");
    String kortvaerk = StringUtils.join(kortParam.getKortvaerk(), "|");

//    if (kortParam.getArketype() != null && !kortParam.getArketype().isEmpty()) {
//      predicates.add("arketype IN :arketype");
//      params.put("arketype", kortParam.getArketype());
//    }

//    if (kortParam.getDaekningsomraade() != null && !kortParam.getDaekningsomraade().isEmpty()) {
//      String predicate = "(";
//
//      for (int i = 0; i < kortParam.getDaekningsomraade().size(); i++) {
//        String daekningsomraade = kortParam.getDaekningsomraade().get(i);
//        predicate += "daekningsomraade like :daekningsomraade" + i;
//        if (i < kortParam.getDaekningsomraade().size() - 1) {
//          predicate += " or ";
//        }
//        params.put("daekningsomraade" + i, "%" + daekningsomraade + "%");
//      }
//      predicate += ")";
//      predicates.add(predicate);
//    }

//    if (StringUtils.isNotBlank(kortParam.getKortbladnummer())) {
//      predicates.add("(lower(kortbladnummer) like lower(:kortbladnummer))");
//      params.put("kortbladnummer", "%" + kortParam.getKortbladnummer() + "%");
//    }
//
//    if (kortParam.getMaalestok() != null && !kortParam.getMaalestok().isEmpty()) {
//      predicates.add("maalestok IN :maalestok");
//      params.put("maalestok", kortParam.getMaalestok());
//    }
//
//    if (StringUtils.isNotBlank(kortParam.getTegner())) {
//      predicates.add("(lower(tegner) like lower(:tegner))");
//      params.put("tegner", "%" + kortParam.getTegner() + "%");
//    }
//
//    if (StringUtils.isNotBlank(kortParam.getTitel())) {
//      predicates.add("(lower(titel) like lower(:titel))");
//      params.put("titel", "%" + kortParam.getTitel() + "%");
//    }
//
//    if (StringUtils.isNotBlank(kortParam.getFritekstsoegning())) {
//      predicates.add("(fts(:fritekstsoegning) = true)");
//      params.put("fritekstsoegning", kortParam.getFritekstsoegning());
//    }
//
//    if (kortParam.getKortvaerk() != null && !kortParam.getKortvaerk().isEmpty()) {
//      predicates.add("kortvaerk IN :kortvaerk");
//      params.put("kortvaerk", kortParam.getKortvaerk());
//    }
//
//    if (kortParam.getGaeldendefra() > 0 & kortParam.getGaeldendetil() > 0) {
//      predicates.add("(gaeldendeperiode_gaeldendetil >= :gaeldendefra");
//      predicates.add("gaeldendeperiode_gaeldendefra <= :gaeldendetil)");
//      params.put("gaeldendefra", kortParam.getGaeldendefra());
//      params.put("gaeldendetil", kortParam.getGaeldendetil());
//    } else if (kortParam.getGaeldendefra() > 0) {
//      predicates.add(
//          "(gaeldendeperiode_gaeldendefra >= :gaeldendefra or gaeldendeperiode_gaeldendefra < :gaeldendefra and gaeldendeperiode_gaeldendetil >= :gaeldendefra)");
//
//      params.put("gaeldendefra", kortParam.getGaeldendefra());
//    } else if (kortParam.getGaeldendetil() > 0) {
//      predicates.add(
//          "(gaeldendeperiode_gaeldendetil <= :gaeldendetil or gaeldendeperiode_gaeldendetil > :gaeldendetil and gaeldendeperiode_gaeldendefra <= :gaeldendetil)");
//
//      params.put("gaeldendetil", kortParam.getGaeldendetil());
//    }
//
//    String and = String.join(" and ", predicates);
    //String orderBy = getOrderBy(kortParam);

//    String whereClause;

//    if (and.isBlank()) {
//      whereClause = " ";
//    } else {
//      whereClause = " where " + and;
//    }

    //String jql = "FROM KortDB" + whereClause + orderBy;
    //logger.info(jql);
//    TypedQuery<KortDB> query = entityManager.createQuery(jql, KortDB.class);
//    for (Map.Entry<String, Object> param : params.entrySet()) {
//      query.setParameter(param.getKey(), param.getValue());
//    }

//    query.setMaxResults(kortParam.getLimit());
//    query.setFirstResult(kortParam.getOffset());

    //List<KortDB> result = query.getResultList();

//    List<KortDto> kortDtoList = iKortDBDao.getAllKort(
//        kortParam.getArketype(), kortParam.getDaekningsomraade(), kortParam.getDirection(),
//        kortParam.getFritekstsoegning(), kortParam.getGaeldendefra(), kortParam.getGaeldendetil(),
//        area, kortParam.getKortbladnummer(), kortParam.getKortvaerk(), kortParam.getMaalestok(),
//        kortParam.getOffset(), kortParam.getLimit(), kortParam.getSort(), kortParam.getTegner(),
//        kortParam.getTitel());

    List<KortDto> kortDtoList = iKortDBDao.getAllKort(
        kortParam.getArketype(), daekningsomraade, kortParam.getFritekstsoegning(),
        kortParam.getGaeldendefra(), kortParam.getGaeldendetil(), area, kortParam.getKortbladnummer(),
        kortvaerk, kortParam.getMaalestok(), kortParam.getLimit(), kortParam.getOffset(), kortParam.getSort(), kortParam.getDirection()) ;


    long count;

    if (kortDtoList.size() >= kortParam.getLimit()) {
      count = kortDtoList.size();
      //count = iKortDBDao.getCount(kortParam, area);
    } else {
      count = kortDtoList.size();
    }

    KortResult kortresult = new KortResult(count, kortDtoList);
    return kortresult;
  }

  private String getOrderBy(KortParam kortParam) {
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

    if (StringUtils.isNotBlank(kortParam.getSort())) {
      if (!sortColumns.containsKey(kortParam.getSort())) {
        throw new IllegalArgumentException("sort must be one of " + sortColumns.keySet());
      }

      String sortBy = sortColumns.get(kortParam.getSort());
      orderby.add(String.format("%s %s", sortBy, kortParam.getDirection()));
    }

    if (StringUtils.isNotBlank(kortParam.getFritekstsoegning())) {
      orderby.add("rnk(:fritekstsoegning)");
    }

    // Added id to orderby, so sorting is always consistent
    orderby.add("id");

    return " order by " + StringUtils.join(orderby, ',');
  }

  /**
   * Find the count of kort in the list
   *
   * @param whereClause
   * @param params
   * @return
   */
//  private Long findCount(String whereClause, Map<String, Object> params) {
//    String jql = "select count(k) from KortDB k" + whereClause;
//    Query query = entityManager.createQuery(jql);
//    for (Map.Entry<String, Object> param : params.entrySet()) {
//      query.setParameter(param.getKey(), param.getValue());
//    }
//    return (Long) query.getSingleResult();
//  }
}
