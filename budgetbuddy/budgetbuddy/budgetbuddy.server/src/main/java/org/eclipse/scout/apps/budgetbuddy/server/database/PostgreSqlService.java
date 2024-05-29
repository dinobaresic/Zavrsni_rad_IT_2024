package org.eclipse.scout.apps.budgetbuddy.server.database;

import org.eclipse.scout.apps.budgetbuddy.server.database.DatabaseProperties.PasswordProperty;
import org.eclipse.scout.apps.budgetbuddy.server.database.DatabaseProperties.URLProperty;
import org.eclipse.scout.apps.budgetbuddy.server.database.DatabaseProperties.UsernameProperty;
import org.eclipse.scout.rt.platform.config.CONFIG;
import org.eclipse.scout.rt.server.jdbc.postgresql.AbstractPostgreSqlService;
import org.eclipse.scout.rt.server.jdbc.postgresql.PostgreSqlStyle;
import org.eclipse.scout.rt.server.jdbc.style.ISqlStyle;

public class PostgreSqlService extends AbstractPostgreSqlService {
  @Override
  protected Class<? extends ISqlStyle> getConfiguredSqlStyle() {
    return PostgreSqlStyle.class;
  }
  @Override
  protected String getConfiguredJdbcMappingName() {
    return CONFIG.getPropertyValue(DatabaseProperties.URLProperty.class);
  }
  @Override
  protected String getConfiguredPassword() {
    return CONFIG.getPropertyValue(PasswordProperty.class);
  }
  @Override
  protected String getConfiguredUsername() {
    return CONFIG.getPropertyValue(UsernameProperty.class);
  }
}
