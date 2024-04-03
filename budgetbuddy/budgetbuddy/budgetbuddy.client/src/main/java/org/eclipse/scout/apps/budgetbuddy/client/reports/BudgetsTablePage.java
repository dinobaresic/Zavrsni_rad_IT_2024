package org.eclipse.scout.apps.budgetbuddy.client.reports;

import org.eclipse.scout.apps.budgetbuddy.client.common.AbstractAddMenu;
import org.eclipse.scout.apps.budgetbuddy.client.common.AbstractDeleteMenu;
import org.eclipse.scout.apps.budgetbuddy.client.informations.MessageBoxHelper;
import org.eclipse.scout.apps.budgetbuddy.client.informations.NotificationHelper;
import org.eclipse.scout.apps.budgetbuddy.client.reports.BudgetsTablePage.Table;
import org.eclipse.scout.apps.budgetbuddy.shared.Icons;
import org.eclipse.scout.apps.budgetbuddy.shared.lookups.IncomeCategoriesLookupCall;
import org.eclipse.scout.apps.budgetbuddy.shared.lookups.WalletLookupCall;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.BudgetsTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IBudgetsService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.*;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.messagebox.IMessageBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(BudgetsTablePageData.class)
public class BudgetsTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Folder;
  }

  @Override
  protected String getConfiguredOverviewIconId() {
    return Icons.Folder;
  }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IBudgetsService.class).getBudgetsTableData(filter));
    }

  @Override
  protected void execPageActivated() {
    execLoadData(new SearchFilter());
  }




  @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("Budgets0");
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
            BEANS.get(IBudgetsService.class).deleteBudget(getIdColumn().getSelectedValue());
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


      public DateCreatedColumn getDateCreatedColumn() {
        return getColumnSet().getColumnByClass(DateCreatedColumn.class);
      }


      public IncomeCategoryColumn getIncomeCategoryColumn() {
        return getColumnSet().getColumnByClass(IncomeCategoryColumn.class);
      }

      public NameColumn getNameColumn() {
        return getColumnSet().getColumnByClass(NameColumn.class);
      }

      public WalletColumn getWalletColumn() {
        return getColumnSet().getColumnByClass(WalletColumn.class);
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

      @Order(1500)
      public class NameColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Name1");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(2000)
      public class IncomeCategoryColumn extends AbstractSmartColumn<Long> {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("CategoryName1");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }

        @Override
        protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
          return IncomeCategoriesLookupCall.class;
        }
      }


      @Order(3000)
      public class AmountColumn extends AbstractBigDecimalColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Amount00");
        }

        @Override
        protected void execDecorateCell(Cell cell, ITableRow row) {
          if (cell.getValue() != null) {
            cell.setBackgroundColor("C0FFC0");
          }
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(6000)
      public class DateCreatedColumn extends AbstractDateColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("DateCreated0");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }

        @Override
        protected String getConfiguredFormat() {
          return "dd.MM.yyyy";
        }
      }

      @Order(7000)
      public class WalletColumn extends AbstractSmartColumn<Long> {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("WalletTablePage");
        }

        @Override
        protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
          return WalletLookupCall.class;
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

    }
}
