package org.eclipse.scout.apps.budgetbuddy.server.general;

import org.eclipse.scout.apps.budgetbuddy.shared.general.GeneralTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.general.IGeneralService;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringHolder;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class GeneralService implements IGeneralService {
  @Override
  public GeneralTablePageData getGeneralTableData(SearchFilter filter) {
    GeneralTablePageData pageData = new GeneralTablePageData();

    StringBuffer  varname1 = new StringBuffer();
    varname1.append("SELECT ");
    varname1.append("    TO_CHAR(date_created, 'MM') AS currentMonth, ");
    varname1.append("    COALESCE(SUM(expenses), 0) AS totalExpenses, ");
    varname1.append("    COALESCE(SUM(amount), 0) AS totalIncome, ");
    varname1.append("    COALESCE(SUM(amount), 0) - COALESCE(SUM(expenses), 0) AS Total ");
    varname1.append("FROM ");
    varname1.append("    budgets ");
    varname1.append("WHERE ");
    varname1.append("    is_deleted = false ");
    varname1.append("    AND EXTRACT(MONTH FROM date_created) = EXTRACT(MONTH FROM CURRENT_DATE) ");
    varname1.append("    AND EXTRACT(YEAR FROM date_created) = EXTRACT(YEAR FROM CURRENT_DATE) ");
    varname1.append("GROUP BY ");
    varname1.append("    TO_CHAR(date_created, 'MM') ");
    varname1.append("ORDER BY ");
    varname1.append("    TO_CHAR(date_created, 'MM');");
    varname1.append(" INTO   :CurrentMonth, :Expenses, :Income, :Total");

    SQL.selectInto(varname1.toString(), pageData);



    return pageData;
  }

  // Method to convert month number to month name
  private String convertMonthNumberToName(String monthNum) {
    switch (monthNum) {
      case "01":
        return "Siječanj";
      case "02":
        return "Veljača";
      case "03":
        return "Ožujak";
      case "04":
        return "Travanj";
      case "05":
        return "Svibanj";
      case "06":
        return "Lipanj";
      case "07":
        return "Srpanj";
      case "08":
        return "Kolovoz";
      case "09":
        return "Rujan";
      case "10":
        return "Listopad";
      case "11":
        return "Studeni";
      case "12":
        return "Prosinac";
      default:
        return "Nepoznat"; // Handle unknown month numbers
    }
  }
}
