package org.eclipse.scout.apps.budgetbuddy.server.reports;

import org.eclipse.scout.apps.budgetbuddy.shared.reports.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.IntegerHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringHolder;
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
        IntegerHolder id = new IntegerHolder();
        String stmt = "INSERT INTO bills (name, address, taxamount, price, date, budget_id) VALUES (:Name, :Address, :TaxAmount, :Amount, :Date, :Budget) RETURNING id INTO :id";
        SQL.selectInto(stmt, formData, new NVPair("id", id));
        insertIntoAllExpenses(formData, id.getValue());
        return formData;
    }

    private void insertIntoAllExpenses(AddBillAiOcrFormData formData, Integer id) {
        IntegerHolder billId = new IntegerHolder();
        billId.setValue(id);
        StringHolder type = new StringHolder();
        type.setValue("Bill");
        String stmt = "INSERT INTO all_expenses (name, date, type, amount, budget_id, bill_id) VALUES (:Name, :Date, :Type, :Amount, :Budget, :billId)";
        SQL.insert(stmt, formData, new NVPair("billId", billId), new NVPair("Type", type));
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
