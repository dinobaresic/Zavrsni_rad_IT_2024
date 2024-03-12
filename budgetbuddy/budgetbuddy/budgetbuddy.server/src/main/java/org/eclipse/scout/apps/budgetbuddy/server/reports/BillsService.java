package org.eclipse.scout.apps.budgetbuddy.server.reports;

import org.eclipse.scout.apps.budgetbuddy.shared.reports.BillsTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IBillsService;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IBudgetsService;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.holders.BigDecimalHolder;
import org.eclipse.scout.rt.platform.holders.LongHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.math.BigDecimal;

public class BillsService implements IBillsService {
    @Override
    public BillsTablePageData getBillsTableData(SearchFilter filter) {
        BillsTablePageData pageData = new BillsTablePageData();
      StringBuffer  varname1 = new StringBuffer();
      varname1.append("SELECT id, ");
      varname1.append("       name, ");
      varname1.append("       address, ");
      varname1.append("       date, ");
      varname1.append("       taxamount, ");
      varname1.append("       price, ");
      varname1.append("       budget_id ");
      varname1.append("FROM   bills ");
		varname1.append("WHERE  is_deleted IS false ");
      varname1.append(" INTO   :Id, :Name, :Address, :Date, :TaxAmount, :Price, :Budget");
      SQL.selectInto(varname1.toString(), pageData);
        return pageData;
    }

  @Override
  public void deleteBill(Integer selectedValue) {
    String stmt = "UPDATE bills SET is_deleted = true, deleted_at = now() WHERE id = :billId";
    SQL.update(stmt, new NVPair("billId", selectedValue));
    BEANS.get(IBudgetsService.class).updateBudgetAmount(getBillAmount(selectedValue), getBudgetId(selectedValue));
  }

  private Long getBudgetId(Integer selectedValue) {
    LongHolder budgetId = new LongHolder();
    StringBuffer  varname1 = new StringBuffer();
    varname1.append("SELECT budget_id ");
    varname1.append("FROM   bills ");
    varname1.append("WHERE  id = :billId ");
    varname1.append(" INTO   :BudgetId");
    SQL.select(varname1.toString(), new NVPair("billId", selectedValue), new NVPair("BudgetId", budgetId));
    return budgetId.getValue();
  }

  private BigDecimal getBillAmount(Integer selectedValue) {
    BigDecimalHolder amount = new BigDecimalHolder();
    StringBuffer  varname1 = new StringBuffer();
    varname1.append("SELECT price ");
    varname1.append("FROM   bills ");
    varname1.append("WHERE  id = :billId ");
    varname1.append(" INTO   :Amount");
    SQL.select(varname1.toString(), new NVPair("billId", selectedValue), new NVPair("Amount", amount));
    return amount.getValue();
  }

  @Override
  public boolean checkBudget(Long budgetId, BigDecimal amount) {
    BigDecimalHolder currentBalance = new BigDecimalHolder();
    StringBuffer  varname1 = new StringBuffer();
    varname1.append("SELECT current_balance ");
    varname1.append("FROM   budgets ");
    varname1.append("WHERE  id = :budgetId ");
    varname1.append(" INTO   :CurrentBalance");
    SQL.select(varname1.toString(), new NVPair("budgetId", budgetId), new NVPair("CurrentBalance", currentBalance));
    if (currentBalance.getValue().compareTo(amount) >= 0) {
      return true;
    }

    return false;
  }


}
