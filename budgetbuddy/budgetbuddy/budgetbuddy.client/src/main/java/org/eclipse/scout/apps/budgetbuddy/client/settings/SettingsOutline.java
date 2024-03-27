package org.eclipse.scout.apps.budgetbuddy.client.settings;

import org.eclipse.scout.apps.budgetbuddy.client.categories.CategoriesTablePage;
import org.eclipse.scout.apps.budgetbuddy.client.categories.IncomeCategoryTablePage;
import org.eclipse.scout.apps.budgetbuddy.client.expenses.ExpensesTablePage;
import org.eclipse.scout.apps.budgetbuddy.client.reports.BillsTablePage;
import org.eclipse.scout.apps.budgetbuddy.client.reports.BudgetsTablePage;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.platform.text.TEXTS;

import org.eclipse.scout.apps.budgetbuddy.shared.Icons;

import java.util.List;

/**
 * @author Dino
 */
@Order(3000)
public class SettingsOutline extends AbstractOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Groups0");
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    super.execCreateChildPages(pageList);
    pageList.add(new CategoriesTablePage());
    pageList.add(new IncomeCategoryTablePage());
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.PersonSolid;
  }
}
