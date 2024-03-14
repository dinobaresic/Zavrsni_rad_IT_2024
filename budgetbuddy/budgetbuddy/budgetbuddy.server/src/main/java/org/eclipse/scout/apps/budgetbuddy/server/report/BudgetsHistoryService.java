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

        varname1.append("SELECT name, ");
        varname1.append("       type, ");
        varname1.append("       date, ");
        varname1.append("       amount ");
        varname1.append("FROM   all_expenses ");

        if (filter.getBudget().getValue() != null) {
            varname1.append(" WHERE  budget_id = " + filter.getBudget().getValue() + " ");
        }

        varname1.append("INTO   :Name, :Type, :Date, :Amount");

        SQL.selectInto(varname1.toString(), pageData, filter);

        return pageData;
    }
}
