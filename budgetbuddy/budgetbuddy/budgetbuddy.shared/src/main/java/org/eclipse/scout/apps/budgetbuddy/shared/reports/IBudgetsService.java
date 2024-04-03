package org.eclipse.scout.apps.budgetbuddy.shared.reports;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.math.BigDecimal;

@TunnelToServer
public interface IBudgetsService extends IService {
    BudgetsTablePageData getBudgetsTableData(SearchFilter filter);
  void deleteBudget(Integer selectedValue);
}
