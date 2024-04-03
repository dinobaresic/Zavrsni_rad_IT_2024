package org.eclipse.scout.apps.budgetbuddy.client.general;

import org.eclipse.scout.apps.budgetbuddy.client.general.GeneralTablePage.Table;
import org.eclipse.scout.apps.budgetbuddy.client.report.BudgetSearchForm;
import org.eclipse.scout.apps.budgetbuddy.shared.Icons;
import org.eclipse.scout.apps.budgetbuddy.shared.general.GeneralDataFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.general.GeneralTablePageData;
import org.eclipse.scout.apps.budgetbuddy.shared.general.IGeneralService;
import org.eclipse.scout.apps.budgetbuddy.shared.report.BudgetSearchFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.report.IBudgetsHistoryService;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
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
  protected String getConfiguredIconId() {
    return Icons.Calendar;
  }

  @Override
  protected String getConfiguredOverviewIconId() {
    return Icons.Calendar;
  }


  @Override
  protected void execLoadData(SearchFilter filter) {
    importPageData(BEANS.get(IGeneralService.class).getGeneralTableData((GeneralDataFormData)filter.getFormData()));
  }

  @Override
  protected Class<? extends ISearchForm> getConfiguredSearchForm() {
    return GeneralDataSearchForm.class;
  }


  @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("ReportWalletAndDate");
    }

    @Override
    protected boolean getConfiguredSearchRequired() {
        return true;
    }

    public class Table extends AbstractTable {

        @Override
        protected boolean getConfiguredAutoResizeColumns() {
            return true;
        }


        public IncomeColumn getIncomeColumn() {
            return getColumnSet().getColumnByClass(IncomeColumn.class);
        }




        @Order(2000)
        public class IncomeColumn extends AbstractBigDecimalColumn {
            @Override
            protected String getConfiguredHeaderText() {
                return TEXTS.get("Income00");
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


    }
}
