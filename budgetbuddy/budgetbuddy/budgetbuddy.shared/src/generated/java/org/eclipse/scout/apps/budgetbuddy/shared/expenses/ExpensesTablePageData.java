package org.eclipse.scout.apps.budgetbuddy.shared.expenses;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@Generated(value = "org.eclipse.scout.apps.budgetbuddy.client.expenses.ExpensesTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class ExpensesTablePageData extends AbstractTablePageData {
    private static final long serialVersionUID = 1L;

    @Override
    public ExpensesTableRowData addRow() {
        return (ExpensesTableRowData) super.addRow();
    }

    @Override
    public ExpensesTableRowData addRow(int rowState) {
        return (ExpensesTableRowData) super.addRow(rowState);
    }

    @Override
    public ExpensesTableRowData createRow() {
        return new ExpensesTableRowData();
    }

    @Override
    public Class<? extends AbstractTableRowData> getRowType() {
        return ExpensesTableRowData.class;
    }

    @Override
    public ExpensesTableRowData[] getRows() {
        return (ExpensesTableRowData[]) super.getRows();
    }

    @Override
    public ExpensesTableRowData rowAt(int index) {
        return (ExpensesTableRowData) super.rowAt(index);
    }

    public void setRows(ExpensesTableRowData[] rows) {
        super.setRows(rows);
    }

    public static class ExpensesTableRowData extends AbstractTableRowData {
        private static final long serialVersionUID = 1L;
        public static final String ID = "ID";
        public static final String name = "name";
        public static final String category = "category";
        public static final String date = "date";
        public static final String budget = "budget";
        public static final String amount = "amount";
        private Integer m_ID;
        private String m_name;
        private Long m_category;
        private Date m_date;
        private Long m_budget;
        private BigDecimal m_amount;

        public Integer getID() {
            return m_ID;
        }

        public void setID(Integer newID) {
            m_ID = newID;
        }

        public String getName() {
            return m_name;
        }

        public void setName(String newName) {
            m_name = newName;
        }

        public Long getCategory() {
            return m_category;
        }

        public void setCategory(Long newCategory) {
            m_category = newCategory;
        }

        public Date getDate() {
            return m_date;
        }

        public void setDate(Date newDate) {
            m_date = newDate;
        }

        public Long getBudget() {
            return m_budget;
        }

        public void setBudget(Long newBudget) {
            m_budget = newBudget;
        }

        public BigDecimal getAmount() {
            return m_amount;
        }

        public void setAmount(BigDecimal newAmount) {
            m_amount = newAmount;
        }
    }
}