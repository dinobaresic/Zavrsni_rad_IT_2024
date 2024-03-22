package org.eclipse.scout.apps.budgetbuddy.client.report;

import org.eclipse.scout.apps.budgetbuddy.client.report.BudgetsHistoryTablePage.Table;
import org.eclipse.scout.apps.budgetbuddy.shared.Icons;
import org.eclipse.scout.apps.budgetbuddy.shared.report.BudgetSearchFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.report.BudgetsHistoryTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.report.IBudgetsHistoryService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@Data(BudgetsHistoryTablePageData.class)
public class BudgetsHistoryTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Clock;
  }

  @Override
  protected String getConfiguredOverviewIconId() {
    return Icons.Clock;
  }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IBudgetsHistoryService.class).getBudgetsHistoryTableData((BudgetSearchFormData)filter.getFormData()));
    }

    @Override
    protected Class<? extends ISearchForm> getConfiguredSearchForm() {
        return BudgetSearchForm.class;
    }

    @Override
    protected boolean getConfiguredSearchRequired() {
        return true;
    }

    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("BudgetsHistory");
    }

    public class Table extends AbstractTable {

        @Override
        protected boolean getConfiguredAutoResizeColumns() {
            return true;
        }

        public AmountColumn getAmountColumn() {
            return getColumnSet().getColumnByClass(AmountColumn.class);
        }

        public DateColumn getDateColumn() {
            return getColumnSet().getColumnByClass(DateColumn.class);
        }

        public NameColumn getNameColumn() {
            return getColumnSet().getColumnByClass(NameColumn.class);
        }

        public TypeColumn getTypeColumn() {
            return getColumnSet().getColumnByClass(TypeColumn.class);
        }

        @Order(1000)
        public class NameColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Name");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }

        }

        @Order(1500)
        public class TypeColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Type");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(2000)
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

    }
}
