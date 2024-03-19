package org.eclipse.scout.apps.budgetbuddy.shared.general;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

import javax.annotation.Generated;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@Generated(value = "org.eclipse.scout.apps.budgetbuddy.client.general.GeneralDataTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class GeneralDataTablePageData extends AbstractTablePageData {
    private static final long serialVersionUID = 1L;

    @Override
    public GeneralDataTableRowData addRow() {
        return (GeneralDataTableRowData) super.addRow();
    }

    @Override
    public GeneralDataTableRowData addRow(int rowState) {
        return (GeneralDataTableRowData) super.addRow(rowState);
    }

    @Override
    public GeneralDataTableRowData createRow() {
        return new GeneralDataTableRowData();
    }

    @Override
    public Class<? extends AbstractTableRowData> getRowType() {
        return GeneralDataTableRowData.class;
    }

    @Override
    public GeneralDataTableRowData[] getRows() {
        return (GeneralDataTableRowData[]) super.getRows();
    }

    @Override
    public GeneralDataTableRowData rowAt(int index) {
        return (GeneralDataTableRowData) super.rowAt(index);
    }

    public void setRows(GeneralDataTableRowData[] rows) {
        super.setRows(rows);
    }

    public static class GeneralDataTableRowData extends AbstractTableRowData {
        private static final long serialVersionUID = 1L;
    }
}
