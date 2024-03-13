package org.eclipse.scout.apps.budgetbuddy.server.reports;

import org.eclipse.scout.apps.budgetbuddy.shared.reports.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class AddBillAiOcrService implements IAddBillAiOcrService {
    @Override
    public AddBillAiOcrFormData prepareCreate(AddBillAiOcrFormData formData) {

        return formData;
    }

    @Override
    public AddBillAiOcrFormData create(AddBillAiOcrFormData formData) {
        String stmt = "INSERT INTO bills (name, address, taxamount, price, date, budget_id) VALUES (:Name, :Address, :TaxAmount, :Amount, :Date, :Budget)";
        SQL.insert(stmt, formData);
        return formData;
    }

    @Override
    public AddBillAiOcrFormData load(AddBillAiOcrFormData formData) {

        return formData;
    }

    @Override
    public AddBillAiOcrFormData store(AddBillAiOcrFormData formData) {

        return formData;
    }
}
