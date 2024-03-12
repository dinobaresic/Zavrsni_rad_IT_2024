package org.eclipse.scout.apps.budgetbuddy.server.reports;

import org.eclipse.scout.apps.budgetbuddy.shared.reports.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class AddBillService implements IAddBillService {
    @Override
    public AddBillFormData prepareCreate(AddBillFormData formData) {

        return formData;
    }

    @Override
    public AddBillFormData create(AddBillFormData formData) {
      String stmt = "INSERT INTO bills (name, address, taxamount, price, date) VALUES (:Name, :Address, :TaxAmount, :Amount, :Date)";
      SQL.insert(stmt, formData);
        return formData;
    }

    @Override
    public AddBillFormData load(AddBillFormData formData) {

        return formData;
    }

    @Override
    public AddBillFormData store(AddBillFormData formData) {

        return formData;
    }
}
