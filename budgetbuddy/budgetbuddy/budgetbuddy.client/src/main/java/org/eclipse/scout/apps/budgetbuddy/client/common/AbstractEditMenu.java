package org.eclipse.scout.apps.budgetbuddy.client.common;

import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.apps.budgetbuddy.shared.Icons;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import java.util.Set;
public class AbstractEditMenu extends AbstractMenu {
  @Override
  protected String getConfiguredText() {
    return TEXTS.get("Edit");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Pencil;
  }

  @Override
  protected Set<? extends IMenuType> getConfiguredMenuTypes() {
    return CollectionUtility.hashSet(TableMenuType.SingleSelection);
  }
}
