package org.eclipse.scout.apps.budgetbuddy.server.report;

import org.eclipse.scout.apps.budgetbuddy.shared.report.BudgetSearchFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.report.BudgetsHistoryTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.report.IBudgetsHistoryService;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class BudgetsHistoryService implements IBudgetsHistoryService {
    @Override
    public BudgetsHistoryTablePageData getBudgetsHistoryTableData(BudgetSearchFormData filter) {
        BudgetsHistoryTablePageData pageData = new BudgetsHistoryTablePageData();

        StringBuffer varname1 = new StringBuffer();
      varname1.append("SELECT ");
      varname1.append("    'račun' AS type, name, date, price AS amount ");
      varname1.append("FROM ");
      varname1.append("    bills ");
      if (filter.getWallet().getValue() != null) {
        varname1.append(" WHERE  wallet_id = " + filter.getWallet().getValue() + " ");
      }
      varname1.append(" ");
      varname1.append("UNION ALL ");
      varname1.append(" ");
      varname1.append("SELECT ");
      varname1.append("    'posebni trošak' AS type, name, date, amount ");
      varname1.append("FROM ");
      varname1.append("    expenses ");
      if (filter.getWallet().getValue() != null) {
        varname1.append(" WHERE  wallet_id = " + filter.getWallet().getValue() + " ");
      }

      varname1.append("ORDER BY date DESC");

        varname1.append("INTO   :Type, :Name, :Date, :Amount");

        SQL.selectInto(varname1.toString(), pageData, filter);

        return pageData;
    }
}
