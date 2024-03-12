package org.eclipse.scout.apps.budgetbuddy.client.reports;

import org.eclipse.scout.apps.budgetbuddy.client.common.AbstractAddMenu;
import org.eclipse.scout.apps.budgetbuddy.client.common.AbstractDeleteMenu;
import org.eclipse.scout.apps.budgetbuddy.client.informations.MessageBoxHelper;
import org.eclipse.scout.apps.budgetbuddy.client.informations.NotificationHelper;
import org.eclipse.scout.apps.budgetbuddy.client.reports.BudgetsTablePage.Table;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.BudgetsTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IBudgetsService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.messagebox.IMessageBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@Data(BudgetsTablePageData.class)
public class BudgetsTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IBudgetsService.class).getBudgetsTableData(filter));
    }



    @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("Budgets");
    }

    public class Table extends AbstractTable {

      @Order(1000)
      public class AddMenu extends AbstractAddMenu {

        @Override
        protected void execAction() {

          AddBudgetForm form = new AddBudgetForm();
          form.startNew();
          form.waitFor();
          if(form.isFormStored()){
            NotificationHelper.showSaveSuccessNotification();
            reloadPage();
          }

        }
      }

      @Order(2000)
      public class DeleteMenu extends AbstractDeleteMenu {
        @Override
        protected void execAction() {
          if (MessageBoxHelper.showDeleteConfirmationMessage() == IMessageBox.YES_OPTION) {
            NotificationHelper.showDeleteSuccessNotification();
            BEANS.get(IBudgetsService.class).deleteBill(getIdColumn().getSelectedValue());
            reloadPage();
          }

        }
      }

      @Override
      protected boolean getConfiguredAutoResizeColumns() {
        return true;
      }

      public IdColumn getIdColumn(){
        return getColumnSet().getColumnByClass(IdColumn.class);
      }

      public AmountColumn getAmountColumn() {
        return getColumnSet().getColumnByClass(AmountColumn.class);
      }

      public CurrentBalanceColumn getCurrentBalanceColumn() {
        return getColumnSet().getColumnByClass(CurrentBalanceColumn.class);
      }

      public ExpensesColumn getExpencesColumn() {
        return getColumnSet().getColumnByClass(ExpensesColumn.class);
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

      }

      @Order(2000)
      public class NameColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("BudgetName");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }


      @Order(3000)
      public class AmountColumn extends AbstractBigDecimalColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Amount");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(4000)
      public class ExpensesColumn extends AbstractBigDecimalColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Expenses");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(5000)
      public class CurrentBalanceColumn extends AbstractBigDecimalColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("CurrentBalance");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

    }
}
