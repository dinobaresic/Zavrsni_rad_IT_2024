package org.eclipse.scout.apps.budgetbuddy.server.reports;

import org.eclipse.scout.apps.budgetbuddy.shared.reports.BudgetsTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IBudgetsService;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.math.BigDecimal;

public class BudgetsService implements IBudgetsService {
    @Override
    public BudgetsTablePageData getBudgetsTableData(SearchFilter filter) {
        BudgetsTablePageData pageData = new BudgetsTablePageData();
      StringBuffer  varname1 = new StringBuffer();
      varname1.append("SELECT id, ");
      varname1.append("       name, ");
      varname1.append("       amount, ");
      varname1.append("       date_created, ");
      varname1.append("       income_category_id, ");
      varname1.append("       wallet_id ");
      varname1.append("FROM   budgets ");
      varname1.append("WHERE  is_deleted IS false ");
      varname1.append(" INTO   :Id, :Name, :Amount, :DateCreated, :IncomeCategory, :Wallet");
      SQL.selectInto(varname1.toString(), pageData);
        return pageData;
    }

  @Override
  public void deleteBudget(Integer selectedValue) {

    String stmt = "UPDATE budgets SET is_deleted = true, deleted_at = now() WHERE id = :budgetId";
    SQL.update(stmt, new NVPair("budgetId", selectedValue));

    String stmt1 = "UPDATE wallet SET balance = balance - (SELECT amount FROM budgets WHERE id = :budgetId) WHERE id = (SELECT wallet_id FROM budgets WHERE id = :budgetId)";
    SQL.update(stmt1, new NVPair("budgetId", selectedValue));

  }

}
