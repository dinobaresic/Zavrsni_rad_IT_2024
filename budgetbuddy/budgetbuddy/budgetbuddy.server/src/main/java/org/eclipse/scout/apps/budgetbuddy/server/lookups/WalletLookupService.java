package org.eclipse.scout.apps.budgetbuddy.server.lookups;

import org.eclipse.scout.apps.budgetbuddy.shared.lookups.IWalletLookupService;
import org.eclipse.scout.rt.server.jdbc.lookup.AbstractSqlLookupService;
import org.eclipse.scout.rt.server.services.lookup.AbstractLookupService;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;

import java.util.List;

public class WalletLookupService extends AbstractSqlLookupService<Long> implements IWalletLookupService {
  @Override
  protected String getConfiguredSqlSelect() {
    StringBuffer stmt = new StringBuffer();
    stmt.append(" SELECT id, name ");
    stmt.append(" FROM wallet ");
    stmt.append(" WHERE is_deleted IS FALSE ");
    stmt.append(" <key> AND id = :key </key> ");
    stmt.append(" <text> AND UPPER(name) LIKE UPPER('%'||:text||'%') </text><all></all> ");
    stmt.append(" ORDER BY name ");
    return stmt.toString();
  }
}
