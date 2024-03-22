package org.eclipse.scout.apps.budgetbuddy.client.expenses;

import org.eclipse.scout.apps.budgetbuddy.client.common.AbstractAddMenu;
import org.eclipse.scout.apps.budgetbuddy.client.common.AbstractDeleteMenu;
import org.eclipse.scout.apps.budgetbuddy.client.expenses.ExpensesTablePage.Table;
import org.eclipse.scout.apps.budgetbuddy.client.informations.MessageBoxHelper;
import org.eclipse.scout.apps.budgetbuddy.client.informations.NotificationHelper;
import org.eclipse.scout.apps.budgetbuddy.shared.Icons;
import org.eclipse.scout.apps.budgetbuddy.shared.expenses.ExpensesTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.expenses.IExpensesService;
import org.eclipse.scout.apps.budgetbuddy.shared.lookups.BudgetLookupCall;
import org.eclipse.scout.apps.budgetbuddy.shared.lookups.CategoriesLookupCall;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IBudgetsService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.*;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.messagebox.IMessageBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import java.math.BigDecimal;
import java.util.Set;

@Data(ExpensesTablePageData.class)
public class ExpensesTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

  @Override
  protected String getConfiguredIconId() {
    return Icons.File;
  }

  @Override
  protected String getConfiguredOverviewIconId() {
    return Icons.File;
  }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IExpensesService.class).getExpensesTableData(filter));
    }

    @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("ParticularExpenses");
    }

    public class Table extends AbstractTable {

      @Order(1000)
      public class AddMenu extends AbstractAddMenu {

        @Override
        protected void execAction() {

          AddExpenseForm form = new AddExpenseForm();
            form.startNew();
            form.waitFor();
            if (form.isFormStored()) {
              BigDecimal amount = form.getAmountField().getValue();
              Long budgetId = form.getBudgetField().getValue();
              BEANS.get(IBudgetsService.class).substractBudgetAmount(amount, budgetId);
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
                BEANS.get(IExpensesService.class).deleteExpense(getIdColumn().getSelectedValue());
                reloadPage();
             }
        }
      }

      @Override
      protected boolean getConfiguredAutoResizeColumns() {
        return true;
      }

      public AmountColumn getAmountColumn() {
        return getColumnSet().getColumnByClass(AmountColumn.class);
      }

      public BudgetColumn getBudgetColumn() {
        return getColumnSet().getColumnByClass(BudgetColumn.class);
      }

      public CategoryColumn getCategotryColumn() {
        return getColumnSet().getColumnByClass(CategoryColumn.class);
      }

      public DateColumn getDateColumn() {
        return getColumnSet().getColumnByClass(DateColumn.class);
      }

      public NameColumn getNameColumn() {
        return getColumnSet().getColumnByClass(NameColumn.class);
      }

      public  IDColumn getIdColumn() {
        return getColumnSet().getColumnByClass(IDColumn.class);
      }


      @Order(1000)
      public class IDColumn extends AbstractIntegerColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Id");
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
          return TEXTS.get("ExpenseName");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(3000)
      public class CategoryColumn extends AbstractSmartColumn<Long> {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Category");
        }

        @Override
        protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
          return CategoriesLookupCall.class;
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(4000)
      public class DateColumn extends AbstractDateColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Date");
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

      @Order(5000)
      public class BudgetColumn extends AbstractSmartColumn<Long> {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Budget");
        }

        @Override
        protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
          return BudgetLookupCall.class;
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(6000)
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




    }
}
