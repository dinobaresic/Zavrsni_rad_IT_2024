package org.eclipse.scout.apps.budgetbuddy.server.expenses;

import org.eclipse.scout.apps.budgetbuddy.shared.expenses.ExpensesTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.expenses.IExpensesService;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IBudgetsService;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.holders.BigDecimalHolder;
import org.eclipse.scout.rt.platform.holders.LongHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.math.BigDecimal;

public class ExpensesService implements IExpensesService {
    @Override
    public ExpensesTablePageData getExpensesTableData(SearchFilter filter) {
   //     setExpenseIsDeletedIfBudgetIsDeleted();
        ExpensesTablePageData pageData = new ExpensesTablePageData();
      StringBuffer  varname1 = new StringBuffer();
      varname1.append("SELECT id, ");
      varname1.append("       name, ");
      varname1.append("       category_id, ");
      varname1.append("       date, ");
      varname1.append("       amount, ");
      varname1.append("       wallet_id ");
      varname1.append("FROM   expenses ");
      varname1.append("WHERE  is_deleted IS false ");
      varname1.append(" INTO   :ID, :Name, :Category, :Date, :Amount, :Wallet");
      SQL.selectInto(varname1.toString(), pageData);
        return pageData;
    }

    /*
    private void setExpenseIsDeletedIfBudgetIsDeleted() {
        StringBuffer  varname1 = new StringBuffer();
        varname1.append("UPDATE bills ");
        varname1.append("SET    is_deleted = true, ");
        varname1.append("       deleted_at = now() ");
        varname1.append("WHERE  budget_id IN (SELECT id ");
        varname1.append("                    FROM   budgets ");
        varname1.append("                    WHERE  is_deleted = true) ");
        SQL.update(varname1.toString());
    }
     */


    @Override
    public void deleteExpense(Integer selectedValue) {
        String stmt = "UPDATE expenses SET is_deleted = true, deleted_at = now() WHERE id = :expenseId";
        SQL.update(stmt, new NVPair("expenseId", selectedValue));
        String stmt1 = "UPDATE wallet SET balance = balance + (SELECT amount FROM expenses WHERE id = :expenseId) WHERE id = (SELECT wallet_id FROM expenses WHERE id = :expenseId)";
        SQL.update(stmt1, new NVPair("expenseId", selectedValue));
    }


}
