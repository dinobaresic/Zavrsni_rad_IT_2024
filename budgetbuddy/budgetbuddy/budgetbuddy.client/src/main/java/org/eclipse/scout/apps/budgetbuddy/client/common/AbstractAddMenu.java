package org.eclipse.scout.apps.budgetbuddy.client.common;

import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import java.util.Set;
import org.eclipse.scout.apps.budgetbuddy.shared.Icons;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
public class AbstractAddMenu extends AbstractMenu {
  @Override
  protected String getConfiguredText() {
    return TEXTS.get("Add0");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.PlusBold;
  }

  @Override
  protected Set<? extends IMenuType> getConfiguredMenuTypes() {
    return CollectionUtility.hashSet(TableMenuType.EmptySpace);
  }
}
