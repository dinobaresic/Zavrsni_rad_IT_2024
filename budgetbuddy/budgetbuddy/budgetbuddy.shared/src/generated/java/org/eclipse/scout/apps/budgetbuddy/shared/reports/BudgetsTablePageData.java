package org.eclipse.scout.apps.budgetbuddy.shared.reports;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@Generated(value = "org.eclipse.scout.apps.budgetbuddy.client.reports.BudgetsTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class BudgetsTablePageData extends AbstractTablePageData {
    private static final long serialVersionUID = 1L;

    @Override
    public BudgetsTableRowData addRow() {
        return (BudgetsTableRowData) super.addRow();
    }

    @Override
    public BudgetsTableRowData addRow(int rowState) {
        return (BudgetsTableRowData) super.addRow(rowState);
    }

    @Override
    public BudgetsTableRowData createRow() {
        return new BudgetsTableRowData();
    }

    @Override
    public Class<? extends AbstractTableRowData> getRowType() {
        return BudgetsTableRowData.class;
    }

    @Override
    public BudgetsTableRowData[] getRows() {
        return (BudgetsTableRowData[]) super.getRows();
    }

    @Override
    public BudgetsTableRowData rowAt(int index) {
        return (BudgetsTableRowData) super.rowAt(index);
    }

    public void setRows(BudgetsTableRowData[] rows) {
        super.setRows(rows);
    }

    public static class BudgetsTableRowData extends AbstractTableRowData {
        private static final long serialVersionUID = 1L;
        public static final String id = "id";
        public static final String name = "name";
        public static final String incomeCategory = "incomeCategory";
        public static final String amount = "amount";
        public static final String dateCreated = "dateCreated";
        public static final String wallet = "wallet";
        private Integer m_id;
        private String m_name;
        private Long m_incomeCategory;
        private BigDecimal m_amount;
        private Date m_dateCreated;
        private Long m_wallet;

        public Integer getId() {
            return m_id;
        }

        public void setId(Integer newId) {
            m_id = newId;
        }

        public String getName() {
            return m_name;
        }

        public void setName(String newName) {
            m_name = newName;
        }

        public Long getIncomeCategory() {
            return m_incomeCategory;
        }

        public void setIncomeCategory(Long newIncomeCategory) {
            m_incomeCategory = newIncomeCategory;
        }

        public BigDecimal getAmount() {
            return m_amount;
        }

        public void setAmount(BigDecimal newAmount) {
            m_amount = newAmount;
        }

        public Date getDateCreated() {
            return m_dateCreated;
        }

        public void setDateCreated(Date newDateCreated) {
            m_dateCreated = newDateCreated;
        }

        public Long getWallet() {
            return m_wallet;
        }

        public void setWallet(Long newWallet) {
            m_wallet = newWallet;
        }
    }
}
