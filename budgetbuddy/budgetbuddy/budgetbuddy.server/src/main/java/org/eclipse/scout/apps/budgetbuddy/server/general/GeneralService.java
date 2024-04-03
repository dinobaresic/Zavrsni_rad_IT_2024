package org.eclipse.scout.apps.budgetbuddy.server.general;

import org.eclipse.scout.apps.budgetbuddy.shared.general.GeneralDataFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.general.GeneralTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.general.IGeneralService;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringHolder;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class GeneralService implements IGeneralService {
  @Override
  public GeneralTablePageData getGeneralTableData(GeneralDataFormData filter) {
    GeneralTablePageData pageData = new GeneralTablePageData();

    StringBuffer  varname1 = new StringBuffer();
    varname1.append("SELECT ");
    varname1.append("    COALESCE(SUM(amount), 0) AS totalIncome ");
    varname1.append("FROM ");
    varname1.append("    budgets ");
    varname1.append("WHERE is_deleted = false ");
    if (filter.getWallet() != null) {
      varname1.append(" AND  wallet_id = " + filter.getWallet().getValue() + " ");
    }

    if (filter.getFrom().getValue() != null) {
      varname1.append(" AND  date_created >= '" + filter.getFrom().getValue() + "' ");
    }

    if (filter.getTo().getValue() != null) {
        varname1.append(" AND  date_created <= '" + filter.getTo().getValue() + "' ");
    }

    varname1.append(" INTO  :Income");

    SQL.selectInto(varname1.toString(), pageData, filter);



    return pageData;
  }

}
