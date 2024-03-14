package org.eclipse.scout.apps.budgetbuddy.server.lookups;

import org.eclipse.scout.apps.budgetbuddy.shared.lookups.ICategoriesLookupService;
import org.eclipse.scout.rt.server.jdbc.lookup.AbstractSqlLookupService;


import java.util.List;

public class CategoriesLookupService extends AbstractSqlLookupService<Long> implements ICategoriesLookupService {

  @Override
  protected String getConfiguredSqlSelect() {
    StringBuffer stmt = new StringBuffer();
    stmt.append(" SELECT id, name ");
    stmt.append(" FROM categories ");
    stmt.append(" WHERE is_deleted IS FALSE ");
    stmt.append(" <key> AND id = :key </key> ");
    stmt.append(" <text> AND UPPER(name) LIKE UPPER('%'||:text||'%') </text><all></all> ");
    stmt.append(" ORDER BY name ");
    return stmt.toString();
  }

}
