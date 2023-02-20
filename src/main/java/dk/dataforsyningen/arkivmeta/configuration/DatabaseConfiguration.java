package dk.dataforsyningen.arkivmeta.configuration;

import java.sql.Types;
import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.argument.Arguments;
import org.jdbi.v3.core.argument.NullArgument;
import org.jdbi.v3.jackson2.Jackson2Plugin;
import org.jdbi.v3.postgis.PostgisPlugin;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * The project need to connect to multiple datasources. One to certificate user and file(s) and the
 * other to save the order Configuration to multiple datasources is inspired from here:
 * https://gist.github.com/dakoctba/1047ca084118ff46a1a917726f99a2b2
 * https://stackoverflow.com/questions/30362546/how-to-use-2-or-more-databases-with-spring
 */
@Configuration
public class DatabaseConfiguration {
  /**
   * The SQL data source that Jdbi will connect to. In this example we use an H2 database, but it
   * can be any JDBC-compatible database. https://jdbi.org/#_spring5
   *
   * @return
   */
  @Bean(name = "arkivmeta")
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource driverManagerDataSource() {
    return new DriverManagerDataSource();
  }

  /**
   * Enable configuration of transactions via annotations. https://jdbi.org/#_spring5
   *
   * @param dataSource
   * @return
   */
  @Bean(name = "arkivmetaTransactionManager")
  @Primary
  public DataSourceTransactionManager dataSourceTransactionManager(
      @Qualifier("arkivmeta") DataSource dataSource) {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(dataSource);
    return dataSourceTransactionManager;
  }

  @Bean(name = "arkivmetaJdbi")
  public Jdbi jdbi(@Qualifier("arkivmeta") DataSource dataSource) {
    Jdbi jdbi = Jdbi.create(dataSource)
        .installPlugin(new SqlObjectPlugin())
        .installPlugin(new PostgresPlugin())
        .installPlugin(new PostgisPlugin())
        .installPlugin(new Jackson2Plugin());

    // https://stackoverflow.com/questions/48254280/why-does-jdbi-bind-fail-with-function-as-parameter
    jdbi.getConfig(Arguments.class)
        .setUntypedNullArgument(new NullArgument(Types.OTHER));

    return jdbi;
  }
}
