package org.eclipse.scout.apps.budgetbuddy.shared.categories;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

import javax.annotation.Generated;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@Generated(value = "org.eclipse.scout.apps.budgetbuddy.client.categories.IncomeCategoryTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class IncomeCategoryTablePageData extends AbstractTablePageData {
    private static final long serialVersionUID = 1L;

    @Override
    public IncomeCategoryTableRowData addRow() {
        return (IncomeCategoryTableRowData) super.addRow();
    }

    @Override
    public IncomeCategoryTableRowData addRow(int rowState) {
        return (IncomeCategoryTableRowData) super.addRow(rowState);
    }

    @Override
    public IncomeCategoryTableRowData createRow() {
        return new IncomeCategoryTableRowData();
    }

    @Override
    public Class<? extends AbstractTableRowData> getRowType() {
        return IncomeCategoryTableRowData.class;
    }

    @Override
    public IncomeCategoryTableRowData[] getRows() {
        return (IncomeCategoryTableRowData[]) super.getRows();
    }

    @Override
    public IncomeCategoryTableRowData rowAt(int index) {
        return (IncomeCategoryTableRowData) super.rowAt(index);
    }

    public void setRows(IncomeCategoryTableRowData[] rows) {
        super.setRows(rows);
    }

    public static class IncomeCategoryTableRowData extends AbstractTableRowData {
        private static final long serialVersionUID = 1L;
    }
}
