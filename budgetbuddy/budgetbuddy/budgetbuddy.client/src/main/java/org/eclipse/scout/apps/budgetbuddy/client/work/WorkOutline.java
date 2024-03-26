package org.eclipse.scout.apps.budgetbuddy.client.work;

import java.util.List;

import org.eclipse.scout.apps.budgetbuddy.client.expenses.ExpensesTablePage;
import org.eclipse.scout.apps.budgetbuddy.client.general.GeneralDataNodePage;
import org.eclipse.scout.apps.budgetbuddy.client.reports.BillsTablePage;
import org.eclipse.scout.apps.budgetbuddy.client.reports.BudgetsTablePage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.text.TEXTS;

import org.eclipse.scout.apps.budgetbuddy.client.helloworld.HelloWorldPage;
import org.eclipse.scout.apps.budgetbuddy.shared.Icons;

/**
 * @author Dino
 */
@Order(1000)
public class WorkOutline extends AbstractOutline {

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    super.execCreateChildPages(pageList);
    pageList.add(new BudgetsTablePage());
    pageList.add(new GeneralDataNodePage());

  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Finance");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Pencil;
  }
}
