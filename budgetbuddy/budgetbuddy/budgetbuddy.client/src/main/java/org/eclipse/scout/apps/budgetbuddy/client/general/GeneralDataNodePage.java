package org.eclipse.scout.apps.budgetbuddy.client.general;


import org.eclipse.scout.apps.budgetbuddy.client.expenses.ExpensesTablePage;
import org.eclipse.scout.apps.budgetbuddy.client.reports.BillsTablePage;
import org.eclipse.scout.apps.budgetbuddy.shared.Icons;
import org.eclipse.scout.apps.budgetbuddy.shared.general.GeneralDataTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.text.TEXTS;

import java.util.List;


@Data(GeneralDataTablePageData.class)
public class GeneralDataNodePage extends AbstractPageWithNodes {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

    @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("ExpenseOverview");
    }

  @Override
  protected boolean getConfiguredShowTileOverview() {
    return true;
  }

  @Override
    protected String getConfiguredIconId() {
        return Icons.PersonSolid;
    }

  @Override
  protected String getConfiguredOverviewIconId() {
    return Icons.Chart;
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    super.execCreateChildPages(pageList);
    pageList.add(new ExpensesTablePage());
    pageList.add(new BillsTablePage());
  }
}
