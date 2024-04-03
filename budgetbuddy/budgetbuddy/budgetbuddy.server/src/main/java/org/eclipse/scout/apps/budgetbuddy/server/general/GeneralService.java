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
    varname1.append("    TO_CHAR(date_created, 'MM') AS currentMonth, ");
    varname1.append("    COALESCE(SUM(expenses), 0) AS totalExpenses, ");
    varname1.append("    COALESCE(SUM(amount), 0) AS totalIncome, ");
    varname1.append("    COALESCE(SUM(amount), 0) - COALESCE(SUM(expenses), 0) AS Total ");
    varname1.append("FROM ");
    varname1.append("    budgets ");
    varname1.append("WHERE ");
    /*
    if (filter.getBudget().getValue() != null) {
      varname1.append(" WHERE  budget_id = " + filter.getBudget().getValue() + " ");
    }

     */
    varname1.append("    is_deleted = false ");
    varname1.append("    AND EXTRACT(MONTH FROM date_created) = EXTRACT(MONTH FROM CURRENT_DATE) ");
    varname1.append("    AND EXTRACT(YEAR FROM date_created) = EXTRACT(YEAR FROM CURRENT_DATE) ");
    varname1.append("GROUP BY ");
    varname1.append("    TO_CHAR(date_created, 'MM') ");
    varname1.append("ORDER BY ");
    varname1.append("    TO_CHAR(date_created, 'MM');");
    varname1.append(" INTO   :CurrentMonth, :Expenses, :Income, :Total");

    SQL.selectInto(varname1.toString(), pageData);



    return pageData;
  }

}
