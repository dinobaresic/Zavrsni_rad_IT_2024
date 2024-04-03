package org.eclipse.scout.apps.budgetbuddy.server.reports;

import org.eclipse.scout.apps.budgetbuddy.shared.reports.BillsTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IBillsService;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IBudgetsService;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.holders.BigDecimalHolder;
import org.eclipse.scout.rt.platform.holders.LongHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.math.BigDecimal;

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
      varname1.append("       price, ");
      varname1.append("       wallet_id ");
      varname1.append("FROM   bills ");
		varname1.append("WHERE  is_deleted IS false ");
      varname1.append(" INTO   :Id, :Name, :Address, :Date, :TaxAmount, :Price, :Wallet");
      SQL.selectInto(varname1.toString(), pageData);
        return pageData;
    }


  @Override
  public void deleteBill(Integer selectedValue) {
    String stmt = "UPDATE bills SET is_deleted = true, deleted_at = now() WHERE id = :billId";
    SQL.update(stmt, new NVPair("billId", selectedValue));
    String stmt1 = "UPDATE wallet SET balance = balance + (SELECT price FROM bills WHERE id = :billId) WHERE id = (SELECT wallet_id FROM bills WHERE id = :billId)";
    SQL.update(stmt1, new NVPair("billId", selectedValue));

  }




}
