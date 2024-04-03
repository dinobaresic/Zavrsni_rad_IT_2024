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
      String insertStmt = "INSERT INTO expenses (name, date, category_id, amount, wallet_id) VALUES (:Name, :Date, :Category, :Amount, :Wallet)";
      SQL.insert(insertStmt, formData);

      String updateStmt = "UPDATE wallet SET balance = balance - :Amount, last_used = now() WHERE id = :Wallet";
      SQL.update(updateStmt, formData);
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
