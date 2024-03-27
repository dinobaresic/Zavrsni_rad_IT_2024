package org.eclipse.scout.apps.budgetbuddy.server.expenses;

import org.eclipse.scout.apps.budgetbuddy.shared.expenses.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.IntegerHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringHolder;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class AddExpenseService implements IAddExpenseService {
    @Override
    public AddExpenseFormData prepareCreate(AddExpenseFormData formData) {

        return formData;
    }

    @Override
    public AddExpenseFormData create(AddExpenseFormData formData) {
        IntegerHolder expenseId = new IntegerHolder();
        String stmt = "INSERT INTO expenses (name, date, budget_id, category_id, amount) VALUES (:Name, :Date, :Budget, :Category, :Amount) RETURNING id INTO :expenseId";
        SQL.selectInto(stmt, formData, new NVPair("expenseId", expenseId));
        insertIntoAllExpenses(formData, expenseId.getValue());
        return formData;
    }

    private void insertIntoAllExpenses(AddExpenseFormData formData, Integer id) {
        IntegerHolder expenseId = new IntegerHolder();
        expenseId.setValue(id);
        StringHolder type = new StringHolder();
        type.setValue("Posebni trošak");
        String stmt = "INSERT INTO all_expenses (name, date, type, amount, budget_id, expense_id) VALUES (:Name, :Date, :Type, :Amount, :Budget, :ExpenseId)";
        SQL.insert(stmt, formData, new NVPair("ExpenseId", expenseId), new NVPair("Type", type));
    }

    @Override
    public AddExpenseFormData load(AddExpenseFormData formData) {

        return formData;
    }

    @Override
    public AddExpenseFormData store(AddExpenseFormData formData) {

        return formData;
    }
}
