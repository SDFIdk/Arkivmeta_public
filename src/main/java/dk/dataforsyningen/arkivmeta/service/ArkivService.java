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
import java.util.List;
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

  @Cacheable(cacheNames = "kort", key = "#kortParam")
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

    List<KortDto> kortDtoList = iKortDBDao.getAllKort(
        kortParam.getArketype(), daekningsomraade, kortParam.getFritekstsoegning(),
        kortParam.getGaeldendefra(), kortParam.getGaeldendetil(), area,
        kortParam.getKortbladnummer(), kortvaerk, kortParam.getMaalestok(), kortParam.getTegner(),
        kortParam.getTitel(), kortParam.getLimit(), kortParam.getOffset(), kortParam.getSort(),
        kortParam.getDirection());

    long count;

    if (kortDtoList.size() >= kortParam.getLimit()) {
      count = iKortDBDao.getCount(kortParam.getArketype(), daekningsomraade,
          kortParam.getFritekstsoegning(), kortParam.getGaeldendefra(), kortParam.getGaeldendetil(),
          area, kortParam.getKortbladnummer(), kortvaerk, kortParam.getMaalestok(),
          kortParam.getTegner(), kortParam.getTitel(), kortParam.getLimit(), kortParam.getOffset());
    } else {
      count = kortDtoList.size();
    }

    KortResult kortresult = new KortResult(count, kortDtoList);
    return kortresult;
  }
}
