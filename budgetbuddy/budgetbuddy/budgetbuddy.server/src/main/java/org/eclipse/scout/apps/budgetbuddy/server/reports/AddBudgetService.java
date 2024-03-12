package org.eclipse.scout.apps.budgetbuddy.server.reports;

import org.eclipse.scout.apps.budgetbuddy.shared.reports.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class AddBudgetService implements IAddBudgetService {
    @Override
    public AddBudgetFormData prepareCreate(AddBudgetFormData formData) {

        return formData;
    }

    @Override
    public AddBudgetFormData create(AddBudgetFormData formData) {

      String stmt = "INSERT INTO budgets (name, amount, expenses, current_balance) VALUES (:Name, :Amount, 0, :Amount)";
      SQL.insert(stmt, formData);
      return formData;

    }

    @Override
    public AddBudgetFormData load(AddBudgetFormData formData) {


        return formData;
    }

    @Override
    public AddBudgetFormData store(AddBudgetFormData formData) {

        return formData;
    }
}
