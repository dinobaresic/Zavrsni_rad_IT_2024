package org.eclipse.scout.apps.budgetbuddy.client.search;

import org.eclipse.scout.apps.budgetbuddy.client.general.GeneralDataNodePage;
import org.eclipse.scout.apps.budgetbuddy.client.general.GeneralTablePage;
import org.eclipse.scout.apps.budgetbuddy.client.report.BudgetsHistoryTablePage;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractSearchOutline;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.scout.apps.budgetbuddy.shared.Icons;

import java.util.List;

/**
 * @author Dino
 */
@Order(2000)
public class SearchOutline extends AbstractOutline {


  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Reports");
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    super.execCreateChildPages(pageList);
    pageList.add(new GeneralTablePage());
    pageList.add(new BudgetsHistoryTablePage());

  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Info;
  }
}
