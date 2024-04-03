package org.eclipse.scout.apps.budgetbuddy.server.wallet;

import org.eclipse.scout.apps.budgetbuddy.shared.wallet.IWalletService;
import org.eclipse.scout.apps.budgetbuddy.shared.wallet.WalletTablePageData;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class WalletService implements IWalletService {
    @Override
    public WalletTablePageData getWalletTableData(SearchFilter filter) {
        WalletTablePageData pageData = new WalletTablePageData();
      StringBuffer  varname1 = new StringBuffer();
      varname1.append("SELECT id, ");
      varname1.append("       name, ");
      varname1.append("       last_used, ");
      varname1.append("       balance ");
      varname1.append("FROM   wallet ");
      varname1.append("WHERE  is_deleted IS false ");
      varname1.append(" INTO   :Id, :Name, :Date, :Balance");
      SQL.selectInto(varname1.toString(), pageData);

        return pageData;
    }
}
