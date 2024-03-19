package org.eclipse.scout.apps.budgetbuddy.client.general;

import org.eclipse.scout.apps.budgetbuddy.client.general.GeneralTablePage.Table;
import org.eclipse.scout.apps.budgetbuddy.shared.general.GeneralTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.general.IGeneralService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.math.BigDecimal;

@Data(GeneralTablePageData.class)
public class GeneralTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IGeneralService.class).getGeneralTableData(filter));
    }

    @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("CurrentMonthData");
    }

    @Override
    protected void execPageActivated() {
        execLoadData(new SearchFilter());
    }

    public class Table extends AbstractTable {

        @Override
        protected boolean getConfiguredAutoResizeColumns() {
            return true;
        }

        public CurrentMonthColumn getCurrentMonthColumn() {
            return getColumnSet().getColumnByClass(CurrentMonthColumn.class);
        }

        public ExpensesColumn getExpensesColumn() {
            return getColumnSet().getColumnByClass(ExpensesColumn.class);
        }

        public IncomeColumn getIncomeColumn() {
            return getColumnSet().getColumnByClass(IncomeColumn.class);
        }

        public TotalColumn getTotalColumn() {
            return getColumnSet().getColumnByClass(TotalColumn.class);
        }

        @Order(1000)
        public class CurrentMonthColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Month");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(2000)
        public class ExpensesColumn extends AbstractBigDecimalColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Expenses");
            }

            @Override
            protected void execDecorateCell(Cell cell, ITableRow row) {
                cell.setBackgroundColor("lightcoral");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(3000)
        public class IncomeColumn extends AbstractBigDecimalColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Income");
            }

            @Override
            protected void execDecorateCell(Cell cell, ITableRow row) {
                cell.setBackgroundColor("lightgreen");
            }

            @Override
            protected int getConfiguredWidth() {
                return 100;
            }
        }

        @Order(4000)
        public class TotalColumn extends AbstractBigDecimalColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Total");
            }

            @Override
            protected void execDecorateCell(Cell cell, ITableRow row) {
                if(getExpensesColumn().getValue(row) != null && getIncomeColumn().getValue(row) != null) {
                    BigDecimal expenses = getExpensesColumn().getValue(row);
                    BigDecimal income = getIncomeColumn().getValue(row);
                    BigDecimal total = income.subtract(expenses);
                    if(total.compareTo(BigDecimal.ZERO) <= 0) {
                        cell.setBackgroundColor("lightcoral");
                    }
                    else {
                        cell.setBackgroundColor("lightgreen");
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
