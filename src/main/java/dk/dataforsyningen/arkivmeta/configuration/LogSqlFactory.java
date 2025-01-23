package dk.dataforsyningen.arkivmeta.configuration;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jdbi.v3.core.statement.SqlLogger;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.sqlobject.customizer.SqlStatementCustomizer;
import org.jdbi.v3.sqlobject.customizer.SqlStatementCustomizerFactory;
import org.jdbi.v3.sqlobject.customizer.SqlStatementCustomizingAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SqlStatementCustomizingAnnotation(LogSqlFactory.Factory.class)
public @interface LogSqlFactory {

  class Factory implements SqlStatementCustomizerFactory {
    private static void logSql(StatementContext context) {
      Logger logger = LoggerFactory.getLogger(LogSqlFactory.class);
      logger.debug("Statement SQL:\n{}", context.getStatement());
    }

    @Override
    public SqlStatementCustomizer createForType(Annotation annotation, Class sqlObjectType) {
      SqlLogger sqlLogger = new SqlLogger() {
        @Override
        public void logBeforeExecution(StatementContext context) {
          logSql(context);
        }
      };
      return statement -> statement.setSqlLogger(sqlLogger);
    }
  }
}