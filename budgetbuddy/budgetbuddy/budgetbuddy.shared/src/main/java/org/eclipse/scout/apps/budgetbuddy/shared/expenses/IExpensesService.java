package org.eclipse.scout.apps.budgetbuddy.shared.expenses;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IExpensesService extends IService {
    ExpensesTablePageData getExpensesTableData(SearchFilter filter);

    void deleteExpense(Integer selectedValue);
}
