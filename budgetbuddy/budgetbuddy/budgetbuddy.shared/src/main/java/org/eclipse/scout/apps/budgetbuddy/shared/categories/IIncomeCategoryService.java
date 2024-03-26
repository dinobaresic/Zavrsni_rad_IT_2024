package org.eclipse.scout.apps.budgetbuddy.shared.categories;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IIncomeCategoryService extends IService {
    IncomeCategoryTablePageData getIncomeCategoryTableData(SearchFilter filter);
}
