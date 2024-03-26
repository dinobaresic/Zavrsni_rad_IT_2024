package org.eclipse.scout.apps.budgetbuddy.client.categories;

import org.eclipse.scout.apps.budgetbuddy.client.categories.IncomeCategoryTablePage.Table;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.IIncomeCategoryService;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.IncomeCategoryTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@Data(IncomeCategoryTablePageData.class)
public class IncomeCategoryTablePage extends AbstractPageWithTable<Table> {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

    @Override
    protected void execLoadData(SearchFilter filter) {
        importPageData(BEANS.get(IIncomeCategoryService.class).getIncomeCategoryTableData(filter));
    }

    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("IncomeCategoryTablePage");
    }

    public class Table extends AbstractTable {

      @Override
      protected boolean getConfiguredAutoResizeColumns() {
        return true;
      }

      


    }
}
