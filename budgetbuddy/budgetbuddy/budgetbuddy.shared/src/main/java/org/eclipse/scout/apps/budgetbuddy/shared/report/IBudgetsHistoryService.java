package org.eclipse.scout.apps.budgetbuddy.shared.report;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IBudgetsHistoryService extends IService {
    BudgetsHistoryTablePageData getBudgetsHistoryTableData(BudgetSearchFormData filter);
}
