package org.eclipse.scout.apps.budgetbuddy.shared.wallet;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IWalletService extends IService {
    WalletTablePageData getWalletTableData(SearchFilter filter);
}
