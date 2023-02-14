package dk.dataforsyningen.arkivmeta.dao;

/**
 * Expose a custom "fts" function to JPA queries
 */
public class PostgresFullTextSearchJPAPlugin {
//  @Override
//  public void contribute(MetadataBuilder metadataBuilder) {
//    metadataBuilder.applySqlFunction("fts",
//        new SQLFunctionTemplate(BooleanType.INSTANCE,
//            "fritekstsoegning @@ plainto_tsquery('danish', ?1)"));
//    metadataBuilder.applySqlFunction("rnk",
//        new SQLFunctionTemplate(BigDecimalType.INSTANCE,
//            "ts_rank(fritekstsoegning, plainto_tsquery('danish', ?1))"));
//  }
}