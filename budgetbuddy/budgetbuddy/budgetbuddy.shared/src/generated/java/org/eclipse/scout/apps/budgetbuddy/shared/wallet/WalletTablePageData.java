package org.eclipse.scout.apps.budgetbuddy.shared.wallet;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@Generated(value = "org.eclipse.scout.apps.budgetbuddy.client.wallet.WalletTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class WalletTablePageData extends AbstractTablePageData {
    private static final long serialVersionUID = 1L;

    @Override
    public WalletTableRowData addRow() {
        return (WalletTableRowData) super.addRow();
    }

    @Override
    public WalletTableRowData addRow(int rowState) {
        return (WalletTableRowData) super.addRow(rowState);
    }

    @Override
    public WalletTableRowData createRow() {
        return new WalletTableRowData();
    }

    @Override
    public Class<? extends AbstractTableRowData> getRowType() {
        return WalletTableRowData.class;
    }

    @Override
    public WalletTableRowData[] getRows() {
        return (WalletTableRowData[]) super.getRows();
    }

    @Override
    public WalletTableRowData rowAt(int index) {
        return (WalletTableRowData) super.rowAt(index);
    }

    public void setRows(WalletTableRowData[] rows) {
        super.setRows(rows);
    }

    public static class WalletTableRowData extends AbstractTableRowData {
        private static final long serialVersionUID = 1L;
        public static final String id = "id";
        public static final String name = "name";
        public static final String date = "date";
        public static final String balance = "balance";
        private Integer m_id;
        private String m_name;
        private Date m_date;
        private BigDecimal m_balance;

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

        public Date getDate() {
            return m_date;
        }

        public void setDate(Date newDate) {
            m_date = newDate;
        }

        public BigDecimal getBalance() {
            return m_balance;
        }

        public void setBalance(BigDecimal newBalance) {
            m_balance = newBalance;
        }
    }
}
