package org.eclipse.scout.apps.budgetbuddy.client.reports;

import org.eclipse.scout.apps.budgetbuddy.client.common.AbstractAddMenu;
import org.eclipse.scout.apps.budgetbuddy.client.common.AbstractDeleteMenu;
import org.eclipse.scout.apps.budgetbuddy.client.informations.MessageBoxHelper;
import org.eclipse.scout.apps.budgetbuddy.client.informations.NotificationHelper;
import org.eclipse.scout.apps.budgetbuddy.client.reports.BillsTablePage.Table;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.BillsTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IBillsService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.messagebox.IMessageBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.util.Set;

@Data(BillsTablePageData.class)
public class BillsTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IBillsService.class).getBillsTableData(filter));
    }

    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("BillsTablePage");
    }

    public class Table extends AbstractTable {

      @Order(1000)
      public class DeleteMenu extends AbstractDeleteMenu {

        @Override
        protected void execAction() {

          if (MessageBoxHelper.showDeleteConfirmationMessage() == IMessageBox.YES_OPTION) {
            NotificationHelper.showDeleteSuccessNotification();
            BEANS.get(IBillsService.class).deleteBill(getIdColumn().getSelectedValue());
            reloadPage();
          }

        }
      }

      @Order(2000)
      public class AddMenu extends AbstractAddMenu {

        @Override
        protected void execAction() {
          AddBillForm form = new AddBillForm();
          form.startNew();
          form.waitFor();
          if(form.isFormStored()) {
            NotificationHelper.showSaveSuccessNotification();
            reloadPage();
          }
        }
      }


      protected boolean getConfiguredAutoResizeColumns() {
        return true;
      }


      public PriceColumn getPriceColumn() {
        return getColumnSet().getColumnByClass(PriceColumn.class);
      }


      public AddressColumn getAddressColumn() {
        return getColumnSet().getColumnByClass(AddressColumn.class);
      }


      public DateColumn getDateColumn() {
        return getColumnSet().getColumnByClass(DateColumn.class);
      }


      public TaxAmountColumn getTaxAmountColumn() {
        return getColumnSet().getColumnByClass(TaxAmountColumn.class);
      }


      public NameColumn getNameColumn() {
        return getColumnSet().getColumnByClass(NameColumn.class);
      }

      public IdColumn getIdColumn() {
        return getColumnSet().getColumnByClass(IdColumn.class);
      }

      @Order(1000)
      public class IdColumn extends AbstractIntegerColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("MyNlsKey");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
        protected boolean getConfiguredDisplayable() {
          return false;
        }
      }

      @Order(2000)
      public class NameColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("MerchantName");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(3000)
      public class AddressColumn extends AbstractStringColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("MerchantAddress");
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
      public class TaxAmountColumn extends AbstractBigDecimalColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("TaxAmount");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }



      @Order(6000)
      public class PriceColumn extends AbstractBigDecimalColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Price");
        }


        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

    }
}
