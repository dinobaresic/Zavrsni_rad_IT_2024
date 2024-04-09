package org.eclipse.scout.apps.budgetbuddy.client.categories;

import org.eclipse.scout.apps.budgetbuddy.client.categories.CategoriesTablePage.Table;
import org.eclipse.scout.apps.budgetbuddy.client.common.AbstractAddMenu;
import org.eclipse.scout.apps.budgetbuddy.client.informations.MessageBoxHelper;
import org.eclipse.scout.apps.budgetbuddy.client.informations.NotificationHelper;
import org.eclipse.scout.apps.budgetbuddy.shared.Icons;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.AddCategoryFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.CategoriesTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.ICategoriesService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.util.Set;

@Data(CategoriesTablePageData.class)
public class CategoriesTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

  @Override
  protected String getConfiguredOverviewIconId() {
    return Icons.Group;
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Group;
  }

  @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(ICategoriesService.class).getCategoriesTableData(filter));
    }

    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("CategoriesTablePage00");
    }

    public class Table extends AbstractTable {

      @Order(1000)
      public class AddMenu extends AbstractAddMenu {

        @Override
        protected void execAction() {
          AddCategoryForm form = new AddCategoryForm();
          form.startNew();
          form.waitFor();
          if (form.isFormStored()) {
            NotificationHelper.showSaveSuccessNotification();
            reloadPage();
          }
        }
      }

      @Override
      protected boolean getConfiguredAutoResizeColumns() {
        return true;
      }

      public IdColumn getIdColumn() {
        return getColumnSet().getColumnByClass(IdColumn.class);
      }

      public NameColumn getNameColumn() {
        return getColumnSet().getColumnByClass(NameColumn.class);
      }


      @Order(1000)
      public class IdColumn extends AbstractIntegerColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("MyColumnName");
        }

        @Override
        protected boolean getConfiguredDisplayable() {
          return false;
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(2000)
      public class NameColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("CategoryName1");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }


    }
}
