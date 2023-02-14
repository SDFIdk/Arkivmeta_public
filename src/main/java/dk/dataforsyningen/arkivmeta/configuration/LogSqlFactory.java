package dk.dataforsyningen.arkivmeta.configuration;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.jdbi.v3.core.statement.SqlLogger;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.sqlobject.customizer.SqlStatementCustomizer;
import org.jdbi.v3.sqlobject.customizer.SqlStatementCustomizerFactory;
import org.jdbi.v3.sqlobject.customizer.SqlStatementCustomizingAnnotation;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SqlStatementCustomizingAnnotation(LogSqlFactory.Factory.class)
public @interface LogSqlFactory {

  class Factory implements SqlStatementCustomizerFactory {
    @Override
    public SqlStatementCustomizer createForType(Annotation annotation, Class sqlObjectType) {
      SqlLogger sqlLogger = new SqlLogger() {
        @Override
        public void logBeforeExecution(StatementContext context) {
          logSql(context);
        }
//        @Override
//        public void logAfterExecution(StatementContext context) {
//          logSql(context);
//        }
      };
      return statement -> statement.setSqlLogger(sqlLogger);
    }

    private static void logSql(StatementContext context) {
//      System.out.println("Raw SQL:\n" + context.getRawSql());
//      System.out.println("Parsed SQL:\n" + context.getParsedSql().getSql());
      System.out.println("Statement SQL:\n" + context.getStatement());
    }
  }
}
