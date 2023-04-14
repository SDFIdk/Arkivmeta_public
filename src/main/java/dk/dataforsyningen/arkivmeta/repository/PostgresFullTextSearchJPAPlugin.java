package dk.dataforsyningen.arkivmeta.repository;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.BooleanType;

/**
 * Expose a custom "fts" function to JPA queries
 */
public class PostgresFullTextSearchJPAPlugin implements MetadataBuilderContributor {
  @Override
  public void contribute(MetadataBuilder metadataBuilder) {
    metadataBuilder.applySqlFunction("fts",
        new SQLFunctionTemplate(BooleanType.INSTANCE,
            "fritekstsoegning @@ plainto_tsquery('simple', ?1)"));
    metadataBuilder.applySqlFunction("rnk",
        new SQLFunctionTemplate(BigDecimalType.INSTANCE,
            "ts_rank(fritekstsoegning, plainto_tsquery('simple', ?1))"));
  }
}
