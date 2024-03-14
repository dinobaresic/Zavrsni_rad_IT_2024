package org.eclipse.scout.apps.budgetbuddy.server.expenses;

import org.eclipse.scout.apps.budgetbuddy.shared.expenses.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
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
        String stmt = "INSERT INTO expenses (name, date, budget_id, category_id, amount) VALUES (:Name, :Date, :Budget, :Category, :Amount)";
        SQL.insert(stmt, formData);
        return formData;
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
