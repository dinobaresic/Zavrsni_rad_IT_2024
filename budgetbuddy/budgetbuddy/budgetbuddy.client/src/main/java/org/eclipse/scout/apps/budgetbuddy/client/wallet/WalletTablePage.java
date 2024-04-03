package org.eclipse.scout.apps.budgetbuddy.client.wallet;

import org.eclipse.scout.apps.budgetbuddy.client.wallet.WalletTablePage.Table;
import org.eclipse.scout.apps.budgetbuddy.shared.wallet.IWalletService;
import org.eclipse.scout.apps.budgetbuddy.shared.wallet.WalletTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.*;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.math.BigDecimal;

@Data(WalletTablePageData.class)
public class WalletTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IWalletService.class).getWalletTableData(filter));
    }

    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("WalletTablePage");
    }

    public class Table extends AbstractTable {

      @Override
      protected boolean getConfiguredAutoResizeColumns() {
        return true;
      }

      public BalanceColumn getBalanceColumn() {
        return getColumnSet().getColumnByClass(BalanceColumn.class);
      }

      public DateColumn getDateColumn() {
        return getColumnSet().getColumnByClass(DateColumn.class);
      }

      public NameColumn getNameColumn() {
        return getColumnSet().getColumnByClass(NameColumn.class);
      }

      @Order(1000)
      public class IdColumn extends AbstractIntegerColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Id");

        }
        @Override
        protected boolean getConfiguredVisible() {
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
          return TEXTS.get("Name11");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(3000)
      public class DateColumn extends AbstractDateTimeColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("LastUsed");
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

      @Order(4000)
      public class BalanceColumn extends AbstractBigDecimalColumn {
        @Override
        protected String getConfiguredHeaderText() {
          return TEXTS.get("Balance");
        }

        @Override
        protected void execDecorateCell(Cell cell, ITableRow row) {
          if (cell.getValue() != null) {
            if (((BigDecimal) cell.getValue()).compareTo(BigDecimal.ZERO) < 0) {
              cell.setBackgroundColor("FFC0C0");
            }else if(((BigDecimal) cell.getValue()).compareTo(BigDecimal.ZERO) > 0){
              cell.setBackgroundColor("C0FFC0");
            }else {
              cell.setBackgroundColor("FFFDD0");
            }
          }
        }

        @Override
        protected int getConfiguredWidth() {
          return 100;
        }
      }

    }
}
