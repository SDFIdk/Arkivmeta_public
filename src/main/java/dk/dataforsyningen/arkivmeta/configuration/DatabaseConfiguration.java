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

@Configuration
public class DatabaseConfiguration {
  /**
   * The SQL data source that Jdbi will connect to. https://jdbi.org/#_spring_5
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
   * Enable configuration of transactions via annotations. https://jdbi.org/#_spring_5
   *
   * @param dataSource
   * @return DataSourceTransactionManager
   */
  @Bean(name = "arkivmetaTransactionManager")
  @Primary
  public DataSourceTransactionManager dataSourceTransactionManager(
          @Qualifier("arkivmeta") DataSource dataSource) {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(dataSource);
    return dataSourceTransactionManager;
  }

  /**
   * Enable plugins
   *
   * @param dataSource
   * @return
   */
  @Bean(name = "arkivmetaJdbi")
  public Jdbi jdbi(@Qualifier("arkivmeta") DataSource dataSource) {
    Jdbi jdbi = Jdbi.create(dataSource)
            .installPlugin(new SqlObjectPlugin())
            .installPlugin(new PostgresPlugin())
            .installPlugin(new PostgisPlugin())
            .installPlugin(new Jackson2Plugin());

    // Enable to bind NUll values when inserting (for postgres it is Other)
    // https://stackoverflow.com/questions/48254280/why-does-jdbi-bind-fail-with-function-as-parameter
    jdbi.getConfig(Arguments.class)
            .setUntypedNullArgument(new NullArgument(Types.OTHER));

    return jdbi;
  }
}
