package org.eclipse.scout.apps.budgetbuddy.server.reports;

import org.eclipse.scout.apps.budgetbuddy.shared.reports.BillsTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IBillsService;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

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
      varname1.append("       price ");
      varname1.append("FROM   bills");
//		varname1.append("WHERE  is_deleted IS false ");
      varname1.append(" INTO   :Id, :Name, :Address, :Date, :TaxAmount, :Price");
      SQL.selectInto(varname1.toString(), pageData);
        return pageData;
    }
}
