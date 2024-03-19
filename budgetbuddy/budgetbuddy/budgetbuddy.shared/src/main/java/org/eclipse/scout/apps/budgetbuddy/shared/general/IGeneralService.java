package org.eclipse.scout.apps.budgetbuddy.shared.general;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IGeneralService extends IService {
    GeneralTablePageData getGeneralTableData(SearchFilter filter);
}
